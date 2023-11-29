/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Dependiente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddependiente;
    
    @ManyToOne
    @JoinColumn(name = "numeroempleado")
    private Empleado empleado;
    
    private String nombre;
    private String apellidopaterno;
    private String apellidomaterno;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechanacimiento;
    
    @ManyToOne
    @JoinColumn(name = "idestadocivil")
    private Estadocivil estadocivil;
    
    private String genero;
    private String telefono;
    private String rfc;
    
    @ManyToOne
    @JoinColumn(name = "iddependientetipo")
    private Dependientetipo dependientetipo;

    public Dependiente() {
    }

    public Dependiente(int iddependiente, Empleado empleado, String nombre, String apellidopaterno, String apellidomaterno, Date fechanacimiento, Estadocivil estadocivil, String genero, String telefono, String rfc, Dependientetipo dependientetipo) {
        this.iddependiente = iddependiente;
        this.empleado = empleado;
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.fechanacimiento = fechanacimiento;
        this.estadocivil = estadocivil;
        this.genero = genero;
        this.telefono = telefono;
        this.rfc = rfc;
        this.dependientetipo = dependientetipo;
    }

    public Dependiente(Empleado empleado, String nombre, String apellidopaterno, String apellidomaterno, Date fechanacimiento, Estadocivil estadocivil, String genero, String telefono, String rfc, Dependientetipo dependientetipo) {
        this.empleado = empleado;
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.fechanacimiento = fechanacimiento;
        this.estadocivil = estadocivil;
        this.genero = genero;
        this.telefono = telefono;
        this.rfc = rfc;
        this.dependientetipo = dependientetipo;
    }

    public int getIddependiente() {
        return iddependiente;
    }

    public void setIddependiente(int iddependiente) {
        this.iddependiente = iddependiente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public Estadocivil getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Estadocivil estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Dependientetipo getDependientetipo() {
        return dependientetipo;
    }

    public void setDependientetipo(Dependientetipo dependientetipo) {
        this.dependientetipo = dependientetipo;
    }

    public Dependiente(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    
    
    
    
    
}
