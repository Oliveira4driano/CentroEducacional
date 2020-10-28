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
CREATE SCHEMA IF NOT EXISTS `bdcentroeducacional` DEFAULT CHARACTER SET latin1 ;
USE `bdcentroeducacional` ;

-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`estado` (
  `estcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `estnome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`estcodigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`informacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`informacao` (
  `infcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `infpergunta1` VARCHAR(45) NULL DEFAULT NULL,
  `infpergunta2` VARCHAR(45) NULL DEFAULT NULL,
  `infpergunta3` VARCHAR(45) NULL DEFAULT NULL,
  `infpergunta4` VARCHAR(48) NULL DEFAULT NULL,
  `infpergunta5` CHAR(1) NULL DEFAULT NULL,
  `infpergunta6` CHAR(1) NULL DEFAULT NULL,
  `infpergunta7` VARCHAR(45) NULL DEFAULT NULL,
  `infpergunta8` VARCHAR(45) NULL DEFAULT NULL,
  `infpergunta9` VARCHAR(45) NULL DEFAULT NULL,
  `infobs` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`infcodigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`turma` (
  `turcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `turdescricao` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`turcodigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`turno` (
  `tnocodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `tnonome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`tnocodigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`aluno` (
  `alucodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `alumatricula` VARCHAR(1000) NULL DEFAULT NULL,
  `alunome` VARCHAR(80) NULL DEFAULT NULL,
  `aludtnascimento` DATE NULL DEFAULT NULL,
  `alusexo` CHAR(1) NULL DEFAULT NULL,
  `alufoto` LONGBLOB NULL DEFAULT NULL,
  `alunaturalidade` VARCHAR(45) NULL DEFAULT NULL,
  `alustatus` VARCHAR(10) NULL DEFAULT NULL,
  `alu_estcodigo` INT(11) NOT NULL,
  `alu_turcodigo` INT(11) NOT NULL,
  `alu_tnocodigo` INT(11) NOT NULL,
  `alu_infcodigo` INT(11) NOT NULL,
  PRIMARY KEY (`alucodigo`),
  INDEX `fk_aluno_informacao1_idx` (`alu_infcodigo` ASC) ,
  INDEX `fk_aluno_estado1_idx` (`alu_estcodigo` ASC) ,
  INDEX `fk_aluno_turma1_idx` (`alu_turcodigo` ASC) ,
  INDEX `fk_aluno_turno1_idx` (`alu_tnocodigo` ASC) ,
  CONSTRAINT `fk_aluno_estado1`
    FOREIGN KEY (`alu_estcodigo`)
    REFERENCES `bdcentroeducacional`.`estado` (`estcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_informacao1`
    FOREIGN KEY (`alu_infcodigo`)
    REFERENCES `bdcentroeducacional`.`informacao` (`infcodigo`)
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
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`endereco` (
  `endcodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `endcep` VARCHAR(9) NULL DEFAULT NULL,
  `endrua` VARCHAR(50) NULL DEFAULT NULL,
  `endnumero` VARCHAR(10) NULL DEFAULT NULL,
  `endbairro` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`endcodigo`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`responsavel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`responsavel` (
  `rescodigo` INT(11) NOT NULL AUTO_INCREMENT,
  `resnome` VARCHAR(50) NULL DEFAULT NULL,
  `reslocaltrabalho` VARCHAR(50) NULL DEFAULT NULL,
  `resprofissao` VARCHAR(50) NULL DEFAULT NULL,
  `resfonetrabalho` VARCHAR(10) NULL DEFAULT NULL,
  `resgrauparentesco` VARCHAR(20) NULL DEFAULT NULL,
  `res_endcodigo` INT(11) NOT NULL,
  PRIMARY KEY (`rescodigo`),
  INDEX `fk_responsavel_endereco1_idx` (`res_endcodigo` ASC) ,
  CONSTRAINT `fk_responsavel_endereco1`
    FOREIGN KEY (`res_endcodigo`)
    REFERENCES `bdcentroeducacional`.`endereco` (`endcodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`fone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`fone` (
  `fontelefone` VARCHAR(15) NULL DEFAULT NULL,
  `fon_rescodigo` INT(11) NOT NULL,
  INDEX `fk_fone_responsavel1_idx` (`fon_rescodigo` ASC) ,
  CONSTRAINT `fk_fone_responsavel1`
    FOREIGN KEY (`fon_rescodigo`)
    REFERENCES `bdcentroeducacional`.`responsavel` (`rescodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `bdcentroeducacional`.`nm_res_alu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdcentroeducacional`.`nm_res_alu` (
  `nm_codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nm_alucodigo` INT(11) NOT NULL,
  `nm_rescodigo` INT(11) NOT NULL,
  PRIMARY KEY (`nm_codigo`),
  INDEX `fk_aluno_has_responsavel_responsavel1_idx` (`nm_rescodigo` ASC) ,
  INDEX `fk_aluno_has_responsavel_aluno1_idx` (`nm_alucodigo` ASC) ,
  CONSTRAINT `fk_aluno_has_responsavel_aluno1`
    FOREIGN KEY (`nm_alucodigo`)
    REFERENCES `bdcentroeducacional`.`aluno` (`alucodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aluno_has_responsavel_responsavel1`
    FOREIGN KEY (`nm_rescodigo`)
    REFERENCES `bdcentroeducacional`.`responsavel` (`rescodigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;

USE `bdcentroeducacional` ;

-- -----------------------------------------------------
-- procedure sp_alterarAluno
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alterarAluno`(p_matricula varchar(1000), p_aluno varchar(80), p_dtnasci date, p_sexo char(1), p_foto longblob, p_naturalidade varchar(45), 
								p_estado int, p_turma int, p_turno int )
begin
		update aluno
        set alunome = p_aluno, aludtnascimento = p_dtnasci, alusexo = p_sexo, alufoto = p_foto, alunaturalidade = p_naturalidade, 
			alu_estcodigo = p_estado, alu_turcodigo= p_turma , alu_tnocodigo = p_turno
        where alumatricula = p_matricula;
        
	
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_alterarEndereco
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alterarEndereco`( p_endcodigo int, p_cep varchar(9), p_rua varchar(50), p_numero varchar(10), p_bairro varchar(50))
begin
		update endereco
        set endcep = p_cep, endrua = p_rua, endnumero = p_numero, endbairro = p_bairro 
        where endcodigo = p_endcodigo;

    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_alterarInformacao
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alterarInformacao`( p_infcodigo int, p_inf1 varchar(45), p_inf2 varchar(45), p_inf3 varchar(45), p_inf4 char(48),
										p_inf5 char(1), p_inf6 char(1), p_inf7 varchar(45), p_inf8 varchar(45), p_inf9 varchar(45), p_obs varchar(100))
begin
		update informacao
        set infpergunta1 = p_inf1 , infpergunta2 = p_inf2, infpergunta3 = p_inf3, infpergunta4 = p_inf4, infpergunta5 = p_inf5, infpergunta6 = p_inf6,
				infpergunta7 = p_inf7, infpergunta8 = p_inf8, infpergunta9 = p_inf9, infobs = p_obs 
        where infcodigo = p_infcodigo;

    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_alterarResponsavel
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alterarResponsavel`( p_res_endcodigo int, p_resnome varchar(50), p_reslocaltrabalho varchar(50), 
										p_resprofissao varchar(50), p_resfonetrabalho varchar(10), p_fonetelefone varchar(15),p_resgrauparentesco varchar(20)
                                        )
begin
		update responsavel
        set resnome = p_resnome, reslocaltrabalho = p_reslocaltrabalho, resprofissao = p_resprofissao, resfonetrabalho = p_resfonetrabalho 
        where res_endcodigo = p_res_endcodigo and resgrauparentesco = p_resgrauparentesco;
        
        update fone
			set fontelefone = p_fonetelefone
				where fon_rescodigo = 	p_res_endcodigo;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_alterarTurma
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_alterarTurma`( p_turcodigo int, p_turnome varchar(45) )
begin
		update turma
        set turdescricao = p_turnome
        where turcodigo = p_turcodigo;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_excluirAluno
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_excluirAluno`(p_alumatricula varchar(1000))
begin 
		delete aluno, endereco, informacao,responsavel, fone
			from aluno
				left join informacao on infcodigo = alu_infcodigo
                left join nm_res_alu on nm_alucodigo = alucodigo
                left join responsavel on rescodigo = nm_rescodigo
                left join endereco on endcodigo = res_endcodigo
                left join fone on fon_rescodigo = rescodigo
			where alumatricula = p_alumatricula;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_excluirTurma
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_excluirTurma`(p_turdescricao varchar(60))
begin 
		delete 
			from turma
			where turdescricao = p_turdescricao;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_inserirAluno
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inserirAluno`( 
	p_aluno varchar(80), p_dtnasci date, p_sexo char(1), p_foto longblob, p_naturalidade varchar(45), 
    p_status varchar(10),p_estado int, p_turma int, p_turno int )
begin 
		declare v_matricula varchar(1000);
        declare v_rescodigo TINYINT DEFAULT 0;
        declare vCOD TINYINT DEFAULT 0;
        declare vANO varchar(4);
        declare vMES varchar(2);
        declare v_aluinfcodigo int default 0;
        set vANO = (select year(current_date()));
        set vMES = (select month(current_date()));
		set vCOD = (select coalesce(max(alucodigo),0) from aluno);
        set v_matricula = (select concat(vANO,vMES,vCOD+1,p_turma));
        set v_aluinfcodigo = (select coalesce(max(infcodigo),0) from informacao);
        set v_rescodigo = (select coalesce(max(rescodigo),0) from responsavel);
		insert into aluno 
			values(null, v_matricula, p_aluno,  p_dtnasci , p_sexo, p_foto, p_naturalidade, p_status, p_estado, p_turma, p_turno, v_aluinfcodigo ); 
		 insert into nm_res_alu
		  values(null,vCOD+1, v_rescodigo);
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_inserirEndereco
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inserirEndereco`(p_cep varchar(9), p_rua varchar(50), p_numero varchar(10), p_bairro varchar(50) )
begin
		insert into endereco values (null,p_cep, p_rua, p_numero, p_bairro);
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_inserirInformacao
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inserirInformacao`(p_inf1 varchar(45), p_inf2 varchar(45), p_inf3 varchar(45), p_inf4 char(48),
										p_inf5 char(1), p_inf6 char(1), p_inf7 varchar(45), p_inf8 varchar(45), p_inf9 varchar(45), p_obs varchar(100))
begin
		insert into informacao values (null,p_inf1, p_inf2, p_inf3, p_inf4, p_inf5, p_inf6, p_inf7, p_inf8, p_inf9, p_obs);
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_inserirResponsavel
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inserirResponsavel`(p_nome varchar(50), p_localtrabalho varchar(50), p_profissao varchar(50), p_fonetrabalho varchar(10), p_parentesco varchar(20), p_fone varchar(15))
begin
		declare v_resbaicodigo int default 0;
        declare v_resfone int default 0;
		set v_resbaicodigo = ((select coalesce(max(endcodigo),0) from endereco));
        set v_resfone = ((select coalesce(max(rescodigo),0) from responsavel));
        insert into responsavel
			values(null, p_nome, p_localtrabalho, p_profissao, p_fonetrabalho, p_parentesco,v_resbaicodigo);
		insert into fone 
			values(p_fone, v_resfone+1);
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_inserirTurma
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_inserirTurma`( p_estnome varchar(60) )
begin 
		insert into turma values(turcodigo, p_estnome); 
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_listarAluno
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarAluno`()
begin
		 -- declare v_mae varchar(2);
         -- set v_mae = (SELECT TIMESTAMPDIFF(YEAR,aludtnascimento,current_date()) from aluno);
		select  alumatricula, alunome, alusexo, tnonome, turdescricao, 
			(TIMESTAMPDIFF(YEAR,aludtnascimento,current_date())) as idade
			from aluno
			 left join turno on tnocodigo = alu_tnocodigo
             left outer join turma on turcodigo = alu_turcodigo
			 left outer join nm_res_alu on nm_alucodigo = alucodigo
             left outer join responsavel on rescodigo = nm_rescodigo
             order by alunome
            ;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_listarEstado
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarEstado`()
begin
		select  estcodigo, estnome
			from estado;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_listarTurma
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarTurma`()
begin
		select  turcodigo, turdescricao
			from turma;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_listarTurno
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listarTurno`()
begin
		select  tnocodigo, tnonome
			from turno;
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_pesquisarAluno
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pesquisarAluno`(p_aluno varchar(45))
begin 
		select  alumatricula, alunome, alusexo, tnonome, turdescricao, 
			(TIMESTAMPDIFF(YEAR,aludtnascimento,current_date())) as idade
			from aluno
				left join turno on tnocodigo = alu_tnocodigo
				left outer join turma on turcodigo = alu_turcodigo
				left outer join nm_res_alu on nm_alucodigo = alucodigo
             -- left outer join responsavel on rescodigo = nm_rescodigo
			where alunome like concat('%', p_aluno,'%') or alumatricula like concat('%', p_aluno,'%'); 
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_pesquisarAlunoAux
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pesquisarAlunoAux`(p_aluno varchar(45))
begin 
		select  alumatricula,alunome, aludtnascimento, alusexo, alufoto, alunaturalidade, estnome, 
				turdescricao, tnonome, infcodigo, infpergunta1, infpergunta2, infpergunta3, infpergunta4, infpergunta5,
                infpergunta6, infpergunta7, infpergunta8, infpergunta9, infobs, resnome, reslocaltrabalho, 
                resprofissao, resfonetrabalho, resgrauparentesco, fontelefone, endcep 

			from aluno 
				inner join informacao on infcodigo = alu_infcodigo
                inner join estado on estcodigo = alu_estcodigo
				inner join turma on turcodigo = alu_turcodigo
                inner join turno on tnocodigo = alu_tnocodigo
				inner join nm_res_alu on nm_alucodigo = alucodigo
                inner join responsavel on rescodigo = nm_rescodigo
                inner join fone on fon_rescodigo = rescodigo
                inner join endereco on endcodigo = res_endcodigo
			where alunome like concat('%', p_aluno,'%') or alumatricula like concat('%', p_aluno,'%'); 
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_pesquisarResponsavelMae
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pesquisarResponsavelMae`(p_aluno varchar(45), p_matricula varchar(1000))
begin 
	select  rescodigo, COALESCE(resnome,0) as resnome, COALESCE(reslocaltrabalho,0) as reslocaltrabalho,COALESCE(resprofissao,0) as resprofissao,
    COALESCE(resfonetrabalho,0) as resfonetrabalho, resgrauparentesco, COALESCE(fontelefone,0) as fontelefone ,endcodigo, endcep, 
    endrua, endnumero, endbairro
		from responsavel 
			inner join fone on fon_rescodigo = rescodigo
			inner join endereco on endcodigo = res_endcodigo
			where  res_endcodigo in (select  alucodigo
											from aluno
												 where alucodigo =res_endcodigo 
													and resgrauparentesco="Mãe" 
                                                    and alumatricula = p_matricula
													and alunome like concat('%', p_aluno,'%'));
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_pesquisarResponsavelPai
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pesquisarResponsavelPai`(p_aluno varchar(45), p_matricula varchar(1000))
begin 
	select rescodigo, COALESCE(resnome,0) as resnome, reslocaltrabalho, resprofissao, resfonetrabalho, resgrauparentesco, fontelefone ,
			endcodigo, endcep, endrua, endnumero, endbairro
		from responsavel 
			inner join fone on fon_rescodigo = rescodigo
			inner join endereco on endcodigo = res_endcodigo
			where  res_endcodigo in (select  alucodigo
											from aluno
												 where alucodigo =res_endcodigo 
													and resgrauparentesco="Pai" 
                                                    and alumatricula = p_matricula
													and alunome like concat('%', p_aluno,'%'));
    end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure sp_pesquisarTurma
-- -----------------------------------------------------

DELIMITER $$
USE `bdcentroeducacional`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_pesquisarTurma`(p_estnome varchar(45))
begin 
		select turcodigo, turdescricao
        from turma
        where turdescricao like concat('%', p_estnome,'%'); 
    end$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
