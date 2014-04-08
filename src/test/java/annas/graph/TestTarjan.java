package annas.graph;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.util.Tarjan;

public class TestTarjan {

	private GraphInterface<String, DefaultArc<String>> graph;

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;

	@Before
	public void setup() {
		this.graph = new DirectedGraph<String, DefaultArc<String>>();

		this.a = new String("A");
		this.b = new String("B");
		this.c = new String("C");
		this.d = new String("D");
		this.e = new String("E");
		this.f = new String("F");
		this.g = new String("G");
		this.h = new String("H");

		this.graph.addNode(a);
		this.graph.addNode(b);
		this.graph.addNode(c);
		this.graph.addNode(d);
		this.graph.addNode(e);
		this.graph.addNode(f);
		this.graph.addNode(g);
		this.graph.addNode(h);

		this.graph.addArc(a, b, new DefaultWeight(1d));
		this.graph.addArc(b, e, new DefaultWeight(1d));
		this.graph.addArc(e, a, new DefaultWeight(1d));
		this.graph.addArc(e, f, new DefaultWeight(1d));
		this.graph.addArc(b, f, new DefaultWeight(1d));
		this.graph.addArc(f, g, new DefaultWeight(1d));
		this.graph.addArc(g, f, new DefaultWeight(1d));
		this.graph.addArc(b, c, new DefaultWeight(1d));
		this.graph.addArc(c, g, new DefaultWeight(1d));
		this.graph.addArc(c, d, new DefaultWeight(1d));
		this.graph.addArc(d, c, new DefaultWeight(1d));
		this.graph.addArc(d, h, new DefaultWeight(1d));
		this.graph.addArc(h, d, new DefaultWeight(1d));
		this.graph.addArc(h, g, new DefaultWeight(1d));
		this.graph.addArc(c, g, new DefaultWeight(1d));

	}

	@Test
	public void testExecute() {
		Tarjan<String, DefaultArc<String>> t = new Tarjan<String, DefaultArc<String>>(
				this.graph);

		ArrayList<ArrayList<String>> k = t.execute();
		assertTrue(k.size() == 3);
	}

}
