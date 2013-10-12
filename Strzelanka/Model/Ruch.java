package Model;
import Wspolrzedne.*;
/**
 * klasa opisujaca ruch na mapie
 * @author marcin
 *
 */
public class Ruch {
	/**
	 * punkt do ktorego dany obiekt zmierza
	 */
	private Punkt punktKoncowy;
	/**
	 * poruszajacy sie obiekt
	 */
	private RuchomyTwor obiekt;
	/**
	 * wektor z jakiem przemieszcze sie obiekt
	 */
	private Wektor przemieszczenie;
	
	public Ruch(Punkt a, RuchomyTwor b, Wektor c){
		punktKoncowy = a;
		obiekt = b;
		przemieszczenie = c;
		
		Punkt punkt = obiekt.getPozycjaNaSzachownicy();
		punkt.x += przemieszczenie.x;
		punkt.y += przemieszczenie.y;
		
		if (obiekt instanceof Istota){
			Istota istota = (Istota)obiekt;
			istota.setwRuchu(true);
			
			if (przemieszczenie.x > 0) istota.setwPrawo(true);
			if (przemieszczenie.x < 0) istota.setwPrawo(false);

		}
	}
	
	/**
	 * przemieszczenie sie o dany wektor, zwraca true jesli obiekt dotarl juz do punktu koncowego
	 * @return true jesli obiekt dotarl do punktu docelowego
	 */
	public boolean wykonajRuch(){
		obiekt.frame.origin.plus(przemieszczenie);

		if (obiekt.frame.origin.equals(punktKoncowy) ||
				obiekt.frame.origin.czyMinal(punktKoncowy, przemieszczenie)){

			
			if (obiekt instanceof Istota){
				Istota istota = (Istota)obiekt;
				istota.setwRuchu(false);

			}
			return true;
		}
		return false;
		
	}

	/**
	 * zwraca wektor przemieszczenia
	 * @return przemieszczenie
	 */
	public Wektor getPrzemieszczenie() {
		return przemieszczenie;
	}

	/**
	 * zwraca obiekt
	 * @return obiekt
	 */
	public RuchomyTwor getObiekt() {
		return obiekt;
	}
	
}

