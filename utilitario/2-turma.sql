use bdcentroeducacional;
-- -----------------------------------------------------
-- Table `turma`.`inserir`
-- -----------------------------------------------------

drop procedure if exists sp_inserirTurma;
delimiter #
create procedure sp_inserirTurma( p_estnome varchar(60) )
	begin 
		insert into turma values(turcodigo, p_estnome); 
    end #
delimiter ;
-- Call sp_inserirTurma('Primeira série');
-- Call sp_inserirTurma('Segunda série');
-- Call sp_inserirTurma('Terceira série');

-- -----------------------------------------------------
-- Table `turma`.`listar`
-- -----------------------------------------------------
drop procedure if exists sp_listarTurma;
delimiter #
create procedure sp_listarTurma()
	begin
		select  turcodigo, turdescricao
			from turma;
    end #
delimiter ;

call sp_listarTurma();

-- -----------------------------------------------------
-- Table `turma`.`pesquisar`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarTurma;

delimiter #
create procedure sp_pesquisarTurma(p_estnome varchar(45))
	begin 
		select turcodigo, turdescricao
        from turma
        where turdescricao like concat('%', p_estnome,'%'); 
    end #
    
delimiter ;

-- call sp_pesquisarTurma('primeira');

-- -----------------------------------------------------
-- Table `turma`.`alterar`
-- -----------------------------------------------------

drop procedure if exists sp_alterarTurma;

delimiter #
create procedure sp_alterarTurma( p_turcodigo int, p_turnome varchar(45) )
	begin
		update turma
        set turdescricao = p_turnome
        where turcodigo = p_turcodigo;
    end #
    
delimiter ;

-- call sp_alterarTurma(1,'escolinha2');

-- -----------------------------------------------------
-- Table `turma`.`excluir`
-- -----------------------------------------------------

drop procedure if exists sp_excluirTurma;

delimiter #
create procedure sp_excluirTurma(p_turdescricao varchar(60))
	begin 
		delete 
			from turma
			where turdescricao = p_turdescricao;
    end #

delimiter ;
-- call sp_excluirTurma(15);


