package game.attackUnities;

public class Cannon extends AttackUnity{

	//CONSTRUCTOR 1  por hacer
	public Cannon(int armor, int baseDamage) {
		setArmor(ARMOR_CANNON+(getTechnologyDefense()*PLUS_ARMOR_CANNON_BY_TECHNOLOGY)*1000/100);
		setBaseDamage(BASE_DAMAGE_CANNON+(getTechnologyAttack()*PLUS_ATTACK_CANNON_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
	}
		
	//CONSTRUCTOR 2
	public Cannon() {
		setArmor(ARMOR_CANNON); //armor por defecto
		setBaseDamage(BASE_DAMAGE_CANNON); //damage por defecto
		
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
		return FOOD_COST_CANNON; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_CANNON;
	}

	public int getIronCost() {
		return IRON_COST_CANNON;
	}

	public int getManaCost() {
		return MANA_COST_CANNON;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CANNON;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CANNON;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
}
