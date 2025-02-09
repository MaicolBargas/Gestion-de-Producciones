-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-02-2025 a las 19:05:46
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
-- Base de datos: `magnolia_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `analisis`
--

CREATE TABLE `analisis` (
  `idAnalisis` int(11) NOT NULL,
  `tipo` varchar(15) NOT NULL,
  `empleado` int(11) NOT NULL,
  `fecha` varchar(15) NOT NULL,
  `levadura` int(11) NOT NULL,
  `mos` int(11) NOT NULL,
  `poliformosTotales` int(11) NOT NULL,
  `poliformosFecales` int(11) NOT NULL,
  `grasa` int(11) DEFAULT NULL,
  `proteina` int(11) DEFAULT NULL,
  `agua` int(11) DEFAULT NULL,
  `idProduccion` int(11) DEFAULT NULL,
  `idIngreso` int(11) DEFAULT NULL,
  `idPasteurizada` int(11) DEFAULT NULL,
  `humedad` int(11) DEFAULT NULL,
  `sal` int(11) DEFAULT NULL,
  `ph` float(10,2) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `idEmpleado` int(4) NOT NULL,
  `ci` int(8) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellido` varchar(15) NOT NULL,
  `idSeccion` int(11) NOT NULL,
  `telefono` int(9) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `activo` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envases`
--

CREATE TABLE `envases` (
  `idEnvase` int(11) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `capacidad` float(10,3) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingresos`
--

