package Controller;

import Model.*;
//import Strzelanka.Strzelanka;
import java.util.*;

import View.*;
import Wspolrzedne.*;

import java.awt.event.*;
import java.awt.*;
/**
 * klasa controllera gry
 * @author marcin
 *
 */

public class Controller extends MouseAdapter implements /* MouseMotionListener ,MouseListener, */ KeyListener, Runnable {
	/**
	 * model gry
	 */
	private Model model;
	
	/**
	 * widok gry
	 */
	private View view;
	
	/**
	 * watek kontrolera
	 */
    private Thread kontroluj;

    /**
     * odstep miedzy obliczeniami kontrolera
     */
    private final int DELAY = 50;
    
    /**
     * punkt na ekranie z ktorego wylatuje pocisk gracza
     */
    private final Punkt PUNKT_STRZALU_GRACZA = new Punkt(288f, 240f); 
    
    /**
     * czas jaki musi uplynac po wlaczeniu gry by odpalila sie logika gry
     */
	private final int CZAS_NA_ROZPOCZECIE = 4000;
	
	/**
	 * czas w ktorym rozpoczela sie gra
	 */
	private long czasRozpoczeciaGry;
	
	/**
	* stan gry: 0 - gra sie rozpoczela, ale nie wystartowala jeszcze logika
	* - gra trwa, 2 - pauza, 3 - wygrana, 4 - przegrana 
	*/
	private byte stanGry = 0;
    
	/**
	 * numer aktualnej planszy
	 */
	protected int numerLvl;
	
	/**
	 * stan wcisniecia klawiszy WSAD
	 */
	private boolean wsad[];
	
	/**
	 * aktualnie trwajace zdarzenie myszy
	 */
	private MouseEvent mysz;
	
	/**
	 * kontruktor zalezny od lvl
	 * @param lvl numer planszy
	 */
    public Controller(int lvl){
		
		System.out.println("Start");
		
		numerLvl = lvl;
		czasRozpoczeciaGry = System.currentTimeMillis();
		
		wsad = new boolean[4];
		/*
		switch (lvl) {
		case 1:
			model = new Lvl1Model(this);
			view = new Lvl1View(this);
			break;

		default:
			break;
		}
		*/

		
	}
	
    


    /**
     * zwraca view
     * @return view
     */
	public View getView(){
		return view;
	}
	
	
	/**
	 * funkcja, ktora przygotowuje obiekty dla view poprzez przeliczanie 
	 * wspolrzednych modelowych na ekranowe
	 */
	 
	public void przygotujObiekty(){

			Twor naMape = model.getBohater();
			view.setHp(model.getHP());
			view.setAktualnaBron(model.getAktualnaBron());
			//obliczam przesuniecie miedzy standardowa pozycja bohatera, a jego aktualna
			Point tmp = new Point((int)naMape.getPozycjaNaSzachownicy().x - 4,
					  (int)naMape.getPozycjaNaSzachownicy().y - 4);
			
			Punkt punkt = naMape.getBounds().origin;
			Punkt punkt2 = naMape.punktWlasciwy();
			Point punkt3 = new Point((int)(punkt.x - punkt2.x), (int)(punkt.y - punkt2.y));
			Point punkt4 = new Point((int)(-punkt3.x), (int)(- punkt3.y));
			
			Point przesuniecie = new Point(tmp.x * View.WIELKOSC_POLA, tmp.y * View.WIELKOSC_POLA);
			
			if (punkt4.y > 0){
				punkt4.y -= View.WIELKOSC_POLA;
				tmp.y -= 1;
			}
			
			if (punkt4.x > 0){
				punkt4.x -= View.WIELKOSC_POLA;
				tmp.x -= 1;
			}
			
			tmp = new Point(tmp);
			view.setPoleStartowe(tmp);
			view.setPoczRysowanie(punkt4);
			
			przesuniecie.x += punkt3.x;
			przesuniecie.y += punkt3.y;
			Point nowy = new Point((int)naMape.getBounds().origin.x,(int)naMape.getBounds().origin.y);
			nowy.x -= przesuniecie.x;
			nowy.y -= przesuniecie.y;
			
			//Twor bohater = naMape;
			ArrayList<Obiekt> obiekty = new ArrayList<Obiekt>();
			
			Obiekt bohater = new Obiekt(naMape.getID() + naMape.obrocony(),nowy);
			
			obiekty.add(bohater);
			
			ArrayList<ArrayList<Twor>> naMapeLista = model.getAktywni();
			for (int i = 0; i < naMapeLista.size(); ++i){
				ArrayList<Twor> naMapeIncepcja = naMapeLista.get(i);
				for (int j = 0; j < naMapeIncepcja.size(); ++j){
				
					naMape = naMapeIncepcja.get(j);
					nowy = new Point((int)naMape.getBounds().origin.x - przesuniecie.x,
									(int)naMape.getBounds().origin.y - przesuniecie.y);
					
					obiekty.add(new Obiekt(naMape.getID() + naMape.obrocony(),nowy));
					
				}
			}
			
			view.setObiekty(obiekty);
			
			ArrayList<OpisBron> bronieDlaView = new ArrayList<OpisBron>();
			ArrayList<Bron> bronie = model.getBronie();
			for (int i = 0; i < bronie.size(); ++i){
				Bron bron = bronie.get(i);
				OpisBron nowa = new OpisBron(bron.getIloscAmunicji(), bron.getIloscAmunicjiWMagazynku());
				bronieDlaView.add(nowa);
			}
			
			
			
			view.setBronie(bronieDlaView);
	}
	
