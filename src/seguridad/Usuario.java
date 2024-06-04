package seguridad;

import models.Galeria;

public class Usuario {
	protected static Galeria galeria;
    private String nombreUsuario;
    private String contrasena;
    private int nivel;

    public Usuario(String nombreUsuario, String contrasena, int nivel, Galeria galeria1) { //Nombre de usario es el id
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nivel = nivel;
        inicializarGaleria(galeria1);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public static Galeria getGaleria() {
		return galeria;
	}

	public String getContrasena() {
        return contrasena;
    }

    public int getNivel() {
        return nivel;
    }
    public static void inicializarGaleria(Galeria galeria1) {
		galeria=galeria1;
	}
}
