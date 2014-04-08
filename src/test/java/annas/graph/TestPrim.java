package annas.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.graph.util.Prim;

public class TestPrim {

	private UndirectedGraph<String, DefaultArc<String>> graph;

	private DirectedGraph<String, DefaultArc<String>> Drigraph;

	private Prim<String, DefaultArc<String>> prim;

	String a = "A"; // u0
	String b = "B";// u1
	String c = "C";// u2
	String d = "D";// u3
	String e = "E";// u4
	String f = "F";// u5

	@Before
	public void setUp() throws Exception {
		
		this.graph = new UndirectedGraph<String, DefaultArc<String>>();

		this.Drigraph = new DirectedGraph<String, DefaultArc<String>>();

		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);

		graph.addArc(a, e, new DefaultWeight(4d));
		graph.addArc(a, c, new DefaultWeight(3d));
		graph.addArc(e, c, new DefaultWeight(0d));
		graph.addArc(c, d, new DefaultWeight(8d));
		graph.addArc(c, b, new DefaultWeight(9d));
		graph.addArc(c, f, new DefaultWeight(3d));
		graph.addArc(b, f, new DefaultWeight(5d));
		
	}

	@Test
	public void TestUndirected() {
		this.prim = new Prim<String, DefaultArc<String>>(graph);

		GraphInterface<String, DefaultArc<String>> g = this.prim.execute();

		assertTrue(g.getNuNodes() == 6);
		assertTrue(g.getNuArcs() == 10);
	}

	@Test
	public void TestDirected() {
		try {
			this.prim = new Prim<String, DefaultArc<String>>(this.Drigraph);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

}
