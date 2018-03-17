package junit;

import jeu.Commande;
import jeu.Taquin;
import main.Main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

@AxisRange(min = 0, max = 0.05)
@BenchmarkMethodChart(filePrefix = "testMethodeTaquin")
@BenchmarkOptions(callgc = false, benchmarkRounds = 1)

public class TaquinSpeed{
	Commande commande= new Commande();
	Taquin taq1;
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
		
	@Before
	public void setUp(){
		taq1 = (Taquin)Main.jeuFromFile("taquin/taq7.taq");
	}
	
	@Test
	public void testSolvable(){
		taq1.estSoluble();
	}
	@Test
	public void testManhattan(){
		taq1.nbPermutFin();
	}
	
	@Test
	public void testResolu(){
		taq1.estResolu();
	}
	
}
