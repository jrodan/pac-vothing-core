-- MySQL dump 10.13  Distrib 5.7.13, for osx10.11 (x86_64)
--
-- Host: localhost    Database: pac
-- ------------------------------------------------------
-- Server version 5.7.13

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (24),(24),(24),(24),(24),(24);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_permission`
--

DROP TABLE IF EXISTS `vothing_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_permission` (
  `id` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_permission`
--

LOCK TABLES `vothing_permission` WRITE;
/*!40000 ALTER TABLE `vothing_permission` DISABLE KEYS */;
INSERT INTO `vothing_permission` VALUES (2,'2016-06-14 20:45:30','2016-06-14 20:45:30','GUEST'),(3,'2016-06-14 20:45:30','2016-06-14 20:45:30','SURVEY_DELETE'),(4,'2016-06-14 20:45:30','2016-06-14 20:45:30','SURVEY_ADD'),(5,'2016-06-14 20:45:30','2016-06-14 20:45:30','SURVEY_UPDATE'),(6,'2016-06-14 20:45:30','2016-06-14 20:45:30','SURVEY_LIST'),(7,'2016-06-14 20:45:30','2016-06-14 20:45:30','ADMIN'),(8,'2016-06-14 20:45:30','2016-06-14 20:45:30','SURVEYOPTIONRATING_UPDATE'),(9,'2016-06-14 20:45:30','2016-06-14 20:45:30','SURVEYOPTIONRATING_DELETE');
/*!40000 ALTER TABLE `vothing_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_role`
--

DROP TABLE IF EXISTS `vothing_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_role` (
  `id` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_role`
--

LOCK TABLES `vothing_role` WRITE;
/*!40000 ALTER TABLE `vothing_role` DISABLE KEYS */;
INSERT INTO `vothing_role` VALUES (10,'2016-06-14 20:45:30','2016-06-14 20:45:30','admin'),(11,'2016-06-14 20:45:30','2016-06-14 20:45:30','user');
/*!40000 ALTER TABLE `vothing_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_rolepermission`
--

DROP TABLE IF EXISTS `vothing_rolepermission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_rolepermission` (
  `roleId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  KEY `FKqq0w7nurjkdv4xdlpq19asdkf` (`permissionId`),
  KEY `FKsfx8djooiuh7p51ed0ccbqrto` (`roleId`),
  CONSTRAINT `FKqq0w7nurjkdv4xdlpq19asdkf` FOREIGN KEY (`permissionId`) REFERENCES `vothing_permission` (`id`),
  CONSTRAINT `FKsfx8djooiuh7p51ed0ccbqrto` FOREIGN KEY (`roleId`) REFERENCES `vothing_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_rolepermission`
--

LOCK TABLES `vothing_rolepermission` WRITE;
/*!40000 ALTER TABLE `vothing_rolepermission` DISABLE KEYS */;
INSERT INTO `vothing_rolepermission` VALUES (11,2),(11,3),(11,4),(11,5),(11,6),(11,7),(11,8),(11,9);
/*!40000 ALTER TABLE `vothing_rolepermission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_survey`
--

DROP TABLE IF EXISTS `vothing_survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_survey` (
  `id` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc9s04q5k61g8cnh4ddkjeerga` (`userId`),
  CONSTRAINT `FKc9s04q5k61g8cnh4ddkjeerga` FOREIGN KEY (`userId`) REFERENCES `vothing_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_survey`
--

LOCK TABLES `vothing_survey` WRITE;
/*!40000 ALTER TABLE `vothing_survey` DISABLE KEYS */;
INSERT INTO `vothing_survey` VALUES (12,'2016-06-14 20:45:30','2016-06-14 20:45:31','testSurvey1',18),(19,'2016-06-14 20:45:31','2016-06-14 20:45:31','survey 2',1),(20,'2016-06-14 20:45:31','2016-06-14 20:45:31','survey 3',18);
/*!40000 ALTER TABLE `vothing_survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_surveyoption`
--

DROP TABLE IF EXISTS `vothing_surveyoption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_surveyoption` (
  `id` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surveyId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh6txbp8h9xurorpv68l09743p` (`surveyId`),
  CONSTRAINT `FKh6txbp8h9xurorpv68l09743p` FOREIGN KEY (`surveyId`) REFERENCES `vothing_survey` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_surveyoption`
--

LOCK TABLES `vothing_surveyoption` WRITE;
/*!40000 ALTER TABLE `vothing_surveyoption` DISABLE KEYS */;
INSERT INTO `vothing_surveyoption` VALUES (14,'2016-06-14 20:45:31','2016-06-14 20:45:31','Option 2',12),(21,'2016-06-14 20:45:31','2016-06-14 20:45:31','Option 1',12),(22,'2016-06-14 20:45:31','2016-06-14 20:45:31','Option 2',12);
/*!40000 ALTER TABLE `vothing_surveyoption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_surveyoptionrating`
--

DROP TABLE IF EXISTS `vothing_surveyoptionrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_surveyoptionrating` (
  `id` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surveyOptionId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqs7n3rjilyuo493yhr3elk403` (`surveyOptionId`),
  KEY `FKql0apa9w5c1oxxdbn9kgeaixc` (`userId`),
  CONSTRAINT `FKql0apa9w5c1oxxdbn9kgeaixc` FOREIGN KEY (`userId`) REFERENCES `vothing_user` (`id`),
  CONSTRAINT `FKqs7n3rjilyuo493yhr3elk403` FOREIGN KEY (`surveyOptionId`) REFERENCES `vothing_surveyoption` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_surveyoptionrating`
--

LOCK TABLES `vothing_surveyoptionrating` WRITE;
/*!40000 ALTER TABLE `vothing_surveyoptionrating` DISABLE KEYS */;
INSERT INTO `vothing_surveyoptionrating` VALUES (16,'2016-06-14 20:45:31','2016-06-14 20:45:31',NULL,14,18),(23,'2016-06-14 20:48:00','2016-06-14 20:48:00',NULL,21,1);
/*!40000 ALTER TABLE `vothing_surveyoptionrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_user`
--

DROP TABLE IF EXISTS `vothing_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_user` (
  `id` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `foreName` varchar(255) NOT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_user`
--

LOCK TABLES `vothing_user` WRITE;
/*!40000 ALTER TABLE `vothing_user` DISABLE KEYS */;
INSERT INTO `vothing_user` VALUES (1,'2016-06-14 20:45:30','2016-06-14 20:45:31','user','default@vothing.com','duck',NULL,'123'),(17,'2016-06-14 20:45:31','2016-06-14 20:45:31','user','user@vothing.com','user',NULL,'123'),(18,'2016-06-14 20:45:31','2016-06-14 20:45:31','admin','admin@vothing.com','admin',NULL,'123');
/*!40000 ALTER TABLE `vothing_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vothing_userrole`
--

DROP TABLE IF EXISTS `vothing_userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vothing_userrole` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  KEY `FK9cpbvqcbsyqxo7uvlrnlbgjdf` (`roleId`),
  KEY `FKo2yj5s1m30gjj4aoojfep9xj1` (`userId`),
  CONSTRAINT `FK9cpbvqcbsyqxo7uvlrnlbgjdf` FOREIGN KEY (`roleId`) REFERENCES `vothing_role` (`id`),
  CONSTRAINT `FKo2yj5s1m30gjj4aoojfep9xj1` FOREIGN KEY (`userId`) REFERENCES `vothing_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vothing_userrole`
--

LOCK TABLES `vothing_userrole` WRITE;
/*!40000 ALTER TABLE `vothing_userrole` DISABLE KEYS */;
INSERT INTO `vothing_userrole` VALUES (17,11),(18,10);
/*!40000 ALTER TABLE `vothing_userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-14 21:00:46
