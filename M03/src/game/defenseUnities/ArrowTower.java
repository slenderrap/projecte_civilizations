package game.defenseUnities;

public class ArrowTower extends DefenseUnit{
	
	//CONSTRUCTOR por hacer
	public ArrowTower(int armor, int baseDamage) {
		setArmor(ARMOR_ARROWTOWER+(getTechnologyDefense()*PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_ARROWTOWER+(getTechnologyAttack()*PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
	}
}
