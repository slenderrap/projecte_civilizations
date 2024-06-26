package front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import game.BuildingException;
import game.ControladorDominio;
import game.EmptyTextException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class VentanaNuevaPartida extends JFrame implements ActionListener{
	private JPanel pPrincipal, pBack, pCentral, pTexto, pStart;
	private JButton bBack, bCrearPartida;
	private ImageIcon fondo;
	private JLabel texto, lVacio;
	private JTextField nameTexto;
	private ControladorDominio datosDominio;
	private int id;
	private BufferedImage iIcono;

	
	public VentanaNuevaPartida() {

		
		datosDominio = new ControladorDominio();
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("New Game");
		
		//poner icono ventana
		try {
			iIcono = ImageIO.read(new File("src/front/img/IconGame.png"));
			this.setIconImage(iIcono);
		} catch (IOException e) {
		}
				
		
		//instanciamos paneles
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
		pBack.setLayout(new BoxLayout(pBack, BoxLayout.Y_AXIS));
		bBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBack.setForeground(Color.WHITE);
		bBack.setBackground(new Color(076,051,026));
		pBack.add(bBack);
		pPrincipal.add(pBack, BorderLayout.NORTH);
		pBack.setOpaque(false);

		texto = new JLabel("Name your Civilization");
		texto.setFont(new Font("Times New Roman", Font.BOLD, 12));
		texto.setForeground(new Color(076, 051, 026));
		nameTexto = new JTextField(20);
		bCrearPartida = new JButton("START");
		bCrearPartida.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bCrearPartida.setForeground(Color.WHITE);
		bCrearPartida.setBackground(new Color(076,051,026));
		
		//listeners
		nameTexto.addActionListener(this);
		bCrearPartida.addActionListener(this);
		bBack.addActionListener(this);
		
		//añadir al panel pTexto
		pTexto = new JPanel();
		pTexto.add(texto);
		pTexto.add(nameTexto);
		pTexto.setOpaque(false);

		//panel pStart
		pStart = new JPanel();
		pStart.add(bCrearPartida);
		
		//añadir al panel Central
		pCentral.setLayout(new GridLayout(6, 1));
		lVacio = new JLabel(" ");
		pCentral.add(lVacio);
		pCentral.add(pTexto);
		pCentral.add(pStart);
		pPrincipal.add(pCentral,BorderLayout.CENTER);
		pCentral.setOpaque(false);
		pStart.setOpaque(false);
		
		this.add(pPrincipal);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
	
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==bCrearPartida) {
			try {
				
				String name = nameTexto.getText();
				if (name.equals("")) {
					throw new EmptyTextException("Text can't be empty!");
					
				}else {

					int id =datosDominio.crearPartida(name);
					setId(id);
					dispose();
					
					new VentanaPartida(id);

				}
			} catch (EmptyTextException e2) {
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
		

