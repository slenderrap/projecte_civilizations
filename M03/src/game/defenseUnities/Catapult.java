package game.defenseUnities;

public class Catapult extends DefenseUnit{

	//CONSTRUCTOR  por hacer
	public Catapult(int armor, int baseDamage) {
		setArmor(ARMOR_CATAPULT+(getTechnologyDefense()*PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_CATAPULT+(getTechnologyAttack()*PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
	}
	
}
