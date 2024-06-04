-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2024 a las 04:51:41
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_subasta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objects`
--

CREATE TABLE `objects` (
  `idPieza` int(8) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `fechaCreacion` int(4) NOT NULL,
  `lugarCreacion` varchar(30) NOT NULL,
  `autor` varchar(30) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `dimensiones` varchar(30) NOT NULL,
  `materiales` varchar(200) DEFAULT NULL,
  `necesitaElectricidad` tinyint(1) NOT NULL,
  `datefechaIngresa` date NOT NULL,
  `Estado_Pieza` varchar(30) NOT NULL,
  `datefechalimite` date NOT NULL,
  `tecnicapintura` varchar(30) DEFAULT NULL,
  `peso` varchar(30) DEFAULT NULL,
  `duracion` varchar(30) DEFAULT NULL,
  `tipovideo` varchar(30) DEFAULT NULL,
  `generofoto` varchar(30) DEFAULT NULL,
  `tipoImpresion` varchar(30) DEFAULT NULL,
  `Tipopapel` varchar(30) DEFAULT NULL,
  `Propietario` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `objects`
--

INSERT INTO `objects` (`idPieza`, `nombre`, `fechaCreacion`, `lugarCreacion`, `autor`, `tipo`, `dimensiones`, `materiales`, `necesitaElectricidad`, `datefechaIngresa`, `Estado_Pieza`, `datefechalimite`, `tecnicapintura`, `peso`, `duracion`, `tipovideo`, `generofoto`, `tipoImpresion`, `Tipopapel`, `Propietario`) VALUES
(1, 'La Mona Lisa', 1503, 'Florencia', 'Leonardo da Vinci', 'OLEO', '77,53,2', NULL, 0, '2024-06-01', 'EXHIBIDA', '2024-06-18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user` varchar(8) NOT NULL,
  `password` varchar(10) NOT NULL,
  `level` int(1) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `contacto` varchar(20) DEFAULT NULL,
  `limiteCompra` int(220) DEFAULT NULL,
  `verificado` tinyint(1) DEFAULT NULL,
  `costo` bigint(220) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='User registration';

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user`, `password`, `level`, `nombre`, `contacto`, `limiteCompra`, `verificado`, `costo`) VALUES
('a', 'a', 3, NULL, NULL, 0, 0, 0),
('admin', 'paco', 3, '', '', 0, 0, 0),
('hola', 'hola2', 1, '', '', 0, 0, 0),
('paco', 'paco', 3, '', '', 0, 0, 0),
('witrecx', 'paco', 3, '', '', 0, 0, 0),
('witrecx2', 'paco1', 0, '', '', 0, 0, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `objects`
--
ALTER TABLE `objects`
  ADD PRIMARY KEY (`idPieza`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
