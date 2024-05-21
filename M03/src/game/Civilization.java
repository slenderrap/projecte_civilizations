package game;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import game.attackUnities.AttackUnity;
import game.attackUnities.Cannon;
import game.attackUnities.Crossbow;
import game.attackUnities.Spearman;
import game.attackUnities.Swordsman;
import game.defenseUnities.ArrowTower;
import game.defenseUnities.Catapult;
import game.defenseUnities.DefenseUnit;
import game.defenseUnities.RocketLauncherTower;
import game.specialUnities.Magician;
import game.specialUnities.Priest;
import game.specialUnities.SpecialUnit;
import game.ControladorDominio;

public class Civilization implements Variables{

	private int technologyDefense;
	private int technologyAttack;
	
	private int wood;
	private int iron;
	private int food;
	private int mana;
	
	private int magicTower;
	private int church;
	private int farm;
	private int smithy;
	private int carpentry;
	private ControladorDominio datosDominio;
	private int id;
	private int battles;
	private ArrayList<MilitaryUnit>[] army = new ArrayList[9];
	
	
	
	
	public Civilization(int id) {
		
		
		super();
		
		this.id = id;
		datosDominio = new ControladorDominio(id);
		ArrayList<String> infoPartida = datosDominio.cargarPartida(id);
		this.wood = Integer.parseInt(infoPartida.get(2));
		this.iron = Integer.parseInt(infoPartida.get(3));
		this.food = Integer.parseInt(infoPartida.get(4));
		this.mana = Integer.parseInt(infoPartida.get(5));
		this.magicTower = Integer.parseInt(infoPartida.get(6));
		this.church = Integer.parseInt(infoPartida.get(7));
		this.farm = Integer.parseInt(infoPartida.get(8));
		this.smithy = Integer.parseInt(infoPartida.get(9));
		this.carpentry = Integer.parseInt(infoPartida.get(10));
		this.technologyDefense = Integer.parseInt(infoPartida.get(11));
		this.technologyAttack = Integer.parseInt(infoPartida.get(12));
		this.technologyAttack = Integer.parseInt(infoPartida.get(12));
		System.out.println("aqui si");
		this.army = army;
		
		for (int i=0; i<9;i++) {
			getArmy()[i] = new ArrayList<MilitaryUnit>();
			//System.out.println("hecho el ciudad" +i);
		}
	}
	//GETTERS SETTERS
	public int getTechnologyDefense() {
		return technologyDefense;
	}
	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}
	public int getTechnologyAttack() {
		return technologyAttack;
	}
	public void setTechnologyAttack(int technologyAttack) {
		this.technologyAttack = technologyAttack;
	}
	public int getWood() {
		return wood;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
	public int getIron() {
		return iron;
	}
	public void setIron(int iron) {
		this.iron = iron;
	}
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getMagicTower() {
		return magicTower;
	}
	public void setMagicTower(int magicTower) {
		this.magicTower = magicTower;
	}
	public int getChurch() {
		return church;
	}
	public void setChurch(int church) {
		this.church = church;
	}
	public int getFarm() {
		return farm;
	}
	public void setFarm(int farm) {
		this.farm = farm;
	}
	public int getSmithy() {
		return smithy;
	}
	public void setSmithy(int smithy) {
		this.smithy = smithy;
	}
	public int getCarpentry() {
		return carpentry;
	}
	public void setCarpentry(int carpentry) {
		this.carpentry = carpentry;
	}
	public int getBattles() {
		return battles;
	}
	public void setBattles(int battles) {
		this.battles = battles;
	}
	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}
	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
	
	
	//METODOS CREAR -------------------------------------------
	
	
	//empieza la partida
	
	
	
	//nueva CHURCH
	public void newChurch() throws ResourceException {
		if (food>FOOD_COST_CHURCH && wood>WOOD_COST_CHURCH && iron>IRON_COST_CHURCH) {
			church+=1;
			food-=FOOD_COST_CHURCH;
			wood-=WOOD_COST_CHURCH;
			iron-=IRON_COST_CHURCH;
		}
		else {
			if(food<FOOD_COST_CHURCH){
				throw new ResourceException("Not enough Food!");			
			}		
			if(wood<WOOD_COST_CHURCH) {
				throw new ResourceException("Not enough Wood!");			
			}
			if(iron<IRON_COST_CHURCH) {
				throw new ResourceException("Not enough Iron!");
			}
		}
	}
	
	//nueva MAGIC TOWER
	public void newMagictower() throws ResourceException{
		if (food>FOOD_COST_MAGICTOWER && wood>WOOD_COST_MAGICTOWER && iron>IRON_COST_MAGICTOWER) {
			magicTower+=1;
			food-=FOOD_COST_MAGICTOWER;
			wood-=WOOD_COST_MAGICTOWER;
			iron-=IRON_COST_MAGICTOWER;
		}
		else {
			if(food<FOOD_COST_MAGICTOWER){
				throw new ResourceException("Not enough Food!");			
			}		
			if(wood<WOOD_COST_MAGICTOWER) {
				throw new ResourceException("Not enough Wood!");			
			}
			if(iron<IRON_COST_MAGICTOWER) {
				throw new ResourceException("Not enough Iron!");
			}
		}
	}
	
	//nueva FARM
	public void newFarm() throws ResourceException {
		if (food>FOOD_COST_FARM && wood>WOOD_COST_FARM && iron>IRON_COST_FARM) {
			farm+=1;
			food-=FOOD_COST_FARM;
			wood-=WOOD_COST_FARM;
			iron-=IRON_COST_FARM;
		}
		else {
			if(food<FOOD_COST_FARM){
				throw new ResourceException("Not enough Food!");			
			}		
			if(wood<WOOD_COST_FARM) {
				throw new ResourceException("Not enough Wood!");			
			}
			if(iron<IRON_COST_FARM) {
				throw new ResourceException("Not enough Iron!");
			}
		}
	}
	
	//nueva CARPENTRY
	public void newCarpentry() throws ResourceException {
		if (food>FOOD_COST_CARPENTRY && wood>WOOD_COST_CARPENTRY && iron>IRON_COST_CARPENTRY) {
			carpentry+=1;
			food-=FOOD_COST_CARPENTRY;
			wood-=WOOD_COST_CARPENTRY;
			iron-=IRON_COST_CARPENTRY;
		}
		else {
			if(food<FOOD_COST_CARPENTRY){
				throw new ResourceException("Not enough Food!");			
			}		
			if(wood<WOOD_COST_CARPENTRY) {
				throw new ResourceException("Not enough Wood!");			
			}
			if(iron<IRON_COST_CARPENTRY) {
				throw new ResourceException("Not enough Iron!");
			}
		}
	}

	//nuevo SMITHY
	public void newSmithy() throws ResourceException {
		if (food>FOOD_COST_SMITHY && wood>WOOD_COST_SMITHY && iron>IRON_COST_SMITHY) {
			smithy+=1;
			food-=FOOD_COST_SMITHY;
			wood-=WOOD_COST_SMITHY;
			iron-=IRON_COST_SMITHY;
		}
		else {
			if(food<FOOD_COST_SMITHY){
				throw new ResourceException("Not enough Food!");			
			}		
			if(wood<WOOD_COST_SMITHY) {
				throw new ResourceException("Not enough Wood!");			
			}
			if(iron<IRON_COST_SMITHY) {
				throw new ResourceException("Not enough Iron!");
			}
		}
	}
	
	
	// UPGRADE TECNOLOGIAS --------------------------
	//upgrade DEFENSA
	public void upgradeTechnologyDefense() throws ResourceException {
		int foodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST;
		int woodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST;
		int ironCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;
		
		if (getTechnologyDefense()>1) {
			for (int i=0; i<getTechnologyDefense()-1;i++) {
				foodCost += foodCost/100*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST;
				woodCost += woodCost/100*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST;
				ironCost += ironCost/100*UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST;
			}
		}
		if (food>foodCost && wood>woodCost && iron>ironCost) {
			technologyDefense+=1;
			food-=foodCost;
			wood-=woodCost;
			iron-=ironCost;
		}
		else {
			if(food<foodCost){
				throw new ResourceException("Not enough Food!");			
			}		
			if(wood<woodCost) {
				throw new ResourceException("Not enough Wood!");			
			}
			if(iron<ironCost) {
				throw new ResourceException("Not enough Iron!");
			}
		}
	}
	
	//upgrade ATTACK
	public void upgradeTechnologyAttack() throws ResourceException {
		int foodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST;
		int woodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST;
		int ironCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
		
		if (getTechnologyAttack()>1) {
			for (int i=0; i<getTechnologyAttack()-1;i++) {
				foodCost += foodCost/100*UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST;
				woodCost += woodCost/100*UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST;
				ironCost += ironCost/100*UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST;
			}
		}
		if (food>foodCost && wood>woodCost && iron>ironCost) {
			technologyAttack+=1;
			food-=foodCost;
			wood-=woodCost;
			iron-=ironCost;
		}
		else {
			if(food<foodCost){
				throw new ResourceException("Not enough Food!");			
			}		
			if(wood<woodCost) {
				throw new ResourceException("Not enough Wood!");			
			}
			if(iron<ironCost) {
				throw new ResourceException("Not enough Iron!");
			}
		}
	}
	
	
	
	//CREAR TROPAS ------------------------------
	
	//TROPAS ATTACK
	public void newSwordsman(int n) throws ResourceException {
		int armor =(ARMOR_SWORDSMAN+(getTechnologyDefense()*PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		int baseDamage = (BASE_DAMAGE_SWORDSMAN+(getTechnologyAttack()*PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_SWORDSMAN && wood>WOOD_COST_SWORDSMAN && iron>IRON_COST_SWORDSMAN) {
				army[0].add(new Swordsman(armor, baseDamage));
				food-=FOOD_COST_SWORDSMAN;
				wood-=WOOD_COST_SWORDSMAN;
				iron-=IRON_COST_SWORDSMAN;
			}
			else {
				if(food<FOOD_COST_SWORDSMAN){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_SWORDSMAN) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_SWORDSMAN) {
					throw new ResourceException("Not enough Iron!");
				}
			}			
		}
	}
	
	public void newSpearman(int n) throws ResourceException {
		int armor = (ARMOR_SPEARMAN+(getTechnologyDefense()*PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY)*1000/100);
		int baseDamage = (BASE_DAMAGE_SPEARMAN+(getTechnologyAttack()*PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_SPEARMAN && wood>WOOD_COST_SPEARMAN && iron>IRON_COST_SPEARMAN) {
				army[1].add(new Spearman(armor, baseDamage));
				food-=FOOD_COST_SPEARMAN;
				wood-=WOOD_COST_SPEARMAN;
				iron-=IRON_COST_SPEARMAN;
			}
			else {
				if(food<FOOD_COST_SPEARMAN){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_SPEARMAN) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_SPEARMAN) {
					throw new ResourceException("Not enough Iron!");
				}
			}			
		}
	}
	
	public void newCrossbow(int n) throws ResourceException {
		int armor=(ARMOR_CROSSBOW+(getTechnologyDefense()*PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY)*1000/100);
		int baseDamage=(BASE_DAMAGE_CROSSBOW+(getTechnologyAttack()*PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_CROSSBOW && wood>WOOD_COST_CROSSBOW && iron>IRON_COST_CROSSBOW) {
				army[2].add(new Crossbow(armor, baseDamage));
				food-=FOOD_COST_CROSSBOW;
				wood-=WOOD_COST_CROSSBOW;
				iron-=IRON_COST_CROSSBOW;
			}
			else {
				if(food<FOOD_COST_CROSSBOW){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_CROSSBOW) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_CROSSBOW) {
					throw new ResourceException("Not enough Iron!");
				}
			}			
		}
	}
	
	public void newCannon(int n) throws ResourceException {
		int armor = (ARMOR_CANNON+(getTechnologyDefense()*PLUS_ARMOR_CANNON_BY_TECHNOLOGY)*1000/100);
		int baseDamage = (BASE_DAMAGE_CANNON+(getTechnologyAttack()*PLUS_ATTACK_CANNON_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_CANNON && wood>WOOD_COST_CANNON && iron>IRON_COST_CANNON) {
				army[3].add(new Cannon(armor, baseDamage));
				food-=FOOD_COST_CANNON;
				wood-=WOOD_COST_CANNON;
				iron-=IRON_COST_CANNON;
			}
			else {
				if(food<FOOD_COST_CANNON){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_CANNON) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_CANNON) {
					throw new ResourceException("Not enough Iron!");
				}
			}			
		}
	}
	
	//TROPAS DEFENSE
	public void newArrowTower(int n) throws ResourceException {
		int armor=(ARMOR_ARROWTOWER+(getTechnologyDefense()*PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)*1000/100);
		int baseDamage=(BASE_DAMAGE_ARROWTOWER+(getTechnologyAttack()*PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_ARROWTOWER && wood>WOOD_COST_ARROWTOWER && iron>IRON_COST_ARROWTOWER) {
				army[4].add(new ArrowTower(armor, baseDamage));
				food-=FOOD_COST_ARROWTOWER;
				wood-=WOOD_COST_ARROWTOWER;
				iron-=IRON_COST_ARROWTOWER;
			}
			else {
				if(food<FOOD_COST_ARROWTOWER){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_ARROWTOWER) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_ARROWTOWER) {
					throw new ResourceException("Not enough Iron!");
				}
			}			
		}
	}
	
	public void newCatapult(int n) throws ResourceException {
		int armor =(ARMOR_CATAPULT+(getTechnologyDefense()*PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY)*1000/100);
		int baseDamage=(BASE_DAMAGE_CATAPULT+(getTechnologyAttack()*PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_CATAPULT && wood>WOOD_COST_CATAPULT && iron>IRON_COST_CATAPULT) {
				army[5].add(new Catapult(armor, baseDamage));
				food-=FOOD_COST_CATAPULT;
				wood-=WOOD_COST_CATAPULT;
				iron-=IRON_COST_CATAPULT;
			}
			else {
				if(food<FOOD_COST_CATAPULT){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_CATAPULT) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_CATAPULT) {
					throw new ResourceException("Not enough Iron!");
				}
			}			
		}
	}
	
	public void newRocketLauncherTower(int n) throws ResourceException {
		int armor=(ARMOR_ROCKETLAUNCHERTOWER+(getTechnologyDefense()*PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
		int baseDamage=(BASE_DAMAGE_ROCKETLAUNCHERTOWER+(getTechnologyAttack()*PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_ROCKETLAUNCHERTOWER && wood>WOOD_COST_ROCKETLAUNCHERTOWER && iron>IRON_COST_ROCKETLAUNCHERTOWER) {
				army[6].add(new RocketLauncherTower(armor, baseDamage));
				food-=FOOD_COST_ROCKETLAUNCHERTOWER;
				wood-=WOOD_COST_ROCKETLAUNCHERTOWER;
				iron-=IRON_COST_ROCKETLAUNCHERTOWER;
			}
			else {
				if(food<FOOD_COST_ROCKETLAUNCHERTOWER){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_ROCKETLAUNCHERTOWER) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_ROCKETLAUNCHERTOWER) {
					throw new ResourceException("Not enough Iron!");
				}
			}			
		}
	}
	
	
	//TROPAS SPECIAL
	public void newMagician(int n) throws ResourceException, BuildingException {
		int armor=0;
		int baseDamage=(BASE_DAMAGE_MAGICIAN+(getTechnologyAttack()*PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY)*1000/100);
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_MAGICIAN && wood>WOOD_COST_MAGICIAN && iron>IRON_COST_MAGICIAN && mana>MANA_COST_MAGICIAN && army[7].size()<magicTower) {
				army[7].add(new Magician(armor, baseDamage));
				food-=FOOD_COST_MAGICIAN;
				wood-=WOOD_COST_MAGICIAN;
				iron-=IRON_COST_MAGICIAN;
				mana-=MANA_COST_MAGICIAN;
			}
			else {
				if(food<FOOD_COST_MAGICIAN){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_MAGICIAN) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_MAGICIAN) {
					throw new ResourceException("Not enough Iron!");
				}
				if(army[7].size()>magicTower) {
					throw new BuildingException("Not enough Magic Towers!");						
				}
			}			
		}
	}
	
	public void newPriest(int n) throws ResourceException, BuildingException {
		int armor=0;
		int baseDamage=0;
		for (int i=0;i<n;i++) {
			if (food>FOOD_COST_PRIEST && wood>WOOD_COST_PRIEST && iron>IRON_COST_PRIEST && mana>MANA_COST_PRIEST && army[8].size()<church) {
				army[8].add(new Priest(armor, baseDamage));
				food-=FOOD_COST_PRIEST;
				wood-=WOOD_COST_PRIEST;
				iron-=IRON_COST_PRIEST;
				mana-=MANA_COST_PRIEST;
			}
			else {
				if(food<FOOD_COST_PRIEST){
					throw new ResourceException("Not enough Food!");
				}		
				if(wood<WOOD_COST_PRIEST) {
					throw new ResourceException("Not enough Wood!");			
				}
				if(iron<IRON_COST_PRIEST) {
					throw new ResourceException("Not enough Iron!");
				}
				if(army[8].size()>=church) {
					throw new BuildingException("Not enough Churches!");					
				}
			}			
		}
	}
	
	
	//OTROS METODOS
	public void printStats() {
		
	}
	
	
	//santificar todas las unidades que no estan santificadas
	public void sanctify() {
		if (army[8].size()>0) { //si la cantidad de priest es mayor que 0
			for (int i=0; i<army.length-1; i++) { //se recorre la lista de army menos el ultimo que es la unidad de priests
				for (int j=0; j<(int)army[i].size(); j++) { //se recorre cada personaje de la unidad
					if (army[i].get(j) instanceof AttackUnity) {
						if (((AttackUnity) army[i].get(j)).isSanctified()==false) {
							//poner armadura +7%
							((AttackUnity) army[i].get(j)).setArmor(((AttackUnity) army[i].get(j)).getArmor()+((AttackUnity) army[i].get(j)).getArmor()/100*PLUS_ARMOR_UNIT_SANCTIFIED);
							//poner damage +7%
							((AttackUnity) army[i].get(j)).setBaseDamage(((AttackUnity) army[i].get(j)).getBaseDamage()+((AttackUnity) army[i].get(j)).getBaseDamage()/100*PLUS_ATTACK_UNIT_SANCTIFIED);
							//poner sanctified en true
							((AttackUnity) army[i].get(j)).setSanctified(true);							
						}
					}
					else if (army[i].get(j) instanceof DefenseUnit) {
						if (((DefenseUnit) army[i].get(j)).isSanctified()==false) {
						//poner armadura +7%
						((DefenseUnit) army[i].get(j)).setArmor(((DefenseUnit) army[i].get(j)).getArmor()+((DefenseUnit) army[i].get(j)).getArmor()/100*PLUS_ARMOR_UNIT_SANCTIFIED);
						//poner damage +7%
						((DefenseUnit) army[i].get(j)).setBaseDamage(((DefenseUnit) army[i].get(j)).getBaseDamage()+((DefenseUnit) army[i].get(j)).getBaseDamage()/100*PLUS_ATTACK_UNIT_SANCTIFIED);
						//poner sanctified en true
						((DefenseUnit) army[i].get(j)).setSanctified(true);	
						}
					}
					else if (army[i].get(j) instanceof SpecialUnit) {
						if (((SpecialUnit) army[i].get(j)).isSanctified()==false) {
						//poner armadura +7%
						((SpecialUnit) army[i].get(j)).setArmor(((SpecialUnit) army[i].get(j)).getArmor()+((SpecialUnit) army[i].get(j)).getArmor()/100*PLUS_ARMOR_UNIT_SANCTIFIED);
						//poner damage +7%
						((SpecialUnit) army[i].get(j)).setBaseDamage(((SpecialUnit) army[i].get(j)).getBaseDamage()+((SpecialUnit) army[i].get(j)).getBaseDamage()/100*PLUS_ATTACK_UNIT_SANCTIFIED);
						//poner sanctified en true
						((SpecialUnit) army[i].get(j)).setSanctified(true);	
						}
					}
				}
			}
		}
	}
	
	//desantificar todas las unidades santificadas
	public void desanctify() {
		if (army[8].size()==0) { //si la cantidad de priest es 0
			for (int i=0; i<army.length-1; i++) { //se recorre la lista de army menos el ultimo que es la unidad de priests
				for (int j=0; j<(int)army[i].size(); j++) { //se recorre cada personaje de la unidad
					if (army[i].get(j) instanceof AttackUnity) {
						if (((AttackUnity) army[i].get(j)).isSanctified()==true) {
							//poner armadura +7%
							((AttackUnity) army[i].get(j)).setArmor(((AttackUnity) army[i].get(j)).getArmor()-((AttackUnity) army[i].get(j)).getArmor()/100*PLUS_ARMOR_UNIT_SANCTIFIED);
							//poner damage +7%
							((AttackUnity) army[i].get(j)).setBaseDamage(((AttackUnity) army[i].get(j)).getBaseDamage()-((AttackUnity) army[i].get(j)).getBaseDamage()/100*PLUS_ATTACK_UNIT_SANCTIFIED);
							//poner sanctified en false
							((AttackUnity) army[i].get(j)).setSanctified(false);							
						}
					}
					else if (army[i].get(j) instanceof DefenseUnit) {
						if (((DefenseUnit) army[i].get(j)).isSanctified()==true) {
						//poner armadura +7%
						((DefenseUnit) army[i].get(j)).setArmor(((DefenseUnit) army[i].get(j)).getArmor()-((DefenseUnit) army[i].get(j)).getArmor()/100*PLUS_ARMOR_UNIT_SANCTIFIED);
						//poner damage +7%
						((DefenseUnit) army[i].get(j)).setBaseDamage(((DefenseUnit) army[i].get(j)).getBaseDamage()-((DefenseUnit) army[i].get(j)).getBaseDamage()/100*PLUS_ATTACK_UNIT_SANCTIFIED);
						//poner sanctified en false
						((DefenseUnit) army[i].get(j)).setSanctified(false);	
						}
					}
					else if (army[i].get(j) instanceof SpecialUnit) {
						if (((SpecialUnit) army[i].get(j)).isSanctified()==true) {
						//poner armadura +7%
						((SpecialUnit) army[i].get(j)).setArmor(((SpecialUnit) army[i].get(j)).getArmor()-((SpecialUnit) army[i].get(j)).getArmor()/100*PLUS_ARMOR_UNIT_SANCTIFIED);
						//poner damage +7%
						((SpecialUnit) army[i].get(j)).setBaseDamage(((SpecialUnit) army[i].get(j)).getBaseDamage()-((SpecialUnit) army[i].get(j)).getBaseDamage()/100*PLUS_ATTACK_UNIT_SANCTIFIED);
						//poner sanctified en false
						((SpecialUnit) army[i].get(j)).setSanctified(false);	
						}
					}
				}
			}
		}
	}
}
