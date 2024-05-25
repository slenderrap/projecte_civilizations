package game;

import java.util.ArrayList;
import java.util.Timer;
import game.TimerPersonalizado;
import bbdd.Datos;


public class ControladorDominio {
	private Datos datos;
	private boolean updatable;
	private int id;
	private TimerPersonalizado timerTask;
	private Timer timer;
	private int[] recursos;

	public ControladorDominio() {
		this.datos = new Datos();
	}
	
public ControladorDominio(int id) {
		this.datos = new Datos(id);
		this.id = id;
		this.updatable=false;
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
	public void setUpdatable(boolean i) {
		this.updatable = i;
	}
	public void iniciarPartida() {
		System.out.println("TimerTask started");
		this.timerTask = new TimerPersonalizado(id);
        //running timer task as daemon thread
        this.timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 500);
		

	}
	
	public void crearSoldado(MilitaryUnit mUnit) {
		System.out.println("aqui1");
		System.out.println(this.updatable);

		System.out.println("aqui2");
		datos.crearSoldado(mUnit);
		System.out.println("aqui3");
		
	}
	
	public void crearConstruccion(int i) {
		
		datos.crearConstruccion(i);
		
	}
	
	public void crearIncrementoTecnologia(int id) {
		datos.crearIncrementoTecnologia(id);
	}
	
	//cuando se actualiza la array que al minuto se subirá
	public void recursosActulizar(int food, int wood, int iron, int mana, int farm,int carpentery, int smithery, int magicTower) {
		timerTask.recursosActualizar(food, wood, iron, mana, farm, carpentery, smithery, magicTower);
	}
	//cuando se sube a la bbdd
	public void actualizarRecursos(int food,int wood,int iron,int mana) {
		datos.actualizarRecursos(food, wood, iron, mana);
		this.recursos = new int[] {food,wood,iron,mana};
		System.out.println("y aqui? "+updatable);
		
	}
	
	public int[] getUpdatable() {
		System.out.println("lo es? "+ updatable);
		if (updatable) {
			setUpdatable(false);
			return recursos;
			
		}
			return null;
		}
	
}