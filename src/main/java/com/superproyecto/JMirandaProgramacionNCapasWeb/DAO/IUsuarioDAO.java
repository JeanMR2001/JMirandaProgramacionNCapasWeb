/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Rol;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Usuario;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IUsuarioDAO {
 
    //firma de metodo
    //tipo de retorno, nombre del metodo, parametros
    List<Usuario> GetAll(Usuario usuario);
    
    int Add(Usuario usuario);
    
    Usuario GetById(int idusuarioEdit);
    
    void Update(Usuario usuario);
    
    void Delete(Usuario usuario);
    
    void ChangeStatus(int idUsuario, boolean status);
    
    Usuario GetByEmail(String email);
}
