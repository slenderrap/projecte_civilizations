package game.attackUnities;

public class Crossbow extends AttackUnity{


	//CONSTRUCTOR 1
	public Crossbow(int armor, int baseDamage) {
		setArmor(armor);
		setBaseDamage(baseDamage);
		setInitialArmor(armor);
	}

	//CONSTRUCTOR 2
	public Crossbow() {
		setArmor(ARMOR_CROSSBOW); //armor por defecto
		setBaseDamage(BASE_DAMAGE_CROSSBOW); //damage por defecto
		
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
		return FOOD_COST_CROSSBOW; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_CROSSBOW;
	}

	public int getIronCost() {
		return IRON_COST_CROSSBOW;
	}

	public int getManaCost() {
		return MANA_COST_CROSSBOW;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CROSSBOW;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CROSSBOW;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}

}
