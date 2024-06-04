package interfaz.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import models.Propietario;
import models.Subasta;

public class realizarOferta  extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer,Subasta> PSubastas;
	private JLabel idpiece;
	private JLabel metodopay;
	private JTextField idPieza;
	private JTextField metodopago;
	private JButton realizarOferta;
	private Propietario Propietary;
	private static final  String Ofertar= "Offer";

	public realizarOferta(Map<Integer, Subasta> pSubastas, Propietario propetary) throws HeadlessException {
		
		PSubastas = pSubastas;
		Propietary=propetary;
		setLayout( new GridLayout(6, 1));
		JLabel titulo= new JLabel("REALIZAR OFERTA");
		titulo. setFont(new Font("Arial", Font.BOLD, 14));
		add(titulo);
		idpiece= new JLabel("Ingrese el Id de la Subasta: ");
		add(idpiece);
		idPieza= new JTextField();
		Border bottomBorder = new MatteBorder(0, 0, 1, 0, Color.BLACK);
	    idPieza.setBorder(bottomBorder);
	    add(idPieza);
		metodopay= new JLabel("Ingrese la cantidad a ofrecer: ");
		add(metodopay);
		
		 metodopago= new JTextField();
		 metodopago.setBorder(bottomBorder);
		 add(metodopago);
		
		
	    
	   
	    realizarOferta= new JButton("Ofertar");
	    realizarOferta.setFont(new Font("Arial", Font.ITALIC, 10));
	    realizarOferta.setActionCommand(Ofertar);
	    realizarOferta.addActionListener(this);
	   
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    // Ajustes para centrar el botón
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes
        gbc.anchor = GridBagConstraints.CENTER;
        add(realizarOferta,gbc);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(Ofertar)) {
			int idpiece= Integer.parseInt(idPieza.getText());
			double valor= Double.parseDouble(metodopago.getText());
			if((idPieza.getText()!=null ||idPieza.getText()!="")&&(metodopago.getText()!=null ||metodopago.getText()!="") ) {
				
				if(PSubastas.containsKey(idpiece)) {
					try {
						int b =Propietary.realizarOferta(idpiece, valor, "credito");
						if(b==0) {
							JOptionPane.showMessageDialog(null, " no se puede realizar la Oferta Negativa", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(b==1) {
							JOptionPane.showMessageDialog(null, " La oferta es mas baja que el valor minimo", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						else if (b==2) {JOptionPane.showMessageDialog(null, "El usuario aun no esta Verificado", "Usuario", JOptionPane.INFORMATION_MESSAGE);
						}else if(b==3) {
							JOptionPane.showMessageDialog(null, " Oferta exitosa", "OfertaAceptada", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					;
				}
				else {
					JOptionPane.showMessageDialog(null, " no se pudo realizar la Oferta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
			else {
				JOptionPane.showMessageDialog(null, " Complete las casillas", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		
	}
	

}
