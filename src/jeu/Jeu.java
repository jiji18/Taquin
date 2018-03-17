package jeu;

import exceptions.MauvaiseTouche;
import java.util.ArrayList;
public interface Jeu {
	/**
	 * Effectuer un deplacement
	 * @param direction
	 * L'entier correspondant au deplacement
	 * @throws MauvaiseTouche
	 * On leve une exception dans le cas ou l'utilisateur tape une mauvaise touche
	 * @throws IndexOutOfBoundsException
	 * Une exception est levee si le deplacement est impossible
	 */
	public void deplacement(Action direction) throws IndexOutOfBoundsException, MauvaiseTouche;
	/**
	 * Affiche le jeu
	 * @return
	 * Une chaine de caractere representant le jeu
	 */
	public String toString();
	/**
	 * Permet de savoir si un jeu est resolu
	 * @return
	 * Un boolean true si le jeu est resolu, false sinon
	 */
	public boolean estResolu();
	
	public int nbPermutFin();
	
	public boolean estSoluble();
	
	public Jeu getPere();
	
	public Action getAction();
	
	public int getProfondeur();
	
	public ArrayList<Jeu> succ();
	
	public boolean equals(Object o);
	
	public int hashCode();
	
	public int getNbCoupsfinale();
	
	public Commande getCommande();
	
	public String description();
	
	public Jeu clone();
	
}
