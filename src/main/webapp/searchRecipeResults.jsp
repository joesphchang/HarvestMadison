<%@include file="taglib.jsp"%>
<%--@elvariable id="recipes" type="java.util.List<com.joeychang.entity.Recipe>"--%>
<c:set var="title" value="Search Results" />

<script type="text/javascript" class="init">
    $(document).ready( function() {
        $('#recipeTable').DataTable();
    })
</script>
<!DOCTYPE html>
<html>
<%@include file="head.jsp"%>
<body>
<%@include file="navigation.jsp"%>
    <h2>Search Results: </h2>
        <table class="recipe-grid-table">
            <thead>
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Description</th>
                <th>Created On</th>
                <th>Ingredients</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="recipe" items="${recipes}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${not empty recipe.imageURL}">
                                <img src="${recipe.imageURL}" alt="${recipe.recipeName}" style="max-width: 100px; height: auto;">
                            </c:when>
                            <c:otherwise>
                                <img src="https://i.imgur.com/BpAX3hE.jpeg" alt="No image available" style="max-width: 100px;">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${recipe.recipeName}</td>
                    <td>${recipe.description}</td>
                    <td>${recipe.createdOn}</td>
                    <td>${recipe.ingredientsText}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
<%@include file="footer.jsp"%>
</body>
</html>

