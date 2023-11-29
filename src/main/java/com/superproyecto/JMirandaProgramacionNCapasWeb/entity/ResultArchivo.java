/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.entity;

import java.util.List;

/**
 *
 * @author digis
 */
public class ResultArchivo {
    
    private int Row;
    private String ErrorMessage;
    
    private List Errores;

    public int getRow() {
        return Row;
    }

    public void setRow(int Row) {
        this.Row = Row;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public List getErrores() {
        return Errores;
    }

    public void setErrores(List Errores) {
        this.Errores = Errores;
    }
    
    
}
