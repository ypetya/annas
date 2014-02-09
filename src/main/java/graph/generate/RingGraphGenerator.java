package annas.graph.generate;

import java.util.HashMap;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates a Ring Graph
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class RingGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Number of nodes in the target graph
	 */
	private int size;

	public RingGraphGenerator(int size) {
		super();
		if (size < 2) {
			throw new IllegalArgumentException("size must be >= 2");
		}
		this.size = size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory,
			Map<String, Object> map) {
		N start_node = null;
		N end_node = null;
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		if (target == null)
			return;
		N newnode = factory.createNode();
		N tmp;
		start_node = newnode;
		target.addNode(newnode);
		tmp = newnode;
		for (int i = 0; i < this.size - 1; i++) {
			newnode = factory.createNode();
			target.addNode(newnode);
			target.addArc(tmp, newnode, new DefaultWeight(1.0));
			tmp = newnode;
		}
		end_node = tmp;
		target.addArc(end_node, start_node, new DefaultWeight(1.0));
	}

}
