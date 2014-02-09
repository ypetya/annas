package annas.graph.generate;

import java.util.ArrayList;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates a Complete Graph @see <a
 * href="http://mathworld.wolfram.com/CompleteGraph.html"> shown here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class CompleteGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Number of nodes in the target graph
	 */
	private int size;

	private boolean random = false;

	public CompleteGraphGenerator(int size) {
		super();
		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}
		this.size = size;
	}

	public CompleteGraphGenerator(int size, boolean random) {
		super();
		if (size < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}
		this.random = random;
		this.size = size;
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
				if (i != j) {
					if (!this.random) {
					target.addArc(tail, head, new DefaultWeight(1d));
					} else {
						target.addArc(tail, head,
								new DefaultWeight(Math.random()));
					}
				}
			}
		}

	}
}
