<%@include file="../../taglib.jsp"%>
<%--@elvariable id="recipe" type="com.joeychang.entity.Recipe"--%>
<%--@elvariable id="ingredients" type="java.util.List<com.joeychang.entity.SeasonalIngredient>"--%>
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
            <input type="text" name="recipeName" class="form-control" value="${recipe.recipeName}" required>
        </div>

        <div class="mb-3">
            <label>Image URL</label>
            <input type="text" name="image-url" class="form-control" value="${recipe.imageURL}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Seasonal Ingredient</label>
            <select name="ingredientId" class="form-select">
                <c:forEach var="ingredient" items="${ingredients}">
                    <option value="${ingredient.id}" ${ingredient.id == recipe.seasonalIngredient.id ? 'selected' : ''}>
                        ${ingredient.seasonalIngredientName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <h3>Ingredients</h3>
        <div id="ingredient-list">
            <c:forEach var="item" items="${recipe.recipeIngredients}">
                <div class="row mb-2">
                    <div class="col-2">
                        <input type="text" name="quantity" class="form-control" value="${item.quantity}">
                    </div>
                    <div class="col-3">
                        <input type="text" name="unit" class="form-control" value="${item.unit}">
                    </div>
                    <div class="col-7">
                        <input type="text" name="ingredientName" class="form-control" value="${item.ingredient.name}">
                    </div>
                </div>
            </c:forEach>

            <c:if test="${empty recipe}">
                <c:forEach var="i" begin="1" end="10">
                    <div class="row mb-2">
                        <div class="col-2"><input type="text" name="quantity" class="form-control" placeholder="Qty"></div>
                        <div class="col-3"><input type="text" name="unit" class="form-control" placeholder="Unit"></div>
                        <div class="col-7"><input type="text" name="ingredientName" class="form-control" placeholder="Ingredient"></div>
                    </div>
                </c:forEach>
            </c:if>
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
