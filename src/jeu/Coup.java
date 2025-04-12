package jeu;
import cartes.*;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJouee;
	private Joueur joueurCible;
	
	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.carteJouee = carteJouee;
		this.joueurCible = joueurCible;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		if (carteJouee instanceof Attaque attaque) {
			return joueurCible.estDepotAutorise(attaque);
		}
		if (carteJouee instanceof DebutLimite limite) {
			return joueurCible.estDepotAutorise(limite);
		}
		return joueurCourant.estDepotAutorise(carteJouee);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (joueurCible == null) {
			sb.append("defausse la carte ");
			sb.append(carteJouee.toString());
		} else {
			sb.append("depose la carte ");
			sb.append(carteJouee.toString());
			sb.append(" dans la zone de jeu de ");
			sb.append(joueurCible.toString());
		}
		return sb.toString();
	}
}
