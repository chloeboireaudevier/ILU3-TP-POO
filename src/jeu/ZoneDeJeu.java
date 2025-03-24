package jeu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> limites = new ArrayList<>();
	private List<Bataille> batailles = new ArrayList<>();
	private List<Borne> bornes = new ArrayList<>();
	
	
	public int donnerLimitationVitesse() {
		if (limites.isEmpty() || (limites.get(limites.size()-1)) instanceof FinLimite){
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int km = 0;
		for (Iterator<Borne> iterator = bornes.iterator(); iterator.hasNext();) {
			Borne borne = iterator.next();
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
	
	public boolean peutAvancer() {
		return !batailles.isEmpty() && batailles.get(batailles.size()).equals(new Parade(Type.FEU));
	}
	
	private boolean estDepotFeuVertAutorise() {
		return batailles.isEmpty() || batailles.get(batailles.size()).equals(new Attaque(Type.FEU)) || ! batailles.get(batailles.size()).equals(new Parade(Type.FEU));
		
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && !depasseLimite(borne)&& !depasse1000(borne);
	}
	
	private boolean depasseLimite(Borne borne) {
		return !limites.isEmpty() && limites.get(limites.size()).equals(new DebutLimite()) && borne.getKm() > 50;
	}
	
	private boolean depasse1000(Borne borne) {
		return donnerKmParcourus()+borne.getKm() > 1000;
		
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return limites.isEmpty() || limites.get(limites.size()).equals(new FinLimite());
		} else if (limite instanceof FinLimite) {
			return !limites.isEmpty() && limites.get(limites.size()).equals(new DebutLimite());
		}
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		} else if (bataille instanceof Parade) {
			if (bataille.getType() == Type.FEU ) {
				return estDepotFeuVertAutorise();
			}else {
				return !batailles.isEmpty() && batailles.get(batailles.size()).getType() == bataille.getType();
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte!=null && carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		} else if (carte !=null && carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte != null && carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		return false;
	}
}
