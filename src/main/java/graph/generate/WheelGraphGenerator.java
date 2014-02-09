package annas.graph.generate;

import java.util.ArrayList;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates a Wheel Graph @see <a
 * href="http://mathworld.wolfram.com/WheelGraph.html"> shown here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class WheelGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Number of nodes in the target graph
	 */
	private int size;

	public WheelGraphGenerator(int size) {
		super();
		if (size < 4) {
			throw new IllegalArgumentException("size must be greater than 3");
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
		N newnode = factory.createNode();
		N tmp;
		N start_node = newnode;
		target.addNode(newnode);
		tmp = newnode;
		for (int i = 0; i < this.size - 2; i++) {
			newnode = factory.createNode();
			target.addNode(newnode);
			target.addArc(tmp, newnode, new DefaultWeight(1.0));
			tmp = newnode;
		}
		N end_node = tmp;
		target.addArc(end_node, start_node, new DefaultWeight(1.0));

		target.addNode(Hub);

		ArrayList<N> nodes = target.getNodeMap();
		for (int i = 0; i < nodes.size(); i++) {
			N tmp1 = nodes.get(i);
			if (tmp1 != Hub) {
				target.addArc(Hub, tmp1, new DefaultWeight(1.0));
			}
		}
	}

}
