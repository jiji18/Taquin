package algo;


import jeu.*;

public class EnsembleIncomplet implements EnsembleMarque {

	private int[] ensemble;
	private int taille;
	
	/**
	 * Constructeur d'un ensemble incomplet
	 * @param pTaille
	 * La taille souhaite pour l'ensemble
	 */
	public EnsembleIncomplet (int pTaille){
		taille=pTaille;
		ensemble=new int[taille];
	}

	/**
	 * Fonction d'ajout d'un jeu a l'ensemble
	 * @param pSommet
	 * Le jeu a ajouter
	 */
	public void ajout(Jeu pSommet) {
		int indice=Math.abs(pSommet.hashCode()%taille);
		if(ensemble[indice]==0)
			ensemble[indice]=1;
	}

	/**
	 * Fonction d'appartenance d'un jeu a l'ensemble
	 * @param pATester
	 * Le jeu sur lequel tester l'appartenance
	 * @return
	 * Un boolean true si l'ensemble contient le jeu, et false sinon
	 */
	public boolean appartient(Jeu pATester) {
		int indice=Math.abs(pATester.hashCode()%taille);
		return ensemble[indice]==1;
	}
	
	/**
	 * Fonction de taille de l'ensemble
	 * @return
	 * Retourne le nombre de positions dans l'ensemble
	 */
	public int taille() {
		return taille;
	}
}
