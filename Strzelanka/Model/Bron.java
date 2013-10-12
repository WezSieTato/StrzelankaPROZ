package Model;

import Wspolrzedne.*;
/**
 * klasa broni
 * @author marcin
 *
 */
public abstract class Bron {
	/**
	 * laczna ilosc dostepnej amunicji
	 */
	protected int iloscAmunicji;
	/**
	 * ilosc amunicja w magazynku
	 */
	protected int iloscAmunicjiWMagazynku;
	/**
	 * ladownosc magazynku
	 */
	protected int ladownoscMagazynku;
	/**
	 * wlasciciel broni
	 */
	protected Strzelajacy wlasciciel;
	/**
	 * predkosc pociskow
	 */
	protected double predkoscPociskow;
	
	
	/**
	 * odstep miedzy strzalami, rzeczywisty = delay * Model.DELAY
	 */
	protected int delay;
	
	/**
	 * czas jaki nie mozna strzelac po ladowaniu
	 */
	protected int czasLadowania;
	
	/**
	 * ile amunicji wlasnie laduje
	 */
	protected int doZaladowania;
	
	/**
	 * czas w ktorym bedzie mozliwe wystrzelenie pocisku
	 */
	protected long czasNastepnejAkcji;
	/**
	 * amunicja z jakiej korzysta bron
	 */
	protected Pocisk amunicja;
	
	/**
	 * zasieg broni
	 */
	protected float zasieg;

	/**
	 * kostruktor
	 */
	public Bron(){
		initWlasciwosci();
	}
	/**
	 * konstruktor
	 * @param a istota strzelajaca korzystajaca z broni
	 */
	public Bron(Strzelajacy a){
		wlasciciel = a;
		initWlasciwosci();
	}
	/**
	 * metoda w ktorej inicjalizuje sie cechy broni
	 */
	protected abstract void initWlasciwosci();

	/**
	 * ustawia wlasciciela
	 * @param wlasciciel
	 */
	void setWlasciciel(Strzelajacy wlasciciel) {
		this.wlasciciel = wlasciciel;
	}
	

	
	/**
	 * sprawdza czy bron jest gotowa do strzalu
	 * @return zwraca true jesli mozna wystrzelic
	 */
	public boolean isGotowy(){
		if(System.currentTimeMillis() >= czasNastepnejAkcji) return true;
		return false;
	}
	
	/**
	 * przeladowywuje bron i wprowadza w bron "spoczynku"
	 */
	public void przeladuj(){
		if (doZaladowania > 0) return;
		int brak = ladownoscMagazynku - iloscAmunicjiWMagazynku;
		if (brak == 0) return;
		
		if (brak >= iloscAmunicji) doZaladowania = iloscAmunicji;
			else doZaladowania = brak;
		
		czasNastepnejAkcji = System.currentTimeMillis() + czasLadowania;

	}
	
	/**
	 * aktualizuje parametry broni jesli minal czas przeladowania
	 */
	public void sprawdzCzyZaladowano(){
		if (doZaladowania > 0 && System.currentTimeMillis() >= czasNastepnejAkcji){
			iloscAmunicji -= doZaladowania;
			iloscAmunicjiWMagazynku += doZaladowania;
			doZaladowania = 0;
		}
	}
	
	/**
	 * zwraca nowo wystrzelony pocisk
	 * @return wystrzelony pocisk
	 */
	public Pocisk wystrzel(){
		sprawdzCzyZaladowano();
		if (isGotowy() && iloscAmunicjiWMagazynku > 0){
			--iloscAmunicjiWMagazynku;
			czasNastepnejAkcji = System.currentTimeMillis() + delay;
			return ((Pocisk)amunicja.clone());
		}
		return null;
	}
	
	/**
	 * ustala w ktorym miejscu ma zaczac nowo wystrzelony pocisk
	 * @return pozycja nowo wystrzelonego pocisku
	 */
	 Punkt ustalPunktWystrzalu(){
		double a, b;
		Rect tmp = wlasciciel.getBounds();
		Punkt punktWystrzalu = wlasciciel.getPunktWystrzalu();
		a = tmp.size.widht * punktWystrzalu.x;
		b = tmp.size.height * punktWystrzalu.y;
		
		a += tmp.origin.x;
		b += tmp.origin.y;
		
		amunicja.setPozycjaNaSzachownicy((Punkt)wlasciciel.getPozycjaNaSzachownicy().clone());
		
		amunicja.setCenter(a, b);
		return (Punkt)amunicja.getCenter().clone();
		
	}
/**
 * zwraca zasieg
 * @return zasieg
 */
	public float getZasieg() {
		return zasieg;
	}
	/**
	 * zwraca iloscAmunicji
	 * @return iloscAmunicji
	 */
	public int getIloscAmunicji() {
		return iloscAmunicji;
	}
	/**
	 * zwraca iloscAmunicjiWMagazynku
	 * @return iloscAmunicjiWMagazynku
	 */	
	public int getIloscAmunicjiWMagazynku() {
		return iloscAmunicjiWMagazynku;
	}
	/**
	 * sprawdza czy bron jest w trakcie ladowania
	 * @return true jesli bron jest przeladowywana
	 */		
	public boolean czyLaduje(){
		if (doZaladowania == 0) return false;
		return true;
	}
	/**
	 * zwraca predkoscPociskow
	 * @return predkoscPociskow
	 */	
	public double getPredkoscPociskow() {
		return predkoscPociskow;
	}
	/**
	 * zwraca amunicja
	 * @return amunicja
	 */	
	public Pocisk getAmunicja() {
		return amunicja;
	}
	/**
	 * dodaje amunicje do zapasu
	 * @param a ilosc nowej amunicji
	 */
	public void dodajAmunicje(int a){
		iloscAmunicji += a;
	}

	
}
