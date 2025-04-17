package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.*;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> main = new ArrayList<>();
	
	public void prendre(Carte carte) {
		if (carte !=null) {
			main.add(carte);
		}
	}
	
	public void jouer(Carte carte) {
		if (carte!= null &&  (main.contains(carte))) {
				main.remove(carte);
		}
	}

	@Override
	public String toString() {
		return "MainJoueur [main=" + main + "]";
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return main.iterator(); 
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte carte = sabot.piocher();
		main.add(carte);
		return carte;
	}
	
	
}
