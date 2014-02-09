/**
 * 
 */
package annas.graph;

/**
 * Simple Graph as described in <a
 * href="http://mathworld.wolfram.com/SimpleGraph.html">
 * http://mathworld.wolfram.com/SimpleGraph.html</a>.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class SimpleGraph<N, A extends ArcInterface<N>> extends
		DirectedGraph<N, A> {

	/**
	 * Method which includes validation to enforce the Simple graph structure.
	 */
	@Override
	public boolean addArc(N tail, N head, WeightedInterface wi) {
		if ((tail == head) || (this.getArc(tail, head).size() > 0))
			return false;

		return super.addArc(tail, head, wi);

	}

}
