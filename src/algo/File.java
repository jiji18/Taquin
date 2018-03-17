package algo;

import java.util.ArrayList;

import jeu.*;


public class File implements EnsembleATraiter{
	
	private ArrayList<Jeu> file;
	private int nombrePositionTraite;
	
	
	/**
	 * Constructeur sans parametre
	 */
	public File(){
		file= new ArrayList<Jeu>();
		nombrePositionTraite=0;
	}

	/**
	 * Permet de savoir si la file est vide ou non
	 * @return
	 * true si l'ensemble est non vide, false sinon
	 */
	public boolean nonVide() {
		return !file.isEmpty();
	}

	/**
	 * Permet de defiler
	 * @return
	 * La tete de la file
	 */	
	public Jeu prend() {
		nombrePositionTraite++;
		return file.remove(0);
	}
	/**
	 * Ajoute un Taquin a la fin de la file
	 * @param p
	 * Le jeu a ajouter
	 * @return
	 * true si le jeu a bien ete ajoute, false sinon
	 */

	public boolean ajout(Jeu p) {
		return file.add(p);
	}

	/**
	 * Donne le nombre de positions traitees
	 * @return
	 * Le nombre de positions traitees
	 */
	public int positionTraite() {
		return nombrePositionTraite;
	}

	/**
	 * Fonction de premier ajout a l'ensemble
	 * @param initial
	 * Le jeu initial a ajouter
	 * @return
	 * true si le jeu a bien ete ajoute, false sinon
	 */	
	public boolean premierAjout(Jeu initial) {
		return ajout(initial);
	}

	/**
	 * Test d'appartenance d'un jeu a la file
	 * @param p
	 * Le jeu a tester
	 * @return
	 * true si le jeu est dans l'ensemble, false sinon
	 */
	public boolean appartient(Jeu p) {
		return file.contains(p);
	}

}
