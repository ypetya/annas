package annas.graph.generate;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

public class Regular2DMeshGenerator<N, A extends ArcInterface<N>> implements
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
	 * 
	 * @param width
	 * @param height
	 */
	public Regular2DMeshGenerator(int width, int height) {
		super();
		this.width = width;
		this.height = height;
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

	}

}
