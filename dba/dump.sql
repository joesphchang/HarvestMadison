-- MySQL dump 10.13  Distrib 8.4.7, for macos15 (arm64)
--
-- Host: 127.0.0.1    Database: harvest_madison
-- ------------------------------------------------------
-- Server version	8.4.7

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'Beets'),(2,'goat cheese'),(3,'Ramps'),(4,'garlic'),(5,'noodles'),(6,'Strawberries'),(7,'crust'),(8,'salt'),(9,'olive oil'),(10,'Apples'),(11,'oats'),(12,'cinnamon'),(13,'Asparagus'),(14,'eggs'),(15,'feta'),(16,'Corn'),(17,'potatoes'),(18,'cream'),(19,'vinegar'),(20,'cloves'),(21,'Pork'),(22,'cider'),(23,'gruyere'),(24,'Avocado'),(25,'Sourdough Bread'),(26,'Lemon Juice'),(27,'Sunflower Seeds'),(28,'Radishes'),(29,'Cucumber'),(30,'Sesame Oil'),(31,'Soy Sauce');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ingredients_text` varchar(1000) DEFAULT NULL,
  `image_url` varchar(500) DEFAULT 'https://i.imgur.com/BpAX3hE.jpeg',
  `user_id` int NOT NULL,
  `seasonal_ingredient_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `recipe_user_id_fk` (`user_id`),
  KEY `recipe_seasonal_id_fk` (`seasonal_ingredient_id`),
  CONSTRAINT `recipe_seasonal_id_fk` FOREIGN KEY (`seasonal_ingredient_id`) REFERENCES `seasonal_ingredient` (`id`),
  CONSTRAINT `recipe_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Beet Salad','Madison winter staple.','2026-02-21 18:26:41','Beets, goat cheese.','https://i.imgur.com/9992ByT.jpeg',1,12),(2,'Ramp Pasta','Spring favorite.','2026-02-21 18:26:41','Ramps, garlic, noodles.','https://i.imgur.com/HhwXKmO.jpeg',1,4),(3,'Strawberry Galette','Rustic summer dessert.','2026-02-21 18:26:41','Strawberries, crust.','https://i.imgur.com/It1Yv4a.jpeg',2,6),(4,'Roasted Beets','Simple and earthy.','2026-02-21 18:26:41','Beets, salt, olive oil.','https://i.imgur.com/UZRjHsG.jpeg',2,12),(5,'Apple Crisp','Classic fall treat.','2026-02-21 18:26:41','Apples, oats, cinnamon.','https://i.imgur.com/BMHbUk1.jpeg',3,9),(6,'Asparagus Frittata','Perfect Sunday brunch.','2026-02-21 18:26:41','Asparagus, eggs, feta.','https://i.imgur.com/lCPKDqI.jpeg',4,5),(7,'Corn Chowder','Fresh summer corn soup.','2026-02-21 18:26:41','Corn, potatoes, cream.','https://i.imgur.com/AIOh7TG.jpeg',5,8),(8,'Pickled Beets','Tangy and preserved.','2026-02-21 18:26:41','Beets, vinegar, cloves.','https://i.imgur.com/ig9ZXoV.jpeg',6,12),(9,'Apple Cider Glazed Pork','Savory autumn dinner.','2026-02-21 18:26:41','Pork, apples, cider.','https://i.imgur.com/rH714Im.jpeg',6,9),(10,'Garlic Potato Gratin','Hearty comfort food.','2026-02-21 18:26:41','Potatoes, garlic, gruyere.','https://i.imgur.com/UYnGgJu.jpeg',5,2),(11,'Parsnip Puree','Creamy and earthy alternative to mashed potatoes.','2026-03-25 03:45:37','Parsnips, Cream','https://i.imgur.com/4vPXapK.jpeg',1,1),(12,'Bacon Wrapped Asparagus','Crispy bacon meets tender Madison asparagus.','2026-03-25 03:45:37','Asparagus, Salt, Bacon','https://i.imgur.com/Wye4bWF.jpeg',2,5),(13,'Zucchini Fritters','Shredded zucchini with a hint of lemon and feta.','2026-03-25 03:45:37','Zucchini, Feta, Lemon','https://i.imgur.com/h3M7Xnu.jpeg',3,7),(14,'Sweet Corn Elote','Grilled corn with crema, lime, and chili powder.','2026-03-25 03:45:37','Corn, Olive Oil, Lime','https://i.imgur.com/3wv07Y1.jpeg',4,8),(15,'Roasted Pumpkin Soup','Smooth and warming, a true October classic.','2026-03-25 03:45:37','Pumpkins, Cream','https://i.imgur.com/wx33WBd.jpeg',5,10),(16,'Cranberry Orange Sauce','Fresh Wisconsin cranberries with citrus zest.','2026-03-25 03:45:37','Cranberries, Vinegar','https://i.imgur.com/nYp5h5E.jpeg',6,11),(17,'Potato Leek Soup','Thick, creamy winter soup with local russets.','2026-03-25 03:45:37','Potatoes, Cream','https://i.imgur.com/TdoPubM.jpeg',2,2),(18,'Honeycrisp Apple Salad','Sliced apples, walnuts, and a cider vinaigrette.','2026-03-25 03:45:37','Apples, Cider','https://i.imgur.com/9nv8tvI.jpeg',3,9),(19,'Beet & Goat Cheese Crostini','Elegant appetizer with honey and fresh beets.','2026-03-25 03:45:37','Beets, Goat Cheese','https://i.imgur.com/kkoMr6F.jpeg',4,12),(20,'Microgreen Avocado Toast','Creamy avocado on sourdough topped with crunchy microgreens.','2026-03-25 03:45:37','Microgreens, Avocado, Sourdough','https://i.imgur.com/MRMm5Ij.jpeg',1,3),(21,'Spring Microgreen Salad','A light, nutrient-dense salad with radishes and seeds.','2026-03-25 03:45:37','Microgreens, Radishes, Sunflower Seeds','https://i.imgur.com/wFpoIg1.jpeg',5,3),(22,'Sesame Microgreen Bowl','Asian-inspired bowl with fresh cucumbers and sesame dressing.','2026-03-25 03:45:37','Microgreens, Cucumber, Sesame Oil','https://i.imgur.com/5NHCXgL.jpeg',6,3);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_ingredient`
