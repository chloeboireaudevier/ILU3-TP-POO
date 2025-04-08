package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
	
	public void deposer(Carte c) {
		zoneDeJeu.deposer(c);
		
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsValides = new HashSet<>();
		for (Iterator<Joueur> iteratorJ = participants.iterator(); iteratorJ.hasNext();) {
			Joueur joueur = iteratorJ.next();
			for (Iterator<Carte> iteratorC = main.iterator();iteratorC.hasNext();) {
				Carte carte = iteratorC.next();
				Coup coup = new Coup(this,carte,joueur);
				if(coup.estValide()) {
					coupsValides.add(coup);
				}
			}
		}
		return coupsValides;
	}
	
	public Set<Coup> coupsDefausse(){
		Set<Coup> coupsDefausse = new HashSet<>();
		for (Iterator<Carte> iterator = main.iterator();iterator.hasNext();) {
			Carte carte = iterator.next();
			Coup coup = new Coup(this,carte,null);
			coupsDefausse.add(coup);
		}
		return coupsDefausse;
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsValides = coupsPossibles(participants);
		if (!coupsValides.isEmpty()) {
			return choixCoupAleatoire(coupsValides);
		}
		Set<Coup> coupsDefausse = coupsDefausse();
		return choixCoupAleatoire(coupsDefausse);
	}
	
	private Coup choixCoupAleatoire(Set<Coup> coups) {
		List<Coup> listCoups = new ArrayList<>();
		listCoups.addAll(coups);
		Random random = new Random();
		int randomNum = random.nextInt(listCoups.size());
		Coup coup = listCoups.get(randomNum);
		listCoups.remove(randomNum);
		return coup;
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
}
