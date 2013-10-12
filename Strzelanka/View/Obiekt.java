package View;

import Wspolrzedne.*;
import java.awt.Point;

/**
 * klasa ktora bedzie posrednikiem miedzy modelowym obiektem na mapie, a tym przedstawionym w View
 */
public class Obiekt {
	private int ID;
	private int x;
	private int y;
	
	public Obiekt(int a, float b, float c){
		ID = a;
		x = (int)b;
		y = (int)c;
	}
	
	public Obiekt(int a, Punkt b){
		ID = a;
		x = (int)b.x;
		y = (int)b.y;
	}
	
	public Obiekt(int a, Point b){
		ID = a;
		x = b.x;
		y = b.y;
	}
	
	public int getID(){
		return ID;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	@Override
	public String toString() {
		return "Obiekt [ID=" + ID + ", x=" + x + ", y=" + y + "]";
	}

}
