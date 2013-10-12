package Model;

import Wspolrzedne.Punkt;

public abstract class Strzelajacy extends Istota {
	
	/**
	 * miejsce z ktorego startuje wystrzelony pocisk wzgledem framu postaci, wartosci od 0 do 1
	 */
	protected Punkt punktWystrzalu;
	/**
	 * bron jaka posluguje sie istota
	 */
	protected Bron bron;

	/**
	 * konstruktor
	 * @param a Punkt na szachownicy w ktorym ma sie znajdowac
	 */
	public Strzelajacy(Punkt a) {
		super(a);
		initPunktuWystrzalu();
		
	}

	/**
	 * konstruktor
	 * @param a x punktu w ktorym ma sie znajdowac twor na szachownicy
	 * @param b y punktu w ktorym ma sie znajdowac twor na szachownicy
	 */
	public Strzelajacy(float a, float b) {
		super(a, b);
		initPunktuWystrzalu();
	}

	/**
	 * inicjalizuje punkt wystrzalu
	 */
	protected void initPunktuWystrzalu(){
		punktWystrzalu = new Punkt (0.5f, 0.2f);
	}


	/**
	 * metoda przeladowywujaca bron
	 */
	public void przeladujBron(){
		bron.przeladuj();
	}
	
	/**
	 * przypisanie tworu do odpowiedniej grupy
	 */
	protected void initGrupy() {
		id_grupy = 4;
		
	}
	
	
	/**
	 * sprawdza czy dana istota jest w zasiegu strzalu
	 * @param a dana istota
	 * @return true jesli jest w zasiegu strzalu
	 */
	public boolean czyWZasiegu(Istota a){
		if (ustalPunktWystrzalu().odlegloscOd(a.getCenter()) <= bron.getZasieg())
			return true;
		return false;
	}
	
	/**
	 * sprawdza czy posiada amunicje w magazynku
	 * @return true jesli amunicja jest w magazynku
	 */
	public boolean czyMaAmunicjeWMagazynku(){
		if (bron.getIloscAmunicjiWMagazynku() > 0) return true;
		return false;
	}

	/**
	 * zwraca punkt wystrzalu
	 * @return punktWystrzalu
	 */
	public Punkt getPunktWystrzalu() {
		return punktWystrzalu;
	}

	/**
	 * wystrzeliwuje pocisk
	 * @return wystrzelony pocisk
	 */
	public Pocisk wystrzel(){
		return bron.wystrzel();
	}

	@Override
	public String toString() {
		return "Strzelajacy [hp=" + hp + ", frame=" + frame + "]";
	}
	
	/**
	 * ustala punkt na ukladzie wspolrzednych w ktorym powinien
	 * pojawic sie nowo wystrzelony pocisk
	 * @return punkt wystrzalu na ukladzie wspolrzednych
	 */
	public Punkt ustalPunktWystrzalu(){
		return bron.ustalPunktWystrzalu();
	}
	
	/**
	 * zwraca zasieg broni
	 * @return zasieg broni
	 */
	public float getZasieg(){
		return bron.getZasieg();
	}

	/**
	 * ustawia bron
	 * @param bron
	 */
	public void setBron(Bron bron) {
		this.bron = bron;
	}

	/**
	 * zwraca bron
	 * @return bron
	 */
	public Bron getBron() {
		return bron;
	}
}
