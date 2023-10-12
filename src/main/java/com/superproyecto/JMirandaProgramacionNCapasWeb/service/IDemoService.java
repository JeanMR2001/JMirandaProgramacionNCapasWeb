/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.service;

import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.NumerosOperacion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.Resultado;

/**
 *
 * @author digis
 */
public interface IDemoService {
    
    Resultado Suma(NumerosOperacion numerosoperacion);
    
    Resultado Resta(NumerosOperacion numerosOperacion);
    
    Resultado Mult(NumerosOperacion numerosOperacion);
    
    Resultado Div(NumerosOperacion numerosOperacion);
    
//    String Saludo(String nombre);
}
