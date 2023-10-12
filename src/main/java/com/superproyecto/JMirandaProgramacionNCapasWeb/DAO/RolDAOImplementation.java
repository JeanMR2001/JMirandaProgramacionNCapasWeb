/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Colonia;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Estado;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImplementation implements IRolDAO{
    
    EntityManager entityManager;
    
    public RolDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Rol> GetAll() {
        TypedQuery<Rol> query = entityManager.createQuery("FROM Rol", Rol.class);
        List<Rol> roles = query.getResultList();
        return roles;
        
    }

    @Override
    public List<Colonia> GetAllColonia() {
        TypedQuery<Colonia> query = entityManager.createQuery("FROM Colonia", Colonia.class);
        List<Colonia> colonias = query.getResultList();
        return colonias;
    }
    
}
