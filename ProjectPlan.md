# Project Plan for Harvest Madison 

Week 1: Project Scoping
[x] Define the "Discovery Gap" problem for Madison home cooks.
[x] Determine the core solution: A Wisconsin Seasonality Engine + Spoonacular API.
[x] Initial research on the Spoonacular API documentation.

Week 2: Infrastructure & Setup
[x] Create project repository on GitHub.
[x] Initialize Maven/Gradle project structure in IntelliJ.
[x] Push initial "Hello World" structure to the remote repository.
[x] Finalize Problem Statement and submit.
[x] Weekly reflection/time log.

Week 3: Requirements & Tech Stack
[x] Finalize technology versions (Java 17+, Tomcat 10+, Hibernate 6+).
[x] Document User Stories (MVP vs. Non-MVP).
[x] Map out the "Seasonal Integrity" logic flow.
[x] Checkpoint 1: Project plan, user stories, and wireframes complete.

Week 4: The Data Layer (Current Week)
Goal: Establish the "Seasonality Engine" database and Hibernate connection.

[X] Database Design: Create a schema that links local Wisconsin harvest dates to generic ingredients.
[X] Create Ingredient and User tables in the local MySQL database.
[x] Hibernate Setup: Create the hibernate.cfg.xml and database.properties.
[ ] Entity Mapping: Create POJOs with Hibernate annotations for Ingredient and User.
[ ] DAO Implementation: Create IngredientDao with a method to getIngredientsByMonth(int month).
[ ] Unit Testing: Implement JUnit tests for the DAO (ensure 0% logic in the DAO, 100% in the tests).
[x] Implement Log4j2 for all error handling and status logging.

Week 5: API Integration & Service Layer
[ ] Set up a GenericDao to reduce code duplication for future entities.
[ ] Create a SpoonacularService class to handle HTTP requests and JSON parsing (using Jackson).
[ ] Integrity Logic: Write the logic that takes a "Seasonal Ingredient" from your DB and queries the API for matching recipes.
[ ] Weekly reflection/time log.

Week 6: Controllers & Dynamic Display
[ ] Create a SearchRecipes servlet/controller.
[ ] Build the results.jsp to display API data (Title, Image, and "Seasonality Badge").
[ ] Implement a "Star Ingredient" display on the homepage that changes based on the current system date.

Week 7: Deployment & Checkpoint 2
[ ] Checkpoint 2 Due: Full CRUD, Unit Tested DAOs, Log4j implemented.
[ ] Set up AWS RDS (MySQL) and update production properties.
[ ] Deploy the .war file to AWS Elastic Beanstalk.
[ ] Verify that the database connection works in the cloud environment.

Week 8 - 12: Authentication & Advanced Features
Focus: Security and UX.

[ ] Implement Cognito or Tomcat Authentication for User Sign-in.
[ ] Checkpoint 3 Due (Week 9): Deployed app with Authentication and DB-driven JSP.
[ ] Create the "Favorite Recipes" functionality (Many-to-Many relationship between Users and Recipes).
[ ] Implement the "Seasonal Substitution" logic (suggesting a seasonal swap).