package interfaz.login;

import java.util.Date;

import models.Pieza;
import models.Subasta;

public class Subastita {
	String Piezas;
	double valorMinimo;
	double ofertaMax;
	Date fechafin;
	String propietariopieza;
	boolean activo;
	public Subastita(Subasta subasta) {
		super();
		Piezas = ObtenerPiezas(subasta);
		this.valorMinimo = subasta.getValorMinimo();
		this.fechafin =  subasta.getFechafin();
		if(subasta.getOfertas().isEmpty()) {
			this.ofertaMax=0;
		}
		else {this.ofertaMax= subasta.ObtenermaxOferta().getMontoOfrecido();}
		
		this.fechafin =  subasta.getFechafin();
		this.propietariopieza = subasta.getPiezas().get(0).getPropietario();
		this.activo = subasta.isActivo();
	}
	public String ObtenerPiezas(Subasta subasta) {
		String b=null;
		int i=0;
		for(Pieza piece:subasta.getPiezas()) {
			if(i==0 ) {
				b=piece.getNombre();
			}
			else {
				b +=  ","+piece.getNombre();
			}
			
		}
		return b;
	}
	public String getPiezas() {
		return Piezas;
	}
	public double getValorMinimo() {
		return valorMinimo;
	}
	
	public double getOfertaMax() {
		return ofertaMax;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public String getPropietariopieza() {
		return propietariopieza;
	}
	public boolean isActivo() {
		return activo;
	}


}
