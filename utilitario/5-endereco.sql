use bdcentroeducacional;

-- -----------------------------------------------------
-- Table `endereco`.`inserir`
-- -----------------------------------------------------

drop procedure if exists sp_inserirEndereco;

delimiter #
create procedure sp_inserirEndereco(p_cep varchar(9), p_rua varchar(50), p_numero varchar(10), p_bairro varchar(50) )
	begin
		insert into endereco values (null,p_cep, p_rua, p_numero, p_bairro);
    end #


delimiter ;

-- call sp_inserirEndereco('69037-003', 'rua k', '102a', 'nova k');


-- -----------------------------------------------------
-- Table `endereco`.`alterar`
-- -----------------------------------------------------

drop procedure if exists sp_alterarEndereco;

delimiter #
create procedure sp_alterarEndereco( p_endcodigo int, p_cep varchar(9), p_rua varchar(50), p_numero varchar(10), p_bairro varchar(50))
	begin
		update endereco
        set endcep = p_cep, endrua = p_rua, endnumero = p_numero, endbairro = p_bairro 
        where endcodigo = p_endcodigo;

    end #
delimiter ;

-- call sp_alterarEndereco('1', '69037-500', 'max teixeira', '11', 'flores');
select * from endereco;






