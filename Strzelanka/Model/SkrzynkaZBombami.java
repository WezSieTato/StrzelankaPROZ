package Model;

import Wspolrzedne.Punkt;
/**
 * skrzynka z pociskami do wyrzutni bomb
 * @author marcin
 *
 */
public class SkrzynkaZBombami extends SkrzynkaZAmunicja {

	public SkrzynkaZBombami() {
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZBombami(double a, double b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	public SkrzynkaZBombami(Punkt a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		iloscAmunicji = 10;
		rodzajAmunicji = 2;
		id = 13;
		initSize(75, 18);
	}

}
