package Model;
import Wspolrzedne.*;

public class Bohater extends Strzelajacy {
	private int maxHp;
	
	public Bohater(Punkt a){
		super(a);
		
	}
	
	public Bohater(float a, float b){
		super(a,b);
	}
	
	protected void initWlasciwosci(){
		maxHp = 100;
		id = 0;
		hp = 50;
		initSize(44, 87);
	//	bron = new Minigun(this);
	}


	protected void initGrupy() {
		id_grupy = 0;
		
	}
	
	public void sprawdzCzyZaladowano(){
		bron.sprawdzCzyZaladowano();
	}
	
	public void ulecz(Apteczka apteczka){
		hp += apteczka.getHp();
		if (hp > maxHp) hp = maxHp;
	}

	@Override
	public Twor umrzyj() {
		// TODO Auto-generated method stub
		return null;
	}



}
