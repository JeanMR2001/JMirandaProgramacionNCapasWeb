/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Municipio;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IMunicipioDAO {
    
    
    List<Municipio> GetAll();
    
    List<Municipio> GetByIdEstado(int IdEstado);
}
