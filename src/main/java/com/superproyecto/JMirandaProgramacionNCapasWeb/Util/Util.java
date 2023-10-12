/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
public class Util {
    
    public static String ConvertirBase64(MultipartFile imagenFile){
        
        String imagen64 = null;
        try{
            if(!imagenFile.isEmpty()){
                byte[] bytes = imagenFile.getBytes();
                imagen64 = Base64.encodeBase64String(bytes);
                
            } else {
                imagen64 = null;
            }
            
            
        } catch (Exception ex){
            
        }
        return imagen64;
    }
    
}
