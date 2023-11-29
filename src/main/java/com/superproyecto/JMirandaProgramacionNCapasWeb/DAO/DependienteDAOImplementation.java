/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Dependiente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class DependienteDAOImplementation implements IDependienteDAO{
    
    
    private EntityManager entityManager;
    
    @Autowired
    public DependienteDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Dependiente> GetAll(String idempleado) {
        TypedQuery<Dependiente> query = entityManager.createQuery("FROM Dependiente WHERE empleado.numeroempleado =: numeroemp", Dependiente.class);
        query.setParameter("numeroemp", idempleado);
        List<Dependiente> dependientes = query.getResultList();
        
        return dependientes;
    }
    
    @Transactional
    @Override
    public void Add(Dependiente dependiente) {
        
        entityManager.persist(dependiente);
    }

    @Transactional
    @Override
    public void Update(Dependiente dependiente) {
        entityManager.merge(dependiente);
    }

    @Transactional
    @Override
    public void Delete(Dependiente dependiente) {
        entityManager.remove(dependiente);
    }

    @Override
    public Dependiente GetById(int iddependiente) {
        TypedQuery<Dependiente> query = entityManager.createQuery("FROM Dependiente WHERE iddependiente =: id", Dependiente.class);
        query.setParameter("id", iddependiente);
        
        Dependiente dependiente = query.getSingleResult();
        return dependiente;
    }
    
}
