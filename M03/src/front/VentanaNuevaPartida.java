package front;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaNuevaPartida extends JFrame {
	private JPanel pPrincipal, pBack;
	private JButton bBack;
	private ImageIcon fondo;
	
	
	
	public VentanaNuevaPartida() {
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("New Game");

		pPrincipal = new JPanel();	
		pBack = new JPanel();
		
		pPrincipal = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		fondo = new ImageIcon("src/front/img/Tabla.png");
		
		bBack = new JButton("Back");
		pBack.add(bBack);
		pPrincipal.add(pBack, BorderLayout.CENTER);
		pBack.setOpaque(false);
		
		this.add(pPrincipal);
		
		
		bBack.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new VentanaInicio();
					}
					
				}
				);
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
}
