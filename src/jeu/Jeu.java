package jeu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import cartes.*;
import utils.*;

public class Jeu {
	public static final int NBCARTES = 6;
	private Sabot sabot;
	private List<Joueur> joueurs = new ArrayList<>(); //TODO list ou set ?
	private ListIterator<Joueur> iterator = joueurs.listIterator();

	public Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		
		Carte[] cartes = jeuDeCartes.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes,cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		this.sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
	
	public void inscrire(List<Joueur> ajout) {
		if (!ajout.isEmpty()) {
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
	
	public String jouerTour(Joueur joueur) {
		StringBuilder sb = new StringBuilder();
		
		Carte carte = sabot.piocher();
		joueur.donner(carte);
		
		sb.append("Le joueur ");
		sb.append(joueur.toString());
		sb.append(" a pioche ");
		sb.append(carte.toString());
		sb.append("\n Il a dans sa main :");
		sb.append(joueur.getMain());
		
		Set<Joueur> participants = new HashSet<>(joueurs);
		participants.remove(joueur);
		Coup coup = joueur.choisirCoup(participants);
		
		carte = coup.getCarteJouee();
		Joueur joueurCible = coup.getJoueurCible();
		
		joueur.retirerDeLaMain(carte);
		
		sb.append("\n");
		sb.append(joueur.toString());
		sb.append("depose la carte ");
		sb.append(carte.toString());
		sb.append(" dans ");
		
		if (joueurCible == null) {
			sabot.ajouterCarte(carte);
			sb.append("la pile de defausse\n");
		} else {
			joueurCible.donner(carte);
			if (joueurCible != joueur) {
				sb.append("la zone de jeu de ");
				sb.append(joueurCible.toString());
				sb.append("\n");
			}else {
				sb.append("dans sa zone de jeu");
			}
		}
		return sb.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		if (!iterator.hasNext()) {
			iterator = joueurs.listIterator();
		}
		return iterator.next();
	}
	
	public String lancer() {
		Boolean estGagne = false;
		while( !sabot.estVide() && !estGagne) {
			Joueur joueur = donnerJoueurSuivant();
			jouerTour(joueur);
			for(Joueur j :joueurs) {
				if (j.donnerKmParcourus()>=1000) {
					estGagne = true;
				}
			}
		}
		return "Partie terminée";
	}
	
}
