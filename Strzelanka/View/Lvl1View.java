package View;

import Controller.Controller;

/**
 * klasa widoku gry dla planszy numer 1
 * @author marcin
 *
 */

public class Lvl1View extends View {


	private static final long serialVersionUID = 1L;

	/**
	 * konstruktor
	 * @param con controller
	 */
	public Lvl1View(Controller con) {
		super(con);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * funkcja tworzaca mape
	 */
protected void initMap(){
		
		//mape podaje na odwrot x jako y i na odwrot, gdyz latwiej jest tak wprowadzac
		int tab[][] = {
				{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1}, 
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,2,3,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,3,0,0,0,2,0,0,0,0,0,0,4,0},
				{1,0,0,0,0,0,0,0,0,0,6,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,11,10,10,10,12,0,0,0,0,0,0},
				{1,0,0,0,13,9,9,9,14,0,0,7,0,0,0},
				{1,0,0,0,13,9,9,9,14,0,0,0,0,2,0},
				{1,0,0,0,17,15,15,15,16,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,7,0,0,0,8,0,0,5,0,0,0,0,0},
				{1,0,0,7,0,0,0,0,0,0,6,0,0,0,0},
				{1,0,1,0,0,7,11,12,0,0,0,0,0,0,0},
				{1,0,0,0,11,10,21,14,0,0,0,0,0,0,0},
				{1,0,1,0,13,9,9,14,0,0,1,0,0,0,0},
				{1,0,1,0,13,9,18,16,0,0,0,5,0,0,0},
				{1,0,1,0,13,9,14,2,0,0,1,0,0,0,0},
				{1,0,0,8,21,9,20,12,0,0,0,0,0,5,0},
				{1,0,0,11,9,9,9,20,12,0,2,0,0,0,0},
				{1,0,0,17,15,15,15,16,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
				};
		
		
		szerokoscMapy = 15;
		dlugoscMapy = 23;
		mapa = new int[szerokoscMapy][dlugoscMapy];
		
		//transponuje macierz z mapa
		   for (int i = 0; i < szerokoscMapy; ++i){
			   for(int j = 0; j < dlugoscMapy; ++j){
				   mapa[i][j] = tab[j][i];
			   }
		   }
		

	}
	
}
