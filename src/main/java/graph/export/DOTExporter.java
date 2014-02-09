package annas.graph.export;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import annas.graph.ArcInterface;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.UndirectedGraph;

/**
 * Export graph to the standard .DOT format @see <a
 * href="http://en.wikipedia.org/wiki/DOT_language"> shown here </a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class DOTExporter<N, A extends ArcInterface<N>> implements
		Exporter<N, A> {

	/**
	 * Stream to output to.
	 */
	private PrintWriter pw;

	/**
	 * Connector
	 */
	private String connector;

	public DOTExporter() {
		super();
		this.connector = "  ";
	}

	public DOTExporter(OutputStream pw) {
		super();
		this.pw = new PrintWriter(pw);
		this.connector = "  ";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void export(OutputStream ops, GraphInterface<N, A> graph) {
		this.pw = new PrintWriter(ops);
		this.export(graph);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void export(GraphInterface<N, A> graph) {

		assert (pw != null);
		
		String indent = "  ";
		
		if (graph instanceof DirectedGraph<?, ?>) {
			pw.println("digraph G { ");
			this.connector = "->";
		}
		if (graph instanceof UndirectedGraph<?, ?>) {
			pw.println("graph G { ");
			this.connector = "--";
		}

		for (N n : graph.getNodeMap()) {
			pw.println(indent + n + ";");
		}

		for (N n : graph.getNodeMap()) {
			ArrayList<A> arcs = graph.getArc(n);
			for (A arc : arcs) {
				pw.println(indent + indent + n + " " + this.connector + " "
						+ arc.getHead() + ";");
			}
		}

		pw.println("}");
		pw.flush();
	}

}
