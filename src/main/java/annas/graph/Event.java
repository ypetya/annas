package annas.graph;

/**
 * Lists all possible event that can occur in a graph
 * 
 * @author Sam Wilson
 */
public enum Event {
	
	/**
	 * Indicates a Node has been added to the Graph
	 */
	Node_Added,
	
	/**
	 * Indicates a Node has been removed from the Graph
	 */
	Node_Removed,
	
	
	/**
	 * Indicates a Arc has been added to the Graph
	 */
	Arc_Added,
	
	/**
	 * Indicates a Arc has been removed from the Graph
	 */
	Arc_Removed,
	
	/**
	 * Removes all Arcs from the Graph
	 */
	Arc_Reset

}
