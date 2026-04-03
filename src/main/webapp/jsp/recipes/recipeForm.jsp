<%@include file="../../taglib.jsp"%>
<%--@elvariable id="recipe" type="com.joeychang.entity.Recipe"--%>
<%--@elvariable id="ingredient" type="com.joeychang.entity.Ingredient"--%>
<c:set var="title" value="${recipe.recipeName}" />
<html>
<head>
    <c:import url="../common/head.jsp" />
</head>
<body>
<c:import url="../common/navigation.jsp" />
<div class="container mt-5">
    <h2>
        <c:choose>
            <c:when test="${not empty recipe}">Update Recipe</c:when>
            <c:otherwise>Add a New Recipe</c:otherwise>
        </c:choose>
    </h2>

    <form action="saveRecipe" method="post">
        <input type="hidden" name="recipeId" value="${recipe.id}">

        <div class="mb-3">
            <label class="form-label">Recipe Title</label>
            <input type="text" name="title" class="form-control" value="${recipe.title}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Seasonal Ingredient</label>
            <select name="ingredientId" class="form-select">
                <c:forEach var="ingredient" items="${ingredient}">
                    <option value="${ingredient.id}"
                        ${ingredient.id == recipe.seasonalIngredient.id ? 'selected' : ''}>
                            ${ingredient.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Instructions</label>
            <textarea name="description" class="form-control" rows="5">${recipe.description}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">
            ${not empty recipe ? 'Save Changes' : 'Create Recipe'}
        </button>
    </form>
</div>
<c:import url="../common/footer.jsp" />
</body>
</html>
