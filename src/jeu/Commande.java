package jeu;

import java.util.HashMap;
import java.util.Set;

public class Commande {
	//Cette map fais le lien entre une action et ces effets
	private HashMap<Action, int[]> deplacement;
	private Set<Action> listeDesClefs;
	//C'est un tableau de pointeurs vers des actions, permet un accés plus facile
	private Action[] tabClef;
	//Cette map fais le lien entre les touches et les actions
	private HashMap<String, Action> tabCorrespondance;

	/**
	 * Contructeur de commande
	 * <p>
	 * Construit l'ensemble des commandes disponibles pour le jeu ainsi que leurs relations (coups inverses)
	 * </p>
	 */
	public Commande(){
		Action n=new Action("N");
		Action s =new Action("S");
		Action e = new Action("E");
		Action o = new Action("O");
		n.setInverse(s);
		s.setInverse(n);
		e.setInverse(o);
		o.setInverse(e);
		
		
		deplacement=new HashMap<Action, int[]>();
		int[] t1=new int[2];
		t1[0]=-1;t1[1]=0;
		deplacement.put(n, t1);
		
		int[] t2=new int[2];
		t2[0]=1;t2[1]=0;
		deplacement.put(s, t2);
		
		int[] t3=new int[2];
		t3[0]=0;t3[1]=1;
		deplacement.put(e, t3);
		
		int[] t4=new int[2];
		t4[0]=0;t4[1]=-1;
		deplacement.put(o, t4);

		listeDesClefs=deplacement.keySet();
		tabClef = new Action[listeDesClefs.size()];
		tabClef[0]=n;
		tabClef[1]=s;
		tabClef[2]=o;
		tabClef[3]=e;
		
		//Initialisation du tableau des correspondance entre les touches tappé et l'action souhaité
		tabCorrespondance= new HashMap<String,Action>();
		
		tabCorrespondance.put("z",tabClef[0]);
		tabCorrespondance.put("s",tabClef[1]);
		tabCorrespondance.put("q",tabClef[2]);
		tabCorrespondance.put("d",tabClef[3]);
	}

	public HashMap<String, Action> getTabCorrespondance() {
		return tabCorrespondance;
	}

	public void setTabCorrespondance(HashMap<String, Action> tabCorrespondance) {
		this.tabCorrespondance = tabCorrespondance;
	}

	public HashMap<Action, int[]> getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(HashMap<Action, int[]> deplacement) {
		this.deplacement = deplacement;
	}
	
	public Set<Action> getListeDesClefs() {
		return listeDesClefs;
	}

	public void setListeDesClefs(Set<Action> listeDesClefs) {
		this.listeDesClefs = listeDesClefs;
	}

	public Action[] getTabClef() {
		return tabClef;
	}

	public void setTabClef(Action[] tabClef) {
		this.tabClef = tabClef;
	}
	
	
}
