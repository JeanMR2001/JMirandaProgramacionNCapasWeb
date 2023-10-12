/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @GetMapping("/")
    public String holaMundo(@RequestParam(name="name", required = false, defaultValue = "Jeansito") String name, Model model){
        
        model.addAttribute("name", name);
        return "holaMundo";
    }
    
    @GetMapping("/suma")
    public String holaSuma(@RequestParam int numero1, @RequestParam int numero2, Model model){
        model.addAttribute("sum", numero1 + numero2);
        return "holaSuma";
    }
    
}
