/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author digis
 */
@Entity
public class Dependientetipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddependientetipo;
    
    private String nombre;

    public Dependientetipo() {
    }

    public Dependientetipo(int iddependientetipo, String nombre) {
        this.iddependientetipo = iddependientetipo;
        this.nombre = nombre;
    }

    public int getIddependientetipo() {
        return iddependientetipo;
    }

    public void setIddependientetipo(int iddependientetipo) {
        this.iddependientetipo = iddependientetipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
