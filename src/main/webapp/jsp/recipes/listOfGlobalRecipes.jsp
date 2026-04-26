<%@include file="../../taglib.jsp"%>
<%--@elvariable id="apiRecipes" type="java.util.List<com.joeychang.entity.SpoonacularRecipe>"--%>
<c:set var="title" value="Global Inspiration" />

<!DOCTYPE html>
<html>
<c:import url="../common/head.jsp" />
<body>
<c:import url="../common/navigation.jsp" />

<div class="container mt-4">
    <h2 class="mb-4"><i class="bi bi-globe"></i> ${title}</h2>
    <p class="text-muted">Explore trending recipes from around the world.</p>

    <c:forEach var="apiRecipe" items="${apiRecipes}">
        <div class="row g-0 mb-4 bg-light border rounded-3 shadow-sm align-items-stretch" style="min-height: 220px;">

            <div class="col-md-3 p-0">
                <img src="${apiRecipe.image}" class="img-fluid rounded-start-3"
                     alt="${apiRecipe.title}" style="height: 100%; width: 100%; object-fit: cover;">
            </div>

            <div class="col-md-6 px-4 py-5 d-flex flex-column justify-content-center">
                <h3 class="fw-bold mb-2">${apiRecipe.title}</h3>
                <div class="mb-2">
                    <span class="badge bg-info text-dark">Spoonacular Result</span>
                    <c:if test="${apiRecipe.vegetarian}">
                        <span class="badge bg-success">Vegetarian</span>
                    </c:if>
                </div>
                <p class="text-secondary mb-0">Ready in ${apiRecipe.readyInMinutes} minutes</p>
            </div>

            <div class="col-md-3 p-4 text-end border-start d-flex flex-column justify-content-center align-items-end">
                <a href="spoonacularDetails?apiId=${apiRecipe.id}"
                   class="btn btn-outline-dark rounded-pill px-5 py-2 fw-bold">
                    View Details
                </a>
            </div>
        </div>
    </c:forEach>

    <c:if test="${empty apiRecipes}">
        <div class="text-center py-5">
            <div class="spinner-border text-success" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
            <p class="mt-3 text-muted">Hanging on while we harvest some global recipes...</p>
        </div>
    </c:if>
</div>

<c:import url="../common/footer.jsp" />
</body>
</html>
