package game;

import java.util.ArrayList;
import java.util.Random;

import game.attackUnities.AttackUnity;
import game.attackUnities.Cannon;
import game.attackUnities.Crossbow;
import game.attackUnities.Spearman;
import game.attackUnities.Swordsman;
import game.defenseUnities.ArrowTower;
import game.defenseUnities.Catapult;
import game.defenseUnities.DefenseUnit;
import game.defenseUnities.RocketLauncherTower;
import game.specialUnities.Magician;
import game.specialUnities.Priest;
import game.specialUnities.SpecialUnit;

public class Battle implements Variables {
	private ArrayList<MilitaryUnit>[] civilizationArmy = new ArrayList[9]; // Almacenar nuestro ejercito
	private ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[4]; // Almacenar ejercito enemigo // son 4
	private ArrayList[][] armies = new ArrayList[2][9]; // Almacena los dos ejercitos
	private String battleDevelopment = ""; // Guarda el desarollo de la partida
	private String battleSummary = ""; // Guarda el resumen de la batalla
	private int[] battleDrops = new int[4]; // Guardar los caidos en battalla
	private int[][] initialCostFleet = new int[2][3]; // Guarda el coste total de todas nuestras unidades y del enemigo
	private int initialNumberUnitsCivilization, initialNumberUnitsEnemy; // Guarda el numero de soldados iniciales de cada ejercito
	private int[] wasteWoodIron = new int[2]; // Residuos generados
	private int[][] resourcesLooses = new int[2][4]; // Guarda las perdidas de los 2 bandos
	private int[][] initialArmies = new int[2][9]; // Guarda la cantidad de cada tipo de soldado de los 2 bandos
	private int[] actualNumberUnitsCivilization = new int[9], actualNumberUnitsEnemy = new int[4]; // Guarda la cantidad de cada tipo de
																									// soldado de los ejercitos actuales
																									// son 4
	private int primerGolpe;
	private Random random = new Random();

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
		if (battleDevelopment == null) {
			return "";
		} else {
			return battleDevelopment;
		}
	}

	public void setBattleDevelopment(String battleDevelopment) {
		this.battleDevelopment = battleDevelopment;
	}

	public String getBattleSummary() {
		return battleSummary;
	}

	public void setBattleSummary(String battleSummary) {
		battleSummary = battleSummary;
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
		return actualNumberUnitsCivilization;
	}

	public void setActualNumberUnitsCivilization(int[] actualNumberUnitsCivilization) {
		this.actualNumberUnitsCivilization = actualNumberUnitsCivilization;
	}

	public int[] getActualNumberUnitsEnemy() {
		return actualNumberUnitsEnemy;
	}

	public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
		this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
	}

	public int getPrimerGolpe() {
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

		for (int i = 0; i < Army.length; i++) { // limpia el ArrayList
			if (getCivilizationArmy()[i] != null) {
				getCivilizationArmy()[i].clear();
			}
		}

		int numeroEjercito = 0;

		for (int i = 0; i < Army.length; i++) { // añade el ejercito aliado
			getCivilizationArmy()[i] = new ArrayList<MilitaryUnit>();
			for (MilitaryUnit unit : Army[i]) {
				getCivilizationArmy()[i].add(createUnit(unit.getClass().getName().substring(unit.getClass().getName().lastIndexOf(".") + 1),
						unit.getActualArmor(), unit.attack()));
			}
			getInitialArmies()[0][i] = getCivilizationArmy()[i].size(); // añade la cantidad de soldados
			numeroEjercito += getCivilizationArmy()[i].size(); // suma el numero de soldados
		}

		setInitialNumberUnitsCivilization(numeroEjercito); // Añade el numero total de todo el ejercito
		createActualNumberUnitsCivilization(); // Ejecuta el metodo para saber el numero de soldados por typos
	}

	// Metodo para guardar el ejercito enemigo, el numero de soldados y el total
	public void listArmyEnemy(ArrayList<MilitaryUnit>[] Army) {

		for (int i = 0; i < /* Army.length */ 4; i++) { // limpia el ArrayList
			if (enemyArmy[i] != null) {
				enemyArmy[i].clear();
			}
		}

		int numeroEjercito = 0;

		for (int i = 0; i < Army.length; i++) { // añade el ejercito enemigo
			enemyArmy[i] = new ArrayList<MilitaryUnit>();
			for (MilitaryUnit unit : Army[i]) {
				enemyArmy[i].add(createUnit(unit.getClass().getName().substring(unit.getClass().getName().lastIndexOf(".") + 1),
						unit.getActualArmor(), unit.attack()));
			}
			initialArmies[1][i] = enemyArmy[i].size(); // añade la cantidad de soldados iniciales
			numeroEjercito += enemyArmy[i].size(); // suma el numero de soldados
		}

		setInitialNumberUnitsEnemy(numeroEjercito); // Añade el numero total de todo el ejercito
		createActualNumberUnitsEnemy(); // Ejecuta el metodo para saber el numero de soldados por typos
	}

	// Metodo para guardar tanto nuestro ejercito como el enemigo
	public void groupArmy(ArrayList<MilitaryUnit>[] cArmy, ArrayList<MilitaryUnit>[] eArmy) {

		for (int i = 0; i < cArmy.length; i++) {
			armies[0][i] = new ArrayList<MilitaryUnit>();
			if (cArmy[i] != null) {
				for (int j = 0; j < cArmy[i].size(); j++) {
					armies[0][i].add(cArmy[i].get(j));
				}
			}
		}

		for (int i = 0; i < eArmy.length; i++) {
			armies[1][i] = new ArrayList<MilitaryUnit>();
			if (eArmy[i] != null) {
				for (int j = 0; j < eArmy[i].size(); j++) {
					armies[1][i].add(eArmy[i].get(j));
				}
			}
		}

	}

	// Metodo para saber el numero actual de nuestro ejercito
	public void createActualNumberUnitsCivilization() {
		for (int i = 0; i < 9; i++) {
			if (armies[0][i] != null && armies[0][i].size() > 0) {
				getActualNumberUnitsCivilization()[i] = armies[0][i].size();
			} else {
				getActualNumberUnitsCivilization()[i] = 0;
			}
		}
	}

	// Metodo para saber el numero actual del ejercito enemigo
	public void createActualNumberUnitsEnemy() {
		for (int i = 0; i < 4; i++) {
			if (armies[1][i] != null && armies[1][i].size() > 0) {
				getActualNumberUnitsEnemy()[i] = armies[1][i].size();
			} else {
				getActualNumberUnitsEnemy()[i] = 0;
			}
		}
	}

	// Metodo para saber cuanto cuesta cada ejercito al completo
	public void createInitialCostFleet() {
		// Inicia los materiales
		int food = 0;
		int wood = 0;
		int iron = 0;

		for (int i = 0; i < initialArmies[0].length; i++) { // Sumamos el coste de cada unidad que hemos perdido
			// Miramos cuanto cuesta la unidad y lo multiplicamos por la perdidas que hemos
			// tenido
			if (civilizationArmy[i] != null && civilizationArmy[i].size() > 0) {
				if (initialArmies[0][i] > 0) {
					food += civilizationArmy[i].get(0).getFoodCost() * initialArmies[0][i];
					wood += civilizationArmy[i].get(0).getWoodCost() * initialArmies[0][i];
					iron += civilizationArmy[i].get(0).getIronCost() * initialArmies[0][i];
				}
			}
		}

		// Guardamos los datos
		initialCostFleet[0][0] = food;
		initialCostFleet[0][1] = wood;
		initialCostFleet[0][2] = iron;

		food = 0;
		wood = 0;
		iron = 0;

		for (int i = 0; i < enemyArmy.length; i++) {
			if (enemyArmy[i] != null && enemyArmy[i].size() > 0) {
				if (initialArmies[0][i] > 0) {
					food += enemyArmy[i].get(0).getFoodCost() * initialArmies[1][i];
					wood += enemyArmy[i].get(0).getWoodCost() * initialArmies[1][i];
					iron += enemyArmy[i].get(0).getIronCost() * initialArmies[1][i];
				}
			}
		}

		initialCostFleet[1][0] = food;
		initialCostFleet[1][1] = wood;
		initialCostFleet[1][2] = iron;
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
			if (civilizationArmy[i] != null && civilizationArmy[i].size() > 0) {
				if (initialArmies[0][i] > 0) {
					food += civilizationArmy[i].get(0).getFoodCost() * (initialArmies[0][i] - actualNumberUnitsCivilization[i]);
					wood += civilizationArmy[i].get(0).getWoodCost() * (initialArmies[0][i] - actualNumberUnitsCivilization[i]);
					iron += civilizationArmy[i].get(0).getIronCost() * (initialArmies[0][i] - actualNumberUnitsCivilization[i]);
				}
			}
		}

		// Guardamos los datos
		resourcesLooses[0][0] = food;
		resourcesLooses[0][1] = wood;
		resourcesLooses[0][2] = iron;
		resourcesLooses[0][3] = iron + 5 * wood + 10 * food;

		food = 0;
		wood = 0;
		iron = 0;

		for (int i = 0; i < enemyArmy.length; i++) {
			if (enemyArmy[i] != null && enemyArmy[i].size() > 0) {
				if (initialArmies[1][i] > 0) {
					food += enemyArmy[i].get(0).getFoodCost() * (initialArmies[1][i] - actualNumberUnitsEnemy[i]);
					wood += enemyArmy[i].get(0).getWoodCost() * (initialArmies[1][i] - actualNumberUnitsEnemy[i]);
					iron += enemyArmy[i].get(0).getIronCost() * (initialArmies[1][i] - actualNumberUnitsEnemy[i]);
				}
			}
		}

		resourcesLooses[1][0] = food;
		resourcesLooses[1][1] = wood;
		resourcesLooses[1][2] = iron;
		resourcesLooses[1][3] = iron + 5 * wood + 10 * food;
	}

	// Para calcular los porcentajes de unidades que quedan respecto los ejércitos
	// iniciales.
	public boolean remainingPercentageFleetCivilization() {
		createActualNumberUnitsCivilization();
		boolean perdido = false;
		float numeroPerderCivilization = (float) (initialNumberUnitsCivilization * 0.20); // Creamos el numero el cual sera nuestro 20%
		float numeroActualCivilization = (float) 0.0;

		for (int soldados : getActualNumberUnitsCivilization()) {
			numeroActualCivilization += (float) soldados; // Sumamos la cantidad de soldados que tenemos actualmente
		}

		if (numeroActualCivilization < numeroPerderCivilization) { // Comprobamos si el numero de soldados es más pequeño que su 20%
			perdido = true;
		}
		return perdido;
	}

	// Para calcular los porcentajes de unidades que quedan respecto los ejércitos
	// enemigos iniciales.
	public boolean remainingPercentageFleetEnemy() {
		createActualNumberUnitsEnemy();
		boolean perdido = false;
		float numeroPerderEnemy = (float) (initialNumberUnitsEnemy * 0.20); // Creamos el numero el cual sera nuestro 20%
		float numeroActualEnemy = (float) 0.0;

		for (int soldados : getActualNumberUnitsEnemy()) {
			numeroActualEnemy += (float) soldados; // Sumamos la cantidad de soldados que tenemos actualmente
		}
		if (numeroActualEnemy < numeroPerderEnemy) { // Comprobamos si el numero de soldados es más pequeño que su 20%
			perdido = true;
		}
		return perdido;
	}

	// Metodos
	public int getCivilizationSoldierGroup(ArrayList<MilitaryUnit>[] army) { // para escoger el grupo atacante civilizacion
		int totalSoldados = 0;

		for (int i = 0; i < army.length - 1; i++) { // Sumaos todos los soldados (-1 para que no entre priest)
			if (army[i] != null) {
				totalSoldados += army[i].size();
			}
		}
		int respuesta = 0;
		while (true) {
			int aleatorio = (int) (Math.random() * totalSoldados + 1); // Generamos un jnumero aleatoria del total de soldados
			totalSoldados = 0;
			for (int i = 0; i < army.length - 1; i++) { // Vamos sumando los soldados por tipos
				totalSoldados += army[i].size();
				if (totalSoldados >= aleatorio && army[i].size() > 0) {// Si el numero de soldados el superior al numero random nos
																		// quedamos
																		// con ese tipo ejercito
					respuesta = i;
					return respuesta;
				}
			}

		}

	}

	public int getSoldierGroup(ArrayList<MilitaryUnit>[] army) { // para escoger el grupo atacante
		int totalSoldados = 0;

		for (int i = 0; i < army.length; i++) { // Sumaos todos los soldados
			if (army[i] != null) {
				totalSoldados += army[i].size();
			}

		}
		int respuesta = 0;
		while (true) {
			int aleatorio = (int) (Math.random() * (totalSoldados + 1)); // Generamos un jnumero aleatoria del total de soldados
			totalSoldados = 0;
			for (int i = 0; i < army.length; i++) { // Vamos sumando los soldados por tipos
				if (army[i] != null) {
					totalSoldados += army[i].size();
					if (totalSoldados >= aleatorio && army[i].size() > 0) {// Si el numero de soldados el superior al numero random nos
																			// quedamos
																			// con ese tipo ejercito
						respuesta = i;
						return respuesta;
					}
				}
			}

		}
	}

	public int getRandomSoldier(ArrayList army) { // Genera un soldado aleatorio
		int respuesta = (int) (Math.random() * (army.size() - 1));
		return respuesta;
	}

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

	public String battleSummary(int turno) {
		createActualNumberUnitsCivilization(); // Actualizaqmos el numero actual de nuestro ejercito
		createActualNumberUnitsEnemy(); // Actualizaqmos el numero actual del ejercito enemigo
		createInitialCostFleet();
		String texto = "";

		texto += "BATTLE NUMBER: " + turno + "\n";
		texto += "BATTLE STATISTICS\n";
		texto += "Army planet Units Drops Initial Army Enemy Units Drops\n";
		// Buscamos a través de las posibles posiciones del ejército de la civilización
		// y el enemigo
		for (int i = 0; i < 9; i++) {
			// Verifica si hay unidades en el ejército de la civilización en la posición i.
			if (civilizationArmy[i] != null && civilizationArmy[i].size() > 0 && initialArmies[0][i] > 0) {
				// Obtiene el nombre de la clase de la primera unidad en la posición i del
				// ejército de la civilización.
				String name = civilizationArmy[i].get(0).getClass().getName()
						.substring(civilizationArmy[i].get(0).getClass().getName().lastIndexOf(".") + 1);
				// Si la posición i es menor que 5, se procesa tanto el ejército de la
				// civilización como el del enemigo.
				if (i < 4) {
					// Agrega información sobre el ejército de la civilización al texto.
					texto += name + " " + initialArmies[0][i] + " " + actualNumberUnitsCivilization[i] + " ";

					// Verifica si hay unidades en el ejército enemigo en la posición i.
					if (enemyArmy[i] != null && enemyArmy[i].size() > 0 && initialArmies[1][i] > 0) {
						// Agrega información sobre el ejército enemigo al texto.
						texto += name + " " + initialArmies[1][i] + " " + actualNumberUnitsEnemy[i] + "\n";
					} else {
						// Si no hay unidades en el ejército enemigo en la posición i, agrega ceros.
						texto += name + " " + 0 + " " + 0 + "\n";
					}
				} else {
					// Si la posición i es mayor o igual a 5, solo se procesa el ejército de la
					// civilización.
					texto += name + " " + initialArmies[0][i] + " " + actualNumberUnitsCivilization[i] + "\n";
				}
				// Si no hay unidades en el ejército de la civilización en la posición i, se
				// verifica el ejército enemigo.
			} else {
				if (i < enemyArmy.length) {
					if (enemyArmy[i] != null && enemyArmy[i].size() > 0 && initialArmies[1][i] > 0) {
						if (i < enemyArmy.length) {
							String name = enemyArmy[i].get(0).getClass().getName()
									.substring(enemyArmy[i].get(0).getClass().getName().lastIndexOf(".") + 1);
							if (enemyArmy[i] != null && enemyArmy[i].size() > 0 && initialArmies[1][i] > 0) {
								// Obtiene el nombre de la clase de la primera unidad en la posición i del
								// ejército enemigo.

								// Si la posición i es menor que 5, se procesa tanto el ejército enemigo como el
								// de la civilización.
								if (i < 4) {
									texto += name + " " + 0 + " " + 0 + " " + name + " " + initialArmies[1][i] + " "
											+ actualNumberUnitsEnemy[i] + "\n";
								}
							} else {
								// Si la posición i es mayor o igual a 5, solo se procesa el ejército enemigo.
								texto += name + " " + 0 + " " + 0 + "\n";
							}
						}
					}
				}
			}

		}

		texto += "***********************************************\n";
		texto += "Cost Army Civilization Cost Army Enemy\n";
		texto += "Food: " + initialCostFleet[0][0] + " Food: " + initialCostFleet[1][0] + "\n";
		texto += "Wood: " + initialCostFleet[0][1] + " Wood: " + initialCostFleet[1][1] + "\n";
		texto += "Iron: " + initialCostFleet[0][2] + " Iron: " + initialCostFleet[1][2] + "\n";
		texto += "***********************************************\n";
		texto += "Losses Army Civilization Losses Army Enemy\n";
		texto += "Food: " + resourcesLooses[0][0] + " Food: " + resourcesLooses[1][0] + "\n";
		texto += "Wood: " + resourcesLooses[0][1] + " Wood: " + resourcesLooses[1][1] + "\n";
		texto += "Iron: " + resourcesLooses[0][2] + " Iron: " + resourcesLooses[1][2] + "\n";
		texto += "***********************************************\n";

		battleSummary += texto;
		return texto;
	}

	// Generar la pelea entre el atacante y el defensor
	public boolean battleSoldiers(ArrayList<MilitaryUnit> attacker, ArrayList<MilitaryUnit> defender, boolean repetirTurno, int turno) {

		// definimos tanto defensor como atacante
		MilitaryUnit attackerSoldier = attacker.get(getRandomSoldier(attacker));
		MilitaryUnit defenderSoldier = defender.get(getRandomSoldier(defender));

		defenderSoldier.takeDamage(attackerSoldier.attack()); // Genramos el ataque

		// Crea los mensaje dependiendo quien ataque, si es par ataca nuestra
		// civilizacion, si es inpar el enemigo
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

//		if (remainingPercentageFleetCivilization() == false && remainingPercentageFleetEnemy() == false) { // Comprueba si nuestra
//																											// civilizacion esta por encima
//																											// del 20%
//			int numeroAleatorioRepetirTurno = random.nextInt(101);
//			if (numeroAleatorioRepetirTurno > attackerSoldier.getChanceAttackAgain()) { // Comprobamos si podra efectuar un nuevo ataque
//				repetirTurno = false;
//			}
//		}

		int numeroAleatorioRepetirTurno = random.nextInt(101);
		if (numeroAleatorioRepetirTurno > attackerSoldier.getChanceAttackAgain()) { // Comprobamos si podra efectuar un nuevo ataque
			repetirTurno = false;
		}

		return repetirTurno;
	}

	// Genera la batalla
	public void battle(Civilization civilization) {
		battleDevelopment = "";
		battleSummary = "";

		boolean battleWin = false;
		boolean battleover = false;
		int turno = getPrimerGolpe();// tiramos a ver quien ataca primero

		// Reseteamos la armadura de nuestros soldados
		resetArmyArmor();

		// Reseteamos los recursos
		for (int i = 0; i < wasteWoodIron.length; i++) {
			wasteWoodIron[i] = 0;
		}

		// Si hay algun prise santificara a todos los soldados
		sanctify();

		while (!battleover) { // Empeza la batalla
			if (remainingPercentageFleetCivilization() == true) { // Comprueba si nuestra civilizacion esta por encima del 20%
				battleover = true;
			}

			if (remainingPercentageFleetEnemy() == true) { // Comprueba si la civilizacion enemiga esta por encima del 20%
				battleover = true;
			}

			battleDevelopment += "****************CHANGE ATTACKER****************\n";

			boolean repetirTurno = true;

			if (turno % 2 == 0) { // Comprueba quien ataca primero
				while (repetirTurno) {
					repetirTurno = battleSoldiers(armies[turno % 2][getCivilizationSoldierGroup(armies[0])],
							armies[(turno + 1) % 2][getSoldierGroup(armies[1])], repetirTurno, turno);
				}
			} else {
				while (repetirTurno) {
					repetirTurno = battleSoldiers(armies[turno % 2][getSoldierGroup(armies[1])],
							armies[(turno + 1) % 2][getSoldierGroup(armies[0])], repetirTurno, turno);
				}
			}

			if (remainingPercentageFleetCivilization() == true) { // Comprueba si nuestra civilizacion esta por encima del 20%
				battleover = true;
			}

			if (remainingPercentageFleetEnemy() == true) { // Comprueba si la civilizacion enemiga esta por encima del 20%
				battleover = true;
			}

			updateResourcesLooses(); // guardamos las perdidas
			desanctify();

			if (turno % 5 == 0) {
				battleSummary(turno);
			}
			turno += 1; // Al terminar añade un turno
		}
		createActualNumberUnitsCivilization(); // Actualizaqmos el numero actual de nuestro ejercito
		createActualNumberUnitsEnemy(); // Actualizaqmos el numero actual del ejercito enemigo
		updateResourcesLooses(); // guardamos la perdidas
		battleSummary(turno);

		// comprobamos quien a tenido menos perdidas
		if (resourcesLooses[0][3] < resourcesLooses[1][3]) { // si hemos perdido menos abremos ganado y nos llevaremos los recursos que se
																// haya generado
			civilization.setWood(civilization.getWood() + wasteWoodIron[0]);
			civilization.setIron(civilization.getIron() + wasteWoodIron[1]);
			battleDevelopment += "******************YOU'VE WON*******************\n";
			battleWin = true;
		} else {
			battleDevelopment += "*****************YOU HAVE LOST*****************\n";
		}

		civilization.setArmy(battleArmyToCivilizationArmy(armies[0]));

		// guarda los caidos
		battleDrops[0] = 0;
		battleDrops[1] = 0;
		battleDrops[2] = 0;
		battleDrops[3] = 0;

		for (int i = 0; i < getCivilizationArmy().length; i++) {
			if (getCivilizationArmy()[i] != null && getCivilizationArmy()[i].size() > 0) {
				if (i < 4) {
					battleDrops[0] += initialArmies[0][i] - actualNumberUnitsCivilization[i];
				} else if (i < 7) {
					battleDrops[1] += initialArmies[0][i] - actualNumberUnitsCivilization[i];
				} else {
					battleDrops[2] += initialArmies[0][i] - actualNumberUnitsCivilization[i];
				}
			}
		}

		for (int i = 0; i < getEnemyArmy().length; i++) {
			battleDrops[3] = 0;
			if (getEnemyArmy()[i] != null && getEnemyArmy()[i].size() > 0) {
				battleDrops[3] += initialArmies[0][i] - actualNumberUnitsEnemy[i];
			}
		}

		// Añde experiencia
		for (int i = 0; i < civilization.getArmy().length; i++) {
			if (civilization.getArmy()[i] != null && civilization.getArmy()[i].size() > 0) {
				for (int j = 0; j < civilization.getArmy()[i].size(); j++) {
					civilization.getArmy()[i].get(j).setExperience(i);
				}
			}
		}
	}

	public ArrayList<MilitaryUnit>[] battleArmyToCivilizationArmy(ArrayList<MilitaryUnit>[] Army) {
		ArrayList<MilitaryUnit>[] battleArmy = new ArrayList[9];

		for (int i = 0; i < Army.length; i++) { // añade el ejercito aliado
			battleArmy[i] = new ArrayList<MilitaryUnit>();
			if (Army[i] != null && Army[i].size() > 0) {
				for (MilitaryUnit unit : Army[i]) {
					battleArmy[i].add(createUnit(unit.getClass().getName().substring(unit.getClass().getName().lastIndexOf(".") + 1),
							unit.getActualArmor(), unit.attack()));
				}
			}

		}

		return battleArmy;

	}

}