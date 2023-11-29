/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.ColoniaDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.DireccionDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EstadoDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.MunicipioDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.PaisDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.RolDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Colonia;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Direccion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Estado;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Municipio;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Rol;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Usuario;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.UsuarioDireccion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.Util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//localhost:8080/usuario/
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioDAOImplementation usuarioDAOImplementation;
    private DireccionDAOImplementation direccionDAOImplementation;
    private RolDAOImplementation rolDAOImplementation;
    private PaisDAOImplementation paisDAOImplementation;
    private EstadoDAOImplementation estadoDAOImplementation;
    private MunicipioDAOImplementation municipioDAOImplementation;
    private ColoniaDAOImplementation coloniaDAOImplementation;

    //EntityManager -- persistencia
    @Autowired
    public UsuarioController(UsuarioDAOImplementation usuarioDAOImplementation,
            DireccionDAOImplementation direccionDAOImplementation,
            RolDAOImplementation rolDAOImplementation,
            PaisDAOImplementation paisDAOImplementation,
            EstadoDAOImplementation estadoDAOImplementation,
            MunicipioDAOImplementation municipioDAOImplementation,
            ColoniaDAOImplementation coloniaDAOImplementation) {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
        this.direccionDAOImplementation = direccionDAOImplementation;
        this.rolDAOImplementation = rolDAOImplementation;
        this.paisDAOImplementation = paisDAOImplementation;
        this.estadoDAOImplementation = estadoDAOImplementation;
        this.municipioDAOImplementation = municipioDAOImplementation;
        this.coloniaDAOImplementation = coloniaDAOImplementation;
    }

    //localhost:8080/usuario/listado
    @GetMapping("/listado")
    private String listadoUsuarios(Model model, HttpSession session) {

        Usuario usuarioLog = (Usuario)session.getAttribute("userLOGIN");
        
        Usuario usuario = new Usuario();
        usuario.setNombre("");
        usuario.setApellidopaterno("");
        usuario.setApellidomaterno("");

        model.addAttribute("usuariologueado", usuarioLog);
        
        List<Usuario> usuarios = usuarioDAOImplementation.GetAll(usuario);
        model.addAttribute("lusuarios", usuarios);
        model.addAttribute("usuariobusqueda", new Usuario());

        List<Direccion> direcciones = direccionDAOImplementation.GetAll();
        model.addAttribute("ldirecciones", direcciones);

        return "listadoUsuarios";
    }

    @PostMapping("/listado")
    private String listadoUsuarios(Model model, @ModelAttribute("usuariobusqueda") Usuario usuariobusqueda) {

        List<Usuario> usuarios = usuarioDAOImplementation.GetAll(usuariobusqueda);

        model.addAttribute("lusuarios", usuarios);
        model.addAttribute("usuariobusqueda", usuariobusqueda);
        

        List<Direccion> direcciones = direccionDAOImplementation.GetAll();
        model.addAttribute("ldirecciones", direcciones);

        return "listadoUsuarios";
    }

