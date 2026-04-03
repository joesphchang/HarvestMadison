<%@include file="../../taglib.jsp"%>
<!DOCTYPE html>
<html>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <a class="navbar-brand" href="<c:url value="/home" />">Harvest Madison</a>
        <div class="me-auto"></div>

        <form class="d-flex mx-auto w-50" role="search" action="${pageContext.request.contextPath}/search" method="GET">
            <input class="form-control me-2" type="text" name="searchTerm" placeholder="Search Recipes">
            <button class="btn btn-outline-success" type="submit" name="submit" value="search">Search</button>
        </form>

        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/home' />">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/recipes' />">Recipes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/about' />">About</a>
            </li>

            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <span class="nav-link text-success">Welcome, ${sessionScope.user.userName}</span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sign Out</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Sign In</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</body>
</html>