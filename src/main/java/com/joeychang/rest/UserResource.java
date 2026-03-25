package com.joeychang.rest;

import com.joeychang.entity.User;
import com.joeychang.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type User resource.
 */
@Path("/users")
public class UserResource {

    /**
     * The User dao.
     */
    GenericDao<User> userDao = new GenericDao<>(User.class);

    /**
     * Gets users.
     *
     * @return the users
     */
    @GET
    @Produces("text/plain")
    public Response getUsers() {
        List<User> users = userDao.getAll();

        StringBuilder output = new StringBuilder("Harvest Madison User List:\n");
        for (User user : users) {
            output.append("ID: ").append(user.getId()).append(" | Name: ").append(user.getFirstName()).append("\n");
        }
        return Response.status(200).entity(output.toString()).build();
    }

    /**
     * Gets users by id.
     *
     * @param id the id
     * @return the users by id
     */
    @GET
    @Path("/search")
    @Produces("text/plain")
    public Response getUsersById(@QueryParam("id") String id) {
        try {
            int userId = Integer.parseInt(id);
            User user = userDao.getById(userId);
            if (user != null) {
                return Response.status(200).entity(user.toString()).build();
            } else {
                return Response.status(404).entity("User not found.").build();
            }
        } catch (Exception exception) {
            return Response.status(400).entity("Invalid ID").build();
        }
    }
}