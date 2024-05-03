package game.attackUnities;

public class Spearman extends AttackUnity{

	//CONSTRUCTOR 1  por hacer
	public Spearman(int armor, int baseDamage) {
		setArmor(ARMOR_SPEARMAN+(getTechnologyDefense()*PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY)*1000/100);
		setBaseDAmage(BASE_DAMAGE_SPEARMAN+(getTechnologyAttack()*PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY)*1000/100);
		setInitialArmor(getArmor());
	}
		
	//CONSTRUCTOR 2
	public Spearman() {
		setArmor(ARMOR_SPEARMAN); //armor por defecto
		setBaseDamage(BASE_DAMAGE_SPEARMAN); //damage por defecto
		
	}
}
