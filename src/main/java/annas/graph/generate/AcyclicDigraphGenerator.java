/**
 * 
 */
package annas.graph.generate;

import java.util.ArrayList;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;
import annas.graph.util.CycleDetector;
import annas.graph.util.Util;

/**
 * @author your Sam Wilson
 * 
 */
public class AcyclicDigraphGenerator<N, A extends ArcInterface<N>> implements
		GraphGenerator<N, A> {

	private final boolean DEBUG = false;

	private int size;

	private int minimum_arc = 1;

	/**
	 * 
	 */
	public AcyclicDigraphGenerator(int size) {
		super();
		this.size = size;
		this.minimum_arc = (size * size) / 4;
	}

	/**
	 * 
	 */
	public AcyclicDigraphGenerator(int size, int min) {
		super();
		this.minimum_arc = min;
		this.size = size;
	}

	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory,
			Map<String, Object> map) {
		for (int i = 0; i < this.size; i++) {
			target.addNode(factory.createNode());
		}

		Util util = new Util(target);
		ArrayList<N> nodes = target.getNodeMap();
		CycleDetector cD;
		while (continueAdding(target)) {
			int i = (int) (Math.random() * nodes.size());
			int j = (int) (Math.random() * nodes.size());

			if (i != j && target.getArc(nodes.get(i), nodes.get(j)).size() == 0) {
				cD = new CycleDetector(target);
				target.addArc(nodes.get(i), nodes.get(j), new DefaultWeight(1d));
				if (cD.containsCycle()) {
					if (DEBUG) {
						System.out.println("Arc REMOVED: Arc induced a cycle");
					}
					target.removeArc(nodes.get(i), nodes.get(j));
				}

			} else if (target.getArc(nodes.get(i), nodes.get(j)).size() != 0) {
				if (util.getInDegree(nodes.get(j)) > 2) {
					target.removeArc(nodes.get(i), nodes.get(j));
				}
			}

		}

	}

	private boolean continueAdding(GraphInterface graph) {
		return graph.getNuArcs() < this.minimum_arc;
	}

}
