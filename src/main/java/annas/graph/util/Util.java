package annas.graph.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;
import annas.graph.util.traversal.BreadthFirst;
import annas.math.EigenvalueDecomposition;
import annas.math.Matrix;
import annas.math.stats.Statistics;

/**
 * Util is a Class containing a group of algorithms for manipulating and
 * extracting data form Graphs.
 * 
 * @author Sam Wilson
 * 
 */

public class Util<N, A extends ArcInterface<N>> {

	/**
	 * Graph Which any algorithm will be applied to.
	 */
	GraphInterface<N, A> graph;

	public Util(GraphInterface<N, A> graph) {
		super();
		this.graph = graph;
	}

	public Dijkstra<N, A> Dijkstra(N start, N end) {
		return new Dijkstra<N, A>(this.graph);

	}

	public Floyd<N, A> Floyd() {
		return new Floyd<N, A>(this.graph);
	}

	public GraphInterface<N, A> Prim() {
		return (GraphInterface<N, A>) (new Prim<N, A>(this.graph)).execute();

	}

	public ArrayList<ArrayList<N>> Tarjan() {
		return new Tarjan(this.graph).execute();

	}

	public GraphInterface<N, A> getGraphComplement() {

		UndirectedGraph<N, A> udirg = new UndirectedGraph<N, A>();
		DirectedGraph<N, A> dirg = GraphHelper.toDirected(udirg);

		ArrayList<N> nodes = this.graph.getNodeMap();

		for (N n : nodes) {
			dirg.addNode(n);
		}
		for (N n : nodes) {
			for (N m : nodes) {
				if (n != m) {
					dirg.addArc(n, m, new DefaultWeight(1d));
				}
			}
		}

		ArrayList<A> arcs;
		for (N n : nodes) {
			arcs = this.graph.getArc(n);
			for (int i = 0; i < arcs.size(); i++) {
				dirg.removeArc(n, arcs.get(i).getHead());
			}
		}
		return dirg;
	}

	public int getInDegree(N node) {
		int index = this.indexof(node);
		Matrix m = this.getAdjacentFrequencyMatrix();
		Statistics s = new Statistics();
		for (int i = 0; i < m.getMatrix().length; i++)
			s.add(m.getMatrix()[i][index]);
		return (int) s.sum();

	}

	public int getOutDegree(N node) {
		return this.graph.getArc(node).size();
	}

	public Matrix getAdjacentFrequencyMatrix() {
		float[][] retval = new float[this.graph.getNuNodes()][this.graph
				.getNuNodes()];
		N Current;

		ArrayList<A> arcs;
		for (int i = 0; i < this.graph.getNodeMap().size(); i++) {
			Current = this.graph.getNodeMap().get(i);
			arcs = this.graph.getArc(Current);
			for (int j = 0; j < arcs.size(); j++) {

				retval[i][indexof(arcs.get(j).getHead())] += 1;

			}
		}
		return new Matrix(retval);
	}

	private int indexof(N node) {
		ArrayList<N> nodes = this.graph.getNodeMap();
		for (int i = 0; i < nodes.size(); i++)
			if (nodes.get(i).equals(node))
				return i;

		return -1;
	}

	public Matrix getAdjacentMatrix() {
		float[][] retval = new float[this.graph.getNodeMap().size()][this.graph
				.getNodeMap().size()];
		N Current;
		ArrayList<N> fg = this.graph.getNodeMap();
		for (int i = 0; i < this.graph.getNodeMap().size(); i++) {
			Current = fg.get(i);
			ArrayList<A> arcs = this.graph.getArc(Current);
			for (int j = 0; j < arcs.size(); j++) {

				retval[i][this.graph.getNodeMap().indexOf(arcs.get(j).getHead())] = 1;

			}
		}
		return new Matrix(retval);

	}

