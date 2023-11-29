/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Estadocivil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class EstadocivilDAOImplementation implements IEstadocivilDAO{

    private EntityManager entityManager;
    
    public EstadocivilDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Estadocivil> GetAll() {
        TypedQuery<Estadocivil> query = entityManager.createQuery("FROM Estadocivil", Estadocivil.class);
        
        List<Estadocivil> estadosciviles = query.getResultList();
        
        return estadosciviles;
    }
    
}
