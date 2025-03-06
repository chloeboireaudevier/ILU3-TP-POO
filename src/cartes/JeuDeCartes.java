package cartes;

import java.util.Iterator;

public class JeuDeCartes {
	private Configuration[] configuration = { new Configuration(new Borne(25), 10),
			new Configuration(new Borne(50), 10), new Configuration(new Borne(75), 10),
			new Configuration(new Borne(100), 12), new Configuration(new Borne(200), 4),
			new Configuration(new Parade(Type.FEU), 14), new Configuration(new FinLimite(), 6),
			new Configuration(new Parade(Type.ESSENCE), 6), new Configuration(new Parade(Type.CREVAISON), 6),
			new Configuration(new Parade(Type.ACCIDENT), 6), new Configuration(new Attaque(Type.FEU), 5),
			new Configuration(new DebutLimite(), 4), new Configuration(new Attaque(Type.ESSENCE), 3),
			new Configuration(new Attaque(Type.CREVAISON), 3), new Configuration(new Attaque(Type.ACCIDENT), 3),
			new Configuration(new Botte(Type.FEU), 1), new Configuration(new Botte(Type.ESSENCE), 1),
			new Configuration(new Botte(Type.CREVAISON), 1), new Configuration(new Botte(Type.ACCIDENT), 1) };

	public String affichageJeuDeCarte() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < configuration.length; i++) {
			s.append(configuration[i].getNbExemplaires());
			s.append(" ");
			s.append(configuration[i].getCarte());
			s.append('\n');

		}
		return s.toString();
	}

	public Carte[] donnerCartes() {
		int nbCartes = 0;
		for (int i = 0; i < configuration.length; i++) {
			nbCartes += configuration[i].getNbExemplaires();
		}
		Carte[] cartes = new Carte[nbCartes];

		for (int i = 0, k = 0; i < 19; i++) {
			for (int j = 0; j < configuration[i].getNbExemplaires(); j++, k++) {
				cartes[k] = configuration[i].getCarte();
			}
		}
		return cartes;
	}

	private static class Configuration {
		private int nbExemplaires;
		private Carte carte;

		private Configuration(Carte carte, int nbExemplaires) {
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
	
	public boolean checkCount() { //TODO à refaire
		Carte[] cartes = donnerCartes();
		
		boolean conforme = true;
		
		Configuration[] configurationTest = new Configuration[configuration.length];
		
		int compteur = 1;
		int indiceConfig = 0;
		
		//On compte le nombre de cartes
		for (int i = 0; i < cartes.length-1; i++) {
			
			if ( cartes[i].equals(cartes[i+1])) {
				
				compteur++;
			} else {
				configurationTest[indiceConfig] = new Configuration(cartes[i],compteur);
				compteur = 1;
				indiceConfig++;
			}
			
		}
		configurationTest[indiceConfig] = new Configuration(cartes[cartes.length-1],compteur);
		
		//On verifie
		for (int i = 0; i < configurationTest.length && conforme; i++) {
			//System.out.println(configuration[i].getCarte());
			//System.out.println(configurationTest[i].getCarte());
			conforme = configurationTest[i]!= null && configurationTest[i].getNbExemplaires() == configuration[i].getNbExemplaires();
		}
		
		return conforme;
	}
}
