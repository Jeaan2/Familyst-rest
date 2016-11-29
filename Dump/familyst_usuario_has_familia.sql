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
-- Table structure for table `usuario_has_familia`
--

DROP TABLE IF EXISTS `usuario_has_familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_has_familia` (
  `usuario_idUsuario` int(11) NOT NULL,
  `familia_idFamilia` int(11) NOT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`usuario_idUsuario`,`familia_idFamilia`),
  KEY `fk_usuario_has_familia_familia1_idx` (`familia_idFamilia`),
  KEY `fk_usuario_has_familia_usuario_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_usuario_has_familia_familia1` FOREIGN KEY (`familia_idFamilia`) REFERENCES `familia` (`idFamilia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_familia_usuario` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_familia`
--

LOCK TABLES `usuario_has_familia` WRITE;
/*!40000 ALTER TABLE `usuario_has_familia` DISABLE KEYS */;
INSERT INTO `usuario_has_familia` VALUES (1,1,1),(1,2,1),(1,3,1),(2,1,1),(3,1,1),(4,1,1),(5,1,1),(6,3,1),(7,3,1),(8,3,1),(9,2,1),(10,2,1),(11,2,1);
/*!40000 ALTER TABLE `usuario_has_familia` ENABLE KEYS */;
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
