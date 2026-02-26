<%@include file="taglib.jsp"%>
<!DOCTYPE html>
<html>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <a class="navbar-brand" href="<c:url value="/index.jsp" />">Harvest Madison</a>
        <div class="me-auto"></div>

        <form class="d-flex mx-auto w-50" role="search" action="<c:url value='/searchRecipes' />" method="GET">
            <input class="form-control me-2" type="search" name="searchQuery" placeholder="Search Recipes" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>

        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/index.jsp' />">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/listOfRecipes.jsp' />">Recipes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/about.jsp' />">About</a>
            </li>

            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li class="nav-item">
                        <span class="nav-link">User: ${sessionScope.user.userName}</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item"><a class="nav-link" href="<c:url value='/signIn.jsp' />">Sign In</a></li>
                    <li class="nav-item"><a class="nav-link" href="<c:url value='/signUp.jsp' />">Sign Up</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</body>
</html>