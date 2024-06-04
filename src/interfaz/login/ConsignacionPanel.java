package interfaz.login;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.sql.Date;
import models.Propietario;

public class ConsignacionPanel extends JPanel implements ActionListener, ItemListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel idpiece;
    private JLabel FechaMaxima;
    private JLabel monto;
    private JRadioButton rbtnVender;
    private JRadioButton rbtnSubastar;
    private JRadioButton rbtnExhibir;
    private ButtonGroup opcionesGroup;
    private JTextField idPieza;
    private JTextField fecha;
    private JTextField precio;
    private JButton CrearSolicitud;
    private Propietario Propietary;
    private static final String Continuar = "Consignacion";
    private static final String ponerprecio = "Price";

    public ConsignacionPanel(Propietario propetary) throws HeadlessException {

        Propietary = propetary;
        //dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        String pattern = "yyyy-MM-dd";
        new SimpleDateFormat(pattern);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("REALIZAR CONSIGNACION");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(titulo, gbc);

        gbc.gridy++;
        idpiece = new JLabel("Que quiere hacer: ");
        add(idpiece, gbc);

        rbtnVender = new JRadioButton("Vender");
        rbtnVender.setActionCommand(ponerprecio);
        rbtnVender.addItemListener(this);
        rbtnSubastar = new JRadioButton("Subastar");
        rbtnSubastar.setActionCommand(ponerprecio);
        rbtnSubastar.addItemListener(this);
        rbtnExhibir = new JRadioButton("Exhibir");
        rbtnExhibir.addItemListener(this);
        opcionesGroup = new ButtonGroup();
        opcionesGroup.add(rbtnVender);
        opcionesGroup.add(rbtnSubastar);
        opcionesGroup.add(rbtnExhibir);

        gbc.gridy++;
        add(rbtnVender, gbc);
        gbc.gridy++;
        add(rbtnSubastar, gbc);
        gbc.gridy++;
        add(rbtnExhibir, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel pieza = new JLabel("ESCRIBA la(s) pieza con(,)");
        add(pieza, gbc);

        gbc.gridy++;
        idPieza = new JTextField();
        idPieza.setColumns(10);
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        idPieza.setBorder(bottomBorder);
        add(idPieza, gbc);

        gbc.gridy++;
        FechaMaxima = new JLabel("Ingrese la fecha limite: ");
        add(FechaMaxima, gbc);

        gbc.gridy++;
        fecha = new JTextField();
        fecha.setBorder(bottomBorder);
        fecha.setColumns(10);
        add(fecha, gbc);

        gbc.gridy++;
        monto = new JLabel("Ingrese el precio: ");
        monto.setVisible(false);
        add(monto, gbc);

        gbc.gridy++;
        precio = new JTextField();
        precio.setBorder(bottomBorder);
        precio.setColumns(10);
        precio.setVisible(false);
        add(precio, gbc);

        gbc.gridy++;
        CrearSolicitud = new JButton("Continuar");
        CrearSolicitud.setFont(new Font("Arial", Font.ITALIC, 10));
        CrearSolicitud.setActionCommand(Continuar);
        CrearSolicitud.addActionListener(this);
        add(CrearSolicitud, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        String accion = getAccion();
        
        String selectedOption = opcionesGroup.getSelection().getActionCommand();
        if (selectedOption.equals(ponerprecio)) {
            precio.setVisible(true);
            monto.setVisible(true);
            

        }  if (comando.equals(Continuar)) {
        	System.out.println(comando);
            if (getAccion() != "EXHIBIDA") {
            	
                String idpiecez = idPieza.getText();
                Date fechafinal = null;
                
                System.out.println(FechaMaxima.getText());
                fechafinal =  Date.valueOf(fecha.getText());

                Propietary.Consignacion(idpiecez, fechafinal, accion, 0);
                
            } else if (getAccion() == "EXHIBIDA") {
                String idpiecez = idPieza.getText();
                Date fechafinal = null;
                fechafinal = Date.valueOf(fecha.getText());
                
                double prcio = Double.parseDouble(precio.getText());
                Propietary.Consignacion(idpiecez, fechafinal, accion, prcio);
            }}}
			
			
		
		public String getAccion()
		{
			if (rbtnVender.isSelected())
			{
				return "ENVENTA";
			}
			else if (rbtnSubastar.isSelected())
			{
				return "ENSUBASTA";
			}
			else if (rbtnExhibir.isSelected())
			{
				return "EXHIBIDA";
			}
			return null; //Default
		}
		public void itemStateChanged(ItemEvent e) {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
		        if (e.getItemSelectable() == rbtnVender || e.getItemSelectable() == rbtnSubastar) {
		            precio.setVisible(true);
		            monto.setVisible(true);
		        } else {
		            precio.setVisible(false);
		            monto.setVisible(false);
		        }
		    }
		}
		







	

}
