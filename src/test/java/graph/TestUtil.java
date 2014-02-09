package annas.test.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.graph.util.Util;
import annas.math.Matrix;
import annas.math.stats.Statistics;

public class TestUtil {

	private GraphInterface<String, DefaultArc<String>> graph;

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;
	private String i;

	@Before
	public void setUp() throws Exception {
		this.graph = new DirectedGraph<String, DefaultArc<String>>();

		this.a = new String("A");
		this.b = new String("B");
		this.c = new String("C");
		this.d = new String("D");
		this.e = new String("E");
		this.f = new String("F");
		this.g = new String("G");
		this.h = new String("H");
		this.i = new String("I");

	}

	@After
	public void tearDown() throws Exception {

		this.a = null;
		this.b = null;
		this.c = null;
		this.d = null;
		this.e = null;
		this.f = null;
		this.h = null;
		this.i = null;
		this.graph = null;

		this.graph = null;
		System.gc();
	}



	@Test
	public final void testGetLapacianMatrix() {
		graph = new UndirectedGraph<String, DefaultArc<String>>();
		this.graph.addNode(a);
		this.graph.addNode(b);
		this.graph.addNode(c);
		this.graph.addNode(d);
		this.graph.addNode(e);
		this.graph.addNode(f);

		this.graph.addArc(a, b, new DefaultWeight(1d));
		this.graph.addArc(a, e, new DefaultWeight(1d));
		this.graph.addArc(e, b, new DefaultWeight(1d));
		this.graph.addArc(e, d, new DefaultWeight(1d));
		this.graph.addArc(d, c, new DefaultWeight(1d));
		this.graph.addArc(c, b, new DefaultWeight(1d));
		this.graph.addArc(d, f, new DefaultWeight(1d));

		System.out.println(this.graph.getNuArcs());
		Util<String, DefaultArc<String>> util = new Util<String, DefaultArc<String>>(
				this.graph);
		Matrix m = util.getLapacianMatrix();
		float[][] j = m.getMatrix();
		Statistics S = new Statistics();
		for (int i = 0; i < j.length; i++) {
			S.add(j[i]);
			assertTrue(S.sum() == 0);
		}

	}

	@Test
	public final void testGetAdjacentMatrix() {
		this.graph = new DirectedGraph<String, DefaultArc<String>>();
		this.graph.addNode(a);
		this.graph.addNode(b);
		this.graph.addNode(c);
		this.graph.addNode(d);
		this.graph.addNode(e);
		this.graph.addNode(f);
		Matrix t = new Matrix(new float[][] {
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f } });
		Util<String, DefaultArc<String>> util = new Util<String, DefaultArc<String>>(
				this.graph);
		assertTrue(util.getAdjacentMatrix().eq(t));

		this.graph.addArc(a, b, new DefaultWeight(1d));
		this.graph.addArc(a, e, new DefaultWeight(1d));
		this.graph.addArc(e, b, new DefaultWeight(1d));
		this.graph.addArc(e, d, new DefaultWeight(1d));
		this.graph.addArc(d, c, new DefaultWeight(1d));
		this.graph.addArc(c, b, new DefaultWeight(1d));
		this.graph.addArc(d, f, new DefaultWeight(1d));

		t = new Matrix(new float[][] { { 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
				{ 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f },
				{ 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f },
				{ 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f } });
		assertTrue(util.getAdjacentMatrix().eq(t));
	}

	@Test
	public final void testGetNumPathNLong() {
		this.graph = new DirectedGraph<String, DefaultArc<String>>();
		this.graph.addNode(a);
		this.graph.addNode(b);
		this.graph.addNode(c);
		this.graph.addNode(d);
		this.graph.addNode(e);
		this.graph.addNode(f);

		this.graph.addArc(a, b, new DefaultWeight(1d));
		this.graph.addArc(b, c, new DefaultWeight(1d));
		this.graph.addArc(c, d, new DefaultWeight(1d));
		this.graph.addArc(d, e, new DefaultWeight(1d));
		this.graph.addArc(e, f, new DefaultWeight(1d));

		Util<String, DefaultArc<String>> util = new Util<String, DefaultArc<String>>(
				this.graph);

		assertTrue(count(util.getNumPathNLong(1)) == 5);
		assertTrue(count(util.getNumPathNLong(2)) == 4);
		assertTrue(count(util.getNumPathNLong(3)) == 3);
		assertTrue(count(util.getNumPathNLong(4)) == 2);
		assertTrue(count(util.getNumPathNLong(5)) == 1);

	}

	private int count(Matrix m) {
		int h = 0;
		for (int i = 0; i < m.getMatrix().length; i++) {
			for (int j = 0; j < m.getMatrix()[0].length; j++) {
				if (m.getMatrix()[i][j] != 0) {
					h++;
				}
			}
		}
		return h;
	}



	@Test
	public final void testGetEigenVectorCentrality() {
		this.graph = new DirectedGraph<String, DefaultArc<String>>();
		this.graph.addNode(a);
		this.graph.addNode(b);
		this.graph.addNode(c);
		this.graph.addNode(d);
		this.graph.addNode(e);
		this.graph.addNode(f);

		this.graph.addArc(a, b, new DefaultWeight(1d));
		this.graph.addArc(b, c, new DefaultWeight(1d));
		this.graph.addArc(c, d, new DefaultWeight(1d));
		this.graph.addArc(d, e, new DefaultWeight(1d));
		this.graph.addArc(e, f, new DefaultWeight(1d));
		Util<String, DefaultArc<String>> util = new Util<String, DefaultArc<String>>(
				this.graph);
		
		float[] y = (util.getEigenVectorCentrality());
		float[] x = {1.0f, -5.2980905E-8f, -1.2246295E-6f, -1.4296941E-5f, 3.4414566E-4f, 0.027700003f};
		for(int i = 0; i<6; i++){
	        assertEquals( y[i], x[i], 0.00000000000001);

		}
		
	}

}
