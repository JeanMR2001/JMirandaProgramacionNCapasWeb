/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EmpresaDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empresa;
import com.superproyecto.JMirandaProgramacionNCapasWeb.Util.Util;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/empresa")
public class EmpresaController {
    
    
    private EmpresaDAOImplementation empresaDAOImplementation;
    
    
    @Autowired
    public EmpresaController(EmpresaDAOImplementation empresaDAOImplementation){
        this.empresaDAOImplementation = empresaDAOImplementation;
    }
    
    
    @GetMapping("/listado")
    private String listadoDeEmpresa(Model model){
        
        List<Empresa> empresas = empresaDAOImplementation.GetAll();
        model.addAttribute("lempresas", empresas);
        
        return "listadoEmpresas";
    }
    
    @GetMapping("/form/{idempresa}")
    public String Form(@PathVariable int idempresa, Model model){
        
        if(idempresa == 0){  //se evalua el id, si es mayor a 0 se hara un update de empresa
            model.addAttribute("empresa", new Empresa());
            return "formEmpresa";
        }
        else {  //si es igual a 0 se agregara la nueva empresa      
            Empresa empresa = empresaDAOImplementation.GetById(idempresa);
            model.addAttribute("empresa", empresa);
            return "formEmpresa";
        }
    }
    
    @PostMapping("/form")
    public String Update(@Valid @ModelAttribute ("empresa") Empresa empresa, @RequestParam("imagenFile") MultipartFile imagenFile, Model model){
        
        String imagen64 = Util.ConvertirBase64(imagenFile);
        empresa.setImagen(imagen64);
        
        if(empresa.getIdempresa() > 0){
            empresaDAOImplementation.Update(empresa);
        } else {
            empresaDAOImplementation.Add(empresa);
        }
        return "redirect:/empresa/listado"; //Redifirge al listado
    }
    
    
    @GetMapping("/eliminar/{idempresa}")
    public String Delete(@PathVariable int idempresa, Model model){
        Empresa empresa = empresaDAOImplementation.GetById(idempresa);
        model.addAttribute("empresa", empresa);
        
        empresaDAOImplementation.Delete(empresa);
        
        return "redirect:/empresa/listado";
    }
}
