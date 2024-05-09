package game;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
	private ArrayList<MilitaryUnit>[] civilizationArmy; // Almacenar nuestro ejercito
	private ArrayList<MilitaryUnit>[] enemyArmy; // Almacenar ejercito enemigo
	private ArrayListt<MilitaryUnit>[][] armies = new ArrayList[2][9]; // Almacena los dos ejercitos
	private String battleDevelopment; // Guarda el desarollo de la partida
	private int[][] initialCostFleet = new int[2][3]; // Guarda el coste de los materiales de tanto nuestro como del enemigo
	private int initialNumberUnitsCivilization, initialNumberUnitsEnemy; // Guarda la catidad inicial de cada ejercito
	private int[] wasteWoodIron = new int[2]; // Residuos generados
	private int civilizationDrops, enemyDrops; // Para calcular las pérdidas de materiales de cada éjercito
	private int[][] resourcesLooses = new int[2][4]; // Guarda las perdidas de los bandos
	private int[][] initialArmies = new int[2][9]; // Cuantifica cada tipo de unidad de los ejercitos iniciales
	private int[] actualNumberUnitsCivilization, actualNumberUnitsEnemy = new int[9]; // Cuantifica cada tipo de unidad de los ejercitos
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

	public ArrayList[][] getArmies() {
		return armies;
	}

	public void setArmies(ArrayList[][] armies) {
		this.armies = armies;
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

	// METODOS BATTALLA4
	public

	public void initInitialArmies() { // Para inicializar el array initialArmies y poder calcular los reportes.

	}

	public void updateResourcesLooses() { // Para generar el array de pérdidas.

	}

	public int fleetResourceCost(ArrayList<MilitaryUnit> army) { // Para calcular costes de los ejércitos.

	}

	public int initialFleetNumber(ArrayList<MilitaryUnit> army) { // Para calcular el número de unidades iniciales de cada ejército

	}

	public int remainderPercentageFleet(ArrayList<MilitaryUnit> army) { // Para calcular los porcentajes de unidades que quedan respecto los
																		// ejércitos iniciales.

	}

	public int getGroupDefender(ArrayList<MilitaryUnit> army) { // para que dado un ejército, nos devuelva el grupo defensor, 0-3 en el caso
																// de la flota enemiga, 0-8 en el caso del ejército de nuestra civilización.

	}

	public int getCivilizationGroupAttacker() { // Que nos servirán para escoger el grupo atacante tanto de nuestra civilización
												// como del ejército enemigo.

	}

	public int getEnemyGroupAttacker() { // Que nos servirán para escoger el grupo atacante tanto de nuestra civilización
											// como del ejército enemigo.

	}

	public void resetArmyArmor() { // que restablecerá las armaduras de nuestro ejército.

	}
}
