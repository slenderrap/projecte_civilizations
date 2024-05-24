package front;
import game.ControladorDominio;
import game.TimerPersonalizado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class VentanaContinue extends JFrame implements ActionListener{
	private JPanel pPrincipal, pBack, pCentral, pNombre, pID, pTabla;
	private JButton bBack,bSearch,bStart,bDelete;
	private JTextField casillaNombre,casillaID;
	private JLabel textoNombre,textoID, lVacio1, lVacio2, lVacio3; 
	private ControladorDominio datosDominio;
	private ImageIcon fondo;
	private JTable tabla;
	private JScrollPane scrollPane;
	
	public VentanaContinue() {
		datosDominio = new ControladorDominio();
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Continue");

		pPrincipal = new JPanel();
		
		//para poner el fondo
		pPrincipal = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		fondo = new ImageIcon("src/front/img/Tabla.png");
				

		//BACK
		pBack = new JPanel();	
		bBack = new JButton("Back");
		pBack.setLayout(new BoxLayout(pBack, BoxLayout.Y_AXIS));
		
		bBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBack.setForeground(Color.WHITE);
		bBack.setBackground(new Color(076,051,026));
		pBack.add(bBack);
		pPrincipal.add(pBack, BorderLayout.NORTH);
		pBack.setOpaque(false);
		
		//CENTRAL
		pCentral = new JPanel();
		pCentral.setLayout(new GridLayout(10, 1));
		pCentral.setOpaque(false);
		
		pNombre = new JPanel();
		pTabla = new JPanel();
		pID = new JPanel();
		//pNombre.setLayout(new BoxLayout(pNombre, BoxLayout.Y_AXIS));
		//pID.setLayout(new BoxLayout(pID, BoxLayout.Y_AXIS));
		pNombre.setOpaque(false);
		pTabla.setOpaque(false);
		pID.setOpaque(false);
		
		bSearch= new JButton("search");
		bStart = new JButton("Start");
		bDelete = new JButton("Delete");

		bSearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bStart.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bSearch.setForeground(Color.WHITE);
		bStart.setForeground(Color.WHITE);
		bDelete.setForeground(Color.WHITE);
		bSearch.setBackground(new Color(076,051,026));
		bStart.setBackground(new Color(076,051,026));
		bDelete.setBackground(new Color(076,051,026));
		
		lVacio1 = new JLabel(" ");
		lVacio2 = new JLabel(" ");
		lVacio3 = new JLabel(" ");
		textoNombre = new JLabel("Name");
		textoID = new JLabel("ID");
		textoNombre.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textoNombre.setForeground(new Color(076,051,026));
		textoID.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textoID.setForeground(new Color(076,051,026));
		casillaNombre = new JTextField(10);
		casillaID = new JTextField(10);
		
		pCentral.add(lVacio1);
		pNombre.add(textoNombre);
		pNombre.add(casillaNombre);
		pNombre.add(bSearch);
		pCentral.add(pNombre);
		
		Object[][] data = {
				{"1", "Paco", "2"},
				{"2", "Pepe", "5"},
				{"3", "Macaco", "3"}
				};
		//array de String's con los títulos de las columnas
		String[] columnNames = {"ID", "Name", "Battles"};
		//se crea la Tabla
		tabla = new JTable(data, columnNames);
		tabla.setPreferredScrollableViewportSize(new Dimension(200, 3000));
		scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(new Dimension(200, 3000));
		pTabla.setPreferredSize(new Dimension(200, 3000));
		pTabla.add(scrollPane);
		
//		pTabla.add(tabla);
		pCentral.add(lVacio2);
//		pCentral.add(scrollPane);
		pCentral.add(pTabla);
		pCentral.add(lVacio3);
				
		pID.add(textoID);
		pID.add(casillaID);
		pID.add(bStart);
		pID.add(bDelete);
		pCentral.add(pID);
		
		
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		
		bBack.addActionListener(this);
		bSearch.addActionListener(this);
		bStart.addActionListener(this);
		bDelete.addActionListener(this);
		

		this.add(pPrincipal);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}


	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==bBack) {
			dispose();
			new VentanaInicio();
		}else if (e.getSource()==bSearch) {
			String name = casillaNombre.getText();
			datosDominio.mostrarPartidasNombre(name);
		}else if (e.getSource()==bStart) {
			try {

				String id = casillaID.getText();
				if (!id.equals("")) {
					int idNumber = Integer.parseInt(id);

					ArrayList<String> datosPartida = datosDominio.cargarPartida(idNumber);
					if (datosPartida.size()!=0){
						System.out.println("empiezaria la partida");
						dispose();
						
						new VentanaPartida(idNumber);
					}else {
						System.out.println("La partida no empieza");
					}
				}else {
					throw new TextoEnBlanco();
				}


			}catch (TextoEnBlanco e2) {
				
				System.err.println("Ese texto esta en blanco");
			
			
				
			} catch (NumberFormatException e3) {
				System.out.println("No has introducido un numero!");
			}catch (Exception e4) {
				e4.getStackTrace();
			}
			

			
		}else if (e.getSource()==bDelete) {
			try {

				String id = casillaID.getText();
				if (!id.equals("")) {
					int idNumber = Integer.parseInt(id);
					datosDominio.borrarPartida(idNumber);
				}else {
					throw new TextoEnBlanco();
				}


			}catch (TextoEnBlanco e2) {
				
				System.err.println("Ese texto esta en blanco");
			
			
				
			} catch (NumberFormatException e3) {
				System.out.println("No has introducido un numero!");
			}catch (Exception e4) {
				e4.getStackTrace();
			}

		
		
	}
	}
}
class TextoEnBlanco extends Exception{
	public TextoEnBlanco() {
	}
}