	public Matrix getLapacianMatrix() {
		float[][] retval = new float[this.graph.getNodeMap().size()][this.graph
				.getNodeMap().size()];

		ArrayList<N> fg = this.graph.getNodeMap();
		for (int i = 0; i < this.graph.getNodeMap().size(); i++) {
			for (int j = 0; j < this.graph.getNodeMap().size(); j++) {
				if (i == j) {
					retval[i][j] = Double.valueOf(this.getOutDegree(fg.get(i)))
							.floatValue();
				} else if (this.graph.getArc(fg.get(i), fg.get(j)).size() > 0) {
					retval[i][j] = -1;
				} else {
					retval[i][j] = 0;
				}
			}
		}

		return new Matrix(retval);
	}

	public float[] getEigenVectorCentrality() {
		Matrix m = new Matrix(this.getAdjacentMatrix());
		EigenvalueDecomposition EVD = new EigenvalueDecomposition(m);

		float[][] p = EVD.getV().getMatrix();
		int n = p.length;
		float[] y = new float[n];
		for (int h = 0; h < n; h++) {
			y[h] = p[h][n - 1];
		}

		return y;

	}

	public Matrix getNumPathNLong(int n) {
		Matrix m = new Matrix(this.getAdjacentMatrix());
		Matrix f = new Matrix(this.getAdjacentMatrix());
		for (int i = 1; i < n; i++) {
			try {
				m = m.MultiplyMatrix(f);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	/**
	 * Checks if the graph is connected. A graph is called connected if every
	 * pair of distinct vertices in the graph can be connected through some
	 * path.
	 * 
	 * @return true if the graph is connected false otherwise
	 * 
	 *         Stack toVisit Set visited
	 * 
	 *         add any vertex to toVisit
	 * 
	 *         while toVisit is not empty currentVertex = toVisit.pop()
	 * 
	 *         visited.add(currentVertex)
	 * 
	 *         for each vertex in neighbors of currentVertex if vertex is not in
	 *         visited toVisit.push(vertex)
	 * 
	 * 
	 *         Read more: http://wiki.answers.com/Q/
	 *         Design_an_algorithm_to_check_the_graph_connection#ixzz16s2kp82Q
	 */
	public boolean isConnected() {
		
		Stack<N> toVisit = new Stack<N>();
		List<N> Visited = new ArrayList<N>();
		toVisit.push(this.graph.getNodeMap().get(0));
		
		while (!toVisit.isEmpty()) {
			N currenVertex = toVisit.pop();
			Visited.add(currenVertex);

			ArrayList<A> t = this.graph.getArc(currenVertex);
			for (A a : t) {

				if (!Visited.contains(a.getHead())
						&& !toVisit.contains(a.getHead())) {
					toVisit.push(a.getHead());
				}
			}
		}
		return Visited.containsAll(this.graph.getNodeMap());

	}

	public boolean isBipartite() {
		return (new BreadthFirst<N, A>(this.graph)).isBipartite();
	}

	public boolean isEmpty() {
		return this.graph.getNuNodes() == 0;
	}

	public boolean isEulerian() {
		if (this.graph instanceof UndirectedGraph) {
			ArrayList<N> nodes = this.graph.getNodeMap();

			for (int i = 0; i < nodes.size(); i++) {
				if ((this.getInDegree(nodes.get(i)) + this.getOutDegree(nodes
						.get(i)) / 2) != 0) {
					return false;
				}
			}
			return true;

		} else if (this.graph instanceof DirectedGraph) {
			if (this.Tarjan().size() == 0) {

				ArrayList<N> nodes = this.graph.getNodeMap();

				for (int i = 0; i < nodes.size(); i++) {
					if (this.getInDegree(nodes.get(i)) == this
							.getOutDegree(nodes.get(i))) {
						return true;
					}
				}
				return false;

			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isTriangleFree() {
		Matrix m = this.getAdjacentMatrix();

		m = m.pow(3);
		if (m.Trace() != 0)
			return false;
		return true;

	}
}
