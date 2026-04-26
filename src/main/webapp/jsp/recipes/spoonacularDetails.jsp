<%@include file="../../taglib.jsp"%>
<%--@elvariable id="apiRecipe" type="com.joeychang.entity.SpoonacularRecipe"--%>
<c:set var="title" value="${apiRecipe.title}" />

<!DOCTYPE html>
<html lang="en">
<c:import url="../common/head.jsp" />
<body>
<c:import url="../common/navigation.jsp" />

<main class="container my-5">
  <div class="mb-4">
    <h1 class="fw-bold">${apiRecipe.title}</h1>
    <span class="badge bg-info text-dark">Spoonacular Global Result</span>
  </div>

  <div class="mb-5">
    <img src="${apiRecipe.image}" alt="${apiRecipe.title}"
         class="rounded border shadow-sm"
         style="max-height: 450px; width: 100%; object-fit: cover;">
  </div>

  <div class="mb-5">
    <div class="lead">
      <c:out value="${apiRecipe.summary}" escapeXml="false" />
    </div>
  </div>

  <hr>

  <div class="row g-5 mt-2">
    <div class="col-md-5">
      <h3 class="h4 mb-4">What You'll Need</h3>
      <ul class="list-unstyled">
        <c:forEach var="item" items="${apiRecipe.extendedIngredients}">
          <li class="py-2 border-bottom">
            <strong>${item.amount} ${item.unit}</strong> ${item.name}
          </li>
        </c:forEach>
      </ul>
    </div>

    <div class="col-md-7">
      <h3 class="h4 mb-4">Cooking Info</h3>
      <div class="p-4 bg-light rounded">
        <p>This recipe was sourced via the Spoonacular API.</p>
        <p class="text-muted mt-4"><em>Check the summary above for preparation details!</em></p>
      </div>
    </div>
  </div>
</main>

<c:import url="../common/footer.jsp" />
</body>
</html>