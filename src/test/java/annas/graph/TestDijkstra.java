package annas.graph;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.GraphPath;
import annas.graph.UndirectedGraph;
import annas.graph.util.Dijkstra;

public class TestDijkstra {

	private UndirectedGraph<String, DefaultArc<String>> graph;
	
	private Dijkstra<String, DefaultArc<String>> dij;
	
	
	String a = "A"; //u0
	String b = "B";//u1
	String c = "C";//u2
	String d = "D";//u3
	String e = "E";//u4
	String f = "F";//u5
	
	GraphPath<String, DefaultArc<String>> gp;

	@Before
	public void setUp() throws Exception {
		
		 graph = new UndirectedGraph<String, DefaultArc<String>>();

		

		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);

		graph.addArc(a, c, new DefaultWeight(13d));
		graph.addArc(a, f, new DefaultWeight(8d));
		graph.addArc(a, e, new DefaultWeight(16d));
		graph.addArc(b, f, new DefaultWeight(10d));
		graph.addArc(b, d, new DefaultWeight(6d));
		graph.addArc(c, e, new DefaultWeight(11d));
		graph.addArc(c, d, new DefaultWeight(14d));		
		graph.addArc(d, e, new DefaultWeight(5d));
		graph.addArc(d, f, new DefaultWeight(17d));
		graph.addArc(e, f, new DefaultWeight(7d));
		
		dij = new Dijkstra<String, DefaultArc<String>>(graph);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void AB(){
		this.gp = dij.execute(a, b);
		assertTrue(gp.size() == 3);
		assertTrue(gp.getDistance() == 18);		
	}
	
	@Test
	public void AC(){
		this.gp = dij.execute(a, c);
		assertTrue(gp.size() == 2);
		assertTrue(gp.getDistance() == 13);		
	}
	
	@Test
	public void AD(){
		this.gp = dij.execute(a, d);
		assertTrue(gp.size() == 4);
		assertTrue(gp.getDistance() == 20);		
	}
	
	@Test
	public void AE(){
		this.gp = dij.execute(a, e);
		assertTrue(gp.size() == 3);
		assertTrue(gp.getDistance() == 15);		
	}
	
	@Test
	public void AF(){
		this.gp = dij.execute(a, f);
		assertTrue(gp.size() == 2);
		assertTrue(gp.getDistance() == 8);		
	}


}
