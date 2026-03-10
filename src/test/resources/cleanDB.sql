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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'Beets'),(2,'goat cheese'),(3,'Ramps'),(4,'garlic'),(5,'noodles'),(6,'Strawberries'),(7,'crust'),(8,'salt'),(9,'olive oil'),(10,'Apples'),(11,'oats'),(12,'cinnamon'),(13,'Asparagus'),(14,'eggs'),(15,'feta'),(16,'Corn'),(17,'potatoes'),(18,'cream'),(19,'vinegar'),(20,'cloves'),(21,'Pork'),(22,'cider'),(23,'gruyere');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,'Beet Salad','Madison winter staple.','2026-02-21 18:26:41','Beets, goat cheese.','https://i.imgur.com/9992ByT.jpeg',1,12),(2,'Ramp Pasta','Spring favorite.','2026-02-21 18:26:41','Ramps, garlic, noodles.','https://i.imgur.com/HhwXKmO.jpeg',1,4),(3,'Strawberry Galette','Rustic summer dessert.','2026-02-21 18:26:41','Strawberries, crust.','https://i.imgur.com/It1Yv4a.jpeg',2,6),(4,'Roasted Beets','Simple and earthy.','2026-02-21 18:26:41','Beets, salt, olive oil.','https://i.imgur.com/UZRjHsG.jpeg',2,12),(5,'Apple Crisp','Classic fall treat.','2026-02-21 18:26:41','Apples, oats, cinnamon.','https://i.imgur.com/BMHbUk1.jpeg',3,9),(6,'Asparagus Frittata','Perfect Sunday brunch.','2026-02-21 18:26:41','Asparagus, eggs, feta.','https://i.imgur.com/lCPKDqI.jpeg',4,5),(7,'Corn Chowder','Fresh summer corn soup.','2026-02-21 18:26:41','Corn, potatoes, cream.','https://i.imgur.com/AIOh7TG.jpeg',5,8),(8,'Pickled Beets','Tangy and preserved.','2026-02-21 18:26:41','Beets, vinegar, cloves.','https://i.imgur.com/ig9ZXoV.jpeg',6,12),(9,'Apple Cider Glazed Pork','Savory autumn dinner.','2026-02-21 18:26:41','Pork, apples, cider.','https://i.imgur.com/rH714Im.jpeg',6,9),(10,'Garlic Potato Gratin','Hearty comfort food.','2026-02-21 18:26:41','Potatoes, garlic, gruyere.','https://i.imgur.com/UYnGgJu.jpeg',5,2);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_ingredient`
--

DROP TABLE IF EXISTS `recipe_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_ingredient` (
                                     `quantity` int DEFAULT NULL,
                                     `unit` varchar(15) DEFAULT NULL,
                                     `recipe_id` int NOT NULL,
                                     `ingredient_id` int NOT NULL,
                                     PRIMARY KEY (`recipe_id`,`ingredient_id`),
                                     KEY `recipe_ingredient_ingredient_id_fk` (`ingredient_id`),
                                     CONSTRAINT `recipe_ingredient_ingredient_id_fk` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`),
                                     CONSTRAINT `recipe_ingredient_recipe_id_fk` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_ingredient`
--

LOCK TABLES `recipe_ingredient` WRITE;
/*!40000 ALTER TABLE `recipe_ingredient` DISABLE KEYS */;
INSERT INTO `recipe_ingredient` VALUES (3,'Whole',1,1),(4,'oz',1,2),(1,'Bunch',2,3),(3,'Cloves',2,4),(12,'oz',2,5),(2,'Cups',3,6),(1,'Each',3,7),(4,'Whole',4,1),(1,'tsp',4,8),(2,'tbsp',4,9),(6,'Whole',5,10),(1,'Cup',5,11),(2,'tsp',5,12),(1,'Bunch',6,13),(8,'Large',6,14),(4,'oz',6,15),(4,'Ears',7,16),(3,'Large',7,17),(1,'Cup',7,18),(2,'lbs',8,1),(2,'Cups',8,19),(1,'tsp',8,20),(2,'Whole',9,10),(2,'lbs',9,21),(1,'Cup',9,22),(4,'Cloves',10,4),(4,'Large',10,17),(6,'oz',10,23);
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
                        `date_of_birth` date DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Joey','Chang','jchang','jchang123','1997-02-13'),(2,'Michelle','Smith','msmith','msmith123','1998-05-12'),(3,'Alice','Baker','abaker','abaker123','1995-10-20'),(4,'Bob','Cook','bcook','bcook123','1990-01-05'),(5,'Charlie','Farmer','cfarmer','cfarmer123','1988-03-29'),(6,'Nate','Grimes','ngrimes','ngrimes123','2001-07-15');
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

-- Dump completed on 2026-03-09 22:37:04
