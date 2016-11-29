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
-- Table structure for table `familia`
--

DROP TABLE IF EXISTS `familia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `familia` (
  `idFamilia` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `dataCriacao` datetime NOT NULL,
  `local` varchar(45) NOT NULL,
  `galeria_idgaleria` int(11) DEFAULT NULL,
  `arquivo_idarquivo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFamilia`),
  KEY `fk_familia_galeria1_idx` (`galeria_idgaleria`),
  KEY `fk_familia_arquivo1_idx` (`arquivo_idarquivo`),
  CONSTRAINT `fk_familia_arquivo1` FOREIGN KEY (`arquivo_idarquivo`) REFERENCES `arquivo` (`idArquivo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_familia_galeria1` FOREIGN KEY (`galeria_idgaleria`) REFERENCES `galeria` (`idGaleria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familia`
--

LOCK TABLES `familia` WRITE;
/*!40000 ALTER TABLE `familia` DISABLE KEYS */;
INSERT INTO `familia` VALUES (1,'Família Fidencio','Família gente boa!','2016-11-26 17:46:00','Curitiba - PR',1,1),(2,'Família Cunha','Família sempre unida!','2016-11-26 17:46:48','Paranaguá - PR',2,2),(3,'Família Silva','Família da hora!','2016-11-26 17:46:58','Curitiba - PR',3,3);
/*!40000 ALTER TABLE `familia` ENABLE KEYS */;
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
