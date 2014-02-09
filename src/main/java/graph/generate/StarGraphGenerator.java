package annas.graph.generate;

import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates a Star Graph @see <a
 * href="http://mathworld.wolfram.com/StarGraph.html"> shown here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class StarGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Number of nodes in the target graph
	 */
	private int size;

	public StarGraphGenerator(int size) {
		super();
		if (size < 0) {
			throw new IllegalArgumentException("size must be greater than 0");
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

		N Hub = factory.createNode();
		N tmp = null;
		target.addNode(Hub);
		for (int i = 0; i < this.size - 1; i++) {
			tmp = factory.createNode();
			target.addNode(tmp);
			target.addArc(Hub, tmp, new DefaultWeight(1.0));
		}
	}

}
