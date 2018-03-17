package algo;

import java.util.Stack;

import jeu.Action;
import jeu.Jeu;
import jeu.Taquin;
import exceptions.MauvaiseTouche;

public class PileAction implements EnsembleATraiter{
	private Jeu initial=null;
	private Stack<Stack <Action>> ensemble= new Stack<>();
	private int PosTraite=0;
	private int TAILLE_LIMITE=Integer.MAX_VALUE;
	
	
	/**
	 * Constructeur d'une pile avec une taille limitee
	 * @param maxSize
	 * La taille limite voulue
	 */
	public PileAction(int maxSize){
		TAILLE_LIMITE=maxSize;
	}
	
	/**
	 * Constructeur sans parametre
	 */
	public PileAction(){
		
	}
	
	/**
	 * Premier ajout d'un jeu
	 * <p>
	 * Dans le cas d'une pile d'actions, on stocke l'etat initial pour pouvoir recalculer tous les autres
	 * @param initial
	 * Le jeu initial a ajouter
	 * @return
	 * Un boolean qui permet de savoir si le jeu a ete ajoute
	 */
	public boolean premierAjout(Jeu initial){
		this.initial=initial;
		
		return true;
	}
	
	/**
	 * Fonction permettant de savoir si la liste est vide
	 * @return
	 * Un boolean true si la liste n'est pas vide, false sinon
	 */
	public boolean nonVide() {
		return !ensemble.isEmpty();
	}

	/**
	 * Prend
	 * <p>
	 * Fonction qui renvoie un jeu de l'ensemble, le type de l'ensemble differencie le type de parcours de graphe
	 * </p>
	 * @return
	 * On retourne le jeu concerne
	 */
	public Jeu prend() {
		//On part de l'état initial
		Jeu res=initial.clone();
		Action temp=null;
		//On dépile la pile d'action à effectuer pour renvoyer le bon jeu
		Stack<Action> aAppliquer=ensemble.pop();
		//On effectue tout les déplacements
		while(!aAppliquer.isEmpty()){
			try {
				temp=aAppliquer.pop();
				res.deplacement(temp);
				res=new Taquin(temp,(Taquin)res);
			} catch (IndexOutOfBoundsException | MauvaiseTouche e) {}
		}
		aAppliquer=null;
		PosTraite++;
		return res;
	}
	
	/**
	 * ajout
	 * <p>
	 * Fonction qui permet d'ajouter un jeu a l'ensemble, dans cet ensemble de pile d'actions, on ajoute les actions qui ont menees au jeu
	 * </p>
	 * @param p
	 * Le jeu a ajouter
	 * @return
	 * On retourne un booleen pour attester de l'ajout du jeu a l'ensemble
	 */
	public boolean ajout(Jeu p) {
		Stack<Action> temp = new Stack<Action>();
		while(p.getPere()!=null){
			temp.add(p.getPere().getAction());
			p=p.getPere();
		}
		//On teste la taille de la pile pour savoir si le jeu ne depasse pas la limite fixé
		if(temp.size()<TAILLE_LIMITE){
			return ensemble.add(temp);
		}
		return false;
	}
	
	/**
	 * positionTraite
	 * <p>
	 * Permet de savoir le nombre de positions qui sont sorties de l'ensemble
	 * </p>
	 * @return
	 * On retourne cet entier
	 */
	public int positionTraite() {
		return PosTraite;
	}
	
	/**
	 * Fonction qui permet de savoir si un jeu est dans la pile
	 * <p>
	 * Cette fonction est necessaire pour le fonctionnement de l'automate, mais cet ensemble ne tourne jamais avec l'automate
	 * Donc la methode retourne false pour ne pas perturber l'algorithme en supprimant des positions de jeu
	 * </p>
	 */
	public boolean appartient(Jeu p) {
		return false;
	}
}
