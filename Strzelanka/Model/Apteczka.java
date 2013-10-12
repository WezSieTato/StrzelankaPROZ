package Model;

import Wspolrzedne.Punkt;

public abstract class Apteczka extends Twor {
	/**
	 * ilosc zycia jaka odnawia apteczka
	 */
	protected int hp;


	public Apteczka() {
		// TODO Auto-generated constructor stub
	}


	public Apteczka(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public Apteczka(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected void initGrupy() {
		id_grupy = 1;
	}

	/**
	 * zwraca hp istoty
	 * @return hp
	 */
	public int getHp() {
		return hp;
	}
	


}
