-- --------------------------------------------------------
-- Värd:                         85.24.242.59
-- Serverversion:                5.7.18-log - MySQL Community Server (GPL)
-- Server-OS:                    Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumpar databasstruktur för java4
CREATE DATABASE IF NOT EXISTS `java4` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `java4`;

-- Dumpar struktur för tabell java4.auctions
CREATE TABLE IF NOT EXISTS `auctions` (
  `auctionid` int(11) NOT NULL AUTO_INCREMENT,
  `auctionowner` int(11) NOT NULL,
  `title` char(30) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `category` int(11) NOT NULL DEFAULT '0',
  `startprice` double NOT NULL DEFAULT '0',
  `starttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  KEY `auctionId` (`auctionid`),
  KEY `auctionOwner` (`auctionowner`),
  KEY `category` (`category`),
  CONSTRAINT `auctionOwner` FOREIGN KEY (`auctionowner`) REFERENCES `users` (`userid`),
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `categories` (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=1578 DEFAULT CHARSET=latin1;

-- Dumpar data för tabell java4.auctions: ~21 rows (ungefär)
/*!40000 ALTER TABLE `auctions` DISABLE KEYS */;
INSERT INTO `auctions` (`auctionid`, `auctionowner`, `title`, `description`, `category`, `startprice`, `starttime`, `endtime`) VALUES
	(1568, 17, 'Camel for sale', 'I have two camels. One of them is up to no good as you can see in this picture. I need to get him to a new home quick. ', 15, 12, '2019-05-02 18:26:59', '2019-05-04 18:26:59'),
	(1569, 17, 'Seasonal clothes', 'Selling my banana shorts and pepsi socks. Need money and already have a pair of Pizza hut pants.', 12, 9, '2019-05-02 18:33:51', '2019-05-04 18:33:51'),
	(1570, 17, 'Romantic swan boat', 'This thing gets the ladies hot. Some minor repairs might be needed. Interested in buying old fruit? Hit my up in the chat! ', 13, 120, '2019-05-02 18:35:56', '2019-05-04 18:35:56'),
	(1571, 7, '17th century chair', 'Selling this very old chair. Probably belonged to a king at some point. ', 16, 900, '2019-05-02 18:37:18', '2019-05-04 18:37:18'),
	(1572, 7, 'Broken nutcracker', 'Some glue and it\'s as good as new. Looking for quick money.', 16, 1, '2019-05-02 18:38:08', '2019-05-04 18:38:08'),
	(1573, 7, 'Best headphones on the market', 'Signed by Steve Ballmer (not visible on photo). Got the certificate from microsoft and everything. Drivers for installing on Mac not included.', 17, 75, '2019-05-02 18:42:04', '2019-05-04 18:42:04'),
	(1574, 23, 'Thermos and bottles', 'The thermos keeps stuff hot and the bottles can be traded in to the Swedish pant-system. Please note that the bottles are empty now, no liquids are included in this auction. ', 16, 20, '2019-05-02 18:46:03', '2019-05-04 18:46:03'),
	(1575, 23, 'Fortnite board game', 'My kids didn\'t clean their room so here I am selling their stuff. Parenting is hard. Will settle for almost nothing. ', 18, 1, '2019-05-02 18:47:44', '2019-05-04 18:47:44'),
	(1576, 23, 'Neighbor for sale', 'He eats most things, don\'t feed him pasta (terrible allergies). Selling because of moving out of the country. ', 15, 90, '2019-05-02 18:49:48', '2019-05-04 18:49:48'),
	(1577, 23, 'Coffee cup', 'Not the ones in the picture. The ones I\'m selling have some stains and need washing. Selling because I\'m a teapot. ', 14, 11, '2019-05-02 18:52:35', '2019-05-04 18:52:35');
/*!40000 ALTER TABLE `auctions` ENABLE KEYS */;

-- Dumpar struktur för tabell java4.bids
CREATE TABLE IF NOT EXISTS `bids` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `auctionid` int(11) NOT NULL,
  `bidamount` double NOT NULL,
  `bidtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `auctionId` (`auctionid`),
  KEY `userId` (`userid`),
  CONSTRAINT `auctionId` FOREIGN KEY (`auctionid`) REFERENCES `auctions` (`auctionid`),
  CONSTRAINT `userId` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=latin1;

-- Dumpar data för tabell java4.bids: ~26 rows (ungefär)
/*!40000 ALTER TABLE `bids` DISABLE KEYS */;
/*!40000 ALTER TABLE `bids` ENABLE KEYS */;

-- Dumpar struktur för tabell java4.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Dumpar data för tabell java4.categories: ~5 rows (ungefär)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`categoryid`, `categoryname`) VALUES
	(12, 'Fashion'),
	(13, 'Vehicles'),
	(14, 'Health'),
	(15, 'Pets'),
	(16, 'Antiques'),
	(17, 'Electronics'),
	(18, 'Hobbies');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumpar struktur för tabell java4.images
