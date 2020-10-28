-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdcentroeducacional
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdcentroeducacional
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdcentroeducacional` ;
USE `bdcentroeducacional` ;

-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`informacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`informacao` (
  `infcodigo` INT NOT NULL AUTO_INCREMENT,
  `infpergunta1` VARCHAR(45) NULL,
  `infpergunta2` VARCHAR(45) NULL,
  `infpergunta3` VARCHAR(45) NULL,
  `infpergunta4` VARCHAR(48) NULL,
  `infpergunta5` CHAR(1) NULL,
  `infpergunta6` CHAR(1) NULL,
  `infpergunta7` VARCHAR(45) NULL,
  `infpergunta8` VARCHAR(45) NULL,
  `infpergunta9` VARCHAR(45) NULL,
  `infobs` VARCHAR(150) NULL,
  PRIMARY KEY (`infcodigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`estado` (
  `estcodigo` INT NOT NULL AUTO_INCREMENT,
  `estnome` VARCHAR(45) NULL,
  PRIMARY KEY (`estcodigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`turma` (
  `turcodigo` INT NOT NULL AUTO_INCREMENT,
  `turdescricao` VARCHAR(60) NULL,
  PRIMARY KEY (`turcodigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`turno` (
  `tnocodigo` INT NOT NULL AUTO_INCREMENT,
  `tnonome` VARCHAR(45) NULL,
  PRIMARY KEY (`tnocodigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`aluno` (
  `alucodigo` INT NOT NULL AUTO_INCREMENT,
  `alumatricula` VARCHAR(1000) NULL,
  `alunome` VARCHAR(80) NULL,
  `aludtnascimento` DATE NULL,
  `alusexo` CHAR(1) NULL,
  `alufoto` LONGBLOB NULL,
  `alunaturalidade` VARCHAR(45) NULL,
  `alustatus` VARCHAR(10) NULL,
  `alu_estcodigo` INT NOT NULL,
  `alu_turcodigo` INT NOT NULL,
  `alu_tnocodigo` INT NOT NULL,
  `alu_infcodigo` INT NOT NULL,
  PRIMARY KEY (`alucodigo`),
  INDEX `fk_aluno_informacao1_idx` (`alu_infcodigo` ASC),
  INDEX `fk_aluno_estado1_idx` (`alu_estcodigo` ASC),
  INDEX `fk_aluno_turma1_idx` (`alu_turcodigo` ASC) ,
  INDEX `fk_aluno_turno1_idx` (`alu_tnocodigo` ASC) ,
  CONSTRAINT `fk_aluno_informacao1`
    FOREIGN KEY (`alu_infcodigo`)
    REFERENCES `bdcentroeducacional`.`informacao` (`infcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_estado1`
    FOREIGN KEY (`alu_estcodigo`)
    REFERENCES `bdcentroeducacional`.`estado` (`estcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_turma1`
    FOREIGN KEY (`alu_turcodigo`)
    REFERENCES `bdcentroeducacional`.`turma` (`turcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_turno1`
    FOREIGN KEY (`alu_tnocodigo`)
    REFERENCES `bdcentroeducacional`.`turno` (`tnocodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`endereco` (
  `endcodigo` INT NOT NULL AUTO_INCREMENT,
  `endcep` VARCHAR(9) NULL,
  `endrua` VARCHAR(50) NULL,
  `endnumero` VARCHAR(10) NULL,
  `endbairro` VARCHAR(50) NULL,
  PRIMARY KEY (`endcodigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`responsavel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`responsavel` (
  `rescodigo` INT NOT NULL AUTO_INCREMENT,
  `resnome` VARCHAR(50) NULL,
  `reslocaltrabalho` VARCHAR(50) NULL,
  `resprofissao` VARCHAR(50) NULL,
  `resfonetrabalho` VARCHAR(10) NULL,
  `resgrauparentesco` VARCHAR(20) NULL,
  `res_endcodigo` INT NOT NULL,
  PRIMARY KEY (`rescodigo`),
  INDEX `fk_responsavel_endereco1_idx` (`res_endcodigo` ASC),
  CONSTRAINT `fk_responsavel_endereco1`
    FOREIGN KEY (`res_endcodigo`)
    REFERENCES `bdcentroeducacional`.`endereco` (`endcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`nm_res_alu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`nm_res_alu` (
  `nm_codigo` INT NOT NULL AUTO_INCREMENT,
  `nm_alucodigo` INT NOT NULL,
  `nm_rescodigo` INT NOT NULL,
  -- PRIMARY KEY (`nm_codigo`, `nm_alucodigo`, `nm_rescodigo`),
  PRIMARY KEY (`nm_codigo`),
  INDEX `fk_aluno_has_responsavel_responsavel1_idx` (`nm_rescodigo` ASC),
  INDEX `fk_aluno_has_responsavel_aluno1_idx` (`nm_alucodigo` ASC),
  CONSTRAINT `fk_aluno_has_responsavel_aluno1`
    FOREIGN KEY (`nm_alucodigo`)
    REFERENCES `bdcentroeducacional`.`aluno` (`alucodigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_aluno_has_responsavel_responsavel1`
    FOREIGN KEY (`nm_rescodigo`)
    REFERENCES `bdcentroeducacional`.`responsavel` (`rescodigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`fone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`fone` (
  `fontelefone` VARCHAR(15) NULL,
  `fon_rescodigo` INT NOT NULL,
  INDEX `fk_fone_responsavel1_idx` (`fon_rescodigo` ASC),
  CONSTRAINT `fk_fone_responsavel1`
    FOREIGN KEY (`fon_rescodigo`)
    REFERENCES `bdcentroeducacional`.`responsavel` (`rescodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
