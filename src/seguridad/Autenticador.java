//Para autenticar que el usuario coincide con su contraseña 
package seguridad;


import java.util.List;

import basedatos.RegistroUser;

public class Autenticador {

    private RegistroUser registroUser;

    public Autenticador() {
        this.registroUser = new RegistroUser();
    }

    public  boolean autenticar(String nombreUsuario, String contrasena) {
        if (nombreUsuario == null || nombreUsuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            throw new IllegalArgumentException("Nombre de usuario y contraseña no pueden ser nulos o vacíos");
        }
        try {
            List<Object> password = registroUser.buscarUsuario(nombreUsuario);
            if (password != null && password.size() > 1 && password.get(1).equals(contrasena)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al autenticar: " + e.getMessage());
        }
        return false;
    }
}