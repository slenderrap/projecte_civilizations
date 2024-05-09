package game;

// BORRAR ESTO MÁS TARDE !!!!!!!!!


public class Pruebas {

	public static void main(String[] args) {
		int tecnologia=3;
		int costeFood = 100;
		if (tecnologia==1) {
			System.out.println("el coste es "+ costeFood);
		}
		if (tecnologia>1) {
			for (int i=0; i<tecnologia-1;i++) {
			costeFood += costeFood/100*10;
			System.out.println(costeFood);
			}
			System.out.println("el coste es "+ costeFood);
		}

	}

}
