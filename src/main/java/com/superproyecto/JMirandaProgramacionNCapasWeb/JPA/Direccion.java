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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Direccion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddireccion;
    
    @Basic
    @NotEmpty(message = "No puede estar vacio, ingrese una calle")
    private String calle;
    
    @NotEmpty(message = "No puede estar vacio, ingrese un numero interior")
    private String numerointerior;
    
    private String numeroexterior;
    
    @ManyToOne
    @JoinColumn (name = "idcolonia")
    private Colonia colonia;
    
    @OneToOne
    @JoinColumn (name = "idusuario")
    private Usuario usuario;

    public Direccion() {
    }

    public Direccion(Usuario usuario) {
        this.usuario = usuario;
    }

    

    public Direccion(int iddireccion, String calle, String numerointerior, String numeroexterior, Colonia colonia, Usuario usuario) {
        this.iddireccion = iddireccion;
        this.calle = calle;
        this.numerointerior = numerointerior;
        this.numeroexterior = numeroexterior;
        this.colonia = colonia;
        this.usuario = usuario;
    }

    public Direccion(String calle, String numerointerior, String numeroexterior, Colonia colonia, Usuario usuario) {
        this.calle = calle;
        this.numerointerior = numerointerior;
        this.numeroexterior = numeroexterior;
        this.colonia = colonia;
        this.usuario = usuario;
    }

    public int getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(int iddireccion) {
        this.iddireccion = iddireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumerointerior() {
        return numerointerior;
    }

    public void setNumerointerior(String numerointerior) {
        this.numerointerior = numerointerior;
    }

    public String getNumeroexterior() {
        return numeroexterior;
    }

    public void setNumeroexterior(String numeroexterior) {
        this.numeroexterior = numeroexterior;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
