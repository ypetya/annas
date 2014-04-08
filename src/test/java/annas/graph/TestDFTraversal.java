package annas.graph;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.graph.util.traversal.DepthFirst;

public class TestDFTraversal {

	private GraphInterface<String, DefaultArc<String>> graph;

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;

	private DepthFirst<String, DefaultArc<String>> traversal;

	@Before
	public void setUp() throws Exception {
		this.graph = new UndirectedGraph<String, DefaultArc<String>>();

		this.a = new String("A");
		this.b = new String("B");
		this.c = new String("C");
		this.d = new String("D");
		this.e = new String("E");
		this.f = new String("F");
		this.g = new String("G");

		this.graph.addNode(a);
		this.graph.addNode(b);
		this.graph.addNode(c);
		this.graph.addNode(d);
		this.graph.addNode(e);
		this.graph.addNode(f);
		this.graph.addNode(g);

		this.graph.addArc(a, b, new DefaultWeight(1d));
		this.graph.addArc(a, c, new DefaultWeight(1d));
		this.graph.addArc(b, d, new DefaultWeight(1d));
		this.graph.addArc(b, e, new DefaultWeight(1d));
		this.graph.addArc(c, f, new DefaultWeight(1d));
		this.graph.addArc(c, g, new DefaultWeight(1d));

		this.traversal = new DepthFirst<String, DefaultArc<String>>(this.graph);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRunN() {
		Iterator<String> i = this.traversal.run(a);

		assertTrue(i.next() == a);
		assertTrue(i.next() == b);
		assertTrue(i.next() == d);
		assertTrue(i.next() == e);
		assertTrue(i.next() == c);
		assertTrue(i.next() == f);
		assertTrue(i.next() == g);
	}

	@Test
	public void testRunNN() {
		Iterator<String> i = this.traversal.run(a, e);

		assertTrue(i.next() == a);
		assertTrue(i.next() == b);
		assertTrue(i.next() == d);
		assertTrue(i.next() == e);
	}

}
