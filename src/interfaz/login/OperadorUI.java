package interfaz.login;

import javax.swing.*;

import models.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class OperadorUI extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Galeria galeria;
    private JLabel bienvenidoLabel;

    public OperadorUI(Galeria galeria) {
        this.galeria = galeria;

        setTitle("Operador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Panel superior
        JPanel panelSuperior = new JPanel(new GridBagLayout()); 
        panelSuperior.setBackground(Color.LIGHT_GRAY);

        JLabel tituloLabel = new JLabel("Galeria Casatina");
        tituloLabel.setFont(new Font("Vivaldi", Font.PLAIN, 20)); 

        bienvenidoLabel = new JLabel("Bienvenido Operador");
        bienvenidoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1; 
        gbc.anchor = GridBagConstraints.CENTER; 
        panelSuperior.add(tituloLabel, gbc);

        gbc.gridy = 1;
        panelSuperior.add(bienvenidoLabel, gbc);

        // Panel central
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentral.setBackground(Color.WHITE);
        JButton verOfertasButton = new JButton("Ver Ofertas Transitorias");
        verOfertasButton.setFont(new Font("Arial", Font.PLAIN, 14));
        verOfertasButton.setBackground(new Color(255, 204, 153)); 
        verOfertasButton.addActionListener(this);
        panelCentral.add(verOfertasButton); 
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ver Ofertas Transitorias")) {
            OfertasPanel ofertasPanel = new OfertasPanel(galeria);
            ofertasPanel.setVisible(true);
        }
    }

 
    private class OfertasPanel extends JFrame {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextArea ofertasTextArea;
        private JButton registrarOfertaButton; 
        private Oferta ofertaSeleccionada; 

        public OfertasPanel(Galeria galeria) {
            setTitle("Panel de Ofertas Transitorias");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(600, 400));
            JPanel panelCentral = new JPanel(new BorderLayout());
            panelCentral.setBackground(Color.WHITE);
            JPanel panelOfertas = new JPanel(new BorderLayout());

            ofertasTextArea = new JTextArea();
            ofertasTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(ofertasTextArea);
            panelOfertas.add(scrollPane);
            JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel seleccionLabel = new JLabel("Seleccionar oferta por ID:");
            JTextField idOfertaTextField = new JTextField(10); 
            JButton seleccionarButton = new JButton("Seleccionar");
            seleccionarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String idOferta = idOfertaTextField.getText(); 
                    
                    for (Oferta oferta : galeria.getHistorialofertasTransitorias()) {
                        if (String.valueOf(oferta.getSubasta()).equals(idOferta)) {
                            ofertaSeleccionada = oferta;
                            registrarOfertaButton.setEnabled(true); 
                            JOptionPane.showMessageDialog(OfertasPanel.this, "Ha seleccionado la oferta con ID: " + oferta.getSubasta());
                            return; 
                        }
                    }
                   
                    JOptionPane.showMessageDialog(OfertasPanel.this, "No se encontró ninguna oferta con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panelSeleccion.add(seleccionLabel);
            panelSeleccion.add(idOfertaTextField);
            panelSeleccion.add(seleccionarButton);
            panelOfertas.add(panelSeleccion, BorderLayout.NORTH);

            // Botón de registrar oferta
            registrarOfertaButton = new JButton("Registrar Oferta");
            registrarOfertaButton.setFont(new Font("Arial", Font.PLAIN, 14));
            registrarOfertaButton.setBackground(new Color(255, 204, 153)); 
            registrarOfertaButton.setEnabled(false); 
            registrarOfertaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarOferta(OfertasPanel.this, ofertaSeleccionada);
                }
            });
            panelOfertas.add(registrarOfertaButton, BorderLayout.SOUTH);

            panelCentral.add(panelOfertas, BorderLayout.CENTER);

            add(panelCentral, BorderLayout.CENTER);

            Queue<Oferta> ofertasTransitorias = galeria.getHistorialofertasTransitorias();
            StringBuilder ofertasBuilder = new StringBuilder();
            for (Oferta oferta : ofertasTransitorias) {
                ofertasBuilder.append("ID de Oferta: ").append(oferta.getSubasta()).append(", Comprador: ").append(oferta.getComprador()).append(", Monto Ofrecido: ").append(oferta.getMontoOfrecido()).append("\n");
            }
            ofertasTextArea.setText(ofertasBuilder.toString());

            pack();
            setLocationRelativeTo(null);
        }
    }

    private void registrarOferta(OfertasPanel ofertasPanel, Oferta oferta) {
        
    	 galeria.getHistorialofertasTransitorias().remove(oferta);
        JOptionPane.showMessageDialog(this, "Oferta registrada exitosamente");

        ofertasPanel.dispose(); 
    }
    
}