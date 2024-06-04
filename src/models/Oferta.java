package models;

public class Oferta {
	
	
	private int subasta;
	private String comprador;
	private double montoOfrecido;
	
	public Oferta ( int idsubasta, String compradorP, double montoOfrecidoP) {
		
		this.subasta= idsubasta;
		this.comprador=compradorP;
		this.montoOfrecido=montoOfrecidoP;
	}
	

	public int getSubasta() {
		return subasta;
	}

	public String getComprador() {
		return comprador;
	}

	public double getMontoOfrecido() {
		return montoOfrecido;
	}

	

	

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	public void setMontoOfrecido(double montoOfrecido) {
		this.montoOfrecido = montoOfrecido;
	}
	
	
}
