package front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import game.Battle;
import game.BuildingException;
import game.Civilization;
import game.ControladorDominio;
import game.MilitaryUnit;
import game.ResourceException;
import game.TimerPersonalizado;
import game.Variables;
import game.attackUnities.Cannon;
import game.attackUnities.Crossbow;
import game.attackUnities.Spearman;
import game.attackUnities.Swordsman;
import game.defenseUnities.ArrowTower;
import game.defenseUnities.Catapult;
import game.defenseUnities.RocketLauncherTower;
import game.specialUnities.Magician;
import game.specialUnities.Priest;

public class VentanaPartida extends JFrame implements ActionListener, Variables, ChangeListener, MouseMotionListener {
	private JPanel principalPanel, lateralPanel, recursosPanel, civilizationPanel, armyPanel, shopPanel, battlegroundPanel;
	private JTabbedPane tabbedPane;
	private JLabel lFood, lWood, lIron, lMana, lAttack, lDefense, lBattles; // labels para resources
	private JLabel lFarm, lSmithy, lCarpentry, lChurch, lMagicTower; // labels para buildings
	private JLabel lSwordsman, lSpearman, lCrossbow, lCannon, lArrowTower, lCatapult, lRocketLauncherTower, lMagician, lPriest; // labels
																																// para army
	private JLabel lAttackFoodCost, lAttackWoodCost, lAttackIronCost, lDefenseFoodCost, lDefenseWoodCost, lDefenseIronCost; // labels shop
																															// coste de
																															// tecnologias
	private JScrollPane spBattleDevelopment, spBattleSummary;
	private JLabel lSwordsmanBattle, lSpearmanBattle, lCrossbowBattle, lCannonBattle, lArrowTowerBattle, lCatapultBattle,
			lRocketLauncherTowerBattle, lMagicianBattle, lPriestBattle, lSwordsmanEnemy, lSpearmanEnemy, lCrossbowEnemy, lCannonEnemy;
	private JTextArea taBattleDevelopment, taBattleSummary;
	private JButton bBuyFarm, bBuySmithy, bBuyCarpentry, bBuyMagicTower, bBuyChurch; // botones shop buy buildings
	private JButton bBuySwordsman, bBuySpearman, bBuyCrossbow, bBuyCannon, bBuyArrowTower, bBuyCatapult, bBuyRocketLauncher, bBuyMagician,
			bBuyPriest; // botones shop buy army
	private JButton bBuyAttack, bBuyDefense; // botones shop buy tecnologias
	private ImageIcon fondo, fondoCivilizationPanel, fondoArmyPanel, fondoShopPanel, fondoBattleground;
	private int id;
	private Civilization civilization;
	private ControladorDominio datosDominio;
	private TimerPersonalizado tPersonalizado;
	private Battle battle;
	private Timer timer;
	private BufferedImage iIcono;
	private ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[4];
	private ArrayList<String[]> datosArmy = new ArrayList<String[]>();

