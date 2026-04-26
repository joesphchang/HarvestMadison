<%@include file="taglib.jsp"%>
<c:set var="title" value="About Harvest Madison" />

<!DOCTYPE html>
<html lang="en">
<c:import url="jsp/common/head.jsp" />
<body>
<c:import url="jsp/common/navigation.jsp" />

<div class="container my-5">
    <div class="row border p-5 rounded shadow-sm align-items-center bg-white mb-5">
        <div class="col-md-5 text-center">
            <img src="images/harvest_madison.png"
                 class="img-fluid rounded shadow-sm"
                 alt="Harvest Madison Logo"
                 style="max-width: 350px;">
        </div>
        <div class="col-md-7 ps-md-5">
            <h6 class="text-muted text-uppercase mb-2">The Mission</h6>
            <h1 class="fw-bold mb-3">Harvest Madison</h1>
            <p class="lead text-dark">Bridging the gap between Madison's local harvests and global culinary inspiration.</p>
            <p class="text-secondary">
                Harvest Madison is a digital platform designed to promote local food systems. By highlighting
                seasonal ingredients available in Dane County and providing curated recipes, we help residents
                eat more sustainably while supporting local producers.
            </p>
        </div>
    </div>

    <div class="row g-4">
        <div class="col-md-6">
            <div class="h-100 p-5 border rounded shadow-sm bg-light">
                <h3 class="fw-bold"><i class="bi bi-cpu text-success"></i> Driven by Technology</h3>
                <p class="text-secondary">
                    Built on a modern Java stack, Harvest Madison utilizes a custom Hibernate ORM
                    framework and the Spoonacular REST API. This technical foundation allows for
                    real-time recipe discovery and a seamless user experience across local and global data sources.
                </p>
            </div>
        </div>

        <div class="col-md-6">
            <div class="h-100 p-5 border rounded shadow-sm bg-light">
                <h3 class="fw-bold"><i class="bi bi-people text-success"></i> Community Focused</h3>
                <p class="text-secondary">
                    Our platform is designed specifically for the Madison community. From the Dane County Farmers' Market
                    to local bakery partnerships, we prioritize seasonal awareness to ensure that every recipe suggested
                    can be made with the freshest ingredients currently growing in our region.
                </p>
            </div>
        </div>
    </div>
</div>

<c:import url="jsp/common/footer.jsp" />
</body>
</html>