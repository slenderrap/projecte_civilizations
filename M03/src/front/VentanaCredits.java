package front;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaCredits extends JFrame{
	private JPanel pPrincipal;
	private JButton bBack;
	private JLabel lOriol, lLuciano, lMar, lAny;
	
	
	public VentanaCredits() {
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Credits");

		pPrincipal = new JPanel();	
		this.add(pPrincipal);
		
		
		bBack = new JButton("Back");
		pPrincipal.add(bBack);
		
		lOriol = new JLabel("Oriol");
		lLuciano = new JLabel("Luciano");
		lMar = new JLabel("Mar");
		lAny = new JLabel("2024");
		pPrincipal.add(lOriol);
		pPrincipal.add(lLuciano);
		pPrincipal.add(lMar);
		pPrincipal.add(lAny);
		
		
		
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
