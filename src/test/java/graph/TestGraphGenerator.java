package annas.test.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.generate.LinearGraphGenerator;


public class TestGraphGenerator {
	
	@Test
	public void TestLinearGraphGenerator(){
		GraphInterface<String, DefaultArc<String>> target = new DirectedGraph<String, DefaultArc<String>>();
		LinearGraphGenerator<String, DefaultArc<String>> lgg = new LinearGraphGenerator<String, DefaultArc<String>>(9);
		Map m = new HashMap();
		lgg.generate(target, new DefaultNodeFactory(),m);
		
		System.out.println(m.values());
		assertTrue(target.getNuNodes() == 9);
		assertTrue(target.getNuArcs() == 8);
		
		for(int i = 0; i<9; i++){
			assertTrue(target.contains(Integer.valueOf(i).toString()));
		}
			
	}

}
