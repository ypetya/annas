package annas.graph.generate;

import java.util.ArrayList;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates a Random Graph @see <a
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class RandomGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Number of nodes in the target graph
	 */
	private int size;

	private double probability;

	public RandomGraphGenerator(int size, double probability) {
		super();
		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}
		this.size = size;
		this.probability = probability;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory,
			Map<String, Object> map) {
		if (target == null)
			return;

		for (int i = 0; i < size; i++) {
			N newVertex = factory.createNode();
			target.addNode(newVertex);
		}

		N head, tail = null;
		ArrayList<N> nodes = target.getNodeMap();
		for (int i = 0; i < this.size; i++) {
			tail = nodes.get(i);
			for (int j = 0; j < this.size; j++) {
				head = nodes.get(j);
				if (this.probability > Math.random()) {

					target.addArc(tail, head, new DefaultWeight(1d));

				}
			}
		}

	}
}
