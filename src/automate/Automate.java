package automate;

import java.util.ArrayList;

import jeu.Action;
import jeu.Jeu;

public interface Automate {
	public boolean suivant(Jeu pJeu, Action action);
	public ArrayList<String> getFail();
	public String getChemin();
}
