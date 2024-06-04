package models;

public class Dimensiones {

	
	private double alto;
	private double ancho;
	private double profundidad;
	
	
	public Dimensiones (double altoP, double anchoP, double profundidadP) {
		this.alto= altoP;
		this.ancho=anchoP;
		this.profundidad=profundidadP;
		
	}
	
	public double calcularVolumen(double alto, double ancho, double profundidad) {
	
		double volumen= (alto*ancho*profundidad);
		return volumen ;
	}
	@Override
    public String toString() {
        return "Dimensiones [alto=" + alto + ", ancho=" + ancho + ", profundidad=" + profundidad + "]";
    }

    public static void main(String[] args) {
        Dimensiones dimensiones = new Dimensiones(2.5, 3.0, 0.1);
        System.out.println("Volumen de la Pieza: " + dimensiones.calcularVolumen(dimensiones.alto, dimensiones.ancho, dimensiones.profundidad) + " metros cúbicos");
    }
}
