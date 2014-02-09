package annas.graph.export;

import java.io.OutputStream;
import java.io.PrintWriter;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;
import annas.graph.util.Util;
import annas.math.Matrix;

public class AdjacentExporter<N, A extends ArcInterface<N>> implements
		Exporter<N, A> {

	/**
	 * Stream to output to.
	 */
	private PrintWriter pw;

	public AdjacentExporter() {
		super();
	}

	public AdjacentExporter(OutputStream pw) {
		super();
		this.pw = new PrintWriter(pw);
	}

	@Override
	public void export(OutputStream ops, GraphInterface<N, A> graph) {
		this.pw = new PrintWriter(ops);
		this.export(graph);
	}

	@Override
	public void export(GraphInterface graph) {

		Matrix m = new Util(graph).getAdjacentMatrix();
		float[][] matrix = m.getMatrix();

		for (int i = 0; i < matrix.length; i++) {
			this.pw.print("{");
			for (int j = 0; j < matrix[0].length; j++) {
				if (j < matrix[0].length - 1) {
					this.pw.print(" " + matrix[i][j] + ",");
				} else {
					this.pw.print(" " + matrix[i][j]);
				}

			}
			this.pw.println(" }");

		}

	}

}
