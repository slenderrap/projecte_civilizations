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
	private ArrayList<MilitaryUnit>[] civilizationArmy; // Almacenar nuestro ejercito
	private ArrayList<MilitaryUnit>[] enemyArmy; // Almacenar ejercito enemigo
	private ArrayList[][] armies = new ArrayList[2][9]; // Almacena los dos ejercitos // --no entiendo para que
	private String battleDevelopment; // Guarda el desarollo de la partida
	private int[][] initialCostFleet = new int[2][3]; // Guarda el coste de los materiales de tanto nuestro como del enemigo // --Creo
														// que no me es util
	private int initialNumberUnitsCivilization, initialNumberUnitsEnemy; // Guarda la catidad inicial de cada ejercito
	private int[] wasteWoodIron = new int[2]; // Residuos generados // --para que
	private int civilizationDrops, enemyDrops; // Para calcular las pérdidas de materiales de cada éjercito
	private int[][] resourcesLooses = new int[2][4]; // Guarda las perdidas de los bandos
	private int[][] initialArmies; // Cuantifica cada tipo de unidad de los ejercitos iniciales
	private int[] actualNumberUnitsCivilization, actualNumberUnitsEnemy; // Cuantifica cada tipo de unidad de los ejercitos
																			// actuales
	private int primerGolpe;

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

	public int isPrimerGolpe() {
		int randomBoolean = new Random(2).nextInt();
		setPrimerGolpe(randomBoolean);
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
		civilizationArmy = new ArrayList[Army.length];

		for (int i = 0; i < Army.length; i++) { // limpia el ArrayList
			civilizationArmy[i].clear();
		}

		actualNumberUnitsCivilization = new int[Army.length];
		initialArmies = new int[0][Army.length];
		int numeroEjercito = 0;

		for (int i = 0; i < Army.length; i++) { // añade el ejercito aliado
			civilizationArmy[i] = new ArrayList<>();
			for (MilitaryUnit unit : Army[i]) {
				civilizationArmy[i].add(createUnit(unit.getClass().getName(), unit.getActualArmor(), unit.attack()));
			}
			initialArmies[0][i] = civilizationArmy[i].size(); // añade la cantidad de soldados
			actualNumberUnitsCivilization[i] = civilizationArmy[i].size();
			numeroEjercito += civilizationArmy[i].size(); // suma el numero de soldados
		}

		setInitialNumberUnitsCivilization(numeroEjercito);
	}

	// Metodo para guardar el ejercito enemigo, el numero de soldados y el total
	public void listArmyEnemy(ArrayList<MilitaryUnit>[] Army) {
		enemyArmy = new ArrayList[Army.length];

		for (int i = 0; i < Army.length; i++) { // limpia el ArrayList
			enemyArmy[i].clear();
		}

		actualNumberUnitsEnemy = new int[Army.length];
		int numeroEjercito = 0;
		initialArmies = new int[1][Army.length];

		for (int i = 0; i < Army.length; i++) { // añade el ejercito enemigo
			enemyArmy[i] = new ArrayList<>();
			for (MilitaryUnit unit : Army[i]) {
				enemyArmy[i].add(createUnit(unit.getClass().getName(), unit.getActualArmor(), unit.attack()));
			}
			initialArmies[1][i] = enemyArmy[i].size(); // añade la cantidad de soldados iniciales
			actualNumberUnitsEnemy[i] = enemyArmy[i].size(); // añade la cantidad de soldados actuales
			numeroEjercito += enemyArmy[i].size(); // suma el numero de soldados
		}

		setInitialNumberUnitsEnemy(numeroEjercito);
	}

	// Metodo para guardar tanto nuestro ejercito como el enemigo
	public void groupArmy() {
		armies[0] = civilizationArmy;
		armies[1] = enemyArmy;
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
			food += civilizationArmy[i].get(0).getFoodCost() * (initialArmies[0][i] - actualNumberUnitsCivilization[i]);
			wood += civilizationArmy[i].get(0).getWoodCost() * (initialArmies[0][i] - actualNumberUnitsCivilization[i]);
			iron += civilizationArmy[i].get(0).getIronCost() * (initialArmies[0][i] - actualNumberUnitsCivilization[i]);
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
			food += enemyArmy[i].get(0).getFoodCost() * (initialArmies[1][i] - actualNumberUnitsCivilization[i]);
			wood += enemyArmy[i].get(0).getWoodCost() * (initialArmies[1][i] - actualNumberUnitsCivilization[i]);
			iron += enemyArmy[i].get(0).getIronCost() * (initialArmies[1][i] - actualNumberUnitsCivilization[i]);
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

		for (float soldados : actualNumberUnitsCivilization) {
			numeroActualCivilization += (float) soldados;
		}

		if (numeroActualCivilization < numeroPerderCivilization) {
			perdido = true;
		}

		return perdido;
	}

	// Para calcular los porcentajes de unidades que quedan respecto los ejércitos
	// enemigos iniciales.
	public boolean remainderPercentageFleetEnemy() {
		boolean perdido = false;
		float numeroPerderEnemy = (float) (initialNumberUnitsEnemy * 0.20);
		float numeroActualEnemy = (float) 0.0;

		for (float soldados : actualNumberUnitsEnemy) {
			numeroActualEnemy += (float) soldados;
		}

		if (numeroActualEnemy < numeroPerderEnemy) {
			perdido = true;
		}

		return perdido;
	}

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

	// crea la pelea entre el atacante y el defensor
	public void battleSoldiers() {

	}

	// genera la batalla
	public boolean battle() {
		boolean battleWin = false;
		int turno = isPrimerGolpe();
		while (battleWin != false) {
			if (turno % 2 == 0) {
				((MilitaryUnit) armies[turno % 2][getCivilizationGroupAttacker(civilizationArmy)].get(0)).attack();
				// batalla armies[turno][pobabilidad de personaje][1]
			}

		}

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
 * ( ) La battalla
 * 
 * ( ) Generar residuos de los soldados caidos
 * 
 * ( ) Comprobar si puede atacar de nuevo
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