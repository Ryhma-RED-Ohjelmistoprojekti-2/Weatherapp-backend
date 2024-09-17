-- MariaDB dump 10.19  Distrib 10.4.27-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: weatherDB
-- ------------------------------------------------------
-- Server version	10.4.27-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `weatherdb_data`
--

DROP TABLE IF EXISTS `weatherdb_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weatherdb_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature` float DEFAULT NULL,
  `humidity` int(11) DEFAULT NULL,
  `barometric_pressure` float DEFAULT NULL,
  `wind_direction` int(11) DEFAULT NULL,
  `avg_wind_speed` float DEFAULT NULL,
  `max_wind_speed` float DEFAULT NULL,
  `rainfall_one_hour` float DEFAULT NULL,
  `rainfall_twenty_four_hour` float DEFAULT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- INSERT INTO `weatherdb_data` 
-- (`temperature`, `humidity`, `barometric_pressure`, `wind_direction`, `avg_wind_speed`, `max_wind_speed`, `rainfall_one_hour`, `rainfall_twenty_four_hour`, `time_stamp`) 
-- VALUES 
-- (15.5, 80, 1013.2, 270, 5.4, 10.2, 0.0, 12.3, '2024-09-12 10:00:00'),
-- (18.7, 75, 1010.5, 90, 6.1, 11.4, 0.1, 10.0, '2024-09-12 11:00:00'),
-- (20.3, 70, 1012.0, 180, 7.2, 12.5, 0.0, 9.5, '2024-09-12 12:00:00'),
-- (22.1, 65, 1011.8, 360, 8.0, 13.0, 0.2, 8.8, '2024-09-12 13:00:00');


--
-- Dumping data for table `weatherdb_data`
--

LOCK TABLES `weatherdb_data` WRITE;
/*!40000 ALTER TABLE `weatherdb_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `weatherdb_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-03 14:14:53
