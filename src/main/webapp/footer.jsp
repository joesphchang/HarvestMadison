<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<body>

<footer class="footer mt-auto py-3 bg-light border-top">
    <div class="container text-center">
        <div class="row">
            <div class="col-md-4 text-md-start">
                <span class="text-muted">&copy; <%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) %> Harvest Madison</span>
            </div>
            <div class="col-md-4">
                <ul class="list-inline mb-0">
                    <li class="list-inline-item"><a href="<c:url value='/index.jsp'/>" class="text-decoration-none text-muted">Home</a></li>
                    <li class="list-inline-item"><a href="<c:url value='/about.jsp'/>" class="text-decoration-none text-muted">About</a></li>
                </ul>
            </div>
            <div class="col-md-4 text-md-end">
                <small class="text-muted">Built in Madison, WI</small>
            </div>
        </div>
    </div>
</footer>
</body>
</html>
