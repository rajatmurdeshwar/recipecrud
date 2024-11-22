CREATE DATABASE  IF NOT EXISTS `recipes_tracker`;
USE `recipes_tracker`;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;

CREATE TABLE `recipes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipeId` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `instructions` text DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL,
  `health_score` int NOT NULL,
  `ready_in` int NOT NULL,
  `servings` int NOT NULL,
  `vegan` boolean DEFAULT NULL,
  `gluten_free` boolean DEFAULT NULL,
  `dairy_free` boolean DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;