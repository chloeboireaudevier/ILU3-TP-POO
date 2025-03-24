package jeu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.*;

public class ZoneDeJeu {
	private List<Carte> limites = new ArrayList<Carte>();
	private List<Carte> batailles = new ArrayList<Carte>();
	private List<Carte> bornes = new ArrayList<Carte>();
	
	
	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || (limites.get(limites.size()-1)) instanceof FinLimite){
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int km = 0;
		for (Iterator<Carte> iterator = bornes.iterator(); iterator.hasNext();) {
			Borne borne = (Borne) iterator.next();
			km+= borne.getKm();
		}
		return km;
	}
	
	public void deposer(Carte c) {
		if(c!=null && c instanceof Borne borne) {
			bornes.add(borne);
		}else if (c!=null && c instanceof Limite limite) {
			limites.add(limite);
		}else if (c!=null && c instanceof Bataille bataille) {
			batailles.add(bataille);
		}
	}
}
