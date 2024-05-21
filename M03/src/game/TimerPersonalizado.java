package game;

import java.util.Timer;
import java.util.TimerTask;

public class TimerPersonalizado extends TimerTask {
	
	private int i=0;
	 @Override
	    public void run() {
		 	i++;
	        System.out.println("Timer task started at: "+i);

	    }

	    
}