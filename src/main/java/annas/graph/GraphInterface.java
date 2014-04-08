package annas.graph;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Base interface for all Graphs
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public interface GraphInterface<N, A extends ArcInterface<N>> extends
		Serializable {

	/**
	 * Adds a node to the graph
	 * 
	 * @param node
	 *            to add to the graph
	 * @return
	 */
	public boolean addNode(N node);

	/**
	 * Removes a node from the graph
	 * 
	 * @param node
	 *            to remove from the graph
	 * @return true if the node is successfully removed from the graph
	 */
	public boolean removeNode(N node);

	/**
	 * Checks if the graph contains a node
	 * 
	 * @param node
	 *            to check if it is contained within the graph
	 * @return true if the node is contained within the graph
	 */
	public boolean contains(N node);

	/**
	 * Adds an arc to the graph
	 * 
	 * @param tail
	 *            Tail of the arc
	 * @param head
	 *            Head of the arc
	 * @param wi
	 *            WeightedInterface
	 * @return true if the arc is successfully added to the graph
	 */
	public boolean addArc(N tail, N head, WeightedInterface wi);

	/**
	 * Gets all arcs which originate from the given node
	 * 
	 * @param tail
	 *            tail of the arcs
	 * @return An arraylist of all arcs originating from the given nodes
	 */
	public ArrayList<A> getArc(N tail);

	/**
	 * Gets all arcs from the tail to the head
	 * 
	 * @param tail
	 *            tail of the arc
	 * @param head
	 *            head of the arc
	 * @return An arraylist of all arcs from the tail to the head
	 */
	public ArrayList<A> getArc(N tail, N head);

	/**
	 * Removes an arc from the graph
	 * 
	 * @param tail
	 *            tail of the arc to remove
	 * @param arc
	 *            arc to remove
	 * @return true if the arc was successfully removed
	 */
	public boolean removeArc(N tail, A arc);

	/**
	 * Removes all arc from the graph
	 * 
	 * @param tail
	 *            tail of the arc to remove
	 * @param head
	 *            head of the arc to remove
	 * @return
	 */
	public boolean removeArc(N tail, N head);

	/**
	 * Removes all arcs with originating from a node
	 * 
	 * @param tail
	 *            tail of the arc
	 * @return true if all arcs originating from the node were removed
	 */
	public boolean removeArc(N tail);

	/**
	 * Removes all arcs from the graph
	 */
	public void resetArcs();

	/**
	 * Gets the @see ArcFactory used by the graph
	 * 
	 * @return ArcFactory used by the graph
	 */
	public ArcFactory<N, A> getArcFactory();

	/**
	 * Gets a list of nodes in the graph
	 * 
	 * @return list of nodes in the graph
	 */
	public ArrayList<N> getNodeMap();

	/**
	 * Current GraphObserver
	 * 
	 * @return GraphObserver
	 */
	public GraphObserver getObserver();

	/**
	 * Gets the version of the graph. The graph version is initially 0, each
	 * method invoked which modifies the graph increments the field by 1.
	 * 
	 * @return
	 */
	public int getVersion();

	/**
	 * Number of arcs contained within the graph
	 * 
	 * @return
	 */
	public int getNuArcs();

	/**
	 * Number of nodes contained within the graph
	 * 
	 * @return
	 */
	public int getNuNodes();

}
