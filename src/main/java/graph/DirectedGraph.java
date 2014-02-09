package annas.graph;

/**
 * Implementation of a Directed Graph similar to @see <a
 * href="http://mathworld.wolfram.com/DirectedGraph.html">Directed Graph</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 * 
 */
public class DirectedGraph<N, A extends ArcInterface<N>> extends Graph<N, A>
		implements GraphInterface<N, A>{

	public DirectedGraph(ArcFactory<N, A> arcfactory) {
		super(arcfactory);
		// TODO Auto-generated constructor stub
	}

	public DirectedGraph() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DirectedGraph(GraphObserver observer) {
		super(observer);
		// TODO Auto-generated constructor stub
	}

}
