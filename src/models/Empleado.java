package models;

import java.util.List;
import seguridad.Usuario;

public class Empleado extends Usuario {
    
    private Usuario idEmpleado;
    
    public Empleado ( String Usuario,String Contrasena, int nivel,Galeria galeria1)
             {
           super(Usuario, Contrasena, nivel,galeria1);
           
       
        
        
    }

    public Usuario getIdEmpleado() {
        return idEmpleado;
    }

    
    public void setIdEmpleado(Usuario idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public List<Compra> verHistorialVentasDirectas(Galeria galeria) {
        return galeria.getHistorialVentasDirectas();
    }
    
 
}
