package game;

import java.util.Timer;
import java.util.TimerTask;
import game.ControladorDominio;

public class TimerPersonalizado extends TimerTask implements Variables{
	private ControladorDominio cDominio;
	private int id;
	private int segundo=0;
	private int minuto=0;
	private int[] recursos;
	public TimerPersonalizado(int id) {
		this.id=id;
		cDominio=new ControladorDominio(id);
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
		 		cDominio.actualizarRecursos(food,wood,iron,mana);
		 		
		 	}
	        System.out.println("Timer task started at: "+minuto+"' "+segundo+"\"");
	        
        
	        
        if (minuto==3 && segundo==0) {
        	System.out.println("Batalla");
        	//generar army enemigo
        	//displayear army enemigo en el panel Battleground
        	
        	
        }else if (minuto==4 && segundo==0) {
        	System.out.println("inicio batalla");
        	minuto=0;
        	//efectuar codigo de la batalla
        	//displayear resultados en panel Battleground
		}
        
        
        
	    }
	    public void recursosActualizar(int food, int wood, int iron, int mana, int farm,int carpentery, int smithery, int magicTower) {
	    	
	    	this.recursos = new int[] {food,wood,iron,mana,farm,carpentery,smithery,magicTower};
			
		}

	    
}