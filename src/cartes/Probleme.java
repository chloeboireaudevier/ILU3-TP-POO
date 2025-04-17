package cartes;

public abstract class Probleme extends Carte {
	
	private Type type;


	protected Probleme(Type type) {
		this.type = type;
	}


	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && getType().equals(((Probleme) obj).getType());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode()+17*(type.hashCode());
	}
	

	

}
