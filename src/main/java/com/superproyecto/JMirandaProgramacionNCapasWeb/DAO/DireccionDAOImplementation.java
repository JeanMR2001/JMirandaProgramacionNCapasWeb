/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Direccion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.UsuarioDireccion;
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
public class DireccionDAOImplementation implements IDireccionDAO{

    private EntityManager entityManager;
    
    @Autowired
    public DireccionDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Direccion> GetAll() {
        TypedQuery<Direccion> query = entityManager.createQuery("FROM Direccion", Direccion.class); //se usa JPQL para el query
        List<Direccion> direcciones = query.getResultList();
        return direcciones;
    }

    @Override
    @Transactional
    public void Add(Direccion direccion) {
        entityManager.persist(direccion);
    }
    
    

    @Override
    public List<UsuarioDireccion> GetAllUD() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Direccion GetById(int idusuarioE) {
        TypedQuery<Direccion> query = entityManager.createQuery("FROM Direccion WHERE usuario.idusuario=: id", Direccion.class);
        query.setParameter("id", idusuarioE);
       Direccion direccion;
       
       try{
           direccion = query.getSingleResult();
       } catch(Exception ex) {
           
           direccion = new Direccion();
       }
        
        return direccion;
    }

    @Override
    @Transactional
    public void Update(Direccion direccion) {
        
        entityManager.merge(direccion);
    }
    
    
    
}
