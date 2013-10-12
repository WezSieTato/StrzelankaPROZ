package Model;

import java.util.Random;
/**
 * Klasa do generowania liczb
 * @author marcin
 *
 */
public class GeneratorLiczb extends Random {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * statyczny generator liczb
	 */
	public static Random Generator = new Random();

	/**
	 * losuje losowa liczbe calkowita
	 * @return wylosowana liczba
	 */
	public static int losuj(){
		return  Generator.nextInt(100);

	}
	
/*
/**
 * ustawia ziarno losowania
 * @param seed
 */
	/*
	public GeneratorLiczb(long seed) {
		super(seed);
		// TODO Auto-generated constructor stub
	}
*/
}
