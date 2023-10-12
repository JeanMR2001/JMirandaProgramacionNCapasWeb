/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Estado;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IEstadoDAO {
    
    List<Estado> GetAll();
    
    List<Estado> GetByIdPais(int IdPais);
}
