/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.JPA;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author digis
 */
@Entity
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "SEQ", sequenceName = "ISEQ$$_73083")
        //@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
        //@SequenceGenerator(name = "SEQ", sequenceName = "Alum_SEQ")
    private int idusuario;
    
    
    @Basic
    @NotEmpty(message = "No puede estar vacio, ingresa datos")
    private String username;
    @NotEmpty(message = "No puede estar vacio, ingresa datos")
    private String nombre;
    @NotEmpty(message = "No puede estar vacio, ingresa datos")
    private String apellidopaterno;

    private String apellidomaterno;
    
    @NotEmpty(message = "No puede estar vacio el email")
    private String email;
    
    @Size(min = 8, message = "La contraseña debe tener minimo 8 caracteres")
    @NotEmpty(message = "No puede estar vacio el password")
    private String password;
    
    @NotEmpty(message = "Selecciona una opción de Sexo")
    private String sexo;
    
    @NotEmpty(message = "No puede estar vacio, ingresa datos")
    @Size(max = 10, message = "El telefono no puede tener más de 10 digitos")
    private String telefono;
    
    @NotEmpty(message = "No puede estar vacio, ingresa datos")
    @Size(max = 10, message = "El Celular no puede tener más de 10 digitos")
    private String celular;
    
    @NotEmpty(message = "No puede estar vacio, ingresa datos")
    @Size(max = 18, message = "El CURP solo puede tener más de 18 caracteres")
    private String curp;
    
    @Lob
    private String imagen;
    
    
    private int status;
    //private int idrol;
    //Rol rol;
    @ManyToOne
    @JoinColumn (name = "idrol")
    private Rol rol;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechanacimiento;

    public Rol getRol() {
        return rol;
    }
    public Usuario(String nombre, String apellidopaterno, String apellidomaterno) {
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
    }

    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Usuario(){
        
    }

    public Usuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(int idusuario, String username, String nombre, String apellidopaterno, String apellidomaterno, String email, String password, String sexo, String telefono, String celular, String curp, String imagen, Rol rol, Date fechanacimiento) {
        this.idusuario = idusuario;
        this.username = username;
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.email = email;
        this.password = password;
        this.sexo = sexo;
        this.telefono = telefono;
        this.celular = celular;
        this.curp = curp;
        this.imagen = imagen;
        this.rol = rol;
        this.fechanacimiento = fechanacimiento;
    }

    public Usuario(String username, String nombre, String apellidopaterno, String apellidomaterno, String email, String password, String sexo, String telefono, String celular, String curp, String imagen, Rol rol, Date fechanacimiento) {
        this.username = username;
        this.nombre = nombre;
        this.apellidopaterno = apellidopaterno;
        this.apellidomaterno = apellidomaterno;
        this.email = email;
        this.password = password;
        this.sexo = sexo;
        this.telefono = telefono;
        this.celular = celular;
        this.curp = curp;
        this.imagen = imagen;
        this.rol = rol;
        this.fechanacimiento = fechanacimiento;
    }
    
    
    

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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
    
    
    
    
    
    
}
