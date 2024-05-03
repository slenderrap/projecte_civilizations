package game.attackUnities;

public class Swordsman extends AttackUnity{
	
	//CONSTRUCTOR 1 por hacer
	public Swordsman(int armor, int baseDamage) {
		setArmor(ARMOR_SWORDSMAN+(getTechnologyDefense()*PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_SWORDSMAN+(getTechnologyAttack()*PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
		
	}
	
	//CONSTRUCTOR 2
	public Swordsman() {
		setArmor(ARMOR_SWORDSMAN); //armor por defecto
		setBaseDamage(BASE_DAMAGE_SWORDSMAN); //damage por defecto
		
	}
	
}
