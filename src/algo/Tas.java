package algo;

import java.util.Comparator;
import java.util.PriorityQueue;

import jeu.*;

public class Tas implements EnsembleATraiter {
	
	private PriorityQueue<Jeu> file;
	private int nombrePositionTraite;
	
	/**
	 * Constructeur principal
	 * @param c
	 * Le comparateur a utiliser
	 */
	public Tas(Comparator<Jeu> c){
		file=new PriorityQueue<Jeu>(11,c);
		nombrePositionTraite=0;
	}
	
	/**
	 * NonVide
	 * @return
	 * Un boolean true si le tas n'est pas vide, false sinon
	 */
	public boolean nonVide() {
		return !file.isEmpty();
	}

	/**
	 * Fonction de retrait d'un jeu
	 * @return
	 * On retourne le jeu retourne par le tas selon le comparateur choisi
	 */
	public Jeu prend() {
		nombrePositionTraite++;
		return file.poll();
	}

	/**
	 * Fonction d'ajout d'un jeu
	 * @param p
	 * Le jeu a ajouter
	 * @return
	 * Un booleen attestant de l'ajout du jeu
	 */
	public boolean ajout(Jeu p) {
		return file.add(p);
	}

	/**
	 * Getter nombre de positions traitees
	 * @return
	 * Le nombre de poisitions traitees
	 */
	public int positionTraite() {
		return nombrePositionTraite;
	}

	/**
	 * Fonction de premier ajout
	 * <p>
	 * Fais appel a ajout(Jeu p)
	 * </p>
	 */
	public boolean premierAjout(Jeu initial) {
		return ajout(initial);
	}

	/**
	 * Fonction d'appartenance d'un jeu a l'ensemble
	 * @param p
	 * Le jeu sur lequel il faut tester l'appartenance
	 * @return
	 * true si le jeu est dans le tas, false sinon
	 */
	public boolean appartient(Jeu p) {
		return file.contains(p);
	}

}
