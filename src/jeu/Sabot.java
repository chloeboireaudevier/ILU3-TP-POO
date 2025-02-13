package jeu;

import cartes.Carte;

public class Sabot {
	private Carte[] cartes;
	private int nbCartes;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public boolean estVide() {
		return nbCartes <=0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes>=100) {
			throw new IllegalStateException();
		}else {
			cartes[nbCartes] = carte;
			nbCartes++;
		}
		
	}
	

}
