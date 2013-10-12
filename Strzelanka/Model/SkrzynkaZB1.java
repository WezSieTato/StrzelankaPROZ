package Model;

import Wspolrzedne.Punkt;
/**
 * skrzynka z pociskami do miniguna
 * @author marcin
 *
 */
public class SkrzynkaZB1 extends SkrzynkaZAmunicja {

	public SkrzynkaZB1() {
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZB1(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZB1(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		iloscAmunicji = 50;
		rodzajAmunicji = 1;
		id = 12;
		initSize(36, 21);
	}

}
