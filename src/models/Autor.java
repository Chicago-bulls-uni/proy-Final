package models;

public class Autor {
	
	
	private String nombre;
	private boolean esColectivo;
	
	public Autor ( String nombreP, boolean esColectivoP) {
		
		
		this.nombre= nombreP;
		this.esColectivo= esColectivoP;
	}

	

	public String getNombre() {
		return nombre;
	}

	public boolean isEsColectivo() {
		return esColectivo;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEsColectivo(boolean esColectivo) {
		this.esColectivo = esColectivo;
	}
	
	
	
}
