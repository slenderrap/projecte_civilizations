package game.defenseUnities;

public class ArrowTower extends DefenseUnit{
	
	//CONSTRUCTOR por hacer
	public ArrowTower(int armor, int baseDamage) {
		setArmor(ARMOR_ARROWTOWER+(getTechnologyDefense()*PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)*1000/100);

		setBaseDamage(BASE_DAMAGE_ARROWTOWER+(getTechnologyAttack()*PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
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
		return FOOD_COST_ARROWTOWER; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_ARROWTOWER;
	}

	public int getIronCost() {
		return IRON_COST_ARROWTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ARROWTOWER;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_ARROWTOWER;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ARROWTOWER;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
}
