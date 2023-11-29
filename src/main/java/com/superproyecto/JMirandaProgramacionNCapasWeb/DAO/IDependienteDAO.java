/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Dependiente;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IDependienteDAO{
    
    List<Dependiente> GetAll(String idempleado);
    
    void Add(Dependiente dependiente);
    
    void Update(Dependiente dependiente);
    
    void Delete(Dependiente dependiente);
    
    Dependiente GetById(int iddependiente);
    
}
