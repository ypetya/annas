package annas.graph.drawing;


import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Interface for the layout of a graph for visualisation
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public interface Placer<N, A extends ArcInterface<N>> {

	/**
	 * Takes a graph and runs an Algorithm to determine the location of each
	 * node in the Graph.
	 * 
	 * @param graph
	 *            Graph to draw.
	 * @return Map mapping nodes to maps of string Object pairs. Containing data
	 *         used by the drawing routine.
	 */
	public Map<N, int[]> place(GraphInterface<N, A> graph,
			int sizeX, int sizeY);

}
