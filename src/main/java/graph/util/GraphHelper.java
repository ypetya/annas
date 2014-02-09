package annas.graph.util;

import java.lang.reflect.Field;
import java.util.Hashtable;

import annas.graph.DirectedGraph;
import annas.graph.Graph;
import annas.graph.UndirectedGraph;

public class GraphHelper {

	/**
	 * Convert an UndirectedGraph to a Directed graph. The link structure is
	 * untouched but calls to the returned class will behave like a directed
	 * graph.
	 * 
	 * @param graph
	 *            Graph to convert
	 * @return directed graph
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static DirectedGraph toDirected(UndirectedGraph graph) {

		try {
			Field nm = Graph.class.getDeclaredField("nodeMap");
			Field v = Graph.class.getDeclaredField("version");
			nm.setAccessible(true);
			v.setAccessible(true);

			Hashtable ht = (Hashtable) nm.get(graph);
			int version = v.getInt(graph);

			DirectedGraph h = new DirectedGraph();
			nm.set(h, ht);
			v.setInt(h, version);

			return h;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Convert a DirectedGraph to a Undirected graph. The link structure is
	 * untouched but calls to the returned class will behave like an Undirected
	 * graph.
	 * 
	 * @param graph
	 *            Graph to convert
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static UndirectedGraph toUndirected(DirectedGraph graph) {
		try {
			Field nm = Graph.class.getDeclaredField("nodeMap");
			Field v = Graph.class.getDeclaredField("version");
			nm.setAccessible(true);
			v.setAccessible(true);

			Hashtable ht = (Hashtable) nm.get(graph);
			int version = v.getInt(graph);

			UndirectedGraph h = new UndirectedGraph();
			nm.set(h, ht);
			v.setInt(h, version);

			return h;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

}
