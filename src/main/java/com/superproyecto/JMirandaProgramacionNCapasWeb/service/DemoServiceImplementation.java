/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.service;

import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.NumerosOperacion;
import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.Resultado;

/**
 *
 * @author digis
 */
public class DemoServiceImplementation implements IDemoService{

    @Override
    public Resultado Suma(NumerosOperacion numerosoperacion) {
        
        Resultado resultado = new Resultado();
        
        resultado.setResultado(numerosoperacion.getNumero1() + numerosoperacion.getNumero2());
        
        return resultado;
    }

    @Override
    public Resultado Resta(NumerosOperacion numerosOperacion) {
        Resultado resultado = new Resultado();
        
        resultado.setResultado(numerosOperacion.getNumero1() - numerosOperacion.getNumero2());
        
        return resultado;
    }

    @Override
    public Resultado Mult(NumerosOperacion numerosOperacion) {
        Resultado resultado = new Resultado();
        
        resultado.setResultado(numerosOperacion.getNumero1() * numerosOperacion.getNumero2());
        
        return resultado;
    }

    @Override
    public Resultado Div(NumerosOperacion numerosOperacion) {
        Resultado resultado = new Resultado();
        
        resultado.setResultado(numerosOperacion.getNumero1() / numerosOperacion.getNumero2());
        
        return resultado;
    }

//    @Override
//    public String Saludo(String nombre) {
//        
//        return "Te quiero mucho " + nombre;
//    }
    
    
}
