-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 25. Feb 2014 um 05:01
-- Server Version: 5.6.16
-- PHP-Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `prgjava`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int(11) NOT NULL COMMENT 'Item ID',
  `item_name` varchar(50) NOT NULL COMMENT 'Name des Items',
  `item_weight` int(11) NOT NULL COMMENT 'gewicht eines einzelnen Items (gramm)',
  `has_expiration_date` tinyint(1) NOT NULL COMMENT 'angabe ob Item ein Verfallsdatum besitzt',
  `item_number` int(11) DEFAULT NULL COMMENT 'anzahl eingelagerten Items',
  PRIMARY KEY (`item_id`),
  KEY `item_name` (`item_name`),
  KEY `has_expiration_date` (`has_expiration_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `shelf`
--

CREATE TABLE IF NOT EXISTS `shelf` (
  `shelf_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Regal ID',
  `shelf_name` text COMMENT 'Regalbezeichnung / name',
  `shelf_nfree_yard` int(11) NOT NULL COMMENT 'anzahl freier Plätze',
  `shelf_max_load` int(11) NOT NULL COMMENT 'max. Traglast (gramm)',
  `shelf_free_load` int(11) NOT NULL COMMENT 'noch freie Traglast (gramm)',
  PRIMARY KEY (`shelf_id`),
  KEY `shelf_nfree_yard` (`shelf_nfree_yard`,`shelf_free_load`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` text NOT NULL,
  `user_login_name` varchar(60) NOT NULL,
  `user_login_psw` text NOT NULL,
  `user_rights` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_login_name` (`user_login_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `yard`
--

CREATE TABLE IF NOT EXISTS `yard` (
  `yard_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Platz ID',
  `yard_item_id` int(11) DEFAULT NULL COMMENT 'eingelagertes Item ( ID )',
  `yard_item_number` int(11) DEFAULT NULL COMMENT 'anzahl auf diesen Platz eingelagerter Items',
  `yard_shelf_id` int(11) NOT NULL COMMENT 'Regal ( ID )',
  `yard_occupied` tinyint(1) NOT NULL COMMENT 'Lagerplatz belegt ?',
  `yard_position_vertical` varchar(10) NOT NULL COMMENT 'Position senkrecht mit Buchstaben gekennzeichnet ',
  `yard_position_horizontally` int(11) NOT NULL COMMENT 'Position waagerecht',
  PRIMARY KEY (`yard_id`),
  KEY `yard_shelf_id` (`yard_shelf_id`),
  KEY `yard_item_id` (`yard_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `yard`
--
ALTER TABLE `yard`
  ADD CONSTRAINT `yard_ibfk_1` FOREIGN KEY (`yard_shelf_id`) REFERENCES `shelf` (`shelf_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `yard_ibfk_2` FOREIGN KEY (`yard_item_id`) REFERENCES `item` (`item_id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
