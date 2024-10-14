CREATE DATABASE  IF NOT EXISTS `appmovil` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `appmovil`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: appmovil
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'toalla femenina');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalles_pedido`
--

DROP TABLE IF EXISTS `detalles_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalles_pedido` (
  `order_detail_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `sold_price` float NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `order_id_idx` (`order_id`),
  KEY `product_id_idx_idx` (`product_id`),
  CONSTRAINT `order_id_idx` FOREIGN KEY (`order_id`) REFERENCES `pedidos` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_id_idx` FOREIGN KEY (`product_id`) REFERENCES `producto` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalles_pedido`
--

LOCK TABLES `detalles_pedido` WRITE;
/*!40000 ALTER TABLE `detalles_pedido` DISABLE KEYS */;
INSERT INTO `detalles_pedido` VALUES (1,1,1,1,8.99),(2,1,3,1,10.99),(3,2,1,1,8.99),(4,3,1,1,8.99),(5,4,1,1,8.99),(6,5,1,1,8.99),(7,6,1,1,8.99),(8,7,1,1,8.99),(9,8,1,1,8.99),(10,9,1,1,8.99),(11,10,1,1,8.99),(12,11,1,1,8.99),(13,12,1,1,8.99),(14,13,1,1,8.99),(15,14,1,1,8.99),(16,14,1,1,8.99),(17,15,1,1,8.99),(18,16,1,1,8.99),(19,17,1,1,8.99),(20,18,3,1,10.99),(21,19,1,1,8.99),(22,19,2,1,12.99);
/*!40000 ALTER TABLE `detalles_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_de_pago`
--

DROP TABLE IF EXISTS `metodo_de_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_de_pago` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `payment_type` varchar(45) NOT NULL,
  `payment_status` varchar(45) NOT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `pedidos` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_de_pago`
--

LOCK TABLES `metodo_de_pago` WRITE;
/*!40000 ALTER TABLE `metodo_de_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `metodo_de_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `order_status` varchar(45) NOT NULL,
  `total_amount` float NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,1,'Pendiente',0,'2024-10-07','2024-10-07'),(2,1,'Pendiente',0,'2024-10-08','2024-10-08'),(3,1,'Pendiente',0,'2024-10-08','2024-10-08'),(4,1,'Pendiente',0,'2024-10-08','2024-10-08'),(5,1,'Pendiente',0,'2024-10-08','2024-10-08'),(6,1,'Pendiente',0,'2024-10-08','2024-10-08'),(7,1,'Pendiente',0,'2024-10-08','2024-10-08'),(8,1,'Pendiente',0,'2024-10-08','2024-10-08'),(9,1,'Pendiente',0,'2024-10-08','2024-10-08'),(10,1,'Pendiente',0,'2024-10-08','2024-10-08'),(11,1,'Pendiente',0,'2024-10-08','2024-10-08'),(12,1,'Pendiente',0,'2024-10-08','2024-10-08'),(13,1,'Pendiente',0,'2024-10-08','2024-10-08'),(14,1,'Pendiente',0,'2024-10-08','2024-10-08'),(15,1,'Pendiente',0,'2024-10-08','2024-10-08'),(16,1,'Pendiente',0,'2024-10-08','2024-10-08'),(17,1,'Pendiente',0,'2024-10-08','2024-10-08'),(18,3,'Pendiente',0,'2024-10-08','2024-10-08'),(19,4,'Pendiente',0,'2024-10-09','2024-10-09');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(140) NOT NULL,
  `price` float NOT NULL,
  `stock_quantity` int NOT NULL,
  `category_id` int DEFAULT NULL,
  `created_at` date NOT NULL,
  `image_route` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `categoria` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Toalla Femenina Regular','Toalla femenina reutilizable de algodón con alta absorción, tamaño regular.',8.99,100,1,'2024-10-04','image_kit3'),(2,'Toalla Femenina Nocturna','Toalla femenina reutilizable ideal para la noche, con mayor absorción.',12.99,80,1,'2024-10-04','image_kit5'),(3,'Toalla Femenina Deportiva','Toalla femenina reutilizable para actividades deportivas, resistente y cómoda.',10.99,50,1,'2024-10-04','image_nocturnas'),(4,'Toalla Femenina Eco','Toalla femenina ecológica, hecha con materiales sostenibles.',9.49,120,1,'2024-10-04','image_protector'),(5,'Toalla Femenina Suave','Toalla femenina ultra suave, diseñada para pieles sensibles.',11.49,70,1,'2024-10-04','image_regulares');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `curp` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `created_at` date NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'A01751150@tec.mx','c410f86637378d41e739f8a6e70d070b0bb292c5f4c30ec6b29e4d50fce3c5e1','Luis','Balderas','BALSSNDUAHE213U',NULL,'3231321431','2024-10-03'),(2,'prueba@gmail.com','d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1','pepe','pecas','CURP1234',NULL,'544322332','2024-10-07'),(3,'A01749760@tec.mx','441b02df090112b0b48b44e9eb6026d2ca1eec0d685c7d5712b59efbb9423a0c','Andrea','Roman','IDSAIUHU31E1BNDSA',NULL,'5513109533','2024-10-08'),(4,'abc@tec.mx','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','Roberto','M R','vsdvsd',NULL,'xxsdvsvgfdbgfdb','2024-10-09');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-09  9:28:00
