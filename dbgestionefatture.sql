-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3310
-- Creato il: Giu 11, 2024 alle 23:30
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbgestionefatture`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `articoli`
--

CREATE TABLE `articoli` (
  `ID` int(11) NOT NULL,
  `Descrizione` varchar(100) NOT NULL,
  `PrezzoUnitario` decimal(10,2) NOT NULL,
  `Quantita` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `articoli`
--

INSERT INTO `articoli` (`ID`, `Descrizione`, `PrezzoUnitario`, `Quantita`) VALUES
(1, 'legno', 10.80, 1),
(2, 'VitiLegno', 0.89, 10),
(3, 'ViteTorx', 0.50, 10),
(4, 'Vitini', 0.80, 10),
(7, 'Macbook', 1300.00, 1),
(8, 'PC', 150.00, 1),
(9, 'Vestito', 10.00, 2),
(10, 'Tv', 1500.00, 1),
(11, 'playstation', 250.00, 1),
(12, 'tv', 240.00, 1),
(13, 'stereo', 150.00, 1),
(14, 'Consolle', 25.00, 1),
(15, 'Lamadonna', 1.00, 10),
(16, 'UnghiePatch', 10.00, 1),
(17, 'borsaGucci', 150.00, 1),
(18, 'Cucina', 105.00, 1),
(19, 'Bambina', 150.00, 1),
(20, 'mattia', 2.00, 1),
(21, 'Piscina', 120.00, 1),
(22, 'SamsungS10', 500.00, 1),
(23, 'SamsungS10', 500.00, 1),
(24, 'piatti', 100.00, 1),
(25, 'piatti', 100.00, 1),
(26, 'Bicchieri', 10.00, 2),
(27, 'Vasi', 20.00, 5),
(28, 'Bicchieri', 10.00, 2),
(29, 'Vasi', 20.00, 5),
(30, 'tavolo', 10.00, 1),
(31, 'sedia', 10.00, 1),
(32, 'tavolo', 10.00, 1),
(33, 'sedia', 10.00, 1),
(34, 'IphoneX', 1300.00, 1),
(35, 'IphoneX', 1300.00, 1),
(36, 'ApplePen', 1.00, 10),
(37, 'ApplePen', 1.00, 10),
(38, 'AppleWatch', 1.00, 1),
(39, 'AppleTv', 200.00, 1),
(40, 'AppleWatch', 1.00, 1),
(41, 'AppleTv', 200.00, 1),
(42, 'AppleMacBook', 1516.00, 1),
(43, 'AppleMacBook', 1516.00, 1),
(44, 'PenDrive', 15.00, 1),
(45, 'PenDrive', 15.00, 1),
(46, 'TastieraRgb', 20.00, 1),
(47, 'TastieraRgb', 20.00, 1),
(48, 'Airpods', 240.00, 1),
(49, 'Alexa', 49.90, 1),
(50, 'motodacross', 1500.00, 1),
(51, 'PcFisso', 1.00, 10),
(52, 'PcPortatile', 200.00, 1),
(53, 'Iphone', 1459.00, 1),
(54, 'IphoneX', 1300.00, 1),
(55, 'IphoneX', 1459.00, 1),
(56, 'IphoneX', 1459.00, 1),
(57, 'IphoneX', 1459.00, 1),
(58, 'Iphone', 1459.00, 1),
(59, 'IphoneX', 1459.00, 1),
(60, 'IphoneX', 1470.00, 1),
(61, 'Iphone8', 859.00, 1),
(62, 'PCFisso', 1200.00, 1),
(63, 'Tv', 1500.00, 1),
(64, 'PilaAA', 2.50, 1),
(65, 'PilaABC', 2.50, 1),
(66, 'PilaAAA', 2.50, 1),
(67, 'TvPhilips', 150.00, 1),
(68, 'Astuccio', 16.00, 1),
(69, 'PennaUsb', 16.00, 1),
(70, 'StampanteHp', 159.45, 1),
(71, 'AppleiMac', 1349.00, 1),
(72, 'Pinza', 10.00, 1),
(73, 'Cacciavite', 15.00, 1),
(74, 'Stereo', 150.00, 1),
(75, 'Tastiera', 25.00, 1),
(76, 'PennaBiro', 1.50, 10),
(77, 'GommaCancella', 0.50, 10),
(78, 'DiscoOro', 10.00, 1),
(79, 'DiscoPlatino', 15.00, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `articoli_fatture`
--

CREATE TABLE `articoli_fatture` (
  `ID_Articolo` int(11) NOT NULL,
  `ID_Fattura` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `articoli_fatture`
--

INSERT INTO `articoli_fatture` (`ID_Articolo`, `ID_Fattura`) VALUES
(61, 35),
(62, 36),
(67, 41),
(69, 43),
(70, 44),
(72, 46),
(73, 46),
(76, 48),
(77, 48),
(78, 49),
(79, 49);

-- --------------------------------------------------------

--
-- Struttura della tabella `clienti`
--

CREATE TABLE `clienti` (
  `ID` int(10) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Cognome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `clienti`
--

INSERT INTO `clienti` (`ID`, `Nome`, `Cognome`) VALUES
(1, 'Nicola', 'Pasquarelli'),
(12, 'Nicola', 'Gianchi'),
(13, 'Alessio', 'Ugo'),
(14, 'Ugo', 'Jetti'),
(15, 'Ugo', 'Marini'),
(16, 'Otello', 'Was'),
(17, 'Gig', 'Robot'),
(20, 'Clara', 'Vergari'),
(21, 'Gianni', 'Macchia'),
(23, 'Corinne', 'Pasquarelli'),
(24, 'Catia', 'Tommasi'),
(25, 'Coldilano', 'Vergari'),
(26, 'Paola', 'Ciacia'),
(27, 'Arturo', 'Gomesh'),
(28, 'Gianni', 'Maschio'),
(29, 'Mattia', 'Pasquarelli'),
(30, 'Alessio', 'Meucci'),
(31, 'Clara', 'Vergari'),
(32, 'Giacomo', 'Leopardi'),
(33, 'Corinne', 'Pasquarelli'),
(34, 'Nicola', 'Pasquarelli'),
(35, 'Nicola', 'Giovanni'),
(36, 'Cinzia', 'Libotti'),
(37, 'Nicola', 'Alibotti'),
(38, 'Nicola', 'Domenico'),
(39, 'Nicola', 'Mucciaccia'),
(40, 'Nicola', 'Pasquarelli'),
(41, 'Nicola', 'Pasquarelli'),
(42, 'Nicola', 'Pasquarelli'),
(43, 'Nicola', 'Pasquarelli'),
(44, 'Nicola', 'Pasquarelli'),
(45, 'Nicola', 'Pasquarelli'),
(46, 'Nicola', 'Pasquarelli'),
(47, 'Nicola', 'Pasquarelli'),
(48, 'Nicola', 'Pasquarelli'),
(49, 'Nicola', 'Giovannilli'),
(50, 'Goffredo', 'Vergari'),
(51, 'Nicola', 'Pasquarelli'),
(52, 'Nicola', 'Vergari'),
(53, 'Nicola', 'Pasquarelli'),
(54, 'Nicola', 'Pasquarelli'),
(55, 'Nicola', 'Gionni'),
(56, 'Nicola', 'Gianni'),
(57, 'Nicola', 'Renna'),
(58, 'Nicola', 'Pisqui'),
(59, 'Klea', 'Carta'),
(60, 'Giovanni', 'Crei'),
(61, 'Gino', 'Mestieri'),
(62, 'Vascoo', 'Rossi');

-- --------------------------------------------------------

--
-- Struttura della tabella `fatture`
--

CREATE TABLE `fatture` (
  `ID` int(10) NOT NULL,
  `p_Cliente` int(10) NOT NULL,
  `Data_di_Emissione` date NOT NULL,
  `imponibile` float DEFAULT NULL,
  `iva` int(5) NOT NULL,
  `Totale_Fattura` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `fatture`
--

INSERT INTO `fatture` (`ID`, `p_Cliente`, `Data_di_Emissione`, `imponibile`, `iva`, `Totale_Fattura`) VALUES
(35, 48, '2024-06-10', 859, 22, 1047.98),
(36, 49, '2024-10-10', 1200, 22, 1464),
(41, 54, '2002-12-15', 150, 22, 183),
(43, 56, '2024-05-16', 16, 22, 19.52),
(44, 57, '2013-01-06', 159.45, 22, 194.529),
(45, 58, '2024-06-15', 1398.9, 22, 1706.66),
(46, 59, '2024-05-05', 25, 22, 30.5),
(47, 60, '2023-11-12', 175, 22, 213.5),
(48, 61, '2022-12-11', 20, 22, 24.4),
(49, 62, '2011-10-12', 25, 22, 30.5);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `articoli`
--
ALTER TABLE `articoli`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `xd_Articoli__Descrizione` (`Descrizione`),
  ADD KEY `xd_Articoli_PrezzoUnitario` (`PrezzoUnitario`);

--
-- Indici per le tabelle `articoli_fatture`
--
ALTER TABLE `articoli_fatture`
  ADD KEY `ID_Articolo` (`ID_Articolo`),
  ADD KEY `ID_Fattura` (`ID_Fattura`);

--
-- Indici per le tabelle `clienti`
--
ALTER TABLE `clienti`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `fatture`
--
ALTER TABLE `fatture`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `p_Cliente` (`p_Cliente`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `articoli`
--
ALTER TABLE `articoli`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT per la tabella `clienti`
--
ALTER TABLE `clienti`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT per la tabella `fatture`
--
ALTER TABLE `fatture`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `articoli_fatture`
--
ALTER TABLE `articoli_fatture`
  ADD CONSTRAINT `articoli_fatture_ibfk_1` FOREIGN KEY (`ID_Fattura`) REFERENCES `fatture` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `articoli_fatture_ibfk_2` FOREIGN KEY (`ID_Articolo`) REFERENCES `articoli` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `fatture`
--
ALTER TABLE `fatture`
  ADD CONSTRAINT `fatture_ibfk_1` FOREIGN KEY (`p_Cliente`) REFERENCES `clienti` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
