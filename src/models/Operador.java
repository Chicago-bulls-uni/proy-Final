package models;

import java.util.Queue;

import seguridad.Usuario;

public class Operador extends Empleado {

    public Operador(String usuario, String contrasena, int nivel, Galeria galeria) {
        super(usuario, contrasena, nivel, galeria);
    }

    public void registrarMovimientosSubasta() {
        Galeria galeria = Usuario.getGaleria();
        Queue<Oferta> historial=galeria.getHistorialofertasTransitorias();
        
        
        while (!historial.isEmpty()) {
            Oferta offer=historial.remove();
            if (!galeria.getMapaSubastas().get(offer.getSubasta()).isActivo() && galeria.getMapaSubastas().containsKey(offer.getSubasta())){
            galeria.getMapaSubastas().get(offer.getSubasta()).AnadirOferta(offer);
            System.out.println(" Anadir la Oferta del Usuario "+offer.getComprador()+" a la subasta" +  offer.getSubasta());
            }
            
            
            else {
                System.out.println("La subasta no esta disponible para Añadir ofertas");
            }  
        }
     }
   }	


