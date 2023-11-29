/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.DependienteDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.DependientetipoDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EmpleadoDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EstadocivilDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Dependiente;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empleado;
import com.superproyecto.JMirandaProgramacionNCapasWeb.Util.Util;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/dependiente")
public class DependienteController {
   
    private DependienteDAOImplementation dependienteDAOImplementation;
    private DependientetipoDAOImplementation dependientetipoDAOImplementation;
    private EstadocivilDAOImplementation estadocivilDAOImplementation;
    private EmpleadoDAOImplementation empleadoDAOImplementation;
    
    public DependienteController(DependienteDAOImplementation dependienteDAOImplementation,
                                 DependientetipoDAOImplementation dependientetipoDAOImplementation,
                                 EstadocivilDAOImplementation estadocivilDAOImplementation,
                                 EmpleadoDAOImplementation empleadoDAOImplementation){
        this.dependienteDAOImplementation = dependienteDAOImplementation;
        this.dependientetipoDAOImplementation = dependientetipoDAOImplementation;
        this.estadocivilDAOImplementation = estadocivilDAOImplementation;
        this.empleadoDAOImplementation = empleadoDAOImplementation;
    }
    
    @GetMapping("/listado/{numeroempleado}")
    public String Dependiente(@PathVariable String numeroempleado, Model model){

        Empleado empleado = empleadoDAOImplementation.GetById(numeroempleado);
        List<Dependiente> dependientes = dependienteDAOImplementation.GetAll(numeroempleado);
        model.addAttribute("numeroempleado", numeroempleado);
        model.addAttribute("empleado", empleado);
        model.addAttribute("dependientes", dependientes);
        
        return "listadoDependientes";
    }
    
    @GetMapping("/form/{numeroempleado}/{iddependiente}")
    public String Form(@PathVariable("numeroempleado") String numeroempleado, 
                       @PathVariable("iddependiente") int iddependiente, Model model){
        
        model.addAttribute("estadosciviles", estadocivilDAOImplementation.GetAll());
        model.addAttribute("dependientetipos", dependientetipoDAOImplementation.GetAll());
       
        if(iddependiente == 0){
            Empleado empleado = new Empleado(numeroempleado);
            model.addAttribute("numberempleado", numeroempleado);
            model.addAttribute("dependiente", new Dependiente(empleado));
            
            return "formDependientes";
            
        }
        else {
            
            Dependiente dependiente = dependienteDAOImplementation.GetById(iddependiente);
            
            model.addAttribute("dependiente", dependiente);
            
            return "formDependientes";
        } 
    }
    
    @PostMapping("/form")
    public String Update(@Valid @ModelAttribute ("dependiente") Dependiente dependiente, Model model){
        
        
        if(dependiente.getIddependiente() > 0){
            dependienteDAOImplementation.Update(dependiente);
        } else {
            dependienteDAOImplementation.Add(dependiente);
        }
        
        return ("redirect:/dependiente/listado/" + dependiente.getEmpleado().getNumeroempleado());
    }
    
    @GetMapping("/eliminar/{iddependiente}")
    public String Delete(@PathVariable int iddependiente, Model model){
        
        Dependiente dependiente = dependienteDAOImplementation.GetById(iddependiente);
        
//        model.addAttribute("empleado", empleado);
        dependienteDAOImplementation.Delete(dependiente);
        
        return "redirect:/dependiente/listado/" + dependiente.getEmpleado().getNumeroempleado();
    }
}
