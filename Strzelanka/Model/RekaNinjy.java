package Model;
/**
 * Bron z jakiej korzysta ninja
 * @author marcin
 *
 */
public class RekaNinjy extends Bron {

	public RekaNinjy() {
		super();
	}

	public RekaNinjy(Strzelajacy a) {
		super(a);
	}

	@Override
	protected void initWlasciwosci() {
		iloscAmunicji = 30;
		ladownoscMagazynku = 5;
		iloscAmunicjiWMagazynku = ladownoscMagazynku;
		zasieg = 4 * Model.WIELKOSC_POLA;
		czasLadowania = 2000;
		delay = 1000;
		amunicja = new Gwiazda();
		predkoscPociskow = 1;
	}

}
