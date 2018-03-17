package junit;

import com.carrotsearch.junitbenchmarks.*;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

import comparateurs.*;
import jeu.*;
import algo.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "EnsembleIncompletDiffTaille")
@BenchmarkOptions(callgc = false, benchmarkRounds = 10000, warmupRounds = 1)

public class EnsembleIncompletTest{
	Commande commande= new Commande();
	Taquin taq1;
	int[] nbPremier={149,1213,13997,21061,200383};
	EnsembleATraiter eat = new Tas(new Manhattan());
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	
	@Before
	public void setUp() throws Exception {
		taq1= new Taquin(4, 4,commande);
	}

	@Test
	public void T149(){
		runAlgo(taq1, new EnsembleIncomplet(149));
	}
	
	@Test
	public void T1213(){
		runAlgo(taq1, new EnsembleIncomplet(1213));
	}
	
	@Test
	public void T13997(){
		runAlgo(taq1, new EnsembleIncomplet(13997));
	}

	@Test
	public void T21061(){
		runAlgo(taq1, new EnsembleIncomplet(21061));
	}

	@Test
	public void T200383(){
		runAlgo(taq1, new EnsembleIncomplet(200383));
	}
	
	private void runAlgo(Jeu jeu, EnsembleMarque em) {
		Algo alg= new Algo(jeu, eat, em, false);
		alg.run();
		assert(alg.getFinale().estResolu());
	}

}
