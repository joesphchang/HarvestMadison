<%@include file="../../taglib.jsp"%>
<%--@elvariable id="recipes" type="java.util.List<com.joeychang.entity.Recipe>"--%>
<%--@elvariable id="apiRecipes" type="java.util.List<com.joeychang.entity.SpoonacularRecipe>"--%>
<c:set var="title" value="Search Results" />

<!DOCTYPE html>
<html>
<c:import url="../common/head.jsp" />
<body>
<c:import url="../common/navigation.jsp" />

<div class="container mt-4">
    <h2 class="mb-4">Search Results for: <span class="text-success">${param.searchTerm}</span></h2>

    <h3 class="h4 mb-4 mt-5"><i class="bi bi-house-door"></i> Madison Community Recipes</h3>
    <c:forEach var="recipe" items="${recipes}">
        <div class="row g-0 mb-4 bg-white border rounded-3 shadow-sm align-items-stretch" style="min-height: 220px;">
            <div class="col-md-3 p-0">
                <img src="${not empty recipe.imageURL ? recipe.imageURL : 'https://i.imgur.com/BpAX3hE.jpeg'}"
                     class="img-fluid rounded-start-3" alt="${recipe.recipeName}"
                     style="height: 100%; width: 100%; object-fit: cover;">
            </div>
            <div class="col-md-6 px-4 py-5 d-flex flex-column justify-content-center">
                <h3 class="fw-bold mb-2">${recipe.recipeName}</h3>
                <p class="text-secondary mb-1">${recipe.description}</p>
                <div class="small text-muted italic">
                    <i class="bi bi-tag"></i> ${recipe.seasonalIngredient.seasonalIngredientName}
                </div>
            </div>
            <div class="col-md-3 p-4 text-end border-start d-flex flex-column justify-content-center align-items-end">
                <a href="recipeDetails?id=${recipe.id}" class="btn btn-dark rounded-pill px-5 py-2 fw-bold">
                    View Recipe
                </a>
            </div>
        </div>
    </c:forEach>

    <c:if test="${empty recipes}">
        <p class="text-muted italic">No local community recipes found.</p>
    </c:if>

    <hr class="my-5">

    <h3 class="h4 mb-4"><i class="bi bi-globe"></i> Global Inspiration</h3>
    <c:forEach var="apiRecipe" items="${apiRecipes}">
        <div class="row g-0 mb-4 bg-light border rounded-3 shadow-sm align-items-stretch" style="min-height: 220px;">
            <div class="col-md-3 p-0">
                <img src="${apiRecipe.image}" class="img-fluid rounded-start-3" alt="${apiRecipe.title}"
                     style="height: 100%; width: 100%; object-fit: cover;">
            </div>

            <div class="col-md-6 px-4 py-5 d-flex flex-column justify-content-center">
                <h3 class="fw-bold mb-2">${apiRecipe.title}</h3>
                <p class="text-secondary mb-1">Explore this recipe from the global Spoonacular database.</p>
                <span class="badge bg-info text-dark align-self-start">Spoonacular API Result</span>
            </div>

            <div class="col-md-3 p-4 text-end border-start d-flex flex-column justify-content-center align-items-end">
                <a href="spoonacularDetails?apiId=${apiRecipe.id}"
                   class="btn btn-outline-dark rounded-pill px-5 py-2 fw-bold">
                    View Details
                </a>
            </div>
        </div>
    </c:forEach>

    <c:if test="${empty apiRecipes and not empty param.searchTerm}">
        <div class="text-center py-5 border border-dashed">
            <p class="text-muted">No global recipes found for "${param.searchTerm}".</p>
        </div>
    </c:if>
</div>

<c:import url="../common/footer.jsp" />
</body>
</html>