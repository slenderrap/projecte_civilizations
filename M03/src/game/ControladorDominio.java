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
		
	}
	
	public int crearPartida(String nombre) {
		this.id = datos.crearNuevaPartida(nombre);
		return id;
		
	}

	public ArrayList<String> cargarPartida(int id) {
		
		return datos.cargarPartida(id);
		
	}
	
	
	public Object[][] mostrarPartidasNombre(String nombre) {
		
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
	
	
	public void crearSoldado(MilitaryUnit mUnit) {

		datos.crearSoldado(mUnit);

		
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
		setUpdatable(true);
		this.recursos = new int[] {food,wood,iron,mana};
		System.out.println("y aqui? "+this.updatable);
		
	}
	
	public int[] getUpdatable() {

			return recursos;
			
		}
	
	public ArrayList<String[]> recuperarSoldadosAtaque(){
		return datos.recuperarSoldadosAtaque();
	}

	public ArrayList<String[]> recuperarSoldadosDefense(){
		return datos.recuperarSoldadosAtaque();
	}
	
	public ArrayList<String[]> recuperarSoldadosSpecial(){
		return datos.recuperarSoldadosSpecial();
	}
}