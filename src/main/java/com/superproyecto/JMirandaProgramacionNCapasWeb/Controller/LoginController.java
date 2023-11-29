/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
     private UsuarioDAOImplementation usuarioDAOImplementation;
     
     @Autowired
     public LoginController(UsuarioDAOImplementation usuarioDAOImplementation){
         this.usuarioDAOImplementation = usuarioDAOImplementation;
     }
    
    @GetMapping("/")
    public String Login(Model model){
//        session.setAttribute("username", );
        model.addAttribute("usuario", new Usuario());
        return "Login";
    }
    
    @PostMapping("/Login")
    public String Login(@ModelAttribute("usuario") Usuario usuario, HttpSession session, Model model){
        
        Usuario usuariologin = usuarioDAOImplementation.GetByEmail(usuario.getEmail());
        
        if(usuariologin == null){
            model.addAttribute("error", "¡El correo o la contraseña son incorrectos!");
            model.addAttribute("usuario", usuario);
            return "Login";
        }
        else {
//            String pass = usuariologin.getPassword();
            if(usuario.getPassword().equals(usuariologin.getPassword())){
                
                session.setAttribute("userLOGIN", usuariologin);
                return "redirect:/Home/welcome";
            }
            else {
                model.addAttribute("error", "¡El correo o la contraseña son incorrectos!");
                model.addAttribute("usuario", usuario);
                return "Login";
            }
        }
        
    }
    
//    @GetMapping("/suma")
//    public String holaSuma(@RequestParam int numero1, @RequestParam int numero2, Model model){
//        model.addAttribute("sum", numero1 + numero2);
//        return "holaSuma";
//    }
    
}
