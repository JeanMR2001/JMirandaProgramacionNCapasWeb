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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            ColoniaDAOImplementation coloniaDAOImplementation) {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
        this.rolDAOImplementation = rolDAOImplementation;
        this.paisDAOImplementation = paisDAOImplementation;
        this.estadoDAOImplementation = estadoDAOImplementation;
        this.municipioDAOImplementation = municipioDAOImplementation;
        this.coloniaDAOImplementation = coloniaDAOImplementation;
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<Usuario>> GetAll(@RequestBody Usuario usuario) {

        if (usuario.getNombre() == null) {
            usuario.setNombre("");
        }
        if (usuario.getApellidopaterno() == null) {
            usuario.setApellidopaterno("");
        }
        if (usuario.getApellidomaterno() == null) {
            usuario.setApellidomaterno("");
        }

        List<Usuario> usuarios = usuarioDAOImplementation.GetAll(usuario);

        if (usuarios.isEmpty()) {
            // Si no hay datos, devolver un ResponseEntity con código 404 (Not Found)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Si hay datos, devolver un ResponseEntity con la lista y código 200 (OK)
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
    }

    @GetMapping("/add")
    public ResponseEntity<Map<String,Object>> Add(@RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();
        
        usuarioDAOImplementation.Add(usuario);
        if (usuario.getIdusuario() > 0) {
            response.put("consola", "Se agregó correctamente al usuario: " + usuario.getNombre());
            response.put("nuevo usuario", usuario);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("error", "Se elimino correctamente al usuario");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<Map<String,Object>> Update(@PathVariable int idusuario, @RequestBody Usuario usuario) {

        Map<String, Object> response = new HashMap<>();
        
        Usuario usuarioEdit = usuarioDAOImplementation.GetById(idusuario);

        usuario.setIdusuario(usuarioEdit.getIdusuario());

        //usuario.setIdusuario(idusuario);
        usuarioDAOImplementation.Update(usuario);
        
        response.put("consola", "Se editó correctamente al usuario: " + usuario.getUsername());
        //response.put("Usuario editado", usuario);


            return new ResponseEntity(response, HttpStatus.CREATED);

    }

    @PostMapping("/delete/{idusuario}")
    public Map<String, Object> Delete(@PathVariable int idusuario) {

        Usuario usuario = usuarioDAOImplementation.GetById(idusuario);
//        Direccion direccion = direccionDAOImplementation.GetById(usuario.getIdusuario());
//                
//        //usuario.setIdusuario(idusuario);
//        direccionDAOImplementation.Delete(direccion);
        usuarioDAOImplementation.Delete(usuario);

        Map<String, Object> response = new HashMap<>();
        response.put("consola", "Se elimino correctamente al usuario");
        return response;

    }

}
