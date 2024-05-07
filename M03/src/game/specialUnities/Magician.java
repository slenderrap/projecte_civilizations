package game.specialUnities;

public class Magician extends SpecialUnit{

	//CONSTRUCTOR
	public Magician(int armor, int baseDamage) {
		setArmor(armor);
		setBaseDamage(baseDamage);
		setInitialArmor(armor);
		
	}
	
	
	//METODOS -----------

	public int attack() {
		return getBaseDamage();
	}

	public void takeDamage(int recievedDamage) {
		setArmor(getArmor()-recievedDamage);
		
	}
	
	public int getActualArmor() {
		return getArmor(); // ??? por qué no se usa getArmor?
	}

	public int getFoodCost() {
		return FOOD_COST_MAGICIAN; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_MAGICIAN;
	}

	public int getIronCost() {
		return IRON_COST_MAGICIAN;
	}

	public int getManaCost() {
		return MANA_COST_MAGICIAN;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_MAGICIAN;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_MAGICIAN;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
}
