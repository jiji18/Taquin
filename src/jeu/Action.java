package jeu;

public class Action {
	String action;
	Action inverse;
	
	/**
	 * Constructeur d'une action sans inverse
	 * @param pAction
	 * Le nom de l'action
	 */
	public Action(String pAction){
		action=pAction;
		//Dans ce cas l'action inverse est nulle
		inverse=null;
	}
	
	/**
	 * Constructeur d'une action avec son inverse
	 * @param pAction
	 * Le nom de l'action a construire
	 * @param pInverse
	 * Un pointeur vers l'action inverse
	 */
	public Action(String pAction, Action pInverse){
		action=pAction;
		inverse=pInverse;
	}

	/**
	 * Getter nom
	 * @return
	 * Le nom de l'action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Getter inverse
	 * @return
	 * Un pointeur vers l'action inverse
	 */
	public Action getInverse() {
		return inverse;
	}
	
	/**
	 * Setter inverse
	 * @param inverse
	 * Un pointeur sur l'action inverse de l'action courante
	 */
	public void setInverse(Action inverse) {
		this.inverse = inverse;
	}

	/**
	 * Retourne un hashcode de l'action
	 */
	public int hashCode(){
		return action.hashCode();
	}
	
	/**
	 * Fonction d'egalite
	 * @param o
	 * L'objet a comparer
	 * @return
	 * true si o = this, false sinon
	 */
	public boolean equals(Object o){
		if (o instanceof Action){
			return ((Action) o).getAction().hashCode()==hashCode();
		}
		return false;
	}
	
}
