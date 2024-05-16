package game;

import java.util.ArrayList;
import java.util.Random;

import src.game.attackUnities.AttackUnity;
import src.game.attackUnities.Cannon;
import src.game.attackUnities.Crossbow;
import src.game.attackUnities.Spearman;
import src.game.attackUnities.Swordsman;
import src.game.defenseUnities.ArrowTower;
import src.game.defenseUnities.Catapult;
import src.game.defenseUnities.DefenseUnit;
import src.game.defenseUnities.RocketLauncherTower;
import src.game.specialUnities.Magician;
import src.game.specialUnities.Priest;
import src.game.specialUnities.SpecialUnit;

public class Battle implements Variables {
	private ArrayList<MilitaryUnit>[] civilizationArmy = new ArrayList[9]; // Almacenar nuestro ejercito
	private ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[9]; // Almacenar ejercito enemigo
	private ArrayList[][] armies = new ArrayList[2][9]; // Almacena los dos ejercitos // --no entiendo para que
	private String battleDevelopment; // Guarda el desarollo de la partida
	private int[][] initialCostFleet = new int[2][3]; // Guarda el coste de los materiales de tanto nuestro como del enemigo // --Creo
														// que no me es util
	private int initialNumberUnitsCivilization, initialNumberUnitsEnemy; // Guarda la catidad inicial de cada ejercito
	private int[] wasteWoodIron = new int[2]; // Residuos generados
	private int civilizationDrops, enemyDrops; // Para calcular las pérdidas de materiales de cada éjercito
	private int[][] resourcesLooses = new int[2][4]; // Guarda las perdidas de los bandos
	private int[][] initialArmies = new int[2][9]; // Cuantifica cada tipo de unidad de los ejercitos iniciales
	private int[] actualNumberUnitsCivilization, actualNumberUnitsEnemy; // Cuantifica cada tipo de unidad de los ejercitos
																			// actuales
	private int primerGolpe;

	private Random random = new Random();
	private Civilization civilization = new Civilization();

	// GETTER AND SETTERS-------------
	public ArrayList<MilitaryUnit>[] getCivilizationArmy() {
		return civilizationArmy;
	}

	public void setCivilizationArmy(ArrayList<MilitaryUnit>[] civilizationArmy) {
		this.civilizationArmy = civilizationArmy;
	}

	public ArrayList<MilitaryUnit>[] getEnemyArmy() {
		return enemyArmy;
	}

	public void setEnemyArmy(ArrayList<MilitaryUnit>[] enemyArmy) {
		this.enemyArmy = enemyArmy;
	}

	public ArrayList[][] getArmies() {
		return armies;
	}

	public void setArmies(ArrayList[][] armies) {
		this.armies = (ArrayList[][]) armies;
	}

	public String getBattleDevelopment() {
		return battleDevelopment;
	}

	public void setBattleDevelopment(String battleDevelopment) {
		this.battleDevelopment = battleDevelopment;
	}

	public int[][] getInitialCostFleet() {
		return initialCostFleet;
	}

	public void setInitialCostFleet(int[][] initialCostFleet) {
		this.initialCostFleet = initialCostFleet;
	}

	public int getInitialNumberUnitsCivilization() {
		return initialNumberUnitsCivilization;
	}

	public void setInitialNumberUnitsCivilization(int initialNumberUnitsCivilization) {
		this.initialNumberUnitsCivilization = initialNumberUnitsCivilization;
	}

	public int getInitialNumberUnitsEnemy() {
		return initialNumberUnitsEnemy;
	}

