package testsFonctionnels;

import cartes.Borne;
import cartes.Attaque;
import cartes.Parade;
import cartes.Type;


public class TestMethodeEquals {

	public static void main(String[] args) {
		Borne borne1 = new Borne(25);
		Borne borne2 = new Borne(25);
		
		System.out.println("Deux cartes de 25km sont identiques ? "+borne1.equals(borne2));
		
		Attaque feurouge1 = new Attaque(Type.FEU);
		Attaque feurouge2 = new Attaque(Type.FEU);
		
		System.out.println("Deux cartes de feu rouge sont identiques ? "+feurouge1.equals(feurouge2));
		
		Parade feuvert = new Parade(Type.FEU);
		
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? "+feuvert.equals(feurouge1));
		

	}

}
