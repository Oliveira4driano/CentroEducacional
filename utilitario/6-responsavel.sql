use bdcentroeducacional;

drop procedure if exists sp_inserirResponsavel;

delimiter #
create procedure sp_inserirResponsavel(p_nome varchar(50), p_localtrabalho varchar(50), p_profissao varchar(50), p_fonetrabalho varchar(10), p_parentesco varchar(20), p_fone varchar(15))
	begin
		declare v_resbaicodigo int default 0;
        declare v_resfone int default 0;
		set v_resbaicodigo = ((select coalesce(max(endcodigo),0) from endereco));
        set v_resfone = ((select coalesce(max(rescodigo),0) from responsavel));
        insert into responsavel
			values(null, p_nome, p_localtrabalho, p_profissao, p_fonetrabalho, p_parentesco,v_resbaicodigo);
		insert into fone 
			values(p_fone, v_resfone+1);
    end #

delimiter ;

-- call sp_inserirResponsavel('adriano', 'tjam','programador', '3656-0021', 'pai', '(92)99292-1431');
-- ALTER TABLE fone CHANGE COLUMN fontelefone fontelefone  VARCHAR(14);
-- desc fone;


-- -----------------------------------------------------
-- Table `ResponsavelMae`.`PesquisarMae`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarResponsavelMae;
delimiter #
create procedure sp_pesquisarResponsavelMae(p_aluno varchar(45), p_matricula varchar(1000))
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
    end #
delimiter ;

-- call sp_pesquisarResponsavelMae('aluno2','20201022');

-- -----------------------------------------------------
-- Table `ResponsavelPai`.`PesquisarPai`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarResponsavelPai;
delimiter #
create procedure sp_pesquisarResponsavelPai(p_aluno varchar(45), p_matricula varchar(1000))
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
    end #
delimiter ;

-- call sp_pesquisarResponsavelPai('aluno','20201011');

-- -----------------------------------------------------
-- Table `Responsavel`.`alterar`
-- -----------------------------------------------------

drop procedure if exists sp_alterarResponsavel;

delimiter #
create procedure sp_alterarResponsavel( p_res_endcodigo int, p_resnome varchar(50), p_reslocaltrabalho varchar(50), 
										p_resprofissao varchar(50), p_resfonetrabalho varchar(10), p_fonetelefone varchar(15),p_resgrauparentesco varchar(20)
                                        )
	begin
		update responsavel
        set resnome = p_resnome, reslocaltrabalho = p_reslocaltrabalho, resprofissao = p_resprofissao, resfonetrabalho = p_resfonetrabalho 
        where res_endcodigo = p_res_endcodigo and resgrauparentesco = p_resgrauparentesco;
        
        update fone
			set fontelefone = p_fonetelefone
				where fon_rescodigo = 	p_res_endcodigo;
    end #
delimiter ;
-- call sp_alterarResponsavel(3,'Ma3','trabalhoMae3', 'profissaoMae3', '1111-1111','(11) 22222-1111', 'Mãe');

select * from endereco;
select * from responsavel;
select * from fone;


            