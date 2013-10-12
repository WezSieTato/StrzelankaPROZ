package Model;

import Wspolrzedne.Punkt;
/**
 * pocisk wykorzystywany w minigunie
 * @author marcin
 *
 */
public class B1 extends Pocisk {

	public B1() {
		// TODO Auto-generated constructor stub
	}

	public B1(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public B1(float a, float b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}
	
	protected void initWlasciwosci() {
	//	super.initWlasciwosci();
		id = 5;
		dmg = 10;
		initSize(2, 2);
	}

}
