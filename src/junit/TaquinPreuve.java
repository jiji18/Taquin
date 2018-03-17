package junit;

import static org.junit.Assert.*;
import jeu.*;

import org.junit.Test;

import exceptions.MauvaiseTouche;

public class TaquinPreuve {
	private Commande commande=new Commande();
	
	@Test
	public void testSolvableCarre() {
		for(int i=2; i<20; i++){
			assertTrue("Doit etre resolu", (new Taquin(i,i,commande)).estSoluble());
		}
		
	}

}
