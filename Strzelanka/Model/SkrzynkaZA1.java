package Model;

import Wspolrzedne.Punkt;
/**
 * skrzynka z pociskami do rewolwera
 * @author marcin
 *
 */
public class SkrzynkaZA1 extends SkrzynkaZAmunicja {

	public SkrzynkaZA1() {
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZA1(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZA1(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		iloscAmunicji = 12;
		rodzajAmunicji = 0;
		id = 11;
		initSize(30, 20);
	}

}
