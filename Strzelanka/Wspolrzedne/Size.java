package Wspolrzedne;
/**
 * klasa odzworujaca wielkosc danego obiektu na mapie
 * @author marcin
 *
 */
public class Size implements Cloneable {
	/**
	 * szerokosc obiektu
	 */
	public double widht;
	/**
	 * wysokosc obiektu
	 */
	public double height;
	
	public Size(double widht, double height){
		this.widht = widht;
		this.height = height;
	}
	

	public Object clone(){
		Size klon = null;
		try {
			klon = (Size)super.clone();
			klon.widht = widht;
			klon.height = height;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return klon;

	}

	@Override
	public String toString() {
		return "Size [widht=" + widht + ", height=" + height + "]";
	}
	

}
