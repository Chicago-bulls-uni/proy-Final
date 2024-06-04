package seguridad;




import models.Galeria;
import models.Operador;
import models.Propietario;

import java.util.List;

import basedatos.Administrador;
import basedatos.RegistroUser;
import models.Cajero;

public class GestorUsuarios {
    private RegistroUser registroUser;
    private Autenticador autenticador;

    public GestorUsuarios() {
        this.registroUser = new RegistroUser();
        this.autenticador = new Autenticador();
    }

    public void crearUsuario(String nombreUsuario, String contrasena, int nivel) {
        registroUser.agregarUsuario(nombreUsuario, contrasena, nivel, 0, null, null, 0, false, 0);
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        if (autenticador.autenticar(nombreUsuario, contrasena)) {
            List<Object> userInfo = registroUser.buscarUsuario(nombreUsuario);
            if (userInfo != null && userInfo.size() >= 3) {
                String usuario = (String) userInfo.get(0);
                String password = (String) userInfo.get(1);
                int nivel = (int) userInfo.get(2);
                Galeria galeria = Usuario.getGaleria();
                
                if (nivel == 0) { 
                   
                    String nombre = (String) userInfo.get(3);
                    String contacto = (String) userInfo.get(4);
                    double limiteCompra = (double) userInfo.get(5);
                    boolean verificado = (boolean) userInfo.get(6);
                    double billetera = (double) userInfo.get(7);
                    return new Propietario(usuario, password, nombre, contacto, limiteCompra, verificado, galeria, billetera);
                } else {
                    switch (nivel) {
                        case 3:
                            return new Administrador(usuario, password, nivel, galeria);
                        case 2:
                            return new Operador(usuario, password, nivel, galeria);
                        case 1:
                            return new Cajero(usuario, password, galeria);
                        case 0:
                            return new Propietario(usuario, password, nombreUsuario, usuario, 0.0, false, galeria, 0.0);
                        default:
                            throw new IllegalArgumentException("Nivel no reconocido");
                    }
                }
            }
        }
        return null;
    }

    public void asignarNivel(String nombreUsuario, String rol) {
        int nivel = obtenerNivelPorRol(rol);
        registroUser.modificarUsuario(nombreUsuario, null, nivel);
    }

    public void eliminarUsuario(String nombreUsuario) {
        registroUser.eliminarUsuario(nombreUsuario);
    }

    public int obtenerNivelPorRol(String rol) {
        switch (rol.toLowerCase()) {
            case "administrador":
                return 3;
            case "operador":
                return 2;
            case "cajero":
                return 1;
            case "comprador":
                return 0;
            default:
                return -1;
        }
    }
}

	/* MAIN DE PRUEBA ES UN COMENTARIO SOLO DESCOMENTAR PARA PROBAR
	 * public static void main(String[] args) { GestorUsuario1 gestorUsuario1 = new
	 * GestorUsuario1(); Galeria galeria = new Galeria( Parámetros de inicialización
	 * ); Usuario.inicializarGaleria(galeria);
	 * 
	 * Usuario usuario = gestorUsuario1.iniciarSesion("nombreUsuario",
	 * "contrasena");
	 * 
	 * if (usuario != null) { System.out.println("Bienvenido " +
	 * usuario.getNombreUsuario() + " con nivel " + usuario.getNivel());
	 * 
	 * } else { System.out.println("Autenticación fallida"); } }
	 */
