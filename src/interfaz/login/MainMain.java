package interfaz.login;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;
import models.Galeria;

public class MainMain {
	
	static Galeria galeria;

	public MainMain() {
		List<Empleado> empleados = new ArrayList<>();
		galeria = new Galeria(empleados, new ArrayList<>());
		
	}
	
	
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Logitech frame = new Logitech(galeria);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
