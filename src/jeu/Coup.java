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
		if (carteJouee instanceof Attaque attaque && joueurCible != joueurCourant) {
			return joueurCible.estDepotAutorise(attaque);
		} else if (carteJouee instanceof DebutLimite limite && joueurCible != joueurCourant) {
			return joueurCible.estDepotAutorise(limite);
		} else if (joueurCourant == joueurCible) {
			return joueurCourant.estDepotAutorise(carteJouee);
		}
		return false;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup coup) {
			if (joueurCible == null && coup.joueurCible == null) {
				return joueurCourant.equals(coup.joueurCourant) && carteJouee.equals(coup.carteJouee);
			} else {
				return joueurCourant.equals(coup.joueurCourant) && joueurCible.equals(coup.joueurCible)
						&& carteJouee.equals(coup.carteJouee);
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int code = 17 * (joueurCourant.hashCode() + carteJouee.hashCode());
		if (joueurCible != null) {
			code += 17 * joueurCible.hashCode();
		}
		return code;
	}
}
