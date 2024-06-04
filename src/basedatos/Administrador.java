package basedatos;

import java.util.HashMap;
import models.Comprador;
import models.Empleado;
import models.Galeria;
import models.Pieza;
import models.Propietario;
import enums.EstadoPieza;

public class Administrador extends Empleado {

	private Inventario inventario;
    public Administrador(String Usuario, String Contrasena, int nivel, Galeria galeria1) {
        super(Usuario, Contrasena, nivel, galeria1);
        this.inventario = new Inventario();
        new HashMap<>();
    }
    
    
    
    /**
	 * Este metodo permite al administrador registrar nuevas piezas.
	 *
	 */
	public void registrarIngresoPieza(Pieza pieza) { 
		
		
		
	}
	/**
	 * Este metodo permite al administrador confirmar si se vendio una pieza.
	 *
	 *@return true or false
	 */
    public boolean confirmarVentaPieza(Pieza pieza) {
    	
    	if(pieza.getEstadoPieza().equals(EstadoPieza.VENDIDO)) {
    		return true;
    		
    	} else {
    		return false;
    	}      	
    }
    /**
	 * Este metodo permite al administrador devolver una pieza a un propietario.
	 *
	 */
	public void devolverPieza(Pieza pieza, Propietario propietario) {
		pieza.setEstadoPieza(EstadoPieza.DEVUELTA);
		propietario.anadirPiezasPropietario(pieza, null);

	}
    
	/**
	 * Este metodo permite al administrador eliminar una pieza del invenatario.
	 *
	 */
	public void elimarPieza(Pieza pieza) {
		inventario.eliminarPiezaListExhibicion(pieza); // ESTE NO ESTA TERMINADO

	}
	
	/**
	 * Este metodo permite al administrador cambiar el atributo de verificar a true de un comprador.
	 *
	 */
	public void verificarComprador(Comprador comprador) {
		comprador.setVerificado(true);
	}
	/**
	 * Este metodo permite al administrador poner un limite de compras a un comprador.
	 *
	 */
	public void setLimiteCompra(Comprador comprador, int limte) {
		comprador.setLimiteCompra(limte);
		
		
	}
	
}
