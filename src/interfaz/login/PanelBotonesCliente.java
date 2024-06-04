package interfaz.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import models.Propietario;
import models.Subasta;



public class PanelBotonesCliente extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel opciones;
	private JButton btnMostrarPiezas;
	private JButton btnHistorial;
	private JButton btnSConsignacion;
	private JButton btnSubastas;
	private JButton btnPiezasVenta ;
	private  Map<Integer,Subasta> subastas;
	private Propietario propietary;
	private JPanel contenedor;
	private JPanel Izquierda;
	
	
	public static final String BOTON_1 = "B1";
	public static final String BOTON_2 = "B2";
	public static final String BOTON_3 = "B3";
	public static final String BOTON_4 = "B4";
	public static final String BOTON_5 = "B5";
	public static final String ROJEAR = "r";
	
	public PanelBotonesCliente(JPanel Contenedor,JPanel izquierda,Propietario propetary ) {
		subastas=Propietario.getGaleria().getMapaSubastas();
		contenedor=Contenedor;
		Izquierda=izquierda;
		propietary=propetary ;
		
		
		

        // Panel para los botones en la parte derecha
        
		setLayout(new GridLayout (5,1,30,0));
		setVisible(true);
		setBorder(new TitledBorder("Opciones "));
		  
		
		opciones=new JLabel("OPCIONES: ");
		opciones.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		opciones.setForeground(Color.BLACK);
		
		btnMostrarPiezas = new JButton("Ver mis piezas");
		btnMostrarPiezas.setActionCommand(BOTON_1);
		btnMostrarPiezas.addActionListener(this);
		btnMostrarPiezas.setFocusPainted(false);
		btnMostrarPiezas.setBackground(new Color(100, 149, 237));
		btnMostrarPiezas.setForeground(Color.WHITE);
		btnMostrarPiezas.setFont(new Font("Arial", Font.BOLD, 14));
		btnMostrarPiezas.setBorder(new LineBorder(Color.WHITE, 2));
		add(btnMostrarPiezas);
		
		
		
		
		btnHistorial = new JButton("Desplegar Historial Piezas");
		btnHistorial.setActionCommand(BOTON_2);
		btnHistorial.addActionListener(this);
		btnHistorial.setFocusPainted(false);
		btnHistorial.setBackground(new Color(100, 149, 237));
		btnHistorial.setForeground(Color.WHITE);
		btnHistorial.setFont(new Font("Arial", Font.BOLD, 14));
		btnHistorial.setBorder(new LineBorder(Color.WHITE, 2));
		
		
		add(btnHistorial);
		
		
		
		btnSConsignacion = new JButton("Consignar Pieza");
		btnSConsignacion.setActionCommand(BOTON_3);
		btnSConsignacion.setFocusPainted(false);
		btnSConsignacion.setBackground(new Color(100, 149, 237));
		btnSConsignacion.setForeground(Color.WHITE);
		btnSConsignacion.setFont(new Font("Arial", Font.BOLD, 14));
		btnSConsignacion.setBorder(new LineBorder(Color.WHITE, 2));
		btnSConsignacion.addActionListener(this);
		add(btnSConsignacion);
		
		
		
		btnSubastas = new JButton("Desplegar Subastas disponibles");
		
		
		btnSubastas.setFocusPainted(false);
		btnSubastas.setBackground(new Color(100, 149, 237));
		btnSubastas.setForeground(Color.WHITE);
		btnSubastas.setFont(new Font("Arial", Font.BOLD, 14));
		btnSubastas.setBorder(new LineBorder(Color.WHITE, 2));
		btnSubastas.setActionCommand(BOTON_4);
		btnSubastas.addActionListener(this);
		add(btnSubastas);
		
		
		
		btnPiezasVenta  = new JButton("Desplegar Piezas en venta");
		btnPiezasVenta .setFocusPainted(false);
		btnPiezasVenta .setBackground(new Color(100, 149, 237));
		btnPiezasVenta .setForeground(Color.WHITE);
		btnPiezasVenta .setFont(new Font("Arial", Font.BOLD, 14));
		btnPiezasVenta .setBorder(new LineBorder(Color.WHITE, 2));
		btnPiezasVenta .setActionCommand(BOTON_5);
		btnPiezasVenta .addActionListener(this);
		add(btnPiezasVenta);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if( comando.equals(BOTON_1)) {
			PanelPiezasadquiridas piezas=new PanelPiezasadquiridas(propietary);
			cambianContenedor(piezas);
			
		}else if(comando.equals(BOTON_3)) {
			ConsignacionPanel piezas=new ConsignacionPanel(propietary);
			cambiarIzquierda(piezas);
			}
		
		else if(comando.equals(BOTON_4)) {
			System.out.println("Número de subastas: " + subastas.size());
			PanelSubastas sub= new PanelSubastas(propietary);
			cambianContenedor(sub);
			realizarOferta subi= new realizarOferta(subastas, propietary);
			cambiarIzquierda(subi);
			
		}
		
		
		
	}
	public void cambianContenedor(JPanel jpa) {
		contenedor.removeAll();
		contenedor.add(jpa, BorderLayout.CENTER);
		contenedor.revalidate();
		contenedor.repaint();
	
	}
	public  void cambiarIzquierda(JPanel jpa) {
		Izquierda.removeAll();
		Izquierda.add(jpa, BorderLayout.CENTER);
		Izquierda.revalidate();
		Izquierda.repaint();
		
		
	}
	

}
