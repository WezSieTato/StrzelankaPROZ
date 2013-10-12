package View;

/**
 * klasa ktora bedzie opisywac parametry broni gracza
 * @author marcin
 *
 */
public class OpisBron {

	/**
	 * ilosc pociskow w postaci textowej
	 */
	private String iloscPociskow;
	/**
	 * ilosc pociskow w magazynku w  postaci textowej
	 */
	private String iloscWMagazynku;
	
	/**
	 * 
	 * @param a iloscPociskow
	 * @param b iloscWMagazynku
	 */
	public OpisBron(int a, int b) {
			iloscPociskow = "" + a;
			iloscWMagazynku = "" + b;
	}
/**
 * zwraca iloscpociskow
 * @return iloscpociskow
 */
	public String getIloscPociskow() {
		return iloscPociskow;
	}
	/**
	 * zwraca iloscWMagazynku
	 * @return iloscWMagazynku
	 */
	public String getIloscWMagazynku() {
		return iloscWMagazynku;
	}




}