--

DROP TABLE IF EXISTS `recipe_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_ingredient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `unit` varchar(15) DEFAULT NULL,
  `recipe_id` int NOT NULL,
  `ingredient_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_recipe_ingredient` (`recipe_id`,`ingredient_id`),
  KEY `recipe_ingredient_ingredient_id_fk` (`ingredient_id`),
  CONSTRAINT `recipe_ingredient_ingredient_id_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`),
  CONSTRAINT `recipe_ingredient_recipe_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_ingredient`
--

LOCK TABLES `recipe_ingredient` WRITE;
/*!40000 ALTER TABLE `recipe_ingredient` DISABLE KEYS */;
INSERT INTO `recipe_ingredient` VALUES (1,3,'Whole',1,1),(2,4,'oz',1,2),(3,1,'Bunch',2,3),(4,3,'Cloves',2,4),(5,12,'oz',2,5),(6,2,'Cups',3,6),(7,1,'Each',3,7),(8,4,'Whole',4,1),(9,1,'tsp',4,8),(10,2,'tbsp',4,9),(11,6,'Whole',5,10),(12,1,'Cup',5,11),(13,2,'tsp',5,12),(14,1,'Bunch',6,13),(15,8,'Large',6,14),(16,4,'oz',6,15),(17,4,'Ears',7,16),(18,3,'Large',7,17),(19,1,'Cup',7,18),(20,2,'lbs',8,1),(21,2,'Cups',8,19),(22,1,'tsp',8,20),(23,2,'Whole',9,10),(24,2,'lbs',9,21),(25,1,'Cup',9,22),(26,4,'Cloves',10,4),(27,4,'Large',10,17),(28,6,'oz',10,23),(29,1,'Cup',20,3),(30,1,'Whole',20,24),(31,2,'Slices',20,25),(32,2,'Cups',21,3),(33,4,'Whole',21,28),(34,2,'tbsp',21,9),(35,2,'Cups',22,3),(36,1,'Whole',22,29),(37,1,'tbsp',22,30),(38,4,'Whole',11,1),(39,2,'tbsp',11,18),(40,1,'Bunch',12,13),(41,4,'Slices',12,8),(42,2,'Whole',13,17),(43,4,'oz',13,15),(44,4,'Ears',14,16),(45,1,'tbsp',14,9),(46,1,'Small',15,10),(47,1,'Cup',15,18),(48,12,'oz',16,11),(49,1,'Cup',16,19),(50,3,'Large',17,17),(51,1,'Cup',17,18),(52,2,'Whole',18,10),(53,1,'tbsp',18,22),(54,2,'Medium',19,1),(55,4,'oz',19,2);
/*!40000 ALTER TABLE `recipe_ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seasonal_ingredient`
--

DROP TABLE IF EXISTS `seasonal_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seasonal_ingredient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `s_ingredient_name` varchar(255) DEFAULT NULL,
  `start_date` int DEFAULT NULL,
  `end_date` int DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seasonal_ingredient`
--

LOCK TABLES `seasonal_ingredient` WRITE;
/*!40000 ALTER TABLE `seasonal_ingredient` DISABLE KEYS */;
INSERT INTO `seasonal_ingredient` VALUES (1,'Parsnips',1,2,'https://i.imgur.com/B5vh5Zf.jpeg'),(2,'Potatoes',2,3,'https://i.imgur.com/wUh9FUG.jpeg'),(3,'Microgreens',3,4,'https://i.imgur.com/ZHPUdpC.jpeg'),(4,'Ramps',4,5,'https://i.imgur.com/bnzd8RH.jpeg'),(5,'Asparagus',5,6,'https://i.imgur.com/26AxKw5.jpeg'),(6,'Strawberries',6,7,'https://i.imgur.com/rtjztRL.jpeg'),(7,'Zucchini',7,8,'https://i.imgur.com/vbmNCeS.jpeg'),(8,'Sweet Corn',8,9,'https://i.imgur.com/sDb7bhV.jpeg'),(9,'Apples',9,10,'https://i.imgur.com/s5F3EIP.jpeg'),(10,'Pumpkins',10,11,'https://i.imgur.com/K84wsf9.jpeg'),(11,'Cranberries',11,12,'https://i.imgur.com/4dpyzwK.jpeg'),(12,'Beets',12,1,'https://i.imgur.com/5QIEntd.jpeg');
/*!40000 ALTER TABLE `seasonal_ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(150) DEFAULT NULL,
  `last_name` varchar(150) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `cognito_sub` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Joey','Chang','jchang','Jchang123!','jchang@madisoncollege.edu','a11b5550-f081-70f8-834e-6661181f0021'),(2,'Michelle','Smith','msmith','Msmith123!','msmith@madisoncollege.edu','71eb9530-0081-708c-8f62-08fd54b325f2'),(3,'Alice','Baker','abaker','Abaker123!','abaker@madisoncollege.du','516b5550-f0e1-7042-3c8b-550569552da9'),(4,'Bob','Cook','bcook','Bcook123!','bcook@madisoncollege.edu','311b4520-80c1-7051-0d7d-ce2e03714099'),(5,'Charlie','Farmer','cfarmer','Cfarmer123!','cfarmer@madisoncollege.edu','11ebe540-0091-7091-266f-d74cf0ce1b4e'),(6,'Nate','Grimes','ngrimes','Ngrimes123!','ngrimes@madisoncollege.edu','71cb35f0-5061-7021-fa3b-a2318f5e154c');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_recipe`
--

DROP TABLE IF EXISTS `user_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_recipe` (
  `user_id` int DEFAULT NULL,
  `recipe_id` int DEFAULT NULL,
  `favorite` tinyint(1) DEFAULT NULL,
  KEY `ur_recipe_id_fk` (`recipe_id`),
  KEY `ur_user_id_fk` (`user_id`),
  CONSTRAINT `ur_recipe_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`),
  CONSTRAINT `ur_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_recipe`
--

LOCK TABLES `user_recipe` WRITE;
/*!40000 ALTER TABLE `user_recipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_recipe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-24 23:55:51
