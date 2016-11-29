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
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `dataCriacao` datetime NOT NULL,
  `local` varchar(45) DEFAULT NULL,
  `familia_idFamilia` int(11) NOT NULL,
  `tipoEvento_idtipoEvento` int(11) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idEvento`,`familia_idFamilia`,`tipoEvento_idtipoEvento`,`usuario_idUsuario`),
  KEY `fk_Evento_familia1_idx` (`familia_idFamilia`),
  KEY `fk_evento_tipoEvento1_idx` (`tipoEvento_idtipoEvento`),
  KEY `fk_evento_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_Evento_familia1` FOREIGN KEY (`familia_idFamilia`) REFERENCES `familia` (`idFamilia`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_tipoEvento1` FOREIGN KEY (`tipoEvento_idtipoEvento`) REFERENCES `tipoevento` (`idTipoEvento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evento_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (2,'Primeiro encontro Família Fidencio 2017','Encontro para planejamento do ano de 2017.','2016-11-26 17:50:53','Casa do Laerzio',1,1,1),(3,'Encontro final da Família Fidencio 2017','Encontro para comemoraçao do ano de 2017.','2016-11-26 17:51:31','Chácara da vó',1,2,1),(4,'Encontro do meio do ano','Churrasquinho.','2016-11-26 17:52:01','Chácara da vó',1,1,1),(5,'Encontro do meio do ano','Churrasquinho.','2016-11-26 17:52:19','Chácara da vó',2,1,1),(6,'Encontro do meio do ano','Churrasquinho.','2016-11-26 17:52:25','Chácara da vó',3,1,1),(7,'Encontro final da Família Silva 2017','Encontro para comemoraçao do ano de 2017.','2016-11-26 17:52:44','Chácara da tia',3,2,1);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
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
