package game;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import game.ControladorDominio;

public class TimerPersonalizado extends TimerTask implements Variables{
	private ControladorDominio cDominio;
	private int id;
	private int segundo=0;
	private int minuto=0;
	private int[] recursos;
	private boolean updateable;
	private boolean crearArmy=false;
	private boolean crearBatalla=false;
	public TimerPersonalizado(int id) {
		this.id=id;
		cDominio=new ControladorDominio(id);
		
	}
	    public int getSegundo() {
		return segundo;
	}
	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
		public void run() {
	    	
		 	segundo++;
		 	if (segundo==60) {
		 		int food = recursos[0];
		 		int wood = recursos[1];
		 		int iron = recursos[2];
		 		int mana = recursos[3];
		 		int farm = recursos[4];
		 		int carpentery = recursos[5];
		 		int smithery = recursos[6];
		 		int magicTower = recursos[7];
		 		
		 		segundo=0;
		 		minuto++;
		 			
		 		
		 		
		 		//updates recursos
		 		
		 		
		 		//GENERACION FOOD
				if (farm==0) {
					food+=CIVILIZATION_FOOD_GENERATED;
				}
				else {
					food+=farm*CIVILIZATION_FOOD_GENERATED_PER_FARM;
				}

//				//GENERACION WOOD
				if (carpentery==0) {
					wood+=CIVILIZATION_WOOD_GENERATED;
				}
				else {
					wood+=carpentery*CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY;
				}

//				//GENERACION IRON
				
				if (smithery==0) {
					iron+=CIVILIZATION_IRON_GENERATED;
				}
				else {
					iron+=smithery*CIVILIZATION_IRON_GENERATED_PER_SMITHY;
				}
//				//GENERACION MANA
				if (magicTower>0) {
					mana+=CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER;
				}
				setUpdateable(true);
		 		cDominio.actualizarRecursos(food,wood,iron,mana);
		 		recursos = new int[] {food,wood,iron,mana,farm,carpentery,smithery,magicTower};
		 		
		 	}
	        System.out.println("Timer task started at: "+minuto+"' "+segundo+"\"");
	        
        
	        
        if (minuto==3 && segundo==0) {
        	setCrearArmy(true);
        	
        }else if (minuto==4 && segundo==0) {

        	setCrearBatalla(true);
        	minuto=0;
        	//checkear que hay mas de un soldado en el array
        	//efectuar codigo de la batalla
        	//displayear resultados en panel Battleground
		}
        
        
        
	    }
	    public void recursosActualizar(int food, int wood, int iron, int mana, int farm,int carpentery, int smithery, int magicTower) {
	    	
	    	this.recursos = new int[] {food,wood,iron,mana,farm,carpentery,smithery,magicTower};
	    	
		}
	    
	    
	    public int[] nuevosRecursos() {
	    	
	    	
	    	int[] recursosEnviar = new int[4];
	    	recursosEnviar[0]= recursos[0];
	    	System.out.println(recursosEnviar[0]);
	    	recursosEnviar[1]= recursos[1];
	    	recursosEnviar[2]= recursos[2];
	    	recursosEnviar[3]= recursos[3];
	    	
	    	return recursosEnviar;

	    }
	    
	    public boolean getUpdateable() {
			return updateable;
		}
	    public void setUpdateable(boolean updateable) {
			this.updateable = updateable;
		}
		public boolean getCrearArmy() {
			return crearArmy;
		}
		public void setCrearArmy(boolean crearArmy) {
			this.crearArmy = crearArmy;
		}
		public boolean getCrearBatalla() {
			return crearBatalla;
		}
		public void setCrearBatalla(boolean crearBatalla) {
			this.crearBatalla = crearBatalla;
		}
	    
}