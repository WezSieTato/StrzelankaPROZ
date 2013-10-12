package Wspolrzedne;
/**
 * klasa wektora w przestrzeni 2d
 * @author marcin
 *
 */
public class Wektor {
	/**
	 * skladowa x wektora
	 */
	public double x;
	/**
	 * skladowa y wektora
	 */
	public double y;
	
	public Wektor(double a, double b){
		x = a;
		y = b;
	}
	
	public Wektor(Punkt pocz, Punkt kon){
		x = kon.x - pocz.x;
		y = kon.y - pocz.y; 
	}
	
	/**
	 * dzieli wektor przez dana liczbe
	 * @param a dana liczba
	 */
	public void podziel(double a){
		y = y / a;
		x = x / a;
	}
	/**
	 * mnozy wektos przez a
	 * @param a
	 */
	public void pomnoz(double a){
		y = y * a;
		x = x * a;
	}

	@Override
	public String toString() {
		return "Wektor [x=" + x + ", y=" + y + "]";
	}

}
