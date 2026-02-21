-- 1. DROP ALL TABLES (Ordered to handle foreign keys)
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `user_recipe`;
DROP TABLE IF EXISTS `recipe`;
DROP TABLE IF EXISTS `seasonal_ingredient`;
DROP TABLE IF EXISTS `user`;
SET FOREIGN_KEY_CHECKS = 1;

-- 2. CREATE TABLE STRUCTURES (Aligned with your Java Entities)

CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        `first_name` varchar(150) DEFAULT NULL,
                        `last_name` varchar(150) DEFAULT NULL,
                        `user_name` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `date_of_birth` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `seasonal_ingredient` (
                                       `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       `s_ingredient_name` varchar(255) DEFAULT NULL,
                                       `start_date` int DEFAULT NULL,
                                       `end_date` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `recipe` (
                          `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `recipe_name` varchar(255) NOT NULL,
                          `description` varchar(255) DEFAULT NULL,
                          `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                          `ingredients_text` varchar(1000) DEFAULT NULL,
                          `user_id` int NOT NULL,
                          `seasonal_ingredient_id` int NOT NULL,
                          CONSTRAINT `recipe_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                          CONSTRAINT `recipe_seasonal_id_fk` FOREIGN KEY (`seasonal_ingredient_id`) REFERENCES `seasonal_ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_recipe` (
                               `user_id` int DEFAULT NULL,
                               `recipe_id` int DEFAULT NULL,
                               `favorite` tinyint(1) DEFAULT NULL,
                               CONSTRAINT `ur_recipe_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`),
                               CONSTRAINT `ur_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. INSERT ALL SEED DATA

-- Users
INSERT INTO `user` VALUES
                       (1,'Joey','Chang','jchang','jchang123','1997-02-13'),
                       (2,'Michelle','Smith','msmith','msmith123','1998-05-12'),
                       (3,'Alice','Baker','abaker','abaker123','1995-10-20'),
                       (4,'Bob','Cook','bcook','bcook123','1990-01-05'),
                       (5,'Charlie','Farmer','cfarmer','cfarmer123','1988-03-29'),
                       (6,'Nate','Grimes','ngrimes','ngrimes123','2001-07-15');

-- Ingredients
INSERT INTO `seasonal_ingredient` VALUES
                                      (1,'Parsnips',1,2),(2,'Potatoes',2,3),(3,'Microgreens',3,4),(4,'Ramps',4,5),
                                      (5,'Asparagus',5,6),(6,'Strawberries',6,7),(7,'Zucchini',7,8),(8,'Sweet Corn',8,9),
                                      (9,'Apples',9,10),(10,'Pumpkins',10,11),(11,'Cranberries',11,12),(12,'Beets',12,1);

-- 2. SEED RECIPES (10 Recipes across the team)
INSERT INTO `recipe` (`recipe_name`, `description`, `ingredients_text`, `user_id`, `seasonal_ingredient_id`) VALUES
('Beet Salad', 'Madison winter staple.', 'Beets, goat cheese.', 1, 12),
('Ramp Pasta', 'Spring favorite.', 'Ramps, garlic, noodles.', 1, 4),
('Strawberry Galette', 'Rustic summer dessert.', 'Strawberries, crust.', 2, 6),
('Roasted Beets', 'Simple and earthy.', 'Beets, salt, olive oil.', 2, 12),
('Apple Crisp', 'Classic fall treat.', 'Apples, oats, cinnamon.', 3, 9),
('Asparagus Frittata', 'Perfect Sunday brunch.', 'Asparagus, eggs, feta.', 4, 5),
('Corn Chowder', 'Fresh summer corn soup.', 'Corn, potatoes, cream.', 5, 8),
('Pickled Beets', 'Tangy and preserved.', 'Beets, vinegar, cloves.', 6, 12),
('Apple Cider Glazed Pork', 'Savory autumn dinner.', 'Pork, apples, cider.', 6, 9),
('Garlic Potato Gratin', 'Hearty comfort food.', 'Potatoes, garlic, gruyere.', 5, 2);