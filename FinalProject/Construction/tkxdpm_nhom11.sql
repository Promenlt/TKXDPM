-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2020 at 03:55 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tkxdpm_nhom11`
--

-- --------------------------------------------------------

--
-- Table structure for table `bike`
--

CREATE TABLE `bike` (
  `bike_id` int(100) NOT NULL,
  `station_id` int(100) NOT NULL,
  `lock_id` int(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  `operation_date` datetime NOT NULL DEFAULT current_timestamp(),
  `type_id` int(100) NOT NULL,
  `status` int(30) NOT NULL DEFAULT 0,
  `value` int(100) DEFAULT 200000,
  `image` varchar(100) NOT NULL DEFAULT 'bike_default.png',
  `license_plate` varchar(200) DEFAULT NULL,
  `battery` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bike`
--

INSERT INTO `bike` (`bike_id`, `station_id`, `lock_id`, `brand`, `model`, `operation_date`, `type_id`, `status`, `value`, `image`, `license_plate`, `battery`) VALUES
(1, 1, 3, 'thong nhat', 'MX123', '2020-11-11 19:53:31', 1, 0, 1000, 'bike_default.jpg', 'TN-01', NULL),
(2, 1, 1, 'hon da', 'HD 123', '2020-11-12 09:47:03', 3, 0, 1500, 'bike_electric.jpg', 'HD-01', 99),
(3, 3, 5, 'China', 'Hang Dom', '2020-11-30 16:41:56', 1, 0, 2000, 'bike_single.jpg', 'CN-01', NULL),
(4, 2, 3, 'America Bike', 'HANG_XIN', '2020-11-30 16:42:56', 2, 0, 1200, 'bike_double.png', 'AM-01', NULL),
(6, 3, 5, 'TestBrand', 'testModel', '2020-12-24 20:09:33', 1, 0, 1000, 'bike_default.png', 'TEST_01', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `bike_type`
--

CREATE TABLE `bike_type` (
  `type_id` int(100) NOT NULL,
  `type` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bike_type`
--

INSERT INTO `bike_type` (`type_id`, `type`) VALUES
(1, 'Single Bike'),
(2, 'Double Bike'),
(3, 'Electric Bike');

-- --------------------------------------------------------

--
-- Table structure for table `docking_lock`
--

CREATE TABLE `docking_lock` (
  `lock_id` int(100) NOT NULL,
  `station_id` int(100) NOT NULL,
  `bar_code` varchar(300) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `docking_lock`
--

INSERT INTO `docking_lock` (`lock_id`, `station_id`, `bar_code`, `status`) VALUES
(1, 1, 'tn_dock_01', 1),
(2, 1, 'tn_dock_02', 1),
(3, 2, 'bk_dock_01', 1),
(4, 3, 'ys_dock_01', 1),
(5, 3, 'ys_dock_02', 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment_method`
--

CREATE TABLE `payment_method` (
  `method_id` int(100) NOT NULL,
  `method_name` varchar(100) NOT NULL,
  `card_code` varchar(100) NOT NULL,
  `owner` varchar(100) NOT NULL,
  `cvv_code` varchar(100) NOT NULL,
  `date_expire` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payment_method`
--

INSERT INTO `payment_method` (`method_id`, `method_name`, `card_code`, `owner`, `cvv_code`, `date_expire`) VALUES
(5, 'Interbank', '118609_group11_2020', 'Group 11', '762', '1125'),
(6, 'Momo', '118609_group11_2020', 'Group 11', '921', '1293');

-- --------------------------------------------------------

--
-- Table structure for table `station`
--

CREATE TABLE `station` (
  `station_id` int(100) NOT NULL,
  `station_name` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `area` int(50) NOT NULL DEFAULT 100,
  `location_code` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `station`
--

INSERT INTO `station` (`station_id`, `station_name`, `address`, `area`, `location_code`) VALUES
(1, 'thong nhat station', 'So 17 Dai Co Viet, Hai Ba Trung, Ha Noi', 200, 'location_01'),
(2, 'SVD Bach Khoa Station', '202 Le Thanh Nghi, Hai Ba Trung, Ha Noi', 100, 'location_02'),
(3, 'Cong Vien Yen So Station', '1097 Giai Phong, Ha Noi', 300, 'location_03');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(50) NOT NULL,
  `bike_id` int(100) NOT NULL,
  `user_id` int(100) NOT NULL,
  `start_time` datetime NOT NULL DEFAULT current_timestamp(),
  `end_time` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1,
  `deposit` double NOT NULL,
  `payment_Id` varchar(100) NOT NULL,
  `bike_rental` double NOT NULL,
  `time_rental` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `bike_id`, `user_id`, `start_time`, `end_time`, `status`, `deposit`, `payment_Id`, `bike_rental`, `time_rental`) VALUES
(13, 1, 1, '2020-12-08 14:50:00', '2020-12-08 14:51:46', 1, 400, '5fcf30275f8a8600176735c3', 0, '0:1:45'),
(15, 1, 1, '2020-12-08 14:55:40', '2020-12-08 14:55:51', 1, 400, '5fcf317c5f8a8600176735c6', 0, '0:0:11'),
(17, 1, 1, '2020-12-09 08:56:36', '2020-12-09 08:57:26', 1, 400, '5fd02ed4708288001784fe9a', 0, '0:0:50'),
(18, 1, 1, '2020-12-09 09:03:36', '2020-12-09 09:03:49', 1, 400, '5fd03077708288001784fe9c', 0, '0:0:13'),
(19, 1, 1, '2020-12-11 00:15:51', '2020-12-11 00:16:11', 1, 400, '5fd257bea3cdb40017a07862', 0, '0:0:19'),
(20, 1, 1, '2020-12-11 00:28:58', '2020-12-11 00:29:02', 1, 400, '5fd25ad8a3cdb40017a07866', 0, '0:0:4'),
(21, 1, 1, '2020-12-11 00:51:26', '2020-12-11 00:51:30', 1, 400, '5fd2601ca3cdb40017a07868', 0, '0:0:4'),
(22, 1, 1, '2020-12-11 01:07:32', '2020-12-11 01:07:35', 1, 400, '5fd263e3a3cdb40017a0786b', 0, '0:0:2'),
(23, 1, 1, '2020-12-11 02:05:51', '2020-12-11 02:12:34', 1, 400, '5fd27142a3cdb40017a07878', 0, '0:6:42'),
(24, 1, 1, '2020-12-11 02:13:30', '2020-12-11 02:15:59', 1, 400, '5fd27359a3cdb40017a0787e', 0, '0:2:29'),
(25, 1, 1, '2020-12-11 02:23:38', '2020-12-11 02:23:42', 1, 400, '5fd275b8a3cdb40017a07880', 0, '0:0:3'),
(26, 4, 1, '2020-12-11 02:31:46', '2020-12-11 02:31:50', 1, 480, '5fd277a1a3cdb40017a07882', 0, '0:0:4'),
(27, 1, 1, '2020-12-11 02:46:13', '2020-12-11 02:46:14', 1, 400, '5fd27b03a3cdb40017a07884', 0, '0:0:1'),
(28, 1, 1, '2020-12-18 09:52:48', '2020-12-18 09:53:32', 1, 400, '5fdc197c49f295001776295b', 0, '0:0:44'),
(29, 1, 1, '2020-12-22 11:36:48', '2020-12-22 11:46:32', 1, 400, '5fdc1f1e49f2950017762965', 0, '0:9:44'),
(30, 2, 3, '2020-12-22 02:16:02', '2020-12-22 02:25:51', 1, 600, '5fe0f4715a8daa0017a6ffbb', 0, '0:9:49'),
(31, 2, 3, '2020-12-22 10:50:32', '2020-12-22 11:40:08', 1, 600, '5fe0fc865a8daa0017a6ffbf', 16000, '0:49:36'),
(32, 2, 3, '2020-12-22 12:37:22', '2020-12-22 12:51:09', 1, 600, '5fe18610184f4c001764ed15', 10000, '0:13:46'),
(33, 1, 3, '2020-12-22 12:57:01', '2020-12-22 13:01:05', 1, 400, '5fe18aac184f4c001764ed1b', 0, '0:4:3'),
(34, 2, 1, '2020-12-22 12:58:01', '2020-12-22 12:58:05', 1, 600, '5fe18ae7184f4c001764ed1c', 0, '0:0:3'),
(35, 2, 1, '2020-12-22 13:00:03', '2020-12-22 13:00:17', 1, 600, '5fe18b62184f4c001764ed1e', 0, '0:0:13'),
(36, 2, 3, '2020-12-22 15:57:16', '2020-12-22 19:23:43', 1, 600, '5fe1b4eb9f451400170f986f', 46000, '3:26:26'),
(37, 2, 3, '2020-12-22 19:07:22', '2020-12-22 19:28:14', 1, 600, '5fe1e6289f451400170f98be', 10000, '0:20:52'),
(38, 2, 3, '2020-12-22 19:30:56', '2020-12-22 19:54:54', 1, 600, '5fe1ec279f451400170f98c2', 1004.4, '0:23:58'),
(39, 2, 3, '2020-12-22 19:39:10', '2020-12-22 19:59:37', 1, 600, '5fe1ed9d9f451400170f98c4', 10000, '0:20:27'),
(40, 2, 3, '2020-12-22 20:46:31', '2020-12-22 21:21:30', 1, 600, '5fe1f8b59f451400170f98d0', 13000, '0:34:58'),
(41, 2, 3, '2020-12-22 21:05:43', '2020-12-22 21:26:15', 1, 600, '5fe201e69f451400170f98d5', 14400, '0:20:31'),
(42, 4, 3, '2020-12-24 03:31:11', '2020-12-24 03:44:39', 1, 480, '5fe2fe58470a840017cbc93e', 9520, '0:13:27'),
(43, 2, 3, '2020-12-24 12:35:07', '2020-12-24 12:35:22', 1, 600, '5fe4288965ba3f00178feeef', -600, '0:0:14'),
(44, 2, 3, '2020-12-24 21:19:23', '2020-12-24 21:40:03', 1, 600, '5fe4a81ad5e9da0017f4f45a', 14400, '0:20:39');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(300) NOT NULL,
  `status` int(30) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `name`, `username`, `password`, `status`) VALUES
(1, 'Nguyen Cong Luat', 'luatnc', 'nchust', 0),
(2, 'Nguyen Hai Long', 'longnh', 'nhhust', 0),
(3, 'Nguyen Thanh Long', 'longnt', 'nthust', 0),
(4, 'Nguyen Van A', 'nguyenA', 'abc123', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bike`
--
ALTER TABLE `bike`
  ADD PRIMARY KEY (`bike_id`),
  ADD KEY `bike_station_id` (`station_id`),
  ADD KEY `bike_lock_id` (`lock_id`),
  ADD KEY `bike_type_id` (`type_id`);

--
-- Indexes for table `bike_type`
--
ALTER TABLE `bike_type`
  ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `docking_lock`
--
ALTER TABLE `docking_lock`
  ADD PRIMARY KEY (`lock_id`),
  ADD KEY `lock_station_id` (`station_id`);

--
-- Indexes for table `payment_method`
--
ALTER TABLE `payment_method`
  ADD PRIMARY KEY (`method_id`);

--
-- Indexes for table `station`
--
ALTER TABLE `station`
  ADD PRIMARY KEY (`station_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `transaction_bike_id` (`bike_id`),
  ADD KEY `transaction_user_id` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bike`
--
ALTER TABLE `bike`
  MODIFY `bike_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `bike_type`
--
ALTER TABLE `bike_type`
  MODIFY `type_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `docking_lock`
--
ALTER TABLE `docking_lock`
  MODIFY `lock_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payment_method`
--
ALTER TABLE `payment_method`
  MODIFY `method_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `station`
--
ALTER TABLE `station`
  MODIFY `station_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bike`
--
ALTER TABLE `bike`
  ADD CONSTRAINT `bike_lock_id` FOREIGN KEY (`lock_id`) REFERENCES `docking_lock` (`lock_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bike_station_id` FOREIGN KEY (`station_id`) REFERENCES `station` (`station_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bike_type_id` FOREIGN KEY (`type_id`) REFERENCES `bike_type` (`type_id`);

--
-- Constraints for table `docking_lock`
--
ALTER TABLE `docking_lock`
  ADD CONSTRAINT `lock_station_id` FOREIGN KEY (`station_id`) REFERENCES `station` (`station_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_bike_id` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`bike_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
