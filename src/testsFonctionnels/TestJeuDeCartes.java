package testsFonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		
		System.out.println(jeu.affichageJeuDeCarte());
		
		
		jeu.donnerCartes();
		
		System.out.println("Test checkCount() : "+jeu.checkCount());
	}
}
