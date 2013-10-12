package Model;

import Wspolrzedne.*;

/**
 * Klasa twory ktory moze przemieszczac sie na mapie
 * @author marcin
 *
 */
public abstract class RuchomyTwor extends Twor implements Cloneable {

	
	public RuchomyTwor() {
		super();
	}

	public Object clone(){
		RuchomyTwor klon = null;
	
			klon = (RuchomyTwor)super.clone();


		return klon;

	}
	
	/**
	 * konstruktor
	 * @param a Punkt na szachownicy w ktorym ma sie znajdowac
	 */
	public RuchomyTwor(Punkt a){
		super(a);
	}
	
	/**
	 * konstruktor
	 * @param a x punktu w ktorym ma sie znajdowac twor na szachownicy
	 * @param b y punktu w ktorym ma sie znajdowac twor na szachownicy
	 */
	public RuchomyTwor(float a, float b){
		super(a, b);
	}

}
