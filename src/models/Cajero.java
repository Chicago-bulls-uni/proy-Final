package models;

import enums.MetodoPago;
import seguridad.Usuario;

import java.sql.Date;
import java.util.List;

public class Cajero extends Empleado{

	public Cajero(String nombreUsuario, String contrasena, Galeria galeria1) {
		super(nombreUsuario, contrasena, 1, galeria1);
		
	}
	
	public List<Compra> getHistorialVentasDirectasCajero() {
		List<Compra> listaVentasHistorialGaleria = Cajero.getGaleria().getHistorialVentasDirectas();
        return listaVentasHistorialGaleria;
    }
	
	
	public void regitrarcompraPieza(String piezaLlave, String metodoPago) {
	    
	    Subasta subasta = Usuario.getGaleria().getMapaSubastas().get(piezaLlave);
	    
	    if (subasta == null) {
	        System.out.println("No se encontró una subasta para la pieza: " + piezaLlave);
	        return;
	    }
	    
	    List<Oferta> ofertas = subasta.getOfertas();
	    if (ofertas == null || ofertas.isEmpty()) {
	        System.out.println("No hay ofertas disponibles para la pieza: " + piezaLlave);
	        return;
	    }
	    
	    
	    
	    Compra compra = new Compra(subasta.getPiezas(), this.getNombreUsuario(), new Date(System.currentTimeMillis()), MetodoPago.valueOf(metodoPago), ofertas.get(0).getMontoOfrecido());
	    
	    if (!compra.isCompraverificada()) {
	        System.out.println("La compra aún no ha sido aprobada por el Administrador");
	    } else {
	        this.QuitarArgent(this.getNombreUsuario(), compra.getMonto(), MetodoPago.valueOf(metodoPago));
	        this.AnadirArgent(this.getNombreUsuario(), compra.getMonto(), compra);
	        
	        this.getGaleria().getHistorialVentasDirectas().add(compra);
	       
	    }
	}
		
	public void QuitarArgent(String nombreUsuario,double Montoaretirar,MetodoPago metodopago) {
		try {
		Usuario.getGaleria().getMapaCompradores().get(nombreUsuario).getBilletera().retirarSaldo(Montoaretirar);
		}catch (Exception e) {
			System.out.println("No se pudo realizar el pago");
		}
		
	}
	public void AnadirArgent(String nombreUsuario,double MOntoanadir,Compra compra) {
		String Expropietario=compra.getPieza().getPropietario();
		if(Expropietario.equals("Galeria")) {
			
		}
		else {
		Usuario.getGaleria().getMapaCompradores().get(Expropietario).getBilletera().agregarSaldo(MOntoanadir);
		
	}

}}
