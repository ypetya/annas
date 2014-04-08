package annas.graph;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.DirectedGraph;
import annas.graph.UndirectedGraph;
import annas.graph.util.GraphHelper;

public class TestGraphHelper {

	private DirectedGraph<String, DefaultArc<String>> dirg;

	private UndirectedGraph<String, DefaultArc<String>> Undg;

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;

	@Before
	public void setUp() throws Exception {
		this.dirg = new DirectedGraph<String, DefaultArc<String>>();
		this.Undg = new UndirectedGraph<String, DefaultArc<String>>();

		this.a = new String("A");
		this.b = new String("B");
		this.c = new String("C");
		this.d = new String("D");
		this.e = new String("E");
		this.f = new String("F");
		this.g = new String("G");
	}

	@After
	public void tearDown() throws Exception {
		this.a = null;
		this.b = null;
		this.c = null;
		this.d = null;
		this.e = null;
		this.f = null;
		this.g = null;

		this.dirg = null;
		this.Undg = null;
		System.gc();
	}

	@Test
	public void testToDirected() {
		this.Undg.addNode(a);
		this.Undg.addNode(b);
		this.Undg.addNode(c);
		this.Undg.addNode(d);
		this.Undg.addNode(e);

		this.Undg.addArc(a, b, new DefaultWeight(1d));
		this.Undg.addArc(b, c, new DefaultWeight(1d));
		this.Undg.addArc(c, c, new DefaultWeight(1d));

		assertTrue(this.Undg.getNuArcs() == 6);

		DirectedGraph<String, DefaultArc<String>> dir = GraphHelper
				.toDirected(this.Undg);

		dir.addArc(c, e, new DefaultWeight(1d));

		assertTrue(dir.getNuArcs() == 7);
	}

	@Test
	public void testToUndirected() throws IllegalArgumentException,
			SecurityException, IllegalAccessException, NoSuchFieldException {
		this.dirg.addNode(a);
		this.dirg.addNode(b);
		this.dirg.addNode(c);
		this.dirg.addNode(d);
		this.dirg.addNode(e);

		this.dirg.addArc(a, b, new DefaultWeight(1d));
		this.dirg.addArc(b, c, new DefaultWeight(1d));
		this.dirg.addArc(c, c, new DefaultWeight(1d));

		assertTrue(this.dirg.getNuArcs() == 3);

		UndirectedGraph<String, DefaultArc<String>> und = GraphHelper
				.toUndirected(this.dirg);

		und.addArc(c, e, new DefaultWeight(1d));

		assertTrue(this.dirg.getNuArcs() == 5);

	}


}
