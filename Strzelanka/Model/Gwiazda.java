package Model;
/**
 * pociski jakimi strzela ninja
 */
import Wspolrzedne.Punkt;

public class Gwiazda extends Pocisk {

	public Gwiazda() {
	}

	public Gwiazda(Punkt a) {
		super(a);
	}

	public Gwiazda(float a, float b) {
		super(a, b);
	}

	@Override
	protected void initWlasciwosci() {
		id = 7;
		dmg = 10;
		initSize(20, 20);
	}

}
