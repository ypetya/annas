package annas.graph.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

import annas.graph.ArcInterface;
import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;

/**
 * Determines a minimal spanning tree for an Undirected graph, as described <a
 * href="http://en.wikipedia.org/wiki/Prim's_algorithm"> here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class Prim<N, A extends ArcInterface<N>> {
	
	/**
	 * Used to Hold all the Possible Edges.
	 */
	private PriorityQueue<Edge<A>> EdgeList;

	/**
	 * Used to Hold all the Vertices.
	 */
	private Hashtable<N,Vertex<N>> htable;
	
	protected GraphInterface<N,A> graph;
	
	public Prim(GraphInterface<N,A> graph){
		super();
		if(!(graph instanceof UndirectedGraph)){
			throw new IllegalArgumentException(
			"Prims can only be applied to Undirected Graphs");
		}
		this.graph = graph;
		this.htable = new Hashtable<N, Vertex<N>>();
		this.EdgeList = new PriorityQueue<Edge<A>>(1,
				new DoubleFieldComparator());
		N tmp = null;
		for (int u = 0; u < graph.getNodeMap().size(); u++) {
			tmp = graph.getNodeMap().get(u);
			this.htable.put(tmp, new Vertex<N>(tmp));
		}
	}
	
	
	/**
	 * Executes Prim's algorithm
	 * 
	 * @return An Undirected Graph representing the minimal spanning tree of the
	 *         specified graph.
	 */
	public GraphInterface<N,DefaultArc<N>> execute() {
		Vertex<N> V;
		Edge<A> E;

		UndirectedGraph<N,DefaultArc<N>> G = new UndirectedGraph<N,DefaultArc<N>>();
		V = new ArrayList<Vertex<N>>(this.htable.values()).get(0);
		V.setSelected(true);
		G.addNode(V.representing);
		this.add(V.representing);

		while (this.NodeRemain()) {
			E = EdgeList.poll();
			
			if (!Find(E.representing.getHead()).isSelected()) {
				G.addNode(E.representing.getHead());
				G.addArc(E.Tail, E.representing.getHead(), new DefaultWeight(
						E.representing.getWeight()));
				Find(E.representing.getHead()).setSelected(true);
				
				EdgeList.remove(E);
				this.add(E.representing.getHead());

			} else {
				EdgeList.remove(E);
			}
		}
		return G;
	}
	
	private void add(N n) {
		ArrayList<A> arcs = this.graph.getArc(n);
		for (int l = 0; l < arcs.size(); l++) {
			EdgeList.add(new Edge<A>(arcs.get(l)));
		}

	}
	
	private boolean NodeRemain() {
		ArrayList<Vertex<N>> VertexMap = new ArrayList<Vertex<N>>(this.htable.values());
		for (int i = 0; i < VertexMap.size(); i++) {
			if (!VertexMap.get(i).isSelected()) {
				return true;
			}
		}
		return false;
	}
	
	private Vertex<N> Find(N c) {
		return this.htable.get(c);
	}
	
	class DoubleFieldComparator implements Comparator<Edge<A>> {

		@Override
		public int compare(Edge<A> arg0, Edge<A> arg1) {
			return arg0.representing.getWeight().compareTo(
					arg1.representing.getWeight());
		}
	}
	
	
	class Vertex<N>{

		private N representing;
		
		/**
		 * True is the Vertex is in the minimum spanning tree.
		 */
		boolean selected;

		/**
		 * Parameterized Constructor.
		 * 
		 * @param n
		 *            Node to be made into a Vertex.
		 */
		public Vertex(N n) {
			super();
			this.representing = n;
			selected = false;
		}

		/**
		 * Gets if Selected is selected.
		 * 
		 * @return true is Selected.
		 */
		public boolean isSelected() {
			return selected;
		}

		/**
		 * Sets Selected.
		 * 
		 * @param selected
		 */
		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Name: " + this.representing.toString();
		}

	}


	class Edge<A extends ArcInterface<N>> {
		
		private A representing;
		
		/**
		 * Name of the Node which the Edge is connecting from.
		 */
		private N Tail;

		/**
		 * Paramaterized Constructor.
		 * 
		 * @param a
		 *            Arc.
		 * @param T
		 *            Name of the Node the Edge is connecting from.
		 */
		Edge(A a) {
			super();
			this.representing = a;
			this.Tail = this.representing.getTail();
		}

		/**
		 * Gets the Name of the Node which the Edge is connecting from.
		 * 
		 * @return Name of the Node.
		 */
		public N getTail() {
			return Tail;
		}

		/**
		 * Sets the Name of the Node which the Edge is connecting from.
		 * 
		 * @param tail
		 *            Name of the Node.
		 */
		public void setTail(N tail) {
			Tail = tail;
		}

		@Override
		public String toString() {
			return "Edge [Tail=" + Tail + ", representing=" + representing
					+ "]";
		}

		
	}
}
