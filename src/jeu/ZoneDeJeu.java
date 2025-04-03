package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> limites = new LinkedList<>();
	private List<Bataille> batailles = new LinkedList<>();
	private Collection<Borne> bornes = new ArrayList<>();
	private Set<Botte> bottes = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (limites.isEmpty()) {
			return 200;
		}
		if ((limites.get(0) instanceof FinLimite)) {
			return 200;
		}
		if (estPrioritaire()) {
			return 200;
		}
		return 50;
	}

	public int donnerKmParcourus() {
		int km = 0;
		for (Borne borne : bornes) {
			km += borne.getKm();
		}
		return km;
	}

	//TODO TP5
	public void deposer(Carte c) {
		if (c != null && c instanceof Borne borne) {
			bornes.add(borne);
		} else if (c != null && c instanceof Limite limite) {
			limites.add(0, limite);
		} else if (c != null && c instanceof Bataille bataille) {
			batailles.add(0, bataille);
		} else if (c instanceof Botte botte) {
			bottes.add(botte);
		}
	}

	public boolean peutAvancer() {
		if (batailles.isEmpty()) {
			return estPrioritaire();
		}
		if (batailles.get(0).equals(Cartes.FEU_VERT)){
			return true;
		}
		if (batailles.get(0) instanceof Parade) {
			return estPrioritaire();
		}
		if (batailles.get(0).equals(Cartes.FEU_ROUGE)) {
			return estPrioritaire();
		}
		if (estBotteCorrespondanteAttaquePresente(batailles.get(0))) {
			return estPrioritaire();
		}
		return false;
	}

	private boolean estBotteCorrespondanteAttaquePresente(Carte carte) {
		if (carte == null) {
			return false;
		}
		if (carte instanceof Attaque attaque) {
			return bottes.contains(new Botte(attaque.getType()));
		}
		return false;
	}

	private boolean estDepotFeuVertAutorise() {
		if (batailles.isEmpty()) {
			return !estPrioritaire();
		}
		if (batailles.get(0).equals(new Attaque(Type.FEU))) {
			return true;
		}
		if (batailles.get(0).equals(new Parade(Type.FEU))){
			return false;
		}
		return estBotteCorrespondanteAttaquePresente(batailles.get(0));

	}

	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && !depasseLimite(borne) && !depasse1000(borne);
	}

	private boolean depasseLimite(Borne borne) {
		if (limites.isEmpty()) {
			return false;
		}
		if (limites.get(0).equals(new DebutLimite())) {
			return true;
		}
		return borne.getKm() > 50;
	}

	private boolean depasse1000(Borne borne) {
		return donnerKmParcourus() + borne.getKm() > 1000;

	}

	private boolean estDepotLimiteAutorise(Limite limite) {
		if (estPrioritaire()) {
			return false;
		} else if (limite instanceof DebutLimite) {
			return limites.isEmpty() || limites.get(0).equals(new FinLimite()); 
		} else if (limite instanceof FinLimite) {
			return !limites.isEmpty() && limites.get(0).equals(new DebutLimite());
		}
		return false;
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bottes.contains(new Botte(bataille.getType()))) {
			return false;
		}
		if (bataille instanceof Attaque) {
			return peutAvancer();
		} else if (bataille instanceof Parade) {
			if (bataille.getType() == Type.FEU) {
				return estDepotFeuVertAutorise();
			} else {
				return !batailles.isEmpty() && batailles.get(0).getType() == bataille.getType();
			}
		}
		return false;
	}

	//TODO TP5
	public boolean estDepotAutorise(Carte carte) {
		if (carte != null && carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		} else if (carte != null && carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte != null && carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		} else if (carte != null && carte instanceof Botte botte) {
			return bottes.add(botte);
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ZoneDeJeu zone) {
			return limites.equals(zone.limites) && bornes.equals(zone.bornes) && batailles.equals(zone.batailles)
					&& bottes.equals(zone.bottes);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 17 * (limites.hashCode() + bornes.hashCode() + batailles.hashCode() + bottes.hashCode());
	}

	public boolean estPrioritaire() {
		return bottes.contains(new Botte(Type.FEU));
	}
}
