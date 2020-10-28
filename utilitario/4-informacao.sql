use bdcentroeducacional;
-- -----------------------------------------------------
-- Table `informacao`.`inserir`
-- -----------------------------------------------------
drop procedure if exists sp_inserirInformacao;

delimiter #
create procedure sp_inserirInformacao(p_inf1 varchar(45), p_inf2 varchar(45), p_inf3 varchar(45), p_inf4 char(48),
										p_inf5 char(1), p_inf6 char(1), p_inf7 varchar(45), p_inf8 varchar(45), p_inf9 varchar(45), p_obs varchar(100))
	begin
		insert into informacao values (null,p_inf1, p_inf2, p_inf3, p_inf4, p_inf5, p_inf6, p_inf7, p_inf8, p_inf9, p_obs);
    end #


delimiter ;

-- call sp_inserirInformacao('p1', 'p2', 'p3','p4', 'S', 'S', 'p7', 'p8', 'p9', null);
-- -----------------------------------------------------
-- Table `informacao`.`alterar`
-- -----------------------------------------------------
drop procedure if exists sp_alterarInformacao;

delimiter #
create procedure sp_alterarInformacao( p_infcodigo int, p_inf1 varchar(45), p_inf2 varchar(45), p_inf3 varchar(45), p_inf4 char(48),
										p_inf5 char(1), p_inf6 char(1), p_inf7 varchar(45), p_inf8 varchar(45), p_inf9 varchar(45), p_obs varchar(100))
	begin
		update informacao
        set infpergunta1 = p_inf1 , infpergunta2 = p_inf2, infpergunta3 = p_inf3, infpergunta4 = p_inf4, infpergunta5 = p_inf5, infpergunta6 = p_inf6,
				infpergunta7 = p_inf7, infpergunta8 = p_inf8, infpergunta9 = p_inf9, infobs = p_obs 
        where infcodigo = p_infcodigo;

    end #
delimiter ;

-- call sp_alterarInformacao(1,"S","N","N", "","N", "N", "N","N", "N","sfasdsfsfasf");

select * from informacao;
