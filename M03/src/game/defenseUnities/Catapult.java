package game.defenseUnities;

public class Catapult extends DefenseUnit{

	//CONSTRUCTOR  por hacer
	public Catapult(int armor, int baseDamage) {
		setArmor(ARMOR_CATAPULT+(getTechnologyDefense()*PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_CATAPULT+(getTechnologyAttack()*PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)*1000/100);
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
		return FOOD_COST_CATAPULT; //falta tener en cuenta incremetos de tecnlogia
	}

	public int getWoodCost() {
		return WOOD_COST_CATAPULT;
	}

	public int getIronCost() {
		return IRON_COST_CATAPULT;
	}

	public int getManaCost() {
		return MANA_COST_CATAPULT;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CATAPULT;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CATAPULT;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}


}
