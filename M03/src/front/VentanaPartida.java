package front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VentanaPartida extends JFrame {
	private JPanel principalPanel, lateralPanel, recursosPanel, civilizationPanel, armyPanel, shopPanel, battlegroundPanel;
	private JTabbedPane tabbedPane;
	private JLabel lFood, lWood, lIron, lMana, lAttack, lDefense, lBattles; //labels para resources
	private JLabel lFarm, lSmithy, lCarpentry, lChurch, lMagicTower; //labels para buildings
	private JLabel lSwordsman, lSpearman, lCrossbow, lCannon, lArrowTower, lCatapult, lRocketLauncherTower, lMagician, lPriest; //labels para army
	private JButton nuevaPartidaButton, continuarPartidaButton, salirButton;
	private ImageIcon fondo, fondoCivilizationPanel, fondoArmyPanel;

	VentanaPartida() {
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("CIVILIZATIONS");
		

		//PANEL PRINCIPAL
		fondo = new ImageIcon("src/front/img/BackgroundTablaPergamino.png"); //añadimos imagen de fondo
		//fondo = new ImageIcon("src/front/img/MapaMesa.png"); // IGNORAR este es para hacer cosas de photoshop
		
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
		
		
		//COSAS DEL PANEL RECURSOS -------------------------------------------
		//LABELS PANEL LATERAL RECURSOS
		lFood = new JLabel("1.000.000.000");
		lWood = new JLabel("20000");
		lIron = new JLabel("30000");
		lMana = new JLabel("40000");
		lAttack = new JLabel("50000");
		lDefense = new JLabel("60000");
		lBattles = new JLabel("70000");
		
		//cambiar fuente y color
		lFood.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lWood.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lIron.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lMana.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lAttack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lDefense.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lBattles.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lFood.setForeground(new Color(076,051,026));
		lWood.setForeground(new Color(076,051,026));
		lIron.setForeground(new Color(076,051,026));
		lMana.setForeground(new Color(076,051,026));
		lAttack.setForeground(new Color(076,051,026));
		lDefense.setForeground(new Color(076,051,026));
		lBattles.setForeground(new Color(076,051,026));
		
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
		lFood.setBounds(50 + insets.left, 80 + insets.top,
		             size.width, size.height);
		size = lWood.getPreferredSize();
		lWood.setBounds(50 + insets.left, 115 + insets.top,
		             size.width, size.height);
		size = lIron.getPreferredSize();
		lIron.setBounds(50 + insets.left, 150 + insets.top,
		             size.width, size.height);
		size = lMana.getPreferredSize();
		lMana.setBounds(50 + insets.left, 185 + insets.top,
		             size.width, size.height);
		size = lAttack.getPreferredSize();
		lAttack.setBounds(50 + insets.left, 300 + insets.top,
		             size.width, size.height);
		size = lDefense.getPreferredSize();
		lDefense.setBounds(50 + insets.left, 335 + insets.top,
		             size.width, size.height);
		size = lBattles.getPreferredSize();
		lBattles.setBounds(50 + insets.left, 450 + insets.top,
		             size.width, size.height);
		// fin de panel recursos ------------------------------------------------------------------------
				
		
		
		//PANELES PARA EL TABBED PANE
		//civilizationPanel = new JPanel();
		fondoCivilizationPanel = new ImageIcon("src/front/img/BackgroundCivilization.png"); //añadimos imagen de fondo
		
		civilizationPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondoCivilizationPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};		
		
		fondoArmyPanel = new ImageIcon("src/front/img/BackgroundArmy.png"); //añadimos imagen de fondo
		
		armyPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondoArmyPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		//armyPanel = new JPanel();
		shopPanel = new JPanel();
		battlegroundPanel = new JPanel();	
		
		
		//TABBED PANEL
		tabbedPane = new JTabbedPane();
		principalPanel.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Civilization", civilizationPanel);
		tabbedPane.addTab("Army", armyPanel);
		tabbedPane.addTab("Shop", shopPanel);
		tabbedPane.addTab("Battleground", battlegroundPanel);
		
		
		
		
		//COSAS DEL PANEL CIVILIZATION -------------------------------------------
		//LABELS CIVILIZATION
		lFarm = new JLabel("1");
		lSmithy = new JLabel("2");
		lCarpentry = new JLabel("3");
		lChurch = new JLabel("4");
		lMagicTower = new JLabel("5");
		
		//cambiar fuente y color
		lFarm.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lSmithy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lCarpentry.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lChurch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lMagicTower.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lFarm.setForeground(new Color(076,051,026));
		lSmithy.setForeground(new Color(076,051,026));
		lCarpentry.setForeground(new Color(076,051,026));
		lChurch.setForeground(new Color(076,051,026));
		lMagicTower.setForeground(new Color(076,051,026));
		
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
		lFarm.setBounds(403 + insets.left, 370 + insets.top,
		             size.width, size.height);
		size = lSmithy.getPreferredSize();
		lSmithy.setBounds(613 + insets.left, 275 + insets.top,
		             size.width, size.height);
		size = lCarpentry.getPreferredSize();
		lCarpentry.setBounds(235 + insets.left, 309 + insets.top,
		             size.width, size.height);
		size = lChurch.getPreferredSize();
		lChurch.setBounds(400 + insets.left, 161 + insets.top,
		             size.width, size.height);
		size = lMagicTower.getPreferredSize();
		lMagicTower.setBounds(187 + insets.left, 111 + insets.top,
		             size.width, size.height);
		//fin de CIVILIZATION------------------------------------------------------------------------
		
		
		
		// COSAS DEL PANEL ARMY -------------------------------------------------------
		
		//LABELS ARMY
		lSwordsman = new JLabel("1");
		lSpearman = new JLabel("2");
		lCrossbow = new JLabel("3");
		lCannon = new JLabel("4");
		lArrowTower = new JLabel("5");
		lCatapult = new JLabel("6");
		lRocketLauncherTower = new JLabel("7");
		lMagician = new JLabel("8");
		lPriest = new JLabel("9");
		
		//cambiar fuente y color
		lSwordsman.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lSpearman.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCrossbow.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCannon.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lArrowTower.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCatapult.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lRocketLauncherTower.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lMagician.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lPriest.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lSwordsman.setForeground(new Color(076,051,026));
		lSpearman.setForeground(new Color(076,051,026));
		lCrossbow.setForeground(new Color(076,051,026));
		lCannon.setForeground(new Color(076,051,026));
		lArrowTower.setForeground(new Color(076,051,026));
		lCatapult.setForeground(new Color(076,051,026));
		lRocketLauncherTower.setForeground(new Color(076,051,026));
		lMagician.setForeground(new Color(076,051,026));
		lPriest.setForeground(new Color(076,051,026));
		
		//añadir al panel Civilization
		armyPanel.add(lSwordsman);
		armyPanel.add(lSpearman);
		armyPanel.add(lCrossbow);
		armyPanel.add(lCannon);
		armyPanel.add(lArrowTower);
		armyPanel.add(lCatapult);
		armyPanel.add(lRocketLauncherTower);
		armyPanel.add(lMagician);
		armyPanel.add(lPriest);
		
		//mover labels a su sitio en coordenadas
		armyPanel.setLayout(null);
		
		insets = armyPanel.getInsets();
		size = lSwordsman.getPreferredSize();
		lSwordsman.setBounds(200 + insets.left, 115 + insets.top,
		             size.width, size.height);
		size = lSpearman.getPreferredSize();
		lSpearman.setBounds(200 + insets.left, 247 + insets.top,
		             size.width, size.height);
		size = lCrossbow.getPreferredSize();
		lCrossbow.setBounds(200 + insets.left, 383 + insets.top,
		             size.width, size.height);
		size = lCannon.getPreferredSize();
		lCannon.setBounds(200 + insets.left, 512 + insets.top,
		             size.width, size.height);
		size = lArrowTower.getPreferredSize();
		lArrowTower.setBounds(442 + insets.left, 115 + insets.top,
		             size.width, size.height);
		size = lCatapult.getPreferredSize();
		lCatapult.setBounds(442 + insets.left, 247 + insets.top,
		             size.width, size.height);
		size = lRocketLauncherTower.getPreferredSize();
		lRocketLauncherTower.setBounds(442 + insets.left, 383 + insets.top,
		             size.width, size.height);
		size = lMagician.getPreferredSize();
		lMagician.setBounds(683 + insets.left, 115 + insets.top,
		             size.width, size.height);
		size = lPriest.getPreferredSize();
		lPriest.setBounds(683 + insets.left, 247 + insets.top,
		             size.width, size.height);
		// fin de ARMY------------------------------------------------------------------------
		
		
		
		
		
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
