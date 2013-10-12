package View;

import Controller.*;
import javax.swing.*;

import java.util.*;
import java.awt.*;

/**
 * abstrakcyjna klasa widoku gry
 * @author marcin
 *
 */

public abstract class View extends JPanel {

	private static final long serialVersionUID = 1L;

	
	/**
	 * controller gry
	 */
	Controller controller;
	
	/**
	 * lista z bitmapami pol map
	 */
	private ArrayList<Image> polaMapy;
	/**
 	 * 
 	* tablica dwywymiarowa intow przedstawiajaca mape
 	* gdzie jedna komorka przedstawia fragment mapy 64 x 64 pixelow
	* a liczba wyznacza numer grafiki w tablicy grafik
	 */

	protected int[][] mapa;
	
	/**
	 * wspolrzedne poczatku rysowania mapy;
	 */
	private Point pocz = new Point (0,0);
	
	/**
	 * pierwsze pole Mapy do narysowania
	 */
	private Point poleStartowe = new Point (0,0);
	
	/**
	 * bok kwadratu bitmapy odpowiadajaca za jedno pole mapy
	 */
	public static final int WIELKOSC_POLA = 64;
	
	/**
	 * szerokosc mapy
	 */
	protected int szerokoscMapy;
	
	/**
	 * dlugosc mapy
	 */
	protected int dlugoscMapy;
	
	/**
	 * lista z bitmapami obiektow ktore beda umieszczane na ekranie;
	*/
	private ArrayList<Image> bitmapyObiektow;
	
	/**
	 * lista obiektow ktore beda umieszczone na mapie
	*/
	private ArrayList<Obiekt> obiekty;
	
	/**
	 * lista opisow broni gracza ktore beda wyswietlane na ekranie
	 */
	private ArrayList<OpisBron> bronie;
	/**
	 * lista nazw broni
	 */
	private ArrayList<String> nazwyBroni;
	
	/**
	 * indeks w tabeli bronie z ktorej aktualnie korzysta gracz
	 */
	private int aktualnaBron;
	
	/**
	 * ilosc hp gracza do wyswietlenia
	 */
	private int hp;
	
	/**
	 * czcionka do wyswietlania stanu HP gracza
	 */
	private final Font CZCIONKA_HP = new Font ("ComicSans",Font.BOLD,22);
	
	/**
	 * czcionka do wyswietlania informacji o broni
	 */
	private final Font CZCIONKA_BRON = new Font ("ComicSans",Font.BOLD,12);
	
	/**
	 * czcionka do wyswietlania informacji o aktualnej broni
	 */
	private final Color KOLOR_AKTUALNA_BRON = new Color(255, 122, 122);
	
	/**
	 * czcionka do wyswietlania waznych informacji o stanie gry
	 */
	private final Font CZCIONKA_KOMUNIKAT= new Font ("ComicSans",Font.BOLD,33);
	
	/**
	 * czcionka do wyswietlania reszty informacji
	 */
	private final Color KOLOR_STANDARD = Color.WHITE;
	
	/**
	 * okno w ktorym otwiera sie gra i zbiera wejscia (mysz i klawiatura)
	 */
	private JFrame okno;

	/**
	 * kontruktor przyjmujacy jako argument kontroler
	 * @param con
	 */
	public View(Controller con){
		controller = con;
		setSize(576, 650);
		
		this.loadImages();
		this.initMap();
		
		nazwyBroni = new ArrayList<String>();
		nazwyBroni.add("Rewolwer");
		nazwyBroni.add("Minigun");
		nazwyBroni.add("Wyrzutnia Bomb");

		requestFocus();
		
		okno = new JFrame();
		okno.add(this);
		okno.addKeyListener(controller);
		okno.addMouseListener(controller);
		okno.addMouseMotionListener(controller);
	 	okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	okno.setSize(576, 650);
	 	okno.setLocation(100, 100);
	 	okno.setTitle("Strzelanka");
	 	okno.setResizable(false);
	 	okno.setVisible(true); 


	}
	
	/**
	 * metoda ladujaca obrazki do tablic
	 */
	private void loadImages(){
		polaMapy = new ArrayList<Image>();
		
		for(int i = 0;i <= 22; ++i){
			String nazwaPliku = "images/mapa/pole";
			nazwaPliku += i;
			nazwaPliku += ".png";
			ImageIcon tmp = new ImageIcon(nazwaPliku);
			Image image = tmp.getImage();
	        polaMapy.add(image);
		}
		
		bitmapyObiektow = new ArrayList<Image>();
		for(int i = 0; i <= 13; ++i){
			String nazwaPliku = "images/obiekty/obiekt";
			nazwaPliku += i;
			nazwaPliku += ".png";
	        ImageIcon tmp = new ImageIcon(nazwaPliku);
	        Image image = tmp.getImage();
	        bitmapyObiektow.add(image);
		}
		
	}
	
