/**
 * 
 */
package annas.graph.generate;

import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DirectedGraph;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;
import annas.graph.util.GraphHelper;
import annas.graph.util.Prim;

/**
 * @author your Sam Wilson
 * 
 */
public class RandomTreeGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	private final boolean DEBUG = false;

	private int size;

	/**
	 * 
	 */
	public RandomTreeGenerator(int size) {
		super();
		this.size = size;
	}

	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory,
			Map<String, Object> map) {
		GraphGenerator g = new CompleteGraphGenerator(this.size, true);
		g.generate(target, factory, null);

		Prim prim = new Prim(GraphHelper.toUndirected((DirectedGraph) target));

		target = prim.execute();

	}

}
