use bdcentroeducacional;

insert into turno values (null,'Matutino');
insert into turno values (null,'Vespertino');
insert into turno values (null,'Noturno');
-- -----------------------------------------------------
-- Table `estado`.`listar`
-- -----------------------------------------------------
drop procedure if exists sp_listarTurno;
delimiter #
create procedure sp_listarTurno()
	begin
		select  tnocodigo, tnonome
			from turno;
    end #
delimiter ;

call sp_listarTurno();




