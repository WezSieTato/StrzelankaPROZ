package Model;

import Wspolrzedne.Punkt;

/**
 * pocisk wykorzystywany w rewolwerze
 * @author marcin
 *
 */public class A1 extends Pocisk {

	public A1() {
		super();
	}

	public A1(Punkt a) {
		super(a);
	}

	public A1(float a, float b) {
		super(a, b);
	}


	protected void initWlasciwosci() {
		//super.initWlasciwosci();
		id = 4;
		dmg = 25;
		initSize(3, 3);
	}

}
