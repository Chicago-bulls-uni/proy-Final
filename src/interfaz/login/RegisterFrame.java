package interfaz.login;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import seguridad.GestorUsuarios;

public class RegisterFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nombreUsuarioField;
    private JPasswordField contrasenaField;
    private JTextField nivelUsuarioField;
    private JButton crearUsuarioButton;
    private JButton volverButton;
    private GestorUsuarios gestorUsuarios;
    private JFrame parentFrame;
    
	public RegisterFrame(JFrame parentFrame) {
		super("Registro de Usuario");
		
		this.parentFrame = parentFrame;
		
		// Configuración de la ventana
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Configuración del layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setBackground(Color.WHITE);

        gestorUsuarios = new GestorUsuarios();
        
        
        //Boton
        Border bottomBorder = new MatteBorder(0, 0, 1, 0, Color.BLACK);
        

        // Nombre de usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre de Usuario:"), gbc);
        nombreUsuarioField = new JTextField();
        nombreUsuarioField.setBorder(bottomBorder);
        gbc.gridx = 1;
        add(nombreUsuarioField, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Contraseña:"), gbc);
        contrasenaField = new JPasswordField();
        contrasenaField.setBorder(bottomBorder);
        gbc.gridx = 1;
        add(contrasenaField, gbc);

        // Nivel de usuario
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Nivel de Usuario:"), gbc);
        nivelUsuarioField = new JTextField();
        nivelUsuarioField.setBorder(bottomBorder);
        gbc.gridx = 1;
        add(nivelUsuarioField, gbc);

        // Botón para crear usuario
        crearUsuarioButton = new JButton("Crear Usuario");
        crearUsuarioButton.addActionListener(this::crearUsuario);
        crearUsuarioButton.setBackground(Color.GREEN);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(crearUsuarioButton, gbc);
        
        // Botón para regresar al frame original
        volverButton = new JButton("Volver");
        volverButton.addActionListener(this::volver);
        volverButton.setBackground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(volverButton, gbc);
	}
	
	public void crearUsuario(ActionEvent e) {
        String nombreUsuario = nombreUsuarioField.getText();
        String contrasena = new String(contrasenaField.getPassword());
        String nivelTexto = nivelUsuarioField.getText();
        
        if (nombreUsuario.isEmpty() || contrasena.isEmpty() || nivelTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nivel;
        try {
            nivel = Integer.parseInt(nivelTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El nivel de usuario debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        gestorUsuarios.crearUsuario(nombreUsuario, contrasena, nivel);
        JOptionPane.showMessageDialog(this, "Usuario creado correctamente.");
        limpiarCampos();
    }

	public void limpiarCampos() {
        nombreUsuarioField.setText("");
        contrasenaField.setText("");
        nivelUsuarioField.setText("");
    }

	public void volver(ActionEvent e) {
        this.setVisible(false);
        parentFrame.setVisible(true);
    }
}
