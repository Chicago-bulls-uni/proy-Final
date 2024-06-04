package models;

import java.util.Date;
import enums.EstadoPieza;

public class Pieza {

	private int idPieza;
	private String Nombre;
	private String tipo;
	private boolean bloqueada;
	private String autor;
	
	private int anio;
	private String lugarCreacion;
	private EstadoPieza estadoPieza;
	

	
    
    private String dimensiones;
    
    private boolean necesitaElectricidad;
    private Date fechaIngresa;
    private Date fechaVenta;
    private double precio;
    private String propietario;
  
	
	
    public Pieza(int idPiezaP,String nombre, String tipo, boolean bloqueadaP, String autorP,  int anioP, String lugarCreacionP, Date fechaIngresa,EstadoPieza estadopiece, String contactoPropietario,int Precio) {

        this.idPieza= idPiezaP;
        this.Nombre=nombre;
        this.tipo=tipo;
        this.bloqueada=bloqueadaP;
        this.autor= autorP;
        this.anio= anioP;
        this.fechaIngresa = fechaIngresa;
        this.estadoPieza= estadopiece;
        this.precio= Precio;
        this.propietario= contactoPropietario;
        
		
	}
    public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
    
    public String getPropietario() {
		return propietario;
	}

    public void setPrecio(double precioP) {
		this.precio = precioP;
	}
    
    public double getPrecio() {
		return precio;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	//AGREGADO
	public Date getFechaVenta() {
		return fechaVenta;
	}

	
	


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}





	public String getDimensiones() {
		return dimensiones;
	}


	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
	public void cambiarEstadoPieza(String estado) {
		this.estadoPieza= EstadoPieza.valueOf(estado);
		
		
	}




	


	public boolean isNecesitaElectricidad() {
		return necesitaElectricidad;
	}


	public void setNecesitaElectricidad(boolean necesitaElectricidad) {
		this.necesitaElectricidad = necesitaElectricidad;
	}


	public Date getFechaIngresa() {
		return fechaIngresa;
	}


	public void setFechaIngresa(Date fechaIngresa) {
		this.fechaIngresa = fechaIngresa;
	}	


	public int getIdPieza() {
		return idPieza;
	}


	public boolean isBloqueada() {
		return bloqueada;
	}


	public String getAutor() {
		return autor;
	}


	


	public int getAnio() {
		return anio;
	}


	public String getLugarCreacion() {
		return lugarCreacion;
	}


	


	public void setIdPieza(int idPieza) {
		this.idPieza = idPieza;
	}


	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}




	public void setAnio(int anio) {
		this.anio = anio;
	}


	public void setLugarCreacion(String lugarCreacion) {
		this.lugarCreacion = lugarCreacion;
	}

	
	
	 


		public EstadoPieza getEstadoPieza() {
			return estadoPieza;
		}


		public void setEstadoPieza(EstadoPieza estadoPieza) {
			this.estadoPieza = estadoPieza;
		}

	    public void venderPieza(Pieza pieza) {
	        pieza.setEstadoPieza(EstadoPieza.VENDIDO);
	    }

	    public void devolverPieza(Pieza pieza) {
	        pieza.setEstadoPieza(EstadoPieza.DEVUELTA);
	    }

		public String getNombre() {
			return Nombre;
		}

		public void setNombre(String nombre) {
			Nombre = nombre;
		}
}