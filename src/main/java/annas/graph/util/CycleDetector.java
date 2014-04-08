package annas.graph.util;

import java.util.Hashtable;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Detects cycles in a graph, this classes uses a depth first search to discover
 * cycles.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class CycleDetector<N, A extends ArcInterface<N>> {

	/**
	 * Graph to detect cycles in
	 */
	protected GraphInterface<N, A> graph;

	private Hashtable<N, Integer> map;

	public CycleDetector(GraphInterface<N, A> graph) {
		super();
		this.graph = graph;
		this.map = new Hashtable<N, Integer>();
	}

	/**
	 * Executes the algorithm
	 * 
	 * @return true if the algorithm discovers a cycles
	 */
	public boolean containsCycle() {

		for (N node : this.graph.getNodeMap())
			map.put(node, -1);

		for (N node : this.graph.getNodeMap()) {
			if (this.map.get(node) == -1) {
				if (this.visit(node)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean visit(N node) {
		this.map.put(node, 0);

		for (A arc : this.graph.getArc(node)) {
			Integer h = this.map.get(arc.getHead());
			if (h == 0) {
				return true;
			} else if (h == -1) {
				if (this.visit(arc.getHead())) {
					return true;
				}
			}
		}
		this.map.put(node, 1);
		return false;

	}
}
