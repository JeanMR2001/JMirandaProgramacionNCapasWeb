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

@Entity
public class Colonia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcolonia;
    
    @Basic
    private String nombre;
    private String codigopostal;
    
    @ManyToOne
    @JoinColumn(name = "idmunicipio")
    private Municipio municipio;

    public Colonia() {
    }

    public Colonia(int idcolonia, String nombre, String codigopostal, Municipio municipio) {
        this.idcolonia = idcolonia;
        this.nombre = nombre;
        this.codigopostal = codigopostal;
        this.municipio = municipio;
    }

    public Colonia(String nombre, String codigopostal, Municipio municipio) {
        this.nombre = nombre;
        this.codigopostal = codigopostal;
        this.municipio = municipio;
    }

    public int getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(int idcolonia) {
        this.idcolonia = idcolonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    
}
