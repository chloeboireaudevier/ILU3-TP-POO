package cartes;

public enum Type {
	FEU("FeuVert","FeuRouge","VehiculePrioritaire"),
	ESSENCE("Essence","PanneEssence","Citerne"),
	CREVAISON("Roue","Crevaison","Increvable"),
	ACCIDENT("Reparation","Accident","AsDuVolant");
	private String parade;
	private String attaque;
	private String botte;
	

	private Type(String parade, String attaque, String botte) {
		this.attaque = attaque;
		this.parade = parade;
		this.botte = botte;
	}


	public String getParade() {
		return parade;
	}


	public String getAttaque() {
		return attaque;
	}


	public String getBotte() {
		return botte;
	}
	
	
	
}
