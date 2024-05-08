package front;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VentanaPartida extends JFrame {
	private JPanel principalPanel, lateralPanel, bottomPanel, ciudadPanel, ejercitoPanel, granjaPanel, carpinteriaPanel, herreriaPanel,
			torreMagicaPanel, iglesiaPanel;
	private JTabbedPane tabbedPane;
	private JLabel lateralImageLabel;
	private JButton nuevaPartidaButton, continuarPartidaButton, salirButton;
	private ImageIcon lateralImage;

	VentanaPartida() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Civilizations");

		principalPanel = new JPanel(new BorderLayout());
		lateralPanel = new JPanel();
		bottomPanel = new JPanel();
		ciudadPanel = new JPanel();
		ejercitoPanel = new JPanel();
		granjaPanel = new JPanel();
		carpinteriaPanel = new JPanel();
		herreriaPanel = new JPanel();
		torreMagicaPanel = new JPanel();
		iglesiaPanel = new JPanel();

		tabbedPane = new JTabbedPane();

		lateralImage = new ImageIcon("C:\\Users\\9luci\\eclipse-workspace\\git\\projecte_civilizations\\M03\\src\\front\\img\\ejemplo.jpg");

		lateralImageLabel = new JLabel(lateralImage);

		nuevaPartidaButton = new JButton("Nueva Partida");
		continuarPartidaButton = new JButton("Continuar Partida");
		salirButton = new JButton("Salir");

		principalPanel.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Inicio", ciudadPanel);
		tabbedPane.addTab("Ejercito", ejercitoPanel);
		tabbedPane.addTab("Granja", granjaPanel);
		tabbedPane.addTab("Carpintería", carpinteriaPanel);
		tabbedPane.addTab("Herreria", herreriaPanel);
		tabbedPane.addTab("Torre Mágica", torreMagicaPanel);
		tabbedPane.addTab("Iglesía", iglesiaPanel);

		principalPanel.add(lateralPanel, BorderLayout.EAST);
		lateralPanel.add(lateralImageLabel);
//		lateralPanel.setBackground(Color.ORANGE);

		principalPanel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBackground(Color.BLUE);

		add(principalPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
