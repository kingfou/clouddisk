-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: clouddisk
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `files_info`
--

DROP TABLE IF EXISTS `files_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `files_info` (
  `fileId` int(11) NOT NULL AUTO_INCREMENT,
  `user_Id` int(11) DEFAULT '1',
  `name` varchar(255) DEFAULT NULL,
  `uploaddate` datetime DEFAULT NULL,
  `size` double DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `folder_Id` int(11) DEFAULT '1' COMMENT '1',
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files_info`
--

LOCK TABLES `files_info` WRITE;
/*!40000 ALTER TABLE `files_info` DISABLE KEYS */;
INSERT INTO `files_info` VALUES (1,NULL,'pom.xml','2019-10-24 07:57:09',2,'H://upload/pom.xml',1),(2,NULL,'README','2019-10-24 08:00:53',0,'H://upload/README',2),(3,NULL,'pom.xml','2019-10-24 08:03:13',2,'H://upload/pom.xml',3),(4,NULL,'pom.xml','2019-10-24 08:04:17',2,'H://upload/pom.xml',NULL),(5,NULL,'pom.xml','2019-10-24 08:04:20',2,'H://upload/pom.xml',NULL),(6,NULL,'pom.xml','2019-10-24 08:20:43',2,'H://upload/pom.xml',NULL),(7,NULL,'pom.xml','2019-10-24 08:21:00',2,'H://upload/pom.xml',NULL),(8,NULL,'clouddisk.iml','2019-10-24 08:29:44',8,'H://upload/clouddisk.iml',NULL),(9,12,'pom.xml','2019-10-24 08:30:26',2,'H://upload/pom.xml',0),(10,12,'pom.xml','2019-10-24 08:31:35',2,'H://upload/pom.xml',0),(11,12,'pom.xml','2019-10-24 08:40:26',2,'H://upload/pom.xml',0),(12,12,'clouddisk.iml','2019-10-24 08:41:21',8,'H://upload/clouddisk.iml',0),(13,12,'README','2019-10-24 08:42:02',0,'H://upload/README',0),(14,12,'pom.xml','2019-10-24 08:43:05',2,'H://upload/pom.xml',0),(15,12,'clouddisk.iml','2019-10-24 08:46:07',8,'H://upload/clouddisk.iml',0),(16,1,'clouddisk.iml','2019-10-24 08:48:01',8,'H://upload/clouddisk.iml',0),(17,1,'pom.xml','2019-10-24 08:49:38',2,'H://upload/pom.xml',0),(18,1,'pom.xml','2019-10-24 08:50:36',2,'H://upload/pom.xml',0),(19,1,'pom.xml','2019-10-24 08:52:35',2,'H://upload/pom.xml',NULL),(20,1,'pom.xml','2019-10-24 09:00:22',2,'H://upload/pom.xml',NULL),(21,NULL,'clouddisk.iml','2019-10-24 10:49:52',8,'H://upload/clouddisk.iml',NULL),(22,NULL,'clouddisk.iml','2019-10-24 10:50:12',8,'H://upload/clouddisk.iml',NULL),(23,NULL,'README','2019-10-24 10:55:43',0,'H://upload/README',NULL),(24,NULL,'README','2019-10-24 10:56:25',0,'H://upload/README',NULL),(25,1,'README','2019-10-24 10:58:20',0,'H://upload/README',NULL),(26,1,'README','2019-10-24 10:59:34',0,'H://upload/README',NULL),(27,1,'README','2019-10-24 10:59:44',0,'H://upload/README',NULL),(28,1,'README','2019-10-24 10:59:50',0,'H://upload/README',NULL),(29,1,'README','2019-10-24 11:36:54',0,'H://upload/README',NULL),(30,1,'README','2019-10-24 11:36:56',0,'H://upload/README',NULL),(31,1,'README','2019-10-24 11:37:08',0,'H://upload/README',NULL),(32,1,'pom.xml','2019-10-24 11:45:39',2,'H://upload/pom.xml',NULL),(33,1,'README','2019-10-24 12:09:41',0,'H://upload/README',NULL),(34,1,'clouddisk.iml','2019-10-25 01:25:03',8,'H://upload/clouddisk.iml',NULL),(35,1,'clouddisk.iml','2019-10-25 01:25:06',8,'H://upload/clouddisk.iml',NULL),(36,1,'clouddisk.iml','2019-10-25 01:25:08',8,'H://upload/clouddisk.iml',NULL),(37,1,'clouddisk.iml','2019-10-25 01:25:16',8,'H://upload/clouddisk.iml',NULL),(38,1,'clouddisk.iml','2019-10-25 01:25:26',8,'H://upload/clouddisk.iml',NULL),(39,1,'n0153282900000005.jpg','2019-10-28 03:03:11',5,'/kingfou/n0153282900000005.jpg',1);
/*!40000 ALTER TABLE `files_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folders`
--

DROP TABLE IF EXISTS `folders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `folders` (
  `name` varchar(255) NOT NULL,
  `folderId` int(11) NOT NULL AUTO_INCREMENT,
  `uploaddate` datetime DEFAULT NULL,
  `size` double DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`folderId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folders`
--

LOCK TABLES `folders` WRITE;
/*!40000 ALTER TABLE `folders` DISABLE KEYS */;
INSERT INTO `folders` VALUES ('电影',1,'2019-10-17 10:37:08',300,1),('学习',2,'2019-10-02 10:38:53',20,1),('其他',3,'2019-10-23 10:39:39',30,1);
/*!40000 ALTER TABLE `folders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `name` varchar(255) DEFAULT NULL,
  `stu_Numb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('kingfou','201821033400',1,'1111','111@mail.com'),('test','1111',2,'1111',NULL),('test','2222',3,'2222',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-28 21:20:11
