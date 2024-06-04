package models;

import java.sql.Date;


import enums.EstadoPieza;


public class Escultura extends Pieza{
	
	private Dimensiones dimension;
	private String materiales;  
	private String peso;
	

	public Escultura(int idPiezaP, String nombre, boolean bloqueadaP, String autorP,  int anioP, String lugarCreacionP,
			String tipoPiezaP,Dimensiones dimensiones,Date fechaing, String pesoP, String materialesP,EstadoPieza estadopiece, String contactoPropietario, int Precio) {
		
		super(idPiezaP,nombre,tipoPiezaP, bloqueadaP, autorP, anioP, lugarCreacionP,fechaing,estadopiece, contactoPropietario, Precio );
		this.dimension=dimensiones;
		this.materiales=materialesP;
		this.setPeso(pesoP);
		
	}
	
	public Dimensiones getDimension() {
		return dimension;
	}
	
	public String getMateriales() {
		return materiales;
	}

	public void setMateriales(String materiales) {
		this.materiales = materiales;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	

}
