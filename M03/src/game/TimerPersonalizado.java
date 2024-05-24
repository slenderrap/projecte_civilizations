package game;

import java.util.Timer;
import java.util.TimerTask;

public class TimerPersonalizado extends TimerTask implements Variables{
	
	private int segundo=0;
	private int minuto=0;
	    public void run() {
		 	segundo++;
		 	if (segundo==60) {
		 		segundo=0;
		 		minuto++;
		 		
		 		//updates recursos
		 		//GENERACION FOOD
//				if (civilization.getFarm()==0) {
//					civilization.setFood(civilization.getFood()+CIVILIZATION_FOOD_GENERATED);
//				}
//				else {
//					civilization.setFood(civilization.getFood()+(civilization.getFarm()*CIVILIZATION_FOOD_GENERATED_PER_FARM));
//				}
//				
//				//GENERACION WOOD
//				if (civilization.getCarpentry()==0) {
//					civilization.setWood(civilization.getWood()+CIVILIZATION_WOOD_GENERATED);
//				}
//				else {
//					civilization.setWood(civilization.getWood()+(civilization.getCarpentry()*CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY));
//				}
//				
//				//GENERACION IRON
//				if (civilization.getSmithy()==0) {
//					civilization.setIron(civilization.getIron()+CIVILIZATION_IRON_GENERATED);
//				}
//				else {
//					civilization.setIron(civilization.getIron()+(civilization.getSmithy()*CIVILIZATION_IRON_GENERATED_PER_SMITHY));
//				}
//				
//				//GENERACION MANA
//				if (civilization.getMagicTower()>0) {
//					civilization.setMana(civilization.getMana()+CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
//				}
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

	    
}