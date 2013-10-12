package Model;

import Wspolrzedne.Punkt;
/**
 * Apteczka ktora zwraca duzo hp
 * @author marcin
 *
 */
public class ApteczkaDuza extends Apteczka {

	public ApteczkaDuza() {
		// TODO Auto-generated constructor stub
	}

	public ApteczkaDuza(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public ApteczkaDuza(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		hp = 50;
		id = 10;
		initSize(64, 48);
	}

}
