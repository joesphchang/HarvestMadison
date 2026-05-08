<%@include file="taglib.jsp"%>
<%--@elvariable id="seasonalIngredient" type="com.joeychang.entity.SeasonalIngredient"--%>
<%--@elvariable id="seasonalRecipes" type="java.util.List<com.joeychang.entity.Recipe>"--%>
<%--@elvariable id="globalRecipes" type="java.util.List<com.joeychang.entity.SpoonacularRecipe>"--%>
<c:set var="title" value="Home" />
<!DOCTYPE html>
<html>
    <c:import url="jsp/common/head.jsp" />
<body>
    <c:import url="jsp/common/navigation.jsp" />
    <div class="container mt-4">
        <div class="border p-4 rounded shadow-sm mb-5">
            <h3 class="mb-4">Local Community ${seasonalIngredient.seasonalIngredientName} Recipes</h3>
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
                        <p class="text-muted italic">No community recipes yet. Be the first to add a local ${seasonalIngredient.seasonalIngredientName} dish!</p>
                        <a href="addRecipe" class="btn btn-outline-dark mt-2">Add a Recipe</a>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="border p-4 rounded shadow-sm bg-light mb-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h3 class="mb-0">Global ${seasonalIngredient.seasonalIngredientName} Recipes</h3>
                <span class="badge bg-secondary">Powered by Spoonacular</span>
            </div>
            <div class="row">
                <c:forEach var="apiRecipe" items="${globalRecipes}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 border-0 bg-transparent">
                            <img src="${apiRecipe.image}" class="card-img-top rounded" alt="${apiRecipe.title}" style="height: 200px; object-fit: cover;">
                            <div class="card-body px-0">
                                <h5 class="card-title fw-bold">${apiRecipe.title}</h5>
                                <a href="spoonacularDetails?apiId=${apiRecipe.id}" class="btn btn-outline-secondary btn-sm mt-2">View Global Recipe</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${empty globalRecipes}">
                    <div class="col-12 text-center py-4">
                        <p class="text-muted italic">Global recipes are currently simmering. Check back soon!</p>
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
                    Built for the community, by the community-celebrating the intersection of local food and modern technology.
                </p>
                <a href="about" class="btn btn-outline-dark btn-sm px-4 mt-2">Read the Full Story</a>
            </div>
        </div>
    </div>
    <c:import url="jsp/common/footer.jsp" />
</body>

</html>
