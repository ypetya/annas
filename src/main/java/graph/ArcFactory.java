package annas.graph;

import java.io.Serializable;

/**
 * Factory used by the Graph class to construct Arcs
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public interface ArcFactory<N, A extends ArcInterface<N>> extends Serializable {

	/**
	 * Creates an arc of type A
	 * 
	 * @param tail
	 *            Tail of the arc
	 * @param head
	 *            Head of the arc
	 * @param wi
	 * @return
	 */
	public A create(N tail, N head, WeightedInterface wi);

}
