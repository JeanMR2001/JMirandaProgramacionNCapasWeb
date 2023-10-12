/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empleado;
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
public class EmpleadoDAOImplementation implements IEmpleadoDAO{

    
    private EntityManager entityManager;
    
    @Autowired
    public EmpleadoDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Empleado> GetAll() {
        TypedQuery<Empleado> query = entityManager.createQuery("From Empleado", Empleado.class);
        
        List<Empleado> empleados = query.getResultList();
        
        return empleados;
    }

    @Override
    @Transactional
    public String Add(Empleado empleado) {
        
        entityManager.persist(empleado);
        return empleado.getNumeroempleado();
    }

    @Override
    @Transactional
    public void Update(Empleado empleado) {
        entityManager.merge(empleado);
    }

    @Override
    @Transactional
    public void Delete(Empleado empleado) {
        entityManager.remove(empleado);
    }

    @Override
    public Empleado GetById(String numeroEmpleado) {
        TypedQuery<Empleado> query = entityManager.createQuery("FROM Empleado WHERE numeroempleado =: id", Empleado.class);
        query.setParameter("id", numeroEmpleado);
        
        Empleado empleado = query.getSingleResult();
        return empleado;
    }
    
}
