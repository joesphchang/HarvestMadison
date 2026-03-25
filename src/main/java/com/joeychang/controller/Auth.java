package com.joeychang.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeychang.auth.*;
import com.joeychang.entity.User;
import com.joeychang.persistence.GenericDao;
import com.joeychang.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet(
        urlPatterns = {"/auth"}
)
/**
 * Inspired by: https://stackoverflow.com/questions/52144721/how-to-get-access-token-using-client-credentials-using-java-code
 */
public class Auth extends HttpServlet implements PropertiesLoader {
    Properties properties;
    String CLIENT_ID;
    String CLIENT_SECRET;
    String OAUTH_URL;
    String LOGIN_URL;
    String REDIRECT_URL;
    String REGION;
    String POOL_ID;
    Keys jwks;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets the auth code from the request and exchanges it for a token containing user info.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authCode = req.getParameter("code");
        Properties props = (Properties) getServletContext().getAttribute("cognitoProperties");

        if (authCode == null || authCode.isEmpty() || props == null) {
            logger.warn("Authentication failed: Missing code or configuration properties.");
            resp.sendRedirect("login");
            return;
        }

        CLIENT_ID = props.getProperty("client.id");
        CLIENT_SECRET = props.getProperty("client.secret");
        OAUTH_URL = props.getProperty("oauthURL");
        REDIRECT_URL = props.getProperty("redirectURL");
        REGION = props.getProperty("region");
        POOL_ID = props.getProperty("poolId");

        try {
            if (jwks == null) {
                loadKey();
            }

            HttpRequest authRequest = buildAuthRequest(authCode, props);
            TokenResponse tokenResponse = getToken(authRequest);
            User user = validate(tokenResponse, props);

            if (user != null) {
                String userName = user.getUserName();

                req.getSession().setAttribute("userName", userName);
                req.getSession().setAttribute("user", user);

                resp.sendRedirect("home");
            } else {
                logger.error("User validation returned null.");
                resp.sendRedirect("error.jsp");
            }

        } catch (IOException | InterruptedException e) {
            logger.error("Error during auth: {}", e.getMessage(), e);
            resp.sendRedirect("error.jsp");
        }
    }

    /**
     * Sends the request for a token to Cognito and maps the response
     * @param authRequest the request to the oauth2/token url in cognito
     * @return response from the oauth2/token endpoint which should include id token, access token and refresh token
     * @throws IOException
     * @throws InterruptedException
     */
    private TokenResponse getToken(HttpRequest authRequest) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<?> response = null;

        response = client.send(authRequest, HttpResponse.BodyHandlers.ofString());

        logger.debug("Response headers: {}", response.headers().toString());
        logger.debug("Response body: {}", response.body().toString());

        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse = mapper.readValue(response.body().toString(), TokenResponse.class);
        logger.debug("Id token: {}", tokenResponse.getIdToken());

        return tokenResponse;

    }

    /**
     * Get values out of the header to verify the token is legit. If it is legit, get the claims from it, such
     * as username.
     * @param tokenResponse
     * @return
     * @throws IOException
     */
    private User validate(TokenResponse tokenResponse, Properties props) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CognitoTokenHeader tokenHeader = mapper.readValue(CognitoJWTParser.getHeader(tokenResponse.getIdToken()).toString(), CognitoTokenHeader.class);

        // Header should have kid and alg- https://docs.aws.amazon.com/cognito/latest/developerguide/amazon-cognito-user-pools-using-the-id-token.html
        String keyId = tokenHeader.getKid();
        String alg = tokenHeader.getAlg();

        KeysItem matchingKey = null;

        for (KeysItem key : jwks.getKeys()) {
            if (key.getKid().equals(keyId)) {
                matchingKey = key;
                break;
            }
        }

        if (matchingKey == null) {
            logger.error("No matching public keys found in JWKS for kid: {}", keyId);
        }
        // Use Key's N and E
        BigInteger modulus = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(jwks.getKeys().get(0).getN()));
        BigInteger exponent = new BigInteger(1, org.apache.commons.codec.binary.Base64.decodeBase64(jwks.getKeys().get(0).getE()));

        // Create a public key
        PublicKey publicKey = null;
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException e) {
            logger.error("Invalid Key Error {}", e.getMessage(), e);
            return null;
        } catch (NoSuchAlgorithmException e) {
            logger.error("Algorithm Error {}", e.getMessage(), e);
            return null;
        }

        // get an algorithm instance
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);

        // Verify ISS field of the token to make sure it's from the Cognito source
        String iss = String.format("https://cognito-idp.%s.amazonaws.com/%s", REGION, POOL_ID);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(iss)
                .withClaim("token_use", "id") // make sure you're verifying id token
                .build();

        // Verify the token
        DecodedJWT jwt = verifier.verify(tokenResponse.getIdToken());
        String email = jwt.getClaim("email").asString();
        String sub = jwt.getClaim("sub").asString();
        String derivedName = email.split("@")[0];

        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> users = userDao.findByPropertyEqual("cognitoSub", sub);
        User user;

        if (users.isEmpty()) {
            // Create the user if they don't exist
            user = new User(derivedName, "User", derivedName, email);
            user.setCognitoSub(sub);
            userDao.insert(user);
        } else {
            user = users.get(0);
        }

        return user;
    }

    /** Create the auth url and use it to build the request.
     *
     * @param authCode auth code received from Cognito as part of the login process
     * @return the constructed oauth request
     */
    private HttpRequest buildAuthRequest(String authCode, Properties props) {
        String clientId = props.getProperty("client.id");
        String clientSecret = props.getProperty("client.secret");
        String oauthUrl = props.getProperty("oauthURL");
        String redirectUri = props.getProperty("redirectURL");

        String keys = clientId + ":" + clientSecret;

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "authorization_code");
        parameters.put("client-secret", clientSecret);
        parameters.put("client_id", clientId);
        parameters.put("code", authCode);
        parameters.put("redirect_uri", redirectUri);

        String form = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

        return HttpRequest.newBuilder().uri(URI.create(oauthUrl))
                .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic " + encoding)
                .POST(HttpRequest.BodyPublishers.ofString(form)).build();
    }

    /**
     * Gets the JSON Web Key Set (JWKS) for the user pool from cognito and loads it
     * into objects for easier use.
     *
     * JSON Web Key Set (JWKS) location: https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json
     * Demo url: https://cognito-idp.us-east-2.amazonaws.com/us-east-2_XaRYHsmKB/.well-known/jwks.json
     *
     * @see Keys
     * @see KeysItem
     */
    private void loadKey() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL jwksURL = new URL(String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", REGION, POOL_ID));
            File jwksFile = new File("jwks.json");
            FileUtils.copyURLToFile(jwksURL, jwksFile);
            jwks = mapper.readValue(jwksFile, Keys.class);
            logger.debug("Keys are loaded. Here's e: {}", jwks.getKeys().get(0).getE());
        } catch (IOException ioException) {
            logger.error("Cannot load json...{}", ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading json{}", e.getMessage(), e);
        }
    }

}
