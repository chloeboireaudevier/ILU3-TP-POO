package testsFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {
	public static void main(String args[]) {
		//Test sur liste vide
		
		List<Integer> listeVide = new ArrayList<>();
		
		System.out.println(listeVide);
		List<Integer> listeVideMelangee = GestionCartes.melanger(listeVide);
		System.out.println(listeVideMelangee);
		System.out.println("liste mélangée sans erreur ? " + GestionCartes.verifierMelange(listeVideMelangee, listeVide));
		
		
		listeVideMelangee = GestionCartes.rassembler(listeVideMelangee);
		System.out.println(listeVideMelangee);
		System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(listeVideMelangee));
		
		//Test sur liste 
		
		List<Integer> liste = new ArrayList<>();
		liste.add(1);
		liste.add(1);
		liste.add(2);
		liste.add(1);
		liste.add(3);
		
		List<Integer> copie = new ArrayList<>(liste);
		
		List<Integer> liste2 = new ArrayList<>();
		liste2.add(1);
		liste2.add(4);
		liste2.add(3);
		liste2.add(2);
		
		System.out.println(liste);
		List<Integer> listeMelangee = GestionCartes.melanger(copie);
		System.out.println(listeMelangee);
		System.out.println("liste mélangée sans erreur ? " + GestionCartes.verifierMelange(listeMelangee, liste));
		
		
		
		listeMelangee = GestionCartes.rassembler(listeMelangee);
		System.out.println(listeMelangee);
		System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(listeMelangee));
		
		System.out.println("Liste melangée avec erreur : "+ GestionCartes.verifierMelange(liste2, liste));
		
		
		
		
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