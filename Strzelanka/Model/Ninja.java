package Model;

import Wspolrzedne.Punkt;
/**
 * postac ninjy, przeciwnika z ktorym mierzy sie gracz
 * @author marcin
 *
 */
public class Ninja extends Strzelajacy {

	public Ninja(Punkt a){
		super(a);
	}
	
	public Ninja(float a, float b){
		super(a,b);
	}
	
	protected void initWlasciwosci(){
		id = 2;
		hp = 100;
		initSize(54, 80);
		bron = new RekaNinjy(this);
	}

	@Override
	public Twor umrzyj() {
		int losuj = GeneratorLiczb.losuj();
		if (losuj < 20) return (new ApteczkaMala((Punkt)pozycjaNaSzachownicy.clone()));
		else if (losuj < 40) return (new ApteczkaSrednia((Punkt)pozycjaNaSzachownicy));
		else if (losuj < 60) return (new ApteczkaDuza((Punkt)pozycjaNaSzachownicy));
		
		return null;
	}
	
	

}
