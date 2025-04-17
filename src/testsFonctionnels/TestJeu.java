package testsFonctionnels;

import jeu.*;

import java.util.ArrayList;
import java.util.List;

import cartes.*;

public class TestJeu {
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Joueur j1 = new Joueur("j1");
		Joueur j2 = new Joueur("j2");
		Joueur j3 = new Joueur("j3");
		
		List<Joueur> liste = new ArrayList<>();
		liste.add(j1);
		liste.add(j2);
		liste.add(j3);
		
		jeu.inscrire(liste);
		jeu.distribuerCartes();
		
		for(Joueur j : liste) {
			System.out.println(j.getMain());
		}
		
//		System.out.println(jeu.jouerTour(j1));
//		System.out.println(jeu.jouerTour(j2));
//		System.out.println(jeu.jouerTour(j3));
		
		System.out.println(jeu.lancer());
	}
	
	
	
	
	
}
