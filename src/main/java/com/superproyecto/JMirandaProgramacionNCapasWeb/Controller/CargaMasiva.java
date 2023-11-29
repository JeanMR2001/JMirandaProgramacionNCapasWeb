/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.superproyecto.JMirandaProgramacionNCapasWeb.Controller;

import com.superproyecto.JMirandaProgramacionNCapasWeb.DAO.EmpleadoDAOImplementation;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empleado;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Empresa;
import com.superproyecto.JMirandaProgramacionNCapasWeb.JPA.Usuario;
import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.ResultArchivo;
import com.superproyecto.JMirandaProgramacionNCapasWeb.entity.ResultTxt;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.mock.web.MockMultipartFile;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/")
public class CargaMasiva {

    private EmpleadoDAOImplementation empleadoDAOImplementation;

    @Autowired
    public CargaMasiva(EmpleadoDAOImplementation empleadoDAOImplementation) {
        this.empleadoDAOImplementation = empleadoDAOImplementation;
    }

    @GetMapping("/carga")
    public String CargaMasivaR() {

        return "cargaMasiva";
    }

    @PostMapping("/cargamasivA")
    public String CargaMasiva(@RequestParam("archivo") MultipartFile archivo, Model model, HttpSession session) throws IOException, ParseException {

        if (archivo != null && !archivo.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename());
            if (extension.equals("xlsx")) {
                List<Empleado> empleados = LeerArchivo(archivo);
                String root = System.getProperty("user.dir");
                String path = "/src/main/resources/static/Image/archivos/";
                String filename = archivo.getOriginalFilename();
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
                String absolutePath = root + path + fecha + filename;
                archivo.transferTo(new File(absolutePath));

                if (empleados.size() > 0) {
                    ResultArchivo result = Validar(empleados);

                    if (result.getErrores().size() > 0) {
                        model.addAttribute("errores", result.getErrores());
                        return "cargaMasiva";
                    } else {
                        model.addAttribute("success", "El archivo es correcto");
                        session.setAttribute("path", absolutePath);
                        return "cargaMasiva";
                    }
                } else {
                    model.addAttribute("errorArchivo", "No se encontraron datos en el archivo");
                    return "cargaMasiva";
                }
            } else {
                //Cuando el archivo es txt
                if (extension.equals("txt")) {
                    List<Empleado> empleados = LeerArchivoTxt(archivo);
                    String root = System.getProperty("user.dir");
                    String path = "/src/main/resources/static/Image/archivos/";
                    String filename = archivo.getOriginalFilename();
                    String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    String absolutepath = root + path + fecha + filename;
                    archivo.transferTo(new File(absolutepath));

                    if (empleados.size() > 0) {
                        ResultArchivo result = ValidarTxt(empleados);

                        if (result.getErrores().size() > 0) {
                            model.addAttribute("errores", result.getErrores());
                            return "cargaMasiva";
                        } else {
                            model.addAttribute("success", "El archivo es correcto");
                            session.setAttribute("path", absolutepath);
                            return "cargaMasiva";
                        }
                    } else {
                        model.addAttribute("errorArchivo", "No se encontraron datos en el archivo");
                        return "cargaMasiva";
                    }
                }
            }
        } else {
            //Cuando el archivo viene vacio
            model.addAttribute("errorArchivo", archivo);
            return "cargaMasiva";
        }
        return "cargaMasiva";
    }

    public ResultArchivo Validar(List<Empleado> empleados) throws ParseException {

        ResultArchivo resultPrincipal = new ResultArchivo();
        resultPrincipal.setErrores(new ArrayList<>());

        int fila = 1;
        String errorMessage = "";
        Date fecha = null;

        for (Empleado empleado : empleados) {

            errorMessage += (empleado.getNumeroempleado().equals("")) ? "Falto número de empleado" : "";
            errorMessage += (empleado.getNombre().equals("")) ? "Falto nombre del Empleado" : "";
            errorMessage += (empleado.getApellidopaterno().equals("")) ? "Falto Apellido paterno del Empleado" : "";
            errorMessage += (empleado.getApellidomaterno().equals("")) ? "Falto Apellido materno del Empleado" : "";
            errorMessage += (empleado.getEmail().equals("")) ? "Falto E-mail del Empleado" : "";
            errorMessage += (empleado.getTelefono().equals("")) ? "Falto Telefono del Empleado" : "";
            errorMessage += (empleado.getNss().equals("")) ? "Falto NSS del Empleado" : "";
            errorMessage += (empleado.getRfc().equals("")) ? "Falto RFC del Empleado" : "";
            errorMessage += (String.valueOf(empleado.getEmpresa().getIdempresa()).equals("")) ? "Falto id de empresa" : "";
            try{
                errorMessage += (empleado.getFechanacimiento().equals(null)) ? "Falto fecha de nacimiento del Empleado" : "";
                errorMessage += (empleado.getFechaingreso().equals(null)) ? "Falto fecha de ingreso del Empleado" : "";
            } catch(NullPointerException ex){
                fecha = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
                fecha = dateFormat.parse("2000-01-01");
                if(empleado.getFechaingreso() == null){
                    empleado.setFechaingreso(fecha);
                    errorMessage += (empleado.getFechaingreso().equals(fecha)) ? "Falto fecha de ingreso del Empleado" : "";
                }
                if(empleado.getFechanacimiento() == null){
                    empleado.setFechanacimiento(fecha);
                    errorMessage += (empleado.getFechanacimiento().equals(fecha)) ? "Falto fecha de nacimiento del Empleado" : "";
                }
                }
            
            
            if (!errorMessage.equals("")) {
                ResultArchivo resultExcel = new ResultArchivo();
                resultExcel.setRow(fila);
                resultExcel.setErrorMessage(errorMessage);

                resultPrincipal.getErrores().add(resultExcel);
                errorMessage = "";
            }
            fila++;

        }
        return resultPrincipal;

    }

    public List<Empleado> LeerArchivoTxt(MultipartFile archivo) throws IOException {

        List<Empleado> empleados = new ArrayList<>();

        File convFile = new File(archivo.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(archivo.getBytes());
        fos.close();

        FileReader filereader = new FileReader(convFile);
        BufferedReader bufferedReader = new BufferedReader(filereader);

        String fila = bufferedReader.readLine();

        while ((fila = bufferedReader.readLine()) != null) {
            Empleado empleado = new Empleado();
            String[] datos = fila.split("\\|");
            String[] datosValidados = AnalizarLtxt(datos);

            if (datosValidados[0] == null) {
                break;
            }
            empleado.setNumeroempleado(datosValidados[0]);
            empleado.setNombre(datosValidados[1]);
            empleado.setApellidopaterno(datosValidados[2]);
            empleado.setApellidomaterno(datosValidados[3]);
            empleado.setEmail(datosValidados[4]);
            empleado.setTelefono(datosValidados[5]);
            empleado.setNss(datosValidados[6]);
            empleado.setRfc(datosValidados[7]);
            empleado.setFechanacimiento(java.sql.Date.valueOf(datosValidados[8]));
            empleado.setFechaingreso(java.sql.Date.valueOf(datosValidados[9]));
            Empresa empresa = new Empresa();
            empresa.setIdempresa(Integer.parseInt(datosValidados[10]));
            empleado.setEmpresa(empresa);

            empleados.add(empleado);
        }
        return empleados;
    }

    public String[] AnalizarLtxt(String[] datos) {

        
        if (datos.length < 11) {
            String[] datosValidados = new String[11];

            for (int i = 0; i < datos.length; i++) {
                datosValidados[i] = datos[i];
            }
            for (int i = datos.length; i < datosValidados.length; i++) {
                datosValidados[i] = "";
            }
            return datosValidados;
        } else {
            String[] datosValidados = datos;
            return datosValidados;
        }

        
    }
    
    public ResultArchivo ValidarTxt(List<Empleado> empleados) {

        ResultArchivo resultPrincipal = new ResultArchivo();
        resultPrincipal.setErrores(new ArrayList<>());

        int fila = 1;
        String errorMessage = "";

        for (Empleado empleado : empleados) {

            errorMessage += (empleado.getNumeroempleado().equals("")) ? "Falto número de empleado" : "";
            errorMessage += (empleado.getNombre().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getApellidopaterno().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getApellidomaterno().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getEmail().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getTelefono().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getNss().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getRfc().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getFechanacimiento().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (empleado.getFechaingreso().equals("")) ? "Falta dato en el documento" : "";
            errorMessage += (String.valueOf(empleado.getEmpresa().getIdempresa()).equals("")) ? "Falta dato en el documento" : "";

            if (!errorMessage.equals("")) {
                ResultArchivo resultExcel = new ResultArchivo();
                resultExcel.setRow(fila);
                resultExcel.setErrorMessage(errorMessage);

                resultPrincipal.getErrores().add(resultExcel);
                errorMessage = "";
            }
            fila++;

        }
        return resultPrincipal;

    }

    public List<Empleado> LeerArchivo(MultipartFile archivo) throws IOException {

        List<Empleado> empleados = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(archivo.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);
        boolean primeraFila = true;

        for (Row row : worksheet) {

            if (primeraFila) {
                primeraFila = false;
                continue;
            }
            if (row.getCell(0) == null) {
                break;
            }
            Empleado empleado = new Empleado();
            empleado.setNumeroempleado(row.getCell(0).toString());
            empleado.setNombre(row.getCell(1).toString());
            empleado.setApellidopaterno(row.getCell(2).toString());
            empleado.setApellidomaterno(row.getCell(3).toString());
            empleado.setEmail(row.getCell(4).toString());
            empleado.setTelefono(row.getCell(5).toString());
            empleado.setNss(row.getCell(6).toString());
            empleado.setRfc(row.getCell(7).toString());
            empleado.setFechaingreso(row.getCell(8).getDateCellValue());
            empleado.setFechanacimiento(row.getCell(9).getDateCellValue());
            Empresa empresa = new Empresa();
            empresa.setIdempresa((int) row.getCell(10).getNumericCellValue());
            empleado.setEmpresa(empresa);

            empleados.add(empleado);
        }
        workbook.close();
        return empleados;
    }

    @PostMapping("/cargamasivA/AddDB")
    public String AddBaseDatos(HttpSession session, Model model) throws IOException {
        String path = session.getAttribute("path").toString();

        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        MultipartFile archivo = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));

        String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename());
        if (extension.equals("xlsx")) {
            List<Empleado> empleados = LeerArchivo(archivo);
            for (Empleado empleado : empleados) {
                empleadoDAOImplementation.Add(empleado);
            }
        } else {
            List<Empleado> empleados = LeerArchivoTxt(archivo);
            for (Empleado empleado : empleados) {
                empleadoDAOImplementation.Add(empleado);
            }
        }

        model.addAttribute("datoscargados", " Los datos se cargaron coreectamente a la BD");
        return "cargaMasiva";
    }
}
