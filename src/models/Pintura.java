package models;

import java.util.Date;

import enums.EstadoPieza;
import enums.TecnicaPintura;

public class Pintura extends Pieza{
	
	private TecnicaPintura tecnicaPintura;
	private Dimensiones dimension;
	

	public Pintura(int idPiezaP, String nombre, boolean bloqueadaP, String autorP,  int anioP, String lugarCreacionP,
			String tipoPiezaP,Dimensiones dimensiones,Date fechaing,EstadoPieza estadopiece, TecnicaPintura tecnicaPinturaP, String contactoPropietario, int Precio) {
		super(idPiezaP,nombre,tipoPiezaP, bloqueadaP, autorP,  anioP, lugarCreacionP,fechaing,estadopiece, contactoPropietario, Precio );
		this.tecnicaPintura= tecnicaPinturaP;
		this.dimension= dimensiones;
		// TODO Auto-generated constructor stub
		
	}
	
	public TecnicaPintura getTecnicaPintura() {
		return TecnicaPintura.valueOf(tecnicaPintura.toString());
	}


	public Dimensiones getDimension() {
		return dimension;
	}

	public void setTecnicaPintura(TecnicaPintura tecnicaPintura) {
		this.tecnicaPintura = tecnicaPintura;
	}
	
	
}