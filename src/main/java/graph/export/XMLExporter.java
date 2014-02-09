package annas.graph.export;

import java.beans.XMLEncoder;
import java.io.OutputStream;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Exports the graph to xml
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class XMLExporter<N, A extends ArcInterface<N>> implements
		Exporter<N, A> {

	/**
	 * Stream to output to
	 */
	private OutputStream ops;

	public XMLExporter() {
		super();
	}

	public XMLExporter(OutputStream ops) {
		super();
		this.ops = ops;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void export(OutputStream pw, GraphInterface<N, A> graph) {
		if (pw != null) {
			this.ops = pw;
			this.export(graph);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void export(GraphInterface<N, A> graph) {
		if (this.ops != null) {
			XMLEncoder encoder;

			encoder = new XMLEncoder(this.ops);
			encoder.writeObject(graph);
			encoder.close();
		}

	}

}
