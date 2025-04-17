package jeu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import cartes.*;
import utils.*;

public class Jeu {
	public static final int NBCARTES = 6;
	private Sabot sabot;
	private Set<Joueur> joueurs = new LinkedHashSet<>();
	private Iterator<Joueur> iterator = joueurs.iterator();

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
		} else if (joueurCible == joueur){
			joueur.deposer(carte);
			sb.append("dans sa zone de jeu");
		}else {
			joueurCible.deposer(carte);
			sb.append("la zone de jeu de ");
			sb.append(joueurCible.toString());
			sb.append("\n");
		}
		System.out.println(sb.toString()+"-------\n");
		return sb.toString();
	}
	
	public Joueur donnerJoueurSuivant() {
		if (!iterator.hasNext()) {
			iterator = joueurs.iterator();
		}
		return iterator.next();
	}
	
	public String lancer() {
		StringBuilder stringBuilder = new StringBuilder();
		while(!sabot.estVide()) {
			Joueur joueur = donnerJoueurSuivant();
			jouerTour(joueur);
			System.out.println("Etat "+joueur.afficherEtatJoueur()+"\n");
			for(Joueur j :joueurs) {
				if (j.donnerKmParcourus()>=1000) {
					stringBuilder.append(j.toString());
					stringBuilder.append(" gagne la partie\n");
					return stringBuilder.toString();
				}
			}
		}
		
		stringBuilder.append("Partie terminée : sabot vide\n");
		
		//List<Joueur> classement = classement();
		
		//stringBuilder.append(classement.toString());
		return stringBuilder.toString();
	}
	
	public List<Joueur> classement(){
		NavigableSet<Joueur> joueursClasses = new TreeSet<>(
				new Comparator<Joueur>() {
					@Override
					public int compare(Joueur joueur1, Joueur joueur2) {
						int compare =  joueur1.donnerKmParcourus() - joueur2.donnerKmParcourus();
						if (compare == 0) {
							compare = joueur1.hashCode() - joueur2.hashCode();
						}
						return compare;
					}
				}
			);
		for (Joueur j : joueurs) {
			joueursClasses.add(j);
		}
		return new ArrayList<>(joueursClasses);	
	}
	
}
