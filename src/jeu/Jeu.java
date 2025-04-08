package jeu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.*;
import utils.*;

public class Jeu {
	public static final int NBCARTES = 6;
	private Sabot sabot;
	private List<Joueur> joueurs = new ArrayList<>();

	private Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		
		Carte[] cartes = jeuDeCartes.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes,cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		this.sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
	
	public void inscrire(List<Joueur> ajout) {
		if (!joueurs.isEmpty()) {
			for (Joueur joueur : ajout) {
				joueurs.add(joueur);
			}
		}
	}
	
	public void distribuerCartes() {
		for(int i = 0; i < NBCARTES;i++) {
			for (Joueur joueur : joueurs) {
				joueur.donner(sabot.piocher());
			}
		}
	}
	
}
