package annas.graph.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates a Hyper Graph @see <a
 * href="http://mathworld.wolfram.com/Hypergraph.html"> shown here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class HyperGraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	/**
	 * Number of dimensions
	 */
	private int dim;

	public HyperGraphGenerator(int dim) {
		super();
		if (dim < 0) {
			throw new IllegalArgumentException(
					"dimensions must be greater than 0");
		}
		this.dim = dim;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory,
			Map<String, Object> map) {
		if (target == null)
			return;

		if (map == null) {
			map = new HashMap<String, Object>();
		}

		int order = (int) Math.pow(2, dim);
		ArrayList<N> nodes = new ArrayList<N>();
		for (int i = 0; i < order; i++) {
			N newnode = factory.createNode();
			target.addNode(newnode);
			nodes.add(newnode);
			if (map != null) {
				String s = Integer.toBinaryString(i);
				while (s.length() < dim) {
					s = "0" + s;
				}
				map.put(s, newnode);
			}
		}

		for (int i = 0; i < order; i++) {
			for (int j = i + 1; j < order; j++) {
				for (int z = 0; z < dim; z++) {
					if ((j ^ i) == (1 << z)) {
						target.addArc(nodes.get(i), nodes.get(j),
								new DefaultWeight(1.0));
						break;
					}
				}
			}
		}
	}

}
