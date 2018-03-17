package automate;

import java.util.ArrayList;

import algo.EnsembleATraiter;
import jeu.Action;
import jeu.Commande;
import jeu.Jeu;

public class Noeud implements Automate {
	
	private EnsembleATraiter traite;
	private String chemin;
	private ArrayList<String> fail;
	
	/**
	 * Constructeur d'un noeud avec parametre
	 * @param pTraite
	 * L'ensemble sur lequel travaille l'automate
	 * @param c
	 * Un pointeur vers les commandes du jeu
	 */
	public Noeud(EnsembleATraiter pTraite, Commande c){
		fail = new ArrayList<String>();
		for(Action a: c.getTabClef()){
			fail.add(a.getAction()+a.getInverse().getAction());
		}
		traite = pTraite;
		chemin="";
	}
	
	public String getChemin() {
		return chemin;
	}

	public ArrayList<String> getFail() {
		return fail;
	}

	/**
	 * Cette methode permet de savoir si l'action que l'on fait n'est pas un coup
	 * redondant en regardant si le chemin plus la nouvelle action n'est pas dans 
	 * notre fail ou n'a pas deja ete traite, auquel cas il rajoute ce nouveau
	 * chemin dans notre fail.
	 * @param pJeu
	 * Le jeu a tester
	 * @param action
	 * L'action qui y mène
	 * @return
	 * un boolean
	 */
	public boolean suivant(Jeu pJeu, Action action) {
		if (!fail.contains(chemin+action.getAction())){
			if(traite.appartient(pJeu)){
				fail.add(chemin+action.getAction());
				return false;
			}
			chemin+=action.getAction();
			return true;
		}
		return false;
	}
}
