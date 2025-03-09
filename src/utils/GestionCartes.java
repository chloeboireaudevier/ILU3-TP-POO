package utils;
import cartes.*;

import java.util.ArrayList;
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
		
}
