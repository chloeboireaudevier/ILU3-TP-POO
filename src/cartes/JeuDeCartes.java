package cartes;

public class JeuDeCartes {
	private Configuration[] configuration = {
			new Configuration(new Borne(25),10),
			new Configuration(new Borne(50),10),
			new Configuration(new Borne(75),10),
			new Configuration(new Borne(100),12),
			new Configuration(new Borne(200),4),
			new Configuration(new Parade(Type.FEU),14),
			new Configuration(new FinLimite(),6),
			new Configuration(new Parade(Type.ESSENCE),6),
			new Configuration(new Parade(Type.CREVAISON),6),
			new Configuration(new Parade(Type.ACCIDENT),6),
			new Configuration(new Attaque(Type.FEU),5),
			new Configuration(new DebutLimite(),4),
			new Configuration(new Attaque(Type.ESSENCE),3),
			new Configuration(new Attaque(Type.CREVAISON),3),
			new Configuration(new Attaque(Type.ACCIDENT),3),
			new Configuration(new Botte(Type.FEU),1),
			new Configuration(new Botte(Type.ESSENCE),1),
			new Configuration(new Botte(Type.CREVAISON),1),
			new Configuration(new Botte(Type.ACCIDENT),1)};
			
	public String affichageJeuDeCarte() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 19; i++) {
			s.append(Integer.toString(configuration[i].getNbExemplaires())+" "+configuration[i].getCarte().toString()+'\n');
	
		}
		return s.toString();
	}
	
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for (int i = 0; i < 19;i++) {
			nbCartes += configuration[i].getNbExemplaires();
		}
		Carte[] cartes = new Carte[nbCartes];
		
		int indiceCartes = 0;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < configuration[i].getNbExemplaires();j++) {
				cartes[indiceCartes] = configuration[i].getCarte();
				indiceCartes++;
			}
		}
		return cartes;
	}


	private static class Configuration{
		private int nbExemplaires;
		private Carte carte;

		private Configuration(Carte carte,int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
		
	}
}
