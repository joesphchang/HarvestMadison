# Weekly Jounral 

Document project progress, the development process, accomplishments, snags,
and time spent in Enterprise Java.

### February 12th, 2026 "The Catch Up"

Today I plan on playing catch-up for this project plan. It's a lot more than I thought but it's doable since I've done
project plans before ranging from Screen Designs, to User Stories. Today I plan on tackling User Stories, Screen Design, 
and the Project Plan. My plan is to do the Project Plan and the User Stories first, before I head into Screen Design.
Therefor, it'll be easier for me to create those Screen Design based off of my plans. These weeks have been eventful,
but I have to make sure I stay up ontop of this project as I could be doing a lot of it last minute. I think I'm going to try
and set up my database and users as well, creating first name, last name, email address, username and password. possibly add
in a birthdate. 

### February 16th, 2026 "Setting Up What Matters"

This week I plan on focusing on setting up the application from database reformat, inserting data into the database, 
to showing the data onto a webpage. Going to do a couple unit testing on get-put-update-delete a user within a database.
Also, I will do the same with an ingredient and recipe. Creating a new recipe owned by a user, updating it, deleting and
getting it to show up on from a search-case. Will do more research on Spoonacular and how to incorporate the API within
my application. This will allow me to set-up and move at ease. Once the logic is done, it's really just setting up the 
JSP pages and getting them to interact with one another. I should probably figure out how to use bootstrap within Java
so I can spend less time on styling and more time on logic-building. 

The plan for seasonal ingredients is to insert data into a Seasonal Ingredient table only within Wisconsin. Utilizing
Dates MM/DD and comparing it to real time to display the actual ingredient. Matching the ingredient within Spoonacular
to get recipes, ingredient photo, ingredient name, etc. 

### February 21st, 2026 "Test Complete" 

I had the week planned out to finish out week 5 and get everything squared away. It was a long week trying to get my project
all set up. I feel like this is the long haul, and once this is all done everything from here on out will be a smooth sail.
I look forward to the upcoming weeks as I deploy this project onto AWS. I plan on doing some boilerplate HTML just to see
what I can get under the hood to display. 

Plan on integrating bootstrap and figuring out the styling throughout. Once I get the pages to all-align and my head/navigation
all squared away onto all my pages I feel like it's all styling from here on out until we get into RESTful Web Services.
Then, I got to figure out how to match seasonal ingredients to the ingredients within the Spoonacular API database, 
pull all the information out, specifically for the monthly ingredients. For now, we digress and move forward as we did accomplish
what was needed to be done for this week and more. 

I should probably time clock how long I've worked on this project so far. I haven't been doing it, but it has been over
5 hours so far. 

### February 23rd, 2026 "AWS and UI Challenges"

This week we're put onto a challenge to deploy our projects onto AWS. The plan for this week is to get a little head-start 
on boilerplate UI just to make sure I'm able to see information on my web-page before I deploy to AWS. I plan on integrating 
Bootstrap UI to make it less complicated on me to design what I want. I also plan on creating my SearchController to be
fully functional, so I'm able to search through recipes and hopefully display the details. It won't be pretty now, but 
it'll be great to get a head-start on UI so I can just do finishing touches when it comes to an end. 

### March 1st, 2026 "AWS Set Up and Peer Review"

The week has passed and now I have gone through the AWS Set up and have done a peer review. Seeing your data being 
shown onto a web-page is unreal especially in java. This class is definitely moving at 110mph, so I'm trying my best to
hold on. Not a bad week and also the peer review has gain me some insight on what I should work on and look forward to. 
I had the opportunity to meet with Emile and we have met for an hour. We're in the same category when it comes to project
ideas so it's nice to reflect and see what others are working on when it comes to similar projects. I'm excited to see my project
unfold more and more as I dig into it. 

### March 3rd, 2026 "Checkpoints, Cognito & User, Restructuring my Pages. Having it all work."

This week is a heavy week of doing, since now I have to turn my focus on the checkpoint. Getting log4j logs going for each request,
fully testing out all my DAO's and making sure I'm making the right decisions with my Database. I have fully transformed my
Servlets, GenericDaos and Web Pages to talk to each other now. I have also implemented log4j within my RecipeController. 
I plan on tackling Unit Testing and getting photos for each image within my SeasonalIngredient table. Working on the logic
to get the specific SeasonalIngredient to show up and on my web-page and the recipes following along with the specific 
ingredient. 

but for the beginning of the week so far I need to do

- Log4J Implementation (Done)
- ERD Diagram (Screenshot within screenshots)
- Fully Tested Each Dao (2 Remaining Tests within Recipe & User. Test out SeasonalIngredientDao)
- Implemented GenericDao (Done)

### March 4th, 2026 "Mid-Week Update"

I have turned in my #2 Checkpoint and hopefully all goes well. Now I'm focusing on catching on Week 7 work and getting
Seasonal Ingredients to show up on my index and along with the Recipes related to that Seasonal Ingredient. 
Once I'm able to figure that logic out, I think everything else will be smooth sailing.

Updated list from March 3rd:

- Log4J Implementation (Done)
- ERD Diagram (Done)
- Fully Tested Each Dao (Done)
- Implemented GenericDao (Done)

### March 8th, 2026 "Phew, what a week."

Today and the previous days, I began tackling user auth and displaying the seasonal ingredients. After achieving a good
front-end ui and accessing my database to display the monthly ingredient. I feel like I'm closer to my MVP. I'm curious
on how I'm going to approach Create, Update, Delete, and Read when I introduce my API. I may have to go back and mess around
with my database again to figure out Recipes and get a handle on what I actually need for my Recipe database. Reviewing and
comparing my Recipe table to Spoonacular, I may need to add in Recipe Information, a Max Ready Time, Min Servings and a 
Max Servings. I've been wondering this entire time, how I would process the amount of ingredients or should I plug that in
within my Recipe Tables, like 1 Tbsp Sugar, 2 Cups of Corn, etc, etc.. 

At the moment, we have a win. A win where I can be proud. I look forward to what I can achieve next, as each day/week seems
to change my course on what my application would become. 