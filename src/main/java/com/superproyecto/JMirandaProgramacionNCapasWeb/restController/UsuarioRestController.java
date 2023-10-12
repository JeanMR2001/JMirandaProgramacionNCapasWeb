/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.restController;

/**
 *
 * @author digis
 */
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.ColoniaDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.DireccionDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EstadoDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.MunicipioDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.PaisDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.RolDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Direccion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Rol;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Usuario;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.UsuarioDireccion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.UsuarioBusqueda;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarioApi")
public class UsuarioRestController {
    
    
    private UsuarioDAOImplementation usuarioDAOImplementation;
    private DireccionDAOImplementation direccionDAOImplementation;
    private RolDAOImplementation rolDAOImplementation;
    private PaisDAOImplementation paisDAOImplementation;
    private EstadoDAOImplementation estadoDAOImplementation;
    private MunicipioDAOImplementation municipioDAOImplementation;
    private ColoniaDAOImplementation coloniaDAOImplementation;
    
    
    public UsuarioRestController(UsuarioDAOImplementation usuarioDAOImplementation,
                                 RolDAOImplementation rolDAOImplementation,
                                 PaisDAOImplementation paisDAOImplementation,
                                 EstadoDAOImplementation estadoDAOImplementation,
                                 MunicipioDAOImplementation municipioDAOImplementation,
                                 ColoniaDAOImplementation coloniaDAOImplementation)
    {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
        this.rolDAOImplementation = rolDAOImplementation;
        this.paisDAOImplementation = paisDAOImplementation;
        this.estadoDAOImplementation = estadoDAOImplementation;
        this.municipioDAOImplementation = municipioDAOImplementation;
        this.coloniaDAOImplementation = coloniaDAOImplementation;
    }
    
    
    @GetMapping("/GetAll")
    public List<Usuario> GetAll(@RequestBody Usuario usuario){
        
        if(usuario.getNombre() == null){
            usuario.setNombre("");
        }
        if(usuario.getApellidopaterno() == null){
            usuario.setApellidopaterno("");
        }
        if(usuario.getApellidomaterno() == null){
            usuario.setApellidomaterno("");
        }
        
        return usuarioDAOImplementation.GetAll(new Usuario(usuario.getNombre(),usuario.getApellidopaterno(), usuario.getApellidomaterno()));
    }
    
    @GetMapping("/add")
    public void Add(@RequestBody Usuario usuario){
        
        usuarioDAOImplementation.Add(new Usuario(usuario.getUsername(), usuario.getNombre(), usuario.getApellidopaterno(),
                                                               usuario.getApellidomaterno(), usuario.getEmail(), usuario.getPassword(), 
                                                                          usuario.getSexo(), usuario.getTelefono(), usuario.getCelular(), 
                                                                              usuario.getCurp(), usuario.getImagen(), usuario.getRol(), usuario.getFechanacimiento()));
        
    }
    
//    @GetMapping("/update/{idusuario}")
//    public String Update(@PathVariable int idusuario){
//        usuarioDAOImplementation.GetById(idusuario);
//        
////        usuarioDAOImplementation.GetAll(new Usuario(usuario.getNombre(), usuario.getApellidopaterno(), usuario.getApellidomaterno()));
//        
//        return "redirect:/usuarioApi/update";
//    }
    
    @PostMapping("/update/{idusuario}")
    public String Update(@PathVariable int idusuario, @RequestBody Usuario usuario){
        
        usuarioDAOImplementation.Update(usuario);
        
        return "redirect:/usuarioApi/GetAll" ;
        
    }
    
    
}

