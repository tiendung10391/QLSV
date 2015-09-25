-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 24, 2015 at 09:55 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gcm`
--
CREATE DATABASE IF NOT EXISTS `gcm` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gcm`;

-- --------------------------------------------------------

--
-- Table structure for table `gcm_users`
--

CREATE TABLE IF NOT EXISTS `gcm_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gcm_regid` text,
  `name` varchar(50) NOT NULL,
  `masv` varchar(255) NOT NULL,
  `malop` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `gcm_users`
--

INSERT INTO `gcm_users` (`id`, `gcm_regid`, `name`, `masv`, `malop`, `created_at`) VALUES
(3, 'APA91bEhz0TB3iRou2FsH0YNlS0MLCOXWvdSJsBzM9tvtRsxp-LIspVrQ06f89Ze6lfqy8ilwFSps8vKuXzJ0M3UsNnqJiGQLdjRhMpjp_-UyngeyP1GAbELJpAQ8sz_8FROAFNAhXXRbcZ2RQS-xpXqVPo1eEzgGQ', 'nguyen van tu', 'sv001', 'JAVA1301', '2015-09-23 09:32:42'),
(5, 'APA91bG2VaqPUYJvyDOyNft79hW2Zbr3DSOnEw3SenVHpdlSNdJfqgL4R7F9D5JNuWaBvpCieF8IGy0HVIcrEMYiI8VFFbBJVi7wdeC3IjucHmfWzBMm66-TcwkHTImoAEKKiXH3RjEe', 'tien dung', 'sv003', 'JAVA1102', '2015-09-23 15:05:37'),
(11, 'APA91bG2VaqPUYJvyDOyNft79hW2Zbr3DSOnEw3SenVHpdlSNdJfqgL4R7F9D5JNuWaBvpCieF8IGy0HVIcrEMYiI8VFFbBJVi7wdeC3IjucHmfWzBMm66-TcwkHTImoAEKKiXH3RjEe', 'dung ga', 'sv001', 'ANDROID', '2015-09-23 15:54:32'),
(15, 'asfsjhfjksahf dgdgsajdga ', 'nguyen van van', 'sv006', 'JAVA1234', '2015-09-24 02:09:15'),
(16, 'APA91bG2VaqPUYJvyDOyNft79hW2Zbr3DSOnEw3SenVHpdlSNdJfqgL4R7F9D5JNuWaBvpCieF8IGy0HVIcrEMYiI8VFFbBJVi7wdeC3IjucHmfWzBMm66-TcwkHTImoAEKKiXH3RjEe', 'tien dung 91', 'sv001', 'JAVA1301', '2015-09-24 03:04:27');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
