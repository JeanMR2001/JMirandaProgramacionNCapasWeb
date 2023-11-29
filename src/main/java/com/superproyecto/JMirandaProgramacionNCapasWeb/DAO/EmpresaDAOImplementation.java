/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empresa;
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
public class EmpresaDAOImplementation implements IEmpresaDAO{
    
    private EntityManager entityManager;
    
    @Autowired
    public EmpresaDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Empresa> GetAll() {
        TypedQuery<Empresa> query = entityManager.createQuery("FROM Empresa", Empresa.class);
        List<Empresa> empresas = query.getResultList();
        
        return empresas;
    }

    @Override
    @Transactional //indica que hara cambios en la BD
    public int Add(Empresa empresa) {
        entityManager.persist(empresa); //el persist indica que se agregara el usuario a la BD
        return empresa.getIdempresa();
    }

    @Override
    @Transactional
    public void Update(Empresa empresa) {
        entityManager.merge(empresa); //el merge realiza cambios en la BD, un update
    }

    @Override
    @Transactional
    public void Delete(Empresa empresa) {
        entityManager.remove(empresa);
    }

    @Override
    public Empresa GetById(int idEmpresa) {
        TypedQuery<Empresa> query = entityManager.createQuery("FROM Empresa WHERE idempresa =: id", Empresa.class);
        query.setParameter("id", idEmpresa);
       
        return query.getSingleResult();
    }

    
    
    
    
    
    
}
