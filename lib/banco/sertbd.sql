-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 20-Out-2018 às 21:20
-- Versão do servidor: 5.7.21
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sertbd`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cad_mercadorias`
--

DROP TABLE IF EXISTS `cad_mercadorias`;
CREATE TABLE IF NOT EXISTS `cad_mercadorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_mercadoria` varchar(100) NOT NULL,
  `cod_barras` bigint(20) NOT NULL,
  `precoVenda` decimal(6,2) NOT NULL,
  `data_cadastro` varchar(10) NOT NULL,
  `unidade` varchar(10) NOT NULL,
  `data_alteracao` varchar(10) NOT NULL,
  `preco_compra` decimal(6,2) NOT NULL,
  `usu_cad` int(11) NOT NULL,
  `usu_edit` int(11) NOT NULL,
  `estoque` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cad_mercadorias`
--

INSERT INTO `cad_mercadorias` (`id`, `nome_mercadoria`, `cod_barras`, `precoVenda`, `data_cadastro`, `unidade`, `data_alteracao`, `preco_compra`, `usu_cad`, `usu_edit`, `estoque`) VALUES
(2, 'LAPIS PRETO SEXTAVADO LEO&LEO', 7897256246831, '1.30', '08/09/2018', 'PC', '', '0.50', 0, 0, 0),
(3, 'LAPIS FABER CASTEL', 7891360400523, '1.25', '08/09/2018', 'UN', '', '0.90', 0, 0, 0),
(4, 'TESTE', 78963542, '1.96', '08/09/2018', 'UND', '', '0.50', 0, 0, 0),
(5, 'TESTE DE ATT', 78963541, '1.63', '08/09/2018', 'UNID', '16/09/2018', '1.20', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE IF NOT EXISTS `empresa` (
  `cnpj` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `nomeFant` varchar(100) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `numeroEnd` int(11) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(100) NOT NULL,
  `contato` bigint(20) NOT NULL,
  `ie` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `empresa`
--

INSERT INTO `empresa` (`cnpj`, `nome`, `nomeFant`, `rua`, `numeroEnd`, `bairro`, `cidade`, `uf`, `contato`, `ie`) VALUES
(31199696000150, 'MARCIO MATHEUS DE SOUZA FERREIRA 11039083404', 'M&K PAPELARIA', 'RUA JOSE DE SIQUEIRA CAMPOS', 135, 'NOSSA SENHORA APARECIDA', 'Salgueiro', 'PE', 8788754442, 78729173);

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
CREATE TABLE IF NOT EXISTS `fornecedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnpj` bigint(20) NOT NULL,
  `ie` bigint(20) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `nomeFant` varchar(100) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `numeroEnd` int(11) DEFAULT NULL,
  `bairro` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `uf` varchar(100) DEFAULT NULL,
  `cep` int(11) DEFAULT NULL,
  `telefone` bigint(11) DEFAULT NULL,
  `celular` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mov_sistema`
--

DROP TABLE IF EXISTS `mov_sistema`;
CREATE TABLE IF NOT EXISTS `mov_sistema` (
  `usuario` int(11) NOT NULL,
  `alteracao` varchar(150) NOT NULL,
  `data_alteracao` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nomeUsuUnique` (`nome`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
