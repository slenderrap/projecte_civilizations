package game;

import java.util.Timer;
import java.util.TimerTask;

public class TimerPersonalizado extends TimerTask {
	
	private int segundo=0;
	private int minuto=0;
	    public void run() {
		 	segundo++;
		 	if (segundo==60) {
		 		segundo=0;
		 		minuto++;
		 		
		 		//updates recursos
		 	}
	        System.out.println("Timer task started at: "+minuto+"' "+segundo+"\"");
	        
        
	        
        if (minuto==3 && segundo==0) {
        	System.out.println("Batalla");
        }else if (minuto==4 && segundo==0) {
        	System.out.println("inicio batalla");
        	minuto=0;
		}
        
        
        
	    }

	    
}