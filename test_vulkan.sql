-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-01-2025 a las 04:34:53
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `test_vulkan`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course`
--

CREATE TABLE `course` (
  `id` bigint(20) NOT NULL,
  `max_capacity` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `course`
--

INSERT INTO `course` (`id`, `max_capacity`, `name`) VALUES
(1, 30, 'Agronomia'),
(2, 20, 'Calculo'),
(3, 20, 'Matematicas'),
(4, 40, 'Historia'),
(7, 35, 'Aritmetica'),
(8, 20, 'Programacion'),
(9, 46, 'Psicologia'),
(10, 20, 'Biologia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `student`
--

INSERT INTO `student` (`id`, `age`, `gender`, `name`, `course_id`) VALUES
(5, 18, 'M', 'Carlos Gutierrez', 2),
(7, 22, 'F', 'Gloria Vargas', 8),
(8, 22, 'M', 'Juan Pérez', 1),
(9, 19, 'F', 'María Gómez', 2),
(10, 25, 'M', 'Carlos López', 3),
(11, 21, 'F', 'Ana Rodríguez', 4),
(12, 30, 'M', 'Luis Sánchez', 7),
(13, 27, 'F', 'Marta Díaz', 8),
(14, 23, 'M', 'José Martínez', 9),
(15, 20, 'F', 'Laura Fernández', 10),
(16, 28, 'M', 'Pedro García', 1),
(17, 18, 'F', 'Sofía González', 2),
(18, 24, 'M', 'Antonio Fernández', 3),
(19, 22, 'F', 'Elena Rodríguez', 4),
(20, 26, 'M', 'David Ruiz', 7),
(21, 21, 'F', 'Isabel Herrera', 8),
(22, 29, 'M', 'Francisco Torres', 9),
(23, 25, 'F', 'Julia Morales', 10),
(24, 23, 'M', 'Diego Pérez', 1),
(25, 20, 'F', 'Paula Sánchez', 2),
(26, 27, 'M', 'Ricardo Castro', 3),
(27, 22, 'F', 'Sara Martínez', 4),
(48, 26, 'M', 'Raúl Ramírez', 9),
(49, 30, 'F', 'Carmen Álvarez', 1),
(50, 24, 'M', 'José Luis Moreno', 7),
(51, 19, 'F', 'Lucía Díaz', 8),
(52, 28, 'M', 'Francisco Ruiz', 9),
(53, 22, 'F', 'Pilar Castillo', 10),
(60, 20, 'M', 'Gonzalo Torres', 7);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdfypyqt0stgfc0aij9kcxm99s` (`course_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `course`
--
ALTER TABLE `course`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKdfypyqt0stgfc0aij9kcxm99s` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