CREATE TABLE `ingresos` (
  `idIngreso` int(4) NOT NULL,
  `idTambo` int(3) NOT NULL,
  `litros` int(6) NOT NULL,
  `litrosDisponibles` int(6) NOT NULL,
  `idSilo` int(2) NOT NULL,
  `fecha` varchar(15) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumo`
--

CREATE TABLE `insumo` (
  `idInsumo` int(2) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `unidad` varchar(7) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_envases`
--

CREATE TABLE `linea_envases` (
  `idLinea` int(11) NOT NULL,
  `idProduccion` int(11) NOT NULL,
  `idEnvase` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_insumos`
--

CREATE TABLE `linea_insumos` (
  `idLinea` int(11) NOT NULL,
  `idProduccion` int(11) NOT NULL,
  `idInsumo` int(11) NOT NULL,
  `cantidad` float(5,2) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasteurizadas`
--

CREATE TABLE `pasteurizadas` (
  `idLecheP` int(11) NOT NULL,
  `temperatura` int(11) NOT NULL,
  `litros` int(11) NOT NULL,
  `idIngreso` int(11) NOT NULL,
  `descremado` varchar(20) NOT NULL,
  `cremaObtenida` int(11) NOT NULL,
  `cremaDisponible` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produccion`
--

CREATE TABLE `produccion` (
  `idProduccion` int(11) NOT NULL,
  `codInterno` varchar(50) NOT NULL,
  `idLechePast` int(11) NOT NULL,
  `litros` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `rendimiento` int(11) NOT NULL,
  `kgLtsObt` int(11) NOT NULL,
  `fecha` varchar(10) NOT NULL,
  `encargadoId` int(11) NOT NULL,
  `horaInicio` varchar(5) NOT NULL,
  `horaFin` varchar(5) NOT NULL,
  `tiempoTrabajado` varchar(5) NOT NULL,
  `nroTacho` int(11) NOT NULL,
  `observaciones` text DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produccion_dulce`
--

CREATE TABLE `produccion_dulce` (
  `id` int(11) NOT NULL,
  `idProduccion` int(11) NOT NULL,
  `phLecheSn` float(10,3) NOT NULL,
  `phLecheNeut` float(10,3) NOT NULL,
  `litrosSuero` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produccion_empleados`
--

CREATE TABLE `produccion_empleados` (
  `id` int(11) NOT NULL,
  `idProduccion` int(11) NOT NULL,
  `idEmpleado` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produccion_manteca`
--

CREATE TABLE `produccion_manteca` (
  `id` int(11) NOT NULL,
  `idProduccion` int(11) NOT NULL,
  `comienzoBatido` varchar(5) NOT NULL,
  `finBatido` varchar(5) NOT NULL,
  `totalBatido` varchar(5) NOT NULL,
  `ormas` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produccion_queso`
--

CREATE TABLE `produccion_queso` (
  `id` int(11) NOT NULL,
  `idProduccion` int(11) NOT NULL,
  `tempPastQueso` float(10,2) NOT NULL,
  `tiempoReposoFermento` varchar(5) NOT NULL,
  `tempReposoFermento` decimal(10,0) NOT NULL,
  `tipoCuajoObtenido` varchar(50) NOT NULL,
  `tiempoCuajado` varchar(5) NOT NULL,
  `tempAlCuajar` float(10,2) NOT NULL,
  `cantCuajoObtenido` decimal(10,0) NOT NULL,
  `tipoDeGrano` varchar(50) NOT NULL,
  `litrosSueroObtenidos` decimal(10,0) NOT NULL,
  `tiempoAgregadoAgua` varchar(5) NOT NULL,
  `tempAgua` float(10,2) NOT NULL,
  `tempCuajoFinal` float(10,2) NOT NULL,
  `unidadesObtenidas` int(11) NOT NULL,
  `acidesFermento` float(10,2) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `produccion_yogur`
--

CREATE TABLE `produccion_yogur` (
  `id` int(11) NOT NULL,
  `idProduccion` int(11) NOT NULL,
  `tempIncubacion` float(10,2) NOT NULL,
  `horaComienzoInc` varchar(5) NOT NULL,
  `horaFinInc` varchar(5) NOT NULL,
  `tiempoIncubacion` varchar(5) NOT NULL,
  `horaComienzoEnfriado` varchar(5) NOT NULL,
  `horaFinEnfriado` varchar(5) NOT NULL,
  `tiempoTotalEnfriado` varchar(5) NOT NULL,
  `tempAguaHelada` float(10,2) NOT NULL,
  `tempAgregadoSabor` float(10,2) NOT NULL,
  `tempAgregadoColor` float(10,2) NOT NULL,
  `litrosSuero` int(11) NOT NULL,
  `unidadesObtenidas` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `activo`) VALUES
(1, 'Queso Colonia', 'Queso Colonia', 1),
(2, 'Queso Cuartirolo', 'Queso Cuartirolo', 1),
(3, 'Queso Dambo', 'Queso Dambo', 1),
(4, 'Queso Holandita', 'Queso Holandita', 1),
(5, 'Queso Magro', 'Queso Magro', 1),
(6, 'Queso Muzarella', 'Queso Muzarella', 1),
(7, 'Queso Parmesano', 'Queso Parmesano', 1),
(8, 'Queso Provolone', 'Queso Provolone', 1),
(9, 'Queso Sardo', 'Queso Sardo', 1),
(10, 'Queso Semiduro', 'Queso Semiduro', 1),
(11, 'Dulce de Leche Crema', 'Dulce de Leche Crema', 1),
(12, 'Dulce de Leche Casero', 'Dulce de Leche Casero', 1),
(13, 'Dulce de Leche Repostero', 'Dulce de Leche Repostero', 1),
(14, 'Dulce de Leche Amarillo', 'Dulce de Leche Suizo Amarillo', 1),
(15, 'Dulce de Leche Azul', 'Dulce de Leche Suizo Azul', 1),
(16, 'Dulce de Leche Especial', 'Dulce de Leche Especial', 1),
(17, 'Dulce de Leche Verde Duro', 'Dulce de Leche Verde Duro', 1),
(18, 'Dulce de Leche Verde Blando', 'Dulce de Leche Verde Blando', 1),
(19, 'Manteca', 'Manteca', 1),
(20, 'Yogur bebible Frutilla', 'Yogur bebible frutilla', 1),
(21, 'Yogur bebible Durazno', 'Yogur bebible Durazno', 1),
(22, 'Yogur bebible Ananà', 'Yogur bebible Ananà', 1),
(23, 'Yogur bebible Banana', 'Yogur bebible Banana', 1),
(24, 'Yogur batido Vainilla', 'Yogur batido Vainilla', 1),
(25, 'Yogur batido Natural', 'Yogur batido Natural', 1),
(26, 'Yogur batido Frutilla', 'Yogur batido Frutilla', 1),
(27, 'Yogur batido Durazno', 'Yogur batido Durazno', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `secciones`
--

CREATE TABLE `secciones` (
  `idSeccion` int(3) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `silos`
--

CREATE TABLE `silos` (
  `IdSilo` int(2) NOT NULL,
  `codigoSilo` varchar(5) NOT NULL,
  `capacidad` int(8) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tambo`
--

CREATE TABLE `tambo` (
  `idTambo` int(3) NOT NULL,
  `nombrePropietario` varchar(20) NOT NULL,
  `contacto` varchar(20) NOT NULL,
  `direccion` varchar(25) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `analisis`
--
ALTER TABLE `analisis`
  ADD PRIMARY KEY (`idAnalisis`),
  ADD KEY `tipo` (`tipo`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`idEmpleado`),
  ADD UNIQUE KEY `ci` (`ci`);

--
-- Indices de la tabla `envases`
--
ALTER TABLE `envases`
  ADD PRIMARY KEY (`idEnvase`);

--
-- Indices de la tabla `ingresos`
--
ALTER TABLE `ingresos`
  ADD PRIMARY KEY (`idIngreso`);

--
-- Indices de la tabla `insumo`
--
ALTER TABLE `insumo`
  ADD PRIMARY KEY (`idInsumo`);

--
-- Indices de la tabla `linea_envases`
--
ALTER TABLE `linea_envases`
  ADD PRIMARY KEY (`idLinea`);

--
-- Indices de la tabla `linea_insumos`
--
ALTER TABLE `linea_insumos`
  ADD PRIMARY KEY (`idLinea`);

--
-- Indices de la tabla `pasteurizadas`
--
ALTER TABLE `pasteurizadas`
  ADD PRIMARY KEY (`idLecheP`);

--
-- Indices de la tabla `produccion`
--
ALTER TABLE `produccion`
  ADD PRIMARY KEY (`idProduccion`);

--
-- Indices de la tabla `produccion_dulce`
--
ALTER TABLE `produccion_dulce`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `produccion_empleados`
--
ALTER TABLE `produccion_empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `produccion_manteca`
--
ALTER TABLE `produccion_manteca`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `produccion_queso`
--
ALTER TABLE `produccion_queso`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `produccion_yogur`
--
ALTER TABLE `produccion_yogur`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `secciones`
--
ALTER TABLE `secciones`
  ADD PRIMARY KEY (`idSeccion`);

--
-- Indices de la tabla `silos`
--
ALTER TABLE `silos`
  ADD PRIMARY KEY (`IdSilo`),
  ADD UNIQUE KEY `codigoSilo` (`codigoSilo`);

--
-- Indices de la tabla `tambo`
--
ALTER TABLE `tambo`
  ADD PRIMARY KEY (`idTambo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `analisis`
--
ALTER TABLE `analisis`
  MODIFY `idAnalisis` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `idEmpleado` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `envases`
--
ALTER TABLE `envases`
  MODIFY `idEnvase` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ingresos`
--
ALTER TABLE `ingresos`
  MODIFY `idIngreso` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `insumo`
--
ALTER TABLE `insumo`
  MODIFY `idInsumo` int(2) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `linea_envases`
--
ALTER TABLE `linea_envases`
  MODIFY `idLinea` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `linea_insumos`
--
ALTER TABLE `linea_insumos`
  MODIFY `idLinea` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pasteurizadas`
--
ALTER TABLE `pasteurizadas`
  MODIFY `idLecheP` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `produccion`
--
ALTER TABLE `produccion`
  MODIFY `idProduccion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `produccion_dulce`
--
ALTER TABLE `produccion_dulce`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `produccion_empleados`
--
ALTER TABLE `produccion_empleados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `produccion_manteca`
--
ALTER TABLE `produccion_manteca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `produccion_queso`
--
ALTER TABLE `produccion_queso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `produccion_yogur`
--
ALTER TABLE `produccion_yogur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `secciones`
--
ALTER TABLE `secciones`
  MODIFY `idSeccion` int(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `silos`
--
ALTER TABLE `silos`
  MODIFY `IdSilo` int(2) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tambo`
--
ALTER TABLE `tambo`
  MODIFY `idTambo` int(3) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
