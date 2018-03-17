package algo;

import java.util.*;

import jeu.*;

public class EnsembleComplet implements EnsembleMarque {
	
	private ArrayList<Jeu> ensemble;
	
	/**
	 * Constructeur d'un ensemble complet
	 */
	public EnsembleComplet(){
		ensemble=new ArrayList<Jeu>();
	}
	
	/**
	 * Fonction d'ajout d'un jeu
	 * @param pSommet
	 * Le jeu a ajouter
	 */
	public void ajout(Jeu pSommet) {
		ensemble.add(pSommet);
	}
	
	/**
	 * Test d'appartenance d'un jeu a l'ensemble
	 * @param pATester
	 * Le jeu sur lequel tester l'appartenance
	 * @return
	 * Un boolean true si l'ensemble contient le jeu, et false sinon
	 */
	public boolean appartient(Jeu pATester) {
		return ensemble.contains(pATester);
	}

	/**
	 * Fonction de taille de l'ensemble
	 * @return
	 * Retourne le nombre de positions dans l'ensemble
	 */
	public int taille() {
		return ensemble.size();
	}
}
