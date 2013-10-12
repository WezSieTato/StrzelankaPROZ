package Model;
import Wspolrzedne.*;
/**
 * klasa "zyjacego" tworu poruszajacego sie po mapie
 * @author marcin
 *
 */
public abstract class Istota extends RuchomyTwor {
	/**
	 * ilosc zycie w przypadku potworow gracza
	 */
	protected int hp;
	/**
	 * czy postac jest zwrocona w prawa strone
	 */
	private boolean wPrawo;
	
	/**
	 * czy postac porusza sie w danej chwili
	 */
	private boolean wRuchu;
	
	public Istota(Punkt a){
		super(a);
		wPrawo = true;
	}
	
	public Istota(float a, float b){
		super(a, b);
		wPrawo = true;
		wRuchu = false;
	}
	
	/**
	 * zwraca czy istota zyje
	 * @return true jesli zyje
	 */
	public boolean isAlive(){
		if (hp > 0) return true;
		return false;
	}
	
	/**
	 * odejmuje istocie okreslona ilosc HP i zwraca czy nadal zyje
	 * @param dmg
	 * @return true jesli  istota nadal zyje
	 */
	public boolean addDmg(int dmg){
		hp -= dmg;
		return isAlive();
	}
	
	@Override
	public int obrocony(){
		if (wPrawo) return 0;
		return 1;
	}
	
	/** 
	 * wykonuje ruch na szachownicy o wektor[a,b], 
	 * @param a dozwolone wartosci <-1,1> 
	 * @param b dozwolone wartosci <-1,1> 
	 * @return zwraca true jesli ruch sie powiodl
	 */
	public boolean ruch(int a, int b){
      if (a == -1) wPrawo = false;
      if (a == 1) wPrawo = true;
      
      pozycjaNaSzachownicy.x += a;
      pozycjaNaSzachownicy.y += b;
      
      frame.origin.x += a * Model.WIELKOSC_POLA;
      frame.origin.y += b * Model.WIELKOSC_POLA;
      
      return true;
      
	}
	
	/**
	 * gdy dana postac umiera, losuje czy wypada z niej jakas rzecz i ja zwraca jesli tak
	 * @return rzecz ktora wypada po smierci danej istoty
	 */
	public abstract Twor umrzyj();
	
	/**
	 * zwraca hp
	 * @return hp
	 */
	public int getHP(){
		return hp;
	}

	/**
	 * zwraca czy dana postac jest w ruchu
	 * @return true jesli w ruchu
	 */
	public boolean iswRuchu() {
		return wRuchu;
	}

	/**
	 * ustawia zmienna w wRuchu
	 * @param wRuchu
	 */
	public void setwRuchu(boolean wRuchu) {
		this.wRuchu = wRuchu;
	}

	/**
	 * zwraca zmienna wPrawo
	 * @return wPrawo
	 */
	public boolean iswPrawo() {
		return wPrawo;
	}

	/**
	 * ustawia zmienna wPrawo
	 * @param wPrawo
	 */
	public void setwPrawo(boolean wPrawo) {
		this.wPrawo = wPrawo;
	}

	@Override
	public String toString() {
		return "Istota [hp=" + hp + ", id=" + id + ", id_grupy=" + id_grupy
				+ ", pozycjaNaSzachownicy=" + pozycjaNaSzachownicy + "]";
	}
}
