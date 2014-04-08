package annas.graph;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.GraphPath;
import annas.graph.UndirectedGraph;
import annas.graph.util.Floyd;
import annas.math.Matrix;

public class TestFloyd {

	private GraphInterface<String, DefaultArc<String>> graph;

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;

	private Floyd<String, DefaultArc<String>> floyd;

	@Before
	public void setup() {
		this.graph = new UndirectedGraph<String, DefaultArc<String>>();

		this.a = new String("A");
		this.b = new String("B");
		this.c = new String("C");
		this.d = new String("D");
		this.e = new String("E");
		this.f = new String("F");

		this.graph.addNode(a);
		this.graph.addNode(b);
		this.graph.addNode(c);
		this.graph.addNode(d);
		this.graph.addNode(e);
		this.graph.addNode(f);

		this.graph.addArc(a, b, new DefaultWeight(5d));
		this.graph.addArc(a, f, new DefaultWeight(1d));
		this.graph.addArc(a, d, new DefaultWeight(7d));
		this.graph.addArc(a, e, new DefaultWeight(3d));
		this.graph.addArc(b, f, new DefaultWeight(1d));
		this.graph.addArc(b, c, new DefaultWeight(4d));
		this.graph.addArc(c, f, new DefaultWeight(1d));
		this.graph.addArc(c, d, new DefaultWeight(2d));
		this.graph.addArc(d, f, new DefaultWeight(50d));
		this.graph.addArc(d, e, new DefaultWeight(3d));


		this.floyd = new Floyd<String, DefaultArc<String>>(this.graph);

	}

	@Test
	public void testGetDistance() {
		Matrix m = new Matrix(this.floyd.getD());
		m.print();
		assertTrue(this.floyd.getDistance(a, b) == 2d);
		assertTrue(this.floyd.getDistance(a, c) == 2d);
		assertTrue(this.floyd.getDistance(a, d) == 4d);
		assertTrue(this.floyd.getDistance(a, e) == 3d);
		assertTrue(this.floyd.getDistance(a, f) == 1d);
		assertTrue(this.floyd.getDistance(b, c) == 2d);
		assertTrue(this.floyd.getDistance(b, d) == 4d);
		assertTrue(this.floyd.getDistance(b, e) == 5d);
		assertTrue(this.floyd.getDistance(b, f) == 1d);
		assertTrue(this.floyd.getDistance(c, d) == 2d);
		assertTrue(this.floyd.getDistance(c, e) == 5d);
		assertTrue(this.floyd.getDistance(c, f) == 1d);
		assertTrue(this.floyd.getDistance(d, e) == 3d);
		assertTrue(this.floyd.getDistance(d, e) == 3d);
		assertTrue(this.floyd.getDistance(e, f) == 4d);
	}

	@Test
	public void testGetRoute() {
		GraphPath<String, DefaultArc<String>> gp = this.floyd.getRoute(a, c);

		assertTrue(gp != null);
		assertTrue(gp.size() == 3);
		Iterator<String> i = gp.getIterator();

		assertTrue(i.next() == this.a);
		assertTrue(i.next() == this.f);
		assertTrue(i.next() == this.c);

	}

}
