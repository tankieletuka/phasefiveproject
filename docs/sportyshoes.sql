-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2023 at 01:24 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sportyshoes`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `c_uid` int(10) NOT NULL,
  `c_description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`c_uid`, `c_description`) VALUES
(1, 'Air Force 1'),
(3, 'Air Force 2'),
(4, 'Air Force 3'),
(5, 'Air Force 4');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `p_uid` int(10) NOT NULL,
  `p_description` varchar(50) NOT NULL,
  `p_c_uid` int(10) DEFAULT NULL COMMENT 'category id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`p_uid`, `p_description`, `p_c_uid`) VALUES
(3, 'Seeta', 3),
(4, 'Lieta', 4),
(7, 'Jordan', 1);

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `pur_uid` int(10) NOT NULL,
  `pur_p_uid` int(10) NOT NULL COMMENT 'Product ID',
  `pur_u_email` varchar(50) NOT NULL COMMENT 'User email',
  `pur_date` date NOT NULL DEFAULT current_timestamp(),
  `pur_count` int(10) NOT NULL COMMENT 'Number of products purchased.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`pur_uid`, `pur_p_uid`, `pur_u_email`, `pur_date`, `pur_count`) VALUES
(3, 3, 'tankieletuka@gmail.com', '2023-04-22', 1),
(4, 3, 'nthabelengkhalane@gmail.com', '2023-04-21', 1),
(5, 4, 'nthabelengkhalane@gmail.com', '2023-04-20', 1),
(6, 3, 'nthabelengkhalane@gmail.com', '2023-04-19', 1),
(7, 3, 'nthabelengkhalane@gmail.com', '2023-04-18', 1),
(8, 4, 'nthabelengkhalane@gmail.com', '2023-04-21', 1),
(9, 4, 'tankieletuka@gmail.com', '2023-04-21', 1),
(10, 7, 'test@gmail.com', '2023-04-20', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `isadmin` tinyint(1) NOT NULL DEFAULT 0,
  `password` varchar(50) DEFAULT NULL COMMENT 'only for system admin/s'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `surname`, `isadmin`, `password`) VALUES
(1, 'bellaletuka@gmail.com', 'Bella', 'Boy', 0, ''),
(4, 'katleholetuka@gmail.com', 'Katleho', 'Letuka', 0, NULL),
(2, 'nthabelengkhalane@gmail.com', 'Nthabeleng', 'Girl', 0, 'password'),
(3, 'tankieletuka@gmail.com', 'Tanki', 'Letuka', 1, 'password'),
(7, 'test@gmail.com', 'test', 'test', 0, 'test');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`c_uid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`p_uid`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`pur_uid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `c_uid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `p_uid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `pur_uid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
