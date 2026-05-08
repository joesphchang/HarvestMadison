<%@include file="../../taglib.jsp"%>
<%--@elvariable id="recipe" type="com.joeychang.entity.Recipe"--%>
<%--@elvariable id="ingredients" type="java.util.List<com.joeychang.entity.SeasonalIngredient>"--%>
<%--@elvariable id="recipeId" type="java.lang.String"--%>
<%--@elvariable id="recipeName" type="java.lang.String"--%>
<%--@elvariable id="imageURL" type="java.lang.String"--%>
<%--@elvariable id="description" type="java.lang.String"--%>
<%--@elvariable id="ingredientRowCount" type="java.lang.Integer"--%>
<%--@elvariable id="quantities" type="java.lang.String[]"--%>
<%--@elvariable id="units" type="java.lang.String[]"--%>
<%--@elvariable id="ingredientNames" type="java.lang.String[]"--%>
<c:set var="title" value="Edit ${recipe.recipeName}" />
<html>
<head>
  <c:import url="../common/head.jsp" />
</head>
<body>
<c:import url="../common/navigation.jsp" />
<div class="container mt-5">
  <h2>Update Recipe</h2>

  <form action="editRecipe" method="post">
    <input type="hidden" name="recipeId" value="${not empty recipe ? recipe.id : recipeId}">

    <div class="mb-3">
      <label class="form-label">Recipe Title</label>
      <input type="text" name="recipeName" class="form-control"
             value="${not empty recipe ? recipe.recipeName : recipeName}" required>
    </div>

    <div class="mb-3">
      <label>Image URL</label>
      <input type="text" name="image-url" class="form-control"
             value="${not empty recipe ? recipe.imageURL : imageURL}" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Seasonal Ingredient</label>
      <select name="ingredientId" class="form-select">
        <c:forEach var="ingredient" items="${ingredients}">
          <option value="${ingredient.id}"
            ${ingredient.id == recipe.seasonalIngredient.id ? 'selected' : ''}>
              ${ingredient.seasonalIngredientName}
          </option>
        </c:forEach>
      </select>
    </div>

    <h3>Ingredients</h3>
    <div id="ingredient-list">

      <%-- Pre-populate from the recipe entity --%>
      <c:if test="${not empty recipe and empty ingredientRowCount}">
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
      </c:if>

      <%-- Re-populate from request attributes --%>
      <c:if test="${not empty ingredientRowCount}">
        <c:set var="rowCount" value="${ingredientRowCount}" />
        <c:forEach begin="0" end="${rowCount - 1}" var="i">
          <div class="row mb-2">
            <div class="col-2">
              <input type="text" name="quantity" class="form-control"
                     placeholder="Qty" value="${quantities[i]}">
            </div>
            <div class="col-3">
              <input type="text" name="unit" class="form-control"
                     placeholder="Unit" value="${units[i]}">
            </div>
            <div class="col-7">
              <input type="text" name="ingredientName" class="form-control"
                     placeholder="Ingredient" value="${ingredientNames[i]}">
            </div>
          </div>
        </c:forEach>
      </c:if>

    </div>

    <input type="hidden" name="ingredientRowCount" value="${rowCount}">
    <button type="submit" name="addIngredient" value="addRow" class="btn btn-outline-secondary mb-3">
      + Add Ingredient Row
    </button>

    <div class="mb-3">
      <label class="form-label">Instructions</label>
      <textarea name="description" class="form-control" rows="5">${not empty recipe ? recipe.description : description}</textarea>
    </div>

    <button type="submit" class="btn btn-primary">Save Changes</button>
  </form>
</div>
<c:import url="../common/footer.jsp" />
</body>
</html>
