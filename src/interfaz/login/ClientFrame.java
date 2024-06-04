package interfaz.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Empleado;
import models.Galeria;
import models.Propietario; 

class ClientFrame extends JFrame {
	private PanelBotonesCliente derecha;
	private JPanel content;
	private JPanel Izquierda;
	private Propietario propetary;
	private static final long serialVersionUID = 1L;
	
    public ClientFrame() {
    	
    	//Pruebas
    	List<Empleado> empleados = new ArrayList<>();
    	Galeria galeria = new Galeria(empleados, new ArrayList<>()); 
    	propetary=new Propietario("user1", "contrasena", "Pepo", "contacto", 10000.00, true, galeria, 9) ;
    	
    	
    	
    	
    	//
        setTitle("Client Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel galeriaLabel = new JLabel("Galería Casatina", SwingConstants.CENTER);
        galeriaLabel.setFont(new Font("Vivaldi", Font.BOLD, 30));
        topPanel.add(galeriaLabel);

        JLabel bienvenidoLabel = new JLabel("Bienvenido "+propetary.getNombre(), SwingConstants.CENTER);
        bienvenidoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        topPanel.add(bienvenidoLabel);
        
        topPanel.setBackground(Color.LIGHT_GRAY);
        add(topPanel,BorderLayout.NORTH);
        content= new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Color.WHITE);
        add(content, BorderLayout.CENTER);
        Izquierda= new JPanel();
        Izquierda.setLayout(new BorderLayout());
        Izquierda.setBackground(Color.WHITE);
        add(Izquierda,BorderLayout.WEST);
        derecha= new PanelBotonesCliente( content,Izquierda,propetary);
        add(derecha,BorderLayout.EAST);
        
    }
    
    
}