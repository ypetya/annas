package annas.graph.generate;

import java.util.ArrayList;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

public class LineGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	private GraphInterface<N, A> graph;

	public LineGraphGenerator(GraphInterface<N, A> graph) {
		super();
		this.graph = graph;
	}

	private GraphInterface<A, DefaultArc<A>> generate() {
		DirectedGraph<A, DefaultArc<A>> dirg = new DirectedGraph<A, DefaultArc<A>>();
		ArrayList<A> arcs = this.getArcs();

		for (A a : arcs)
			dirg.addNode(a);

		for (int i = 0; i < arcs.size(); i++) {
			for (int j = 0; j < arcs.size(); j++) {
				if (i != j) {
					if (this.areAdjacent(arcs.get(i), arcs.get(j))
							&& !this.alreadyContains(dirg, arcs.get(i), arcs
									.get(j))) {
						dirg.addArc(arcs.get(i), arcs.get(j),
								new DefaultWeight(1.0));
					}
				}
			}
		}

		return dirg;
	}

	private ArrayList<A> getArcs() {
		ArrayList<A> retval = new ArrayList<A>();

		ArrayList<N> nodes = this.graph.getNodeMap();
		for (N n : nodes)
			retval.addAll(this.graph.getArc(n));

		return retval;
	}

	private boolean alreadyContains(GraphInterface graph, A a1, A a2) {
		return graph.getArc(a1, a2).size() > 0;

	}

	private boolean areAdjacent(A a1, A a2) {
		if ((a1.getHead() == a2.getHead() || a1.getHead() == a2.getTail())
				|| (a1.getTail() == a2.getHead() || a1.getTail() == a2
						.getTail()))
			return true;
		return false;
	}

	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory,
			Map<String, Object> map) {
		this.graph = (GraphInterface<N, A>) this.generate();
		target = this.graph;
	}
}
