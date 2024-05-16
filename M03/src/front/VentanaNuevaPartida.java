package front;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;
import bbdd.Datos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VentanaNuevaPartida extends JFrame implements ActionListener{
	private JPanel pPrincipal, pBack,pCentral;
	private JButton bBack, bCrearPartida;
	private ImageIcon fondo;
	private JLabel texto;
	private JTextField nameTexto;
	private Datos datos;
	private int id;

	
	VentanaNuevaPartida() {
		//datos = new Datos();
		
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("New Game");
		pPrincipal = new JPanel();
		pBack = new JPanel();
		pCentral = new JPanel();
		

		//para poner el fondo
		pPrincipal = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		fondo = new ImageIcon("src/front/img/Tabla.png");
		
		//boton back
		bBack = new JButton("Back");
		pBack.add(bBack);
		pPrincipal.add(pBack, BorderLayout.NORTH);
		pBack.setOpaque(false);
		
		//instanciar
		texto= new JLabel("Introduzca un nombre de partida");
		nameTexto = new JTextField(20);
		bCrearPartida = new JButton("Crear partida");
		
		//listeners
		nameTexto.addActionListener(this);
		bCrearPartida.addActionListener(this);
		bBack.addActionListener(this);
		
		//añadir al pPrincipal
		pPrincipal.add(pCentral,BorderLayout.CENTER);
		pCentral.add(texto);
		pCentral.add(nameTexto);
		pCentral.add(bCrearPartida);
		pCentral.setOpaque(false);
		
		this.add(pPrincipal);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==bCrearPartida) {
			try {
				
				String name = nameTexto.getText();
				if (name.equals("")) {
					throw new TextoEnBlanco();
					
				}else {
					System.out.println("Creando nueva partida");

					int id =datos.crearNuevaPartida(name);
					System.out.println("El ID de la nueva partida es: "+id);
					setId(id);
					dispose();
					new VentanaPartida();

				}
			} catch (TextoEnBlanco e2) {
				System.out.println("El texto está vacio");
			}
		}
		else if (e.getSource()==bBack) {
				dispose();
				new VentanaInicio();
			}
		}
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id=id;
	}
	}
		

class TextoEnBlanco extends Exception{
	public TextoEnBlanco() {
	}
}
