package algo;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;

import jeu.Action;
import jeu.Jeu;
import automate.Automate;
import automate.Noeud;

public class Algo extends Thread{
	private EnsembleMarque marque;
	private EnsembleATraiter aTraiter;
	private Jeu initial;
	private Jeu finale;
	private ArrayList<Action> solution;
	private long tempExec;
	private Automate automate;
	private int nombrePositionsTraite;
	private int nbIterations;
	private int PROF_AUTOMATE = Integer.MAX_VALUE;
	
	/**
	 * Constructeur d'un Algo
	 * @param pInit
	 * Le jeu qu'il faut resoudre dans son etat initial
	 * @param pTraiter
	 * L'ensemble a� traiter a utiliser
	 * @param pMarque
	 * L'ensemble marque a utiliser
	 * @param pAutomate
	 * Un booleen pour savoir si l'algorithme utilise un automate ou non
	 */
	public Algo(Jeu pInit, EnsembleATraiter pTraiter, EnsembleMarque pMarque, boolean pAutomate){
		this.initial=pInit;
		aTraiter=pTraiter;
		marque=pMarque;
		solution=new ArrayList<Action>();
		if(pAutomate)
			//On crée l'automate
			automate = new Noeud(pTraiter,pInit.getCommande());
		else
			//Sinon on passe l'automate à null
			automate=null;
		nombrePositionsTraite=0;
		nbIterations = 0;
	}
	
	/**
	 * Methode run progressif
	 * <p>
	 * Cette methode vaut pour la partie E du sujet
	 * </p>
	 */
	public void runProgressif(){
		aTraiter=new PileAction(initial.getNbCoupsfinale());
		while(!run(0)){
			System.out.println("On relance un parcours");
			aTraiter=new PileAction(initial.getNbCoupsfinale()+1);
		}
	}
	
	
	/**
	 * Fonction run de l'algo
	 * <p>
	 * C'est cette fonction qui permet la resolution du jeu
	 * </p>
	 * @param temps
	 * C'est le temps imparti pour que l'algo trouve une solution, ou 0 s'il n'y a pas de limite
	 * @return
	 * On retourne un booleen pour savoir si l'algorithme a trouve une solution ou pas
	 */
	public boolean run(int temps){
		int nbUtilAutomate=0;
		Timer t = new Timer();
		if(temps != 0) {
			t.schedule(new Arret(), temps);
		}
		long timeDeb=System.currentTimeMillis();
		System.out.println("Taquin depart :\n"+initial);
			boolean fin=false;
			marque.ajout(this.initial);
			aTraiter.premierAjout(initial);
			ArrayList<Jeu> succ=initial.succ();
			for(Jeu p : succ)
				aTraiter.ajout(p);
			while(aTraiter.nonVide() && !fin){
				Jeu pos = aTraiter.prend();
				succ = pos.succ();
				if(automate!=null && nbUtilAutomate<=PROF_AUTOMATE){
					ArrayList<Jeu> succR = reduireSucc(succ);
					succ=succR;
					nbUtilAutomate++;
				}
				for(Jeu p: succ){
					nbIterations++;
					if(!marque.appartient(p)){
						if(p.estResolu()){
							System.out.println("On a une solution");
							fin=true;
							finale=p;
							Toolkit.getDefaultToolkit().beep();
							System.out.println("Solution :\n"+p);
						}else{
							marque.ajout(p);
							aTraiter.ajout(p);
						}
					}
				}
			setSolution();
		}
		long timeFin = System.currentTimeMillis();
		tempExec=timeFin-timeDeb;
		t.cancel();
		return fin;
	}
	
	/**
	 * Fonction de description
	 * <p>
	 * Recapitule le deroulement de d'algorithme
	 * </p>
	 * @return
	 * Un string pour pouvoir afficher la description
	 */
	public String description() {
		if(finale==null)
			return "L'algorithme n'a pas trouvé de solution";
		return initial.description() + "\n\nL'algorithme a dure " + nbIterations
				+ " iterations, au cours desquelles il a traite " + aTraiter.positionTraite()
				+ " positions du jeu.\nSon execution a pris " + tempExec
				+ " ms.\n\n"+((automate!=null)?"Au cours de son execution, l'algorithme est parti "
				+ (automate.getFail().size() - 4)
				+ " fois dans une mauvaise direction.\n\nAu final, il aura supprime " + automate.getFail().size()
				+ " combinaisons de coups redondants afin de donner une solution optimale de longueur "
				+ getStringSolution().length():"") + ".\nChemin : " + getStringSolution();
	}
	
	/**
	 * Getter sur l'automate
	 * @return
	 * L'automate
	 */
	public Automate getAutomate() {
		return automate;
	}
	
	/**
	 * Setter de l'automate
	 * @param automate
	 * L'automate a utiliser
	 */
	public void setAutomate(Automate automate) {
		this.automate = automate;
	}
	
	/**
	 * Fonction d'elagage
	 * <p>
	 * C'est cette methode qui fait appel a l'automate
	 * </p>
	 * @param aReduire
	 * La liste des positions a traiter
	 * @return
	 * La liste avec les positions
	 */
	private ArrayList<Jeu> reduireSucc(ArrayList<Jeu> aReduire){
		ArrayList<Jeu> res = new ArrayList<Jeu>();
		for(Jeu j : aReduire){
			//System.out.println("action de j : "+ j.getAction());
			if (automate.suivant(j, j.getAction()))
				res.add(j);
		}
		return res;
	}
	
	/**
	 * Fonction d'initialisation de la solution
	 * <p>
	 * Cette fonction permet de remonter tous les peres a partir de l'etat final jusqu'au dernier pour avoir le chemin menant a la solution
	 * </p>
	 */
	public void setSolution(){
		Jeu parcours=finale;
		Stack<Action> temp = new Stack<Action>();
		while(parcours!=null){
			temp.push(parcours.getAction());
			parcours=parcours.getPere();
		}
		while(!temp.isEmpty()){
			solution.add(temp.pop());
		}
		
	}
	
	
	/**
	 * Fonction toString de la solution
	 * @return
	 * Une chaine de caractere representant la solution
	 */
	public String getStringSolution(){
		String s="";
		for(Action act : solution){
			if(act!=null)
				s+=act.getAction();
		}
		return s;
	}
	
	public Jeu getInitial() {
		return initial;
	}

	/**
	 * Getter solution
	 * @return
	 * La solution sous forme d'une ArrayList
	 */
	public ArrayList<Action> getSolution(){
		return solution;
	}
	
	/**
	 * Getter nombre de positions traitees
	 * @return
	 * Le nombre de positions traitees
	 */
	public int getNombrePositionTraite(){
		return this.aTraiter.positionTraite();
	}
	
	/**
	 * Getter nombre de positions traitees
	 * @return
	 * Le nombre de positions traitees
	 */
	public int getNombrePositionsTraite() {
		return nombrePositionsTraite;
	}

	/**
	 * Getter position finale
	 * @return
	 * Retourne la position finale trouvee par l'algorithme
	 */
	public Jeu getFinale() {
		return finale;
	}

	/**
	 * Getter temps d'execution
	 * @return
	 * Le temps qu'a mis l'algorithme pour trouver la solution
	 */
	public long getTempExec() {
		return tempExec;
	}
	
	public void setProfondeurAutomate(int pProf){
		PROF_AUTOMATE=pProf;
	}
}
