/**
 * 
 */
package annas.graph.isomorphism;

import java.util.List;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * @author Sam Wilson
 * @version 1.1
 * 
 */
public interface GraphMapping<N, A extends ArcInterface<N>, N1, A1 extends ArcInterface<N1>> {


	/**
	 * Gets the mapped node in the direction indicated by the first two
	 * parameters
	 * 
	 * @param from
	 *            Graph which node belongs to
	 * @param to
	 *            Graph to retrieve the mapped node from.
	 * @param node
	 *            Node to retrieve mapping of.
	 * @return Mapped Node
	 */
	public Object getMappedNode(GraphInterface from, GraphInterface to,
 Object node);

	/**
	 * Gets the mapped arc in the direction indicated by the first two
	 * parameters
	 * 
	 * @param from
	 *            Graph which arc belongs to
	 * @param to
	 *            Graph to retrieve the mapped arc from.
	 * @param arc
	 *            Arc to retrieve mapping of.
	 * @return Mapped Arc
	 */
	public List<ArcInterface> getMappedArc(GraphInterface from,
			GraphInterface to,
			ArcInterface arc);

	/**
	 * 
	 * @return the number of items currently in the mapping
	 */
	public int size();

	public abstract void addMapping(N n1, N1 n2);

	public boolean conatains(GraphInterface g, Object obj);
}
