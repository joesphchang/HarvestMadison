<%@include file="../../taglib.jsp"%>
<%--@elvariable id="recipe" type="com.joeychang.entity.Recipe"--%>
<c:set var="title" value="${recipe.recipeName}" />

<!DOCTYPE html>
<html lang="en">
<c:import url="../common/head.jsp" />
<body>
<c:import url="../common/navigation.jsp" />

<main class="container my-5">
    <div class="mb-4">
        <h1 class="fw-bold">${recipe.recipeName}</h1>
        <p class="text-success mb-0">Best Season: ${recipe.seasonalIngredient.seasonalIngredientName}</p>
    </div>

    <div class="mb-5">
        <img src="${recipe.imageURL}" alt="${recipe.recipeName}"
             class="rounded border shadow-sm"
             style="max-height: 450px; width: 100%; object-fit: cover;">
    </div>

    <div class="mb-5">
        <p class="lead">${recipe.description}</p>
        <button class="btn btn-sm btn-outline-secondary">
            <i class="far fa-star"></i> Save to Favorites
        </button>
        <c:choose>
            <c:when test="${not empty sessionScope.user and sessionScope.user.id == recipe.user.id}">
                <a href="<c:url value='/editRecipe?id=${recipe.id}' />" class="btn btn-sm btn-outline-info">Edit Recipe</a>
            </c:when>
        </c:choose>
        <c:if test="${not empty recipe}">
            <form action="deleteRecipe" method="post" style="display:inline;">
                <input type="hidden" name="recipeId" value="${recipe.id}">
                <button type="submit" class="btn btn-sm btn-outline-danger">Delete This Recipe</button>
            </form>
        </c:if>
    </div>

    <hr>

    <div class="row g-5 mt-2">
        <div class="col-md-5">
            <h3 class="h4 mb-4">What You'll Need</h3>
            <ul class="list-unstyled">
                <c:forEach var="ri" items="${recipe.recipeIngredients}">
                    <li class="py-2 border-bottom">
                        <strong>${ri.quantity} ${ri.unit}</strong> ${ri.ingredient.name}
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="col-md-7">
            <h3 class="h4 mb-4">How to Prepare</h3>
            <div class="p-4 bg-light rounded">
                <p>${recipe.ingredientsText}</p>
                <p class="text-muted mt-4"><em>Step-by-step instructions coming soon!</em></p>
            </div>
        </div>
    </div>
</main>

<c:import url="../common/footer.jsp" />
</body>
</html>