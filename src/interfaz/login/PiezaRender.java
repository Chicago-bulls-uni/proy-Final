package interfaz.login;

import java.text.SimpleDateFormat;
import models.Escultura;
import models.Fotografia;
import models.Impresion;
import models.Pieza;
import models.Pintura;
import models.Video;

public class PiezaRender {
	private int idPieza;
	private String Nombre;
	private String tipo;
	private boolean bloqueada;
	private String autor;
	private SimpleDateFormat sdf;
	private int anio;
	private String lugarCreacion;
    private String dimensiones;
    private String fechaIngresa;
    private String fechaVenta;
    private double precio;
    private String propietario;
    private String DetallesEspecificos;
	public PiezaRender(Pieza piece) {
		super();
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		this.idPieza = piece.getIdPieza();
		Nombre = piece.getNombre();
		this.tipo = piece.getTipo();
		this.bloqueada= piece.isBloqueada();
		this.autor = piece.getAutor();
		this.anio = piece.getAnio();
		this.lugarCreacion = piece.getLugarCreacion();
		
		this.dimensiones = piece.getDimensiones();
		this.fechaIngresa =sdf.format( piece.getFechaIngresa());
		if(piece.getFechaVenta() != null) {
			this.fechaVenta = sdf.format( piece.getFechaVenta());
		}
		else {this.fechaVenta= "0000-00-00";
		}
		
		this.precio = piece.getPrecio();
		this.propietario = piece.getPropietario();
		if(piece instanceof Pintura) {
			Pintura pintura= (Pintura) piece;
			this.DetallesEspecificos= "Tecnica pintura:" + pintura.getTecnicaPintura().name();
		}
		else if(piece instanceof Escultura) {
			Escultura escultura = (Escultura) piece;
			this.DetallesEspecificos= "Dimensiones: " + escultura.getDimensiones()+ "," +"Peso: " +escultura.getPeso()+ "," +"Materiales" +escultura.getMateriales();
		}
		else if (piece instanceof Video) {
			Video video = (Video) piece;
			this.DetallesEspecificos="Tipo Video: "+  video.getTipovideo().name() + "," +"Duracion:"+video.getDuracionMinutos();
		}
		else if (piece instanceof Fotografia) {
			Fotografia fotografia = (Fotografia) piece;
			this.DetallesEspecificos="Genero Fotografico: "+ fotografia.getGeneroFotografico().name();
		}
		else if (piece instanceof Impresion) {
			Impresion impresion = (Impresion) piece;
			this.DetallesEspecificos ="Tipo Papel: "+ impresion.getTipoPapel().name() +"," + "Proceso: " +impresion.getProcesoImpresion().name();
		}
		else {
			this.DetallesEspecificos="Socoso";
		}
		
		
		
		
	
		  
	}
	public int getIdPieza() {
		return idPieza;
	}
	public String getNombre() {
		return Nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public boolean isBloqueada() {
		return bloqueada;
	}
	public String getAutor() {
		return autor;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public int getAnio() {
		return anio;
	}
	public String getLugarCreacion() {
		return lugarCreacion;
	}
	public String getDimensiones() {
		return dimensiones;
	}
	public String getFechaIngresa() {
		return fechaIngresa;
	}
	public String getFechaVenta() {
		return fechaVenta;
	}
	public double getPrecio() {
		return precio;
	}
	public String getPropietario() {
		return propietario;
	}
	public String getDetallesEspecificos() {
		return DetallesEspecificos;
	}
	
    
  
	

}
