package interfaz.login;

import java.awt.Color; 
import models.*;
import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CajeroUI extends JFrame implements ActionListener {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton historialVentasButton;
    private JButton registrarCompraButton;
    private JButton registrarButton;
    private JPanel mainPanel;
    private JPanel rightPanel;
    private JPanel registerPanel;
    private JPanel centerPanel;
    private JTextArea resultTextArea;
    private JTextField piezaTextField;
    private JTextField metodoPagoTextField;
    private Galeria galeria;
    private Cajero cajero;
    private CardLayout cardLayout;
    private JLabel mensajeErrorLabel;
    

    public CajeroUI(Galeria galeria, Cajero cajero) {
        super("Cajero");
        this.galeria = galeria;
        this.cajero = cajero;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        Dimension buttonSize = new Dimension(200, 40);
        Dimension textFieldSize = new Dimension(20, 20); 

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        Color blueDark = new Color(255, 204, 153); 
        
        // Panel principal
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel galeriaLabel = new JLabel("Galería Casatina", SwingConstants.CENTER);
        galeriaLabel.setFont(new Font("Vivaldi", Font.BOLD, 30));
        topPanel.add(galeriaLabel);

        JLabel bienvenidoLabel = new JLabel("Bienvenido Cajero", SwingConstants.CENTER);
        bienvenidoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        topPanel.add(bienvenidoLabel);
        
        topPanel.setBackground(Color.LIGHT_GRAY);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(blueDark);

        historialVentasButton = new JButton("Ver Historial de Ventas");
        registrarCompraButton = new JButton("Registrar Compra");
        historialVentasButton.setPreferredSize(textFieldSize); 
        registrarCompraButton.setPreferredSize(textFieldSize); 
        historialVentasButton.setFont(new Font("Arial", Font.PLAIN, 14));
        registrarCompraButton.setFont(new Font("Arial", Font.PLAIN, 14));
        historialVentasButton.setBackground(new Color(255, 153, 153)); 
        registrarCompraButton.setBackground(new Color(255, 153, 153));
        
        rightPanel.add(Box.createVerticalGlue()); 
        rightPanel.add(historialVentasButton);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(registrarCompraButton);
        rightPanel.add(Box.createVerticalGlue());
        historialVentasButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registrarCompraButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Panel de registro de compra
        registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        
        piezaTextField = new JTextField();
        metodoPagoTextField = new JTextField();
        piezaTextField.setPreferredSize(buttonSize); 
        metodoPagoTextField.setPreferredSize(buttonSize);
        piezaTextField.setFont(new Font("Arial", Font.PLAIN, 20)); 
        metodoPagoTextField.setFont(new Font("Arial", Font.PLAIN, 20)); 
        registerPanel.add(new JLabel("Nombre de la Pieza:"));
        registerPanel.add(piezaTextField);
        registerPanel.add(new JLabel("Método de Pago:"));
        registerPanel.add(metodoPagoTextField);
        
        
        // está bien aspecto botón de Registrar
        registrarButton = new JButton("Registrar");
        registrarButton.setEnabled(true);
        registrarButton.setFont(new Font("Arial", Font.PLAIN, 14)); 
        registrarButton.setBackground(new Color(173, 216, 230));
        registerPanel.add(registrarButton);

        mensajeErrorLabel = new JLabel(); 
        mensajeErrorLabel.setForeground(Color.RED);
        registerPanel.add(mensajeErrorLabel);

        // Panel central
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        // Agregar componentes al mainPanel
        mainPanel.add(rightPanel, "Main");
        mainPanel.add(registerPanel, "Register");

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);

        historialVentasButton.addActionListener(this);
        registrarCompraButton.addActionListener(this);
        registrarButton.addActionListener(this);

        cardLayout.show(mainPanel, "Main");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == historialVentasButton) {
            mostrarHistorialVentas();
        } else if (e.getSource() == registrarCompraButton) {
            mostrarComprasPorRegistrar();
            cardLayout.show(mainPanel, "Register"); // Cambiar a la vista de registro
        } else if (e.getSource() == registrarButton) {
            registrarCompra();
            cardLayout.show(mainPanel, "Main");
            limpiarPanelCentral();

        }
    }
    private void limpiarPanelCentral() {
        resultTextArea.setText(""); // Limpiar el contenido del JTextArea
    }
    private void mostrarHistorialVentas() {
        List<Compra> historialVentas = cajero.getHistorialVentasDirectasCajero();
        System.out.println("Historial de ventas tamaño: " + historialVentas.size());

        resultTextArea.setText("Este es el historial de ventas registradas:\n\n"); 

        if (historialVentas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No has tenido piezas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            resultTextArea.append("No hay historial de ventas disponibles.\n");
        } else {
            for (Compra compra : historialVentas) {
                String compraInfo = "ID de Compra: " + compra.getIdCompra() + "\n" +
                                    "Nombre de la Pieza: " + compra.getPiezas().get(0).getNombre() + "\n" +
                                    "Comprador: " + compra.getComprador() + "\n" +
                                    "Fecha de la Compra: " + compra.getFecha() + "\n" +
                                    "Método de Pago: " + compra.getMetodoPago() + "\n" +
                                    "Monto Pagado: " + compra.getMonto() + "\n\n";
                resultTextArea.append(compraInfo);
            }
        }
    }

    private void mostrarComprasPorRegistrar() {
        Map<String, Compra> comprasPorRegistrar = galeria.getCompras().getMapaCompras();
        resultTextArea.setText("Compras por Registrar:\n\n");
        for (Map.Entry<String, Compra> entry : comprasPorRegistrar.entrySet()) {
            Compra compra = entry.getValue();
            String compraInfo = "Nombre de la Pieza: " + compra.getPiezas().get(0).getNombre() + "\n" +
                                "Comprador: " + compra.getComprador() + "\n" +
                                "Fecha de la Compra: " + compra.getFecha() + "\n" +
                                "Método de Pago: " + compra.getMetodoPago() + "\n" +
                                "Monto Pagado: " + compra.getMonto() + "\n\n";
            resultTextArea.append(compraInfo);
        }
    }
    
    private void registrarCompra() {
        String pieza = piezaTextField.getText();
        String metodoPago = metodoPagoTextField.getText();
        
        Map<String, Compra> comprasPorRegistrar = galeria.getCompras().getMapaCompras();
        
        if (comprasPorRegistrar.containsKey(pieza)) {
            Compra compra = comprasPorRegistrar.get(pieza);
            cajero.regitrarcompraPieza(pieza, metodoPago);
            comprasPorRegistrar.remove(pieza);
            galeria.getHistorialVentasDirectas().add(compra); 

            JOptionPane.showMessageDialog(this, "Compra registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "La pieza no se encuentra en las compras por registrar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
   
}