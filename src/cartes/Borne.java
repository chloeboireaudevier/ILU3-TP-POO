package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return Integer.toString(km)+" KM";
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && km == ((Borne)obj).km;
	}
	
	
}
