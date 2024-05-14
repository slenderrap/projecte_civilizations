package front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


public class VentanaInicio extends JFrame implements ActionListener{
	private JPanel pInicio, pNuevaPartida, pContinuarPartida, pCreditos, pSalir, pVacio;
	private JButton bNuevaPartida, bContinuarPartida, bCreditos, bSalir;
	private ImageIcon fondo, iNuevaPartida, iContinuarPartida, iCreditos, iSalir;


	VentanaInicio() {
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Civilizations");

		//pInicio = new JPanel(new GridLayout(5, 1));
		pNuevaPartida = new JPanel();
		pContinuarPartida = new JPanel();
		pSalir = new JPanel();
		pVacio = new JPanel();
		pCreditos = new JPanel();

		//creamos botones
		bNuevaPartida = new JButton();
		bContinuarPartida = new JButton();
		bCreditos = new JButton();
		bSalir = new JButton();
		bNuevaPartida.addActionListener(this);
		bContinuarPartida.addActionListener(this);

		//ponemos fondo en inicio
		fondo = new ImageIcon("src/front/img/Tabla.png");
		
		pInicio = new JPanel(new GridLayout(5, 1)) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		
		//hacemos los paneles NO opacos
		pNuevaPartida.setOpaque(false);
		pContinuarPartida.setOpaque(false);
		pSalir.setOpaque(false);
		pVacio.setOpaque(false);
		pCreditos.setOpaque(false);

		//insertamos cada panel en pInicio, y cada boton en su panel
		pInicio.add(pVacio); //para que no esté pegado arriba
		pInicio.add(pNuevaPartida);
		pNuevaPartida.add(bNuevaPartida);
		pInicio.add(pContinuarPartida);
		pContinuarPartida.add(bContinuarPartida);
		pInicio.add(pCreditos);
		pCreditos.add(bCreditos);
		pInicio.add(pSalir);
		pSalir.add(bSalir);
		
		//Inicializamos las imagenes para botones
		iNuevaPartida = new ImageIcon("src/front/img/NewGame.png");
		iContinuarPartida = new ImageIcon("src/front/img/Cotinue.png");
		iCreditos = new ImageIcon("src/front/img/Credits.png");
		iSalir = new ImageIcon("src/front/img/Exit.png");
		
		//Metemos cada imagen en su boton y los hacemos transparentes
		bNuevaPartida.setIcon(iNuevaPartida); 
		bNuevaPartida.setOpaque(false);
		bNuevaPartida.setContentAreaFilled(false);
		bNuevaPartida.setBorderPainted(false);
		
		bContinuarPartida.setIcon(iContinuarPartida); 
		bContinuarPartida.setOpaque(false);
		bContinuarPartida.setContentAreaFilled(false);
		bContinuarPartida.setBorderPainted(false);
		
		bCreditos.setIcon(iCreditos); 
		bCreditos.setOpaque(false);
		bCreditos.setContentAreaFilled(false);
		bCreditos.setBorderPainted(false);
		
		bSalir.setIcon(iSalir); 
		bSalir.setOpaque(false);
		bSalir.setContentAreaFilled(false);
		bSalir.setBorderPainted(false);
		
		
//		bSalir.setBackground(Color.GRAY);
//		bSalir.setForeground(Color.BLACK);
//		bSalir.setFocusPainted(false);
//		bSalir.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		this.add(pInicio);
		
		bNuevaPartida.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new VentanaNuevaPartida();
					}
					
				}
				);
		
		bContinuarPartida.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new VentanaContinue();
					}
					
				}
				);
		
		bCreditos.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new VentanaCredits();
					}
					
				}
				);
		
		bSalir.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(EXIT_ON_CLOSE);
						
					}
				});
		
		
		
		

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==nuevaPartida) {
			new VentanaNuevaPartida();
			this.dispose();
		}else if (e.getSource()==continuarPartida) {
			new VentanaPartida();
			this.dispose();
		}
	}
}
