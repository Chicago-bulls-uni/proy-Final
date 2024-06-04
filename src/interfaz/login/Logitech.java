package interfaz.login;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import basedatos.Administrador;
import basedatos.RegistroUser;
import models.Cajero;
import models.Comprador;
import models.Galeria;
import models.Operador;
import seguridad.GestorUsuarios;
import seguridad.Usuario;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Logitech extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private static final String LoginButton= "B1";
    private static final String RegisterButton= "B2";
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private GestorUsuarios gestorUsuarios;
    private Galeria galeria;
    private RegistroUser registroUser;

    /**
     * Launch the application.
     */
    

    public Logitech(Galeria galeria) {
        // Create the frame
        gestorUsuarios = new GestorUsuarios();
        this.galeria = galeria;
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 723, 610);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setForeground(new Color(128, 255, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(0, 0, 373, 573);
        contentPane.add(panel);
        panel.setLayout(null);

        // Principal Logo
        lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon("./src/images/History_Price.jpg"));
        lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel_2.setBounds(-109, -15, 526, 288);
        panel.add(lblNewLabel_2);

        // Register button 
        registerButton = new JButton("Register");
        registerButton.setForeground(new Color(0, 0, 255));
        registerButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        registerButton.setBackground(Color.DARK_GRAY);
        registerButton.setBounds(100, 300, 186, 34); 
        registerButton.setActionCommand(RegisterButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterWindow();
            }
        });
        Border bottomBorder1 = new MatteBorder(0, 0, 0, 0, Color.BLACK);
        registerButton.setBorder(bottomBorder1);
        panel.add(registerButton);

        // Username label
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNewLabel.setBounds(383, 144, 129, 34);
        contentPane.add(lblNewLabel);

        usernameField = new JTextField();
        usernameField.setBounds(383, 188, 274, 41);
        Border bottomBorder = new MatteBorder(0, 0, 1, 0, Color.BLACK);
        usernameField.setBorder(bottomBorder);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        // Password label
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblPassword.setBounds(383, 251, 129, 34);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(383, 311, 274, 41);
        passwordField.setBorder(bottomBorder);
        contentPane.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
        loginButton.setBackground(new Color(255, 0, 0));
        loginButton.setBounds(421, 382, 186, 34);
        loginButton.setActionCommand(LoginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comando = e.getActionCommand();
                if(LoginButton.equals(comando)) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String role = authenticate(username, password);
                    if (role != null) {
                        lblNewLabel_1.setText("Login successful");
                        lblNewLabel_1.setForeground(Color.GREEN);
                        openRoleWindow(role, username);
                    } else {
                        lblNewLabel_1.setText("Invalid username or password");
                        lblNewLabel_1.setForeground(Color.RED);
                    }
                }
            }
        });

        contentPane.add(loginButton);

        // Error message
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(408, 516, 249, 13);
        contentPane.add(lblNewLabel_1);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(374, 38, 325, 72);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        // Logo icon
        lblNewLabel_4 = new JLabel("New label");
        lblNewLabel_4.setBounds(3, 0, 76, 62);
        lblNewLabel_4.setIcon(new ImageIcon("./src/images/Gallery_2.jpg"));
        panel_1.add(lblNewLabel_4);

        // Login title
        lblNewLabel_3 = new JLabel("INICIAR SESION");
        lblNewLabel_3.setBounds(3, 49, 249, 81);
        panel_1.add(lblNewLabel_3);
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
        
        // No account label
        lblNewLabel_6 = new JLabel("No tienes cuenta?");
        lblNewLabel_6.setBounds(100, 270, 186, 34);
        panel.add(lblNewLabel_6);
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblNewLabel_6.setForeground(Color.WHITE);

        // Login button label
        lblNewLabel_5 = new JLabel("INICIAR SESION");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblNewLabel_5.setBounds(89, 13, 226, 37);
        panel_1.add(lblNewLabel_5);
    }

    private String authenticate(String username, String password) {
        Usuario usuario = gestorUsuarios.iniciarSesion(username, password);

        if (usuario instanceof Administrador) {
            return "Admin";
        } else if (usuario instanceof Cajero) {
            return "Employee";
        } else if (usuario instanceof Operador) {
            return "Operator";
        } else if (usuario instanceof Comprador) {
            return "Client";
            
        } else {
            return null;
        }
    }

    public void openRoleWindow(String role, String username) {
    	registroUser = new RegistroUser();
    	List<Object> usuario = registroUser.buscarUsuario(username);
        JFrame roleFrame;
        switch (role) {
            case "Admin":
                roleFrame = new AdminFrame(this, galeria);
                break;
            case "Employee":
                Cajero cajero = new Cajero(usuario.get(0).toString(), usuario.get(1).toString(), galeria); 
                roleFrame = new CajeroUI(galeria, cajero);
                break;
            case "Client":
                roleFrame = new ClientFrame();
                break;
                
            case "Operator":
				roleFrame = new OperadorUI(galeria);
				break;
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }
        roleFrame.setVisible(true);
        this.dispose();
    }

	public void openRegisterWindow() {
		JFrame registerFrame;
		registerFrame = new RegisterFrame(this);
		registerFrame.setVisible(true);
		this.dispose();
	}
}