package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {

   
    public String convertirFechaString(Date fecha) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(fecha);
    }

 
    public Date convertirStringFecha(String fechaString) {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            return df.parse(fechaString);
        } catch (ParseException e) {
        
            System.err.println("Error: No se pudo convertir la cadena a fecha.");
            return null; 
        }
    }
}
