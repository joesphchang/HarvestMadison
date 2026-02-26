<%@include file="taglib.jsp"%>
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
        <table id="recipeTable" class="display" cellspacing="0" width="100%">
            <c:forEach var="recipe" items="${recipes}">
            <thead>
            <th>${recipe.imageURL}</th>
            <th>Recipe Name</th>
            <th>Description</th>
            <th>Created On</th>
            <th>Ingredients</th>
            </thead>
            <tbody>
                <tr>
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

