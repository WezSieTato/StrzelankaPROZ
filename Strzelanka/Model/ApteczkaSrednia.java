package Model;

import Wspolrzedne.Punkt;
/**
 * Apteczka ktora zwraca srednia ilosc hp
 * @author marcin
 *
 */
public class ApteczkaSrednia extends Apteczka {

	public ApteczkaSrednia() {
		// TODO Auto-generated constructor stub
	}

	public ApteczkaSrednia(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public ApteczkaSrednia(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		hp = 30;
		id = 9;
		initSize(40, 31);
	}

}
