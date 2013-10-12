package Strzelanka;

import Model.*;
import View.*;
import Controller.Controller;

/**
 * klasa uruchamiajaca gre
 * @author marcin
 *
 */
public class Strzelanka {
    public static void main(String[] args) {
        Controller gra = new Controller(1);
		gra.setModel( new Lvl1Model(gra));
		gra.setView( new Lvl1View(gra));
}
  
}
