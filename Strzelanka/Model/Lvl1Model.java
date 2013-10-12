package Model;

import Wspolrzedne.Size;
import Controller.Controller;

/**
 * klasa modelu gry dla planszy numer 1
 * @author marcin
 *
 */

public class Lvl1Model extends Model {

	/**
	 * konstruktor
	 * @param con controller
	 */
	public Lvl1Model(Controller con) {
		super(con);
	}
	
	/**
	 * funkcja tworzaca mape
	 */
	protected void initMapy(){

		wielkoscSzachownicy = new Size(15, 23);
		/*
			boolean tab[][] = {
0				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 
1				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
2				{1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
3				{1,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
4				{1,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
5				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
6				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
7				{1,0,0,0,0,1,1,1,0,0,0,0,0,0,0},
8				{1,0,0,0,0,1,1,1,0,0,0,0,0,1,0},
9				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
10				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
11				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
12				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
13				{1,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
14				{1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
15				{1,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
16				{1,0,1,0,0,1,1,0,0,0,1,0,0,0,0},
17				{1,0,1,0,0,1,0,0,0,0,0,0,0,0,0},
18				{1,0,1,0,0,1,0,1,0,0,1,0,0,0,0},
19				{1,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
20				{1,0,0,0,1,1,1,1,1,0,1,0,0,0,0},
21				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
22				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
				};
		*/
		
		boolean tab[][] = {
				{true,false,true,true,true,true,true,true,true,true,true,true,true,true,true}, 
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,true,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,true,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,true,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,true,true,true,false,false,false,false,false,false,false},
				{true,false,false,false,false,true,true,true,false,false,false,false,false,true,false},
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,true,false,false,false,false},
				{true,false,true,false,false,false,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,false,false,true,false,false,false,false,false,false,false,false},
				{true,false,true,false,false,true,true,false,false,false,true,false,false,false,false},
				{true,false,true,false,false,true,false,false,false,false,false,false,false,false,false},
				{true,false,true,false,false,true,false,true,false,false,true,false,false,false,false},
				{true,false,false,false,false,true,false,false,false,false,false,false,false,false,false},
				{true,false,false,false,true,true,true,true,true,false,true,false,false,false,false},
				{true,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
				};
	   szachownica = new boolean[(int)wielkoscSzachownicy.widht][(int)wielkoscSzachownicy.height];
	   
	   for (int i = 0; i < wielkoscSzachownicy.widht; ++i){
		   for(int j = 0; j < wielkoscSzachownicy.height; ++j){
			   szachownica[i][j] = tab[j][i];
		   }
	   }
		
	}
	
	/**
	 * funkcja dodajaca postacie na plansze
	 */
	protected void initPostaci(){
		setBohater(new Bohater(14,4));
		
		dodajSkrzynke(new SkrzynkaZBombami(0,22));
		dodajSkrzynke(new SkrzynkaZA1(1,1));
		dodajSkrzynke(new SkrzynkaZB1(14,22));
		
		dodajPrzeciwnika(new Ninja(1,5));
		dodajPrzeciwnika(new Ninja(4,5));
		dodajPrzeciwnika(new Ninja(8,4));
		dodajPrzeciwnika(new Ninja(5,16));
		dodajPrzeciwnika(new Ninja(2,13));
		dodajPrzeciwnika(new Ninja(10,10));
		dodajPrzeciwnika(new Ninja(5,13));
		dodajPrzeciwnika(new Ninja(8,16));
		dodajPrzeciwnika(new Ninja(0,22));
		dodajPrzeciwnika(new Ninja(1,1));
		dodajPrzeciwnika(new Ninja(14,22));


		System.out.println("Frame: bohatera" + bohater.getBounds());

	}
	
}
