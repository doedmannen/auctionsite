-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.37 - MySQL Community Server (GPL)
-- Server OS:                    Win32
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
  `auctionid` int(11) NOT NULL AUTO_INCREMENT,
  `auctionowner` int(11) NOT NULL,
  `title` char(30) NOT NULL,
  `description` varchar(255) NOT NULL,
  `category` int(11) NOT NULL DEFAULT '0',
  `startprice` double NOT NULL DEFAULT '0',
  `starttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `auctionId` (`auctionid`),
  KEY `auctionOwner` (`auctionowner`),
  KEY `category` (`category`),
  CONSTRAINT `auctionOwner` FOREIGN KEY (`auctionowner`) REFERENCES `users` (`userid`),
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `categories` (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=1547 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table auctionsite.bids
CREATE TABLE IF NOT EXISTS `bids` (
  `userid` int(11) NOT NULL,
  `auctionid` int(11) NOT NULL,
  `bidamount` double NOT NULL,
  `bidtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `auctionId` (`auctionid`),
  KEY `userId` (`userid`),
  CONSTRAINT `auctionId` FOREIGN KEY (`auctionid`) REFERENCES `auctions` (`auctionid`),
  CONSTRAINT `userId` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table auctionsite.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table auctionsite.images
CREATE TABLE IF NOT EXISTS `images` (
  `auctionid` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  KEY `auctionId` (`auctionid`),
  CONSTRAINT `IdAuction` FOREIGN KEY (`auctionid`) REFERENCES `auctions` (`auctionid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table auctionsite.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `senderid` int(11) NOT NULL,
  `receiverid` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `timesent` datetime NOT NULL,
  KEY `senderId` (`senderid`),
  KEY `receiverId` (`receiverid`),
  CONSTRAINT `receiverId` FOREIGN KEY (`receiverid`) REFERENCES `users` (`userid`),
  CONSTRAINT `senderId` FOREIGN KEY (`senderid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table auctionsite.sessions
CREATE TABLE IF NOT EXISTS `sessions` (
  `sessionkey` varchar(255) NOT NULL,
  UNIQUE KEY `sessionKey` (`sessionkey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table auctionsite.users
CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT 'password',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
