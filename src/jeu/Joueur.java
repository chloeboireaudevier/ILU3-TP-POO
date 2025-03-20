package jeu;

import cartes.*;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main = new MainJoueur();
	
	private Joueur(String nom) {
		this.nom = nom;
		this.zoneDeJeu = new ZoneDeJeu();
	}

	@Override
	public String toString() {
		return "Joueur [nom=" + nom + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o!= null && o instanceof Joueur joueur ) {
			return this.nom.equals(joueur.nom);
		}
		return false;
	}

	public MainJoueur getMain() {
		return main;
	}

	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
}
