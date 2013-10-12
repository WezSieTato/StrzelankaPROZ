package Model;

import Wspolrzedne.Punkt;
/**
 * Apteczka ktora zwraca malo hp
 * @author marcin
 *
 */
public class ApteczkaMala extends Apteczka {

	public ApteczkaMala() {
		// TODO Auto-generated constructor stub
	}

	public ApteczkaMala(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public ApteczkaMala(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		hp = 10;
		id = 8;
		initSize(20, 16);
	}

}
