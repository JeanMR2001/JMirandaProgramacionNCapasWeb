/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Dependientetipo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class DependientetipoDAOImplementation implements IDependientetipoDAO{

    private EntityManager entityMnagaer;
    
    @Autowired
    public DependientetipoDAOImplementation(EntityManager entityManager){
        this.entityMnagaer = entityManager;
    }
    
    @Override
    public List<Dependientetipo> GetAll() {
        TypedQuery<Dependientetipo> query = entityMnagaer.createQuery("FROM Dependientetipo", Dependientetipo.class);
        
        List<Dependientetipo> dependientestipos = query.getResultList();
        
        return dependientestipos;
    }
    
}
