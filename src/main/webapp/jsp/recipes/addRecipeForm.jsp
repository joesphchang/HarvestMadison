<%@include file="../../taglib.jsp"%>
<%--@elvariable id="ingredients" type="java.util.List<com.joeychang.entity.SeasonalIngredient>"--%>
<%--@elvariable id="recipeName" type="java.lang.String"--%>
<%--@elvariable id="imageURL" type="java.lang.String"--%>
<%--@elvariable id="description" type="java.lang.String"--%>
<%--@elvariable id="ingredientRowCount" type="java.lang.Integer"--%>
<%--@elvariable id="quantities" type="java.lang.String[]"--%>
<%--@elvariable id="units" type="java.lang.String[]"--%>
<%--@elvariable id="ingredientNames" type="java.lang.String[]"--%>
<c:set var="title" value="Add a New Recipe" />
<html>
<head>
  <c:import url="../common/head.jsp" />
</head>
<body>
<c:import url="../common/navigation.jsp" />
<div class="container mt-5">
  <h2>Add a New Recipe</h2>

  <form action="addRecipe" method="post">
    <div class="mb-3">
      <label class="form-label">Recipe Title</label>
      <input type="text" name="recipeName" class="form-control"
             value="${recipeName}" required>
    </div>

    <div class="mb-3">
      <label>Image URL</label>
      <input type="text" name="image-url" class="form-control"
             value="${imageURL}" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Seasonal Ingredient</label>
      <select name="ingredientId" class="form-select">
        <c:forEach var="ingredient" items="${ingredients}">
          <option value="${ingredient.id}">
              ${ingredient.seasonalIngredientName}
          </option>
        </c:forEach>
      </select>
    </div>

    <h3>Ingredients</h3>
    <div id="ingredient-list">
      <c:set var="rowCount" value="${not empty ingredientRowCount ? ingredientRowCount : 3}" />
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
    </div>

    <input type="hidden" name="ingredientRowCount" value="${rowCount}">
    <button type="submit" name="addIngredient" value="addRow" class="btn btn-outline-secondary mb-3">
      + Add Ingredient Row
    </button>

    <div class="mb-3">
      <label class="form-label">Instructions</label>
      <textarea name="description" class="form-control" rows="5">${description}</textarea>
    </div>

    <button type="submit" class="btn btn-primary">Create Recipe</button>
  </form>
</div>
<c:import url="../common/footer.jsp" />
</body>
</html>
