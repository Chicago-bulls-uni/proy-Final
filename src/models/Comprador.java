package models;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import enums.MetodoPago;
import seguridad.Usuario;
import utils.ofertaNegativoException;
public class Comprador extends Usuario {
	
	private int idComprador;
	private String nombre;
	private String contacto;
	
	private Map<Date, Compra> comprasRealizadas;
	private double limiteCompra;
	private boolean verificado;
	protected Billetera billetera;

	
	public Comprador (String Usuario,String Contrasena, String nombreP, String contactoP,  double limiteCompraP, boolean verificadoP,Galeria galeria1, double saldo) 
	{   super(Usuario, Contrasena, 0 ,galeria1);
		
		this.setNombre(nombreP);
		this.comprasRealizadas = new TreeMap<Date, Compra>();
		this.setContacto(contactoP);
		this.verificado=false;
		this.setLimiteCompra(limiteCompraP);
		this.setVerificado(verificadoP);
		this.billetera = new Billetera();

	
	}
	
	

	public Billetera getBilletera() {
		return billetera;
	}



	public void setBilletera(Billetera billetera) {
		this.billetera = billetera;
	}



	public int getIdComprador() {
		return idComprador;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContacto() {
		return contacto;
	}
	
	public   int realizarOferta(int idSubastaP, double valorMinimoP, String metodopago) throws Exception {
        Galeria galeria = Usuario.getGaleria();
        Oferta offer= null;
        int b=3;
        
        
        
        
          if (valorMinimoP < 0 || this.isVerificado() == false) {
              b=0;
              throw new ofertaNegativoException("El valor mínimo  de la oferta no puede ser negativo");
              
             }
          
          
          else if (valorMinimoP == 0 || this.isVerificado() == false)  {
                System.out.println(" El valor de la oferta no puede ser nulo");
                b=1;
                }
          
         else if (this.isVerificado() == false) {
             System.out.println(" Comprador no esta verificado");
             b=2;
              }
         else {
          offer = new Oferta(idSubastaP, this.getNombreUsuario(), valorMinimoP);
          galeria.getMapaSubastas().get(idSubastaP);
          galeria.anadiraOfertasTransitorias(offer);
          b=3;
            }
        return b ;  
          }

	public Map<Date, Compra> getComprasRealizadas() {
		return comprasRealizadas;
	}

	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	

	public double getLimiteCompra() {
		return limiteCompra;
	}

	public void setLimiteCompra(double limiteCompra) {
		this.limiteCompra = limiteCompra;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}
	
	public void comprarPieza(Pieza pieza, MetodoPago metodoPago) {
		Galeria galeria = Usuario.getGaleria();
        galeria.realizarCompraDirecta(this, pieza.getIdPieza(), metodoPago);
    }
	
	//IMPLEMENTAR ESTE METODO
	public boolean realizarPago(MetodoPago metodoDePago, double montoAPagar, Pieza pieza) {
        if (billetera != null) {
            billetera.pagoPieza(montoAPagar, metodoDePago, pieza);// Se hace la consignacion
            billetera.finPagoPieza(montoAPagar, metodoDePago, pieza);// Se  extrae el dinero y se hace el registro
            return true;
        } else {
            System.out.println("Error: La billetera del comprador no está configurada.");
            return false;
        }
	
	
}
}
