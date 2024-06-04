package models;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import basedatos.Administrador;
import basedatos.Inventario;
import enums.EstadoPieza; 
import enums.MetodoPago;


public class Galeria {
	
	
	private List<Empleado> empleados;
	private List<Integer> historialPiezas; 
	private  Map<Integer, Subasta> mapaSubastas;
	private Map<String, Propietario> mapaCompradores;
	private ControllerCompras compras= new ControllerCompras();
	private  int ContadorSub=0;
    private Queue<Oferta> HistorialofertasTransitorias;
	private Inventario inventary;
	private List<Compra> historialVentasDirectas;
	private List<Pieza> piezas;
	private Administrador admin;	
	
    
	
	public Galeria (List<Empleado> empleadosP ,List<Integer> historialPiezasP) {
		
		this.empleados= empleadosP;
		this.historialPiezas=historialPiezasP;
		mapaSubastas=new HashMap<Integer, Subasta>( );
		this.BeginInventary();
		this.HistorialofertasTransitorias= new LinkedList<Oferta> ();
		this.mapaCompradores = new HashMap<>(); 
		this.compras = new ControllerCompras();
		this.piezas = new ArrayList<>(); 
		this.historialVentasDirectas= new ArrayList<>();
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Administrador getAdmin() {
		return admin;
	}
	

	public List<Compra> getHistorialVentasDirectas() {
        return historialVentasDirectas;
    }
	public Queue<Oferta> getHistorialofertasTransitorias() {
		return HistorialofertasTransitorias;
	}
	public Map<String, Propietario> getMapaCompradores() {
        return mapaCompradores;
    }
	public void setMapaCompradores(String nombre, Propietario comprador) {
	    this.mapaCompradores.put(nombre, comprador);
	}
	
	public Compra getCompra(int idCompra) {
		for (Compra compra : historialVentasDirectas) {
			if (compra.getIdCompra() == idCompra) {
				return compra;
			}
		}
		return null;
	}

	public void iniciarcompradore(String usuario, Propietario prope) {
        if(mapaCompradores==null) {
            mapaCompradores= new HashMap<String, Propietario>();
            mapaCompradores.put(usuario, prope);
        }
        else {
            mapaCompradores.put(usuario, prope);
        }
    }

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public List<Integer> getHistorialPiezas() {
		return historialPiezas;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public void BeginInventary() {
		this.inventary= new Inventario();
	};

	public void setHistorialPiezas(List<Integer> historialPiezas) {
		this.historialPiezas = historialPiezas;
	}


	public Inventario getInventary() {
		return inventary;
	}

	public   void setSubasta( double valorminimo,String NombrePiezas,Date fechaFina) {
        System.out.println("P");
        List<Pieza> Piezas= new ArrayList<Pieza>();
        String[] DI= NombrePiezas.split(",");
         for (int i = 0; i < DI.length; i++) {
             
             Piezas.add(this.getInventary().getPiezasinventario().get(DI[i]));
             
         }
        
        
        Subasta newsub= new Subasta(this.ContadorSub,valorminimo,fechaFina);
        newsub.AgregarpiezasSubasta(Piezas);
        
        this.mapaSubastas.put(this.ContadorSub, newsub);
        this.ContadorSub ++;
        
        
    }

	

	public void  AgregarpiezasSubasta(List<Pieza> piezas1){
        for (int i = 0; i < piezas1.size(); i++) {
            Pieza b =piezas1.get(i);
               this.piezas.add(b);
           }
       
   }

	

	public void setMapaSubastas(Map<Integer, Subasta> mapaSubastas) {
		this.mapaSubastas = mapaSubastas;
	}
	
	public List<Pieza> verPiezasEnVenta() {
        return inventary.obtenerPiezasEnVenta();
    }
	
	
	
	public void realizarCompraDirecta(Comprador comprador, int idPieza, MetodoPago metodoDePago) {
	    Pieza pieza = null;
	    List<Pieza> piezasEnVenta = inventary.obtenerPiezasEnVenta();
	    for (Pieza p : piezasEnVenta) {
	        if (p.getIdPieza() == idPieza) {
	            pieza = p;
	            break;
	        }
	    }

	    if (pieza != null) {
	        if (!pieza.isBloqueada()) {
	            if (comprador.isVerificado()) {
	                double montoAPagar = pieza.getPrecio();
	                if (comprador.getLimiteCompra() < montoAPagar) {
	                    System.out.println("El límite de compra del comprador no es suficiente para pagar esta pieza.");
	                } else {
						Random rand = new Random();
						int idCompra = rand.nextInt(1000);

	                    Compra compra = new Compra(piezas, comprador.getNombre().toString(), new Date(System.currentTimeMillis()), metodoDePago, montoAPagar);
	                    if (comprador.realizarPago(metodoDePago, montoAPagar, pieza)) {
	                        comprador.registrarCompraEnHistorial(comprador, compra);
	                        pieza.setEstadoPieza(EstadoPieza.VENDIDO);
	                        pieza.setBloqueada(true);
	                        String propietario = pieza.getPropietario();
	                        mapaCompradores.get(propietario).getBilletera().agregarSaldo(montoAPagar);
	                        historialVentasDirectas.add(compra); 
	                        System.out.println("Compra realizada con éxito.");
	                    } else {
	                        System.out.println("La compra no pudo ser procesada debido a un problema con el pago.");
	                    }
	                }
	            } else {
	                System.out.println("El comprador no está autorizado para realizar compras.");
	            }
	        } else {
	            System.out.println("La pieza seleccionada ya ha sido vendida.");
	        }
	    } else {
	        System.out.println("La pieza con el ID especificado no se encontró o no está disponible para la venta.");
	    }
	}


	public void anadiraOfertasTransitorias(Oferta offer) {
		this.HistorialofertasTransitorias.add(offer);
	}

	public Map<Integer, Subasta> getMapaSubastas() {
		
		return mapaSubastas;
	}
	public ControllerCompras getCompras() {
		return this.compras;
	}
	
	public List<Pieza> getPiezas() {
        return piezas;
    }
}
