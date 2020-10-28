use bdcentroeducacional;

-- -----------------------------------------------------
-- Table `estado`.`inserir`
-- -----------------------------------------------------

insert into estado values (null,'Amazonas (AM)');
insert into estado values (null,'Acre (AC)');
insert into estado values (null,'Alagoas (AL)');
insert into estado values (null,'Amapá (AP)');
insert into estado values (null,'Bahia (BA)');
insert into estado values (null,'Ceará (CE)');
insert into estado values (null,'Distrito Federal (DF)');
insert into estado values (null,'Espírito Santo (ES)');
insert into estado values (null,'Goiás (GO)');
insert into estado values (null,'Maranhão (MA)');
insert into estado values (null,'Mato Grosso (MT)');
insert into estado values (null,'Mato Grosso do Sul (MS)');
insert into estado values (null,'Minas Gerais (MG)');
insert into estado values (null,'Pará (PA)');
insert into estado values (null,'Paraíba (PB)');
insert into estado values (null,'Paraná (PR)');
insert into estado values (null,'Pernambuco (PE)');
insert into estado values (null,'Piauí (PI)');
insert into estado values (null,'Rio de Janeiro (RJ)');
insert into estado values (null,'Rio Grande do Norte (RN)');
insert into estado values (null,'Rio Grande do Sul (RS)');
insert into estado values (null,'Rondônia (RO)');
insert into estado values (null,'Roraima (RR)');
insert into estado values (null,'Santa Catarina (SC)');
insert into estado values (null,'São Paulo (SP)');
insert into estado values (null,'Sergipe (SE)');
insert into estado values (null,'Tocantins (TO)');

-- -----------------------------------------------------
-- Table `estado`.`listar`
-- -----------------------------------------------------
drop procedure if exists sp_listarEstado;
delimiter #
create procedure sp_listarEstado()
	begin
		select  estcodigo, estnome
			from estado;
    end #
delimiter ;

call sp_listarEstado();





