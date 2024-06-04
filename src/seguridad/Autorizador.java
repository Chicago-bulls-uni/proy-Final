package seguridad;


import java.util.List;

import basedatos.RegistroUser;

public class Autorizador {
	
	private static final String REGISTRAR_INGRESO = "registrarIngreso";
    private static final String CONFIRMAR_VENTA = "confirmarVenta";
    private static final String CONFIRMAR_DEVOLUCION = "confirmarDevolucion";
    private static final String REGISTRAR_OFERTA = "registrarOferta";
    private static final String VERIFICAR_COMPRADOR = "verificarComprador";
    private static final String ESTABLECER_MAXIMO_COMPRA = "establecerMaximoCompra";
	
    private RegistroUser registroUser;

    public Autorizador() {
        this.registroUser = new RegistroUser();
    }

    public boolean tienePermiso(String nombreUsuario, String permiso) {
        try {
            List<Object> tabla = registroUser.buscarUsuario(nombreUsuario);
            int nivel = (int) tabla.get(2);
            
            switch (permiso) {
            case REGISTRAR_INGRESO:
            case CONFIRMAR_VENTA:
            case CONFIRMAR_DEVOLUCION:
            case VERIFICAR_COMPRADOR:
            case ESTABLECER_MAXIMO_COMPRA:
                return nivel >= 3;
            case REGISTRAR_OFERTA:
                return nivel >= 2;
            default:
                return true;
        
            }
        } catch (Exception e) {
            System.out.println("Error al verificar permiso: " + e.getMessage());
        }
        return false;
    }
}