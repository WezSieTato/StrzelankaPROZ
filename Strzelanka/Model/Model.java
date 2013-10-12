package Model;
import Wspolrzedne.*;
import Controller.Controller;
import java.util.*;

/**
 * abstrakcyjna klasa modelu gry
 * @author marcin
 *
 */

public abstract class Model implements Runnable {
	
	/**
	 * controller gry
	 */
	private Controller controller;
	
	/**
	 * watek modelu
	 */
	private Thread logika;
	
	/**
	 * odstep miedzy przetwarzaniem logiki
	 */
	private final int DELAY = 5;


	/**
	 * szachownica - mapa przedstawiana w postaci kwadratow
	 * true oznacza ze na danym polu jest przeszkoda badz jakas istota
	 * i inna istota nie moze na nie wejsc
	 */
	protected boolean[][] szachownica;
	
	/**
	 * wielkosc mapy - szachownicy
	 */
	protected Size wielkoscSzachownicy; 
	
	/**
	 * wielkosc jednego kwadratu na szachownicy
	 */
	public static final float WIELKOSC_POLA = 64f;
		
	/**
	 * wlasciwa mapa to uklad wspolrzedny, szachownica wyznacza obszary niedozwolone na nim
	 */
	protected Size wielkoscMapy;
	
	/**
	 * postac ktora kieruje gracz
	 */
	protected Bohater bohater;
	
	/**
	 * lista elementow ktora jest w grze ale nie wchodzi w interakcje z graczem
	 */
	protected ArrayList<Twor> nieaktywni;
	
	/**
	 * lista list elementow ktore sa w grze i wchodza w interakcje z graczem
	 */
	private ArrayList<ArrayList<Twor>> aktywni;
	/**
	 * lista aktywnych przeciwnikow
	 */
	private ArrayList<Twor> przeciwnicy;
	/**
	 * lista pociskow gracza
	 */
	private ArrayList<Twor> pociskiGracza;
	/**
	 * lista pociskow przeciwnikow
	 */
	private ArrayList<Twor> pociskiPrzeciwnikow;
	/**
	 * lista skrzyn ktore sa na mapie i gracz moze je zebrac
	 */
	private ArrayList<Twor> skrzynki;
	/**lista aktualnie przemieszczajacych sie tworow
	 * (pociskow lub istot)
	 */
	private ArrayList<Ruch> listaKrokow;
	
	/**
	 * lista broni gracza
	 */
	private ArrayList<Bron> bronie;
	
	/**
	 * ilosc przeciwnikow jacy znajduja sie na mapie
	 */
	private int iloscPrzeciwnikow = 0;
	
	/**
	 * indeks aktualnie uzywanej broni w tablicy bronie
	 */
	private int aktualnaBron;
	
	/**
	 * konstruktor modelu
	 * @param con controller
	 */
	public Model(Controller con){
		controller = con;

		this.initMapy();
		
		nieaktywni = new ArrayList<Twor>();
		przeciwnicy = new ArrayList<Twor>();
		pociskiGracza = new ArrayList<Twor>();
		pociskiPrzeciwnikow = new ArrayList<Twor>();
		listaKrokow = new ArrayList<Ruch>();
		aktywni = new ArrayList<ArrayList<Twor>>();
		skrzynki = new ArrayList<Twor>();
		aktywni.add(przeciwnicy);
		aktywni.add(skrzynki);
		aktywni.add(pociskiGracza);
		aktywni.add(pociskiPrzeciwnikow);
		
		
		this.initPostaci();
		sprawdzWidocznosc();
		
		bronie = new ArrayList<Bron>();
		bronie.add(new Rewolwer(bohater));
		bronie.add(new Minigun(bohater));
		bronie.add(new WyrzutniaBomb(bohater));
		aktualnaBron = 1;
		bohater.setBron(bronie.get(aktualnaBron));
		
        logika = new Thread(this);
        logika.start();
        
        
		
	}
    
