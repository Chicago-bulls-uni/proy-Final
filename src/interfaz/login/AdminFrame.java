package interfaz.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import basedatos.Administrador;
import basedatos.Inventario;
import models.Comprador;
import models.Galeria;
import models.Pieza;
import models.Propietario;
import enums.EstadoPieza;

class AdminFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtPiezaID, txtLimiteCompras, txtCompradorID;
    private JLabel lblPiezaID, lblLimiteCompras, lblCompradorID;
    private JTextField txtNombre, txtAutor, txtAnio, txtLugarCreacion;
    private JLabel lblNombre, lblTipo, lblAutor, lblAnio, lblLugarCreacion;
    private JRadioButton rbPintura, rbVideo, rbEscultura, rbFotografia, rbImpresion;
    private ButtonGroup bgTipo;
    private JPanel panelEspecial;
    private JButton btnRegistrarPieza, btnConfirmarVenta, btnDevolverPieza, btnEliminarPieza, btnVerificarComprador, btnLimitarCompras, btnLogout;
    private Administrador administrador;
    private JTextArea detallesTextArea;
    private Inventario inventario;
    private Galeria galeria;

    public AdminFrame(JFrame parentFrame, Galeria galeria) {
    	       this.galeria = galeria;
        setTitle("Admin Dashboard");
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblPiezaID = new JLabel("ID de la Pieza:");
        txtPiezaID = new JTextField();
        lblCompradorID = new JLabel("ID del Comprador:");
        txtCompradorID = new JTextField();
        lblLimiteCompras = new JLabel("Límite de Compras:");
        txtLimiteCompras = new JTextField();
        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        lblTipo = new JLabel("Tipo:");
        lblAutor = new JLabel("Autor:");
        txtAutor = new JTextField();
        lblAnio = new JLabel("Año:");
        txtAnio = new JTextField();
        lblLugarCreacion = new JLabel("Lugar de Creación:");
        txtLugarCreacion = new JTextField();

        // Create radio buttons for "tipo"
        rbPintura = new JRadioButton("Pintura");
        rbVideo = new JRadioButton("Video");
        rbEscultura = new JRadioButton("Escultura");
        rbFotografia = new JRadioButton("Fotografía");
        rbImpresion = new JRadioButton("Impresión");

        // Group the radio buttons
        bgTipo = new ButtonGroup();
        bgTipo.add(rbPintura);
        bgTipo.add(rbVideo);
        bgTipo.add(rbEscultura);
        bgTipo.add(rbFotografia);
        bgTipo.add(rbImpresion);

        // Add action listeners
        rbPintura.addActionListener(e -> mostrarParametrosEspeciales("Pintura"));
        rbVideo.addActionListener(e -> mostrarParametrosEspeciales("Video"));
        rbEscultura.addActionListener(e -> mostrarParametrosEspeciales("Escultura"));
        rbFotografia.addActionListener(e -> mostrarParametrosEspeciales("Fotografía"));
        rbImpresion.addActionListener(e -> mostrarParametrosEspeciales("Impresión"));

        btnRegistrarPieza = new JButton("Registrar Pieza");
        btnRegistrarPieza.addActionListener(this::registrarPieza);
        btnConfirmarVenta = new JButton("Confirmar Venta");
        btnConfirmarVenta.addActionListener(this::confirmarVenta);
        btnDevolverPieza = new JButton("Devolver Pieza");
        btnDevolverPieza.addActionListener(this::devolverPieza);
        btnEliminarPieza = new JButton("Eliminar Pieza");
        btnEliminarPieza.addActionListener(this::eliminarPieza);
        btnVerificarComprador = new JButton("Verificar Comprador");
        btnVerificarComprador.addActionListener(this::verificarComprador);
        btnLimitarCompras = new JButton("Limitar Compras");
        btnLimitarCompras.addActionListener(this::limitarCompras);

        btnLogout = new JButton("Log Out");
        btnLogout.addActionListener(e -> {
            parentFrame.setVisible(true);
            SwingUtilities.getWindowAncestor(this).dispose();
        });

        detallesTextArea = new JTextArea();
        detallesTextArea.setRows(10);
        detallesTextArea.setColumns(30);
        detallesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(detallesTextArea);

        // Add components to the layout
        gbc.weightx = 1;
        gbc.weighty = 1;

        // IDs panel - Top Left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblPiezaID, gbc);
        gbc.gridy = 1;
        add(txtPiezaID, gbc);
        gbc.gridy = 2;
        add(lblCompradorID, gbc);
        gbc.gridy = 3;
        add(txtCompradorID, gbc);
        gbc.gridy = 4;
        add(lblLimiteCompras, gbc);
        gbc.gridy = 5;
        add(txtLimiteCompras, gbc);
        gbc.gridwidth = 1;

        // Botones panel - Bottom Left
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(btnRegistrarPieza, gbc);
        gbc.gridy = 7;
        add(btnConfirmarVenta, gbc);
        gbc.gridy = 8;
        add(btnDevolverPieza, gbc);
        gbc.gridy = 9;
        add(btnEliminarPieza, gbc);
        gbc.gridy = 10;
        add(btnVerificarComprador, gbc);
        gbc.gridy = 11;
        add(btnLimitarCompras, gbc);

        // Parameter input panel - Right
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        add(lblNombre, gbc);
        gbc.gridy = 1;
        add(txtNombre, gbc);
        gbc.gridy = 2;
        add(lblTipo, gbc);
        gbc.gridy = 3;

        // Add radio buttons for "Tipo"
        JPanel tipoPanel = new JPanel();
        tipoPanel.add(rbPintura);
        tipoPanel.add(rbVideo);
        tipoPanel.add(rbEscultura);
        tipoPanel.add(rbFotografia);
        tipoPanel.add(rbImpresion);
        add(tipoPanel, gbc);

        gbc.gridy = 4;
        add(lblAutor, gbc);
        gbc.gridy = 5;
        add(txtAutor, gbc);
        gbc.gridy = 6;
        add(lblAnio, gbc);
        gbc.gridy = 7;
        add(txtAnio, gbc);
        gbc.gridy = 8;
        add(lblLugarCreacion, gbc);
        gbc.gridy = 9;
        add(txtLugarCreacion, gbc);

        // Detalles panel - Center
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 12;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Panel for special parameters
        panelEspecial = new JPanel();
        panelEspecial.setLayout(new BoxLayout(panelEspecial, BoxLayout.Y_AXIS));
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridheight = 11;
        add(panelEspecial, gbc);

        // Logout button - Bottom Right
        gbc.gridx = 6;
        gbc.gridy = 12;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
        add(btnLogout, gbc);
    }

    private void mostrarParametrosEspeciales(String tipo) {
        panelEspecial.removeAll();
        switch (tipo) {
            case "Video":
                panelEspecial.add(new JLabel("Necesita Electricidad:"));
                panelEspecial.add(new JTextField(10));
                panelEspecial.add(new JLabel("Duración:"));
                panelEspecial.add(new JTextField(10));
                panelEspecial.add(new JLabel("Tipo Video:"));
                panelEspecial.add(new JTextField(10));
                break;
            case "Pintura":
                panelEspecial.add(new JLabel("Dimensiones:"));
                panelEspecial.add(new JTextField(10));
                panelEspecial.add(new JLabel("Técnica de Pintura:"));
                panelEspecial.add(new JTextField(10));
                break;
            case "Escultura":
                panelEspecial.add(new JLabel("Peso:"));
                panelEspecial.add(new JTextField(10));
                panelEspecial.add(new JLabel("Necesita Electricidad:"));
                panelEspecial.add(new JTextField(10));
                panelEspecial.add(new JLabel("Materiales:"));
                panelEspecial.add(new JTextField(10));
                break;
            case "Fotografía":
                panelEspecial.add(new JLabel("Tipo Foto:"));
                panelEspecial.add(new JTextField(10));
                break;
            case "Impresión":
                panelEspecial.add(new JLabel("Tipo Papel:"));
                panelEspecial.add(new JTextField(10));
                panelEspecial.add(new JLabel("Tipo Impresión:"));
                panelEspecial.add(new JTextField(10));
                break;
        }
        panelEspecial.revalidate();
        panelEspecial.repaint();
    }

    private void registrarPieza(ActionEvent e) {
        try {
            int idPieza = Integer.parseInt(txtPiezaID.getText());
            String nombre = txtNombre.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            String lugarCreacion = txtLugarCreacion.getText();
            String autor = txtAutor.getText();
            String tipo = getSelectedTipo();
            if(tipo == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un tipo de pieza.");
                return;
            }

            Pieza pieza = new Pieza(
                    idPieza, nombre, tipo, false, autor, anio, lugarCreacion,
                    new Date(System.currentTimeMillis()), EstadoPieza.BODEGA, "PropietarioN/A", anio
            );

            
            
            inventario.agregarpieza(nombre, pieza);
            JOptionPane.showMessageDialog(this, "Pieza registrada exitosamente.");
            mostrarDetalles("Pieza Registrada: " + pieza.getIdPieza() + ", " + pieza.getNombre());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos. Por favor, revise los campos.");
        }
    }

    private String getSelectedTipo() {
        if(rbPintura.isSelected()) return "Pintura";
        if(rbVideo.isSelected()) return "Video";
        if(rbEscultura.isSelected()) return "Escultura";
        if(rbFotografia.isSelected()) return "Fotografía";
        if(rbImpresion.isSelected()) return "Impresión";
        return null;
    }

    private void confirmarVenta(ActionEvent e) {
        String piezaID = txtPiezaID.getText();
        Pieza pieza = inventario.getPiezasinventario().get(piezaID);
        if (pieza != null) {
            boolean confirmed = administrador.confirmarVentaPieza(pieza);
            JOptionPane.showMessageDialog(this, "Venta " + (confirmed ? "confirmada." : "no confirmada."));
            mostrarDetalles("Pieza de ID " + piezaID + ": Venta " + (confirmed ? "confirmada." : "no confirmada."));
        } else {
            JOptionPane.showMessageDialog(this, "Pieza no encontrada.");
            mostrarDetalles("Pieza de ID " + piezaID + " no encontrada.");
        }
    }

    private void devolverPieza(ActionEvent e) {
        String piezaID = txtPiezaID.getText();
        String compradorID = txtCompradorID.getText();
        Pieza pieza = inventario.getPiezasinventario().get(piezaID);
        Propietario propetario = galeria.getMapaCompradores().get(compradorID);
        if (pieza != null && propetario != null) {
            administrador.devolverPieza(pieza, propetario);
            JOptionPane.showMessageDialog(this, "Pieza devuelta exitosamente.");
            mostrarDetalles("Pieza de ID " + piezaID + " devuelta al propietario.");
        } else {
            JOptionPane.showMessageDialog(this, "Pieza o Comprador no encontrado.");
            mostrarDetalles("Pieza de ID " + piezaID + " o comprador no encontrado.");
        }
    }

    private void eliminarPieza(ActionEvent e) {
        String piezaID = txtPiezaID.getText();
        Pieza pieza = inventario.getPiezasinventario().get(piezaID);
        if (pieza != null) {
            administrador.elimarPieza(pieza);
            JOptionPane.showMessageDialog(this, "Pieza eliminada exitosamente.");
            mostrarDetalles("Pieza de ID " + piezaID + " eliminada.");
        } else {
            JOptionPane.showMessageDialog(this, "Pieza no encontrada.");
            mostrarDetalles("Pieza de ID " + piezaID + " no encontrada.");
        }
    }

    private void verificarComprador(ActionEvent e) {
        String compradorID = txtCompradorID.getText();
        Comprador comprador = galeria.getMapaCompradores().get(compradorID);
        if (comprador != null) {
            administrador.verificarComprador(comprador);
            JOptionPane.showMessageDialog(this, "Comprador verificado exitosamente.");
            mostrarDetalles("Comprador de ID " + compradorID + " verificado.");
        } else {
            JOptionPane.showMessageDialog(this, "Comprador no encontrado.");
            mostrarDetalles("Comprador de ID " + compradorID + " no encontrado.");
        }
    }

    private void limitarCompras(ActionEvent e) {
        String compradorID = txtCompradorID.getText();
        int limite;
        try {
            limite = Integer.parseInt(txtLimiteCompras.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para el límite de compras.");
            return;
        }
        Comprador comprador = galeria.getMapaCompradores().get(compradorID);
        if (comprador != null) {
            administrador.setLimiteCompra(comprador, limite);
            JOptionPane.showMessageDialog(this, "Límite de compras actualizado exitosamente.");
            mostrarDetalles("Límite de compras del comprador de ID " + compradorID + " actualizado a " + limite + ".");
        } else {
            JOptionPane.showMessageDialog(this, "Comprador no encontrado.");
            mostrarDetalles("Comprador de ID " + compradorID + " no encontrado.");
        }
    }

    private void mostrarDetalles(String detalles) {
        detallesTextArea.append(detalles + "\n" );
    }
}