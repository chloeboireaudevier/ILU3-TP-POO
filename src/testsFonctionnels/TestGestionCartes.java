package testsFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {
	public static void main(String args[]) {
		//Test sur les valeurs
		
		List<Integer> list = new ArrayList<>();
		
		
		
		//Test sur le jeu de cartes
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste mélangée sans erreur ? " + GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));
		
		
		listeCartes = GestionCartes.rassembler(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(listeCartes));

	}

}