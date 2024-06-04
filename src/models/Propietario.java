package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import enums.EstadoPieza;
import seguridad.Usuario;

public class Propietario extends Comprador {
	
	
	
	private String contacto;
	private double limiteCompra;
	private Map<Date, List<Pieza>> HistorialPieza;
	private List<Pieza> Piezasadquiridad;
	
	
	
	public Propietario (String Usuario,String Contrasena, String nombreP, String contactoP,  double limiteCompraP, boolean verificadoP,Galeria galeria1, double saldo) {
		super(Usuario, Contrasena,nombreP,contactoP,limiteCompraP,verificadoP,galeria1, saldo );
		
		
		this.Piezasadquiridad= new ArrayList<Pieza>();
		this.HistorialPieza= new TreeMap<Date, List<Pieza>>();
		this.limiteCompra=limiteCompraP;
		
		
		
		
	}
	

	
	
	public void Consignacion(String nombre,Date fechafin, String Quehacer, double Precio) {
		Galeria galeria = Usuario.getGaleria();
		for(Pieza i: this.getPiezasadquiridad()) {
		this.getPiezasadquiridad();
			
			if(i.getNombre()== nombre){
				if(Quehacer.toUpperCase()=="VENDER"){
					galeria.getInventary().getPiezasinventario().get(nombre).cambiarEstadoPieza(Quehacer);
					i.cambiarEstadoPieza(Quehacer);
					Usuario.getGaleria().getInventary().CambiarEstadoPieza(Quehacer, nombre);
					
				}
				else if (Quehacer.toUpperCase()=="SUBASTAR"){
					galeria.getInventary().getPiezasinventario().get(nombre).cambiarEstadoPieza(Quehacer);
					i.cambiarEstadoPieza(Quehacer);
					Usuario.getGaleria().setSubasta(Precio, nombre, (java.sql.Date) fechafin);
					Usuario.getGaleria().getInventary().CambiarEstadoPieza(Quehacer, nombre);
					
				}
				else if (Quehacer.toUpperCase()=="EXHIBIR") {
					galeria.getInventary().getPiezasinventario().get(nombre).cambiarEstadoPieza(Quehacer);
					i.cambiarEstadoPieza(Quehacer);
					Usuario.getGaleria().getInventary().CambiarEstadoPieza(Quehacer, nombre);
					
				}
				
				
				
				
			}
			
		}
		
		
	}

	public String getContacto() {
		return contacto;
	}



	public double getLimiteCompra() {
		return limiteCompra;
	}
	//nombre corregido
	public void anadirPiezasPropietario(Pieza piece, Date Fechaing) {
		this.Piezasadquiridad.add(piece);
	    
	    
	    if (!this.HistorialPieza.containsKey(Fechaing)) {
	        this.HistorialPieza.put(Fechaing, new ArrayList<Pieza>());}
	    List<Pieza> piecesList = this.HistorialPieza.get(Fechaing);
	    
	    
	    piecesList.add(piece);
	}
	public void EliminarPiezasDisponibles(Pieza piece) {
		if(this.Piezasadquiridad.contains(piece)){
			this.Piezasadquiridad.remove(piece);
		}
		else System.out.println("No se puede eliminar una pieza que no hace parte de las pizeas del propietario");
		
	}
	



	


	public Map<Date, List<Pieza>> getHistorialPieza() {
		return HistorialPieza;
	}
	
	






	public List<Pieza> getPiezasadquiridad() {
		return Piezasadquiridad;
		
	}
	public Pieza getPiearequerida( String nombre ) {
		Pieza founded= null;
		
		
		for (Pieza Piece : this.getPiezasadquiridad()) {
			
		    
			if (Piece.getNombre()== nombre){
				 founded= Piece;
				
			}
			else {
				founded= null;
			}
		
		}
		
		return founded;
		    
		
		
	}




	public void setContacto(String contacto) {
		this.contacto = contacto;
	}



	public void setLimiteCompra(double limiteCompra) {
		this.limiteCompra = limiteCompra;
	}



	

	
	public void actualizarPropietario () {
		
	}
	
	public void ponerEnVenta(Pieza pieza, double precio) {
	    
	    
	    pieza.setPrecio(precio);
	    pieza.setEstadoPieza(EstadoPieza.ENVENTA);
	    this.anadirPiezasPropietario(pieza, new Date());
	}
	
	
	//REVISAR AL IMPLEMENTAR BIEN ADMIN RESGITROPIEZASUBASTA
	
	
	public void venderPieza(Pieza pieza, double monto) {
        // Cambiar el estado de la pieza a VENDIDO en el mapa historialPieza
        if (HistorialPieza.containsKey(pieza)) {
            List<Pieza> listaPiezas = HistorialPieza.get(pieza);
            for (Pieza p : listaPiezas) {
                if (p.getIdPieza() == pieza.getIdPieza()) {
                    p.setEstadoPieza(EstadoPieza.VENDIDO);
                    break;
                }
               
            }    
        }
        
        billetera.agregarSaldo(monto);
        billetera.agregarTransaccionExitosa(monto, pieza );
    }

    public void piezaDevolucion(Pieza pieza) {
        
    	
        if (Piezasadquiridad.contains(pieza)) {
            Piezasadquiridad.get(Piezasadquiridad.indexOf(pieza)).cambiarEstadoPieza("DEVUELTA");
            HistorialPieza.remove(pieza);
            System.out.println("La pieza ha sido devuelta y eliminada del historial.");
        } else {
            System.out.println("La pieza no estÃ¡ en la lista de piezas adquiridas.");
        }
    }
	
}