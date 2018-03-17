package algo;

import jeu.*;

public interface EnsembleATraiter {
	public boolean nonVide();
	public Jeu prend();
	public boolean premierAjout(Jeu initial);
	public boolean ajout(Jeu p);
	public int positionTraite();
	public boolean appartient(Jeu p);
}
