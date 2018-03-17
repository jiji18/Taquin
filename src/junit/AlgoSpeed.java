package junit;
import static org.junit.Assert.assertNotNull;
import jeu.Commande;
import jeu.Jeu;
import main.Main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import algo.Algo;
import algo.EnsembleATraiter;
import algo.EnsembleComplet;
import algo.EnsembleIncomplet;
import algo.EnsembleMarque;
import algo.File;
import algo.Pile;
import algo.PileAction;
import algo.Tas;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import comparateurs.DepthManhattan;
import comparateurs.Manhattan;

@AxisRange(min = 0, max = 0.05)
@BenchmarkMethodChart(filePrefix = "testdesalgo")
@BenchmarkOptions(callgc = false, benchmarkRounds = 1)

public class AlgoSpeed extends AbstractBenchmark{
	static Commande commande=new Commande();
	Jeu jeu;
	int tailleEnsembleIncomplet=200383;
	int PROF_AUTOMATE=3;
		
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();

	@Before
	public void setUp(){
		jeu = Main.jeuFromFile("taquin/taq1.taq");
	}
	
	@Test
	public void RedondantManhattan(){
		Jeu j = runTest(jeu, new EnsembleComplet(), new Tas(new Manhattan()),true);
		assertNotNull("Doit etre non null",j);
	}
	
	@Test
	public void RedondantProfManhattan(){
		Jeu j = runTest(jeu, new EnsembleComplet(), new Tas(new DepthManhattan()),true);
		assertNotNull("Doit etre non null",j);
	}

	@Test
	public void PileActionIncomplet(){
		Jeu j = runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new PileAction(),false);
		assertNotNull("Doit etre non null",j);
	}
	
	@Test
	public void PileActionComplet(){
		Jeu j = runTest(jeu, new EnsembleComplet(), new PileAction(),false);
		assertNotNull("Doit etre non null",j);
	}

	
	@Test
	public void FileIncomplet(){
		Jeu j = runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new File(),false);
		assertNotNull("Doit etre non null",j);
	}
	
/*	@Test
	public void FileComplet(){
		Jeu j = runTest(jeu, new EnsembleComplet(), new File());
		assertNotNull("Doit etre non null",j);
	}*/
	
	@Test
	public void PileIncomplet(){
		Jeu j = runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new Pile(),false);
		assertNotNull("Doit etre non null",j);
	}

/*	@Test
	public void PileComplet(){
		Jeu j = runTest(jeu, new EnsembleComplet(), new Pile());
		assertNotNull("Doit etre non null",j);
	}*/
	
	@Test
	public void ManhattanIncomplet(){
		Jeu j = runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new Manhattan()),false);
		assertNotNull("Doit etre non null",j);
	}
	
	@Test
	public void ManhattanComplet(){
		Jeu j = runTest(jeu, new EnsembleComplet(), new Tas(new Manhattan()),false);
		assertNotNull("Doit etre non null",j);
	}
	
	@Test
	public void PManhattanIncomplet(){
		Jeu j = runTest(jeu, new EnsembleIncomplet(tailleEnsembleIncomplet), new Tas(new DepthManhattan()),false);
		assertNotNull("Doit etre non null",j);
	}
	
	@Test
	public void PManhattanComplet(){
		Jeu j = runTest(jeu, new EnsembleComplet(), new Tas(new DepthManhattan()),false);
		assertNotNull("Doit etre non null",j);
	}
		
	/**
	 * Execute un algo
	 * @param jeu
	 * Le jeu a resoudre
	 * @param em
	 * L'ensemble marque
	 * @param eat
	 * L'ensemble a traiter
	 */
	private Jeu runTest(Jeu jeu, EnsembleMarque em, EnsembleATraiter eat, Boolean avecAutomate){
		//System.out.println("Nouveau test en cours");
		Algo algo = new Algo(jeu, eat, em, avecAutomate);
		if(avecAutomate) algo.setProfondeurAutomate(PROF_AUTOMATE);
		algo.run(0);
		return algo.getFinale();
	}

}
