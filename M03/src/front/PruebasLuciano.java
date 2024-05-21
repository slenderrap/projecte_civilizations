package front;

import java.util.ArrayList;

import game.Battle;
import game.Civilization;
import game.MilitaryUnit;
import game.Variables;
import game.attackUnities.Cannon;
import game.attackUnities.Crossbow;
import game.attackUnities.Swordsman;
import game.defenseUnities.Catapult;
import game.specialUnities.Magician;
import game.specialUnities.Priest;

public class PruebasLuciano implements Variables {

	public static void main(String[] args) {
		Civilization civilization = new Civilization();
		Battle Battle = new Battle();

		civilization.setWood(99);
		civilization.setIron(99);
		civilization.setFood(99);
		civilization.setMana(99);

		System.out.println(civilization.getIron());

		civilization.getArmy()[0].add(new Swordsman());
		civilization.getArmy()[0].add(new Swordsman());
		civilization.getArmy()[2].add(new Crossbow());
		civilization.getArmy()[2].add(new Crossbow());
		civilization.getArmy()[3].add(new Cannon());
		civilization.getArmy()[3].add(new Cannon());
		civilization.getArmy()[3].add(new Cannon());
		civilization.getArmy()[5].add(new Catapult(BASE_DAMAGE_CATAPULT, ARMOR_CATAPULT));
		civilization.getArmy()[5].add(new Catapult(BASE_DAMAGE_CATAPULT, ARMOR_CATAPULT));
		civilization.getArmy()[7].add(new Magician(BASE_DAMAGE_MAGICIAN, 1));
		civilization.getArmy()[8].add(new Priest(0, 1));

//		System.out.println(civilization.getArmy());

		for (ArrayList<MilitaryUnit> army : civilization.getArmy()) {
			for (MilitaryUnit persona : army) {

				System.out.println(persona.getClass().getName().substring(persona.getClass().getName().lastIndexOf(".") + 1));
			}
		}

		Battle.listArmyCivilization(civilization.getArmy());
		Battle.listArmyEnemy(civilization.getArmy());
		Battle.groupArmy();

		Battle.battle();
	}

}
