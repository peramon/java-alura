CREATE SCHEMA `hotel_alura` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE hotel_alura;
INSERT INTO usuario (correo,contraseña) VALUES ("admin" , "admin");

CREATE TABLE `hotel_alura`.`usuariod` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `correo` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE);

CREATE TABLE `hotel_alura`.`reserva` (
  `idReserva` INT NOT NULL AUTO_INCREMENT,
  `FechaEntrada` VARCHAR(10) NOT NULL,
  `FechaSalida` VARCHAR(10) NOT NULL,
  `Valor` INT NOT NULL,
  `FormaPago` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idReserva`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;

CREATE TABLE `hotel_alura`.`huesped` (
  `idhuesped` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `apellido` VARCHAR(20) NOT NULL,
  `fechaNacimiento` VARCHAR(10) NOT NULL,
  `nacionalidad` VARCHAR(25) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `idReservaF` INT NOT NULL,
  PRIMARY KEY (`idhuesped`),
  INDEX `idReserva_idx` (`idReservaF` ASC) VISIBLE,
  CONSTRAINT `idReserva`
    FOREIGN KEY (`idReservaF`)
    REFERENCES `reserva` (`idReserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
