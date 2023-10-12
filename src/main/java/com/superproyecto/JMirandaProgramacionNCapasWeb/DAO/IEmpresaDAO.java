/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empresa;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IEmpresaDAO {
    
    List<Empresa> GetAll();
    
    int Add(Empresa empresa);
    
    void Update(Empresa empresa);
    
    void Delete(Empresa empresa);
    
    Empresa GetById(int idEmpresa);
    
}
