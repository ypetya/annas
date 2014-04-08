package annas.graph.generate;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

public class Irregular2DMeshGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Width of the mesh
	 */
	private int width;

	/**
	 * Height of the mesh
	 */
	private int height;

	/**
	 * Number of Additional Arcs
	 */
	private int additional_Arcs;


	/**
	 * 
	 * @param width
	 * @param height
	 * @param additional
	 */
	public Irregular2DMeshGenerator(int width, int height, int additional) {
		super();
		this.width = width;
		this.height = height;
		this.additional_Arcs = additional;
	}

	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory,
			Map<String, Object> map) {
		N[][] tmp = (N[][]) new Object[this.width][this.height];

		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				tmp[i][j] = factory.createNode();
				target.addNode(tmp[i][j]);
			}
		}

		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if (i != 0) {
					target.addArc(tmp[i][j], tmp[i - 1][j], new DefaultWeight(
							1d));
				}

				if (i != this.width - 1) {
					target.addArc(tmp[i][j], tmp[i + 1][j], new DefaultWeight(
							1d));
				}

				if (j != 0) {
					target.addArc(tmp[i][j], tmp[i][j - 1], new DefaultWeight(
							1d));
				}

				if (j != this.height - 1) {
					target.addArc(tmp[i][j], tmp[i][j + 1], new DefaultWeight(
							1d));
				}

			}
		}

		int n1, n2;
		int m1, m2;
		for (int i = 0; i < this.additional_Arcs; i++) {
			n1 = (int) Math.sqrt((Math.random() * tmp.length));
			n2 = (int) Math.sqrt((Math.random() * tmp[0].length));

			m1 = (int) Math.sqrt((Math.random() * tmp.length));
			m2 = (int) Math.sqrt((Math.random() * tmp[0].length));

			target.addArc(tmp[n1][n2], tmp[m1][m2], new DefaultWeight(1d));
		}

	}

}