	/**
	 * funkcja tworzaca mape
	 */
	protected abstract void initMap();
	
	/**
	 * przeciazona funkcja pain rysujaca gre
	 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		
		int stanGry = controller.getStanGry();
		
		if (stanGry == 0){
			setBackground(Color.BLACK);
			g.setFont(CZCIONKA_KOMUNIKAT);
			g2d.drawString("Poziom: " + controller.getNumerLvl(), 300, 300);
		}
		
		if (stanGry == 3){
			setBackground(Color.BLACK);
			g.setFont(CZCIONKA_KOMUNIKAT);
			g2d.drawString("Wygrales!", 300, 300);
		}
		
		if (stanGry == 4 || stanGry == 5){
			setBackground(Color.BLACK);
			g.setFont(CZCIONKA_KOMUNIKAT);
			g2d.drawString("Przegrales!", 300, 300);
		}

		if (stanGry == 1 || stanGry == 2){
			
			Point tmp = new Point (pocz);
			
			//rysowanie mapy
			for (int i = poleStartowe.y ; i < poleStartowe.y + 10; ++i){
				tmp.x = pocz.x;
				for (int j = poleStartowe.x ; j < poleStartowe.x + 10; ++j){
	
					if (i < 0 || j < 0 || j >= szerokoscMapy || i >= dlugoscMapy)
						g2d.drawImage(polaMapy.get(1) , tmp.x, tmp.y, this);
	
					else 
					{
						g2d.drawImage(polaMapy.get(mapa[j][i]) , tmp.x, tmp.y, this);
					}
					tmp.x += View.WIELKOSC_POLA;
					
				}
				tmp.y += View.WIELKOSC_POLA;
			}
			
			//rysowanie obiektow
			for (int i = obiekty.size() - 1; i >= 0; --i){
			//for (int i = 0; i < obiekty.size(); ++i){
				Obiekt rysuj = obiekty.get(i);
				g2d.drawImage(bitmapyObiektow.get(rysuj.getID()),rysuj.getX(),rysuj.getY(),this);
	
			}
			g2d.fillRect(0, 576, 576, 74);
			
			g.setFont(CZCIONKA_HP);
			g.setColor(KOLOR_STANDARD);
			if (hp < 30)
				g.setColor(Color.RED);
			g2d.drawString("HP: " + hp, 20, 620);
			
			g.setFont(CZCIONKA_BRON);
			
			for (int i = 0; i < 3; ++i){
				if ( i == aktualnaBron) g.setColor(KOLOR_AKTUALNA_BRON);
				else g.setColor(KOLOR_STANDARD);
				OpisBron bron = bronie.get(i);
				g2d.drawString(nazwyBroni.get(i), 150 + 90*i, 600);
				g2d.drawString(bron.getIloscPociskow(), 150 + 90*i, 640);
				g2d.drawString(bron.getIloscWMagazynku(), 150 + 90*i, 620);
			}
		}
		
		if (stanGry == 2){
		//	setBackground(Color.BLACK);
			g.setFont(CZCIONKA_KOMUNIKAT);
			g2d.drawString("Pauza", 300, 300);
		}
		
 
	}

	/**
	 * ustawia obiekty na mapie
	 * @param a nowa lista obiektow
	 */
	public void setObiekty(ArrayList<Obiekt> a){
		obiekty = a;
		//this.repaint();
	}
	
	/**
	 * ustawia punkt w ktorym zaczyna rysowac
	 * @param a nowy punkt poczatku rysowania
	 */
	public void setPoczRysowanie(Point a){
		pocz = a;
	}
	
	/**
	 * ustawia pole startowe do rysowania
	 * @param a nowe pole startowe do rysowania
	 */
	public void setPoleStartowe(Point a){
		poleStartowe = a;
	}

	/**
	 * ustawia hp
	 * @param hp nowe hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * ustawia liste z  broniami
	 * @param bronie nowa lista broni
	 */
	public void setBronie(ArrayList<OpisBron> bronie) {
		this.bronie = bronie;
	}

	/**
	 * ustawia aktualna bron
	 * @param aktualnaBron nowa aktualna bron
	 */
	public void setAktualnaBron(int aktualnaBron) {
		this.aktualnaBron = aktualnaBron;
	}

}
