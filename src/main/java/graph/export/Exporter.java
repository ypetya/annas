package annas.graph.export;

import java.io.OutputStream;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Interface for all Exporters. Exporters provide an alternative representation
 * of a graph from use outside of the annas package.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public interface Exporter<N, A extends ArcInterface<N>> {

	/**
	 * Exports the provided graph.
	 * 
	 * @param ops
	 *            Stream to output the graph to
	 * @param graph
	 *            Graph to export
	 */
	public void export(OutputStream ops, GraphInterface<N, A> graph);

	/**
	 * Exports the provided graph.
	 * 
	 * @param graph
	 *            Graph to export
	 */
	public void export(GraphInterface<N, A> graph);

}
