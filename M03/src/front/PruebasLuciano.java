package front;

import java.util.ArrayList;

import game.Battle;
import game.Civilization;
import game.MilitaryUnit;
import game.Variables;
import game.attackUnities.Cannon;
import game.attackUnities.Crossbow;
import game.attackUnities.Spearman;
import game.attackUnities.Swordsman;
import game.defenseUnities.Catapult;
import game.specialUnities.Magician;
import game.specialUnities.Priest;

public class PruebasLuciano implements Variables {
	static private ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[4];
	static Civilization civilization = new Civilization();

	public static void createEnemyArmy() {

		// LIMPIAR SI HAY ARMADA ANTERIOR
		for (int i = 0; i < 4; i++) {
			enemyArmy[i].clear();
		}
		// CALCULAR RECURSOS ACTUALES
		int enemyFood = 0;
		int enemyWood = 0;
		int enemyIron = 0;

		if (civilization.getBattles() == 0) {
			enemyFood = FOOD_BASE_ENEMY_ARMY;
			enemyWood = WOOD_BASE_ENEMY_ARMY;
			enemyIron = IRON_BASE_ENEMY_ARMY;
		}
		if (civilization.getBattles() > 0) {
			enemyFood = FOOD_BASE_ENEMY_ARMY + (FOOD_BASE_ENEMY_ARMY / 100 * ENEMY_FLEET_INCREASE * civilization.getBattles());
			enemyWood = WOOD_BASE_ENEMY_ARMY + (WOOD_BASE_ENEMY_ARMY / 100 * ENEMY_FLEET_INCREASE * civilization.getBattles());
			enemyIron = IRON_BASE_ENEMY_ARMY + (IRON_BASE_ENEMY_ARMY / 100 * ENEMY_FLEET_INCREASE * civilization.getBattles());
		}

		// CREAR TROPAS
		int arrayPorcientos[] = { 35, 25, 20, 20 };
		while (enemyFood > 8000 && enemyWood > 3000 && enemyIron > 50) { // mientras haya recursos para comprar el más barato(swordsman)

			// SE SACA EL TIPO DE UNIDAD A CREAR
			int total = 0;
			for (int i = 0; i < arrayPorcientos.length; i++) {
				total += arrayPorcientos[i];
			}
			int aleatorio = (int) (Math.random() * total);

			total = 0;
			int tipo = 0;
			for (int i = 0; i < arrayPorcientos.length; i++) {
				total += arrayPorcientos[i];
				if (total > aleatorio) {
					tipo = i;
					break;
				}
			}

			// SE CREA EL TIPO DE UNIDAD
			if (tipo == 0 && enemyFood > 8000 && enemyWood > 3000 && enemyIron > 50) {
				enemyArmy[0].add(new Swordsman());
				enemyFood -= 8000;
				enemyWood -= 3000;
				enemyIron -= 50;
				System.out.println("SE HA CREADO SWORDSMAN");
			}
			if (tipo == 1 && enemyFood > 5000 && enemyWood > 6500 && enemyIron > 50) {
				enemyArmy[1].add(new Spearman());
				enemyFood -= 5000;
				enemyWood -= 6500;
				enemyIron -= 50;
				System.out.println("SE HA CREADO SPEARMAN");
			}
			if (tipo == 2 && enemyWood > 45000 && enemyIron > 7000) {
				enemyArmy[2].add(new Crossbow());
				enemyWood -= 45000;
				enemyIron -= 7000;
				System.out.println("SE HA CREADO CROSSBOW");
			}
			if (tipo == 3 && enemyWood > 30000 && enemyIron > 15000) {
				enemyArmy[3].add(new Cannon());
				enemyWood -= 30000;
				enemyIron -= 15000;
				System.out.println("SE HA CREADO CANNON");
			}

			// FEEDBACK STATS
			System.out.println("RECURSOS QUEDAN -------");
			System.out.println("food " + enemyFood);
			System.out.println("wood " + enemyWood);
			System.out.println("iron " + enemyIron);

		}
	}

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

		// INSTANCIAS ARMADA ENEMIGA
		for (int i = 0; i < 4; i++) {
			enemyArmy[i] = new ArrayList<MilitaryUnit>();
			System.out.println("hecho el enemy" + i);
		}
		createEnemyArmy();

//		System.out.println(civilization.getArmy());

		for (ArrayList<MilitaryUnit> army : civilization.getArmy()) {
			for (MilitaryUnit persona : army) {

				System.out.println(persona.getClass().getName().substring(persona.getClass().getName().lastIndexOf(".") + 1));
			}
		}

		Battle.listArmyCivilization(civilization.getArmy());
		Battle.listArmyEnemy(enemyArmy);
		Battle.groupArmy(civilization.getArmy(), enemyArmy);

		Battle.battle(civilization);
	}

}
