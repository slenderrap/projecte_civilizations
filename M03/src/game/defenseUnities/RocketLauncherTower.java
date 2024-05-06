package game.defenseUnities;

public class RocketLauncherTower extends DefenseUnit{

	//CONSTRUCTOR por hacer
	public RocketLauncherTower(int armor, int baseDamage) {
		setArmor(ARMOR_ROCKETLAUNCHERTOWER+(getTechnologyDefense()*PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_ROCKETLAUNCHERTOWER+(getTechnologyAttack()*PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
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
		return FOOD_COST_ROCKETLAUNCHERTOWER; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_ROCKETLAUNCHERTOWER;
	}

	public int getIronCost() {
		return IRON_COST_ROCKETLAUNCHERTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ROCKETLAUNCHERTOWER;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
}
