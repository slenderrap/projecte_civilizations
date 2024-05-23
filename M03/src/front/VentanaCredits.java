package front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaCredits extends JFrame{
	private JPanel pPrincipal, pBack, pContenedor, pTextos;
	private JButton bBack;
	private JLabel lOriol, lLuciano, lMar, lAny, lVacio1, lVacio2;
	private ImageIcon fondo;
	
	public VentanaCredits() {
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Credits");

		
		//ponemos fondo en inicio
		fondo = new ImageIcon("src/front/img/Tabla.png");
		
		pPrincipal = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		//panel para boton Back y boton Back
		pBack = new JPanel();
		pBack.setLayout(new BoxLayout(pBack, BoxLayout.Y_AXIS));
		bBack = new JButton("Back");
		bBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBack.setForeground(Color.WHITE);
		bBack.setBackground(new Color(076,051,026));
		pBack.add(bBack);
		pPrincipal.add(pBack, BorderLayout.NORTH);
		
		
		//Panel para el texto
		pContenedor = new JPanel();	
		pContenedor.setLayout(new FlowLayout());
		pTextos = new JPanel();	
		pTextos.setLayout(new BoxLayout(pTextos, BoxLayout.Y_AXIS));
		lOriol = new JLabel("Oriol Arribas");
		lLuciano = new JLabel("Luciano Poyanco");
		lMar = new JLabel("Mar Mèlich");
		lAny = new JLabel("2024");
		lVacio1 = new JLabel(" ");
		lVacio2 = new JLabel(" ");

		lOriol.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lLuciano.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lMar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lAny.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lOriol.setForeground(new Color(076,051,026));
		lLuciano.setForeground(new Color(076,051,026));
		lMar.setForeground(new Color(076,051,026));
		lAny.setForeground(new Color(076,051,026));
		
		pTextos.add(lVacio1);
		pTextos.add(lVacio2);
		pTextos.add(lOriol);
		pTextos.add(lLuciano);
		pTextos.add(lMar);
		pTextos.add(lAny);
		pContenedor.add(pTextos, CENTER_ALIGNMENT);
		pPrincipal.add(pContenedor, BorderLayout.CENTER);
		
		
		pBack.setOpaque(false);
		pContenedor.setOpaque(false);
		pTextos.setOpaque(false);
		
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
