-- -----------------------------------------------------
-- Table `Aluno`.`inserir`   matricula: ano, mes, cod aluno, turma
-- -----------------------------------------------------
use bdcentroeducacional;

drop procedure if exists sp_inserirAluno;
delimiter #
create procedure sp_inserirAluno( 
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
    end #
delimiter ;
-- Call sp_inserirAluno('adriano de oliveira', '2020-04-20', 'm','blob11', 'manaus','ativo', 1,1,1);

-- -----------------------------------------------------
-- Table `Aluno`.`listar`
-- -----------------------------------------------------
drop procedure if exists sp_listarAluno;
delimiter #
create procedure sp_listarAluno()
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
    end #
delimiter ;
call sp_listarAluno();

-- -----------------------------------------------------
-- Table `Aluno`.`Pesquisar`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarAluno;

delimiter #
create procedure sp_pesquisarAluno(p_aluno varchar(45))
	begin 
		select  alumatricula, alunome, alusexo, tnonome, turdescricao, 
			(TIMESTAMPDIFF(YEAR,aludtnascimento,current_date())) as idade
			from aluno
				left join turno on tnocodigo = alu_tnocodigo
				left outer join turma on turcodigo = alu_turcodigo
				left outer join nm_res_alu on nm_alucodigo = alucodigo
             -- left outer join responsavel on rescodigo = nm_rescodigo
			where alunome like concat('%', p_aluno,'%') or alumatricula like concat('%', p_aluno,'%'); 
    end #
    
delimiter ;
-- call sp_pesquisarAluno(1);

-- -----------------------------------------------------
-- Table `Aluno`.`PesquisarAux`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarAlunoAux;
delimiter #
create procedure sp_pesquisarAlunoAux(p_aluno varchar(45))
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
    end #
    
delimiter ;
    
call sp_pesquisarAlunoAux('4'); 
        
-- -----------------------------------------------------
-- Table `turma`.`excluir`
-- -----------------------------------------------------

drop procedure if exists sp_excluirAluno;
delimiter #
create procedure sp_excluirAluno(p_alumatricula varchar(1000))
	begin 
		delete  fone, endereco, informacao,responsavel, nm_res_alu,  aluno
			from aluno
				left join informacao on infcodigo = alu_infcodigo
                left join nm_res_alu on nm_alucodigo = alucodigo
                left join responsavel on rescodigo = nm_rescodigo
                left join endereco on endcodigo = res_endcodigo
                left join fone on fon_rescodigo = rescodigo
			where alumatricula = p_alumatricula;
    end #

delimiter ;
call sp_excluirAluno('20201011');   
-- -----------------------------------------------------
-- Table `Aluno`.`alterar`
-- -----------------------------------------------------
drop procedure if exists sp_alterarAluno;

delimiter #
create procedure sp_alterarAluno(p_matricula varchar(1000), p_aluno varchar(80), p_dtnasci date, p_sexo char(1), p_foto longblob, p_naturalidade varchar(45), 
								p_estado int, p_turma int, p_turno int )
	begin
		update aluno
        set alunome = p_aluno, aludtnascimento = p_dtnasci, alusexo = p_sexo, alufoto = p_foto, alunaturalidade = p_naturalidade, 
			alu_estcodigo = p_estado, alu_turcodigo= p_turma , alu_tnocodigo = p_turno
        where alumatricula = p_matricula;
        
	
    end #
delimiter ;
-- call sp_alterarAluno('20201042', 'aluno4','1989-10-14', 'F', '','acriano',2,2,1);

												
 select * from turno;  
select * from turma;
select * from estado;                                                   

select * from fone;
select * from endereco;
select * from responsavel;
select * from nm_res_alu;
select * from aluno; 
select * from informacao;



    

		


