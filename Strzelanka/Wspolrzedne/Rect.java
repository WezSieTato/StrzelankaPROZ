package Wspolrzedne;
/**
 *  klasa ktora sluzy do przechowywania wspolrzednych danego obiektu na mapie w formie kwadrata
 */
public class Rect implements Cloneable{
	public Punkt origin;
	public Size size;
	
	public Rect(Punkt origin, Size size){
		this.origin = (Punkt)origin.clone(); // new Punkt(origin);
		this.size = (Size)size.clone(); // new Size(size);
	}
	
	public Rect(double x, double y,double widht, double height){
		this.origin = new Punkt(x,y);
		this.size = new Size(widht, height);
	}
	
	public Rect(Rect a){
		this.origin = (Punkt)a.origin.clone(); // new Punkt(a.origin);
		this.size = (Size)a.size.clone(); // new Size(a.size);
	}
	
	public Object clone(){
		Rect klon = null;
		try {
			klon = (Rect)super.clone();
			klon.origin = (Punkt)this.origin.clone(); // new Punkt(a.origin);
			klon.size = (Size)this.size.clone(); // new Size(a.size);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return klon;

	}
	
	/**
	 * metoda sprawdzajaca czy dany obszar koliduje z danym punktem
	 * @param a dany punkt
	 * @return true jesli koliduje
	 */
	public boolean zawiera(Punkt a){
		if (a.x >= origin.x && 
			a.x <= origin.x + size.widht &&
			a.y >= origin.y &&
			a.y <= origin.y + size.height)
			return true;
		return false;
	}
	
	/**
	 * metoda sprawdzajaca czy dany obszar koliduje z danym obszarem
	 * @param a dany obszar
	 * @return true jesli koliduje
	 */
	public boolean przecina(Rect a){
		if (this.zawiera(a.origin) ||
			this.zawiera(new Punkt(a.origin.x,a.origin.y + a.size.height)) ||
			this.zawiera(new Punkt(a.origin.x + a.size.widht,a.origin.y + a.size.height)) ||
			this.zawiera(new Punkt(a.origin.x + a.size.widht,a.origin.y)))
			return true;
		return false;
	}
	
	/**
	 * metoda zwracajaca srodek obszaru
	 * @return srodek obszaru
	 */
	public Punkt getCenter(){
		double a = origin.x + size.widht / 2;
		double b = origin.y + size.height / 2;
		Punkt centrum = new Punkt(a,b);
		return centrum;
	}
	
	/**
	 * metoda ustawiajaca srodek w danym punkcie
	 * @param a nowy srodek
	 */
	public void setCenter(Punkt a){
		origin.x = a.x - size.widht / 2;
		origin.y = a.y - size.height / 2;
	}
	
	/**
	 * metoda ustawiajaca srodek w danym punkcie
	 * @param a nowy x srodka
	 * @param b nowy y srodka
	 */
	public void setCenter(double a, double b){
		origin.x = a - size.widht / 2;
		origin.y = b - size.height / 2;
	}
	
	/**
	 * zwraca punkt w ktorym musialaby sie znajdowac
	 * ramka aby srodek byl w argumencie
	 */
	public Punkt originDlaSrodka(Punkt srodek){
		Rect frame = new Rect(this);
		frame.setCenter(srodek);
		return frame.origin;
	}

	@Override
	public String toString() {
		return "Rect [origin=" + origin + ", size=" + size + "]";
	}
	
}
