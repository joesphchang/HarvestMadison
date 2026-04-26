<%@include file="taglib.jsp"%>
<%--@elvariable id="seasonalIngredient" type="com.joeychang.entity.SeasonalIngredient"--%>
<%--@elvariable id="seasonalRecipes" type="java.util.List<com.joeychang.entity.Recipe>"--%>
<c:set var="title" value="Home" />
<!DOCTYPE html>
<html>
    <c:import url="jsp/common/head.jsp" />
<body>
    <c:import url="jsp/common/navigation.jsp" />
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

        <div class="row border p-5 mt-5 rounded shadow-sm align-items-center bg-white mb-5">
            <div class="col-md-5 text-center">
                <img src="images/home-hero.jpg"
                     class="img-fluid rounded border shadow-sm"
                     alt="Fresh Seedlings"
                     style="max-height: 250px; width: 100%; object-fit: cover;">
            </div>

            <div class="col-md-7 ps-md-5">
                <h6 class="text-muted text-uppercase mb-2">Our Mission</h6>
                <h2 class="fw-bold mb-3">Harvest Madison</h2>
                <p class="text-secondary" style="line-height: 1.7;">
                    Harvest Madison is a community-driven platform dedicated to connecting the people of Madison with the
                    flavors of the season. By integrating local farm-fresh ingredients with a robust global recipe
                    database, this application empowers home cooks to support local agriculture while exploring
                    culinary traditions from around the world.
                </p>
                <p class="text-secondary small italic">
                    Built for the community, by the community—celebrating the intersection of local food and modern technology.
                </p>
                <a href="about" class="btn btn-outline-dark btn-sm px-4 mt-2">Read the Full Story</a>
            </div>
        </div>
    </div>
    <c:import url="jsp/common/footer.jsp" />
</body>

</html>
