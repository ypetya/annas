package annas.graph.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * 
 * Tarjan's Algorithm (named for its discoverer, Robert Tarjan) is a graph
 * theory algorithm for finding the strongly connected components of a graph. As
 * described in Tarjan, R.: Depth-first search and linear graph algorithms, SIAM
 * J. Com- put. 1, no. 2, pp. 146–160
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class Tarjan<N, A extends ArcInterface<N>> {

	private GraphInterface<N, A> graph;

	private Map<N, vertex<N>> verticies;

	private ArrayList<ArrayList<N>> comp;

	private int index;

	private Stack<vertex<N>> S;

	public Tarjan(GraphInterface<N, A> graph) {
		super();
		this.graph = graph;
		this.verticies = new HashMap<N, vertex<N>>();
		this.comp = new ArrayList<ArrayList<N>>();
		this.index = 0;
		this.S = new Stack<vertex<N>>();
		for (N node : this.graph.getNodeMap()) {
			this.verticies.put(node, new vertex<N>(node));
		}

	}

	/**
	 * executes Tarjan's algorithm of the suppled graph
	 * 
	 * @return A list of lists of node which belong to the same component
	 */
	public ArrayList<ArrayList<N>> execute() {
		ArrayList<vertex<N>> tmp = new ArrayList<vertex<N>>(this.verticies
				.values());
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i).notNumbered()) {
				this.execute(tmp.get(i).node);
			}
		}

		return this.comp;
	}

	private ArrayList<ArrayList<N>> execute(N n) {

		this.run(this.verticies.get(n));

		return this.comp;
	}

	private void run(vertex<N> v) {
		v.index = this.index;
		v.lowlink = this.index;
		this.index++;
		this.S.push(v);

		ArrayList<A> arcs = this.graph.getArc(v.node);

		for (A arc : arcs) {
			vertex<N> vp = this.verticies.get(arc.getHead());

			if (vp.index == -1) {
				this.run(vp);
				v.lowlink = Math.min(v.lowlink, vp.lowlink);
			} else if (this.S.contains(vp)) {
				v.lowlink = Math.min(v.lowlink, vp.index);
			}
		}

		if (v.lowlink == v.index) {
			vertex<N> j;
			ArrayList<N> component = new ArrayList<N>();
			do {
				j = this.S.pop();
				component.add(j.node);
			} while (j != v);

			comp.add(component);
		}
	}

	@SuppressWarnings("hiding")
	private class vertex<N> {

		public N node;
		public int index;
		public int lowlink;

		/**
		 * @param n
		 */
		public vertex(N n) {
			super();
			this.index = -1;
			this.lowlink = -1;
			this.node = n;
		}

		public boolean notNumbered() {
			if (index == -1) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			return "vertex [index=" + index + ", lowlink=" + lowlink
					+ ", node=" + node + "]";
		}

	}

}
