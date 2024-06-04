package basedatos;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import enums.EstadoPieza;
import enums.GeneroFotografico;
import enums.ProcesoImpresion;
import enums.TecnicaPintura;
import enums.TipoPapel;
import enums.TipoVideo;
import models.Autor;
import models.Dimensiones;
import models.Escultura;
import models.Fotografia;
import models.Impresion;
import models.Pieza;
import models.Pintura;
import models.Video;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Inventario {
    private Map<String, Pieza> piezasinventario;
    public Inventario() {
        this.piezasinventario = new HashMap<String, Pieza>();
        new HashMap<String, Autor>();
    }

    public void dataBaseAgregar(int idPieza, String nombre, int date, String lugarCreacion, String autor, String tipo, String dimensiones, String materiales, boolean necesitaElectricidad, String estadoPieza, java.sql.Date fechaIngresa, java.util.Date fechaLimite, String aditionalInfo,String propietario, int precio) {
        if (!this.piezasinventario.containsKey(nombre)) {
        	try {
                Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/bd_subasta", "root", "");
                PreparedStatement pst = connector.prepareStatement("INSERT INTO objects (idPieza, nombre, fechaCreacion, lugarCreacion, autor, tipo, dimensiones, materiales, necesitaElectricidad, datefechaIngresa, Estado_Pieza, datefechalimite, tecnicapintura, peso, duracion, tipovideo, generofoto, tipoImpresion, Tipopapel, Propietario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?)");
 
                pst.setInt(1, idPieza); //id
                pst.setString(2, nombre);
                pst.setInt(3, date);
                pst.setString(4, lugarCreacion);
                pst.setString(5, autor);
                pst.setString(6, tipo);
                pst.setString(7, dimensiones);

                // Set default values
                pst.setString(8, null); // materiales
                pst.setBoolean(9, false); // necesitaElectricidad
                pst.setDate(10, new java.sql.Date(System.currentTimeMillis())); // date
       
                pst.setString(11, estadoPieza); // Estado_Pieza
                pst.setDate(12, new java.sql.Date(fechaLimite.getTime())); // date
                pst.setString(13, null); // tecnicapintura
                pst.setString(14, null); // peso
                pst.setString(15, null); // duracion
                pst.setString(16, null); // tipovideo
                pst.setString(17, null); // generofoto
                pst.setString(18, null); // tipoImpresion
                pst.setString(19, null); // Tipopapel
                pst.setString(20, propietario);
                pst.setInt(21, precio );

                // Actualizar según el tipo de pieza
                String[] listainfo = aditionalInfo.split(",");
                if (tipo.equals("Pintura")) {
                    pst.setString(14, listainfo[0]); // tecnicapintura

                    Dimensiones D = parseDimensiones(dimensiones);
                    Pintura p = new Pintura(idPieza, nombre, false, autor, date, lugarCreacion, tipo, D, fechaIngresa, EstadoPieza.valueOf(estadoPieza), TecnicaPintura.valueOf(listainfo[0]),propietario, precio);
                    this.getPiezasinventario().put(nombre, p);
                } else if (tipo.equals("Escultura")) {
                    pst.setString(8, materiales); // materiales
                    pst.setBoolean(9, necesitaElectricidad); // necesitaElectricidad
                    pst.setString(15, listainfo[0]); // peso

                    Dimensiones D = parseDimensiones(dimensiones);
                    Escultura p = new Escultura(idPieza, nombre, false, autor, date, lugarCreacion, tipo, D, fechaIngresa, listainfo[0], materiales, EstadoPieza.valueOf(estadoPieza),propietario, precio);
                    this.getPiezasinventario().put(nombre, p);
                } else if (tipo.equals("Video")) {
                    pst.setBoolean(9, necesitaElectricidad); // necesitaElectricidad
                    pst.setString(16, listainfo[0]); // duracion
                    pst.setString(17, listainfo[1]); // tipovideo

                    Video p = new Video(idPieza, nombre, false, autor, date, lugarCreacion, tipo, fechaIngresa, listainfo[0], TipoVideo.valueOf(listainfo[1]), EstadoPieza.valueOf(estadoPieza),propietario, precio);
                    this.getPiezasinventario().put(nombre, p);
                } else if (tipo.equals("Fotografia")) {
                    pst.setBoolean(9, necesitaElectricidad); // necesitaElectricidad
                    pst.setString(18, listainfo[0]); // generofoto

                    Fotografia p = new Fotografia(idPieza, nombre, false, autor, date, lugarCreacion, tipo, fechaIngresa, GeneroFotografico.valueOf(listainfo[0]), EstadoPieza.valueOf(estadoPieza),propietario, precio);
                    this.getPiezasinventario().put(nombre, p);
                } else if (tipo.equals("Impresion")) {
                    pst.setBoolean(9, false); // necesitaElectricidad
                    pst.setString(19, listainfo[0]); // tipoImpresion
                    pst.setString(20, listainfo[1]); // Tipopapel

                    Impresion p = new Impresion(idPieza, nombre, false, autor, date, lugarCreacion, tipo, fechaIngresa, ProcesoImpresion.valueOf(listainfo[0]), TipoPapel.valueOf(listainfo[1]), EstadoPieza.valueOf(estadoPieza),propietario, precio);
                    this.getPiezasinventario().put(nombre, p);
                }
                
                pst.setString(14, null); // tecnicapintura
                pst.setString(15, null); // peso
                pst.setString(16, null); // duracion
                pst.setString(17, null); // tipovideo
                pst.setString(18, null); // generofoto
                pst.setString(19, null); // tipoImpresion
                pst.setString(20, null); // Tipopapel

                

                pst.executeUpdate();
                pst.close();
                connector.close();
                System.out.println("Registro completado: " + idPieza);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La pieza ya está registrada en el inventario, ZZZZZZ");
        }
    }

    public Dimensiones parseDimensiones(String dimensiones) {
        String[] DI = dimensiones.split(",");
        return new Dimensiones(Double.parseDouble(DI[0]), Double.parseDouble(DI[1]), Double.parseDouble(DI[2]));
    }

    public void getAllPiezas() throws SQLException {
        new ArrayList<>();
        try {
        Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/bd_subasta", "root", "");
        
        PreparedStatement pst = connector.prepareStatement("SELECT * FROM objects ");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int idPieza = rs.getInt("idPieza");
            String nombre = rs.getString("nombre");
            int fechaCreacion = rs.getInt("fechaCreacion");
            String lugarCreacion = rs.getString("lugarCreacion");
            String tipo = rs.getString("tipo");
            String autor = rs.getString("autor");
            String dimensiones = rs.getString("dimensiones");
            String materiales = rs.getString("materiales");
            rs.getBoolean("necesitaElectricidad");
            java.sql.Date fechaIngresa = rs.getDate("datefechaIngresa");
            String estadoPieza = rs.getString("Estado_Pieza");
            String tecnicaPintura = rs.getString("tecnicapintura");
            String peso = rs.getString("peso");
            String duracion = rs.getString("duracion");
            String tipoVideo = rs.getString("tipovideo");
            String generoFoto = rs.getString("generofoto");
            String tipoImpresion = rs.getString("tipoImpresion");
            String tipoPapel = rs.getString("Tipopapel");
            String propietario=rs.getString("Propietario");
            int precio = rs.getInt("precio");

            if (tipo.equals("Pintura")) {
                String[] DI = dimensiones.split(",");
                Dimensiones D = new Dimensiones(Double.parseDouble(DI[0]), Double.parseDouble(DI[1]), Double.parseDouble(DI[2]));
                Pintura p = new Pintura(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, D, fechaIngresa, EstadoPieza.valueOf(estadoPieza), TecnicaPintura.valueOf(tecnicaPintura),propietario, precio);
                this.getPiezasinventario().put(nombre, p);
            } else if (tipo.equals("Escultura")) {
                String[] DI = dimensiones.split(",");
                Dimensiones D = new Dimensiones(Double.parseDouble(DI[0]), Double.parseDouble(DI[1]), Double.parseDouble(DI[2]));
                Escultura p = new Escultura(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, D, fechaIngresa, peso, materiales, EstadoPieza.valueOf(estadoPieza),propietario, precio);
                this.getPiezasinventario().put(nombre, p);
            } else if (tipo.equals("Video")) {
                Video p = new Video(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, fechaIngresa, duracion, TipoVideo.valueOf(tipoVideo), EstadoPieza.valueOf(estadoPieza),propietario, precio);
                this.getPiezasinventario().put(nombre, p);
            } else if (tipo.equals("Fotografia")) {
                Fotografia p = new Fotografia(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, fechaIngresa, GeneroFotografico.valueOf(generoFoto), EstadoPieza.valueOf(estadoPieza),propietario, precio);
                this.getPiezasinventario().put(nombre, p);
            } else if (tipo.equals("Impresion")) {
                Impresion p = new Impresion(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, fechaIngresa, ProcesoImpresion.valueOf(tipoImpresion), TipoPapel.valueOf(tipoPapel), EstadoPieza.valueOf(estadoPieza),propietario, precio);
                this.getPiezasinventario().put(nombre, p);
            }
        }
    
        rs.close();
        pst.close();
        connector.close();
    }catch (SQLException e) {
        e.printStackTrace();}}
    public void CambiarEstadoPieza(String estadoPieza,String nombrepieza) {
    	  try {
              Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/bd_subasta", "root", "");
              PreparedStatement pst = connector.prepareStatement("SELECT * FROM objects WHERE nombre =" + nombrepieza);
         
              pst.setString(12, estadoPieza);
              

              int rowsAffected = pst.executeUpdate();
              System.out.println("Filas Afectadas: " + rowsAffected);
            		  
    	  }catch (SQLException e) {
              e.printStackTrace();}
    }
    public void CambiarPropietario(String UserPropietario,String nombrepieza) {
  	  try {
            Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/bd_subasta", "root", "");
            PreparedStatement pst = connector.prepareStatement("SELECT * FROM objects WHERE nombre =" + nombrepieza);
       
            pst.setString(21, UserPropietario);
            

            int rowsAffected = pst.executeUpdate();
            System.out.println("Filas Afectadas: " + rowsAffected);
          		  
  	  }catch (SQLException e) {
            e.printStackTrace();}
  }

    public void agregarpieza(String nombre, Pieza pieza) {
        this.piezasinventario.put(nombre, pieza);
    }

    public Map<String, Pieza> getPiezasinventario() {
        return piezasinventario;
    }

    public List<Pieza> obtenerPiezasExhibidasList(List<Pieza> listaPiezas) {
        List<Pieza> piezasExhibidas = new ArrayList<>();
        List<Pieza> exibidos = new ArrayList<>();

        for (Pieza pieza : listaPiezas) {
            if (pieza.getEstadoPieza() == EstadoPieza.EXHIBIDA) {
                piezasExhibidas.add(pieza);
            }
        }

        Iterator<String> it = this.piezasinventario.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Pieza piezaEnInventario = this.piezasinventario.get(key);

            if (piezaEnInventario.getEstadoPieza() == EstadoPieza.EXHIBIDA) {
                exibidos.add(piezaEnInventario);
            }
        }

        piezasExhibidas.addAll(exibidos);

        return piezasExhibidas;
    }

    public List<Pieza> obtenerPiezasBodega(List<Pieza> listaPiezas) {
        List<Pieza> piezasBodega = new ArrayList<>();
        for (Pieza pieza : listaPiezas) {
            if (pieza.getEstadoPieza() == EstadoPieza.BODEGA) {
                piezasBodega.add(pieza);
            }
        }
        return piezasBodega;
    }

    public List<Pieza> obtenerPiezasEnVenta() {
        List<Pieza> piezasEnVenta = new ArrayList<>();
        for (Map.Entry<String, Pieza> entry : piezasinventario.entrySet()) {
            Pieza pieza = entry.getValue();
            if (pieza.getEstadoPieza() == EstadoPieza.ENVENTA) {
                piezasEnVenta.add(pieza);
            }
        }
        return piezasEnVenta;
    }

    public List<Pieza> obtenerPiezasSubasta() {
        List<Pieza> piezasSubasta = new ArrayList<>();
        for (Map.Entry<String, Pieza> entry : piezasinventario.entrySet()) {
            Pieza pieza = entry.getValue();
            if (pieza.getEstadoPieza() == EstadoPieza.ENSUBASTA) {
                piezasSubasta.add(pieza);
            }
        }
        return piezasSubasta;
    }

    public List<Pieza> obtenerPiezasExhibicion() {
        List<Pieza> piezasExhibicion = new ArrayList<>();
        for (Map.Entry<String, Pieza> entry : piezasinventario.entrySet()) {
            Pieza pieza = entry.getValue();
            if (pieza.getEstadoPieza() == EstadoPieza.EXHIBIDA) {
                piezasExhibicion.add(pieza);
            }
        }
        return piezasExhibicion;
    }

    public void eliminarPiezaListExhibicion(Pieza pieza) {
        List<Pieza> piezasExhibicion = obtenerPiezasExhibicion();
        if (piezasExhibicion.contains(pieza)) {
            piezasExhibicion.remove(pieza);
        } else {
            System.out.println("La pieza no está en la lista de exhibición.");
        }
    }

    public void eliminarPiezaListSubasta(Pieza pieza) {
        List<Pieza> piezasSubasta = obtenerPiezasSubasta();
        if (piezasSubasta.contains(pieza)) {
            piezasSubasta.remove(pieza);
        } else {
            System.out.println("La pieza no está en la lista de subastas.");
        }
    }
    
    
    
    public Map<String, Pieza> obtenerPiezasEnMapa() {
        Map<String, Pieza> piezas = new HashMap<>();
        String query = "SELECT * FROM objects";

        try (Connection connector = DriverManager.getConnection("jdbc:mysql://localhost/bd_subasta", "root", "");
             PreparedStatement pst = connector.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int idPieza = rs.getInt("idPieza");
                String nombre = rs.getString("nombre");
                int fechaCreacion = rs.getInt("fechaCreacion");
                String lugarCreacion = rs.getString("lugarCreacion");
                String tipo = rs.getString("tipo");
                String autor = rs.getString("autor");
                String dimensiones = rs.getString("dimensiones");
                String materiales = rs.getString("materiales");
                rs.getBoolean("necesitaElectricidad");
                java.sql.Date fechaIngresa = rs.getDate("datefechaIngresa");
                String estadoPieza = rs.getString("Estado_Pieza");
                String tecnicaPintura = rs.getString("tecnicapintura");
                String peso = rs.getString("peso");
                String duracion = rs.getString("duracion");
                String tipoVideo = rs.getString("tipovideo");
                String generoFoto = rs.getString("generofoto");
                String tipoImpresion = rs.getString("tipoImpresion");
                String tipoPapel = rs.getString("Tipopapel");
                String propietario = rs.getString("Propietario");
                int precio = rs.getInt("precio");

                Dimensiones D = parseDimensiones(dimensiones);
                Pieza pieza = null;

                if (tipo.equals("Pintura")) {
                    pieza = new Pintura(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, D, fechaIngresa, EstadoPieza.valueOf(estadoPieza), TecnicaPintura.valueOf(tecnicaPintura), propietario, precio);
                } else if (tipo.equals("Escultura")) {
                    pieza = new Escultura(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, D, fechaIngresa, peso, materiales, EstadoPieza.valueOf(estadoPieza), propietario, precio);
                } else if (tipo.equals("Video")) {
                    pieza = new Video(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, fechaIngresa, duracion, TipoVideo.valueOf(tipoVideo), EstadoPieza.valueOf(estadoPieza), propietario, precio);
                } else if (tipo.equals("Fotografia")) {
                    pieza = new Fotografia(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, fechaIngresa, GeneroFotografico.valueOf(generoFoto), EstadoPieza.valueOf(estadoPieza), propietario, precio);
                } else if (tipo.equals("Impresion")) {
                    pieza = new Impresion(idPieza, nombre, false, autor, fechaCreacion, lugarCreacion, tipo, fechaIngresa, ProcesoImpresion.valueOf(tipoImpresion), TipoPapel.valueOf(tipoPapel), EstadoPieza.valueOf(estadoPieza), propietario, precio);
                }

                piezas.put(nombre, pieza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return piezas;
    }
    
      
    
    
    
}