	public VentanaPartida(int id) {
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setTitle("CIVILIZATIONS");

		// poner icono ventana
		try {
			iIcono = ImageIO.read(new File("src/front/img/IconGame.png"));
			this.setIconImage(iIcono);
		} catch (IOException e) {
		}

		// BBDD
		civilization = new Civilization(id);
		datosDominio = new ControladorDominio(id);

		datosArmy = datosDominio.recuperarSoldadosAtaque();
		System.out.println("TimerTask started");
		tPersonalizado = new TimerPersonalizado(id);
		// running timer task as daemon thread
		timer = new Timer(true);
		timer.scheduleAtFixedRate(tPersonalizado, 0, 350);
		tPersonalizado.recursosActualizar(civilization.getFood(), civilization.getWood(), civilization.getIron(), civilization.getMana(),
				civilization.getFarm(), civilization.getCarpentry(), civilization.getSmithy(), civilization.getMagicTower());

//		for (int i=0; i<datosArmy.size();i++) {
//			if(datosArmy.get(i).length!=0) {}
//		}
		if (datosArmy.size() > 0) {
			for (int i = 0; i < datosArmy.size(); i++) {
				if (datosArmy.get(i)[0].equals("Swordsman")) {
					civilization.getArmy()[0]
							.add(new Swordsman(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				} else if (datosArmy.get(i)[0].equals("Spearman")) {
					civilization.getArmy()[1]
							.add(new Spearman(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				} else if (datosArmy.get(i)[0].equals("Crossbow")) {
					civilization.getArmy()[2]
							.add(new Crossbow(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				} else if (datosArmy.get(i)[0].equals("Cannon")) {
					civilization.getArmy()[3].add(new Cannon(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				}
			}
		}

		datosArmy = datosDominio.recuperarSoldadosDefense();
		if (datosArmy.size() > 0) {
			for (int i = 0; i < datosArmy.size(); i++) {
				if (datosArmy.get(i)[0].equals("ArrowTower")) {
					civilization.getArmy()[4]
							.add(new ArrowTower(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				} else if (datosArmy.get(i)[0].equals("Catapult")) {
					civilization.getArmy()[5]
							.add(new Catapult(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				} else if (datosArmy.get(i)[0].equals("RocketLauncherTower")) {
					civilization.getArmy()[6]
							.add(new RocketLauncherTower(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				}
			}

		}

		datosArmy = datosDominio.recuperarSoldadosSpecial();
		if (datosArmy.size() > 0) {
			for (int i = 0; i < datosArmy.size(); i++) {
				if (datosArmy.get(i)[0].equals("Magician")) {
					civilization.getArmy()[7]
							.add(new Magician(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				} else if (datosArmy.get(i)[0].equals("Priest")) {
					civilization.getArmy()[8].add(new Priest(Integer.parseInt(datosArmy.get(i)[1]), Integer.parseInt(datosArmy.get(i)[2])));
				}
			}
		}

		// Battle
		battle = new Battle();

		// PANEL PRINCIPAL
		fondo = new ImageIcon("src/front/img/BackgroundTablaPergamino.png"); // añadimos imagen de fondo
		// fondo = new ImageIcon("src/front/img/PergaminoShopVersion2.png"); // IGNORAR
		// este es para hacer cosas de photoshop

		principalPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		// PANEL LATERAL
		lateralPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		principalPanel.add(lateralPanel, BorderLayout.EAST);
		lateralPanel.setPreferredSize(new Dimension(402, 50));

		// RECURSOS PANEL
		recursosPanel = new JPanel();
		recursosPanel.setLayout(null); // null para poder poner los labels en la coordenada que queramos libremente
		recursosPanel.setPreferredSize(new Dimension(270, 500));

		// LATERAL PANEL
		lateralPanel.setOpaque(false);
		lateralPanel.add(recursosPanel);
		recursosPanel.setOpaque(false);
		lateralPanel.setBorder(BorderFactory.createEmptyBorder(90, 60, 0, 0));

		// COSAS DEL PANEL RECURSOS -------------------------------------------
		// LABELS PANEL LATERAL RECURSOS
		lFood = new JLabel(String.valueOf(civilization.getFood()));
		lWood = new JLabel(String.valueOf(civilization.getWood()));
		lIron = new JLabel(String.valueOf(civilization.getIron()));
		lMana = new JLabel(String.valueOf(civilization.getMana()));
		lAttack = new JLabel(String.valueOf(civilization.getTechnologyAttack()));
		lDefense = new JLabel(String.valueOf(civilization.getTechnologyDefense()));
		lBattles = new JLabel(String.valueOf(civilization.getBattles()));

		// cambiar fuente y color
		lFood.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lWood.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lIron.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lMana.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lAttack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lDefense.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lBattles.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lFood.setForeground(new Color(076, 051, 026));
		lWood.setForeground(new Color(076, 051, 026));
		lIron.setForeground(new Color(076, 051, 026));
		lMana.setForeground(new Color(076, 051, 026));
		lAttack.setForeground(new Color(076, 051, 026));
		lDefense.setForeground(new Color(076, 051, 026));
		lBattles.setForeground(new Color(076, 051, 026));

		// añadir labels al panel recursos
		recursosPanel.add(lFood);
		recursosPanel.add(lWood);
		recursosPanel.add(lIron);
		recursosPanel.add(lMana);
		recursosPanel.add(lAttack);
		recursosPanel.add(lDefense);
		recursosPanel.add(lBattles);

		// mover labels a su sitio en coordenadas
		Insets insets = recursosPanel.getInsets();
		Dimension size = lFood.getPreferredSize();


		lFood.setBounds(50 + insets.left, 80 + insets.top, size.width+50, size.height);
		size = lWood.getPreferredSize();
		lWood.setBounds(50 + insets.left, 115 + insets.top, size.width+50, size.height);
		size = lIron.getPreferredSize();
		lIron.setBounds(50 + insets.left, 150 + insets.top, size.width+50, size.height);
		size = lMana.getPreferredSize();
		lMana.setBounds(50 + insets.left, 185 + insets.top, size.width+50, size.height);
		size = lAttack.getPreferredSize();
		lAttack.setBounds(50 + insets.left, 300 + insets.top, size.width+50, size.height);
		size = lDefense.getPreferredSize();
		lDefense.setBounds(50 + insets.left, 335 + insets.top, size.width+50, size.height);
		size = lBattles.getPreferredSize();
		lBattles.setBounds(50 + insets.left, 450 + insets.top, size.width+50, size.height);

		// fin de panel recursos
		// ------------------------------------------------------------------------

		// PANELES PARA EL TABBED PANE
		fondoCivilizationPanel = new ImageIcon("src/front/img/BackgroundCivilization.png"); // añadimos imagen de fondo

		civilizationPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondoCivilizationPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		fondoArmyPanel = new ImageIcon("src/front/img/BackgroundArmy.png"); // añadimos imagen de fondo
		armyPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondoArmyPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		fondoShopPanel = new ImageIcon("src/front/img/BackgroundShop.png"); // añadimos imagen de fondo

		shopPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondoShopPanel.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		fondoBattleground = new ImageIcon("src/front/img/BackgroundBattleground.png");
		battlegroundPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(fondoBattleground.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		// TABBED PANEL
		tabbedPane = new JTabbedPane();
		principalPanel.add(tabbedPane, BorderLayout.CENTER);
		principalPanel.addMouseMotionListener(this);
		tabbedPane.addTab("Civilization", civilizationPanel);
		tabbedPane.addTab("Army", armyPanel);
		tabbedPane.addTab("Shop", shopPanel);
		tabbedPane.addTab("Battleground", battlegroundPanel);
		tabbedPane.addChangeListener(this);
		tabbedPane.addMouseMotionListener(this);

		// fin de cosas del TABBED PANE
		// -------------------------------------------------------

		// COSAS DEL PANEL CIVILIZATION -------------------------------------------
		// LABELS CIVILIZATION

		lFarm = new JLabel(String.valueOf(civilization.getFarm()));
		lSmithy = new JLabel(String.valueOf(civilization.getSmithy()));
		lCarpentry = new JLabel(String.valueOf(civilization.getCarpentry()));
		lChurch = new JLabel(String.valueOf(civilization.getChurch()));
		lMagicTower = new JLabel(String.valueOf(civilization.getMagicTower()));

		// cambiar fuente y color
		lFarm.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lSmithy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lCarpentry.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lChurch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lMagicTower.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lFarm.setForeground(new Color(076, 051, 026));
		lSmithy.setForeground(new Color(076, 051, 026));
		lCarpentry.setForeground(new Color(076, 051, 026));
		lChurch.setForeground(new Color(076, 051, 026));
		lMagicTower.setForeground(new Color(076, 051, 026));

		// añadir al panel Civilization
		civilizationPanel.add(lFarm);
		civilizationPanel.add(lSmithy);
		civilizationPanel.add(lCarpentry);
		civilizationPanel.add(lChurch);
		civilizationPanel.add(lMagicTower);

		// mover labels a su sitio en coordenadas
		civilizationPanel.setLayout(null);

		insets = civilizationPanel.getInsets();
		size = lFarm.getPreferredSize();
		lFarm.setBounds(398 + insets.left, 370 + insets.top, size.width+30, size.height);
		size = lSmithy.getPreferredSize();
		lSmithy.setBounds(608 + insets.left, 275 + insets.top, size.width+30, size.height);
		size = lCarpentry.getPreferredSize();
		lCarpentry.setBounds(230 + insets.left, 309 + insets.top, size.width+30, size.height);
		size = lChurch.getPreferredSize();
		lChurch.setBounds(395 + insets.left, 161 + insets.top, size.width+30, size.height);
		size = lMagicTower.getPreferredSize();
		lMagicTower.setBounds(182 + insets.left, 111 + insets.top, size.width+30, size.height);
		// fin de
		// CIVILIZATION------------------------------------------------------------------------

		// COSAS DEL PANEL ARMY -------------------------------------------------------
		// LABELS ARMY
		lSwordsman = new JLabel(String.valueOf(civilization.getArmy()[0].size()));
		lSpearman = new JLabel(String.valueOf(civilization.getArmy()[1].size()));
		lCrossbow = new JLabel(String.valueOf(civilization.getArmy()[2].size()));
		lCannon = new JLabel(String.valueOf(civilization.getArmy()[3].size()));
		lArrowTower = new JLabel(String.valueOf(civilization.getArmy()[4].size()));
		lCatapult = new JLabel(String.valueOf(civilization.getArmy()[5].size()));
		lRocketLauncherTower = new JLabel(String.valueOf(civilization.getArmy()[6].size()));
		lMagician = new JLabel(String.valueOf(civilization.getArmy()[7].size()));
		lPriest = new JLabel(String.valueOf(civilization.getArmy()[8].size()));

		// cambiar fuente y color
		lSwordsman.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lSpearman.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCrossbow.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCannon.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lArrowTower.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCatapult.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lRocketLauncherTower.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lMagician.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lPriest.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lSwordsman.setForeground(new Color(076, 051, 026));
		lSpearman.setForeground(new Color(076, 051, 026));
		lCrossbow.setForeground(new Color(076, 051, 026));
		lCannon.setForeground(new Color(076, 051, 026));
		lArrowTower.setForeground(new Color(076, 051, 026));
		lCatapult.setForeground(new Color(076, 051, 026));
		lRocketLauncherTower.setForeground(new Color(076, 051, 026));
		lMagician.setForeground(new Color(076, 051, 026));
		lPriest.setForeground(new Color(076, 051, 026));

		// añadir al panel Army
		armyPanel.add(lSwordsman);
		armyPanel.add(lSpearman);
		armyPanel.add(lCrossbow);
		armyPanel.add(lCannon);
		armyPanel.add(lArrowTower);
		armyPanel.add(lCatapult);
		armyPanel.add(lRocketLauncherTower);
		armyPanel.add(lMagician);
		armyPanel.add(lPriest);

		// mover labels a su sitio en coordenadas
		armyPanel.setLayout(null);

		insets = armyPanel.getInsets();
		size = lSwordsman.getPreferredSize();
		lSwordsman.setBounds(200 + insets.left, 115 + insets.top, size.width+30, size.height);
		size = lSpearman.getPreferredSize();
		lSpearman.setBounds(200 + insets.left, 247 + insets.top, size.width+30, size.height);
		size = lCrossbow.getPreferredSize();
		lCrossbow.setBounds(200 + insets.left, 383 + insets.top, size.width+30, size.height);
		size = lCannon.getPreferredSize();
		lCannon.setBounds(200 + insets.left, 512 + insets.top, size.width+30, size.height);
		size = lArrowTower.getPreferredSize();
		lArrowTower.setBounds(442 + insets.left, 115 + insets.top, size.width+30, size.height);
		size = lCatapult.getPreferredSize();
		lCatapult.setBounds(442 + insets.left, 247 + insets.top, size.width+30, size.height);
		size = lRocketLauncherTower.getPreferredSize();
		lRocketLauncherTower.setBounds(442 + insets.left, 383 + insets.top, size.width+30, size.height);
		size = lMagician.getPreferredSize();
		lMagician.setBounds(683 + insets.left, 115 + insets.top, size.width+30, size.height);
		size = lPriest.getPreferredSize();
		lPriest.setBounds(683 + insets.left, 247 + insets.top, size.width+30, size.height);
		// fin de
		// ARMY------------------------------------------------------------------------

		// COSAS DEL PANEL SHOP -------------------------------------------------------
		// LABELS SHOP

		// calculos e instanciado de labels
		int AFoodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST;
		int AWoodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST;
		int AIronCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;

		if (civilization.getTechnologyAttack() > 1) {
			for (int i = 0; i < civilization.getTechnologyAttack() - 1; i++) {
				AFoodCost += AFoodCost / 100 * UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST;
				AWoodCost += AWoodCost / 100 * UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST;
				AIronCost += AIronCost / 100 * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST;
			}
		}
		lAttackFoodCost = new JLabel(String.valueOf(AFoodCost));
		lAttackWoodCost = new JLabel(String.valueOf(AWoodCost));
		lAttackIronCost = new JLabel(String.valueOf(AIronCost));

		int DFoodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST;
		int DWoodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST;
		int DIronCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;

		if (civilization.getTechnologyDefense() > 1) {
			for (int i = 0; i < civilization.getTechnologyDefense() - 1; i++) {
				DFoodCost += DFoodCost / 100 * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST;
				DWoodCost += DWoodCost / 100 * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST;
				DIronCost += DIronCost / 100 * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST;
			}
		}
		lDefenseFoodCost = new JLabel(String.valueOf(DFoodCost));
		lDefenseWoodCost = new JLabel(String.valueOf(DWoodCost));
		lDefenseIronCost = new JLabel(String.valueOf(DIronCost));

		// cambiar fuente y color
		lAttackFoodCost.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lAttackWoodCost.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lAttackIronCost.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lDefenseFoodCost.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lDefenseWoodCost.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lDefenseIronCost.setFont(new Font("Times New Roman", Font.BOLD, 12));

		lAttackFoodCost.setForeground(new Color(076, 051, 026));
		lAttackWoodCost.setForeground(new Color(076, 051, 026));
		lAttackIronCost.setForeground(new Color(076, 051, 026));
		lDefenseFoodCost.setForeground(new Color(076, 051, 026));
		lDefenseWoodCost.setForeground(new Color(076, 051, 026));
		lDefenseIronCost.setForeground(new Color(076, 051, 026));

		// añadir al panel Shop
		shopPanel.add(lAttackFoodCost);
		shopPanel.add(lAttackWoodCost);
		shopPanel.add(lAttackIronCost);
		shopPanel.add(lDefenseFoodCost);
		shopPanel.add(lDefenseWoodCost);
		shopPanel.add(lDefenseIronCost);

		// mover labels a su sitio en coordenadas

		shopPanel.setLayout(null);

		insets = shopPanel.getInsets();
		size = lAttackFoodCost.getPreferredSize();
		lAttackFoodCost.setBounds(390 + insets.left, 470 + insets.top, size.width + 30, size.height);
		size = lAttackWoodCost.getPreferredSize();
		lAttackWoodCost.setBounds(390 + insets.left, 490 + insets.top, size.width + 30, size.height);
		size = lAttackIronCost.getPreferredSize();
		lAttackIronCost.setBounds(390 + insets.left, 510 + insets.top, size.width + 30, size.height);
		size = lDefenseFoodCost.getPreferredSize();
		lDefenseFoodCost.setBounds(390 + insets.left, 557 + insets.top, size.width + 30, size.height);
		size = lDefenseWoodCost.getPreferredSize();
		lDefenseWoodCost.setBounds(390 + insets.left, 577 + insets.top, size.width + 30, size.height);
		size = lDefenseIronCost.getPreferredSize();

		lDefenseIronCost.setBounds(390 + insets.left, 597 + insets.top, size.width + 30, size.height);

		// BUTTONS SHOP !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		bBuyFarm = new JButton("Buy");
		bBuyFarm.addActionListener(this);
		bBuySmithy = new JButton("Buy");
		bBuySmithy.addActionListener(this);
		bBuyCarpentry = new JButton("Buy");
		bBuyCarpentry.addActionListener(this);
		bBuyMagicTower = new JButton("Buy");
		bBuyMagicTower.addActionListener(this);
		bBuyChurch = new JButton("Buy");
		bBuyChurch.addActionListener(this);

		bBuySwordsman = new JButton("Buy");
		bBuySwordsman.addActionListener(this);
		bBuySpearman = new JButton("Buy");
		bBuySpearman.addActionListener(this);
		bBuyCrossbow = new JButton("Buy");
		bBuyCrossbow.addActionListener(this);
		bBuyCannon = new JButton("Buy");
		bBuyCannon.addActionListener(this);
		bBuyArrowTower = new JButton("Buy");
		bBuyArrowTower.addActionListener(this);
		bBuyCatapult = new JButton("Buy");
		bBuyCatapult.addActionListener(this);
		bBuyRocketLauncher = new JButton("Buy");
		bBuyRocketLauncher.addActionListener(this);
		bBuyMagician = new JButton("Buy");
		bBuyMagician.addActionListener(this);
		bBuyPriest = new JButton("Buy");
		bBuyPriest.addActionListener(this);

		bBuyAttack = new JButton("Buy");
		bBuyAttack.addActionListener(this);
		bBuyDefense = new JButton("Buy");
		bBuyDefense.addActionListener(this);

		// cambiar fuente, color, background

		bBuyFarm.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuySmithy.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyCarpentry.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyMagicTower.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyChurch.setFont(new Font("Times New Roman", Font.BOLD, 12));

		bBuySwordsman.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuySpearman.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyCrossbow.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyCannon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyArrowTower.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyCatapult.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyRocketLauncher.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyMagician.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyPriest.setFont(new Font("Times New Roman", Font.BOLD, 12));

		bBuyAttack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		bBuyDefense.setFont(new Font("Times New Roman", Font.BOLD, 12));

		bBuyFarm.setForeground(Color.WHITE);
		bBuySmithy.setForeground(Color.WHITE);
		bBuyCarpentry.setForeground(Color.WHITE);
		bBuyMagicTower.setForeground(Color.WHITE);
		bBuyChurch.setForeground(Color.WHITE);

		bBuySwordsman.setForeground(Color.WHITE);
		bBuySpearman.setForeground(Color.WHITE);
		bBuyCrossbow.setForeground(Color.WHITE);
		bBuyCannon.setForeground(Color.WHITE);
		bBuyArrowTower.setForeground(Color.WHITE);
		bBuyCatapult.setForeground(Color.WHITE);
		bBuyRocketLauncher.setForeground(Color.WHITE);
		bBuyMagician.setForeground(Color.WHITE);
		bBuyPriest.setForeground(Color.WHITE);

		bBuyAttack.setForeground(Color.WHITE);
		bBuyDefense.setForeground(Color.WHITE);

		bBuyFarm.setBackground(new Color(076, 051, 026));
		bBuySmithy.setBackground(new Color(076, 051, 026));
		bBuyCarpentry.setBackground(new Color(076, 051, 026));
		bBuyMagicTower.setBackground(new Color(076, 051, 026));
		bBuyChurch.setBackground(new Color(076, 051, 026));

		bBuySwordsman.setBackground(new Color(076, 051, 026));
		bBuySpearman.setBackground(new Color(076, 051, 026));
		bBuyCrossbow.setBackground(new Color(076, 051, 026));
		bBuyCannon.setBackground(new Color(076, 051, 026));
		bBuyArrowTower.setBackground(new Color(076, 051, 026));
		bBuyCatapult.setBackground(new Color(076, 051, 026));
		bBuyRocketLauncher.setBackground(new Color(076, 051, 026));
		bBuyMagician.setBackground(new Color(076, 051, 026));
		bBuyPriest.setBackground(new Color(076, 051, 026));

		bBuyAttack.setBackground(new Color(076, 051, 026));
		bBuyDefense.setBackground(new Color(076, 051, 026));

		// añadir al panel Shop
		shopPanel.add(bBuyFarm);
		shopPanel.add(bBuySmithy);
		shopPanel.add(bBuyCarpentry);
		shopPanel.add(bBuyMagicTower);
		shopPanel.add(bBuyChurch);

		shopPanel.add(bBuySwordsman);
		shopPanel.add(bBuySpearman);
		shopPanel.add(bBuyCrossbow);
		shopPanel.add(bBuyCannon);
		shopPanel.add(bBuyArrowTower);
		shopPanel.add(bBuyCatapult);
		shopPanel.add(bBuyRocketLauncher);
		shopPanel.add(bBuyMagician);
		shopPanel.add(bBuyPriest);

		shopPanel.add(bBuyAttack);
		shopPanel.add(bBuyDefense);

		// mover labels a su sitio en coordenadas
		insets = shopPanel.getInsets();
		size = bBuyFarm.getPreferredSize();
		bBuyFarm.setBounds(210 + insets.left, 100 + insets.top, size.width, size.height);
		size = bBuySmithy.getPreferredSize();
		bBuySmithy.setBounds(210 + insets.left, 190 + insets.top, size.width, size.height);
		size = bBuyCarpentry.getPreferredSize();
		bBuyCarpentry.setBounds(210 + insets.left, 285 + insets.top, size.width, size.height);
		size = bBuyMagicTower.getPreferredSize();
		bBuyMagicTower.setBounds(210 + insets.left, 405 + insets.top, size.width, size.height);
		size = bBuyChurch.getPreferredSize();
		bBuyChurch.setBounds(210 + insets.left, 525 + insets.top, size.width, size.height);
		// comprar soldados --------
		size = bBuySwordsman.getPreferredSize();
		bBuySwordsman.setBounds(455 + insets.left, 70 + insets.top, size.width, size.height);
		size = bBuySpearman.getPreferredSize();
		bBuySpearman.setBounds(455 + insets.left, 170 + insets.top, size.width, size.height);
		size = bBuyCrossbow.getPreferredSize();
		bBuyCrossbow.setBounds(455 + insets.left, 265 + insets.top, size.width, size.height);
		size = bBuyCannon.getPreferredSize();
		bBuyCannon.setBounds(455 + insets.left, 360 + insets.top, size.width, size.height);
		// --
		size = bBuyArrowTower.getPreferredSize();
		bBuyArrowTower.setBounds(690 + insets.left, 345 + insets.top, size.width, size.height);
		size = bBuyCatapult.getPreferredSize();
		bBuyCatapult.setBounds(690 + insets.left, 445 + insets.top, size.width, size.height);
		size = bBuyRocketLauncher.getPreferredSize();
		bBuyRocketLauncher.setBounds(690 + insets.left, 555 + insets.top, size.width, size.height);
		// --
		size = bBuyMagician.getPreferredSize();
		bBuyMagician.setBounds(690 + insets.left, 85 + insets.top, size.width, size.height);
		size = bBuyPriest.getPreferredSize();
		bBuyPriest.setBounds(690 + insets.left, 195 + insets.top, size.width, size.height);
		// comprar tecnologias --------
		size = bBuyAttack.getPreferredSize();
		bBuyAttack.setBounds(455 + insets.left, 480 + insets.top, size.width, size.height);
		size = bBuyDefense.getPreferredSize();
		bBuyDefense.setBounds(455 + insets.left, 568 + insets.top, size.width, size.height);
		// fin de SHOP
		// ------------------------------------------------------------------------

		// COSAS DEL PANEL BATTLEGROUND
		// -------------------------------------------------------

		// LABELS BATTLEGROUND
		lSwordsmanBattle = new JLabel(String.valueOf(civilization.getArmy()[0].size()));
		lSpearmanBattle = new JLabel(String.valueOf(civilization.getArmy()[1].size()));
		lCrossbowBattle = new JLabel(String.valueOf(civilization.getArmy()[2].size()));
		lCannonBattle = new JLabel(String.valueOf(civilization.getArmy()[3].size()));
		lArrowTowerBattle = new JLabel(String.valueOf(civilization.getArmy()[4].size()));
		lCatapultBattle = new JLabel(String.valueOf(civilization.getArmy()[5].size()));
		lRocketLauncherTowerBattle = new JLabel(String.valueOf(civilization.getArmy()[6].size()));
		lMagicianBattle = new JLabel(String.valueOf(civilization.getArmy()[7].size()));
		lPriestBattle = new JLabel(String.valueOf(civilization.getArmy()[8].size()));
		lSwordsmanEnemy = new JLabel("0");
		lSpearmanEnemy = new JLabel("0");
		lCrossbowEnemy = new JLabel("0");
		lCannonEnemy = new JLabel("0");
		taBattleDevelopment = new JTextArea(battle.getBattleDevelopment());
		taBattleSummary = new JTextArea(battle.getBattleSummary());

		spBattleDevelopment = new JScrollPane(taBattleDevelopment);
		spBattleSummary = new JScrollPane(taBattleSummary);

		// cambiar fuente y color
		lSwordsmanBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lSpearmanBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCrossbowBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCannonBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lArrowTowerBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lCatapultBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lRocketLauncherTowerBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lMagicianBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lPriestBattle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lSwordsmanEnemy.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lSpearmanEnemy.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lCrossbowEnemy.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lCannonEnemy.setFont(new Font("Times New Roman", Font.BOLD, 27));
		taBattleDevelopment.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		taBattleSummary.setFont(new Font("Times New Roman", Font.BOLD, 13));

		lSwordsmanBattle.setForeground(new Color(076, 051, 026));
		lSpearmanBattle.setForeground(new Color(076, 051, 026));
		lCrossbowBattle.setForeground(new Color(076, 051, 026));
		lCannonBattle.setForeground(new Color(076, 051, 026));
		lArrowTowerBattle.setForeground(new Color(076, 051, 026));
		lCatapultBattle.setForeground(new Color(076, 051, 026));
		lRocketLauncherTowerBattle.setForeground(new Color(076, 051, 026));
		lMagicianBattle.setForeground(new Color(076, 051, 026));
		lPriestBattle.setForeground(new Color(076, 051, 026));
		lSwordsmanEnemy.setForeground(new Color(076, 051, 026));
		lSpearmanEnemy.setForeground(new Color(076, 051, 026));
		lCrossbowEnemy.setForeground(new Color(076, 051, 026));
		lCannonEnemy.setForeground(new Color(076, 051, 026));
		taBattleDevelopment.setForeground(new Color(255, 255, 255));
		taBattleSummary.setForeground(new Color(255, 255, 255));

		lSwordsmanBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lSpearmanBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lCrossbowBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lCannonBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lArrowTowerBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lCatapultBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lRocketLauncherTowerBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lMagicianBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lPriestBattle.setHorizontalAlignment(SwingConstants.CENTER);
		lSwordsmanEnemy.setHorizontalAlignment(SwingConstants.CENTER);
		lSpearmanEnemy.setHorizontalAlignment(SwingConstants.CENTER);
		lCrossbowEnemy.setHorizontalAlignment(SwingConstants.CENTER);
		lCannonEnemy.setHorizontalAlignment(SwingConstants.CENTER);

		taBattleDevelopment.setLineWrap(true); // Permitir ajuste de línea automático
		taBattleDevelopment.setWrapStyleWord(true); // Ajustar por palabras
		taBattleDevelopment.setEditable(false); // Hacer el JTextArea no editable
		taBattleDevelopment.setOpaque(false); // Hacer el JTextArea transparente
		taBattleSummary.setLineWrap(true); // Permitir ajuste de línea automático
		taBattleSummary.setWrapStyleWord(true); // Ajustar por palabras
		taBattleSummary.setEditable(false); // Hacer el JTextArea no editable
		taBattleSummary.setOpaque(false); // Hacer el JTextArea transparente

		spBattleDevelopment.setPreferredSize(new Dimension(322, 275));
		spBattleDevelopment.setOpaque(false);
		spBattleDevelopment.getViewport().setOpaque(false);
		spBattleSummary.setPreferredSize(new Dimension(322, 275));
		spBattleSummary.setOpaque(false);
		spBattleSummary.getViewport().setOpaque(false);

		// añadir al panel Battleground
		battlegroundPanel.add(lSwordsmanBattle);
		battlegroundPanel.add(lSpearmanBattle);
		battlegroundPanel.add(lCrossbowBattle);
		battlegroundPanel.add(lCannonBattle);
		battlegroundPanel.add(lArrowTowerBattle);
		battlegroundPanel.add(lCatapultBattle);
		battlegroundPanel.add(lRocketLauncherTowerBattle);
		battlegroundPanel.add(lMagicianBattle);
		battlegroundPanel.add(lPriestBattle);
		battlegroundPanel.add(lSwordsmanEnemy);
		battlegroundPanel.add(lSpearmanEnemy);
		battlegroundPanel.add(lCrossbowEnemy);
		battlegroundPanel.add(lCannonEnemy);
//		battlegroundPanel.add(lBattleDevelopment);
//		battlegroundPanel.add(lBattleSummary);

		battlegroundPanel.add(spBattleDevelopment);
		battlegroundPanel.add(spBattleSummary);

		// mover labels a su sitio en coordenadas
		battlegroundPanel.setLayout(null);

		insets = battlegroundPanel.getInsets();
		size = lSwordsmanBattle.getPreferredSize();
		lSwordsmanBattle.setBounds(16 + insets.left, 138 + insets.top, size.width + 30, size.height);
		size = lSpearmanBattle.getPreferredSize();
		lSpearmanBattle.setBounds(16 + insets.left, 204 + insets.top, size.width + 30, size.height);
		size = lCrossbowBattle.getPreferredSize();
		lCrossbowBattle.setBounds(16 + insets.left, 268 + insets.top, size.width + 30, size.height);
		size = lCannonBattle.getPreferredSize();
		lCannonBattle.setBounds(74 + insets.left, 138 + insets.top, size.width + 30, size.height);
		size = lArrowTowerBattle.getPreferredSize();
		lArrowTowerBattle.setBounds(74 + insets.left, 204 + insets.top, size.width + 30, size.height);
		size = lCatapultBattle.getPreferredSize();
		lCatapultBattle.setBounds(74 + insets.left, 268 + insets.top, size.width + 30, size.height);
		size = lRocketLauncherTowerBattle.getPreferredSize();
		lRocketLauncherTowerBattle.setBounds(131 + insets.left, 138 + insets.top, size.width + 30, size.height);
		size = lMagicianBattle.getPreferredSize();
		lMagicianBattle.setBounds(131 + insets.left, 204 + insets.top, size.width + 30, size.height);
		size = lPriestBattle.getPreferredSize();
		lPriestBattle.setBounds(131 + insets.left, 268 + insets.top, size.width + 30, size.height);
		size = lSwordsmanEnemy.getPreferredSize();
		lSwordsmanEnemy.setBounds(583 + insets.left, 156 + insets.top, size.width + 30, size.height);
		size = lSpearmanEnemy.getPreferredSize();
		lSpearmanEnemy.setBounds(583 + insets.left, 257 + insets.top, size.width + 30, size.height);
		size = lCrossbowEnemy.getPreferredSize();
		lCrossbowEnemy.setBounds(671 + insets.left, 156 + insets.top, size.width + 30, size.height);
		size = lCannonEnemy.getPreferredSize();
		lCannonEnemy.setBounds(671 + insets.left, 257 + insets.top, size.width + 30, size.height);
//		size = lBattleDevelopment.getPreferredSize();
//		lBattleDevelopment.setBounds(insets.left, insets.top, size.width + 30, size.height);
//		size = lBattleSummary.getPreferredSize();
//		lBattleSummary.setBounds(683 + insets.left, 247 + insets.top, size.width + 30, size.height);
		size = spBattleDevelopment.getPreferredSize();
		spBattleDevelopment.setBounds(20 + insets.left, 339 + insets.top, size.width + 30, size.height);
		size = spBattleSummary.getPreferredSize();
		spBattleSummary.setBounds(409 + insets.left, 339 + insets.top, size.width + 30, size.height);

		// fin de BATTLEGROUND
		// ------------------------------------------------------------------------

		// todo en opaco falso porque no sé qué está fallando, por qué no se ve el fondo
		// en tabbed panel

//		tabbedPane.setOpaque(false);
//		civilizationPanel.setOpaque(false);
//		armyPanel.setOpaque(false);
//		shopPanel.setOpaque(false);
//		battlegroundPanel.setOpaque(false);	
//		
//		lateralPanel.setOpaque(false);
//		principalPanel.setOpaque(false);

		// si lo pongo en invisible sí se ve el fondo:
//		tabbedPane.setVisible(false);
//		civilizationPanel.setVisible(false);
//		armyPanel.setVisible(false);
//		shopPanel.setVisible(false);
//		battlegroundPanel.setVisible(false);	

		System.out.println(civilization.getArmy());
		this.add(principalPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		// INSTANCIAS ARMADA ENEMIGA
		for (int i = 0; i < 4; i++) {
			enemyArmy[i] = new ArrayList<MilitaryUnit>();
			System.out.println("hecho el enemy" + i);
		}

	}

	public void createEnemyArmy() {

		// LIMPIAR SI HAY ARMADA ANTERIOR
		for (int i = 0; i < 4; i++) {
			enemyArmy[i].clear();
		}
		// CALCULAR RECURSOS ACTUALES
		int enemyFood = 0;
		int enemyWood = 0;
		int enemyIron = 0;

		if (civilization.getBattles() == 0) {
			enemyFood = FOOD_BASE_ENEMY_ARMY;
			enemyWood = WOOD_BASE_ENEMY_ARMY;
			enemyIron = IRON_BASE_ENEMY_ARMY;
		}
		if (civilization.getBattles() > 0) {
			enemyFood = FOOD_BASE_ENEMY_ARMY + (FOOD_BASE_ENEMY_ARMY / 100 * ENEMY_FLEET_INCREASE * civilization.getBattles());
			enemyWood = WOOD_BASE_ENEMY_ARMY + (WOOD_BASE_ENEMY_ARMY / 100 * ENEMY_FLEET_INCREASE * civilization.getBattles());
			enemyIron = IRON_BASE_ENEMY_ARMY + (IRON_BASE_ENEMY_ARMY / 100 * ENEMY_FLEET_INCREASE * civilization.getBattles());
		}

		// CREAR TROPAS
		int arrayPorcientos[] = { 35, 25, 20, 20 };
		while (enemyFood > 8000 && enemyWood > 3000 && enemyIron > 50) { // mientras haya recursos para comprar el más barato(swordsman)

			// SE SACA EL TIPO DE UNIDAD A CREAR
			int total = 0;
			for (int i = 0; i < arrayPorcientos.length; i++) {
				total += arrayPorcientos[i];
			}
			int aleatorio = (int) (Math.random() * total);

			total = 0;
			int tipo = 0;
			for (int i = 0; i < arrayPorcientos.length; i++) {
				total += arrayPorcientos[i];
				if (total > aleatorio) {
					tipo = i;
					break;
				}
			}

			// SE CREA EL TIPO DE UNIDAD
			if (tipo == 0 && enemyFood > 8000 && enemyWood > 3000 && enemyIron > 50) {
				enemyArmy[0].add(new Swordsman());
				enemyFood -= 8000;
				enemyWood -= 3000;
				enemyIron -= 50;
				System.out.println("SE HA CREADO SWORDSMAN");
			}
			if (tipo == 1 && enemyFood > 5000 && enemyWood > 6500 && enemyIron > 50) {
				enemyArmy[1].add(new Spearman());
				enemyFood -= 5000;
				enemyWood -= 6500;
				enemyIron -= 50;
				System.out.println("SE HA CREADO SPEARMAN");
			}
			if (tipo == 2 && enemyWood > 45000 && enemyIron > 7000) {
				enemyArmy[2].add(new Crossbow());
				enemyWood -= 45000;
				enemyIron -= 7000;
				System.out.println("SE HA CREADO CROSSBOW");
			}
			if (tipo == 3 && enemyWood > 30000 && enemyIron > 15000) {
				enemyArmy[3].add(new Cannon());
				enemyWood -= 30000;
				enemyIron -= 15000;
				System.out.println("SE HA CREADO CANNON");
			}

			// FEEDBACK STATS
			System.out.println("RECURSOS QUEDAN -------");
			System.out.println("food " + enemyFood);
			System.out.println("wood " + enemyWood);
			System.out.println("iron " + enemyIron);

		}
	}

	public void actionPerformed(ActionEvent e) {

		// soldados
		if (e.getSource() == bBuySwordsman) {
			try {
				System.out.println("evento");
				civilization.newSwordsman(1);
				System.out.println("antes ddbb");
				System.out.println(civilization.getArmy()[0].size());
				datosDominio.crearSoldado(civilization.getArmy()[0].getLast());
				lSwordsman.setText(String.valueOf(civilization.getArmy()[0].size()));
				lSwordsmanBattle.setText(String.valueOf(civilization.getArmy()[0].size()));

			} catch (ResourceException e1) {
			}

		} else if (e.getSource() == bBuySpearman) {
			try {
				System.out.println("evento");
				civilization.newSpearman(1);
				datosDominio.crearSoldado(civilization.getArmy()[1].getLast());
				lSpearman.setText(String.valueOf(civilization.getArmy()[1].size()));
				lSpearmanBattle.setText(String.valueOf(civilization.getArmy()[1].size()));
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyCrossbow) {
			try {
				System.out.println("evento");
				civilization.newCrossbow(1);
				datosDominio.crearSoldado(civilization.getArmy()[2].getLast());
				lCrossbow.setText(String.valueOf(civilization.getArmy()[2].size()));
				lCrossbowBattle.setText(String.valueOf(civilization.getArmy()[2].size()));
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyCannon) {
			try {
				System.out.println("evento");
				civilization.newCannon(1);
				datosDominio.crearSoldado(civilization.getArmy()[3].getLast());
				lCannon.setText(String.valueOf(civilization.getArmy()[3].size()));
				lCannonBattle.setText(String.valueOf(civilization.getArmy()[3].size()));
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyArrowTower) {
			try {
				System.out.println("evento");
				civilization.newArrowTower(1);
				datosDominio.crearSoldado(civilization.getArmy()[4].getLast());
				lArrowTower.setText(String.valueOf(civilization.getArmy()[4].size()));
				lArrowTowerBattle.setText(String.valueOf(civilization.getArmy()[4].size()));
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyCatapult) {
			try {
				System.out.println("evento");
				civilization.newCatapult(1);
				datosDominio.crearSoldado(civilization.getArmy()[5].getLast());
				lCatapult.setText(String.valueOf(civilization.getArmy()[5].size()));
				lCatapultBattle.setText(String.valueOf(civilization.getArmy()[5].size()));
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyRocketLauncher) {
			try {
				System.out.println("evento");
				civilization.newRocketLauncherTower(1);
				datosDominio.crearSoldado(civilization.getArmy()[6].getLast());
				lRocketLauncherTower.setText(String.valueOf(civilization.getArmy()[6].size()));
				lRocketLauncherTowerBattle.setText(String.valueOf(civilization.getArmy()[6].size()));
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyMagician) {
			try {
				System.out.println("evento");
				civilization.newMagician(1);
				datosDominio.crearSoldado(civilization.getArmy()[7].getLast());
				lMagician.setText(String.valueOf(civilization.getArmy()[7].size()));
				lMagicianBattle.setText(String.valueOf(civilization.getArmy()[7].size()));
			} catch (ResourceException e1) {
			} catch (BuildingException e1) {
			}
		} else if (e.getSource() == bBuyPriest) {
			try {
				System.out.println("evento");
				civilization.newPriest(1);
				datosDominio.crearSoldado(civilization.getArmy()[8].getLast());
				lPriest.setText(String.valueOf(civilization.getArmy()[8].size()));
				lPriestBattle.setText(String.valueOf(civilization.getArmy()[8].size()));
			} catch (ResourceException e1) {
			} catch (BuildingException e1) {
			}
		}

		// edificios
		else if (e.getSource() == bBuyFarm) {
			try {
				System.out.println("evento");
				civilization.newFarm();
				lFarm.setText(String.valueOf(civilization.getFarm()));
				datosDominio.crearConstruccion(1);
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuySmithy) {
			try {
				System.out.println("evento");
				civilization.newSmithy();
				lSmithy.setText(String.valueOf(civilization.getSmithy()));
				datosDominio.crearConstruccion(2);
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyCarpentry) {
			try {
				System.out.println("evento");
				civilization.newCarpentry();
				lCarpentry.setText(String.valueOf(civilization.getCarpentry()));
				datosDominio.crearConstruccion(3);
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyMagicTower) {
			try {
				System.out.println("evento");
				civilization.newMagictower();
				lMagicTower.setText(String.valueOf(civilization.getMagicTower()));
				datosDominio.crearConstruccion(4);
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyChurch) {
			try {
				System.out.println("evento");
				civilization.newChurch();
				lChurch.setText(String.valueOf(civilization.getChurch()));
				datosDominio.crearConstruccion(5);

			} catch (ResourceException e1) {
			}

			// tecnologias

		} else if (e.getSource() == bBuyAttack) {
			try {
				System.out.println("tecnologia");
				civilization.upgradeTechnologyAttack();
				lAttack.setText(String.valueOf(civilization.getTechnologyAttack()));
				int foodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST;
				int woodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST;
				int ironCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;

				if (civilization.getTechnologyAttack() > 1) {
					for (int i = 0; i < civilization.getTechnologyAttack() - 1; i++) {
						foodCost += foodCost / 100 * UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST;
						woodCost += woodCost / 100 * UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST;
						ironCost += ironCost / 100 * UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST;
					}
				}
				lAttackFoodCost.setText(Integer.toString(foodCost));
				lAttackWoodCost.setText(Integer.toString(woodCost));
				lAttackIronCost.setText(Integer.toString(ironCost));

				datosDominio.crearIncrementoTecnologia(1);
			} catch (ResourceException e1) {
			}
		} else if (e.getSource() == bBuyDefense) {
			try {
				System.out.println("tecnologia");
				civilization.upgradeTechnologyDefense();
				lDefense.setText(String.valueOf(civilization.getTechnologyDefense()));
				int foodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST;
				int woodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST;
				int ironCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;

				if (civilization.getTechnologyAttack() > 1) {
					for (int i = 0; i < civilization.getTechnologyAttack() - 1; i++) {
						foodCost += foodCost / 100 * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST;
						woodCost += woodCost / 100 * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST;
						ironCost += ironCost / 100 * UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST;
					}
				}
				lDefenseFoodCost.setText(Integer.toString(foodCost));
				lDefenseWoodCost.setText(Integer.toString(woodCost));
				lDefenseIronCost.setText(Integer.toString(ironCost));

				datosDominio.crearIncrementoTecnologia(2);
			} catch (ResourceException e1) {
			}
		}
		lFood.setText(String.valueOf(civilization.getFood()));
		lWood.setText(String.valueOf(civilization.getWood()));
		lIron.setText(String.valueOf(civilization.getIron()));
		lMana.setText(String.valueOf(civilization.getMana()));

		tPersonalizado.recursosActualizar(civilization.getFood(), civilization.getWood(), civilization.getIron(), civilization.getMana(),
				civilization.getFarm(), civilization.getCarpentry(), civilization.getSmithy(), civilization.getMagicTower());

	}

	public void stateChanged(ChangeEvent e) {

	}
  public void mouseMoved(MouseEvent e) {
	if (tPersonalizado.getCrearBatalla()) {
		boolean empezarBatalla=false;
		for (int i=0;i<civilization.getArmy().length;i++) {
			if (civilization.getArmy()[i].size()!=0) {
				empezarBatalla=true;
			}
		}
		if (empezarBatalla) {
		tabbedPane.setSelectedComponent(battlegroundPanel);
		battle.listArmyCivilization(civilization.getArmy());
		battle.listArmyEnemy(enemyArmy);
		battle.groupArmy(civilization.getArmy(), enemyArmy);
		battle.battle(civilization);
		taBattleDevelopment.setText(battle.getBattleDevelopment());
        taBattleSummary.setText(battle.getBattleSummary());
		int batallas=Integer.parseInt(lBattles.getText())+1;
		lBattles.setText(String.valueOf(batallas));
		//cambiar labels
		lSwordsman.setText(String.valueOf(civilization.getArmy()[0].size()));
		lSwordsmanBattle.setText(String.valueOf(civilization.getArmy()[0].size()));
		lSpearman.setText(String.valueOf(civilization.getArmy()[1].size()));
		lSpearmanBattle.setText(String.valueOf(civilization.getArmy()[1].size()));
		lCrossbow.setText(String.valueOf(civilization.getArmy()[2].size()));
		lCrossbowBattle.setText(String.valueOf(civilization.getArmy()[2].size()));
		lCannon.setText(String.valueOf(civilization.getArmy()[3].size()));
		lCannonBattle.setText(String.valueOf(civilization.getArmy()[3].size()));
		lArrowTower.setText(String.valueOf(civilization.getArmy()[4].size()));
		lArrowTowerBattle.setText(String.valueOf(civilization.getArmy()[4].size()));
		lCatapult.setText(String.valueOf(civilization.getArmy()[5].size()));
		lCatapultBattle.setText(String.valueOf(civilization.getArmy()[5].size()));
		lRocketLauncherTower.setText(String.valueOf(civilization.getArmy()[6].size()));
		lRocketLauncherTowerBattle.setText(String.valueOf(civilization.getArmy()[6].size()));
		lMagician.setText(String.valueOf(civilization.getArmy()[7].size()));
		lMagicianBattle.setText(String.valueOf(civilization.getArmy()[7].size()));
		lPriest.setText(String.valueOf(civilization.getArmy()[8].size()));
		lPriestBattle.setText(String.valueOf(civilization.getArmy()[8].size()));
//  for (int i = 0; i<text.length;i++) {
//			datosDominio.insertLogs(text[i]);
//		}

		
		}else {
			JOptionPane.showMessageDialog(null, "There isn't a civilization army to fight", "Not soldiers found!", JOptionPane.WARNING_MESSAGE);
		}		
		
		
		





			tPersonalizado.setCrearBatalla(false);
		}
	

}