//    @GetMapping("/add")
//    public String Add(Model model) {
//
//        model.addAttribute("usuario", new Usuario());
//        return "formUsuario";
//    }
//    @PostMapping("addusuario")
//    public String Add(@ModelAttribute Usuario usuario) {
//        //logica necesario para enviar dicha información a DAO
//        usuarioDAOImplementation.Add(usuario);
//
//        return "redirect:/usuario/listado"; //una ves se ejecute redirecciona a la pagina listado
//    }
//Combinacion update con add
    @GetMapping("/form/{idusuario}")
    public String Form(@PathVariable int idusuario, Model model) {

        List<Rol> roles = rolDAOImplementation.GetAll();
        model.addAttribute("roles", roles);
//        List<Colonia> colonias = rolDAOImplementation.GetAllColonia();
//        model.addAttribute("colonias", colonias);
        model.addAttribute("paises", paisDAOImplementation.GetAll());
        if (idusuario == 0) { //ADD
            model.addAttribute("usuariodireccion", new UsuarioDireccion());
            return "updateUsuario";
        } else {
            Usuario usuario = usuarioDAOImplementation.GetById(idusuario);
            Direccion direccion = direccionDAOImplementation.GetById(idusuario);

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion(usuario, direccion);

            if (usuarioDireccion.getDireccion().getColonia() != null) {
                model.addAttribute("estados", estadoDAOImplementation.GetByIdPais(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getPais().getIdpais()));
                model.addAttribute("municipios", municipioDAOImplementation.GetByIdEstado(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getIdestado()));
                model.addAttribute("colonias", coloniaDAOImplementation.GetByIdMunicipio(usuarioDireccion.getDireccion().getColonia().getMunicipio().getIdMunicipio()));

            }
            
            if(usuario.getSexo() != null){
                if(usuarioDireccion.getUsuario().getSexo().equals("H ")){
                    model.addAttribute("sexoH", true);
                } else if (usuarioDireccion.getUsuario().getSexo().equals("M ")) {
                    model.addAttribute("sexoM", true);
                }
            }

            model.addAttribute("usuariodireccion", usuarioDireccion);

            return "updateUsuario"; //formUsuario
        }

    }

    @PostMapping("/form")  //form
    public String Update(@Valid @ModelAttribute("usuariodireccion") UsuarioDireccion usuariodireccion,
            @RequestParam("imagenFile") MultipartFile imagenFile, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("usuariodireccion", usuariodireccion);
            return "updateUsuario";
        }
        String imagen64 = Util.ConvertirBase64(imagenFile);
        usuariodireccion.getUsuario().setImagen(imagen64);

        if (usuariodireccion.getUsuario().getIdusuario() > 0) {
            usuarioDAOImplementation.Update(usuariodireccion.getUsuario());
            Direccion direccion = direccionDAOImplementation.GetById(usuariodireccion.getUsuario().getIdusuario());
            if(direccion.getUsuario() == null){
                direccion = new Direccion(usuariodireccion.getDireccion().getCalle(), usuariodireccion.getDireccion().getNumerointerior(),
                    usuariodireccion.getDireccion().getNumeroexterior(), usuariodireccion.getDireccion().getColonia(),
                        usuariodireccion.getUsuario());
                direccionDAOImplementation.Add(direccion);
            }else {
                
                Direccion direccionnueva = usuariodireccion.getDireccion();
                direccionnueva.setUsuario(usuariodireccion.getUsuario());
                direccionnueva.setIddireccion(usuariodireccion.getDireccion().getIddireccion());
                direccionDAOImplementation.Update(direccionnueva);
            }
            
            
        } else {
            usuariodireccion.getUsuario().setStatus(1);
            int idUsuario = usuarioDAOImplementation.Add(usuariodireccion.getUsuario());
            Direccion direccion = new Direccion(usuariodireccion.getDireccion().getCalle(), usuariodireccion.getDireccion().getNumerointerior(),
                    usuariodireccion.getDireccion().getNumeroexterior(), usuariodireccion.getDireccion().getColonia(), new Usuario(idUsuario));
            usuariodireccion.setDireccion(direccion);
            direccionDAOImplementation.Add(direccion);
        }
        return "redirect:/usuario/listado"; // Redirige a la lista de usuarios o a otra página
    }

    @GetMapping("/eliminar/{idusuario}")
    public String Delete(@PathVariable int idusuario, Model model) {

        Usuario usuario = usuarioDAOImplementation.GetById(idusuario);
        model.addAttribute("usuario", usuario);
        usuarioDAOImplementation.Delete(usuario);

        return "redirect:/usuario/listado";

    }

    @GetMapping("/ChangeStatus")
    @ResponseBody
    public void ChangeStatus(@RequestParam("idUsuario") int idUsuario, @RequestParam("status") boolean status) {

        usuarioDAOImplementation.ChangeStatus(idUsuario, status);
    }

    @GetMapping("/GetEstadoByIdPais")
    @ResponseBody
    public List<Estado> GetEstadoByIdPais(@RequestParam("IdPais") int IdPais) {
        List<Estado> estados = estadoDAOImplementation.GetByIdPais(IdPais);
        return estados;
    }

    @GetMapping("/GetMunicipioByIdEstado")
    @ResponseBody
    public List<Municipio> GetMunicipioByIdEstado(@RequestParam("IdEstado") int IdEstado) {
        List<Municipio> municipios = municipioDAOImplementation.GetByIdEstado(IdEstado);
        return municipios;
    }

    @GetMapping("/GetColoniaByIdMunicipio")
    @ResponseBody
    public List<Colonia> GetColoniaByIdMunicipio(@RequestParam("IdMunicipio") int IdMunicipio) {
        List<Colonia> colonias = coloniaDAOImplementation.GetByIdMunicipio(IdMunicipio);
        return colonias;
    }

//    List<UsuarioDireccion> Lusuariodireccion = new ArrayList<>();
//
//        for (int i = 0; i < usuarios.size(); i++) {
//            Usuario usuario = usuarios.get(i);
//            Direccion direccion = direcciones.get(i);
//            UsuarioDireccion usuarioDireccion = new UsuarioDireccion(usuario, direccion);
//            Lusuariodireccion.add(usuarioDireccion);
//        }
//        model.addAttribute("usuariodireccion", Lusuariodireccion);
}
