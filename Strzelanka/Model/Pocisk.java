package Model;

import Wspolrzedne.Punkt;
/**
 * klasa pocisku ktory jest wystrzeliwany z broni
 * @author marcin
 *
 */
public abstract class Pocisk extends RuchomyTwor implements Cloneable {
/**
 * ilosc obrazen jakie zadaje dany pocisk
 */
	protected int dmg;
	
	public Pocisk(){
		super();
	}
	
	
	public Pocisk(Punkt a) {
		super(a);
	}

	public Object clone(){
		Pocisk klon = null;
		klon = (Pocisk)super.clone();
		klon.dmg = dmg;

		return klon;

	}
	
	public Pocisk(float a, float b) {
		super(a, b);
	}


	@Override
	protected void initGrupy() {
		id_grupy = 3;
	}

	/**
	 * zwraca dmg
	 * @return dmg
	 */
	public int getDmg() {
		return dmg;
	}

	@Override
	protected abstract void initWlasciwosci();


	@Override
	public String toString() {
		return "Pocisk [dmg=" + dmg + ", frame=" + frame + "]";
	}
	

}
