package utils;
import cartes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes  {
	
	public static <E> E extraire(List<E> list) {
		Random rand = new Random();
		int randomNum = rand.nextInt(list.size());
		
		E element = list.get(randomNum);
		
		for (int i = randomNum; i < list.size()-1; i++) {
			list.add(i,list.get(i+1));
		}
		list.remove(list.size()-1);
		
		return element;
	}
	
	public static <E> E extraireWithIterator(List<E> list) {
		Random rand = new Random();
		int randomNum = rand.nextInt(list.size());
		
		ListIterator<E> iterator = list.listIterator(randomNum);
		E element = null;
		
		if (iterator.hasNext()) {
			element = iterator.next();
			iterator.remove();
		}
		return element;
	}
		
	public static <E> List<E> melanger(List<E> list) {
		List<E> melangee = new ArrayList<>();
		
		for (ListIterator<E> listIterator = list.listIterator();listIterator.hasNext();) {
			melangee.add(listIterator.next());
			listIterator.remove();			
		}
		
		return melangee;
		
	}
	
	public static <E> boolean verifierMelange(List<E> l1, List<E> l2) {
		boolean memeOccurence = true;
		
		for (ListIterator<E> listIterator = l1.listIterator();listIterator.hasNext() && memeOccurence;) {
			E element = listIterator.next();
			if (Collections.frequency(l1,element) != Collections.frequency(l2, element)) {
				memeOccurence = false;
			}
		}
		
		
		//Il faut vérifier dans les deux sens
		for (ListIterator<E> listIterator = l2.listIterator();listIterator.hasNext() && memeOccurence;) {
			E element = listIterator.next();
			if (Collections.frequency(l1,element) != Collections.frequency(l2, element)) {
				memeOccurence = false;
			}
		}
		
		return memeOccurence;
		
	}
	
	public static <E> List<E> rassembler(List<E> list) {
		List<E> listeOrdonnee = new ArrayList<>();
		
		for (ListIterator<E> iterator = list.listIterator(); iterator.hasNext();) {
			//On recupère la carte
			E element = iterator.next();
			
			//Si la carte n'est pas déjà présente on ajoute le nombre qu'il faut
			if (!listeOrdonnee.contains(element)) {
				for (int i = 0; i < Collections.frequency(list,element); i++) {
					listeOrdonnee.add(element);
				}
			}
		}
		return listeOrdonnee;
	}
	
	public static <E> boolean verifierRassemblement(List<E> list) {
		boolean rassemble = true;
		
		E element = list.get(0);
		E previousElt;
		for (ListIterator<E> iterator = list.listIterator(1); iterator.hasNext() && rassemble;) {
			previousElt = element;
			element = iterator.next();
			
			if ((!element.equals(previousElt))&& iterator.hasNext()) {
				for (ListIterator<E> iterator2 = list.listIterator(iterator.nextIndex()); iterator2.hasNext() && rassemble;) {
					E doublon = iterator2.next();
					if (doublon.equals(previousElt)) {
						rassemble = false;
					}
				}
			}					
		}
		return rassemble;
	}
}
