/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Colonia;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Rol;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IRolDAO {
    
    List<Rol> GetAll();
    
    List<Colonia> GetAllColonia();
}
