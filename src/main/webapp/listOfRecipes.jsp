<%@include file="taglib.jsp"%>
<%--@elvariable id="recipes" type="java.util.List<com.joeychang.entity.Recipe>"--%>
<c:set var="title" value="List of Recipes" />
<!DOCTYPE html>
<html>
<%@include file="head.jsp"%>
<body>
<%@include file="navigation.jsp"%>
    <table class="recipe-grid-table table table-striped">
        <thead>
        <tr>
            <th>Photo</th>
            <th>Name</th>
            <th>Date Added</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="recipe" items="${recipes}">
            <tr>
                <td><img src="${recipe.imageURL}" style="width:80px;"></td>
                <td><strong>${recipe.recipeName}</strong></td>
                <td><fmt:formatDate value="${recipe.createdOn}" pattern="MMM d, yyyy" /></td>
                <td>
                    <a href="viewRecipe?id=${recipe.id}" class="btn btn-sm btn-info">View</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<%@include file="footer.jsp"%>
</body>

</html>
