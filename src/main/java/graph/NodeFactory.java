package annas.graph;

/**
 * Factory for creating nodes
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 */
public interface NodeFactory<N> {
	
	public N createNode();

}
