package front;

import bbdd.Datos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaNuevaPartida extends JFrame implements ActionListener{
	private JPanel pPrincipal;
	private JLabel texto;
	private JTextField nameTexto;
	private JButton crearPartida;
	private Datos datos;
	VentanaNuevaPartida() {
		datos = new Datos();
		
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Nueva partida");
		pPrincipal = new JPanel();
		
		texto= new JLabel("Introduzca un nombre de partida");
		nameTexto = new JTextField(20);
		crearPartida = new JButton("Crear partida");
		nameTexto.addActionListener(this);
		crearPartida.addActionListener(this);
		
		pPrincipal.add(texto);
		pPrincipal.add(nameTexto);
		pPrincipal.add(crearPartida);
		add(pPrincipal);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		datos.maxID();
	}
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==crearPartida) {
			//datos = new Datos();
			try {
				
				String name = nameTexto.getText();
				if (name.equals("")) {
					throw new TextoEnBlanco();
					
				}else {
					System.out.println("Creando nueva partida");
					datos.crearNuevaPartida(name);
				}
			} catch (TextoEnBlanco e2) {
				System.out.println("El texto está vacio");
			}
			
		}
		
	}
}
class TextoEnBlanco extends Exception{
	public TextoEnBlanco() {
		
	}
}
