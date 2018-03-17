package jeu;

import java.util.HashMap;

public class PositionFinale {
	private HashMap<Integer, int[]> damierFin;
	
	public PositionFinale(HashMap<Integer, int[]> pPosFinale){
		damierFin = pPosFinale;
	}

	public HashMap<Integer, int[]> getDamierFin() {
		return damierFin;
	}

	public void setDamierFin(HashMap<Integer, int[]> damierFin) {
		this.damierFin = damierFin;
	}
	
	public boolean equals(int [][] damier){
		for(int i=0; i<damier.length;i++)
			for(int j=0; j<damier[0].length; j++)
				if ((damierFin.get(damier[i][j])[0]!=i)||(damierFin.get(damier[i][j])[1]!=j))
					return false;
		return true;
	}
}
