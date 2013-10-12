package Model;
/**
 * bron o sredniej szybkosc i sredniej mocy
 * ale o duzym zasiegu
 * @author marcin
 *
 */
public class Rewolwer extends Bron {

	public Rewolwer() {
		// TODO Auto-generated constructor stub
	}

	public Rewolwer(Strzelajacy a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		iloscAmunicji = 18;
		ladownoscMagazynku = 6;
		iloscAmunicjiWMagazynku = ladownoscMagazynku;
		zasieg = 5 * Model.WIELKOSC_POLA;
		czasLadowania = 1000;
		delay = 500;
		amunicja = new A1();
		predkoscPociskow = 1.5;

	}

}
