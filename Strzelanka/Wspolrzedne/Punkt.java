package Wspolrzedne;
import java.awt.Point;
/**
 * klasa odzworujaca punkt na mapie
 * @author marcin
 *
 */
public class Punkt implements Cloneable {
	/**
	 * zmienna na osi poziomej ukladu wspolrzednych
	 */
	public double x;
	/**
	 * zmienna na osi poziomej ukladu wspolrzednych
	 */
	public double y;
	
	/**
	 * konstruktor
	 * @param x polozenie na osi X
	 * @param y polozenie na osi Y
	 */
	public Punkt(double x, double y){
		this.x = x;
		this.y = y;
	}
	/**
	 * konstruktor
	 * @param point punkt klasy Point z biblioteki awt
	 */
	public Punkt(Point point){
		this.x = point.x;
		this.y = point.y;
	}
	/**
	 * funkcja klonujaca
	 */
	public Object clone(){
		Punkt klon = null;
		try {
			klon = (Punkt)super.clone();
			klon.x = x;
			klon.y = y;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return klon;

	}
	
	/**
	 * dodaje dany wektor do punktu
	 * @param a Wektor jaki dodajemy do punktu
	 */
	public void plus(Wektor a){
		x += a.x;
		y += a.y;
	}
	
	/**
	 * dodaje dany wektor do punktu
	 * @param a x wektora
	 * @param b y wektora
	 */
	public void plus(double a, double b){
		x += a;
		y += b;
	}

	/**
	 * sprawdza czy punkt poruszajacy sie danym wektorem minal punkt a
	 * @param a punkt poruszajacy
	 * @param wektor dany wektor
	 * @return true jesli minal juz punkt
	 */
	public boolean czyMinal(Punkt a, Wektor wektor){
	///	if (equals(a)) return true;
		if (wektor.x != 0 && wektor.y != 0) return czyMinalWPrzyblizeniu(a, wektor);
		if ((wektor.x > 0 && a.x <= x ) || (wektor.x < 0 && a.x >= x) || (wektor.x == 0)){
			if ((wektor.y > 0 && a.y <= y ) || (wektor.y < 0 && a.y >= y) || (wektor.y == 0))
				return true;
		}
		return false;
	}

	/**
	 * sprawdza czy punkt poruszajacy sie danym wektorem minal punkt a
	 * przynajmniej po jednej osi
	 * @param a punkt poruszajacy
	 * @param wektor dany wektor
	 * @return true jesli minal juz punkt
	 */
	public boolean czyMinalWPrzyblizeniu(Punkt a, Wektor wektor){
		if (((int)wektor.x > 0 && (int)a.x <= x ) || (wektor.x < 0 && a.x >= x))
				return true;
			if ((wektor.y > 0 && a.y <= y ) || (wektor.y < 0 && a.y >= y))
				return true;
		
		return false;
	}
	
	/**
	 * sprawdza czy ktoras ze skladowych jest ujemna
	 * @return true jesli jakas skladowa jest ujemna
	 */
	public boolean czyUjemne(){
		if (x < 0) return true;
		if (y < 0) return true;
		return false;
	}
	
	/**
	 * sprawdza czy punkt nie wyszedl poza obszar a
	 * @param a obszar w jakim ma sie miescic punkt
	 * @return true jesli sie miesci
	 */
	public boolean czySieMiesci(Size a){
		if (x >= a.widht) return false;
		if (y >= a.height) return false;
		return true;
	}
	/**
	 * funkcja opisujaca obiekt w postaci napisu
	 */
	public String toString() {
		return "Punkt [x=" + x + ", y=" + y + "]";
	}

	/**
	 * zwraca odleglosc miedzy punktem, a punktem a
	 * @param a Punkt 
	 * @return odleglosc miedzy punkietm a punktem a
	 */
	public double odlegloscOd(Punkt a){
		double dx = a.x - this.x;
		double dy = a.y - this.y;
		double dxy = dx*dx + dy*dy;
		dxy = (float) Math.sqrt(dxy);
		return dxy;
	}
	
	/**
	 * sprawdza czy do danego punktu mniejsza jest odleglosc po osi X od osi Y
	 * @param a dany punkt
	 * @return true jesli po osi X jest mniejsza odleglosc niz po Y
	 */
	public boolean czyPoX(Punkt a){
		int dx = Math.abs((int)(a.x - x));
		int dy = Math.abs((int)(a.y -y));
		if (dy > dx) return true;
		return false;
	}
	
	/**
	 * zwraca roznice po osi X miedzy a, a this
	 * @param a
	 * @return roznica po osi X
	 */
	public double roznicaX(Punkt a ){
		return x - a.x;
	}

	/**
	 * zwraca roznice po osi Y miedzy a, a this
	 * @param a
	 * @return roznica po osi Y
	 */	public double roznicaY(Punkt a ){
		return y - a.y;
	}
	
	/**
	 * zwraca w ktorym kierunku po osi X znajduje sie punkt a
	 * @param a
	 * @return kierunek (-1,0,1)
	 */
	public int kierunekX(Punkt a){
		int dx = (int)roznicaX(a);
		if (dx > 0) return 1;
	    if (dx < 0) return -1;
	    return 0;
	}
	
	/**
	 * zwraca w ktorym kierunku po osi Y znajduje sie punkt a
	 * @param a
	 * @return kierunek (-1,0,1)
	 */
	public int kierunekY(Punkt a){
		int dy = (int)roznicaY(a);
		if (dy > 0) return 1;
	    if (dy < 0) return -1;
	    return 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punkt other = (Punkt) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}


}
