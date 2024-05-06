package game.attackUnities;

public class Swordsman extends AttackUnity{
	
	//CONSTRUCTOR 1 por hacer
	public Swordsman(int armor, int baseDamage) {
		setArmor(ARMOR_SWORDSMAN+(getTechnologyDefense()*PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		setBaseDamage(BASE_DAMAGE_SWORDSMAN+(getTechnologyAttack()*PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
		
	}
	
	//CONSTRUCTOR 2
	public Swordsman() {
		setArmor(ARMOR_SWORDSMAN); //armor por defecto
		setBaseDamage(BASE_DAMAGE_SWORDSMAN); //damage por defecto
		
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
		return FOOD_COST_SWORDSMAN; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_SWORDSMAN;
	}

	public int getIronCost() {
		return IRON_COST_SWORDSMAN;
	}

	public int getManaCost() {
		return MANA_COST_SWORDSMAN;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_SWORDSMAN;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SWORDSMAN;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
	
}
