package front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VentanaPartida extends JFrame implements Fuente {
	private JPanel principalPanel, lateralPanel, recursosPanel, bottomPanel, ciudadPanel, armyPanel, shopPanel;
	private JTabbedPane tabbedPane;
	private JLabel lateralLabel, lateralHierro, lateralMadera;
	private JButton nuevaPartidaButton, continuarPartidaButton, salirButton;
	private ImageIcon fondo;

	VentanaPartida() {
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("Civilizations");

		principalPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		lateralPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		recursosPanel = new JPanel(new GridLayout(5, 1));
		bottomPanel = new JPanel();
		ciudadPanel = new JPanel();
		armyPanel = new JPanel();
		shopPanel = new JPanel();

		tabbedPane = new JTabbedPane();

		fondo = new ImageIcon("src/front/img/mesa.jpg");

		lateralLabel = new JLabel("-------------------------------------------------------------------");
		lateralHierro = new JLabel("Hierro                                                    30");
		lateralMadera = new JLabel("Madera                                                    40");

		nuevaPartidaButton = new JButton("Nueva Partida");
		continuarPartidaButton = new JButton("Continuar Partida");
		salirButton = new JButton("Salir");

		principalPanel.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Civilization", ciudadPanel);
		tabbedPane.addTab("Army", armyPanel);
		tabbedPane.addTab("Shop", shopPanel);

		principalPanel.add(lateralPanel, BorderLayout.EAST);
		lateralPanel.setPreferredSize(new Dimension(402, 50));
		lateralPanel.setOpaque(false);
		lateralPanel.add(recursosPanel);
		recursosPanel.setOpaque(false);
		recursosPanel.add(lateralLabel);
		recursosPanel.add(lateralHierro);
		recursosPanel.add(lateralMadera);
		lateralPanel.setBorder(BorderFactory.createEmptyBorder(90, 60, 0, 0));
		lateralHierro.setFont(new Font("Times New Roman", Font.BOLD, 16));

		principalPanel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setBackground(Color.BLUE);

		add(principalPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}