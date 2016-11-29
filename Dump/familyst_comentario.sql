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
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentario` (
  `idComentario` int(11) NOT NULL AUTO_INCREMENT,
  `dados` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `noticia_idnoticia` int(11) DEFAULT NULL,
  `evento_idEvento` int(11) DEFAULT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idComentario`,`usuario_idUsuario`),
  KEY `fk_comentario_noticia1_idx` (`noticia_idnoticia`),
  KEY `fk_comentario_evento1_idx` (`evento_idEvento`),
  KEY `fk_comentario_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_comentario_evento1` FOREIGN KEY (`evento_idEvento`) REFERENCES `evento` (`idEvento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario_noticia1` FOREIGN KEY (`noticia_idnoticia`) REFERENCES `noticia` (`idNoticia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (6,'e ai galeraaaaaaaaaaaaaaaaaaaaaaaaaaaa','2016-11-27 20:38:18',1,2,1),(7,'boraaaaaaaaaaaaaaaaa','2016-11-27 20:38:29',1,2,1),(8,'QUERO VEEEEEEE','2016-11-27 20:38:34',1,2,1),(9,'QUERO VEEEEEEE','2016-11-27 20:38:41',6,3,1),(10,'ESSA GALERAAAAA','2016-11-27 20:38:48',2,3,1),(11,'NO FINAL DE SEMANAAAAAAAAAAAAAA','2016-11-27 20:38:57',2,3,1),(12,'NO FINAL DE SEMANAAAAAAAAAAAAAA','2016-11-27 20:39:07',2,4,1),(13,'NO FINAL DE SEMANAAAAAAAAAAAAAA','2016-11-27 20:39:12',2,5,1),(14,'BSSSSSS','2016-11-27 20:39:16',7,5,1),(15,'AEEEEE','2016-11-27 20:39:19',7,5,1),(16,'BORAAAAAAAAA','2016-11-27 20:39:26',3,6,1),(17,'QUERO VE','2016-11-27 20:39:31',8,6,1),(18,'TUNTS','2016-11-27 20:39:35',8,6,1),(19,'AEHOOO','2016-11-27 20:39:40',4,6,1),(20,'AEHOOO','2016-11-27 20:39:46',4,7,1),(21,'CHUPA Q EH DE MANGA','2016-11-27 20:39:52',9,7,1),(22,'HÃ','2016-11-27 20:39:56',5,7,1),(23,'CACETE','2016-11-27 20:40:01',5,7,1);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29 21:21:09
