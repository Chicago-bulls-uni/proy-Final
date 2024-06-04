package models;

import java.sql.Date;
import java.util.List;

import enums.MetodoPago;

public class Compra {

    private int idCompra; 
    private List <Pieza> piezas;
    private String comprador;
    private Date fecha;
    private MetodoPago metodoPago;
    private double monto;
    private boolean Compraverificada;
    
    public Compra (List <Pieza> piezas, String compradorP, Date fechaP, MetodoPago metodoPagoP, double montoP) {
        
        this.piezas=piezas;
        this.comprador=compradorP;
        this.fecha= fechaP;
        this.metodoPago=metodoPagoP;
        this.monto= montoP;
        this.Compraverificada=false;
        
    }

    public int getIdCompra() {
        return idCompra;
    }

    

    public List<Pieza> getPiezas() {
        return piezas;
    }

    public String getComprador() {
        return comprador;
    }

    public Date getFecha() {
        return fecha;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    
    

    public boolean isCompraverificada() {
        return Compraverificada;
    }

    public void setCompraverificada(boolean compraverificada) {
        Compraverificada = compraverificada;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
        
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
}