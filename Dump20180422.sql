-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: healer
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Current Database: `healer`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `healer` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `healer`;

--
-- Table structure for table `disease`
--

DROP TABLE IF EXISTS `disease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disease` (
  `disease_id` int(11) NOT NULL,
  `disease` text NOT NULL,
  PRIMARY KEY (`disease_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disease`
--

LOCK TABLES `disease` WRITE;
/*!40000 ALTER TABLE `disease` DISABLE KEYS */;
INSERT INTO `disease` VALUES (1,'dehydration'),(2,'chicken pox'),(3,'measles'),(4,'whooping cough'),(5,'food poisoning');
/*!40000 ALTER TABLE `disease` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disease_specialist_map`
--

DROP TABLE IF EXISTS `disease_specialist_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disease_specialist_map` (
  `iddisease_specialist_map` int(11) NOT NULL,
  `disease_id` int(11) DEFAULT NULL,
  `specialist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddisease_specialist_map`),
  KEY `disease_id_idx` (`disease_id`),
  KEY `specialist_id_idx` (`specialist_id`),
  CONSTRAINT `disease_id` FOREIGN KEY (`disease_id`) REFERENCES `disease` (`disease_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `specialist_id` FOREIGN KEY (`specialist_id`) REFERENCES `specialist` (`specialist_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disease_specialist_map`
--

LOCK TABLES `disease_specialist_map` WRITE;
/*!40000 ALTER TABLE `disease_specialist_map` DISABLE KEYS */;
INSERT INTO `disease_specialist_map` VALUES (1,1,1),(2,2,2),(3,3,2),(4,4,3),(5,5,1);
/*!40000 ALTER TABLE `disease_specialist_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disease_to_test_map`
--

DROP TABLE IF EXISTS `disease_to_test_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disease_to_test_map` (
  `map_id` int(11) NOT NULL,
  `disease_id` int(11) DEFAULT NULL,
  `test_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`map_id`),
  KEY `test_id_idx` (`test_id`),
  KEY `disease_id_idx` (`disease_id`),
  CONSTRAINT `test_id` FOREIGN KEY (`test_id`) REFERENCES `tests` (`test_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disease_to_test_map`
--

LOCK TABLES `disease_to_test_map` WRITE;
/*!40000 ALTER TABLE `disease_to_test_map` DISABLE KEYS */;
INSERT INTO `disease_to_test_map` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,1),(5,2,2),(6,2,3),(7,3,1),(8,3,2),(9,4,6),(10,5,5),(11,5,1),(12,5,2);
/*!40000 ALTER TABLE `disease_to_test_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disease_treatment_map`
--

DROP TABLE IF EXISTS `disease_treatment_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disease_treatment_map` (
  `map_id` int(11) NOT NULL AUTO_INCREMENT,
  `disease_id` int(11) DEFAULT NULL,
  `treatment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`map_id`),
  KEY `treatment_id` (`treatment_id`),
  KEY `disease_id` (`disease_id`),
  CONSTRAINT `disease_treatment_map_ibfk_1` FOREIGN KEY (`treatment_id`) REFERENCES `treatment` (`treatment_id`),
  CONSTRAINT `disease_treatment_map_ibfk_2` FOREIGN KEY (`disease_id`) REFERENCES `disease` (`disease_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disease_treatment_map`
--

LOCK TABLES `disease_treatment_map` WRITE;
/*!40000 ALTER TABLE `disease_treatment_map` DISABLE KEYS */;
INSERT INTO `disease_treatment_map` VALUES (1,1,1),(2,1,2),(3,2,3),(4,2,4),(5,3,5),(6,3,6),(7,3,7),(8,3,8),(9,3,9),(10,4,10),(11,4,11),(12,4,8),(13,5,12),(14,5,13),(15,5,8),(16,5,2);
/*!40000 ALTER TABLE `disease_treatment_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialist`
--

DROP TABLE IF EXISTS `specialist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialist` (
  `specialist_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`specialist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialist`
--

LOCK TABLES `specialist` WRITE;
/*!40000 ALTER TABLE `specialist` DISABLE KEYS */;
INSERT INTO `specialist` VALUES (1,'physician'),(2,'dermatologist'),(3,'Pulmonologist');
/*!40000 ALTER TABLE `specialist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom`
--

DROP TABLE IF EXISTS `symptom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptom` (
  `symp_id` int(11) NOT NULL,
  `symptom` text NOT NULL,
  PRIMARY KEY (`symp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom`
--

LOCK TABLES `symptom` WRITE;
/*!40000 ALTER TABLE `symptom` DISABLE KEYS */;
INSERT INTO `symptom` VALUES (1,'Extreme Thirst'),(2,'Less frequent urination'),(3,'dark colored urine'),(4,'fatigue'),(5,'dizziness'),(6,'confusion'),(7,'fever'),(8,'cough'),(9,'runny nose'),(10,'vomiting'),(11,'breathlessness'),(12,'nausea'),(13,'diarrhea'),(14,'runny eyes'),(15,'spots with blister'),(16,'cramps'),(17,'abdominal pain');
/*!40000 ALTER TABLE `symptom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom_disease_map`
--

DROP TABLE IF EXISTS `symptom_disease_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptom_disease_map` (
  `map_id` int(11) NOT NULL AUTO_INCREMENT,
  `symptom_id` int(11) DEFAULT NULL,
  `disease_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`map_id`),
  KEY `symptom_id` (`symptom_id`),
  KEY `disease_id` (`disease_id`),
  CONSTRAINT `symptom_disease_map_ibfk_1` FOREIGN KEY (`symptom_id`) REFERENCES `symptom` (`symp_id`),
  CONSTRAINT `symptom_disease_map_ibfk_2` FOREIGN KEY (`disease_id`) REFERENCES `disease` (`disease_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom_disease_map`
--

LOCK TABLES `symptom_disease_map` WRITE;
/*!40000 ALTER TABLE `symptom_disease_map` DISABLE KEYS */;
INSERT INTO `symptom_disease_map` VALUES (1,9,3),(2,14,3),(3,1,1),(4,2,1),(5,3,1),(6,4,1),(7,5,1),(8,6,1),(9,7,2),(10,7,3),(11,7,5),(12,8,3),(13,8,4),(15,9,4),(16,10,4),(17,10,3),(18,11,4),(19,12,5),(20,13,5),(22,15,2),(23,16,5),(24,17,5);
/*!40000 ALTER TABLE `symptom_disease_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tests`
--

DROP TABLE IF EXISTS `tests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tests` (
  `test_id` int(11) NOT NULL,
  `test` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
INSERT INTO `tests` VALUES (1,'blood test'),(2,'urine test'),(3,'blood pressure'),(4,'vitamin tests'),(5,'stool culture'),(6,'throat culture');
/*!40000 ALTER TABLE `tests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment` (
  `treatment_id` int(11) NOT NULL,
  `treatment` text NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`treatment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES (1,'Ors','Replenish fluids and electrolytes using oral rehydration slution(ors).'),(2,'Drink sports drink','Drink more water ,sports drink or other liquids.'),(3,'No treatment','In healthy children, chickenpox typically requires no medical treatment. Just use an antihistamine to relieve itching. But for the most part, the disease is allowed to run its course.'),(4,'Antiviral drug','Take antiviral drug such as acyclovir (Zovirax) or another drug called immune globulin intravenous (Privigen).'),(5,'Post-exposure vaccination','Nonimmunized people, including infants, take the measles vaccination within 72 hours of exposure to the measles virus to provide protection against the disease.'),(6,'Immune serum globulin','Pregnant women, infants and people with weakened immune systems who are exposed to the virus may receive an injection of proteins (antibodies) called immune serum globulin. When given within six days of exposure to the virus, these antibodies can prevent measles or make symptoms less severe.'),(7,'Fever reducers','Take medications such as acetaminophen (Tylenol, others), ibuprofen (Advil, Motrin, others) or naproxen (Aleve) to help relieve the fever that accompanies measles.'),(8,'Antibiotics','If a bacterial infection, such as pneumonia or an ear infection, develops while you or your child has measles, use an antibiotic.'),(9,'Vitamin A','People with low levels of vitamin A are more likely to have a more severe case of measles. Taking vitamin A may lessen the severity of the measles. It\'s generally given as a large dose of 200,000 international units (IU) for two days.'),(10,'Humidity','It helps break up the thick mucus. Use a humidifier, or get some steam going in the bath or shower. You could heat up a pan of water and close up the room or cover yourself and the pan under a sheet.'),(11,'Small meals','Take small meals, You’re less likely to vomit them up.'),(12,'Let your stomach settle','Stop eating and drinking for a few hours.'),(13,'Rest','The illness and dehydration can weaken and tire you so take some quality rest.');
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-21 13:12:24
