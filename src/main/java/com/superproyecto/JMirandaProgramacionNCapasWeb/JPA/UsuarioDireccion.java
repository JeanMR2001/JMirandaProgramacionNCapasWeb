/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.JPA;

import java.util.List;


public class UsuarioDireccion {
    
    private Usuario usuario;
    private Direccion direccion;

    public UsuarioDireccion() {
    }

    public UsuarioDireccion(Usuario usuario, Direccion direccion) {
        this.usuario = usuario;
        this.direccion = direccion;
    }
    
    public UsuarioDireccion(List<Usuario> usuario, List<Direccion> direccion) {
        this.usuario = (Usuario) usuario;
        this.direccion = (Direccion) direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    
}
