CREATE TABLE `recipes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `recipeId` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` LONGTEXT DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `instructions` LONGTEXT DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL,
  `health_score` int NOT NULL,
  `ready_in` int NOT NULL,
  `servings` int NOT NULL,
  `vegan` boolean DEFAULT NULL,
  `gluten_free` boolean DEFAULT NULL,
  `dairy_free` boolean DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


CREATE TABLE `users` (
`id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `phonenumber` VARCHAR(20) DEFAULT NULL,
  `city` VARCHAR(255) DEFAULT NULL,
  `email` VARCHAR(255) UNIQUE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;