	/**
	 * metoda przetwarzajaca wejscia klawiature i mysz
	 */
	private void przetworzWejscia(){
		if (wsad[0] == true)
			model.ruch(0, -1);
		if (wsad[1] == true)
			model.ruch(0, 1);
		if (wsad[2] == true)
			model.ruch(-1, 0);
		if (wsad[3] == true)
			model.ruch(1, 0);
		
		if (mysz != null && mysz.getY() < 576){
			Punkt pocz = new Punkt(mysz.getPoint());
			Punkt kon = (Punkt)PUNKT_STRZALU_GRACZA.clone();
			Wektor wektor = new Wektor(kon, pocz);
			model.strzel(wektor);
		}
			
	}
	
	public void keyPressed(KeyEvent e){
		if (stanGry == 1)
		switch (e.getKeyCode()){
		case KeyEvent.VK_W :
			wsad[0] = true;
			break;
			
		case KeyEvent.VK_S :
			wsad[1] = true;
			break;
			
		case KeyEvent.VK_A :
			wsad[2] = true;
			break;
			
		case KeyEvent.VK_D :
			wsad[3] = true;
			break;
			
		case KeyEvent.VK_R :
			model.przeladujBron();
			break;
			
		case KeyEvent.VK_Q :
			model.zmienBron(false);
			break;
			
		case KeyEvent.VK_E :
			model.zmienBron(true);
			break;
			
		case KeyEvent.VK_1 :
			model.zmienBron(0);
			break;
			
		case KeyEvent.VK_2 :
			model.zmienBron(1);
			break;
			
		case KeyEvent.VK_3 :
			model.zmienBron(2);
			break;
			
			default:
			break;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_P)
			pauzuj();
	}

	public void keyTyped(KeyEvent e){

	}

	public void keyReleased(KeyEvent e) { 
		switch (e.getKeyCode()){
		case KeyEvent.VK_W :
			wsad[0] = false;
			break;
			
		case KeyEvent.VK_S :
			wsad[1] = false;
			break;
			
		case KeyEvent.VK_A :
			wsad[2] = false;
			break;
			
		case KeyEvent.VK_D :
			wsad[3] = false;
			break;
		}
	} 
	
	/**
	 * metoda watku
	 */
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (stanGry != 3) {
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            
            
            if (stanGry == 0){
            	if (System.currentTimeMillis() >= czasRozpoczeciaGry + CZAS_NA_ROZPOCZECIE)
            		stanGry = 1;
            }


            
    		if (stanGry == 1 || stanGry == 2){
    			przetworzWejscia();
    			przygotujObiekty();
    		}
    		
    		if (stanGry == 4){
    			czasRozpoczeciaGry = System.currentTimeMillis();
    			stanGry = 5;
    			
    			switch (numerLvl) {
    			case 1:
    				model = new Lvl1Model(this);
    				break;

    			default:
    				break;
    			} 
    			przygotujObiekty();
    		}
    		
    		if (stanGry == 5 && System.currentTimeMillis() >= CZAS_NA_ROZPOCZECIE + czasRozpoczeciaGry){

    			stanGry = 1;
    		}

    		view.repaint();
            beforeTime = System.currentTimeMillis();
        }
    }

	@Override
	public void mousePressed(MouseEvent e) {
		mysz = e;
		

				
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mysz = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mysz = e;
	}
/*
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
*/
	/**
	 * zwraca stan gry
	 * @return stan gry
	 */
	public byte getStanGry() {
		return stanGry;
	}

	/**
	 * zwraca aktualny lvl
	 * @return numer planszy
	 */
	public int getNumerLvl() {
		return numerLvl;
	}
	
	/**
	 * pauzuje gre lub wznawia gre
	 */
	public void pauzuj(){
		int tmp = stanGry;
		if (tmp == 1)
			stanGry = 2;
		if (tmp == 2)
			stanGry = 1;
		
	}

	/**
	 * ustawia stan gry
	 * @param stanGry nowy stan gry
	 */
	public void setStanGry(byte stanGry) {
		this.stanGry = stanGry;
	}

	/**
	 * ustawia model gry
	 * @param model nowy model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * ustawia view gry
	 * @param view nowy view
	 */
	public void setView(View view) {
		this.view = view;
		this.przygotujObiekty();
        kontroluj = new Thread(this);
        kontroluj.start();
	}
}
