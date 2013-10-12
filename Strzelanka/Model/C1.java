package Model;

import Wspolrzedne.Punkt;
/**
 * pocisk wykorzystywany w wyrzutni bomb
 * @author marcin
 *
 */
public class C1 extends Pocisk {

	public C1() {
		// TODO Auto-generated constructor stub
	}

	public C1(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public C1(float a, float b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		id = 6;
		dmg = 100;
		initSize(6, 6);

	}

}