	/**
	 * metoda watku logiki
	 */
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (controller.getStanGry() != 4 && controller.getStanGry() != 3) {


            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            

            
            if (controller.getStanGry() == 1){
	            bohater.sprawdzCzyZaladowano();
	            wykonajRuchy();
	            
	            zdarzeniaSkrzynek();
	            zdarzeniaPociskow();
	            inteligencjaPrzeciwnikow();
	            
	            
	            beforeTime = System.currentTimeMillis();
            }
        }
    }
    
    /**
     * funkcja tworzaca mape
     */
	abstract protected void initMapy();
	
	/**
	 * funkcja dodajaca postacie na plansze
	 */
	abstract protected void initPostaci();
	
	
	/**
	 * wstawia bohatera do gry, tylko jesli nie ma innego bohatera i nowy bohater znajduje sie na wolnej pozycji
	 * @param a bohater
	 */
	protected void setBohater(Bohater a){
		if (bohater == null &&
				!szachownica[(int)a.getPozycjaNaSzachownicy().x][(int)a.getPozycjaNaSzachownicy().y]){
			szachownica[(int)a.getPozycjaNaSzachownicy().x][(int)a.getPozycjaNaSzachownicy().y] = true;
			bohater = a;
		}
	}
	/**
	 * dodaje przeciwnika, jesli przeciwnik nie mogl zostac dodany bo pod danym polem 
	 * znajduje sie przeszkoda lub inna postac zwraca false
	 */
	protected boolean dodajPrzeciwnika(Istota a){
		if (!szachownica[(int)a.getPozycjaNaSzachownicy().x][(int)a.getPozycjaNaSzachownicy().y]){
			szachownica[(int)a.getPozycjaNaSzachownicy().x][(int)a.getPozycjaNaSzachownicy().y] = true;
			nieaktywni.add(a);
			++iloscPrzeciwnikow;
			return true;
		}
		return false;
	}
	
	/**
	 * dodaje skrzynke na mape
	 * @param a skrzynka
	 * @return zwraca true jesli dodanie sie udalo
	 */
	protected boolean dodajSkrzynke(Twor a){
		if (!szachownica[(int)a.getPozycjaNaSzachownicy().x][(int)a.getPozycjaNaSzachownicy().y]){
			nieaktywni.add(a);
			return true;
		}
		return false;
	}
	
	/**
	 * zwraca bohatera
	 * @return bohater
	 */
	public Bohater getBohater(){
		return bohater;
	}

	
	
	/**
	 * dodaje obiekt przemieszczajacy postac na mapie
	 * @param istota Istota ktora ma sie przemieszczac
	 * @param a przemieszczenie na osi X
	 * @param b przemieszczenie na osi Y
	 * @return jesli ruch jest dozwolony zwraca true, jesli nie false
	 */
	private boolean ruchIstota(Istota istota ,int a, int b){
		if(istota.iswRuchu()) return false;
		
		
		Punkt tmp = (Punkt)istota.getPozycjaNaSzachownicy().clone(); ///new Punkt(istota.getPozycjaNaSzachownicy());
		tmp.plus(a, b);
		if(tmp.czyUjemne() || !tmp.czySieMiesci(wielkoscSzachownicy) || szachownica[(int)tmp.x][(int)tmp.y]  )
			return false;

		szachownica[(int)istota.getPozycjaNaSzachownicy().x][(int)istota.getPozycjaNaSzachownicy().y] = false;
		szachownica[(int)tmp.x][(int)tmp.y] = true;
		tmp.x = istota.getBounds().origin.x + a * Model.WIELKOSC_POLA;
		tmp.y = istota.getBounds().origin.y + b * Model.WIELKOSC_POLA;
		
		
		listaKrokow.add(new Ruch(tmp,istota, new Wektor(a, b)));

		if (istota instanceof Bohater) 
			sprawdzWidocznosc();
		
		return true;
	}
	
	/**
	 * przemieszcza gracza
	 * @param a przemieszczenie na osi X
	 * @param b przemieszczenie na osi Y
	 * @return jesli ruch jest dozwolony zwraca true, jesli nie false
	 */
	public boolean ruch(int a, int b){
		if (!bohater.iswRuchu()){
			if (a > 0) bohater.setwPrawo(true);
			if (a < 0) bohater.setwPrawo(false);
		}
		return ruchIstota(bohater, a, b);
	}
	
	/**
	 * sprawdza ktore twory sa w zasiegu wzroku
	 */
	private void sprawdzWidocznosc(){
		for(int i = nieaktywni.size() - 1; i >= 0; --i){
			Twor twor = nieaktywni.get(i);
			if (isVisible(twor)){
				switch (twor.getId_grupy()){
					case 4:
						przeciwnicy.add(twor);
						break;
						
					case 1:
						skrzynki.add(twor);
						break;
					case 2:
						skrzynki.add(twor);
						break;
					default:
						break;
				}
				nieaktywni.remove(i);
			}
		}
			
		for(int j = 0; j < aktywni.size(); ++j){
			ArrayList<Twor> lista = aktywni.get(j);
				for(int i = lista.size() - 1; i >= 0; --i){
					Twor twor = lista.get(i);
					if(!isVisible(twor)){
						switch (twor.getId_grupy()){
						case 3:
							
							break;
						default:
							nieaktywni.add(twor);
							break;
					}
						
						lista.remove(i);
					}
				}
				
		}


	}
	
	/**
	 * wykonuje trwajace przemieszczenia
	 */
	private void wykonajRuchy(){
		for(int i = listaKrokow.size() - 1; i >= 0; --i ){
			Ruch ruch = listaKrokow.get(i);
			if (ruch.wykonajRuch()){
				if (ruch.getObiekt() instanceof Pocisk){
					pociskiGracza.remove(ruch.getObiekt());
					pociskiPrzeciwnikow.remove(ruch.getObiekt());
				}
				listaKrokow.remove(i);
			}
		
		}
	}
	
	/**
	 * kieruje inteligencja przeciwnikow
	 */
	private void inteligencjaPrzeciwnikow(){
		for (int i = 0; i < przeciwnicy.size(); ++i){
			Istota przeciwnik = (Istota)przeciwnicy.get(i);
			if (przeciwnik instanceof Strzelajacy){
				Strzelajacy strzelec = (Strzelajacy)przeciwnik;
				if(!strzelec.czyMaAmunicjeWMagazynku())
					strzelec.getBron().przeladuj();
				if(!strzelec.czyWZasiegu(bohater)){
					kierujPrzeciwnikaDoGracza(strzelec);
				}
				
				
				wystrzelPocisk(strzelec, bohater.getCenter());
			}
		}
	}
	
	/**
	 * prowadzi danego przeciwnika w strone gracza
	 * @param a przeciwnik ktory ma sie kierowac w strone gracza
	 */
	private void kierujPrzeciwnikaDoGracza(Istota a) {
		if (a.iswRuchu()) return;
		Punkt pozycjaBohatera = bohater.getPozycjaNaSzachownicy();
		Punkt pozycjaA = a.getPozycjaNaSzachownicy();
		
		if (!pozycjaBohatera.czyPoX(pozycjaA)){
			if (!ruchIstota(a, (int)pozycjaBohatera.kierunekX(pozycjaA), 0)){
				int dy = (int)pozycjaBohatera.kierunekY(pozycjaA);
				if (!ruchIstota(a, 0, dy) && dy == 0)
					if (!ruchIstota(a, 0, -1))
						ruchIstota(a, 0, 1);
			}
		}
		else{
			if (!ruchIstota(a, 0, (int)pozycjaBohatera.kierunekY(pozycjaA))){
				int dx = (int)pozycjaBohatera.kierunekX(pozycjaA);
				if (!ruchIstota(a, dx, 0) && dx == 0)
					if(!ruchIstota(a, 1, 0) )
						ruchIstota(a, -1, 0);
				
			}
		}
		
	}
	
	/**
	 * sprawdza czy dany twor jest w zasiegu "wzroku" bohatera
	 * @param a sprawdzany twor
	 * @return zwraca true jesli dany twor jest w zasiegu wzroku bohatera
	 */
	private boolean isVisible(Twor a){
		Punkt twor = a.getPozycjaNaSzachownicy();
		Punkt bohaterowy = bohater.getPozycjaNaSzachownicy();
		if (twor.x > bohaterowy.x - 6 && twor.x < bohaterowy.x + 6 
				&& twor.y > bohaterowy.y - 6 && twor.y < bohaterowy.y + 6)
			return true;
		return false;
	}
	
	
	/**
	 * wystrzeliwuje pocisk przez 
	 * odpowiednia postac i dodaje go do odpowiednich list
	 * @param postac Istota ktora wystrzeliwuje pocisk
	 * @param punkt punkt w ktorego kierunku wystrzelono pocisk
	 * @return zwraca true jesli udalo sie wystrzelic pocisk
	 */
	private boolean wystrzelPocisk(Strzelajacy postac, Punkt punkt) {
		Pocisk pocisk =  postac.wystrzel();
		if (pocisk == null) return false;
		Rect frame = (Rect)pocisk.getBounds().clone();
		Punkt poczatek = frame.getCenter();
		Punkt koniec = frame.originDlaSrodka(punkt);

		Wektor wektor = new Wektor(poczatek, koniec);
		
		//obliczam stosunek odleglosci do punktu w ktory chemy trafic, a zasiegu
		double stosunek =   postac.getZasieg() / poczatek.odlegloscOd(koniec);
		wektor.pomnoz(stosunek);
		
		koniec = (Punkt)poczatek.clone();
		koniec.plus(wektor);
		wektor.podziel(postac.getZasieg());
		wektor.pomnoz(postac.getBron().getPredkoscPociskow());
		
		if (postac instanceof Bohater)
			pociskiGracza.add(pocisk);
		else
			pociskiPrzeciwnikow.add(pocisk);
		listaKrokow.add(new Ruch(koniec, pocisk, wektor));
		return true;
	}
	
	/**
	 * oddaje strzal gracza
	 * @param wektor Wektor w ktorym kierunku wystrzelil gracz
	 */
	public void strzel(Wektor wektor){
		
		if (wektor.x > 0) bohater.setwPrawo(true);
		if (wektor.x < 0) bohater.setwPrawo(false);
	
		Punkt punkt = bohater.ustalPunktWystrzalu(); ;
		punkt.plus(wektor);

		wystrzelPocisk(bohater, punkt);
	}

	
	/**
	 * przeladowywuje bron gracza
	 */
	public void przeladujBron(){
		bohater.przeladujBron();
	}

	/**
	 * zwraca ilosc zycia bohatera
	 * @return zycie bohatera
	 */
	public int getHP(){
		return bohater.getHP();
	}

	/**
	 * zwraca liste broni gracza
	 * @return lista broni
	 */
	public ArrayList<Bron> getBronie() {
		return bronie;
	}

	/**
	 * zwraca aktualna bron gracza
	 * @return aktualna bron gracza
	 */
	public int getAktualnaBron() {
		return aktualnaBron;
	}
	
	
	/**
	 * zmienia bron gracza jesli moze
	 *  false jedna bron wczesniej 
	 *  true bron do przodu na liscie
	 * @param a 
	 */
	public void zmienBron(boolean a){
		if(bronie.get(aktualnaBron).czyLaduje()) return;
		
		if(a) aktualnaBron = (aktualnaBron + 1 ) %3;
		else {
			if (aktualnaBron == 0) aktualnaBron = 2;
			else
				aktualnaBron = (aktualnaBron - 1 ) %3;
		}
		
		bohater.setBron(bronie.get(aktualnaBron));
	}
	
	/**
	 * funkcja zmieniajaca bron gracza 
	 * na konretna bron pod wskazanym indeksem w tablicy broni
	 * @param a indeks nowej aktualnej broni na liscie broni
	 */
	public void zmienBron(int a){
		if(bronie.get(aktualnaBron).czyLaduje()) return;
		aktualnaBron = a;
		bohater.setBron(bronie.get(aktualnaBron));
	}
	
	/**
	 * sprawdza zdarzenia pociskow z postaciami
	 */
	private void zdarzeniaPociskow(){
		for (int i = pociskiGracza.size() - 1; i >=  0; --i){
			Pocisk pocisk = (Pocisk)pociskiGracza.get(i);
			for (int j = przeciwnicy.size() - 1 ; j >= 0; --j){
				Istota przeciwnik = (Istota)przeciwnicy.get(j);
				if (pocisk.zderzaSieZ(przeciwnik)){
					if (!przeciwnik.addDmg(pocisk.getDmg())){
						usmierc(przeciwnik);
					}
					pociskiGracza.remove(i);
					break;
				}
				
			}
		}
		
		for (int i = pociskiPrzeciwnikow.size() - 1; i >=  0; --i){
			Pocisk pocisk = (Pocisk)pociskiPrzeciwnikow.get(i);
			if (pocisk.zderzaSieZ(bohater)){
				if(!bohater.addDmg(pocisk.getDmg())){
					usmierc(bohater);
					}
				pociskiPrzeciwnikow.remove(i);
			}
				
				
			
		}
	}
	
	/**
	 * zdarzenia gracza ze skrzynkami
	 */
	private void zdarzeniaSkrzynek(){
		
		for (int i = skrzynki.size() - 1; i >= 0; --i){
			Twor skrzynka = skrzynki.get(i);
			if (skrzynka.zderzaSieZ(bohater)){
				if (skrzynka instanceof Apteczka){
					bohater.ulecz((Apteczka)skrzynka);
				}
				
				if (skrzynka instanceof SkrzynkaZAmunicja){
					zabierzAmunicje((SkrzynkaZAmunicja)skrzynka);
				}
				skrzynki.remove(i);
			}
		}
	}

	/**
	 * usmierca dana istote
	 * @param a istota do usmiercenia
	 */
	private void usmierc(Istota a){
		Punkt pozycjaA = a.getPozycjaNaSzachownicy();
		szachownica[(int)pozycjaA.x][(int)pozycjaA.y] = false;
		
		if (a instanceof Bohater){
			controller.setStanGry((byte)4);
		}
		else{
			Twor nowy = a.umrzyj();
			if (nowy != null)
				skrzynki.add(nowy);
			przeciwnicy.remove(a);
			if (--iloscPrzeciwnikow == 0)
				controller.setStanGry((byte)3);
		}
	}
	
	/**
	 * zwraca liste z aktywnymi tworami
	 * @return lista z aktywnymi tworami
	 */
	public ArrayList<ArrayList<Twor>> getAktywni() {
		return aktywni;
	}

	/**
	 * zwraca liste z aktywnymi przeciwnikami
	 * @return lista z aktywnymi przeciwnikami
	 */
	public ArrayList<Twor> getPrzeciwnicy() {
		return przeciwnicy;
	}

	/**
	 * funkcja dodajaca amunicje ze skrzynki dla gracza
	 * @param skrzynka Skrzynka z amunicja z ktorej czerpie bohater
	 */
	private void zabierzAmunicje(SkrzynkaZAmunicja skrzynka){
		bronie.get(skrzynka.getRodzajAmunicji()).dodajAmunicje(skrzynka.getIloscAmunicji());
		
	}

}
