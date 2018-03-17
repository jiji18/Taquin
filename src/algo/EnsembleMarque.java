package algo;

import jeu.*;

public interface EnsembleMarque {
	void ajout(Jeu pSommet);
	boolean appartient(Jeu pATester);
	int taille();
}
