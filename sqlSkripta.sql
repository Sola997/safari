-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema safari_schema
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Uloga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Uloga` (
  `idUloga` INT NOT NULL AUTO_INCREMENT,
  `nazivUloge` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idUloga`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Korisnik` (
  `idKorisnik` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `imeKorisnika` VARCHAR(100) NOT NULL,
  `prezimeKorisnika` VARCHAR(100) NOT NULL,
  `e-mail` VARCHAR(255) NULL,
  `Uloga_idUloga` INT NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_Korisnik_Uloga1_idx` (`Uloga_idUloga` ASC) VISIBLE,
  CONSTRAINT `fk_Korisnik_Uloga1`
    FOREIGN KEY (`Uloga_idUloga`)
    REFERENCES `mydb`.`Uloga` (`idUloga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Kategorija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Kategorija` (
  `idKategorija` INT NOT NULL AUTO_INCREMENT,
  `nazivKategorije` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idKategorija`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Predmet` (
  `idPredmet` INT NOT NULL AUTO_INCREMENT,
  `nazivPredmeta` VARCHAR(100) NOT NULL,
  `opis` VARCHAR(255) NULL,
  `stanje` VARCHAR(45) NULL,
  `pocetnaCena` INT NULL,
  `krajAukcije` DATETIME NULL,
  `idProdavca` INT NOT NULL,
  `Kategorija_idKategorija` INT NOT NULL,
  `status` TINYINT NULL,
  PRIMARY KEY (`idPredmet`),
  INDEX `fk_Predmet_Korisnik1_idx` (`idProdavca` ASC) VISIBLE,
  INDEX `fk_Predmet_Kategorija1_idx` (`Kategorija_idKategorija` ASC) VISIBLE,
  CONSTRAINT `fk_Predmet_Korisnik1`
    FOREIGN KEY (`idProdavca`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Predmet_Kategorija1`
    FOREIGN KEY (`Kategorija_idKategorija`)
    REFERENCES `mydb`.`Kategorija` (`idKategorija`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Slika`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Slika` (
  `idSlika` INT NOT NULL AUTO_INCREMENT,
  `putanja` VARCHAR(255) NOT NULL,
  `ime` VARCHAR(255) NULL,
  `Predmet_idPredmet` INT NOT NULL,
  PRIMARY KEY (`idSlika`),
  INDEX `fk_Slika_Predmet1_idx` (`Predmet_idPredmet` ASC) VISIBLE,
  CONSTRAINT `fk_Slika_Predmet1`
    FOREIGN KEY (`Predmet_idPredmet`)
    REFERENCES `mydb`.`Predmet` (`idPredmet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Licitacija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Licitacija` (
  `Korisnik_idKorisnik` INT NOT NULL,
  `Predmet_idPredmet` INT NOT NULL,
  `ponuda` INT NOT NULL,
  `datumLicitacije` DATETIME NULL,
  `pobedio` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`Korisnik_idKorisnik`, `Predmet_idPredmet`),
  INDEX `fk_Korisnik_has_Predmet_Predmet1_idx` (`Predmet_idPredmet` ASC) VISIBLE,
  INDEX `fk_Korisnik_has_Predmet_Korisnik1_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  CONSTRAINT `fk_Korisnik_has_Predmet_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Korisnik_has_Predmet_Predmet1`
    FOREIGN KEY (`Predmet_idPredmet`)
    REFERENCES `mydb`.`Predmet` (`idPredmet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ocena`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ocena` (
  `idOcena` INT NOT NULL AUTO_INCREMENT,
  `komentar` VARCHAR(255) NULL,
  `ocena` INT NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  PRIMARY KEY (`idOcena`),
  INDEX `fk_Ocena_Korisnik1_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  CONSTRAINT `fk_Ocena_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Poruka`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Poruka` (
  `idPoruka` INT NOT NULL AUTO_INCREMENT,
  `tekstPoruke` VARCHAR(255) NULL,
  `idKorisnik1` INT NOT NULL,
  `idKorisnik2` INT NOT NULL,
  `datum` DATETIME NULL,
  PRIMARY KEY (`idPoruka`),
  INDEX `fk_Poruka_Korisnik1_idx` (`idKorisnik1` ASC) VISIBLE,
  INDEX `fk_Poruka_Korisnik2_idx` (`idKorisnik2` ASC) VISIBLE,
  CONSTRAINT `fk_Poruka_Korisnik1`
    FOREIGN KEY (`idKorisnik1`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Poruka_Korisnik2`
    FOREIGN KEY (`idKorisnik2`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE USER 'user1' IDENTIFIED BY 'sola123';

GRANT ALL ON `mydb`.* TO 'user1';
GRANT ALL ON `safari_schema`.* TO 'user1';
GRANT SELECT ON TABLE `mydb`.* TO 'user1';
GRANT SELECT ON TABLE `safari_schema`.* TO 'user1';
GRANT SELECT, INSERT, TRIGGER ON TABLE `mydb`.* TO 'user1';
GRANT SELECT, INSERT, TRIGGER ON TABLE `safari_schema`.* TO 'user1';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `mydb`.* TO 'user1';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `safari_schema`.* TO 'user1';
GRANT EXECUTE ON ROUTINE `mydb`.* TO 'user1';
GRANT EXECUTE ON ROUTINE `safari_schema`.* TO 'user1';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
