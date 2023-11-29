/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Usuario;
import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.NumerosOperacion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.Resultado;
import com.superproyecto.JMirandaProgramacionNCapasWeb.service.DemoServiceImplementation;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */

@Controller
@RequestMapping("/Home")
public class DemoController {
    
    @PostMapping("/suma")
    public Resultado Suma(@RequestBody NumerosOperacion numerosOperacion){
        
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Suma(numerosOperacion);
    }
    
    @PostMapping("/resta")
    public Resultado Resta(@RequestBody NumerosOperacion numerosOperacion){
        
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Resta(numerosOperacion);
    }
    
    @PostMapping("/mult")
    public Resultado Mult(@RequestBody NumerosOperacion numerosOperacion){
        
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Mult(numerosOperacion);
    }
    
    @PostMapping("/div")
    public Resultado Div(@RequestBody NumerosOperacion numerosOperacion){
        
        DemoServiceImplementation demoServiceImplementation = new DemoServiceImplementation();
        return demoServiceImplementation.Div(numerosOperacion);
    }
    
    @PostMapping("/saludo")
    public Map<String, Object> Saludo(@RequestBody NumerosOperacion numerosOperacion){
        
        Map<String,Object> response = new HashMap<>();
        response.put("saludo", "Te quiero mucho " + numerosOperacion.getNombre());
        return response;
    }
    
    @GetMapping("/welcome")
    public String Bienvenida(HttpSession session, Model model){
         Usuario usuarioLog = (Usuario)session.getAttribute("userLOGIN");
         
         model.addAttribute("usuariologueado", usuarioLog);
         return "Home";
    }
    
    
}
