package Model;

public class WyrzutniaBomb extends Bron {

	public WyrzutniaBomb() {
		// TODO Auto-generated constructor stub
	}

	public WyrzutniaBomb(Strzelajacy a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initWlasciwosci() {
		iloscAmunicji = 0;
		ladownoscMagazynku = 5;
		iloscAmunicjiWMagazynku = 3;
		zasieg = 8 * Model.WIELKOSC_POLA;
		czasLadowania = 3000;
		delay = 3000;
		amunicja = new C1();
		predkoscPociskow = 1;


	}

}
