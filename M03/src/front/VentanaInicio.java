package front;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaInicio extends JFrame {
	private JPanel pInicio, pNuevaPartida, pContinuarPartida, pSalir, pVacio;
	private JButton nuevaPartida, continuarPartida, salir;

	VentanaInicio() {
		setSize(500, 500);
		setLocationRelativeTo(null); // Para que se salga centrada la ventana
		setTitle("Civilizations");

		pInicio = new JPanel(new GridLayout(5, 1));
		pNuevaPartida = new JPanel();
		pContinuarPartida = new JPanel();
		pSalir = new JPanel();
		pVacio = new JPanel();

		nuevaPartida = new JButton("Nueva Partida");
		continuarPartida = new JButton("Continuar Partida");
		salir = new JButton("Salir");

		add(pInicio);

		pInicio.add(pVacio);
		pInicio.add(pNuevaPartida);
		pNuevaPartida.add(nuevaPartida);
		pInicio.add(pContinuarPartida);
		pContinuarPartida.add(continuarPartida);
		pInicio.add(pSalir);
		pSalir.add(salir);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
