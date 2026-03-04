<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<c:import url="../../../taglib.jsp" /><c:import url="../../../taglib.jsp" />
<c:set var="title" value="Error" />

<c:import url="/WEB-INF/jsp/fragments/header.jsp" />

<!DOCTYPE html>
<html>
    <head>
        <title>System Error</title>
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/fragments/header.jsp" />

        <h1>We hit an error.</h1>

        <p>
            <c:choose>
                <c:when test="${not empty pageContext.exception.message}">
                    Error: <c:out value="${pageContext.exception.message}" />
                </c:when>
                <c:otherwise>
                    An unexpected error occurred. Please try again later.
                </c:otherwise>
            </c:choose>
        </p>

        <c:import url="/WEB-INF/jsp/fragments/footer.jsp" />
    </body>
</html>