CREATE DATABASE  IF NOT EXISTS `familyst` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `familyst`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: familyst
-- ------------------------------------------------------
-- Server version	5.7.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `idFacebook` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `local` varchar(45) DEFAULT NULL,
  `dataCriacao` datetime NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Jefferson Diego Fidencio','12345',NULL,'jdfidencio@hotmail.com','Curitiba - PR','2016-11-26 17:45:41'),(2,'Aline Fidencio','12345',NULL,'am@hotmail.com','Curitiba - PR','2016-11-26 18:51:14'),(3,'Thais Fidencio','12345',NULL,'at@hotmail.com','Curitiba - PR','2016-11-26 18:51:24'),(4,'Laerzio Fidencio','12345',NULL,'lsfas@hotmail.com','Curitiba - PR','2016-11-26 18:51:30'),(5,'Neiva Fidencio','12345',NULL,'ds@hotmail.com','Curitiba - PR','2016-11-26 18:51:40'),(6,'Jean Silva','12345',NULL,'jeaan3@gmail.com','Curitiba - PR','2016-11-26 18:52:13'),(7,'Mae do Jean Silva','12345',NULL,'jasdas@gmail.com','Curitiba - PR','2016-11-26 18:52:25'),(8,'Pai do Jean Silva','12345',NULL,'jasdasdss@gmail.com','Curitiba - PR','2016-11-26 18:52:33'),(9,'Adriel','12345',NULL,'ss@gmail.com','Curitiba - PR','2016-11-26 18:52:44'),(10,'Nadia Cunha','12345',NULL,'ssdss@gmail.com','Curitiba - PR','2016-11-26 18:52:50'),(11,'Eurides Cunha','12345',NULL,'asdasd@gmail.com','Curitiba - PR','2016-11-26 18:52:58');
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

-- Dump completed on 2016-11-29 21:21:10
