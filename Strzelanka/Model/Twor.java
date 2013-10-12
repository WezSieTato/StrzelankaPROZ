package Model;

import Wspolrzedne.*;

/**
 * abstrakcyjna klasa obiektu znajdujacego sie na planszy
 * @author marcin
 *
 */
public abstract class Twor implements Cloneable {
	/**
	 * id danego tworu
	 */
	protected int id;
	
	/**
	 * id grupy danego tworu
	 */
	protected byte id_grupy;
	
	/**
	 * pozycja wzgledem szachownicy
	 */
	protected Punkt pozycjaNaSzachownicy;
	
	/**
	 * pozycja na ukladzie wspolrzednym
	 */
	protected Rect frame;
	
	/**
	 * konstruktor inicjalizujacy wlasciwosci i grupe
	 */
	public Twor(){
		this.initGrupy();
		this.initWlasciwosci();
	}
	
	/**
	 * funkcja klonujaca
	 */
	public Object clone(){
		Twor klon = null;
		try {
			klon = (Twor)super.clone();
			klon.id = id;
			klon.id_grupy = id_grupy;
			klon.frame = (Rect)frame.clone();
			klon.pozycjaNaSzachownicy = (Punkt)pozycjaNaSzachownicy.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Blad kopiowania Tworu");
			e.printStackTrace();
		}
		return klon;

	}
	
	/**
	 * konstruktor tworu przyjmujacy jako argument x i y tworu na szachownicy
	 * @param a
	 * @param b
	 */
	public Twor(double a, double b){
		this.pozycjaNaSzachownicy = new Punkt (a, b);
		this.initGrupy();
		this.initWlasciwosci();
		this.umiescNaUkladzie();

	}
	
	/**
	 * konstruktor tworu przyjmujacy jako argument punkt tworu na szachownicy
	 * @param a
	 */
	public Twor(Punkt a){
		this.pozycjaNaSzachownicy = (Punkt)a.clone();
		this.initGrupy();
		this.initWlasciwosci();
		this.umiescNaUkladzie();
	}
	
	/**
	 * funkcja ustawiajaca twor w odpowiedniej pozycji na mapie w ukladzie wspolrzednych
	 */
	public void umiescNaUkladzie(){
		frame.origin = punktWlasciwy();
	}
	
	/**
	 * funkcja na podstawie pozycji na szachownicy i wielkosci
	 * oblicza pozycje tworu w ukladzie wspolrzednych
	 * i zwraca punkt w ktorym powinien sie znajdowac
	 */
	public Punkt punktWlasciwy(){
		double a = Model.WIELKOSC_POLA * pozycjaNaSzachownicy.x;
		double b = Model.WIELKOSC_POLA * pozycjaNaSzachownicy.y;
		a = a +((Model.WIELKOSC_POLA - frame.size.widht) / 2);
		b += (Model.WIELKOSC_POLA - 10 - frame.size.height);
		return (new Punkt(a, b));
	}
	
	/**
	 * ustawianie odpowiednich wlasciwosci dla tworu
	 */
	abstract protected void initWlasciwosci();
	
	/**
	 * przypisanie tworu do odpowiedniej grupy
	 */
	abstract protected void initGrupy();

	/**
	 * na podstawie przyjmowanych parametrow ustawia wielkosc tworu
	 * @param a szerokosc tworu
	 * @param b wysokosc tworu
	 */
	protected void initSize(float a, float b){
		Punkt origin = new Punkt(0,0);
		Size size = new Size(a,b);
		frame = new Rect (origin, size);
	}
	
	/**
	 * zwraca srodek tworu
	 * @return punkt w ktorym znajduje sie srodek tworu
	 */
	public Punkt getCenter(){
		return frame.getCenter();
	}
	/**
	 * ustawia tak twor na ukladzie wspolrzednych by jego
	 * srodek znajdowal sie punkcie a
	 * @param a punkt w ktorym ma obecnie byc srodek
	 */
	public void setCenter(Punkt a){
		frame.setCenter(a);
	}
	
	/**
	 * ustawia tak twor na ukladzie wspolrzednych by jego
	 * srodek znajdowal sie punkcie (a , b)
	 * @param a - x nowego srodka
	 * @param b - y nowego srodka
	 */
	public void setCenter(double a, double b){
		frame.setCenter(a, b);
	}
	
	/**
	 * zwraca kopie frame tworu
	 * @return kopia kwadratu przedstawiajaca dany twor
	 */
	public Rect getBounds(){
		Rect bounds = new Rect(frame);
		return bounds;
	}
	
	/**
	 * zwraca pozycja na szachownicy
	 * @return Punkt - pozycja na szachownicy
	 */
	public Punkt getPozycjaNaSzachownicy(){
		return pozycjaNaSzachownicy;
	}
	
	/**
	 * sprawdza czy twor nie zderza sie z innym
	 * @param a Twor z ktorym sprawdzana jest kolizja
	 * @return boolean mowiacy czy doszlo do kolizji
	 */
	public boolean zderzaSieZ(Twor a){
		if ( frame.przecina(a.getBounds())) return true;
		return a.getBounds().przecina(frame);
	}

	/**
	 * zwraca id tworu
	 * @return byte - id tworu
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * zwraca czy dana twor jest zwrocony w lewa strone
	 * @return true jesli twor jest obrocony w lewa strone
	 */
	public int obrocony(){
		return 0;
	}

	/**
	 * Ustawia pozycje na szachownicy
	 * @param pozycjaNaSzachownicy
	 */
	public void setPozycjaNaSzachownicy(Punkt pozycjaNaSzachownicy) {
		this.pozycjaNaSzachownicy = pozycjaNaSzachownicy;
	}

	/**
	 * zwraca id_grupy
	 * @return id_grupy
	 */
	public byte getId_grupy() {
		return id_grupy;
	}
	
	
}
