package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import bbdd.Datos;


public class ControladorDominio {
	private Datos datos;
	private int id;

	public ControladorDominio() {
		this.datos = new Datos();
	}
	public ControladorDominio(int id) {
		this.datos = new Datos();
		this.id = id;
	}
	
	public int crearPartida(String nombre) {
		
		this.id = datos.crearNuevaPartida(nombre);
		return id;
		
	}

	public ArrayList<String> cargarPartida(int id) {
		
		return datos.cargarPartida(id);
		
	}
	
	
	public ArrayList<String[]> mostrarPartidasNombre(String nombre) {
		
		return datos.mostrarPartidasNombre(nombre);
		
		
	}
	
	public void borrarPartida(int id) {
		datos.borrarPartida(id);
	}
	
	public int getId() {
		return id;
	}
	public void iniciarPartida() {
		System.out.println("TimerTask started");
		TimerPersonalizado timerTask = new TimerPersonalizado();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
		

	}
	
	
	
}