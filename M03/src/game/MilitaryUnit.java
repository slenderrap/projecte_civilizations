package game;

import game.attackUnities.Cannon;
import game.attackUnities.Crossbow;
import game.attackUnities.Spearman;
import game.attackUnities.Swordsman;
import game.defenseUnities.ArrowTower;
import game.defenseUnities.Catapult;
import game.defenseUnities.RocketLauncherTower;
import game.specialUnities.Magician;
import game.specialUnities.Priest;

public interface MilitaryUnit {

	abstract int attack(); //devuelve el poder de ataque de la unidad
	abstract void takeDamage(int recievedDamage); //restará a nuestro armor el daño de recievedDamage
	abstract int getActualArmor(); //devuelve armor actual, después del ataque
	
	abstract int getFoodCost(); //devuelve coste de comida que tiene crear nueva unidad
	abstract int getWoodCost(); //devuelve coste de madera que tiene crear nueva unidad
	abstract int getIronCost(); //devuelve coste de hierro que tiene crear nueva unidad
	abstract int getManaCost(); //devuelve coste de mana que tiene crear nueva unidad
	
	abstract int getChanceGeneratinWaste(); //devuelve probabilidad de generar residuos al ser eliminada
	abstract int getChanceAttackAgain(); //devuelve la probabilidad de volver a atacar
	
	abstract void resetArmor(); //restablece armor al valor initialArmor
	abstract void setExperience(int n); //establece experiencia a nuevo valor n
	abstract int getExperience(); //devuelve experiencia actual
}