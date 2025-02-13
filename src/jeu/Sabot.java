package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] cartes;
	private int nbCartes;
	private int nbOperations; //Pour l'itérateur

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public boolean estVide() {
		return nbCartes <=0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes>=100) {
			throw new IllegalStateException(); //TODO bonne méthode ?
		}else {
			cartes[nbCartes] = carte;
			nbCartes++;
		}
		
	}
	
	@Override
	public Iterator<Carte> iterator(){
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nbOpReference = nbOperations;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;

		}
		@Override
		public Carte next(){
			verificationConcourrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				nbOperations = nbOpReference++;
				return carte;
			}else {
				throw new NoSuchElementException();
			}
			
		}
		
		@Override
		public void remove() {
			verificationConcourrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}else {
				for (int i = 0;i<nbCartes;i++) {
					cartes[i] = cartes[i+1];
				}
				nextEffectue = false;
				nbCartes--;
				indiceIterateur--;
				nbOperations++;
				nbOpReference++;
			}
		}
		
		private void verificationConcourrence() {
			if (nbOpReference != nbOperations) {
				throw new ConcurrentModificationException();
			}
		}
		
	}
	

}
