package front;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaContinue extends JFrame{
	private JPanel pPrincipal;
	private JButton bBack;
	
	
	
	public VentanaContinue() {
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Continue");

		pPrincipal = new JPanel();	
		this.add(pPrincipal);
		bBack = new JButton("Back");
		pPrincipal.add(bBack);
		
		
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
