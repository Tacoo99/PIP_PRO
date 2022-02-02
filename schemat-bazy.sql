-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 02 Lut 2022, 12:37
-- Wersja serwera: 10.4.22-MariaDB
-- Wersja PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `magazyn`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `admins`
--

CREATE TABLE `admins` (
  `ID` int(11) NOT NULL,
  `Login` varchar(20) NOT NULL,
  `Haslo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `admins`
--

INSERT INTO `admins` (`ID`, `Login`, `Haslo`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `clients`
--

CREATE TABLE `clients` (
  `ID` int(11) NOT NULL,
  `login` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL,
  `acc_creation` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `clients`
--

INSERT INTO `clients` (`ID`, `login`, `email`, `password`, `name`, `surname`, `acc_creation`) VALUES
(8, 'klient', 'klient@poczta.com', 'klient', 'Jan', 'Błachowicz', '29-12-2021'),
(10, 'renata', 'rena@mail.com', 'rena', 'rena', 'rena', '07-01-2022');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `ID` int(11) NOT NULL,
  `klient` varchar(256) NOT NULL,
  `sn_sprzetu` varchar(60) NOT NULL,
  `data_zamowienia` varchar(254) NOT NULL,
  `godzina_zamowienia` varchar(254) NOT NULL,
  `do_kiedy` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`ID`, `klient`, `sn_sprzetu`, `data_zamowienia`, `godzina_zamowienia`, `do_kiedy`) VALUES
(1, 'klient', 'sn-asd-123-fg', '30.01.2022', '22:53:23', '2022-01-31'),
(2, 'klient', 'CN-00-F4GHBSADF', '31.01.2022', '17:56:48', '2022-01-11'),
(3, 'klient', 'CN-00-F4GHBSADF', '31.01.2022', '18:11:50', '2022-01-24'),
(5, 'klient', 'SN-FDG-212-ASD', '01.02.2022', '19:12:50', '2022-02-23'),
(6, 'klient', 'CN-00-F4GHBSADF', '02.02.2022', '09:18:16', '2022-02-03');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pendingorders`
--

CREATE TABLE `pendingorders` (
  `ID` int(11) NOT NULL,
  `klient` varchar(256) NOT NULL,
  `sn_sprzetu` varchar(60) NOT NULL,
  `data_zamowienia` varchar(254) NOT NULL,
  `godzina_zamowienia` varchar(254) NOT NULL,
  `do_kiedy` varchar(254) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `pendingorders`
--

INSERT INTO `pendingorders` (`ID`, `klient`, `sn_sprzetu`, `data_zamowienia`, `godzina_zamowienia`, `do_kiedy`) VALUES
(4, 'klient', 'CN-FRE-43FDG4', '01.02.2022', '18:41:53', '2022-02-02'),
(7, 'klient', 'SN-234324-SF', '02.02.2022', '09:36:27', '2022-02-03');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stock`
--

CREATE TABLE `stock` (
  `ID` int(11) NOT NULL,
  `SN` varchar(20) NOT NULL,
  `Producent` varchar(30) NOT NULL,
  `Model` varchar(20) NOT NULL,
  `Typ` varchar(10) NOT NULL,
  `CPU` varchar(50) NOT NULL,
  `GPU` varchar(50) NOT NULL,
  `RAM` varchar(50) NOT NULL,
  `Dysk` varchar(50) NOT NULL,
  `Ekran` varchar(50) NOT NULL,
  `Dostepny` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `stock`
--

INSERT INTO `stock` (`ID`, `SN`, `Producent`, `Model`, `Typ`, `CPU`, `GPU`, `RAM`, `Dysk`, `Ekran`, `Dostepny`) VALUES
(1, 'CN-00-F4GHBSADF', 'DELL', 'INSPIRON', 'Laptop', 'I5', 'NVIDIA', 'DDR3', 'SSD', 'LED', '0'),
(2, 'CN-FRE-43FDG4', 'ASUS', 'VIVOBOOK', 'Laptop', 'RYZEN', 'RADEON VEGA', 'DDR4', 'HDD', 'LCD', '1'),
(3, 'SN-FDG-212-ASD', 'HP', 'L14', 'Tablet', 'ATOM', 'INTEL', '4000', '256', 'LCD', '1'),
(4, 'CN-SAD_DAS-123', 'DELL', 'L14', 'Telefon', 'ATOM', 'INTEL', '8', '60', 'IPS', '1'),
(5, 'SN-234324-SF', 'ASUS', 'Z14', 'Laptop', 'I5', 'INTEL', '8192', '256', '15,4', '0');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `pendingorders`
--
ALTER TABLE `pendingorders`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `admins`
--
ALTER TABLE `admins`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `clients`
--
ALTER TABLE `clients`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT dla tabeli `orders`
--
ALTER TABLE `orders`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `pendingorders`
--
ALTER TABLE `pendingorders`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `stock`
--
ALTER TABLE `stock`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
