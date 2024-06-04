package utils;

import java.util.UUID;

public class GeneradorID {
	
	public static String generarID() {
        
        UUID uuid = UUID.randomUUID();
      
        String id = uuid.toString().substring(0, 8);
        return id;
    }

    public static void main(String[] args) {
       
        for (int i = 0; i < 5; i++) {
            System.out.println("ID generado: " + generarID());
        }
    }

}
