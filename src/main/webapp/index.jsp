<%@include file="taglib.jsp"%>
<%--@elvariable id="seasonalIngredient" type="com.joeychang.entity.SeasonalIngredient"--%>
<%--@elvariable id="seasonalRecipes" type="java.util.List<com.joeychang.entity.Recipe>"--%>
<c:set var="title" value="Home" />
<!DOCTYPE html>
<html>
    <c:import url="WEB-INF/jsp/common/head.jsp" />
<body>
    <c:import url="WEB-INF/jsp/common/navigation.jsp" />
    <div class="container mt-4">
        <div class="row border p-4 mb-5 rounded shadow-sm align-items-center">
            <div class="col-md-5">
                <img src="${seasonalIngredient.imageURL}" class="img-fluid rounded" alt="${seasonalIngredient.seasonalIngredientName}">
            </div>
            <div class="col-md-7">
                <h6 class="text-muted text-uppercase">Seasonal Vegetable of the Month</h6>
                <h1 class="display-4 fw-bold">${seasonalIngredient.seasonalIngredientName}</h1>
                <p class="lead">Fresh from local Madison farms this March.</p>
            </div>
        </div>

        <div class="border p-4 rounded shadow-sm">
            <h3 class="mb-4">Recipes with ${seasonalIngredient.seasonalIngredientName}</h3>
            <div class="row">
                <c:forEach var="recipe" items="${seasonalRecipes}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0">
                            <img src="${recipe.imageURL}" class="card-img-top rounded" alt="${recipe.recipeName}" style="height: 200px; object-fit: cover;">
                            <div class="card-body px-0">
                                <h5 class="card-title fw-bold">${recipe.recipeName}</h5>
                                <p class="card-text text-muted small">${recipe.description}</p>
                                <a href="recipeDetails?id=${recipe.id}" class="btn btn-outline-dark btn-sm">Recipe Detail</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${empty seasonalRecipes}">
                    <div class="col-12 text-center py-4">
                        <p class="text-muted italic">Check back soon for new ${seasonalIngredient.seasonalIngredientName} recipes!</p>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <c:import url="WEB-INF/jsp/common/footer.jsp" />
</body>

</html>
