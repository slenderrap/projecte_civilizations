package front;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import game.Civilization;
import game.MilitaryUnit;
import game.ResourceException;
import game.Variables;
import game.attackUnities.Cannon;
import game.attackUnities.Crossbow;
import game.attackUnities.Spearman;
import game.attackUnities.Swordsman;

public class Main implements Variables{
	static private ArrayList<MilitaryUnit>[] enemyArmy = new ArrayList[4];
	//private int[] enemyArray = new int[4];
	static Civilization civilization = new Civilization();
	
	
	public static void createEnemyArmy() {
		
		//LIMPIAR SI HAY ARMADA ANTERIOR
		for (int i=0; i<4;i++) {
			enemyArmy[i].clear();
		}
		//CALCULAR RECURSOS ACTUALES
		int enemyFood=0;
		int enemyWood=0;
		int enemyIron=0;
		
		if (civilization.getBattles()==0){
			enemyFood= FOOD_BASE_ENEMY_ARMY;
			enemyWood = WOOD_BASE_ENEMY_ARMY;
			enemyIron = IRON_BASE_ENEMY_ARMY;
		}
		if (civilization.getBattles()>0) {
			enemyFood= FOOD_BASE_ENEMY_ARMY+(FOOD_BASE_ENEMY_ARMY/100*ENEMY_FLEET_INCREASE*civilization.getBattles());
			enemyWood = WOOD_BASE_ENEMY_ARMY+(WOOD_BASE_ENEMY_ARMY/100*ENEMY_FLEET_INCREASE*civilization.getBattles());
			enemyIron = IRON_BASE_ENEMY_ARMY+(IRON_BASE_ENEMY_ARMY/100*ENEMY_FLEET_INCREASE*civilization.getBattles());
		}
		
		//CREAR TROPAS
		int arrayPorcientos[] = {35,25,20,20};
		while(enemyFood>8000 && enemyWood>3000 && enemyIron>50) { //mientras haya recursos para comprar el más barato(swordsman)
			
			//SE SACA EL TIPO DE UNIDAD A CREAR
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
			
			//SE CREA EL TIPO DE UNIDAD
			if (tipo==0 && enemyFood>8000 && enemyWood>3000 && enemyIron>50) {
				enemyArmy[0].add(new Swordsman());
				enemyFood-=8000;
				enemyWood-=3000;
				enemyIron-=50;
				System.out.println("SE HA CREADO SWORDSMAN");
			}
			if (tipo==1 && enemyFood>5000 && enemyWood>6500 && enemyIron>50) {
				enemyArmy[1].add(new Spearman());
				enemyFood-=5000;
				enemyWood-=6500;
				enemyIron-=50;
				System.out.println("SE HA CREADO SPEARMAN");
			}
			if (tipo==2 && enemyWood>45000 && enemyIron>7000) {
				enemyArmy[2].add(new Crossbow());
				enemyWood-=45000;
				enemyIron-=7000;
				System.out.println("SE HA CREADO CROSSBOW");
			}
			if (tipo==3 && enemyWood>30000 && enemyIron>15000) {
				enemyArmy[3].add(new Cannon());
				enemyWood-=30000;
				enemyIron-=15000;
				System.out.println("SE HA CREADO CANNON");
			}
			
			//FEEDBACK STATS
			System.out.println("RECURSOS QUEDAN -------");
			System.out.println("food "+ enemyFood);
			System.out.println("wood "+ enemyWood);
			System.out.println("iron "+ enemyIron);
			
			
		}
		System.out.println("SALIDAAAAAA");
		
		
		
		
	}


	public static void main(String[] args) {
		//INSTANCIAS ARMADA ENEMIGA
		for (int i=0; i<4;i++) {
			enemyArmy[i] = new ArrayList<MilitaryUnit>();
			System.out.println("hecho el enemy" +i);
		}
		createEnemyArmy();
		
		//TASK GENERACION DE RECURSOS
		TimerTask taskRecursos = new TimerTask() { //creamos task
			public void run() {
				
				//GENERACION FOOD
				if (civilization.getFarm()==0) {
					civilization.setFood(civilization.getFood()+CIVILIZATION_FOOD_GENERATED);
				}
				else {
					civilization.setFood(civilization.getFood()+(civilization.getFarm()*CIVILIZATION_FOOD_GENERATED_PER_FARM));
				}
				
				//GENERACION WOOD
				if (civilization.getCarpentry()==0) {
					civilization.setWood(civilization.getWood()+CIVILIZATION_WOOD_GENERATED);
				}
				else {
					civilization.setWood(civilization.getWood()+(civilization.getCarpentry()*CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY));
				}
				
				//GENERACION IRON
				if (civilization.getSmithy()==0) {
					civilization.setIron(civilization.getIron()+CIVILIZATION_IRON_GENERATED);
				}
				else {
					civilization.setIron(civilization.getIron()+(civilization.getSmithy()*CIVILIZATION_IRON_GENERATED_PER_SMITHY));
				}
				
				//GENERACION MANA
				if (civilization.getMagicTower()>0) {
					civilization.setMana(civilization.getMana()+CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
				}
//				
//				System.out.println("food "+civilization.getFood());
//				System.out.println("wood "+civilization.getWood());
//				System.out.println("iron "+civilization.getIron());
//				System.out.println("mana "+civilization.getMana());
//				System.out.println("-------------");
				
//				civilization.setFarm(1);
//				civilization.setCarpentry(1);
//				civilization.setSmithy(1);
//				civilization.setMagicTower(1);
				
			}
		};
		
		//TIMER 
		Timer timer = new Timer(); //creamos timer
		//timer.schedule(taskRecursos, 10000, 60000); //repetir cada 60seg, a partir del primer 10seg
		timer.schedule(taskRecursos, 5000, 5000); //repetir cada 5seg, a partir del primer 10seg
		
		

		//VentanaPartida v = new VentanaPartida();
		
		System.out.println("eloooo");
		
		
	}
}
