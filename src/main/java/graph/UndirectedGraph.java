package annas.graph;

/**
 * Implementation of a Undirected Graph similar to @see <a
 * href="http://mathworld.wolfram.com/UndirectedGraph.html">Directed Graph</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 * 
 */
public class UndirectedGraph<N, A extends ArcInterface<N>> extends Graph<N, A>
		implements GraphInterface<N, A> {

	public UndirectedGraph() {
		super();

	}

	public UndirectedGraph(ArcFactory<N, A> arcfactory) {
		super(arcfactory);

	}

	public UndirectedGraph(GraphObserver observer) {
		super(observer);

	}

	/**
	 * Adds an arc to the graph, adds to directed arcs to the graph. This graph
	 * increments the graph version by two.
	 * 
	 * @return true if the arc was successfully added
	 */
	public boolean addArc(N tail, N head, WeightedInterface wi) {
		return super.addArc(tail, head, wi) && super.addArc(head, tail, wi);
	}

}
