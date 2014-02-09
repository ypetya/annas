package annas.graph.generate;

import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates an empty Graph @see <a
 * href="http://mathworld.wolfram.com/EmptyGraph.html"> shown here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class EmptyGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Number of nodes in the target graph
	 */
	private int size;

	public EmptyGraphGenerator(int size) {
		super();
		if (size < 0) {
			throw new IllegalArgumentException("size mist be greater than 0");
		}
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

		for (int i = 0; i < this.size; i++)
			target.addNode(factory.createNode());

	}

}
