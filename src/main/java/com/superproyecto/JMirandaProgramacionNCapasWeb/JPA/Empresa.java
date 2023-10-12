/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.JPA;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 *
 * @author digis
 */
@Entity
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idempresa;
    
    @Basic
    private String nombre;
    private String telefono;
    private String email;
    private String direccionweb;
    
    @Lob
    private String imagen;

    public Empresa() {
    }

    public Empresa(int idempresa, String nombre, String telefono, String email, String direccionweb, String imagen) {
        this.idempresa = idempresa;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccionweb = direccionweb;
        this.imagen = imagen;
    }

    public Empresa(String nombre, String telefono, String email, String direccionweb, String imagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccionweb = direccionweb;
        this.imagen = imagen;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccionweb() {
        return direccionweb;
    }

    public void setDireccionweb(String direccionweb) {
        this.direccionweb = direccionweb;
    }
    
    
}
