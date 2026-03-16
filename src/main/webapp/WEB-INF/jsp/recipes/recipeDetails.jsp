<%@include file="../../../taglib.jsp"%>
<%--@elvariable id="recipe" type="com.joeychang.entity.Recipe"--%>
<c:set var="title" value="Details" />
<!DOCTYPE html>
<html>
    <c:import url="../common/head.jsp" />
    <body>
        <c:import url="../common/navigation.jsp" />
        <div class="recipe-container">
            <h1>${recipe.recipeName}</h1>
            <p><em>${recipe.description}</em></p>

            <img src="${recipe.imageURL}" alt="${recipe.recipeName}" style="max-width: 300px; border-radius: 8px;" />

            <h2>Ingredients</h2>
            <ul>
                <c:forEach var="ri" items="${recipe.recipeIngredients}">
                    <li>
                        <strong>${ri.quantity} ${ri.unit}</strong> - ${ri.ingredient.name}
                    </li>
                </c:forEach>
            </ul>
        </div>
        <c:import url="../common/footer.jsp" />
    </body>
</html>


apiKey=e138a8cd94534ba4b27d6b6b0d39912d