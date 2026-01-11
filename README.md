# Harvest Madison: Individual Project

This repository serves as my individual project for Madison College's Enterprise Java Class.

### Problem Statement

Home cooks in Madison, Wisconsin, frequently struggle to identify and utilize seasonal produce
available at local farmers markets. While generic recipe websites 
offer a vast array of options, they rarely account for regional agricultural seasonality. This leads to a 
"discovery gap" where residents want to support local farmers but don't know what to do with 
specific seasonal items, like kohlrabi in July or parsnips in October, or how to find recipes that 
maximize the use of what is currently in stock at the market(s).

Harvest Madison is a data-driven web application that bridges this gap. By utilizing a custom
Wisconsin Seasonality Engine, the app identifies what is currently being harvested in the Madison area.
It then consumes the `Spoonacular API` to suggest recipes that prioritizes these seasonal 
ingredients. Users can discover "Star Ingredients" of the month, view trending recipes among other
Madison residents, and save their favorite seasonal finds to a personal profile. 

### Project Technologies/Techniques

* Security/Authentication
  * AWS Cognito (Identity Provider for user registration and login)
* Database
  * MySQL 8.x
* ORM Framework
  * Hibernate 6.4.4.Final
* Dependency Management
  * Maven
* Web Services consumed using Java
  * Spoonacular API (Recipe discovery and ingredient analysis)
  * OpenWeatherMap API (To adjust harvest timelines based on local
    Madison frost/weather data)
* UI / Design
  * Bootstrap 5 
  * JSP & JSTL (Dynamic content rendering)
* Data Validation
  * Bootstrap Validator for front-end
  * Hibernate Validator for back-end data integrity
* Logging
  * Log4J2
* Hosting
  * AWS
* Project Lombok
* Unit Testing
  * JUnit 5 to cover seasonality logic and data access objects
* IDE 
  * IntelliJ IDEA

### Design

*[Screen Design](ScreenDesign.md)
*[User Stories](UserStories.md)

### [Project Plan](ProjectPlan.md)

### [Weekly Journal](WeeklyJournal.md)
