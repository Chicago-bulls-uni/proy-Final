package utils;


public class Conversor {
	
    public static int stringAEntero(String cadena) {
        try {
            return Integer.parseInt(cadena);
        } catch (NumberFormatException e) {
            
            System.err.println("Error: No se pudo convertir la cadena a entero.");
            return 0; // O 
        }
    }

 
    public static String enteroAString(int numero) {
        return String.valueOf(numero);
    }
}