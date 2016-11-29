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
-- Table structure for table `noticia`
--

DROP TABLE IF EXISTS `noticia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `noticia` (
  `idNoticia` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `familia_idFamilia` int(11) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idNoticia`,`familia_idFamilia`,`usuario_idUsuario`),
  KEY `fk_noticia_familia1_idx` (`familia_idFamilia`),
  KEY `fk_noticia_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_noticia_familia1` FOREIGN KEY (`familia_idFamilia`) REFERENCES `familia` (`idFamilia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_noticia_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticia`
--

LOCK TABLES `noticia` WRITE;
/*!40000 ALTER TABLE `noticia` DISABLE KEYS */;
INSERT INTO `noticia` VALUES (1,'A data do evento x FOI ALTERADA!!!!','2016-11-26 18:39:43',1,1),(2,'Dai galeraaaaaa','2016-11-26 18:39:59',1,1),(3,'Pessoal venho convidar todos para a.. ','2016-11-26 18:44:32',1,1),(4,'DAEEEEEE GALERAAAAAAAAAAAAAA WUUUUUUWWW','2016-11-26 18:44:46',1,1),(5,'DAEEEEEE GALERAAAAAAAAAAAAAA WUUUUUUWWW','2016-11-26 18:44:55',2,1),(6,'DALE DALE DALE','2016-11-26 18:45:02',2,1),(7,'PQP','2016-11-26 18:45:07',2,1),(8,'ULTIMO GAS','2016-11-26 18:45:13',2,1),(9,'QUERO VE','2016-11-26 18:45:16',2,1),(10,'ESSA GALERA','2016-11-26 18:45:20',2,1),(11,'NO FINAL DE SEMANA PORRAAAAAAA','2016-11-26 18:45:29',3,1),(12,'NO FINAL DE SEMANA PORRAAAAAAA','2016-11-26 18:45:50',3,1);
/*!40000 ALTER TABLE `noticia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29 21:21:11
