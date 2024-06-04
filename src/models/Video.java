package models;

import java.sql.Date;

import enums.EstadoPieza;
import enums.TipoVideo;

public class Video extends Pieza{
	
	private String duracionMinutos;
	private TipoVideo tipo;
	private boolean necesitaElectricidad;
	
	public Video(int idPiezaP,String nombre, boolean bloqueadaP, String autorP,  int anioP, String lugarCreacionP,
			String tipoPiezaP, Date fechaing, String duracionMinutosP, TipoVideo tipoP,EstadoPieza estadopiece, String contactoPropietario, int Precio) {
		super(idPiezaP,nombre,tipoPiezaP, bloqueadaP, autorP,  anioP, lugarCreacionP, fechaing,estadopiece, contactoPropietario, Precio);
		this.duracionMinutos = duracionMinutosP;
		this.tipo = tipoP;
		this.setNecesitaElectricidad(true);
	}

	public String getDuracionMinutos() {
		return duracionMinutos;
	}

	public TipoVideo getTipovideo() {
		return tipo;
	}


	public void setDuracionMinutos(String duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}


	public void setTipo(TipoVideo tipo) {
		this.tipo = tipo;
	}


	
	
	public static String convertirAHora(int duracionMinutos) {
        int horas = duracionMinutos / 60;
        int minutos = duracionMinutos % 60;
        String horaFormateada = String.format("%02d%02d", horas, minutos);
        
        return horaFormateada;
    }

	public boolean isNecesitaElectricidad() {
		return necesitaElectricidad;
	}

	public void setNecesitaElectricidad(boolean necesitaElectricidad) {
		this.necesitaElectricidad = necesitaElectricidad;
	}
		
		
}