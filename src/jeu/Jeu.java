package jeu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.*;
import utils.*;

public class Jeu {
	private Sabot sabot;

	private Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		
		Carte[] cartes = jeuDeCartes.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes,cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		this.sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
	
	
}