	public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
		this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
	}

	public int[] getWasteWoodIron() {
		return wasteWoodIron;
	}

	public void setWasteWoodIron(int[] wasteWoodIron) {
		this.wasteWoodIron = wasteWoodIron;
	}

	public int getCivilizationDrops() {
		return civilizationDrops;
	}

	public void setCivilizationDrops(int civilizationDrops) {
		this.civilizationDrops = civilizationDrops;
	}

	public int getEnemyDrops() {
		return enemyDrops;
	}

	public void setEnemyDrops(int enemyDrops) {
		this.enemyDrops = enemyDrops;
	}

	public int[][] getResourcesLooses() {
		return resourcesLooses;
	}

	public void setResourcesLooses(int[][] resourcesLooses) {
		this.resourcesLooses = resourcesLooses;
	}

	public int[][] getInitialArmies() {
		return initialArmies;
	}

	public void setInitialArmies(int[][] initialArmies) {
		this.initialArmies = initialArmies;
	}

	public int[] getActualNumberUnitsCivilization() {
		for (int i = 0; i < armies[0].length; i++) {
			actualNumberUnitsCivilization[i] = armies[0][i].size();
		}
		return actualNumberUnitsCivilization;
	}

	public void setActualNumberUnitsCivilization(int[] actualNumberUnitsCivilization) {
		this.actualNumberUnitsCivilization = actualNumberUnitsCivilization;
	}

	public int[] getActualNumberUnitsEnemy() {
		for (int i = 0; i < armies[1].length; i++) {
			actualNumberUnitsEnemy[i] = armies[1][i].size();
		}
		return actualNumberUnitsEnemy;
	}

	public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
		this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
	}

	public int isPrimerGolpe() {
		int numeroAleatorio = random.nextInt(2);
		setPrimerGolpe(numeroAleatorio);
		return primerGolpe;
	}

	public void setPrimerGolpe(int primerGolpe) {
		this.primerGolpe = primerGolpe;
	}

	// METODOS BATTALLA -------------------------------------------

	// encontrar constructor del soldado
	public MilitaryUnit createUnit(String unitType, int actualArmor, int attack) {
		switch (unitType) {
		case "Swordsman":
			return new Swordsman(actualArmor, attack);
		case "Spearman":
			return new Spearman(actualArmor, attack);
		case "Crossbow":
			return new Crossbow(actualArmor, attack);
		case "Cannon":
			return new Cannon(actualArmor, attack);
		case "ArrowTower":
			return new ArrowTower(actualArmor, attack);
		case "Catapult":
			return new Catapult(actualArmor, attack);
		case "RocketLauncherTower":
			return new RocketLauncherTower(actualArmor, attack);
		case "Magician":
			return new Magician(actualArmor, attack);
		case "Priest":
			return new Priest(actualArmor, attack);
		default:
			throw new IllegalArgumentException("Tipo de unidad militar no válido: " + unitType);
		}
	}

	// Metodo para guardar nuestro ejercito, el numero de soldados y el total
	public void listArmyCivilization(ArrayList<MilitaryUnit>[] Army) {

		System.out.println("CREANDO LISTA CIVILIZACION"); // borrar

		for (int i = 0; i < Army.length; i++) { // limpia el ArrayList
			System.out.println("Miro si hay algo"); // borrar
			if (civilizationArmy[i] != null && civilizationArmy[i].size() > 0) {
				civilizationArmy[i].clear();
				System.out.println("Se a limpiado"); // borrar
			}
		}

		actualNumberUnitsCivilization = new int[Army.length];
		int numeroEjercito = 0;

		for (int i = 0; i < Army.length; i++) { // añade el ejercito aliado
			civilizationArmy[i] = new ArrayList<>();
			for (MilitaryUnit unit : Army[i]) {
				civilizationArmy[i].add(createUnit(unit.getClass().getName().substring(unit.getClass().getName().lastIndexOf(".") + 1),
						unit.getActualArmor(), unit.attack()));
				System.out.println("He creado un soldado tipo: "
						+ unit.getClass().getName().substring(unit.getClass().getName().lastIndexOf(".") + 1)); // borrar
			}
			initialArmies[0][i] = civilizationArmy[i].size(); // añade la cantidad de soldados
			actualNumberUnitsCivilization[i] = civilizationArmy[i].size();
			numeroEjercito += civilizationArmy[i].size(); // suma el numero de soldados
		}

		setInitialNumberUnitsCivilization(numeroEjercito);
		System.out.println("Cantidad de soldados: " + initialArmies[0][3] + " tipo : "
				+ Army[3].get(0).getClass().getName().substring(Army[3].get(0).getClass().getName().lastIndexOf(".") + 1)); // borrar
		System.out.println("Cantidad de soldados actualmente: " + actualNumberUnitsCivilization[7] + " tipo : "
				+ Army[7].get(0).getClass().getName().substring(Army[7].get(0).getClass().getName().lastIndexOf(".") + 1)); // borrar
		System.out.println("Cantidad total soldados: " + getInitialNumberUnitsCivilization()); // borrar
	}

	// Metodo para guardar el ejercito enemigo, el numero de soldados y el total
	public void listArmyEnemy(ArrayList<MilitaryUnit>[] Army) {

		System.out.println("CREANDO LISTA ENEMY"); // borrar

		for (int i = 0; i < Army.length; i++) { // limpia el ArrayList
			System.out.println("Miro si hay algo"); // borrar
			if (enemyArmy[i] != null && enemyArmy[i].size() > 0) {
				enemyArmy[i].clear();
				System.out.println("Se a limpiado"); // borrar
			}
		}

		actualNumberUnitsEnemy = new int[Army.length];
		int numeroEjercito = 0;

		for (int i = 0; i < Army.length; i++) { // añade el ejercito enemigo
			enemyArmy[i] = new ArrayList<>();
			for (MilitaryUnit unit : Army[i]) {
				enemyArmy[i].add(createUnit(unit.getClass().getName().substring(unit.getClass().getName().lastIndexOf(".") + 1),
						unit.getActualArmor(), unit.attack()));
				System.out.println("He creado un soldado tipo: "
						+ unit.getClass().getName().substring(unit.getClass().getName().lastIndexOf(".") + 1)); // borrar
			}
			initialArmies[1][i] = enemyArmy[i].size(); // añade la cantidad de soldados iniciales
			actualNumberUnitsEnemy[i] = enemyArmy[i].size(); // añade la cantidad de soldados actuales
			numeroEjercito += enemyArmy[i].size(); // suma el numero de soldados
		}

		setInitialNumberUnitsEnemy(numeroEjercito);
		System.out.println("Cantidad de soldados: " + initialArmies[1][3] + " tipo : "
				+ Army[3].get(0).getClass().getName().substring(Army[3].get(0).getClass().getName().lastIndexOf(".") + 1)); // borrar
		System.out.println("Cantidad de soldados actualmente: " + actualNumberUnitsCivilization[7] + " tipo : "
				+ Army[7].get(0).getClass().getName().substring(Army[7].get(0).getClass().getName().lastIndexOf(".") + 1)); // borrar
		System.out.println("Cantidad total soldados: " + getInitialNumberUnitsEnemy()); // borrar
	}

	// Metodo para guardar tanto nuestro ejercito como el enemigo
	public void groupArmy() {
		armies[0] = civilizationArmy;
		armies[1] = enemyArmy;

		System.out.println("Viendo nuestros soldados");
		for (ArrayList grupo : armies[0]) {
			for (int i = 0; i < grupo.size(); i++) {
				System.out.println(grupo.get(i).getClass().getName().substring(grupo.get(i).getClass().getName().lastIndexOf(".") + 1));
			}
		}

		System.out.println("Viendo soldados enemigos");
		for (ArrayList grupo : armies[1]) {
			for (int i = 0; i < grupo.size(); i++) {
				System.out.println(grupo.get(i).getClass().getName().substring(grupo.get(i).getClass().getName().lastIndexOf(".") + 1));
			}
		}

//		System.out.println(armies[0]); // borrar
//		System.out.println(armies[1]); // borrar
	}

	// metodo para saber las perdidas de recursos
	public void updateResourcesLooses() {
		// Inicia los materiales
		int food = 0;
		int wood = 0;
		int iron = 0;

		for (int i = 0; i < initialArmies[0].length; i++) { // Sumamos el coste de cada unidad que hemos perdido
			// Miramos cuanto cuesta la unidad y lo multiplicamos por la perdidas que hemos
			// tenido
			food += civilizationArmy[i].get(0).getFoodCost() * (initialArmies[0][i] - getActualNumberUnitsCivilization()[i]);
			wood += civilizationArmy[i].get(0).getWoodCost() * (initialArmies[0][i] - getActualNumberUnitsCivilization()[i]);
			iron += civilizationArmy[i].get(0).getIronCost() * (initialArmies[0][i] - getActualNumberUnitsCivilization()[i]);
		}

		// Guardamos los datos
		resourcesLooses[0][0] = food;
		resourcesLooses[0][1] = wood;
		resourcesLooses[0][2] = iron;
		resourcesLooses[0][3] = iron + 5 * wood + 10 * food;

		food = 0;
		wood = 0;
		iron = 0;

		for (int i = 0; i < initialArmies[1].length; i++) {
			food += enemyArmy[i].get(0).getFoodCost() * (initialArmies[1][i] - getActualNumberUnitsEnemy()[i]);
			wood += enemyArmy[i].get(0).getWoodCost() * (initialArmies[1][i] - getActualNumberUnitsEnemy()[i]);
			iron += enemyArmy[i].get(0).getIronCost() * (initialArmies[1][i] - getActualNumberUnitsEnemy()[i]);
		}

		resourcesLooses[1][0] = food;
		resourcesLooses[1][1] = wood;
		resourcesLooses[1][2] = iron;
		resourcesLooses[1][3] = iron + 5 * wood + 10 * food;
	}

	// Para calcular los porcentajes de unidades que quedan respecto los ejércitos
	// iniciales.
	public boolean remainderPercentageFleetCivilization() {
		boolean perdido = false;
		float numeroPerderCivilization = (float) (initialNumberUnitsCivilization * 0.20);
		float numeroActualCivilization = (float) 0.0;

		for (int soldados : getActualNumberUnitsCivilization()) {
			numeroActualCivilization += (float) soldados;
		}

		if (numeroActualCivilization < numeroPerderCivilization) {
			perdido = true;
		}

		System.out.println(
				"Quedan " + numeroActualCivilization + " soldados y su 20% es " + numeroPerderCivilization + " de la Civilizacion");

		return perdido;
	}

	// Para calcular los porcentajes de unidades que quedan respecto los ejércitos
	// enemigos iniciales.
	public boolean remainderPercentageFleetEnemy() {
		boolean perdido = false;
		float numeroPerderEnemy = (float) (initialNumberUnitsEnemy * 0.20);
		float numeroActualEnemy = (float) 0.0;

		for (int soldados : getActualNumberUnitsEnemy()) {
			numeroActualEnemy += (float) soldados;
		}

		if (numeroActualEnemy < numeroPerderEnemy) {
			perdido = true;
		}

		System.out.println("Quedan " + numeroActualEnemy + " soldados y su 20% es " + numeroPerderEnemy + " del Enemigo");
		return perdido;
	}

	// Metodos Mar
	public int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
		// para que dado un ejército, nos devuelva el grupo defensor, 0-3 en el caso de
		// la flota enemiga, 0-8 en el caso del ejército de nuestra civilización.
		int totalSoldados = 0;
		for (int i = 0; i < army.length; i++) {
			totalSoldados += army[i].size();
		}
		int aleatorio = (int) (Math.random() * totalSoldados);

		totalSoldados = 0;
		int respuesta = 0;
		for (int i = 0; i < army.length; i++) {
			totalSoldados += army[i].size();
			if (totalSoldados > aleatorio) {
				respuesta = i;
				break;
			}
		}
		return respuesta;

	}

	public int getCivilizationGroupAttacker(ArrayList<MilitaryUnit>[] army) { // para escoger el grupo atacante civilizacion
		int totalSoldados = 0;
		for (int i = 0; i < army.length - 1; i++) { // -1 para que no entre priest
			totalSoldados += army[i].size();
		}
		int aleatorio = (int) (Math.random() * totalSoldados);

		totalSoldados = 0;
		int respuesta = 0;
		for (int i = 0; i < army.length - 1; i++) { // -1 para que no entre priest
			totalSoldados += army[i].size();
			if (totalSoldados > aleatorio) {
				respuesta = i;
				break;
			}
		}
		return respuesta;
	}

	public int getEnemyGroupAttacker(ArrayList<MilitaryUnit>[] army) { // para escoger el grupo atacante enemigo
		int totalSoldados = 0;
		for (int i = 0; i < army.length; i++) {
			totalSoldados += army[i].size();
		}
		int aleatorio = (int) (Math.random() * totalSoldados);

		totalSoldados = 0;
		int respuesta = 0;
		for (int i = 0; i < army.length; i++) {
			totalSoldados += army[i].size();
			if (totalSoldados > aleatorio) {
				respuesta = i;
				break;
			}
		}
		return respuesta;
	}

	// Metodos luciano

	public int getCivilizationSoldierGroup(ArrayList<MilitaryUnit>[] army) {
		int totalSoldados = 0;
		for (int i = 0; i < army.length - 1; i++) { // -1 para que no entre priest
			totalSoldados += army[i].size();
		}

		int aleatorio = (int) (Math.random() * totalSoldados);

		totalSoldados = 0;
		int respuesta = 0;
		for (int i = 0; i < army.length - 1; i++) { // -1 para que no entre priest
			totalSoldados += army[i].size();
			if (totalSoldados >= aleatorio) {
				respuesta = i;
				break;
			}
		}

		System.out.println(
				army[respuesta].get(0).getClass().getName().substring(army[respuesta].get(0).getClass().getName().lastIndexOf(".") + 1));
		return respuesta;

	}

	public int getSoldierGroup(ArrayList<MilitaryUnit>[] army) {
		int totalSoldados = 0;
		for (int i = 0; i < army.length; i++) {
			totalSoldados += army[i].size();
		}
		int aleatorio = (int) (Math.random() * totalSoldados);

		totalSoldados = 0;
		int respuesta = 0;
		for (int i = 0; i < army.length; i++) {
			totalSoldados += army[i].size();
			if (totalSoldados >= aleatorio) {
				respuesta = i;
				break;
			}
		}

		System.out.println(
				army[respuesta].get(0).getClass().getName().substring(army[respuesta].get(0).getClass().getName().lastIndexOf(".") + 1));
		return respuesta;
	}

	public int getRandomSoldier(ArrayList army) {
		int respuesta = (int) (Math.random() * army.size());
		return respuesta;
	}

	// -----------------------------------
	public void resetArmyArmor() { // que restablecerá las armaduras de nuestro ejército.
		for (int i = 0; i < civilizationArmy.length; i++) {
			for (int j = 0; j < civilizationArmy[i].size(); j++) {
				civilizationArmy[i].get(j).resetArmor();
			}
		}
	}

	// santificar todas las unidades que no estan santificadas
	void sanctify() {
		if (civilizationArmy[8].size() > 0) { // si la cantidad de priest es mayor que 0
			for (int i = 0; i < civilizationArmy.length - 1; i++) { // se recorre la lista de army menos el ultimo que es la unidad de
																	// priests
				for (int j = 0; j < (int) civilizationArmy[i].size(); j++) { // se recorre cada personaje de la unidad
					if (civilizationArmy[i].get(j) instanceof AttackUnity) {
						if (((AttackUnity) civilizationArmy[i].get(j)).isSanctified() == false) {
							// poner armadura +7%
							((AttackUnity) civilizationArmy[i].get(j)).setArmor(((AttackUnity) civilizationArmy[i].get(j)).getArmor()
									+ ((AttackUnity) civilizationArmy[i].get(j)).getArmor() / 100 * PLUS_ARMOR_UNIT_SANCTIFIED);
							// poner damage +7%
							((AttackUnity) civilizationArmy[i].get(j)).setBaseDamage(((AttackUnity) civilizationArmy[i].get(j))
									.getBaseDamage()
									+ ((AttackUnity) civilizationArmy[i].get(j)).getBaseDamage() / 100 * PLUS_ATTACK_UNIT_SANCTIFIED);
							// poner sanctified en true
							((AttackUnity) civilizationArmy[i].get(j)).setSanctified(true);
						}
					} else if (civilizationArmy[i].get(j) instanceof DefenseUnit) {
						if (((DefenseUnit) civilizationArmy[i].get(j)).isSanctified() == false) {
							// poner armadura +7%
							((DefenseUnit) civilizationArmy[i].get(j)).setArmor(((DefenseUnit) civilizationArmy[i].get(j)).getArmor()
									+ ((DefenseUnit) civilizationArmy[i].get(j)).getArmor() / 100 * PLUS_ARMOR_UNIT_SANCTIFIED);
							// poner damage +7%
							((DefenseUnit) civilizationArmy[i].get(j)).setBaseDamage(((DefenseUnit) civilizationArmy[i].get(j))
									.getBaseDamage()
									+ ((DefenseUnit) civilizationArmy[i].get(j)).getBaseDamage() / 100 * PLUS_ATTACK_UNIT_SANCTIFIED);
							// poner sanctified en true
							((DefenseUnit) civilizationArmy[i].get(j)).setSanctified(true);
						}
					} else if (civilizationArmy[i].get(j) instanceof SpecialUnit) {
						if (((SpecialUnit) civilizationArmy[i].get(j)).isSanctified() == false) {
							// poner armadura +7%
							((SpecialUnit) civilizationArmy[i].get(j)).setArmor(((SpecialUnit) civilizationArmy[i].get(j)).getArmor()
									+ ((SpecialUnit) civilizationArmy[i].get(j)).getArmor() / 100 * PLUS_ARMOR_UNIT_SANCTIFIED);
							// poner damage +7%
							((SpecialUnit) civilizationArmy[i].get(j)).setBaseDamage(((SpecialUnit) civilizationArmy[i].get(j))
									.getBaseDamage()
									+ ((SpecialUnit) civilizationArmy[i].get(j)).getBaseDamage() / 100 * PLUS_ATTACK_UNIT_SANCTIFIED);
							// poner sanctified en true
							((SpecialUnit) civilizationArmy[i].get(j)).setSanctified(true);
						}
					}
				}
			}
		}
	}

	// desantificar todas las unidades santificadas
	void desanctify() {
		if (civilizationArmy[8].size() == 0) { // si la cantidad de priest es 0
			for (int i = 0; i < civilizationArmy.length - 1; i++) { // se recorre la lista de army menos el ultimo que es la unidad de
																	// priests
				for (int j = 0; j < (int) civilizationArmy[i].size(); j++) { // se recorre cada personaje de la unidad
					if (civilizationArmy[i].get(j) instanceof AttackUnity) {
						if (((AttackUnity) civilizationArmy[i].get(j)).isSanctified() == true) {
							// poner armadura +7%
							((AttackUnity) civilizationArmy[i].get(j)).setArmor(((AttackUnity) civilizationArmy[i].get(j)).getArmor()
									- ((AttackUnity) civilizationArmy[i].get(j)).getArmor() / 100 * PLUS_ARMOR_UNIT_SANCTIFIED);
							// poner damage +7%
							((AttackUnity) civilizationArmy[i].get(j)).setBaseDamage(((AttackUnity) civilizationArmy[i].get(j))
									.getBaseDamage()
									- ((AttackUnity) civilizationArmy[i].get(j)).getBaseDamage() / 100 * PLUS_ATTACK_UNIT_SANCTIFIED);
							// poner sanctified en false
							((AttackUnity) civilizationArmy[i].get(j)).setSanctified(false);
						}
					} else if (civilizationArmy[i].get(j) instanceof DefenseUnit) {
						if (((DefenseUnit) civilizationArmy[i].get(j)).isSanctified() == true) {
							// poner armadura +7%
							((DefenseUnit) civilizationArmy[i].get(j)).setArmor(((DefenseUnit) civilizationArmy[i].get(j)).getArmor()
									- ((DefenseUnit) civilizationArmy[i].get(j)).getArmor() / 100 * PLUS_ARMOR_UNIT_SANCTIFIED);
							// poner damage +7%
							((DefenseUnit) civilizationArmy[i].get(j)).setBaseDamage(((DefenseUnit) civilizationArmy[i].get(j))
									.getBaseDamage()
									- ((DefenseUnit) civilizationArmy[i].get(j)).getBaseDamage() / 100 * PLUS_ATTACK_UNIT_SANCTIFIED);
							// poner sanctified en false
							((DefenseUnit) civilizationArmy[i].get(j)).setSanctified(false);
						}
					} else if (civilizationArmy[i].get(j) instanceof SpecialUnit) {
						if (((SpecialUnit) civilizationArmy[i].get(j)).isSanctified() == true) {
							// poner armadura +7%
							((SpecialUnit) civilizationArmy[i].get(j)).setArmor(((SpecialUnit) civilizationArmy[i].get(j)).getArmor()
									- ((SpecialUnit) civilizationArmy[i].get(j)).getArmor() / 100 * PLUS_ARMOR_UNIT_SANCTIFIED);
							// poner damage +7%
							((SpecialUnit) civilizationArmy[i].get(j)).setBaseDamage(((SpecialUnit) civilizationArmy[i].get(j))
									.getBaseDamage()
									- ((SpecialUnit) civilizationArmy[i].get(j)).getBaseDamage() / 100 * PLUS_ATTACK_UNIT_SANCTIFIED);
							// poner sanctified en false
							((SpecialUnit) civilizationArmy[i].get(j)).setSanctified(false);
						}
					}
				}
			}
		}
	}

	// Generar la pelea entre el atacante y el defensor
	public boolean battleSoldiers(ArrayList<MilitaryUnit> attacker, ArrayList<MilitaryUnit> defender, boolean repetirTurno, int turno) {

		// definimos tanto defensor como atacante
		MilitaryUnit attackerSoldier = attacker.get(getRandomSoldier(attacker));
		MilitaryUnit defenderSoldier = defender.get(getRandomSoldier(defender));

		System.out.println(
				"Atacante: " + attackerSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1));
		System.out.println(
				"Defensor: " + defenderSoldier.getClass().getName().substring(defenderSoldier.getClass().getName().lastIndexOf(".") + 1));

		defenderSoldier.takeDamage(attackerSoldier.attack()); // Genramos el ataque

		// Crea los mensaje dependiendo quien ataque
		if (turno % 2 == 0) {
			battleDevelopment += "Attacks Civilization: "
					+ attackerSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1)
					+ " attacks "
					+ defenderSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1) + "\n";
			battleDevelopment += attackerSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1)
					+ " genrates the damage = " + attackerSoldier.attack() + "\n";
			battleDevelopment += defenderSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1)
					+ " stays with armor =  " + defenderSoldier.getActualArmor() + "\n";
		} else {
			battleDevelopment += "Attacks army enemy: "
					+ attackerSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1)
					+ " attacks "
					+ defenderSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1) + "\n";
			battleDevelopment += attackerSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1)
					+ " genrates the damage = " + attackerSoldier.attack() + "\n";
			battleDevelopment += defenderSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1)
					+ " stays with armor =  " + defenderSoldier.getActualArmor() + "\n";
		}

		if (defenderSoldier.getActualArmor() <= 0) { // Comprobamos que si el defensor esta 0 o negativo de vida
			// Si es true se elimina
			defender.remove(defenderSoldier);

			int numeroAleatorioRecursos = random.nextInt(101);

			if (numeroAleatorioRecursos > defenderSoldier.getChanceGeneratinWaste()) { // Comprobamos si generara algun recurso
				wasteWoodIron[0] += defenderSoldier.getWoodCost() * PERCENTATGE_WASTE;
				wasteWoodIron[1] += defenderSoldier.getIronCost() * PERCENTATGE_WASTE;
			}

			battleDevelopment += "We eliminate"
					+ defenderSoldier.getClass().getName().substring(attackerSoldier.getClass().getName().lastIndexOf(".") + 1) + "\n";
		}

		int numeroAleatorioRepetirTurno = random.nextInt(101);

		if (numeroAleatorioRepetirTurno > attackerSoldier.getChanceAttackAgain()) { // Comprobamos si podra efectuar un nuevo ataque
			System.out.println("Numero aleatorio: " + numeroAleatorioRepetirTurno + " > chance: " + attackerSoldier.getChanceAttackAgain());
			repetirTurno = false;
		}

		return repetirTurno;
	}

	// Genera la batalla
	public boolean battle() {

		System.out.println("\nCOMIENZA LA BATALLA\n");

		battleDevelopment = "";

		boolean battleWin = false;
		boolean battleover = false;
		int turno = isPrimerGolpe();// tiramos a ver quien ataca primero

		// Reseteamos la armadura de nuestros soldados
		resetArmyArmor();

		// Resetea los recursos
		for (int i = 0; i < wasteWoodIron.length; i++) {
			wasteWoodIron[i] = 0;
		}

		// Si hay algun prise santificara a todos los soldados
		sanctify();

		while (!battleover) { // Empeza la batalla
			battleDevelopment += "********************CHANGE ATTACKER********************\n";

			boolean repetirTurno = true;

			if (turno % 2 == 0) { // Comprueba quien ataca primero
				while (repetirTurno) {
					repetirTurno = battleSoldiers(armies[turno % 2][getCivilizationSoldierGroup(civilizationArmy)],
							armies[(turno + 1) % 2][getSoldierGroup(enemyArmy)], repetirTurno, turno);
				}
			} else {
				while (repetirTurno) {
					repetirTurno = battleSoldiers(armies[turno % 2][getSoldierGroup(enemyArmy)],
							armies[(turno + 1) % 2][getSoldierGroup(civilizationArmy)], repetirTurno, turno);
				}
			}

			System.out.println(remainderPercentageFleetCivilization());

			if (remainderPercentageFleetCivilization() == true) { // Comprueba si nuestra civilizacion esta por encima del 20%
				// Si es true habremos perdido
				System.out.println("He comprobado que civilizacion este por encima del 20%");
				battleDevelopment += "********************YOU HAVE LOST********************\n";
				battleover = true;
			}

			System.out.println(remainderPercentageFleetEnemy());

			if (remainderPercentageFleetEnemy() == true) { // Comprueba si la civilizacion enemiga esta por encima del 20%
				// Si es true habremos ganado y conseguido los recursos que se hayan generado
				// durante la batalla
				System.out.println("He comprobado que el enemigo este por encima del 20%");
				battleDevelopment += "********************YOU'VE WON********************\n";
				battleWin = true;
				civilization.setWood(civilization.getWood() + wasteWoodIron[0]);
				civilization.setIron(civilization.getIron() + wasteWoodIron[1]);
				battleover = true;
			}

			turno += 1; // Al terminar añade un turno
			System.out.println(turno);
			desanctify();
		}

		System.out.println(battleDevelopment);
		return battleWin;
	}

}

