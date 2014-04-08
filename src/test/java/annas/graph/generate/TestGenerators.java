package annas.graph.generate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import annas.graph.DefaultWeight;
import annas.graph.DirectedGraph;
import annas.graph.UndirectedGraph;
import annas.graph.generate.CompleteGraphGenerator;
import annas.graph.generate.EmptyGraphGenerator;
import annas.graph.generate.HyperGraphGenerator;
import annas.graph.generate.LineGraphGenerator;
import annas.graph.generate.LinearGraphGenerator;
import annas.graph.generate.RingGraphGenerator;
import annas.graph.generate.StarGraphGenerator;
import annas.graph.generate.WheelGraphGenerator;
import annas.graph.DefaultNodeFactory;

public class TestGenerators {

	@Test
	public final void testLinearGenerator() {

		LinearGraphGenerator lg = new LinearGraphGenerator(5);
		DirectedGraph dg = new DirectedGraph();
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 5);
		assertTrue(dg.getNuArcs() == lg.getLength() - 1);

		assertTrue(lg.getStart_node() != null);
		assertTrue(lg.getEnd_node() != null);
		assertTrue(dg.getArc(lg.getStart_node()).size() == 1);
		assertTrue(dg.getArc(lg.getEnd_node()).size() == 0);

		try {
			lg.generate(null, null, null);
		} catch (NullPointerException e1) {
			fail("");
		}

		try {
			lg = new LinearGraphGenerator(-1);
			lg.generate(new DirectedGraph(), new DefaultNodeFactory(), null);

		} catch (Exception e) {
			assertTrue(e.getMessage() == "size must be >= 0");
		}


	}

	@Test
	public final void testCompleteGenerator() {

		CompleteGraphGenerator lg = new CompleteGraphGenerator(5);
		DirectedGraph dg = new DirectedGraph();
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 5);
		assertTrue(dg.getNuArcs() == 20);
		try {
			lg.generate(null, null, null);
		} catch (NullPointerException e1) {
			fail("");
		}

		try {
			lg = new CompleteGraphGenerator(-1);
			lg.generate(new DirectedGraph(), new DefaultNodeFactory(), null);

		} catch (Exception e) {
			assertTrue(e.getMessage() == "size must be >= 0");
		}

	}

	@Test
	public final void testEmptyGenerator() {

		EmptyGraphGenerator lg = new EmptyGraphGenerator(5);
		DirectedGraph dg = new DirectedGraph();
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 5);
		assertTrue(dg.getNuArcs() == 0);

		try {
			lg.generate(null, null, null);
		} catch (NullPointerException e1) {
			fail("");
		}

		try {
			lg = new EmptyGraphGenerator(-1);
			lg.generate(new DirectedGraph(), new DefaultNodeFactory(), null);

		} catch (Exception e) {
			assertTrue(e.getMessage() == "size mist be greater than 0");
		}



	}

	@Test
	public final void testHyperGenerator() {

		HyperGraphGenerator lg = new HyperGraphGenerator(3);
		DirectedGraph dg = new DirectedGraph();
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 8);
		assertTrue(dg.getNuArcs() == 12);

		try {
			lg.generate(null, null, null);
		} catch (NullPointerException e1) {
			fail("");
		}

		try {
			lg = new HyperGraphGenerator(-1);
			lg.generate(new DirectedGraph(), new DefaultNodeFactory(), null);

		} catch (Exception e) {

			assertTrue(e.getMessage() == "dimensions must be greater than 0");
		}

	}

	@Test
	public final void testStarGenerator() {

		StarGraphGenerator lg = new StarGraphGenerator(8);
		DirectedGraph dg = new DirectedGraph();
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 8);
		assertTrue(dg.getNuArcs() == 7);

		try {
			lg.generate(null, null, null);
		} catch (NullPointerException e1) {
			fail("");
		}

		try {
			lg = new StarGraphGenerator(-1);
			lg.generate(new DirectedGraph(), new DefaultNodeFactory(), null);

		} catch (Exception e) {

			assertTrue(e.getMessage() == "size must be greater than 0");
		}

	}

	@Test
	public final void testRingGenerator() {

		RingGraphGenerator lg = new RingGraphGenerator(8);
		DirectedGraph dg = new DirectedGraph();
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 8);
		assertTrue(dg.getNuArcs() == 8);

		try {
			lg.generate(null, null, null);
		} catch (NullPointerException e1) {
			fail("");
		}

		try {
			lg = new RingGraphGenerator(-1);
			lg.generate(new DirectedGraph(), new DefaultNodeFactory(), null);

		} catch (Exception e) {

			assertTrue(e.getMessage() == "size must be >= 2");
		}

	}

	@Test
	public final void testWheelGenerator() {

		WheelGraphGenerator lg = new WheelGraphGenerator(8);
		DirectedGraph dg = new DirectedGraph();
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 8);
		assertTrue(dg.getNuArcs() == 14);

		try {
			lg.generate(null, null, null);
		} catch (NullPointerException e1) {
			fail("");
		}

		try {
			lg = new WheelGraphGenerator(-1);
			lg.generate(new DirectedGraph(), new DefaultNodeFactory(), null);

		} catch (Exception e) {

			assertTrue(e.getMessage() == "size must be greater than 3");
		}

	}

	@Test
	public final void testLineGenerator() {

		
		UndirectedGraph dg = new UndirectedGraph();
		dg.addNode("A");
		dg.addNode("B");
		dg.addNode("C");
		dg.addArc("A", "B", new DefaultWeight(1d));
		dg.addArc("B", "C", new DefaultWeight(1d));
		dg.addArc("C", "A", new DefaultWeight(1d));
		LineGraphGenerator lg = new LineGraphGenerator(dg);
		lg.generate(dg, new DefaultNodeFactory(), null);

		assertTrue(dg.getNuNodes() == 3);

		assertEquals(dg.getNuArcs(), 6);

	}

}
