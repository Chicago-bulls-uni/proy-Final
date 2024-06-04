package interfaz.login;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

class EmployeeFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeFrame() {
        setTitle("Employee Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome, Employee!"), BorderLayout.CENTER);
    }
}