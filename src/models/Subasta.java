package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Subasta {
      
     private int idSubasta; 
     private List<Pieza> piezas;
     private double valorMinimo;
     private List<Oferta> ofertas;
     private Date fechainicio;
     private Date fechafin;
     private boolean activo;
     
     
     
     public Subasta (int idSubastaP, double valorMinimoP, Date fechafini) {
         this.idSubasta=idSubastaP;
         this.piezas= new ArrayList<Pieza>();
         this.valorMinimo=valorMinimoP;
         this.ofertas= new ArrayList<Oferta>();
         this.fechainicio= new Date(System.currentTimeMillis());
         this.activo=true;
         this.fechafin=fechafini;
         
     }
     


    public Date getFechafin() {
        return fechafin;
    }
    



    public int getIdSubasta() {
        return idSubasta;
    }


    public List<Pieza> getPiezas() {
        return piezas;
    }
    public void  AgregarpiezasSubasta(List<Pieza> piezas1){
        
         for (int i = 0; i < piezas1.size(); i++) {
             Pieza b =piezas1.get(i);
                this.getPiezas().add(b);
            }
        
    }


    public double getValorMinimo() {
        return valorMinimo;
    }


    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
    }


    public void setPiezas(List<Pieza> piezas) {
        this.piezas = piezas;
    }


    public void setfechafin(Date fechaFin) {
        this.fechafin=fechaFin;
        
    }   
    


    public boolean isActivo() {
        return activo;
    }



    public List<Oferta> getOfertas() {
        return ofertas;
    }


    public void AnadirOferta(Oferta newoffer) {
        
        this.ofertas.add(newoffer);
    }
    public Oferta ObtenermaxOferta(){
        
        this.ofertas.sort(Comparator.comparing(Oferta::getMontoOfrecido));
        return this.ofertas.get(0);
    }
    public Date getFechainicio() {
        return fechainicio;
    }


    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }


    public void finalizarSubasta() {
        this.activo = false;
        
    }
    
     
     
     
}