/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JMirandaProgramacionNCapasWebApplication implements CommandLineRunner{

    private static Logger LOG  = LoggerFactory.getLogger(JMirandaProgramacionNCapasWebApplication.class);
    
    public static void main(String[] args){
		SpringApplication.run(JMirandaProgramacionNCapasWebApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("Hola Spring, te quiero mucho");
        LOG.info("Hola spring te quiero mucho");
    }
    
    /*@GetMapping("/yopis")
    public String Hello (@RequestParam(value = "name", defaultValue = "Jeansito") String name){
        return String.format("Hello %s!", name);
    }*/
    
    
}
