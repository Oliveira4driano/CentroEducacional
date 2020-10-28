use bdcentroeducacional;
-- SET GLOBAL validate_password.policy=LOW;
-- -----------------------------------------------------
-- Table `Usuario`.`Criar Usuario`
-- -----------------------------------------------------
drop procedure if exists sp_criarUsuario;
delimiter #
create procedure sp_criarUsuario(p_usuario varchar(50), p_senha varchar(50), p_host varchar(50), p_selecionar char(1), p_inserir char(1), p_atualizar char(1), p_deletar char(1)/* p_diasexpirar int ,*/)
begin
	declare variavel varchar(150);
     -- SET GLOBAL validate_password.policy=LOW; //apenas para mysql 8
    set @variavel = concat('create user ', p_usuario, '@',p_host, ' IDENTIFIED BY \'', p_senha/*, '\' PASSWORD EXPIRE INTERVAL ', p_diasexpirar, ' DAY*/,'\';');
	
    prepare execucao from @variavel;
    execute execucao;
	UPDATE `mysql`.`user` SET `Select_priv` = p_selecionar, `Insert_priv` = p_inserir, `Update_priv` = p_atualizar, `Delete_priv` = p_deletar WHERE (`Host` = p_host) and (`User` = p_usuario);
    deallocate prepare execucao;
    /*insert into usuario values (usucodigo, p_usuario);*/
end#
delimiter ;
-- call sp_criarUsuario('admin2', '12345678','localhost');
-- -----------------------------------------------------
-- Table `usuario`.`listar`
-- -----------------------------------------------------
drop procedure if exists sp_listarUsuario;
delimiter #
create procedure sp_listarUsuario()
	begin
		select User as usuario, Select_priv, `Insert_priv`, `Update_priv`, `Delete_priv`
			from mysql.user
				where User !='debian-sys-maint' and User != 'mysql.infoschema' and User != 'mysql.session' and User !='mysql.sys';
    end #
delimiter ;

call sp_listarUsuario();

-- -----------------------------------------------------
-- Table `Usuario`.`Excluir`
-- -----------------------------------------------------
drop procedure if exists sp_excluirUsuario;
delimiter #
	create procedure sp_excluirUsuario(p_usuario varchar(50), p_host varchar(50))
    begin 
		delete 
        from  mysql.user
         WHERE (`Host` = p_host) and (`User` = p_usuario);
    end #

delimiter ;
-- call sp_excluirUsuario('administrador','localhost');

-- -----------------------------------------------------
-- Table `Usuario`.`Alterar`
-- -----------------------------------------------------
    
drop procedure if exists sp_alterarUsuario;
delimiter #
create procedure sp_alterarUsuario(p_usuario varchar(50), p_novaSenha varchar(50),p_host varchar(50), p_selecionar char(1), p_inserir char(1), p_atualizar char(1), p_deletar char(1)/* p_diasexpirar int ,*/)
begin
	declare variavel varchar(150);
  
    set @variavel = concat('ALTER USER ', p_usuario, '@',p_host,' IDENTIFIED BY \'', p_novaSenha/*, '\' PASSWORD EXPIRE INTERVAL ', p_novoDia, ' DAY;*/,'\';');
    prepare execucao from @variavel;
    execute execucao;
    UPDATE `mysql`.`user` SET `Select_priv` = p_selecionar, `Insert_priv` = p_inserir, `Update_priv` = p_atualizar, `Delete_priv` = p_deletar WHERE (`Host` = p_host) and (`User` = p_usuario);
    deallocate prepare execucao;
end#
delimiter ;

-- call sp_alterarUsuario('adriano','12345678','localhost','Y','Y','N','N');

-- -----------------------------------------------------
-- Table `Usuario`.`pesquisar`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarUsuario;

delimiter #
create procedure sp_pesquisarUsuario(p_usuario varchar(45))
	begin 
		select User, Select_priv, Insert_priv, Update_priv, Delete_priv
        from mysql.user
        where User like concat('%', p_usuario,'%'); 
    end #
    
delimiter ;

-- call sp_pesquisarUsuario('adriano');




-- create user 'administrador'@'localhost' identified by 'admim123';
-- grant all privileges on bdcentroeducacional . * to 'administrador'@'localhost';
-- flush privileges;
-- Para alterar o parâmetro validate_password.policy, basta executar o comando abaixo.
-- select * from mysql.proc;
 
 -- Para verificar o status do validade
 -- SHOW VARIABLES LIKE 'validate_password%';


-- GRANT SELECT, CREATE, UPDATE, DELETE, ALL> PRIVILEGES ON mysql.proc TO '<user>'@'%';
-- FLUSH PRIVILEGES;

-- select *from mysql.user;


