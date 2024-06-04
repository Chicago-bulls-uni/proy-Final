package models;

import java.util.HashMap;
import java.util.Map;


public class ControllerCompras {
private Map< String,Compra> mapaCompras;

public ControllerCompras() {
super();
this.mapaCompras = new HashMap<String, Compra>();
}

public Map<String, Compra> getMapaCompras() {
return mapaCompras;
}
public void anadiracompras(String nombrePieza,Compra compra) {

this.getMapaCompras().put(nombrePieza, compra);
}
public Compra getCompraEspecifica(String nombrePieza) {
Compra comprasa=this.getMapaCompras().get(nombrePieza);
return comprasa;

}



}