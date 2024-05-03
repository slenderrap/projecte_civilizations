package game.attackUnities;

public class Cannon extends AttackUnity{

	//CONSTRUCTOR 1  por hacer
	public Cannon(int armor, int baseDamage) {
		setArmor(ARMOR_CANNON+(getTechnologyDefense()*PLUS_ARMOR_CANNON_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_CANNON+(getTechnologyAttack()*PLUS_ATTACK_CANNON_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
	}
		
	//CONSTRUCTOR 2
	public Cannon() {
		setArmor(ARMOR_CANNON); //armor por defecto
		setBaseDamage(BASE_DAMAGE_CANNON); //damage por defecto
		
	}
}
