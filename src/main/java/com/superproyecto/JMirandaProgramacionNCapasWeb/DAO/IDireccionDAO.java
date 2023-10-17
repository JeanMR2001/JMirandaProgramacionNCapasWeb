/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Direccion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.UsuarioDireccion;

import java.util.List;
import org.springframework.stereotype.Repository;


public interface IDireccionDAO {
    
    List<Direccion> GetAll();
    
    void Add(Direccion direccion);
    
    void Update(Direccion direccion);
    
    void Delete(Direccion direccion);
    
    Direccion GetById(int idusuarioE);
    
//    Direccion Delete(int iddireccion);
    
    List<UsuarioDireccion> GetAllUD();
}
//kjgkjgk soy milton :))) ontabas comiendo yo penese que ya no querias hablarme :(