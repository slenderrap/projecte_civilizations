package game;

import java.util.ArrayList;

public class Civilization implements MilitaryUnit{

	private int technologyDefense;
	private int technologyAttack;
	
	private int wood;
	private int iron;
	private int food;
	private int mana;
	
	private int magicTower;
	private int church;
	private int farm;
	private int smithy;
	private int carpentry;
	
	private int battles;
	private ArrayList<MilitaryUnit> army = new ArrayList[9]; //el jordi ha hecho esto raro ????
	
	
	//GETTERS SETTERS
	public int getTechnologyDefense() {
		return technologyDefense;
	}
	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}
	public int getTechnologyAttack() {
		return technologyAttack;
	}
	public void setTechnologyAttack(int technologyAttack) {
		this.technologyAttack = technologyAttack;
	}
	public int getWood() {
		return wood;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
	public int getIron() {
		return iron;
	}
	public void setIron(int iron) {
		this.iron = iron;
	}
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getMagicTower() {
		return magicTower;
	}
	public void setMagicTower(int magicTower) {
		this.magicTower = magicTower;
	}
	public int getChurch() {
		return church;
	}
	public void setChurch(int church) {
		this.church = church;
	}
	public int getFarm() {
		return farm;
	}
	public void setFarm(int farm) {
		this.farm = farm;
	}
	public int getSmithy() {
		return smithy;
	}
	public void setSmithy(int smithy) {
		this.smithy = smithy;
	}
	public int getCarpentry() {
		return carpentry;
	}
	public void setCarpentry(int carpentry) {
		this.carpentry = carpentry;
	}
	public int getBattles() {
		return battles;
	}
	public void setBattles(int battles) {
		this.battles = battles;
	}
	public ArrayList<MilitaryUnit> getArmy() {
		return army;
	}
	public void setArmy(ArrayList<MilitaryUnit> army) {
		this.army = army;
	}
	
	
	//METODOS
	
	void newChurch() {
		
	}
	
	void newMagictower(){
		
	}
	
	void newFarm() {
		
	}
	
	void newCarpentry() {
		
	}
	
	void newSmithy() {
		
	}
	
	void upgradeTechnologyDefense() {
		
	}
	
	void upgradeTechnologyAttack() {
		
	}
	
	
	
	//CREAR TROPAS
	void newSwordsman(int n) {
		
	}
	
	void newSpearman(int n) {
		
	}
	
	void newCrossbow(int n) {
		
	}
	
	void newCannon(int n) {
		
	}
	
	void newArrowTower(int n) {
		
	}
	
	void newCatapult(int n) {
		
	}
	
	void newRocketLauncher(int n) {
		
	}
	
	void newMagician(int n) {
		
	}
	
	void newPriest(int n) {
		
	}
	
	
	//OTROS METODOS
	void printStats() {
		
	}
	
}
