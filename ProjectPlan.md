# Project Plan for Harvest Madison

### Week 2
    - [x] Create project repository on GitHub
    - [x] Initialize Maven/Gradle project structure in IntelliJ
    - [x] Push initial "Hello World" structure to the remote repository
    - [x] Finalize Problem Statement and submit
    - [x] Weekly reflection/time log

### Week 3
    - [x] Finalize technology versions (Java 17+, Tomcat 10+, Hibernate 6+)
    - [x] Document User Stories (MVP vs. Non-MVP)
    - [x] Map out the "Seasonal Integrity" logic flow
    - [x] Checkpoint 1: Project plan, user stories, and wireframes complete
    - [x] Update weekly reflection/time log

### Week 4 - The Data Layer
    - [x] Database Design: Create schema for user, recipe, seasonal_ingredient, and seasonal_ingredient_recipes
    - [x] Create ingredient, recipe, user, and seasonal_ingredient tables in local MySQL
    - [x] Hibernate Setup: Create hibernate.cfg.xml and database.properties
    - [x] Implement Log4j2 for all error handling and status logging
    - [X] Note: Ensure your POJO entity mapping and UserDao unit tests are finalized as you transition into Week 5.

### Week 5 - Create GenericDao & Junit Test for full Users and Recipes
    - [X] Set up a GenericDao to reduce code duplication for future entities.
    - [X] Set up Java Beans and intergate it with GenericDao. 
    - [X] Set up a way for Seasonal Ingredients to show up for current month.
    - [X] Create Sample Database to test with.
    - [X] Create JUnit tests to set Users, Recipes and Seasonal Ingredients. 
    - [X] Update weekly reflection/time log.

### Week 6 - Controllers & Dynamic Display
    Focus: Discovery User Stories (Search by Name and Hero Ingredient)
    - [X] Create HomeServlet to handle the "Monthly Hero" logic for the index page.
    - [X] Create SearchRecipesServlet to manage keyword searches via API.
    - [X] Create index.jsp with a featured section for the current seasonal vegetable.
    - [X] Create results.jsp to display randomized API recipe data (Title, Image, and Seasonality Badge).
    - [X] Create AboutServlet and about.jsp to explain Madison-specific harvest data.
    - [X] Update weekly reflection/time log.

### Week 7 - Checkpoint 2 Due
    Goal: Full CRUD, Unit Tested DAOs, Log4j implemented, and initial AWS Deployment.
    - [ ] Double-check all Checkpoint 2 items are visible in GitHub (Database designed, one DAO with full CRUD, unit tests complete).
    - [ ] Set up AWS RDS (MySQL) and update production properties.
    - [ ] Deploy the .war file to AWS Elastic Beanstalk.
    - [ ] Verify database connection in the cloud environment.
    - [ ] Update weekly reflection/time log.

### Week 8 - 12: Authentication & Checkpoint 3
    Focus: Security and User-Specific CRUD
    - [ ] Implement Sign-In and Sign-Up logic utilizing UserDao
    - [ ] Implement Sign-Out story to securely terminate user sessions
    - [ ] Checkpoint 3 Due (Week 9): Deployed app with Authentication and at least one JSP displaying DB data
    - [ ] Create the "Personal Cookbook" functionality (CRUD operations for UserRecipe table)
    - [ ] Implement "Favorite Recipes" functionality (Many-to-Many relationship between Users and Recipes)

### Week 15 - Final Polish
    - [ ] Final Presentation prep and recording video walk-through
    - [ ] Code quality check and final documentation updates
    - [ ] Weekly journal entry