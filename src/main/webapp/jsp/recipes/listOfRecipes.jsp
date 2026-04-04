<%@include file="../../taglib.jsp"%>
<%--@elvariable id="recipes" type="java.util.List<com.joeychang.entity.Recipe>"--%>
<c:set var="title" value="All Harvest Recipes" />
<!DOCTYPE html>
<html>
<c:import url="../common/head.jsp" />
<body>
<c:import url="../common/navigation.jsp" />
<div class="container mt-4">

    <div>
        <h2 class="mb-4">${title}</h2>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <a href="<c:url value='/addRecipe' />" class="btn btn-primary">Add Recipe</a>
            </c:when>
        </c:choose>
    </div>

    <c:forEach var="recipe" items="${recipes}">
        <div class="row g-0 mb-4 bg-white border rounded-3 shadow-sm align-items-stretch" style="min-height: 220px;">

            <div class="col-md-3 p-0">
                <img src="${not empty recipe.imageURL ? recipe.imageURL : 'https://i.imgur.com/BpAX3hE.jpeg'}"
                     class="img-fluid rounded-start-3"
                     alt="${recipe.recipeName}"
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
                <div class="small text-muted mb-3">Prep: X min | Cook: Y min</div>
                <a href="recipeDetails?id=${recipe.id}"
                   class="btn btn-dark rounded-pill px-5 py-2 fw-bold">
                    View Recipe
                </a>
            </div>
        </div>
    </c:forEach>

    <c:if test="${empty recipes}">
        <div class="text-center py-5 border border-dashed">
            <p class="text-muted">No recipes found for this harvest.</p>
        </div>
    </c:if>
</div>
<c:import url="../common/footer.jsp" />
</body>
</html>