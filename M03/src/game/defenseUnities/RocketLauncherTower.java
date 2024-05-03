package game.defenseUnities;

public class RocketLauncherTower extends DefenseUnit{

	//CONSTRUCTOR por hacer
	public RocketLauncherTower(int armor, int baseDamage) {
		setArmor(ARMOR_ROCKETLAUNCHERTOWER+(getTechnologyDefense()*PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_ROCKETLAUNCHERTOWER+(getTechnologyAttack()*PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
	}
}
