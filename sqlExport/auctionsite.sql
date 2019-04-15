-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.25-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for auctionsite
CREATE DATABASE IF NOT EXISTS `auctionsite` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `auctionsite`;

-- Dumping structure for table auctionsite.auctions
CREATE TABLE IF NOT EXISTS `auctions` (
  `auctionId` int(11) NOT NULL AUTO_INCREMENT,
  `auctionOwner` int(11) NOT NULL,
  `title` char(30) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` int(11) NOT NULL DEFAULT '0',
  `startPrice` double NOT NULL DEFAULT '0',
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  KEY `auctionId` (`auctionId`),
  KEY `auctionOwner` (`auctionOwner`),
  KEY `category` (`category`),
  CONSTRAINT `auctionOwner` FOREIGN KEY (`auctionOwner`) REFERENCES `users` (`userId`),
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `categories` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=1547 DEFAULT CHARSET=latin1;

-- Dumping data for table auctionsite.auctions: ~2 rows (approximately)
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` (`auctionId`, `auctionOwner`, `title`, `description`, `category`, `startPrice`, `startTime`, `endTime`) VALUES
	(1534, 2, '3 Amazing Puppies', 'Selling one lot of three amazing puppies! They are cute, fluffy, and probably delicious. Guaranteed to have very soft ears.', 10, 500, '2019-04-15 13:43:42', '2019-04-16 13:43:46'),
	(1546, 1, 'Lightly used sex toys', 'Including all your favorites in new to lightly used condition!', 11, 100, '2019-04-15 13:44:37', '2019-04-17 13:44:40');
/*!40000 ALTER TABLE `auctions` ENABLE KEYS */;

-- Dumping structure for table auctionsite.bids
CREATE TABLE IF NOT EXISTS `bids` (
  `userId` int(11) NOT NULL,
  `auctionId` int(11) NOT NULL,
  `bidAmount` double NOT NULL,
  `bidTime` datetime NOT NULL,
  KEY `auctionId` (`auctionId`),
  KEY `userId` (`userId`),
  CONSTRAINT `auctionId` FOREIGN KEY (`auctionId`) REFERENCES `auctions` (`auctionId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table auctionsite.bids: ~0 rows (approximately)
/*!40000 ALTER TABLE `bids` DISABLE KEYS */;
/*!40000 ALTER TABLE `bids` ENABLE KEYS */;

-- Dumping structure for table auctionsite.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table auctionsite.categories: ~12 rows (approximately)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`categoryId`, `categoryName`) VALUES
	(0, 'Uncategorized'),
	(1, 'Vehicles'),
	(2, 'Fashion'),
	(3, 'Health and Beauty'),
	(4, 'Electronics'),
	(5, 'Collectibles'),
	(6, 'Home and Garden'),
	(7, 'Sporting Goods'),
	(8, 'Hobbies'),
	(9, 'Music'),
	(10, 'Pets'),
	(11, 'Miscellaneous');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumping structure for table auctionsite.images
CREATE TABLE IF NOT EXISTS `images` (
  `auctionId` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  KEY `auctionId` (`auctionId`),
  CONSTRAINT `IdAuction` FOREIGN KEY (`auctionId`) REFERENCES `auctions` (`auctionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table auctionsite.images: ~0 rows (approximately)
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;

-- Dumping structure for table auctionsite.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `senderId` int(11) NOT NULL,
  `receiverId` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `timeSent` datetime NOT NULL,
  KEY `senderId` (`senderId`),
  KEY `receiverId` (`receiverId`),
  CONSTRAINT `receiverId` FOREIGN KEY (`receiverId`) REFERENCES `users` (`userId`),
  CONSTRAINT `senderId` FOREIGN KEY (`senderId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table auctionsite.messages: ~0 rows (approximately)
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;

-- Dumping structure for table auctionsite.sessions
CREATE TABLE IF NOT EXISTS `sessions` (
  `sessionKey` varchar(255) NOT NULL,
  UNIQUE KEY `sessionKey` (`sessionKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table auctionsite.sessions: ~0 rows (approximately)
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;

-- Dumping structure for table auctionsite.users
CREATE TABLE IF NOT EXISTS `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT 'password',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table auctionsite.users: ~2 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userId`, `email`, `firstName`, `lastName`, `password`) VALUES
	(1, 'anemail@email.com', 'Lynette', 'Lemire', 'password'),
	(2, 'anotheremail@email.com', 'Marvin', 'McDougal', 'password');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
