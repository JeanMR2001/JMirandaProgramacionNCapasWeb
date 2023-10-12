/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empleado;
import java.util.List;

/**
 *
 * @author digis
 */

public interface IEmpleadoDAO {
    
    List<Empleado> GetAll();
    
    String Add(Empleado empleado);
    
    void Update(Empleado empleado);
    
    void Delete(Empleado empleado);
    
    Empleado GetById(String numeroEmpleado);
    
}
