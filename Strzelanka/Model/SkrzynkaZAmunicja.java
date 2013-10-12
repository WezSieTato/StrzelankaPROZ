package Model;

import Wspolrzedne.Punkt;
/**
 * skrzynka z amunicja ktora gracz moze podniesc i doladowac bron
 * @author marcin
 *
 */
public abstract class SkrzynkaZAmunicja extends Twor {
	protected int iloscAmunicji;
	protected int rodzajAmunicji;
	
	

	public SkrzynkaZAmunicja() {
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZAmunicja(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZAmunicja(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void initGrupy() {
		id_grupy = 2;
	}
/**
 * zwraca ilosc amunicji
 * @return iloscAmunicji
 */
	public int getIloscAmunicji() {
		return iloscAmunicji;
	}
/**
 * zwraca rodzajAmunicji
 * @return rodzajAmunicji
 */
	public int getRodzajAmunicji() {
		return rodzajAmunicji;
	}

}
