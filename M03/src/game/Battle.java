package game;

import java.util.ArrayList;
import java.util.Random;

import game.attackUnities.AttackUnity;
import game.defenseUnities.DefenseUnit;
import game.specialUnities.SpecialUnit;

public class Battle implements Variables {
	private ArrayList<MilitaryUnit>[] civilizationArmy; // Almacenar nuestro ejercito
	private ArrayList<MilitaryUnit>[] enemyArmy; // Almacenar ejercito enemigo
	private MilitaryUnit[][] armies; // Almacena los dos ejercitos
	private String battleDevelopment; // Guarda el desarollo de la partida
	private int[][] initialCostFleet = new int[2][3]; // Guarda el coste de los materiales de tanto nuestro como del enemigo // Creo
														// que no me es util
	private int initialNumberUnitsCivilization, initialNumberUnitsEnemy; // Guarda la catidad inicial de cada ejercito
	private int[] wasteWoodIron = new int[2]; // Residuos generados
	private int civilizationDrops, enemyDrops; // Para calcular las pérdidas de materiales de cada éjercito
	private int[][] resourcesLooses = new int[2][4]; // Guarda las perdidas de los bandos
	private int[][] initialArmies; // Cuantifica cada tipo de unidad de los ejercitos iniciales
	private int[] actualNumberUnitsCivilization, actualNumberUnitsEnemy; // Cuantifica cada tipo de unidad de los ejercitos
																			// actuales
	private boolean primerGolpe;

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

	public MilitaryUnit[][] getArmies() {
		return armies;
	}

	public void setArmies(ArrayList[][] armies) {
		this.armies = (MilitaryUnit[][]) armies;
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

	public boolean isPrimerGolpe() {
		boolean randomBoolean = new Random().nextBoolean();
		setPrimerGolpe(randomBoolean);
		return primerGolpe;
	}

	public void setPrimerGolpe(boolean primerGolpe) {
		this.primerGolpe = primerGolpe;
	}

	// METODOS BATTALLA

	public void agruparEjercito() { // Metodo para guardar tanto nuestro ejercito como el enemigo
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				armies[i] = civilizationArmy[i].toArray(new MilitaryUnit[0]);
				listaEjercitoCivilizacion(civilizationArmy);
			} else {
				armies[i] = enemyArmy[i].toArray(new MilitaryUnit[0]);
				listaEjercitoEnemigo(enemyArmy);
			}
		}
	}

	public void listaEjercitoCivilizacion(ArrayList<MilitaryUnit>[] Army) { // Metodo para guardar nuestro ejercito, el numero de soldados y
																			// el total

		actualNumberUnitsCivilization = new int[Army.length];
		civilizationArmy = new ArrayList[Army.length];
		initialArmies = new int[0][Army.length];
		int numeroEjercito = 0;
		for (int i = 0; i < Army.length; i++) {
			civilizationArmy[i] = new ArrayList<MilitaryUnit>(Army[i]); // añade la Array
			initialArmies[0][i] = civilizationArmy[i].size(); // añade la cantidad de soldados
			actualNumberUnitsCivilization[i] = civilizationArmy[i].size();
			numeroEjercito += civilizationArmy[i].size(); // suma el numero de soldados
		}
		setInitialNumberUnitsCivilization(numeroEjercito);
	}

	public void listaEjercitoEnemigo(ArrayList<MilitaryUnit>[] Army) { // Metodo para guardar el ejercito enemigo, el numero de soldados y
																		// el total
		actualNumberUnitsEnemy = new int[Army.length];
		int numeroEjercito = 0;
		enemyArmy = new ArrayList[Army.length];
		initialArmies = new int[1][Army.length];
		for (int i = 0; i < Army.length; i++) {
			enemyArmy[i] = new ArrayList<MilitaryUnit>(Army[i]); // añade la Array
			initialArmies[1][i] = enemyArmy[i].size(); // añade la cantidad de soldados iniciales
			actualNumberUnitsEnemy[i] = enemyArmy[i].size(); // añade la cantidad de soldados actuales
			numeroEjercito += enemyArmy[i].size(); // suma el numero de soldados
		}
		setInitialNumberUnitsEnemy(numeroEjercito);
	}

	public void updateResourcesLooses() { // Para generar el array de pérdidas.
		int food = 0;
		int wood = 0;
		int iron = 0;

		for (int i = 0; i < actualNumberUnitsCivilization.length; i++) {// no esta terminado
			food += civilizationArmy[i][0].getFoodCost() * actualNumberUnitsCivilization[i];
			wood += civilizationArmy[i][0].getWoodCost() * actualNumberUnitsCivilization[i];
			iron += civilizationArmy[i][0].getIronCost() * actualNumberUnitsCivilization[i];
		}
		for (int i = 0; i < actualNumberUnitsEnemy.length; i++) {// no esta terminado
			food += enemyArmy[i][0].getFoodCost() * actualNumberUnitsEnemy[i];
			wood += enemyArmy[i][0].getWoodCost() * actualNumberUnitsEnemy[i];
			iron += enemyArmy[i][0].getIronCost() * actualNumberUnitsEnemy[i];
		}
	}

	public boolean remainderPercentageFleetCivilization() { // Para calcular los porcentajes de unidades que quedan respecto los
		// ejércitos iniciales.
		boolean perdido = false;
		float numeroPerderCivilization = (float) (initialNumberUnitsCivilization * 0.20);
		float numeroActualCivilization = 0;

		for (int soldados : actualNumberUnitsCivilization) {
			numeroActualCivilization += soldados;
		}

		return perdido;
	}

	public boolean remainderPercentageFleetEnemy() { // Para calcular los porcentajes de unidades que quedan respecto los
		// ejércitos iniciales.
		boolean perdido = false;
		float numeroPerderEnemy = (float) (initialNumberUnitsEnemy * 0.20);
		float numeroActualEnemy = 0;

		for (int soldados : actualNumberUnitsEnemy) {
			numeroActualEnemy += soldados;
		}
		return perdido;
	}

	public int getGroupDefender(ArrayList<MilitaryUnit>[] army) { // para que dado un ejército, nos devuelva el grupo defensor, 0-3 en el
																	// caso
																	// de la flota enemiga, 0-8 en el caso del ejército de nuestra
																	// civilización.
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

}
