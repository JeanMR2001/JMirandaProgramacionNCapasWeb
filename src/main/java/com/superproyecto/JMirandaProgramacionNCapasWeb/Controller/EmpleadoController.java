/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EmpleadoDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EmpresaDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empleado;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empresa;
import com.superproyecto.JMirandaProgramacionNCapasWeb.Util.Util;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
    
    private EmpleadoDAOImplementation empleadoDAOImplementation;
    private EmpresaDAOImplementation empresaDAOImplementation;
    
    @Autowired
    public EmpleadoController(EmpleadoDAOImplementation empleadoDAOImplementation, EmpresaDAOImplementation empresaDAOImplemetation){
        this.empleadoDAOImplementation = empleadoDAOImplementation;
        this.empresaDAOImplementation = empresaDAOImplemetation;
    }
    
    @GetMapping("/listado")
    public String ListadoDeEmpleados(Model model){
        
        List<Empleado> empleados = empleadoDAOImplementation.GetAll();
        model.addAttribute("lempleados", empleados);
        
        List<Empresa> empresas = empresaDAOImplementation.GetAll();
        model.addAttribute("lempresas", empresas);
        
        return "listadoEmpleados";
    }
    
    @GetMapping("/form/{numeroempleado}")
    public String Form(@PathVariable String numeroempleado, Model model){
        
        String nuevo = "new";
        model.addAttribute("empresas", empresaDAOImplementation.GetAll());
        
        if(numeroempleado.equals(nuevo)){
            model.addAttribute("empleado", new Empleado());
            
            return "formEmpleado";
        }
        else {
            Empleado empleado = empleadoDAOImplementation.GetById(numeroempleado);
            model.addAttribute("empleado", empleado);
            return "formEmpleado";
        }
    }
    
    @PostMapping("/form")
    public String Update(@Valid @ModelAttribute ("empleado") Empleado empleado, @RequestParam("imagenFile") MultipartFile imagenFile, Model model){
        
        String imagen64 = Util.ConvertirBase64(imagenFile);
        empleado.setFoto(imagen64);
        
        if(empleado.getNumeroempleado() != "new"){
            empleadoDAOImplementation.Update(empleado);
        } else {
            empleadoDAOImplementation.Add(empleado);
        }
        
        return ("redirect:/empleado/listado");
    }
    
    @GetMapping("/eiminar/{numeroempleado}")
    public String Delete(@PathVariable String numeroEmpleado, Model model){
        
        Empleado empleado = empleadoDAOImplementation.GetById(numeroEmpleado);
        model.addAttribute("empleado", empleado);
        
        empleadoDAOImplementation.Delete(empleado);
        
        return "redirect:/empleado/listado";
    }
    
    
    
}
