package Model;
/**
 * bron o malyn zasiegu i malej mocy
 * ale o duzej szybkosci
 * @author marcin
 *
 */
public class Minigun extends Bron {

	public Minigun() {
		super();
	}

	public Minigun(Strzelajacy a) {
		super(a);
	}

	@Override
	protected void initWlasciwosci() {
		// TODO Auto-generated method stub	
	iloscAmunicji = 200;
	ladownoscMagazynku = 20;
	iloscAmunicjiWMagazynku = ladownoscMagazynku;
	zasieg = 3 * Model.WIELKOSC_POLA;
	czasLadowania = 2000;
	delay = 100;
	amunicja = new B1();
	predkoscPociskow = 2;
	}

}
