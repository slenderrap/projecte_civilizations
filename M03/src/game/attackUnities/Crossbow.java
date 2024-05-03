package game.attackUnities;

public class Crossbow extends AttackUnity{

	//CONSTRUCTOR 1 por hacer
		public Crossbow(int armor, int baseDamage) {
			setArmor(ARMOR_CROSSBOW+(getTechnologyDefense()*PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY)*1000/100);
			setBaseDAmage(BASE_DAMAGE_CROSSBOW+(getTechnologyAttack()*PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY)*1000/100);
			setInitialArmor(getArmor());
		}
		
	//CONSTRUCTOR 2
	public Crossbow() {
		setArmor(ARMOR_CROSSBOW); //armor por defecto
		setBaseDamage(BASE_DAMAGE_CROSSBOW); //damage por defecto
		
	}
}
