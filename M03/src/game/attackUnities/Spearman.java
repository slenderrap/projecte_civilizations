package game.attackUnities;

public class Spearman extends AttackUnity{

	//CONSTRUCTOR 1
	public Spearman(int armor, int baseDamage) {
		setArmor(armor);
		setBaseDamage(baseDamage);
		setInitialArmor(armor);
	}
		
	//CONSTRUCTOR 2
	public Spearman() {
		setArmor(ARMOR_SPEARMAN); //armor por defecto
		setBaseDamage(BASE_DAMAGE_SPEARMAN); //damage por defecto
		
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
		return FOOD_COST_SPEARMAN; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_SPEARMAN;
	}

	public int getIronCost() {
		return IRON_COST_SPEARMAN;
	}

	public int getManaCost() {
		return MANA_COST_SPEARMAN;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_SPEARMAN;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SPEARMAN;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
	
	
}
