/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Colonia;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IColoniaDAO {
    
    List<Colonia> GetAll();
    
    List<Colonia> GetByIdMunicipio(int IdMunicipio);
    
}
