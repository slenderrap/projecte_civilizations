package front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import game.Civilization;
import game.ControladorDominio;

public class VentanaPartida extends JFrame {
	private JPanel principalPanel, lateralPanel, recursosPanel, civilizationPanel, armyPanel, shopPanel, battlegroundPanel;
	private JTabbedPane tabbedPane;
	private JLabel lFood, lWood, lIron, lMana, lAttack, lDefense, lBattles;
	private JLabel lFarm, lSmithy, lCarpentry, lChurch, lMagicTower;
	private ImageIcon fondo, fondoPanel;
	private int id;
	private Civilization civilization;
	private ControladorDominio datosDominio;
	public VentanaPartida(int id) {
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("CIVILIZATIONS");
		
		civilization = new Civilization(id);
		
		
		
	
		datosDominio = new ControladorDominio(id);
		datosDominio.iniciarPartida();
		
		
		//PANEL PRINCIPAL
		fondo = new ImageIcon("src/front/img/MapaMesa.png"); //añadimos imagen de fondo
		
		principalPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		//PANEL LATERAL
		lateralPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		principalPanel.add(lateralPanel, BorderLayout.EAST);
		lateralPanel.setPreferredSize(new Dimension(402, 50));
		
		
		//RECURSOS PANEL
		recursosPanel = new JPanel();
		recursosPanel.setLayout(null); //null para poder poner los labels en la coordenada que queramos libremente
		recursosPanel.setPreferredSize(new Dimension(270,500));	
		
		//LATERAL PANEL
		lateralPanel.setOpaque(false);
		lateralPanel.add(recursosPanel);
		recursosPanel.setOpaque(false);
		lateralPanel.setBorder(BorderFactory.createEmptyBorder(90, 60, 0, 0));
				
		
		//PANELES PARA EL TABBED PANE
		//civilizationPanel = new JPanel();
		fondoPanel = new ImageIcon("src/front/img/prueba.png"); //añadimos imagen de fondo
		
		civilizationPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondoPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};		
		armyPanel = new JPanel();
		shopPanel = new JPanel();
		battlegroundPanel = new JPanel();	
		
		
		//TABBED PANEL
		tabbedPane = new JTabbedPane();
		principalPanel.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Civilization", civilizationPanel);
		tabbedPane.addTab("Army", armyPanel);
		tabbedPane.addTab("Shop", shopPanel);
		tabbedPane.addTab("Battleground", battlegroundPanel);
		
		
		
		//COSAS DEL PANEL RECURSOS -------------------------------------------
		//LABELS PANEL LATERAL RECURSOS
		lFood = new JLabel("1.000.000.000");
		lWood = new JLabel("20000");
		lIron = new JLabel("30000");
		lMana = new JLabel("40000");
		lAttack = new JLabel("50000");
		lDefense = new JLabel("60000");
		lBattles = new JLabel("70000");
		
		//cambiar fuente
		lFood.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lWood.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lIron.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lMana.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lAttack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lDefense.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lBattles.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		//añadir labels al panel recursos
		recursosPanel.add(lFood);
		recursosPanel.add(lWood);
		recursosPanel.add(lIron);
		recursosPanel.add(lMana);
		recursosPanel.add(lAttack);
		recursosPanel.add(lDefense);
		recursosPanel.add(lBattles);
		
		//mover labels a su sitio en coordenadas
		Insets insets = recursosPanel.getInsets();
		Dimension size = lFood.getPreferredSize();
		lFood.setBounds(45 + insets.left, 80 + insets.top,
		             size.width, size.height);
		size = lWood.getPreferredSize();
		lWood.setBounds(45 + insets.left, 115 + insets.top,
		             size.width, size.height);
		size = lIron.getPreferredSize();
		lIron.setBounds(45 + insets.left, 150 + insets.top,
		             size.width, size.height);
		size = lMana.getPreferredSize();
		lMana.setBounds(45 + insets.left, 185 + insets.top,
		             size.width, size.height);
		size = lAttack.getPreferredSize();
		lAttack.setBounds(45 + insets.left, 310 + insets.top,
		             size.width, size.height);
		size = lDefense.getPreferredSize();
		lDefense.setBounds(45 + insets.left, 345 + insets.top,
		             size.width, size.height);
		size = lBattles.getPreferredSize();
		lBattles.setBounds(45 + insets.left, 450 + insets.top,
		             size.width, size.height);
		//------------------------------------------------------------------------
		
		
		
		//COSAS DEL PANEL CIVILIZATION -------------------------------------------
		//LABELS CIVILIZATION
		lFarm = new JLabel("1");
		lSmithy = new JLabel("2");
		lCarpentry = new JLabel("3");
		lChurch = new JLabel("4");
		lMagicTower = new JLabel("5");
		
		//cambiar fuente
		lFarm.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lSmithy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lCarpentry.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lChurch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lMagicTower.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		//añadir al panel Civilization
		civilizationPanel.add(lFarm);
		civilizationPanel.add(lSmithy);
		civilizationPanel.add(lCarpentry);
		civilizationPanel.add(lChurch);
		civilizationPanel.add(lMagicTower);
		
		//mover labels a su sitio en coordenadas
		civilizationPanel.setLayout(null);
		
		insets = civilizationPanel.getInsets();
		size = lFarm.getPreferredSize();
		lFarm.setBounds(403 + insets.left, 380 + insets.top,
		             size.width, size.height);
		size = lSmithy.getPreferredSize();
		lSmithy.setBounds(618 + insets.left, 285 + insets.top,
		             size.width, size.height);
		size = lCarpentry.getPreferredSize();
		lCarpentry.setBounds(233 + insets.left, 317 + insets.top,
		             size.width, size.height);
		size = lChurch.getPreferredSize();
		lChurch.setBounds(402 + insets.left, 170 + insets.top,
		             size.width, size.height);
		size = lMagicTower.getPreferredSize();
		lMagicTower.setBounds(185 + insets.left, 118 + insets.top,
		             size.width, size.height);
		//------------------------------------------------------------------------
		
		
		
		
		
		
		//todo en opaco falso porque no sé qué está fallando, por qué no se ve el fondo en tabbed panel
//		tabbedPane.setOpaque(false);
//		civilizationPanel.setOpaque(false);
//		armyPanel.setOpaque(false);
//		shopPanel.setOpaque(false);
//		battlegroundPanel.setOpaque(false);	
//		
//		lateralPanel.setOpaque(false);
//		principalPanel.setOpaque(false);
		
		//si lo pongo en invisible sí se ve el fondo:
//		tabbedPane.setVisible(false);
//		civilizationPanel.setVisible(false);
//		armyPanel.setVisible(false);
//		shopPanel.setVisible(false);
//		battlegroundPanel.setVisible(false);	
		
		

		this.add(principalPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
	}
	
	
}
