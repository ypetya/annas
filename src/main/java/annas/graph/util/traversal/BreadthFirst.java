package annas.graph.util.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Performs a Breadth first traversal
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class BreadthFirst<N, A extends ArcInterface<N>> implements Traversal<N, A> {

	private GraphInterface<N, A> graph;

	private LinkedList<N> Order;

	private HashMap<N, Integer> map;

	private boolean Bipartite = true;

	/**
	 * 
	 */
	public BreadthFirst() {
		super();
		this.map = new HashMap<N, Integer>();
		this.Order = new LinkedList<N>();
	}

	public BreadthFirst(GraphInterface<N, A> g) {
		super();
		this.graph = g;
		this.map = new HashMap<N, Integer>();
		this.Order = new LinkedList<N>();
	}

	/* (non-Javadoc)
	 * @see annas.graph.util.traversal.Traversal#run(N)
	 */
	@Override
	public Iterator<N> run(N s) {
		this.BF(s);
		return this.Order.iterator();
	}

	/* (non-Javadoc)
	 * @see annas.graph.util.traversal.Traversal#run(N, N)
	 */
	@Override
	public Iterator<N> run(N s, N tar) {
		this.run(s);
		this.BF(s, tar);
		return this.Order.iterator();
	}

	private void BF(N n) {
		Queue<N> l = new LinkedList<N>();

		map.put(n, 0);
		l.offer(n);

		while (l.size() > 0) {
			N m = l.poll();
			this.Order.add(m);
			ArrayList<A> arcs = this.graph.getArc(m);
			for (int i = 0; i < arcs.size(); i++) {

				if (this.Order.contains(arcs.get(i).getHead())) {

				} else {
					l.offer(arcs.get(i).getHead());
				}

				if (!map.containsKey(arcs.get(i).getHead())) {
					map.put(arcs.get(i).getHead(), this.label(map.get(m)));

				} else {
					if (map.get(arcs.get(i).getHead()) % 2 == map.get(m) % 2) {
						this.Bipartite = false;
					}
				}
			}
		}

	}

	private void BF(N n, N Target) {
		LinkedList<N> h = new LinkedList<N>();
		for (int i = 0; i < this.Order.size(); i++) {
			h.add(this.Order.get(i));
			if (h.get(h.size() - 1).equals(Target)) {
				this.Order = h;
				return;
			}
		}
	}

	/* (non-Javadoc)
	 * @see annas.graph.util.traversal.Traversal#getGraph()
	 */
	@Override
	public GraphInterface<N, A> getGraph() {
		return graph;
	}

	/* (non-Javadoc)
	 * @see annas.graph.util.traversal.Traversal#setGraph(annas.graph.GraphInterface)
	 */
	@Override
	public void setGraph(GraphInterface<N, A> g) {
		this.graph = g;
	}

	private int label(int i) {
		return i + 1;
	}

	public int getLevel(N node) {
		return this.map.get(node);
	}

	@SuppressWarnings("hiding")
	class BFNode<N> {

		int label = Integer.MAX_VALUE;

		N n;

		public BFNode(N n, int label) {
			this.n = n;
			this.label = label;
		}

		public int getLabel() {
			return label;
		}

		public void setLabel(int label) {
			this.label = label;
		}

		public N getN() {
			return n;
		}

		public void setN(N n) {
			this.n = n;
		}

	}

	public boolean isBipartite() {
		return Bipartite;
	}

	public void setBipartite(boolean bipartite) {
		Bipartite = bipartite;
	}
}
