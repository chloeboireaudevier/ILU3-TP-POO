package utils;
import cartes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes  {
	
	public static Carte extraire(List<Carte> cartes) {
		Random rand = new Random();
		int randomNum = rand.nextInt(cartes.size());
		
		Carte carte = cartes.get(randomNum);
		
		for (int i = randomNum; i < cartes.size()-1; i++) {
			cartes.add(i,cartes.get(i+1));
		}
		cartes.remove(cartes.size()-1);
		
		return carte;
	}
	
	public static Carte extraireWithIterator(List<Carte> cartes) {
		Random rand = new Random();
		int randomNum = rand.nextInt(cartes.size());
		
		ListIterator<Carte> iterator = cartes.listIterator(randomNum);
		Carte carte = null;
		
		if (iterator.hasNext()) {
			carte = iterator.next();
			iterator.remove();
		}
		return carte;
	}
		
	public static List<Carte> melanger(List<Carte> list) {
		List<Carte> cartes = new ArrayList<>();
		
		for (ListIterator<Carte> listIterator = list.listIterator();listIterator.hasNext();) {
			cartes.add(listIterator.next());
			listIterator.remove();			
		}
		
		return cartes;
		
	}
	
	public boolean verifierMelange(List<Carte> l1, List<Carte> l2) {
		boolean memeOccurence = true;
		
		for (ListIterator<Carte> listIterator = l1.listIterator();listIterator.hasNext() && memeOccurence;) {
			Carte carte = listIterator.next();
			if (Collections.frequency(l1,carte) != Collections.frequency(l2, carte)) {
				memeOccurence = false;
			}
		}
		
		
		//Il faut vérifier dans les deux sens
		for (ListIterator<Carte> listIterator = l2.listIterator();listIterator.hasNext() && memeOccurence;) {
			Carte carte = listIterator.next();
			if (Collections.frequency(l1,carte) != Collections.frequency(l2, carte)) {
				memeOccurence = false;
			}
		}
		
		return memeOccurence;
		
	}
}
