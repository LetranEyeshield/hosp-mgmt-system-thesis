-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 09, 2017 at 05:39 PM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `h_m_s`
--

-- --------------------------------------------------------

--
-- Table structure for table `admission`
--

CREATE TABLE IF NOT EXISTS `admission` (
  `patient_id` varchar(10) NOT NULL,
  `date_month` varchar(50) NOT NULL,
  `date_day` varchar(50) NOT NULL,
  `date_year` varchar(50) NOT NULL,
  `date_hour` varchar(50) NOT NULL,
  `date_minutes` varchar(50) NOT NULL,
  `am_pm` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_month` varchar(50) NOT NULL,
  `birth_day` varchar(50) NOT NULL,
  `birth_year` varchar(50) NOT NULL,
  `birthplace` varchar(250) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `civil_status` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `occupation` varchar(250) NOT NULL,
  `nationality` varchar(250) NOT NULL,
  `religion` varchar(50) NOT NULL,
  `ward` varchar(50) NOT NULL,
  `physician` varchar(250) NOT NULL,
  `father` varchar(250) NOT NULL,
  `mother` varchar(250) NOT NULL,
  `philhealth` varchar(50) NOT NULL,
  `diagnosis` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission`
--

INSERT INTO `admission` (`patient_id`, `date_month`, `date_day`, `date_year`, `date_hour`, `date_minutes`, `am_pm`, `first_name`, `middle_name`, `last_name`, `birth_month`, `birth_day`, `birth_year`, `birthplace`, `age`, `gender`, `civil_status`, `address`, `occupation`, `nationality`, `religion`, `ward`, `physician`, `father`, `mother`, `philhealth`, `diagnosis`) VALUES
('1360079', '', '', '', '', '', '', 'Miel', 'Francisco', 'Garcia', 'December', '6', '1988', 'Dagupan City, Pangasinan', '29', 'Female', 'Divorced', 'Sison, Pangasinan', 'Nurse', 'Filipino', 'Born Again Christian', '3', '', '', '', '', 'Chest Pain'),
('1596192', '', '', '', '', '', '', 'Alexandra', 'Aquino', 'Santos', 'June', '4', '2010', 'Mangaldan, Pangasinan', '6', 'Female', 'Single', 'Mangaldan, Pangasinan', 'None', 'Filipino', 'Roman Catholic', '6', '', 'Alex Santos', 'Rea Santos', '', 'Diarrhea'),
('2510850', '', '', '', '', '', '', 'Christine', 'Santiago', 'Pagtalunan', 'July', '3', '1999', 'Nalsian, Manaoag, Pangasinan', '17', 'Female', 'Single', 'Nalsian, Manaoag, Pangasinan', 'Student', 'Filipino', 'Roman Catholic', '2', '', 'Macario Pagtalunan', 'Evelyn Pagtalunan', '', 'Dengue'),
('3027193', '', '', '', '', '', '', 'Ziano', 'Flores', 'Ramirez', 'March', '1', '1990', 'Manaoag, Pangasinan', '27', 'Male', 'Separated', 'Anda, Pangasinan', 'Fisherman', 'Filipino', 'Roman Catholic', '1', '', 'Franco Ramirez', 'Fe Ramirez', '', 'Skin Problem'),
('3065741', '', '', '', '', '', '', 'Alonzo', 'Soriano', 'Cruz', 'March', '12', '1998', 'Urdaneta, Pangasinan', '19', 'Male', 'Single', 'Urdaneta, Pangasinan', 'None', 'Filipino', 'Iglesia NI Cristo', '3', '', 'Miguel Cruz', 'Grace Cruz', '', 'Redness of Eyes'),
('5590681', '', '', '', '', '', '', 'Kathryn', 'P.', 'Acosta', 'May', '1', '1981', 'Manaoag, Pangasinan', '31', 'Female', 'Married', 'Manaoag', 'Instructor', 'Filipino', 'Catholic', '143', '', '', '', '', 'Headache'),
('5750212', '', '', '', '', '', '', 'Lyndon', 'Dela Peña', 'Lim', 'October', '10', '1996', 'Sison, Pangasinan', '20', 'Male', 'Single', 'Sison, Pangasinan', 'Student', 'Filipino', 'Roman Catholic', '1', '', 'Anastacio Lim', 'Bea Lim', '', 'Appendix'),
('5811689', '', '', '', '', '', '', 'Moriel Kim', 'B.', 'Mana-ay', 'December', '15', '1995', 'Baritao Manaoag, Pangasinan', '21', 'Female', 'Married', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Filipino', 'Roman Catholic', '7', '', 'Honorio Mana-ay', 'Rosalinda Mana-ay', '', 'Birth Delivery'),
('7795274', '', '', '', '', '', '', 'Mary Rose', 'Gonzalez', 'Rodriguez', 'March', '4', '1996', 'San Ramon, Manaoag, Pangasinan', '20', 'Female', 'Single', 'San Ramon, Manaoag, Pangasinan', 'None', 'Filipino', 'Roman Catholic', '1', '', 'Romeo Rodriguez', 'Marlyn Rodriguez', '', 'Skin Cancer'),
('7916857', 'January', '21', '2018', '10', '30', 'am', 'Eren', 'B.', 'Jaeger', 'June', '15', '1995', 'Baritao Manaoag, Pangasinan', '21', 'Male', 'Married', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Filipino', 'Roman Catholic', '7', 'Dr. Mejia', '', '', 'with philhealth', 'Headache'),
('7934156', '', '', '', '', '', '', 'Jamaica', 'Ferrer', 'Rosalin', 'October', '24', '1993', 'Lingayen, Pangasinan', '23', 'Female', 'Married', 'LIngayen, Pangasinan', 'English Teacher', 'Filipino', 'Roman Catholic', '1', '', 'Mr. Ferrer', 'Mrs. Ferrer', '', 'Birth Delivery'),
('8906609', '', '', '', '', '', '', 'Franz', 'Aquino', 'Agustin', 'February', '3', '1986', 'Dagupan City, Pangasinan', '30', 'Female', 'Married', 'Dagupan City, Pangasinan', 'Teacher', 'Filipino', 'Roman Catholic', '3', '', 'Filipe Aquino', 'Ranze Aquino', '', 'Redness of The Skin'),
('8947372', '', '', '', '', '', '', 'Lanie', 'Aguilar', 'Dimafilis', 'May', '6', '1986', 'Oraan East, Manaoag, Pangasinan', '28', 'Female', 'Single', 'Oraan East, Manaoag, Pangasinan', 'Factory Worker', 'Filipino', 'Roman Catholic', '2', '', 'Ronie Dimafilis', 'Vangie Dimafilis', '', 'Kidney Problem');

-- --------------------------------------------------------

--
-- Table structure for table `admission_history`
--

CREATE TABLE IF NOT EXISTS `admission_history` (
  `aid` int(11) NOT NULL,
  `patient_id` varchar(50) NOT NULL,
  `date_month` varchar(50) NOT NULL,
  `date_day` varchar(50) NOT NULL,
  `date_year` varchar(50) NOT NULL,
  `date_hour` varchar(50) NOT NULL,
  `date_minutes` varchar(50) NOT NULL,
  `am_pm` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_month` varchar(50) NOT NULL,
  `birth_day` varchar(50) NOT NULL,
  `birth_year` varchar(50) NOT NULL,
  `birthplace` varchar(250) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `civil_status` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `occupation` varchar(250) NOT NULL,
  `nationality` varchar(250) NOT NULL,
  `religion` varchar(50) NOT NULL,
  `ward` varchar(50) NOT NULL,
  `physician` varchar(250) NOT NULL,
  `father` varchar(250) NOT NULL,
  `mother` varchar(250) NOT NULL,
  `philhealth` varchar(50) NOT NULL,
  `diagnosis` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission_history`
--

INSERT INTO `admission_history` (`aid`, `patient_id`, `date_month`, `date_day`, `date_year`, `date_hour`, `date_minutes`, `am_pm`, `first_name`, `middle_name`, `last_name`, `birth_month`, `birth_day`, `birth_year`, `birthplace`, `age`, `gender`, `civil_status`, `address`, `occupation`, `nationality`, `religion`, `ward`, `physician`, `father`, `mother`, `philhealth`, `diagnosis`) VALUES
(1, '0', 'January', '21', '2018', '10', '30', 'am', 'Moriel Kim', 'B.', 'Mana-ay', 'December', '15', '1995', 'Baritao Manaoag, Pangasinan', '21', 'Female', 'Married', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Filipino', 'Roman Catholic', '143', 'Dr. Mejia', 'Honorio Mana-ay', 'Rosalinda Mana-ay', 'with philhealth', 'Birth Delivery');

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE IF NOT EXISTS `billing` (
  `patient_id` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `ward` varchar(50) NOT NULL,
  `ward_charge` double NOT NULL,
  `ward_type` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `address` varchar(2000) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `phic` varchar(2000) NOT NULL,
  `month` varchar(50) NOT NULL,
  `day` varchar(50) NOT NULL,
  `year` varchar(50) NOT NULL,
  `patient_case` varchar(2000) NOT NULL,
  `no_days` double NOT NULL,
  `doctor_charge` double NOT NULL,
  `other_charge` double NOT NULL,
  `bill` double NOT NULL,
  `surgery` text NOT NULL,
  `surgery_charge` varchar(200) NOT NULL,
  `laboratory` text NOT NULL,
  `laboratory_charge` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`patient_id`, `first_name`, `middle_name`, `last_name`, `ward`, `ward_charge`, `ward_type`, `age`, `address`, `gender`, `phic`, `month`, `day`, `year`, `patient_case`, `no_days`, `doctor_charge`, `other_charge`, `bill`, `surgery`, `surgery_charge`, `laboratory`, `laboratory_charge`) VALUES
('1360079', 'Miel', 'Francisco', 'Garcia', '3', 0, 'Charity Ward', '29', 'Sison, Pangasinan', 'Female', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('1596192', 'Alexandra', 'Aquino', 'Santos', '6', 0, 'Private', '6', 'Mangaldan, Pangasinan', 'Female', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('2510850', 'Christine', 'Santiago', 'Pagtalunan', '2', 0, 'Charity Ward', '17', 'Nalsian, Manaoag, Pangasinan', 'Female', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('3027193', 'Ziano', 'Flores', 'Ramirez', '1', 0, 'Private', '27', 'Anda, Pangasinan', 'Male', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('3065741', 'Alonzo', 'Soriano', 'Cruz', '3', 0, 'Charity Ward', '19', 'Urdaneta, Pangasinan', 'Male', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 500, 2500, 69150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
('5750212', 'Lyndon', 'Dela Peña', 'Lim', '1', 0, 'Pay Ward', '20', 'Sison, Pangasinan', 'Male', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', 500, 'Private', '21', 'Baritao, Manaoag, Pangasinan', 'Female', 'philhealth', 'January', '7', '2017', 'cancer', 2, 500, 500, 2500, '', '0.0', '', '0.0'),
('7795274', 'Mary Rose', 'Gonzalez', 'Rodriguez', '1', 0, 'Charity Ward', '20', 'San Ramon, Manaoag, Pangasinan', 'Female', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 4250, '', '0.0', '', '0.0'),
('7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', '', '0.0'),
('8906609', 'Franz', 'Aquino', 'Agustin', '3', 0, 'Charity Ward', '30', 'Dagupan City, Pangasinan', 'Female', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0'),
('8947372', 'Lanie', 'Aguilar', 'Dimafilis', '2', 0, 'Pay Ward', '28', 'Oraan East, Manaoag, Pangasinan', 'Female', '', '', '', '', '', 0, 0, 0, 0, '', '0', '', '0');

-- --------------------------------------------------------

--
-- Table structure for table `billing_history`
--

CREATE TABLE IF NOT EXISTS `billing_history` (
  `bid` int(11) NOT NULL,
  `patient_id` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `ward` varchar(50) NOT NULL,
  `ward_charge` double NOT NULL,
  `ward_type` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `address` varchar(2000) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `phic` varchar(2000) NOT NULL,
  `month` varchar(50) NOT NULL,
  `day` varchar(50) NOT NULL,
  `year` varchar(50) NOT NULL,
  `patient_case` varchar(2000) NOT NULL,
  `no_days` double NOT NULL,
  `doctor_charge` double NOT NULL,
  `other_charge` double NOT NULL,
  `bill` double NOT NULL,
  `surgery` text NOT NULL,
  `surgery_charge` varchar(200) NOT NULL,
  `laboratory` text NOT NULL,
  `laboratory_charge` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `billing_history`
--

INSERT INTO `billing_history` (`bid`, `patient_id`, `first_name`, `middle_name`, `last_name`, `ward`, `ward_charge`, `ward_type`, `age`, `address`, `gender`, `phic`, `month`, `day`, `year`, `patient_case`, `no_days`, `doctor_charge`, `other_charge`, `bill`, `surgery`, `surgery_charge`, `laboratory`, `laboratory_charge`) VALUES
(1, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 500, 2500, 69150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(2, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', 500, 'Private', '21', 'Baritao, Manaoag, Pangasinan', 'Female', 'philhealth', 'January', '7', '2017', 'cancer', 2, 500, 500, 2000, '', '', '', ''),
(3, '7916857', 'Moriel Kim', 'B.', 'Mana-ay', '5', 500, 'Private', '21', 'Baritao, Manaoag, Pangasinan', 'Female', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 3500, '', '', '', ''),
(4, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 0, 'Private', '23', 'LIngayen, Pangasinan', 'Female', '', '', '', '', '', 0, 0, 0, 0, '', '', '', ''),
(5, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 6, 500, 2500, 71150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(6, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 8, 500, 2500, 72150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(7, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', 500, 'Private', '21', 'Baritao, Manaoag, Pangasinan', 'Female', 'philhealth', 'January', '7', '2017', 'cancer', 2, 500, 500, 2500, '', '0.0', '', '0.0'),
(8, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', '', '0.0'),
(9, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'Ultrasound', '0.0'),
(10, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'X-RAY', '0.0'),
(11, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'X-RAY', '0.0'),
(12, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 13500, 'Breast Augmentation Surgery', '10000.0', 'X-RAY', '0.0'),
(13, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', 500, 'Private', '21', 'Baritao, Manaoag, Pangasinan', 'Female', 'philhealth', 'January', '7', '2017', 'cancer', 2, 500, 500, 2500, '', '0.0', 'X-RAY', '0.0'),
(14, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 4250, '', '0.0', '', '0.0'),
(15, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'X-RAY', '0.0'),
(16, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'X-RAY', '0.0'),
(17, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 4250, '', '0.0', 'X-RAY', '0.0'),
(18, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'X-RAY', '0.0'),
(19, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 23500, 'Breast Augmentation Surgery', '10000.0', 'X-RAY', '10000.0'),
(20, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'X-RAY', '0.0'),
(21, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, 'Breast Augmentation Surgery', '0.0', 'X-RAY', '0.0'),
(22, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', 'X-RAY', '0.0'),
(23, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 4000, '', '0.0', 'X-RAY', '500.0'),
(24, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 29000, 'Colon Surgery', '25000.0', 'X-RAY', '500.0'),
(25, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 4250, '', '0.0', '', '0.0'),
(26, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 4250, '', '0.0', '', '0.0'),
(27, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 4250, '', '0.0', 'Ultrasound', '750.0'),
(28, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 3500, '', '0.0', '', '0.0'),
(29, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 4250, '', '0.0', 'Ultrasound', '750.0'),
(30, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 14250, 'Breast Augmentation Surgery', '10000.0', 'Ultrasound', '750.0'),
(31, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 4000, '', '0.0', 'X-RAY', '500.0'),
(32, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 14000, 'Breast Augmentation Surgery', '10000.0', 'X-RAY', '500.0'),
(33, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 69750, 'Plastic and Reconstructive Surgery', '65000.0', 'X-RAY', '500.0'),
(34, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 500, 25000, 91650, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(35, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 500, 250, 66900, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(36, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 54750, 'Neurosurgery', '50000.0', 'X-RAY', '500.0'),
(37, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 1500, 2500, 71150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(38, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 70000, 'Plastic and Reconstructive Surgery', '65000.0', 'Ultrasound', '750.0'),
(39, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 1500, 5000, 38500, 'Orthopedic Surgery', '30000.0', 'Blood Test', '500.0'),
(40, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', 500, 'Private', '21', 'Baritao, Manaoag, Pangasinan', 'Female', 'philhealth', 'January', '7', '2017', 'cancer', 2, 500, 500, 21250, 'Gynecological Surgery', '18000.0', 'Ultrasound', '750.0'),
(41, '7916857', 'Eren', 'B.', 'Jaeger', '7', 250, 'Pay Ward', '21', 'Baritao, Manaoag, Pangasinan', 'Male', 'PhilHealth', 'February', '2', '2017', 'headache', 5, 500, 500, 23750, 'Gynecological Surgery', '18000.0', 'HCG', '1500.0'),
(42, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', 500, 'Private', '23', 'LIngayen, Pangasinan', 'Female', 'PhilHealth', 'January', '14', '2017', 'Birth Delivery', 3, 500, 500, 19250, 'Rectal Surgery', '15000.0', 'Ultrasound', '750.0'),
(43, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 500, 2500, 69000, 'Plastic and Reconstructive Surgery', '65000.0', 'Pap Smear', '500.0'),
(44, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 500, 5700, 72200, 'Plastic and Reconstructive Surgery', '65000.0', 'Pap Smear', '500.0'),
(45, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 5000, 2500, 78150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(46, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 5000, 2500, 38000, 'Pediatric Surgery', '25000.0', 'Pap Smear', '500.0'),
(47, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 5000, 2500, 78150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(48, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 5000, 2500, 48150, 'Ophthalmological Surgery', '35000.0', 'Flu Test', '650.0'),
(49, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 5000, 2500, 78150, 'Plastic and Reconstructive Surgery', '65000.0', 'Flu Test', '650.0'),
(50, '5590681', 'Kathryn', 'P.', 'Acosta', '143', 0, 'Charity Ward', '31', 'Manaoag', 'Female', 'PhilHealth', 'February', '14', '2017', 'Headache', 2, 5000, 2500, 63150, 'Neurosurgery', '50000.0', 'Flu Test', '650.0');

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE IF NOT EXISTS `consultation` (
  `patient_id` varchar(10) NOT NULL,
  `date` varchar(100) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_month` varchar(50) NOT NULL,
  `birth_day` varchar(50) NOT NULL,
  `birth_year` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `address` varchar(2000) NOT NULL,
  `civil_status` varchar(20) NOT NULL,
  `doctor` varchar(90) NOT NULL,
  `reason` varchar(2000) NOT NULL,
  `orientation` varchar(50) NOT NULL,
  `fee` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation`
--

INSERT INTO `consultation` (`patient_id`, `date`, `first_name`, `middle_name`, `last_name`, `birth_month`, `birth_day`, `birth_year`, `age`, `gender`, `address`, `civil_status`, `doctor`, `reason`, `orientation`, `fee`) VALUES
('1360079', '', 'Miel', 'Francisco', 'Garcia', 'December', '6', '1988', '29', 'Female', 'Sison, Pangasinan', 'Divorced', '', '', '', ''),
('1596192', '', 'Alexandra', 'Aquino', 'Santos', 'June', '4', '2010', '6', 'Female', 'Mangaldan, Pangasinan', 'Single', '', '', '', ''),
('2510850', '', 'Christine', 'Santiago', 'Pagtalunan', 'July', '3', '1999', '17', 'Female', 'Nalsian, Manaoag, Pangasinan', 'Single', '', '', '', ''),
('3027193', '', 'Ziano', 'Flores', 'Ramirez', 'March', '1', '1990', '27', 'Male', 'Anda, Pangasinan', 'Separated', '', '', '', ''),
('3065741', '', 'Alonzo', 'Soriano', 'Cruz', 'March', '12', '1998', '19', 'Male', 'Urdaneta, Pangasinan', 'Single', '', '', '', ''),
('5590681', 'January 1, 2017', 'Kathryn', 'P.', 'Acosta', 'May', '1', '1981', '31', 'Female', 'Manaoag', 'Married', 'Dr. Mike ', 'Headache', 'New', '250'),
('5750212', '', 'Lyndon', 'Dela Peña', 'Lim', 'October', '10', '1996', '20', 'Male', 'Sison, Pangasinan', 'Single', '', '', '', ''),
('5811689', '', 'Moriel Kim', 'B.', 'Mana-ay', 'December', '15', '1995', '21', 'Female', 'Baritao, Manaoag, Pangasinan', 'Married', '', '', '', ''),
('7795274', '', 'Mary Rose', 'Gonzalez', 'Rodriguez', 'March', '4', '1996', '20', 'Female', 'San Ramon, Manaoag, Pangasinan', 'Single', '', '', '', ''),
('7916857', '', 'Eren', 'B.', 'Jaeger', 'June', '15', '1995', '21', 'Male', 'Baritao, Manaoag, Pangasinan', 'Married', '', '', '', ''),
('7934156', '', 'Jamaica', 'Ferrer', 'Rosalin', 'October', '24', '1993', '23', 'Female', 'LIngayen, Pangasinan', 'Married', '', '', '', ''),
('8906609', '', 'Franz', 'Aquino', 'Agustin', 'February', '3', '1986', '30', 'Female', 'Dagupan City, Pangasinan', 'Married', '', '', '', ''),
('8947372', '', 'Lanie', 'Aguilar', 'Dimafilis', 'May', '6', '1986', '28', 'Female', 'Oraan East, Manaoag, Pangasinan', 'Single', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `consultation_history`
--

CREATE TABLE IF NOT EXISTS `consultation_history` (
  `cid` int(11) NOT NULL,
  `patient_id` varchar(50) NOT NULL,
  `date` varchar(100) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_month` varchar(50) NOT NULL,
  `birth_day` varchar(50) NOT NULL,
  `birth_year` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `address` varchar(2000) NOT NULL,
  `civil_status` varchar(20) NOT NULL,
  `doctor` varchar(90) NOT NULL,
  `reason` varchar(2000) NOT NULL,
  `orientation` varchar(50) NOT NULL,
  `fee` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consultation_history`
--

INSERT INTO `consultation_history` (`cid`, `patient_id`, `date`, `first_name`, `middle_name`, `last_name`, `birth_month`, `birth_day`, `birth_year`, `age`, `gender`, `address`, `civil_status`, `doctor`, `reason`, `orientation`, `fee`) VALUES
(1, '5590681', 'January 1, 2017', 'Kathryn', 'P.', 'Acosta', 'May', '1', '1981', '31', 'Female', 'Manaoag', 'Married', 'Dr. Mike ', 'Headache', 'New', '250');

-- --------------------------------------------------------

--
-- Table structure for table `discharge`
--

CREATE TABLE IF NOT EXISTS `discharge` (
  `patient_id` varchar(10) NOT NULL,
  `case_no` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `ward` varchar(50) NOT NULL,
  `ward_type` varchar(50) NOT NULL,
  `bed_no` varchar(50) NOT NULL,
  `admit_date` varchar(50) NOT NULL,
  `discharge_date` varchar(50) NOT NULL,
  `days` varchar(50) NOT NULL,
  `disposition` varchar(250) NOT NULL,
  `result` varchar(250) NOT NULL,
  `physician` varchar(250) NOT NULL,
  `admit_diagnosis` varchar(250) NOT NULL,
  `final_diagnosis` varchar(250) NOT NULL,
  `other_diagnosis` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discharge`
--

INSERT INTO `discharge` (`patient_id`, `case_no`, `first_name`, `middle_name`, `last_name`, `address`, `ward`, `ward_type`, `bed_no`, `admit_date`, `discharge_date`, `days`, `disposition`, `result`, `physician`, `admit_diagnosis`, `final_diagnosis`, `other_diagnosis`) VALUES
('1360079', '', 'Miel', 'Francisco', 'Garcia', 'Sison, Pangasinan', '3', 'Charity Ward', '4', '', '', '', '', '', '', '', '', ''),
('1596192', '', 'Alexandra', 'Aquino', 'Santos', 'Mangaldan, Pangasinan', '6', 'Private', '1', '', '', '', '', '', '', '', '', ''),
('2510850', '', 'Christine', 'Santiago', 'Pagtalunan', 'Nalsian, Manaoag, Pangasinan', '2', 'Charity Ward', '1', '', '', '', '', '', '', '', '', ''),
('3027193', '', 'Ziano', 'Flores', 'Ramirez', 'Anda, Pangasinan', '1', 'Private', '2', '', '', '', '', '', '', '', '', ''),
('3065741', '', 'Alonzo', 'Soriano', 'Cruz', 'Urdaneta, Pangasinan', '3', 'Charity Ward', '4', '', '', '', '', '', '', '', '', ''),
('5590681', '', 'Kathryn', 'P.', 'Acosta', 'Manaoag', '143', 'Charity Ward', '2', '', '', '', '', '', '', '', '', ''),
('5750212', '', 'Lyndon', 'Dela Peña', 'Lim', 'Sison, Pangasinan', '1', 'Pay Ward', '3', '', '', '', '', '', '', '', '', ''),
('5811689', '', 'Moriel Kim', 'B.', 'Mana-ay', 'Baritao, Manaoag, Pangasinan', '7', 'Private', '2', '', '', '', '', '', '', '', '', ''),
('7795274', '', 'Mary Rose', 'Gonzalez', 'Rodriguez', 'San Ramon, Manaoag, Pangasinan', '1', 'Charity Ward', '3', '', '', '', '', '', '', '', '', ''),
('7916857', '143', 'Eren', 'B.', 'Jaeger', 'Baritao, Manaoag, Pangasinan', '7', 'Pay Ward', '1', 'January 21 2018', 'January 23,  2018', '3', 'Discharge', 'Recovered', 'Dr. Mejia', 'Birth Delivery', 'None', 'None'),
('7934156', '143', 'Jamaica', 'Ferrer', 'Rosalin', 'LIngayen, Pangasinan', '1', 'Private', '2', 'december  21', 'july 1', '3', 'HAMA', '-48 hours', 'Doctor kevs Gamboa', 'Pregnant			', 'Pregnant', 'Non'),
('8906609', '', 'Franz', 'Aquino', 'Agustin', 'Dagupan City, Pangasinan', '3', 'Charity Ward', '4', '', '', '', '', '', '', '', '', ''),
('8947372', '', 'Lanie', 'Aguilar', 'Dimafilis', 'Oraan East, Manaoag, Pangasinan', '2', 'Pay Ward', '1', '', '', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `discharge_history`
--

CREATE TABLE IF NOT EXISTS `discharge_history` (
  `did` int(11) NOT NULL,
  `patient_id` varchar(50) NOT NULL,
  `case_no` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `ward` varchar(50) NOT NULL,
  `ward_type` varchar(50) NOT NULL,
  `bed_no` varchar(50) NOT NULL,
  `admit_date` varchar(50) NOT NULL,
  `discharge_date` varchar(50) NOT NULL,
  `days` varchar(50) NOT NULL,
  `disposition` varchar(250) NOT NULL,
  `result` varchar(250) NOT NULL,
  `physician` varchar(250) NOT NULL,
  `admit_diagnosis` varchar(250) NOT NULL,
  `final_diagnosis` varchar(250) NOT NULL,
  `other_diagnosis` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discharge_history`
--

INSERT INTO `discharge_history` (`did`, `patient_id`, `case_no`, `first_name`, `middle_name`, `last_name`, `address`, `ward`, `ward_type`, `bed_no`, `admit_date`, `discharge_date`, `days`, `disposition`, `result`, `physician`, `admit_diagnosis`, `final_diagnosis`, `other_diagnosis`) VALUES
(1, '', '143', 'Moriel Kim', 'B.', 'Mana-ay', 'Baritao, Manaoag, Pangasinan', '143', 'Pay Ward', '143', 'January 21 2018', 'January 23,  2018', '3', 'Discharge', 'Recovered', 'Dr. Mejia', 'Birth Delivery', 'None', 'None'),
(2, '7916857', '143', 'Moriel Kim', 'B.', 'Mana-ay', 'Baritao, Manaoag, Pangasinan', '143', 'Pay Ward', '143', 'January 21 2018', 'January 23,  2018', '3', 'Discharge', 'Recovered', 'Dr. Mejia', 'Birth Delivery', 'None', 'None'),
(3, '7934156', '666', 'Jamaica', 'Ferrer', 'Rosalin', 'LIngayen, Pangasinan', '1', 'Private', '2', 'december  21', 'july 1', '3', 'HAMA', '-48 hours', 'Doctor kevs Gamboa', 'Pregnant			', 'Pregnant', 'nonPregnant'),
(4, '7934156', '666', 'Jamaica', 'Ferrer', 'Rosalin', 'LIngayen, Pangasinan', '1', 'Private', '2', 'december  21', 'july 1', '3', 'HAMA', '-48 hours', 'Doctor kevs Gamboa', 'Pregnant			', 'Pregnant', 'Non'),
(5, '7934156', '143', 'Jamaica', 'Ferrer', 'Rosalin', 'LIngayen, Pangasinan', '1', 'Private', '2', 'december  21', 'july 1', '3', 'HAMA', '-48 hours', 'Doctor kevs Gamboa', 'Pregnant			', 'Pregnant', 'Non');

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE IF NOT EXISTS `doctors` (
  `doctor_count` int(11) NOT NULL,
  `doctor_id` varchar(10) NOT NULL,
  `doctor_name` varchar(250) NOT NULL,
  `specialization` varchar(250) NOT NULL,
  `birth_month` varchar(50) NOT NULL,
  `birth_day` varchar(50) NOT NULL,
  `birth_year` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `religion` varchar(250) NOT NULL,
  `nationality` varchar(250) NOT NULL,
  `e_mail` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `charge` varchar(50) NOT NULL,
  `photo` longtext NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`doctor_count`, `doctor_id`, `doctor_name`, `specialization`, `birth_month`, `birth_day`, `birth_year`, `age`, `gender`, `religion`, `nationality`, `e_mail`, `address`, `charge`, `photo`) VALUES
(1, '1484493', 'Kevin', 'Heart surgery', 'December', '9', '1994', '25', 'Male', 'Roman Catholic', 'Filipino', 'www.shiten@gmail.com', 'Babasit', '500', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\creators\\shiten.jpg'),
(8, '4301735', 'Jericoh Gutierrez', 'Physician', 'August', '7', '1996', '20', 'Male', 'Roman Catholic', 'Filipino', 'Gutierrez.jericoh@gmail.com', 'Cabanbanan Manaoag Pang.', '900', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\creators\\jhe.jpg'),
(2, '5578507', 'Moriel', 'Physician', 'December', '15', '1995', '21', 'Female', 'Catholic', 'Fil-Chinese', 'www.moriel@gmail.com', 'Baritao', '500', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\creators\\moriel.jpg'),
(3, '600325', 'Eyeshield Rosalin', 'Neurologist', 'March', '26', '1983', '33', 'Male', 'Roman Catholic', 'Filipino', 'www.eyeshield@gmail.com', 'Poblacion', '1500', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\creators\\eyeshield.jpg'),
(7, '7513602', 'Ginalyn Salon', 'Physician', 'October', '17', '1996', '20', 'Female', 'Catholic', 'Filipino', 'www.gina@gmail.com', 'Lipit', '150', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\res\\download.jpg'),
(4, '8354401', 'Brain Purganan', 'Physician', 'November', '21', '1994', '22', 'Male', 'INC', 'Flipino', 'www.brain@gmail.com', 'Sta. Maria', '150', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\creators\\brain.jpg'),
(5, '8595114', 'Herbert Gamboa', 'Physician', 'January', '5', '1995', '21', 'Male', 'Catholic', 'Filipino', 'www.herbs@gmail.com', 'Pugaro', '500', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\creators\\herbs.jpg'),
(6, '8792901', 'Rolan Masajul', 'Dentis', 'January', '25', '1993', '20', 'Male', 'Catholic', 'Filipino', 'www.bates@gmail.com', 'Pugaro', '600', 'C:\\Users\\aatotXD\\Desktop\\JAVA_HMS\\HMS\\creators\\bates.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `doctors_schedule`
--

CREATE TABLE IF NOT EXISTS `doctors_schedule` (
  `schedule_id` int(11) NOT NULL,
  `doctor_id` varchar(50) NOT NULL,
  `doctor_name` text NOT NULL,
  `date` text NOT NULL,
  `time` text NOT NULL,
  `patient` text NOT NULL,
  `subject` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctors_schedule`
--

INSERT INTO `doctors_schedule` (`schedule_id`, `doctor_id`, `doctor_name`, `date`, `time`, `patient`, `subject`) VALUES
(1, '1484493', 'Kevin', 'Jamuary 21, 2017', '2:00 pm', 'Juan Dela Cruz', 'Consultation'),
(2, '4301735', 'Jericoh Gutierrez', 'February 21, 2017', '10:30 am', 'Ginalyn Salon', 'Theraphy'),
(3, '5578507', 'Moriel', 'February 14, 2017', '3:00 pm', 'Michael Cris Rosalin', 'Heart Check-Up'),
(4, '600325', 'Eyeshield Rosalin', 'Jamuary 14, 2017', '2:00 pm', 'Jamaica Ferrer', 'Heart Check-Up'),
(5, '600325', 'Eyeshield Rosalin', 'February 14, 2017', '3:00 pm', 'Moriel Kim B. Mana-ay', 'Heart Check-Up'),
(6, '8354401', 'Brain Purganan', 'March 11, 2016', '4:00 pm', 'Daisy Veloso', 'Check-Up');

-- --------------------------------------------------------

--
-- Table structure for table `partial_billing`
--

CREATE TABLE IF NOT EXISTS `partial_billing` (
  `patient_id` varchar(50) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `middle_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `ward_no` varchar(50) NOT NULL,
  `ward_charge` varchar(50) NOT NULL,
  `ward_type` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `num_days` varchar(50) NOT NULL,
  `doctor_charge` varchar(50) NOT NULL,
  `other_charge` varchar(50) NOT NULL,
  `partial_bill` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partial_billing`
--

INSERT INTO `partial_billing` (`patient_id`, `first_name`, `middle_name`, `last_name`, `ward_no`, `ward_charge`, `ward_type`, `age`, `gender`, `num_days`, `doctor_charge`, `other_charge`, `partial_bill`) VALUES
('1360079', 'Miel', 'Francisco', 'Garcia', '3', '', 'Charity Ward', '29', 'Female', '', '', '', ''),
('1596192', 'Alexandra', 'Aquino', 'Santos', '6', '', 'Private', '6', 'Female', '', '', '', ''),
('2510850', 'Christine', 'Santiago', 'Pagtalunan', '2', '', 'Charity Ward', '17', 'Female', '', '', '', ''),
('3027193', 'Ziano', 'Flores', 'Ramirez', '1', '', 'Private', '27', 'Male', '', '', '', ''),
('3065741', 'Alonzo', 'Soriano', 'Cruz', '3', '', 'Charity Ward', '19', 'Male', '', '', '', ''),
('5590681', 'Kathryn', 'P.', 'Acosta', '143', '', 'Charity Ward', '31', 'Female', '', '', '', ''),
('5750212', 'Lyndon', 'Dela Peña', 'Lim', '1', '', 'Pay Ward', '20', 'Male', '', '', '', ''),
('5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', '', 'Private', '21', 'Female', '', '', '', ''),
('7795274', 'Mary Rose', 'Gonzalez', 'Rodriguez', '1', '', 'Charity Ward', '20', 'Female', '', '', '', ''),
('7916857', 'Eren', 'B.', 'Jaeger', '7', '', 'Pay Ward', '21', 'Male', '', '', '', ''),
('7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', '', 'Private', '23', 'Female', '', '', '', ''),
('8906609', 'Franz', 'Aquino', 'Agustin', '3', '', 'Charity Ward', '30', 'Female', '', '', '', ''),
('8947372', 'Lanie', 'Aguilar', 'Dimafilis', '2', '', 'Pay Ward', '28', 'Female', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `partial_billing_history`
--

CREATE TABLE IF NOT EXISTS `partial_billing_history` (
  `pbid` int(11) NOT NULL,
  `patient_id` varchar(50) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `middle_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `ward_no` varchar(50) NOT NULL,
  `ward_charge` varchar(50) NOT NULL,
  `ward_type` varchar(50) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `num_days` varchar(50) NOT NULL,
  `doctor_charge` varchar(50) NOT NULL,
  `other_charge` varchar(50) NOT NULL,
  `partial_bill` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `partial_billing_history`
--

INSERT INTO `partial_billing_history` (`pbid`, `patient_id`, `first_name`, `middle_name`, `last_name`, `ward_no`, `ward_charge`, `ward_type`, `age`, `gender`, `num_days`, `doctor_charge`, `other_charge`, `partial_bill`) VALUES
(1, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '0.0', '0.0', '0.0'),
(2, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '0.0', '0.0', '0.0'),
(3, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '0.0', '0.0', '0.0'),
(4, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '0.0', '0.0', '0.0'),
(5, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '0.0', '0.0', '0.0'),
(6, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '6.0', '0.0', '0.0', '0.0'),
(7, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '500.0', 'Charity Ward', '31', 'Female', '6.0', '500.0', '500.0', '4000.0'),
(8, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '500.0', 'Charity Ward', '31', 'Female', '6.0', '500.0', '500.0', '4000.0'),
(9, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', '500.0', 'Private', '23', 'Female', '5.0', '500.0', '500.0', '3500.0'),
(10, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', '500.0', 'Private', '23', 'Female', '2.0', '500.0', '500.0', '2000.0'),
(11, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', '1', '500.0', 'Private', '23', 'Female', '3.0', '500.0', '500.0', '2500.0'),
(12, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '500.0', '2500.0', '3000.0'),
(13, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '250.0', 'Pay Ward', '31', 'Female', '5.0', '500.0', '2500.0', '4250.0'),
(14, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '500.0', 'Private', '31', 'Female', '5.0', '500.0', '2500.0', '5500.0'),
(15, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '500.0', '500.0', '1000.0'),
(16, '7916857', 'Eren', 'B.', 'Jaeger', '7', '250.0', 'Pay Ward', '21', 'Male', '5.0', '500.0', '200.0', '1950.0'),
(17, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '500.0', '1500.0', '2000.0'),
(18, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '1500.0', '2500.0', '4000.0'),
(19, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '900.0', '900.0', '900.0', '1800.0'),
(20, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '900.0', '900.0', '1800.0'),
(21, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', '500.0', 'Private', '21', 'Female', '5.0', '0.0', '0.0', '2500.0'),
(22, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', '500.0', 'Private', '21', 'Female', '5.0', '50092.0', '825820.0', '878412.0'),
(23, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '5.0', '500.0', '500.0', '1000.0'),
(24, '5590681', 'Kathryn', 'P.', 'Acosta', '143', '0.0', 'Charity Ward', '31', 'Female', '0.0', '0.0', '0.0', '0.0'),
(25, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', '7', '500.0', 'Private', '21', 'Female', '3.0', '2500.0', '5000.0', '9000.0');

-- --------------------------------------------------------

--
-- Table structure for table `patient_info`
--

CREATE TABLE IF NOT EXISTS `patient_info` (
  `patient_count` int(11) NOT NULL,
  `patient_id` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_month` varchar(20) NOT NULL,
  `birth_day` varchar(20) NOT NULL,
  `birth_year` varchar(20) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `birthplace` varchar(200) NOT NULL,
  `civil_status` varchar(50) NOT NULL,
  `occupation` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `nationality` varchar(50) NOT NULL,
  `religion` varchar(50) NOT NULL,
  `ward` varchar(50) NOT NULL,
  `ward_type` varchar(50) NOT NULL,
  `bed_no` varchar(50) NOT NULL,
  `blood_type` varchar(50) NOT NULL,
  `blood_pressure` varchar(50) NOT NULL,
  `diagnosis` varchar(2000) NOT NULL,
  `father` varchar(250) NOT NULL,
  `mother` varchar(250) NOT NULL,
  `parents_address` varchar(2000) NOT NULL,
  `parents_contact` varchar(20) NOT NULL,
  `spouse` varchar(50) NOT NULL,
  `spouse_address` varchar(250) NOT NULL,
  `spouse_contact` varchar(20) NOT NULL,
  `h_plan` varchar(250) NOT NULL,
  `insurance` varchar(250) NOT NULL,
  `dependency` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient_info`
--

INSERT INTO `patient_info` (`patient_count`, `patient_id`, `first_name`, `middle_name`, `last_name`, `birth_month`, `birth_day`, `birth_year`, `age`, `gender`, `birthplace`, `civil_status`, `occupation`, `address`, `nationality`, `religion`, `ward`, `ward_type`, `bed_no`, `blood_type`, `blood_pressure`, `diagnosis`, `father`, `mother`, `parents_address`, `parents_contact`, `spouse`, `spouse_address`, `spouse_contact`, `h_plan`, `insurance`, `dependency`) VALUES
(8, '1360079', 'Miel', 'Francisco', 'Garcia', 'December', '6', '1988', '29', 'Female', 'Dagupan City, Pangasinan', 'Divorced', 'Nurse', 'Sison, Pangasinan', 'Filipino', 'Born Again Christian', '3', 'Charity Ward', '4', 'AB', '160/90', 'Chest Pain', '', '', '', '', '', '', '', 'None', 'PhilHealth', 'SSS Dependent'),
(13, '1596192', 'Alexandra', 'Aquino', 'Santos', 'June', '4', '2010', '6', 'Female', 'Mangaldan, Pangasinan', 'Single', 'None', 'Mangaldan, Pangasinan', 'Filipino', 'Roman Catholic', '6', 'Private', '1', 'B', '90/60', 'Diarrhea', 'Alex Santos', 'Rea Santos', 'Mangaldan, Pangasinan', '09997845376', '', '', '', 'None', 'None', 'PhilHealth Dependent'),
(11, '2510850', 'Christine', 'Santiago', 'Pagtalunan', 'July', '3', '1999', '17', 'Female', 'Nalsian, Manaoag, Pangasinan', 'Single', 'Student', 'Nalsian, Manaoag, Pangasinan', 'Filipino', 'Roman Catholic', '2', 'Charity Ward', '1', 'B', '90/60', 'Dengue', 'Macario Pagtalunan', 'Evelyn Pagtalunan', 'Nalsian, Manaoag, Pangasinan', '09056781234', '', '', '', 'None', 'PhilHealth', 'PhilHealth Dependent'),
(5, '3027193', 'Ziano', 'Flores', 'Ramirez', 'March', '1', '1990', '27', 'Male', 'Manaoag, Pangasinan', 'Separated', 'Fisherman', 'Anda, Pangasinan', 'Filipino', 'Roman Catholic', '1', 'Private', '2', 'AB', '130/70', 'Skin Problem', 'Franco Ramirez', 'Fe Ramirez', 'Manaoag, Pangasinan', '0934561232', 'Grace Ramirez', 'Urdaneta, Pangasinan', '09765432348', 'None', 'PhilHealth', 'PhilHealth Dependent'),
(6, '3065741', 'Alonzo', 'Soriano', 'Cruz', 'March', '12', '1998', '19', 'Male', 'Urdaneta, Pangasinan', 'Single', 'None', 'Urdaneta, Pangasinan', 'Filipino', 'Iglesia NI Cristo', '3', 'Charity Ward', '4', 'B', '120/60', 'Redness of Eyes', 'Miguel Cruz', 'Grace Cruz', 'Urdaneta, Pangasinan', '09321235679', '', '', '', 'None', 'None', 'PhilHealth Dependent'),
(4, '5590681', 'Kathryn', 'P.', 'Acosta', 'May', '1', '1981', '31', 'Female', 'Manaoag, Pangasinan', 'Married', 'Instructor', 'Manaoag', 'Filipino', 'Catholic', '143', 'Charity Ward', '2', 'AB', '120/80', 'Headache', '', '', '', '', '', '', '', '', 'PhilHealth', 'SSS Dependent'),
(9, '5750212', 'Lyndon', 'Dela Peña', 'Lim', 'October', '10', '1996', '20', 'Male', 'Sison, Pangasinan', 'Single', 'Student', 'Sison, Pangasinan', 'Filipino', 'Roman Catholic', '1', 'Pay Ward', '3', 'O', '120/60', 'Appendix', 'Anastacio Lim', 'Bea Lim', 'Sison, Pangasinan', '09347876544', '', '', '', 'None', 'PhilHealth', 'PhilHealth Dependent'),
(3, '5811689', 'Moriel Kim', 'B.', 'Mana-ay', 'December', '15', '1995', '21', 'Female', 'Baritao Manaoag, Pangasinan', 'Married', 'Teacher', 'Baritao, Manaoag, Pangasinan', 'Filipino', 'Roman Catholic', '7', 'Private', '2', 'AB', '120/80', 'Birth Delivery', 'Honorio Mana-ay', 'Rosalinda Mana-ay', '', '', 'Michael Cris Rosalin ', 'Poblacion, Manaoag, Pangasinan', '09486861225', '', 'PhilHealth', 'SSS Dependent'),
(10, '7795274', 'Mary Rose', 'Gonzalez', 'Rodriguez', 'March', '4', '1996', '20', 'Female', 'San Ramon, Manaoag, Pangasinan', 'Single', 'None', 'San Ramon, Manaoag, Pangasinan', 'Filipino', 'Roman Catholic', '1', 'Charity Ward', '3', 'O', '100/70', 'Skin Cancer', 'Romeo Rodriguez', 'Marlyn Rodriguez', 'San Ramon, Manaoag, Pangasinan', '09081234679', '', '', '', 'None', 'PhilHealth', 'SSS Dependent'),
(1, '7916857', 'Eren', 'B.', 'Jaeger', 'June', '15', '1995', '21', 'Male', 'Baritao Manaoag, Pangasinan', 'Married', 'Teacher', 'Baritao, Manaoag, Pangasinan', 'Filipino', 'Roman Catholic', '7', 'Pay Ward', '1', 'AB', '120/80', 'Headache', '', '', '', '', '', '', '', '', 'PhilHealth', 'PhilHealth Dependent'),
(2, '7934156', 'Jamaica', 'Ferrer', 'Rosalin', 'October', '24', '1993', '23', 'Female', 'Lingayen, Pangasinan', 'Married', 'English Teacher', 'LIngayen, Pangasinan', 'Filipino', 'Roman Catholic', '1', 'Private', '2', 'A', '120/80', 'Birth Delivery', 'Mr. Ferrer', 'Mrs. Ferrer', 'Lingayen, Pangasinan', '', 'Eyeshield Rosalin', 'Manaoag, Pangasinan', '09486861225', '', 'PhilHealth', 'SSS Dependent'),
(7, '8906609', 'Franz', 'Aquino', 'Agustin', 'February', '3', '1986', '30', 'Female', 'Dagupan City, Pangasinan', 'Married', 'Teacher', 'Dagupan City, Pangasinan', 'Filipino', 'Roman Catholic', '3', 'Charity Ward', '4', 'A', '160/70', 'Redness of The Skin', 'Filipe Aquino', 'Ranze Aquino', 'Urdaneta, Pangasinan', '09127454783', 'Michael Agustin', 'Dagupan City, Pangasinan', '09121374646', 'None', 'PhilHealth', 'GSIS Dependent'),
(12, '8947372', 'Lanie', 'Aguilar', 'Dimafilis', 'May', '6', '1986', '28', 'Female', 'Oraan East, Manaoag, Pangasinan', 'Single', 'Factory Worker', 'Oraan East, Manaoag, Pangasinan', 'Filipino', 'Roman Catholic', '2', 'Pay Ward', '1', 'A', '100/80', 'Kidney Problem', 'Ronie Dimafilis', 'Vangie Dimafilis', 'Oraan East, Manaoag, Pangasinan', '09356789433', '', '', '', 'None', 'PhilHealth', 'SSS Dependent');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE IF NOT EXISTS `pharmacy` (
  `medicine_count` int(11) NOT NULL,
  `medicine_code` varchar(50) NOT NULL,
  `company_name` varchar(500) NOT NULL,
  `brand_name` varchar(500) NOT NULL,
  `generic_name` varchar(500) NOT NULL,
  `quantity` text NOT NULL,
  `price` text NOT NULL,
  `description` varchar(2000) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy`
--

INSERT INTO `pharmacy` (`medicine_count`, `medicine_code`, `company_name`, `brand_name`, `generic_name`, `quantity`, `price`, `description`) VALUES
(1, '1', 'Mercury Drug', 'Ambroxol', 'Mucosolvan,Ambrolex,Zobrixol', '20', '30', 'Cough and Colds'),
(2, '2', 'Zuellig Pharma Corp.', 'Ambroxol 30mg/ 60ml Syrup', 'Mucosolvan,Ambrolex,Zobrixol', '25', '35.50', 'Cough and colds'),
(3, '3', 'Wyeth Phil. Inc.', 'Carbocisteine 500mg Cap', 'Loviscol,Solmux,phlegmol', '30', '2.75', 'Cough and Colds'),
(4, '4', 'Metro Drug Inc.', 'Guiafenesin 100mg/ 60ml Syrup', 'Robitussin , Benadryl Exp, Transpulmin-G', '30', '26.75', 'Cough and Colds'),
(5, '5', 'Bristol-Myers Squibb (Phil.) Inc.', 'Phenyl+Para+Dextro Tab', 'Tuseran', '35', '7.50', 'Coughs and Colds'),
(6, '6', 'GlaxoSmithKline Philippines', 'Mefenamic Acid 250mg Cap', 'Ponstan,Gardan, Dolfenal', '30', '1.50', 'Fever and Body Pain'),
(7, '7', 'Abbott Laboratories', 'Mefenamic Acid 500mg Cap.', 'Ponstan,Gardan,Dolfenal', '35', '2.00', 'Fever and Body Pain'),
(8, '8', 'Pfizer Inc.', 'Paracetamol 250mg/60ml Syrup', 'Tempra Forte, Biogesic, Calpol', '40', '30', 'Fever and Body Pain'),
(9, '9', 'Sanofi-Avents Phil. Inc.', 'Paracetamol 500mgTab', 'Tempra Forte, Biogesic, Calpol', '20', '0.75', 'Fever and Body Pain'),
(10, '10', 'Boehringer Inglheim (Phil.)Inc.', 'Paracetamol+Ibup Cap (ENERLAX)', 'Alaxan Fr, Restolax, Muskelox', '40', '2.75', 'Fever and Body Pain'),
(11, '11', 'Novartis  Healthcare Phil. Inc.', 'Salbutamol 2mg/60ml Syrup', 'Ventolin, Asfrenon, Librentin', '20', '28', 'Asthma'),
(12, '12', 'Bayer Phil. Inc.', 'Salbutamol 2mg tab', 'Ventolin, Asfrenon, Asmalin', '20', '0.75', 'ASTHMA'),
(13, '13', 'Interphil Laboratories Inc.', 'Salbutamol Nebules ', 'Asmalin,Ventolin Nebules', '20', '8.50', 'ASTHMA'),
(14, '14', 'Schering-Plough Corporation', 'Salbu+Guiafenesin 100ml Syrup', 'Ventolin,Expectorant,Pulmovent', '20', '34.25', 'ASTHMA'),
(15, '', '', '', '', '', '0', '');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy_billing`
--

CREATE TABLE IF NOT EXISTS `pharmacy_billing` (
  `id` int(11) NOT NULL,
  `transaction` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy_patient_billing`
--

CREATE TABLE IF NOT EXISTS `pharmacy_patient_billing` (
  `patient_id` varchar(50) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `middle_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `occupation` varchar(2000) NOT NULL,
  `status` varchar(50) NOT NULL,
  `bill` varchar(50) NOT NULL,
  `payment` varchar(50) NOT NULL,
  `balance` varchar(50) NOT NULL,
  `meds1` varchar(500) NOT NULL,
  `meds2` varchar(500) NOT NULL,
  `meds3` varchar(500) NOT NULL,
  `meds4` varchar(500) NOT NULL,
  `meds5` varchar(500) NOT NULL,
  `meds6` varchar(500) NOT NULL,
  `meds7` varchar(500) NOT NULL,
  `meds8` varchar(500) NOT NULL,
  `meds9` varchar(500) NOT NULL,
  `meds10` varchar(500) NOT NULL,
  `quantity1` varchar(50) NOT NULL,
  `quantity2` varchar(50) NOT NULL,
  `quantity3` varchar(50) NOT NULL,
  `quantity4` varchar(50) NOT NULL,
  `quantity5` varchar(50) NOT NULL,
  `quantity6` varchar(50) NOT NULL,
  `quantity7` varchar(50) NOT NULL,
  `quantity8` varchar(50) NOT NULL,
  `quantity9` varchar(50) NOT NULL,
  `quantity10` varchar(50) NOT NULL,
  `price1` varchar(50) NOT NULL,
  `price2` varchar(50) NOT NULL,
  `price3` varchar(50) NOT NULL,
  `price4` varchar(50) NOT NULL,
  `price5` varchar(50) NOT NULL,
  `price6` varchar(50) NOT NULL,
  `price7` varchar(50) NOT NULL,
  `price8` varchar(50) NOT NULL,
  `price9` varchar(50) NOT NULL,
  `price10` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy_patient_billing`
--

INSERT INTO `pharmacy_patient_billing` (`patient_id`, `first_name`, `middle_name`, `last_name`, `age`, `gender`, `address`, `occupation`, `status`, `bill`, `payment`, `balance`, `meds1`, `meds2`, `meds3`, `meds4`, `meds5`, `meds6`, `meds7`, `meds8`, `meds9`, `meds10`, `quantity1`, `quantity2`, `quantity3`, `quantity4`, `quantity5`, `quantity6`, `quantity7`, `quantity8`, `quantity9`, `quantity10`, `price1`, `price2`, `price3`, `price4`, `price5`, `price6`, `price7`, `price8`, `price9`, `price10`) VALUES
('1360079', 'Miel', 'Francisco', 'Garcia', '29', 'Female', 'Sison, Pangasinan', 'Nurse', 'Divorced', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('1596192', 'Alexandra', 'Aquino', 'Santos', '6', 'Female', 'Mangaldan, Pangasinan', 'None', 'Single', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('2510850', 'Christine', 'Santiago', 'Pagtalunan', '17', 'Female', 'Nalsian, Manaoag, Pangasinan', 'Student', 'Single', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('3027193', 'Ziano', 'Flores', 'Ramirez', '27', 'Male', 'Anda, Pangasinan', 'Fisherman', 'Separated', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('3065741', 'Alonzo', 'Soriano', 'Cruz', '19', 'Male', 'Urdaneta, Pangasinan', 'None', 'Single', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('5590681', 'Kathryn', 'P.', 'Acosta', '31', 'Female', 'Manaoag', 'Instructor', 'Married', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('5750212', 'Lyndon', 'Dela Peña', 'Lim', '20', 'Male', 'Sison, Pangasinan', 'Student', 'Single', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('5811689', 'Moriel Kim', 'B.', 'Mana-ay', '21', 'Female', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Married', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('7795274', 'Mary Rose', 'Gonzalez', 'Rodriguez', '20', 'Female', 'San Ramon, Manaoag, Pangasinan', 'None', 'Single', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('7916857', 'Eren', 'B.', 'Jaeger', '21', 'Male', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Married', 'P 3.0', '500', 'P ', 'Paracetamol 500mgTab', '', '', '', '', '', '', '', '', '', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0.75', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('7934156', 'Jamaica', 'Ferrer', 'Rosalin', '23', 'Female', 'LIngayen, Pangasinan', 'English Teacher', 'Married', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('8906609', 'Franz', 'Aquino', 'Agustin', '30', 'Female', 'Dagupan City, Pangasinan', 'Teacher', 'Married', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
('8947372', 'Lanie', 'Aguilar', 'Dimafilis', '28', 'Female', 'Oraan East, Manaoag, Pangasinan', 'Factory Worker', 'Single', '', '', '', '', '', '', '', '', '', '', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy_patient_billing_history`
--

CREATE TABLE IF NOT EXISTS `pharmacy_patient_billing_history` (
  `ppbhid` int(11) NOT NULL,
  `patient_id` varchar(50) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `middle_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `age` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `address` text NOT NULL,
  `occupation` varchar(2000) NOT NULL,
  `status` varchar(50) NOT NULL,
  `bill` varchar(50) NOT NULL,
  `payment` varchar(50) NOT NULL,
  `balance` varchar(50) NOT NULL,
  `meds1` varchar(500) NOT NULL,
  `meds2` varchar(500) NOT NULL,
  `meds3` varchar(500) NOT NULL,
  `meds4` varchar(500) NOT NULL,
  `meds5` varchar(500) NOT NULL,
  `meds6` varchar(500) NOT NULL,
  `meds7` varchar(500) NOT NULL,
  `meds8` varchar(500) NOT NULL,
  `meds9` varchar(500) NOT NULL,
  `meds10` varchar(500) NOT NULL,
  `quantity1` varchar(50) NOT NULL,
  `quantity2` varchar(50) NOT NULL,
  `quantity3` varchar(50) NOT NULL,
  `quantity4` varchar(50) NOT NULL,
  `quantity5` varchar(50) NOT NULL,
  `quantity6` varchar(50) NOT NULL,
  `quantity7` varchar(50) NOT NULL,
  `quantity8` varchar(50) NOT NULL,
  `quantity9` varchar(50) NOT NULL,
  `quantity10` varchar(50) NOT NULL,
  `price1` varchar(50) NOT NULL,
  `price2` varchar(50) NOT NULL,
  `price3` varchar(50) NOT NULL,
  `price4` varchar(50) NOT NULL,
  `price5` varchar(50) NOT NULL,
  `price6` varchar(50) NOT NULL,
  `price7` varchar(50) NOT NULL,
  `price8` varchar(50) NOT NULL,
  `price9` varchar(50) NOT NULL,
  `price10` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy_patient_billing_history`
--

INSERT INTO `pharmacy_patient_billing_history` (`ppbhid`, `patient_id`, `first_name`, `middle_name`, `last_name`, `age`, `gender`, `address`, `occupation`, `status`, `bill`, `payment`, `balance`, `meds1`, `meds2`, `meds3`, `meds4`, `meds5`, `meds6`, `meds7`, `meds8`, `meds9`, `meds10`, `quantity1`, `quantity2`, `quantity3`, `quantity4`, `quantity5`, `quantity6`, `quantity7`, `quantity8`, `quantity9`, `quantity10`, `price1`, `price2`, `price3`, `price4`, `price5`, `price6`, `price7`, `price8`, `price9`, `price10`) VALUES
(1, '7916857', 'Moriel Kim', 'B.', 'Mana-ay', '21', 'Female', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Married', 'P 3.0', '500', 'P ', 'Paracetamol 500mgTab', '', '', '', '', '', '', '', '', '', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0.75', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
(2, '7916857', 'Moriel Kim', 'B.', 'Mana-ay', '21', 'Female', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Married', 'P 3.0', '500', 'P ', 'Paracetamol 500mgTab', '', '', '', '', '', '', '', '', '', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0.75', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
(3, '7916857', 'Moriel Kim', 'B.', 'Mana-ay', '21', 'Female', 'Baritao, Manaoag, Pangasinan', 'Teacher', 'Married', 'P 3.0', '500', 'P ', 'Paracetamol 500mgTab', '', '', '', '', '', '', '', '', '', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0.75', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `question` longtext NOT NULL,
  `answer` longtext NOT NULL,
  `block` int(10) NOT NULL,
  `user_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `question`, `answer`, `block`, `user_type`) VALUES
('brain', 'purganan', 'what is my favorite song', 'closer', 0, 'Admin'),
('chariz', 'chariz12', 'who is the secret character in the movie another', 'reiko minami', 0, 'Cashier'),
('eyeshield', 'rosalin', 'what is my favorite angel type', 'grimangel', 0, 'Admin'),
('ginalyn', 'salon', 'what is your pin code', '12-001215', 0, 'Treasurer'),
('herbs', 'gamboa', 'who is the female protagonist in re-zero in another world', 'satella', 0, 'Admin'),
('Jericoh', 'gutierrez', 'where is the birthplace of my mother', 'bauang, la union', 0, 'Data Encoder'),
('letran', 'letran', 'what is the best school in the philippines', 'letran-manaoag', 0, 'Admin'),
('moriel', 'mana-ay', 'who is my inspiration', 'eyeshield rosalin', 0, 'Admin'),
('pedro', 'malanum', 'who is the protagonist of accel world', 'arita hayumu', 0, 'Data Encoder'),
('rolan', 'faith', 'who is the leader of oujou white knights', 'shin seijuro', 0, 'Info Officer'),
('ryn', 'aquino', 'when was our last family reunion', 'december 31 2014', 0, 'Pharmacist'),
('shaira', 'singh', 'what is the name of my first dog', 'joel', 0, 'Nurse'),
('shiten', 'tenshi', 'who is the hero in black bullet', 'rentaro', 0, 'Doctor');

-- --------------------------------------------------------

--
-- Table structure for table `users_log`
--

CREATE TABLE IF NOT EXISTS `users_log` (
  `ul_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `user_type` varchar(50) NOT NULL,
  `date` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_log`
--

INSERT INTO `users_log` (`ul_id`, `username`, `user_type`, `date`) VALUES
(1, 'shiten', 'Doctor', '02/16/2017 11:37:8'),
(2, 'shiten', 'Doctor', '02/17/2017 2:48:51'),
(3, 'ryn', 'Pharmacist', '02/17/2017 2:49:6'),
(4, 'ginalyn', 'Treasurer', '02/17/2017 2:49:18'),
(5, 'moriel', 'Admin', '02/17/2017 2:49:45'),
(6, 'brain', 'Admin', '02/20/2017 9:38:36'),
(7, 'brain', 'Admin', '02/20/2017 9:42:17'),
(8, 'brain', 'Admin', '02/20/2017 9:50:26'),
(9, 'jhe', 'Data Encoder', '02/20/2017 10:13:8'),
(10, 'letran', 'Admin', '02/20/2017 10:13:32'),
(11, 'moriel', 'Admin', '02/21/2017 7:7:3'),
(12, 'moriel', 'Admin', '02/21/2017 10:36:35'),
(13, 'moriel', 'Admin', '02/22/2017 10:52:13'),
(14, 'letran', 'Admin', '02/22/2017 11:3:4'),
(15, 'letran', 'Admin', '02/22/2017 11:5:47'),
(16, 'letran', 'Admin', '02/22/2017 11:6:25'),
(17, 'letran', 'Admin', '02/22/2017 11:8:28'),
(18, 'letran', 'Admin', '02/22/2017 11:9:37'),
(19, 'letran', 'Admin', '02/22/2017 11:10:59'),
(20, 'letran', 'Admin', '02/22/2017 1:56:4'),
(21, 'letran', 'Admin', '02/22/2017 2:0:48'),
(22, 'letran', 'Admin', '02/22/2017 2:2:16'),
(23, 'letran', 'Admin', '02/22/2017 2:27:12'),
(24, 'jhe', 'Data Encoder', '02/22/2017 2:58:9'),
(25, 'letran', 'Admin', '02/22/2017 3:3:36'),
(26, 'letran', 'Admin', '02/22/2017 3:56:43'),
(27, 'letran', 'Admin', '02/22/2017 4:2:55'),
(28, 'letran', 'Admin', '02/22/2017 4:7:51'),
(29, 'letran', 'Admin', '02/22/2017 4:9:51'),
(30, 'letran', 'Admin', '02/22/2017 4:16:3'),
(31, 'chariz', 'Cashier', '02/23/2017 3:12:40'),
(32, 'letran', 'Admin', '03/02/2017 12:6:24'),
(33, 'letran', 'Admin', '03/02/2017 1:8:28'),
(34, 'moriel', 'Admin', '03/07/2017 8:30:39');

-- --------------------------------------------------------

--
-- Table structure for table `ward`
--

CREATE TABLE IF NOT EXISTS `ward` (
  `ward_id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `number` varchar(50) NOT NULL,
  `num_beds` varchar(50) NOT NULL,
  `bed_no` varchar(50) NOT NULL,
  `doctor_experties` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price_per_day` varchar(50) NOT NULL,
  `availability` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ward`
--

INSERT INTO `ward` (`ward_id`, `type`, `number`, `num_beds`, `bed_no`, `doctor_experties`, `description`, `price_per_day`, `availability`) VALUES
(1, 'Private', '1', '5', '2', 'Surgery', 'Surgery Ward', '500', 'Not Available'),
(2, 'Pay Ward', '1', '3', '3', 'Theraphy', 'Theraphy Ward', '250', 'Not Available'),
(3, 'Charity Ward', '3', '2', '4', 'Surgery', 'Surgery Ward', '0', 'Not Available'),
(4, 'Private', '143', '5', '5', 'Midwifery', 'Midwifery Ward', '500', 'Not Available'),
(5, 'Charity Ward', '143', '5', '2', 'Midwifery', 'Midwifery Ward', '0', 'Not Available'),
(6, 'Private', '021', '5', '5', 'Neurology', 'Neuro Surgery Ward', '500', 'Available'),
(7, 'Private', '5', '5', '3', 'Physician', 'Physician Ward', '500', 'Available'),
(8, 'Pay Ward', '6', '4', '3', 'Physician', 'Physician Ward', '250', 'Available'),
(9, 'Private', '7', '1', '2', 'Surgery', 'Surgery Ward', '500', 'Not Available'),
(10, 'Pay Ward', '7', '3', '1', 'Surgery', 'Surgery Ward', '250', 'Not Available'),
(11, 'Charity Ward', '4', '5', '3', 'Surgery', 'Surgery Ward', '0', 'Available'),
(12, 'Private', '2', '1', '1', 'None', 'Private Ward', '500', 'Available'),
(13, 'Private', '3', '1', '1', 'None', 'Private Ward', '500', 'Available'),
(14, 'Private', '4', '1', '1', 'None', 'Private Ward', '500', 'Available'),
(15, 'Private', '6', '1', '1', 'None', 'Private Ward', '500', 'Not Available'),
(16, 'Pay Ward', '1', '3', '1', 'Theraphy', 'Theraphy Ward', '250', 'Available'),
(17, 'Pay Ward', '1', '3', '2', 'Theraphy', 'Theraphy Ward', '250', 'Available'),
(18, 'Pay Ward', '6', '4', '4', 'Physician', 'Physician Ward', '250', 'Available'),
(19, 'Pay Ward', '6', '4', '2', 'Physician', 'Physician Ward', '250', 'Available'),
(20, 'Pay Ward', '6', '4', '1', 'Physician', 'Physician Ward', '250', 'Available'),
(21, 'Pay Ward', '2', '3', '1', 'None', 'Pay Ward', '250', 'Not Available'),
(22, 'Pay Ward', '2', '3', '2', 'None', 'Pay Ward', '250', 'Available'),
(23, 'Pay Ward', '2', '3', '3', 'None', 'Pay Ward', '250', 'Available'),
(24, 'Charity Ward', '1', '3', '1', 'None', 'Charity Ward', '0', 'Available'),
(25, 'Charity Ward', '1', '3', '2', 'None', 'Charity Ward', '0', 'Available'),
(26, 'Charity Ward', '1', '3', '3', 'None', 'Charity Ward', '0', 'Not Available'),
(27, 'Charity Ward', '3', '2', '4', 'Surgery', 'Surgery Ward', '0', 'Not Available'),
(28, 'Charity Ward', '2', '4', '1', 'None', 'Charity Ward', '0', 'Not Available'),
(29, 'Charity Ward', '2', '4', '2', 'None', 'Charity Ward', '0', 'Available'),
(30, 'Charity Ward', '2', '4', '3', 'None', 'Charity Ward', '0', 'Available'),
(31, 'Charity Ward', '2', '4', '4', 'None', 'Charity Ward', '0', 'Available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admission`
--
ALTER TABLE `admission`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `admission_history`
--
ALTER TABLE `admission_history`
  ADD PRIMARY KEY (`aid`);

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `billing_history`
--
ALTER TABLE `billing_history`
  ADD PRIMARY KEY (`bid`);

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `consultation_history`
--
ALTER TABLE `consultation_history`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `discharge`
--
ALTER TABLE `discharge`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `discharge_history`
--
ALTER TABLE `discharge_history`
  ADD PRIMARY KEY (`did`);

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`doctor_id`),
  ADD KEY `doctor_count` (`doctor_count`);

--
-- Indexes for table `doctors_schedule`
--
ALTER TABLE `doctors_schedule`
  ADD PRIMARY KEY (`schedule_id`);

--
-- Indexes for table `partial_billing`
--
ALTER TABLE `partial_billing`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `partial_billing_history`
--
ALTER TABLE `partial_billing_history`
  ADD PRIMARY KEY (`pbid`);

--
-- Indexes for table `patient_info`
--
ALTER TABLE `patient_info`
  ADD PRIMARY KEY (`patient_id`),
  ADD KEY `patient_count` (`patient_count`);

--
-- Indexes for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD PRIMARY KEY (`medicine_count`);

--
-- Indexes for table `pharmacy_billing`
--
ALTER TABLE `pharmacy_billing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pharmacy_patient_billing`
--
ALTER TABLE `pharmacy_patient_billing`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `pharmacy_patient_billing_history`
--
ALTER TABLE `pharmacy_patient_billing_history`
  ADD PRIMARY KEY (`ppbhid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `users_log`
--
ALTER TABLE `users_log`
  ADD PRIMARY KEY (`ul_id`);

--
-- Indexes for table `ward`
--
ALTER TABLE `ward`
  ADD PRIMARY KEY (`ward_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admission_history`
--
ALTER TABLE `admission_history`
  MODIFY `aid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `billing_history`
--
ALTER TABLE `billing_history`
  MODIFY `bid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT for table `consultation_history`
--
ALTER TABLE `consultation_history`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `discharge_history`
--
ALTER TABLE `discharge_history`
  MODIFY `did` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `doctor_count` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `doctors_schedule`
--
ALTER TABLE `doctors_schedule`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `partial_billing_history`
--
ALTER TABLE `partial_billing_history`
  MODIFY `pbid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `patient_info`
--
ALTER TABLE `patient_info`
  MODIFY `patient_count` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `pharmacy`
--
ALTER TABLE `pharmacy`
  MODIFY `medicine_count` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `pharmacy_billing`
--
ALTER TABLE `pharmacy_billing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `pharmacy_patient_billing_history`
--
ALTER TABLE `pharmacy_patient_billing_history`
  MODIFY `ppbhid` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users_log`
--
ALTER TABLE `users_log`
  MODIFY `ul_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `ward`
--
ALTER TABLE `ward`
  MODIFY `ward_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
