package annas.graph.generate;

import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Interface all Graph generating classes should implement.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public interface GraphGenerator<N, A extends ArcInterface<N>> {
	
	/**
	 * Generates a graph.
	 * 
	 * @param target
	 *            Target Graph object
	 * @param factory
	 *            Factory for creating nodes.
	 * @param map
	 *            Provides a means of communication data to the generating
	 *            algorithm.
	 */
	public void generate(GraphInterface<N,A> target, NodeFactory<N> factory, Map<String, Object> map);

}
