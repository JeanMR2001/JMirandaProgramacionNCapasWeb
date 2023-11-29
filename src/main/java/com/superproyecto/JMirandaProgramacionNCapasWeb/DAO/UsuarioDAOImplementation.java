/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.DAO;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Direccion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Rol;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementation implements IUsuarioDAO {

    private EntityManager entityManager;

    @Autowired
    public UsuarioDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Usuario> GetAll(Usuario usuario) {
        TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario WHERE LOWER(nombre) LIKE LOWER(:nombreusuario) AND LOWER(apellidopaterno) LIKE LOWER(:paterno) AND LOWER(apellidomaterno) LIKE LOWER(:materno)", Usuario.class);
        query.setParameter("nombreusuario", ('%' + usuario.getNombre() + '%'));
        query.setParameter("paterno", ('%' + usuario.getApellidopaterno()+ '%'));
        query.setParameter("materno", ('%' + usuario.getApellidomaterno()+ '%'));
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }
    
    

    @Override
    @Transactional //realiza cambios en la base de datos
    public int Add(Usuario usuario) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        entityManager.persist(usuario);
        
        return usuario.getIdusuario();
    }

    @Override
    public Usuario GetById(int idusuarioEdit) {
        TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario WHERE idusuario=:idusuarioEdit", Usuario.class);
        query.setParameter("idusuarioEdit", idusuarioEdit);
        
        Usuario usuario = query.getSingleResult();
        
        return usuario;
    }

    @Override
    @Transactional
    public void Update(Usuario usuario) {
        
        entityManager.merge(usuario);
        
        
    }

    @Override
    @Transactional
    public void Delete(Usuario usuario) {
        entityManager.remove(usuario);
    }

    @Override
    @Transactional
    public void ChangeStatus(int idUsuario, boolean status) {
        
        Usuario usuario = entityManager.find(Usuario.class, idUsuario);
           
        usuario.setStatus((status)? 1 : 0);
        entityManager.merge(usuario);
        
    }

    @Override
    public Usuario GetByEmail(String email) {
        
        TypedQuery<Usuario> query = entityManager.createQuery("FROM Usuario WHERE email =: emailbusq", Usuario.class);
        query.setParameter("emailbusq", email);
        
        Usuario usuario;
        
        try{
           usuario = query.getSingleResult();
       } catch(Exception ex) {
           
           usuario = new Usuario();
       }
        
        return usuario;
        
    }

    

    
    
    

    
}
