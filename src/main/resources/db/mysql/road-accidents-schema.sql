-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: roadaccidents
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1


--
-- Table structure for table `road_accident`
--

DROP TABLE IF EXISTS `road_accident`;

CREATE TABLE `road_accident` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `accident_index` varchar(255) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `number_of_calsualties` int(11) DEFAULT NULL,
  `number_of_vehicles` int(11) DEFAULT NULL,
  `occur_at` datetime DEFAULT NULL,
  `occur_on` datetime DEFAULT NULL,
  `accident_severity` bigint(20) DEFAULT NULL,
  `district_authority` bigint(20) DEFAULT NULL,
  `light_condition` bigint(20) DEFAULT NULL,
  `police_force` bigint(20) DEFAULT NULL,
  `road_surface` bigint(20) DEFAULT NULL,
  `weather_condition` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`oid`)
--  ,KEY `FK8grhhwufd9fxbw0vgryg3aif5` (`accident_severity`),
--  KEY `FKdwdjtnn0b1uqyrnmip871jo05` (`district_authority`),
--  KEY `FK73qrqkd8vwb8ufyxroto65b21` (`light_condition`),
--  KEY `FKb1l1u6x1lr1b2bmifoxuaoyd5` (`police_force`),
--  KEY `FKkmmh9kx8h111yau9aorfy6m1m` (`road_surface`),
--  KEY `FK3fqniol6s186jrkpkfhp2dpg4` (`weather_condition`),
--  CONSTRAINT `FK3fqniol6s186jrkpkfhp2dpg4` FOREIGN KEY (`weather_condition`) REFERENCES `weather_condition` (`oid`),
--  CONSTRAINT `FK73qrqkd8vwb8ufyxroto65b21` FOREIGN KEY (`light_condition`) REFERENCES `light_condition` (`oid`),
--  CONSTRAINT `FK8grhhwufd9fxbw0vgryg3aif5` FOREIGN KEY (`accident_severity`) REFERENCES `accident_severity` (`oid`),
--  CONSTRAINT `FKb1l1u6x1lr1b2bmifoxuaoyd5` FOREIGN KEY (`police_force`) REFERENCES `police_force` (`oid`),
--  CONSTRAINT `FKdwdjtnn0b1uqyrnmip871jo05` FOREIGN KEY (`district_authority`) REFERENCES `district_authority` (`oid`),
--  CONSTRAINT `FKkmmh9kx8h111yau9aorfy6m1m` FOREIGN KEY (`road_surface`) REFERENCES `road_surface` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=163555 DEFAULT CHARSET=utf8;


--
-- Table structure for table `accident_severity`
--

DROP TABLE IF EXISTS `accident_severity`;
CREATE TABLE `accident_severity` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

--
-- Table structure for table `district_authority`
--

DROP TABLE IF EXISTS `district_authority`;
CREATE TABLE `district_authority` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=7073 DEFAULT CHARSET=utf8;

--
-- Table structure for table `light_condition`
--

DROP TABLE IF EXISTS `light_condition`;
CREATE TABLE `light_condition` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

--
-- Table structure for table `police_force`
--

DROP TABLE IF EXISTS `police_force`;
CREATE TABLE `police_force` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=868 DEFAULT CHARSET=utf8;



--
-- Table structure for table `road_surface`
--

DROP TABLE IF EXISTS `road_surface`;
CREATE TABLE `road_surface` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

--
-- Table structure for table `weather_condition`
--

DROP TABLE IF EXISTS `weather_condition`;
CREATE TABLE `weather_condition` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;

-- Dump completed on 2016-05-18 15:12:08