/*
 * 
 * (X) Comienzo de combate aleatorio
 * 
 * (X) Almazenar nuestro ejercito
 * 
 * (X) Almazenar ejercito enemigo
 * 
 * (X) Almazenar juntos
 * 
 * ( ) Coste de la civilizacion nuestra y enemiga // no sirve par nada creo
 * 
 * (X) Cuantificar nuestro ejercito
 * 
 * (X) Cuantificar ejercito enemigo
 * 
 * (X) Cuantificar nuestro ejercito por tipo de soldado
 * 
 * (X) Cuantificar ejercito enemigo por tipo de soldado
 * 
 * (X) Probabilidad de que unidad ataca Aliado
 * 
 * (X) Probabilidad de que unidad defiender Aliado
 * 
 * (X) Probabilidad de que unidad ataca/defiender Enemigo
 * 
 * (X) Resetear armadura
 * 
 * (X) La battalla
 * 
 * (X) Generar residuos de los soldados caidos
 * 
 * (X) Comprobar si puede atacar de nuevo
 * 
 * (X) calcular el 20% de los ejercitos
 * 
 * (X) Calcular cantidad de recursos perdidos
 * 
 * (X) Santificar unidades
 * 
 * (X) Desantificar unidades
 * 
 * ( ) Batalla paso a paso
 * 
 * ( ) Resumen de la batalla
 * 
 */