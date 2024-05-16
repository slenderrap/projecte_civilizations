package front;
import bbdd.Datos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaContinue extends JFrame implements ActionListener{
	private JPanel pPrincipal;
	private JButton bBack,bSearch,bStart;
	private JTextField casillaNombre,casillaID;
	private JLabel textoNombre,textoID; 
	private Datos datos;
	
	public VentanaContinue() {
		datos = new Datos();
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Continue");

		pPrincipal = new JPanel();	
		this.add(pPrincipal);
		bBack = new JButton("Back");
		bSearch= new JButton("search");
		bStart = new JButton("Start");
		pPrincipal.add(bBack);
		
		textoNombre = new JLabel("Nombre");
		textoID = new JLabel("ID");
		casillaNombre = new JTextField(10);
		casillaID = new JTextField(10);
		
		bBack.addActionListener(this);
		bSearch.addActionListener(this);
		bStart.addActionListener(this);
		pPrincipal.add(textoNombre);
		pPrincipal.add(casillaNombre);
		pPrincipal.add(textoID);
		pPrincipal.add(casillaID);
		pPrincipal.add(bSearch);
		pPrincipal.add(bStart);
		
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
			datos.mostrarPartidasNombre(name);
		}else if (e.getSource()==bStart) {
			try {

				String id = casillaID.getText();
				if (!id.equals("")) {
					int idNumber = Integer.parseInt(id);

					ArrayList<String> datosPartida = datos.seleccionarPartida(idNumber);
					if (datosPartida.size()!=0){
						System.out.println("empiezaria la partida");
						for (String string : datosPartida) {
							System.out.println(string);
						}
						dispose();
						new VentanaPartida();
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
			

			
		}
		
		
	}
}
class TextoEnBlanco extends Exception{
	public TextoEnBlanco() {
	}
}