CREATE TABLE IF NOT EXISTS `images` (
  `auctionid` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `img_order` int(11) NOT NULL,
  KEY `auctionId` (`auctionid`),
  CONSTRAINT `IdAuction` FOREIGN KEY (`auctionid`) REFERENCES `auctions` (`auctionid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumpar data för tabell java4.images: ~40 rows (ungefär)
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` (`auctionid`, `path`, `img_order`) VALUES
	(1568, '33531e22-4403-48af-a701-7a18e4a197b9.jpg', 1),
	(1568, '63011e80-2374-481f-8427-7cd0a2d48cfa.jpg', 2),
	(1568, 'b61b1d27-b4cc-4e93-b889-b300bb01799a.jpg', 3),
	(1569, '3b10f273-08b8-4cde-aa55-6fc4eee12f83.jpg', 1),
	(1569, 'be1a768b-7633-4d1e-8370-e93dd650b9ba.jpg', 2),
	(1570, '2fc6f34f-ed63-45c7-a758-a6454aed75cb.jpg', 1),
	(1570, '67f10948-210d-4164-a260-b17c033e1684.jpg', 2),
	(1571, '18fde324-b314-4391-a7a7-94d46a96b96f.jpg', 1),
	(1572, '0aa9ab0f-71cc-48cf-9dde-6b29d1e56b98.jpg', 1),
	(1573, 'a1b141fc-a41e-4f79-bedf-9cc13103725d.jpg', 1),
	(1573, '699f344e-c69b-4c51-93f2-419cac72554a.jpg', 2),
	(1573, '4ed7043c-aa7c-4193-989f-72e66d7e67ab.jpg', 3),
	(1574, 'f48927b2-34a4-406c-afe5-13089db61ab5.jpg', 1),
	(1574, 'e3c2773a-f8b6-4881-969e-314e739468d7.jpg', 2),
	(1574, '1fc3531a-f8d1-4bb6-9f0c-c62a28e6ce99.jpg', 3),
	(1574, 'c587c170-54f2-4051-b2f9-e3e4f35358ab.jpg', 4),
	(1574, '1ee3bdec-9ee1-42db-9a24-57f3fb673d18.jpg', 5),
	(1575, 'b060cc26-d080-4a58-b972-85441c24c916.jpg', 1),
	(1576, 'c21643af-1624-4334-80e2-a96d375c6eba.jpg', 1),
	(1577, '188e67c6-0fc7-4e8d-bc7a-9ee84a92d755.jpg', 1);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;

-- Dumpar struktur för tabell java4.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `senderid` int(11) NOT NULL,
  `receiverid` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `timesent` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hasread` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `senderId` (`senderid`),
  KEY `receiverId` (`receiverid`),
  CONSTRAINT `receiverId` FOREIGN KEY (`receiverid`) REFERENCES `users` (`userid`),
  CONSTRAINT `senderId` FOREIGN KEY (`senderid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=latin1;

-- Dumpar data för tabell java4.messages: ~183 rows (ungefär)
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;

-- Dumpar struktur för tabell java4.notifications
CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `auctionid` int(11) NOT NULL,
  `bidid` int(11) NOT NULL,
  `hasread` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_notifications_users` (`userid`),
  KEY `FK_notifications_auctions` (`auctionid`),
  KEY `FK_notifications_bids` (`bidid`),
  CONSTRAINT `FK_notifications_auctions` FOREIGN KEY (`auctionid`) REFERENCES `auctions` (`auctionid`),
  CONSTRAINT `FK_notifications_bids` FOREIGN KEY (`bidid`) REFERENCES `bids` (`id`),
  CONSTRAINT `FK_notifications_users` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- Dumpar data för tabell java4.notifications: ~0 rows (ungefär)
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;

-- Dumpar struktur för tabell java4.users
CREATE TABLE IF NOT EXISTS `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT 'password',
  `avatar_class` varchar(255) NOT NULL DEFAULT 'fas fa-smile',
  `avatar_color` varchar(255) DEFAULT '#000000',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- Dumpar data för tabell java4.users: ~16 rows (ungefär)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userid`, `email`, `firstname`, `lastname`, `password`, `avatar_class`, `avatar_color`) VALUES
	(3, 'something@something.com', 'Kenneth', 'Boman', '$2a$10$3jNCmJqDYifePL9NXxWfp.GP5YFeslD3vQgf.ue9OHR4GHBcV2/Ya', 'fas fa-smile', 'black'),
	(4, 'arne@gmail.com', 'Arne', 'Larsson', '$2a$10$fLH0U0UutBriSZtaXxlmPeTymPQPfwtw9Dhr2e98Nue6SdmLLBAfi', 'fas fa-smile', 'black'),
	(5, 'kalle@kalle.se', 'kalle', 'kalle', '$2a$10$CFxLSWhYr0QXhd6nQVxOsOtw3.iNW5Md0H2Z2C6L7cbjKdzU.Cifu', 'fas fa-smile', 'green'),
	(6, 'bengt@bengt.se', 'Bengt', 'Bengtsson', '$2a$10$oj8rn0rvBhpFNq.r/DvXpOb1Lp2G/lB9V048tEHSH3LyOSCx6g.cW', 'fas fa-smile', 'green'),
	(7, 'sven@sven.sven', 'Sven', 'Svensson', '$2a$10$6FNpZBqS7hPyxvstfTsnpO4lWWt.yvOPHRXWBOl7eejY/giOR1/PG', 'fas fa-smile', 'black'),
	(8, 'ralle@ralle.se', 'Ralle', 'Rallesson', '$2a$10$KUkuiVi73sNxEm2ZmTZfweKzMHUWJEuvsr3O5gGur4QcoW2wZUl/e', 'fas fa-smile', 'black'),
	(10, 'sven@sven.svenne', 'Sven', 'Svensson', '$2a$10$HCM.33iKaGosFs8sU5AhPe/Xf3qBMHBH7mTKhwMKJ3smLoaBIr.zG', 'fas fa-smile', 'black'),
	(11, 'svne@svne.com', 'Sveen', 'Sveeeenssson', '$2a$10$RM8Q0XRBzvZlYqqGeLZJxuvcJgnK9/QQk9fKuQO0mruWG.i1/40FS', 'fas fa-smile', 'black'),
	(12, 'kent@lenny.peojf', 'kent', 'lenny', '$2a$10$kWrhlQ5ZM/0F77PqHgi7jOS5AZqcoUWH7bDd.Wuikf2SSF5a51hgW', 'fas fa-smile', 'black'),
	(15, 'hej@hej.hej', 'hej', 'hej', '$2a$10$Ctfoa7.KS9cqCRYCw7zsE.N1h1s3bdZYGRzroJo0ehrBmsWHY9Mk.', 'fas fa-smile', 'yellow'),
	(16, 'keke@keke.keke', 'keke', 'keke', '$2a$10$WT/aHIKCP/qrgeu.EniZGeeUx38Qcph4IKwyKXN/4jScaJUwZ/1BW', 'fas fa-smile', 'green'),
	(17, 'kent@kent.kent', 'kent', 'kent', '$2a$10$GbVU13CpADMvILb/k.nOueCNuofsIj06mX/CcGZMo/AuSfQeVTp8q', 'fas fa-user-secret', 'blue'),
	(18, 'jens@jens.se', 'Jens', 'Jens', '$2a$10$jHIhUp.uVYtq1VKhh.KqvOJ0vLoGmvs1rM4YJYszt7qYnWUy2vqC.', 'fas fa-dragon', 'black'),
	(19, 'berit@gmail.com', 'Berit', 'Larsson', '$2a$10$TX.7qIXt9FGHfSJ/LgvTbuo/DU8L76LTXEl/X10PnwuuzwL2AoiTG', 'fas fa-cat', 'black'),
	(20, 'kalle@kalle.kalle', 'Kalle', 'Kallesson', '$2a$10$cRVPT5hMAOPZ2BL5uwuiAOhp35mHqW4IQtVA2v9jVxgjtvfA214uG', 'fas fa-smile', 'brown'),
	(21, 'henning@henning.henning', 'Henning', 'Henningsson', '$2a$10$LhgHrJdVGzd54AQllHf/SOmOqZtlm0llFSCMOUgKgDvNj3cGa41n.', 'fas fa-smile', 'blue'),
	(22, 'kerstin@kerstinsson.se', 'Kerstin', 'Kerstinsson', '$2a$10$JZmS5N7WD963XgiBiXUo0.B.SSBDLr3Cx5Yx7qpl9cH8S7nCMdN/.', 'fas fa-smile', 'black'),
	(23, 'jens@jens.jens', 'Jens', 'Jensson', '$2a$10$Ki2yfLYm9v6XKwg/LGDzYuEAwOwDjc8ZhvJHqiFVph4yz1ah1qUXC', 'fas fa-smile', '#000000');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
