package game.defenseUnities;

import game.MilitaryUnit;
import game.Variables;

public abstract class DefenseUnit implements MilitaryUnit, Variables{
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	
	//GETTERS SETTERS
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getInitialArmor() {
		return initialArmor;
	}
	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = getExperience() + experience ;
	}
	public boolean isSanctified() {
		return sanctified;
	}
	public void setSanctified(boolean sanctified) {
		this.sanctified = sanctified;
	}
	
	
	
}