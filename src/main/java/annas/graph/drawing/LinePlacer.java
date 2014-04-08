package annas.graph.drawing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Places Nodes on a straight line
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class LinePlacer<N, A extends ArcInterface<N>> implements Placer<N, A> {

	public LinePlacer() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<N, int[]> place(GraphInterface<N, A> graph, int sizeX, int sizeY) {

		int Hspacing = (sizeX / (graph.getNuNodes() + 1));

		int Hpos = Hspacing;

		Map<N, int[]> map = new HashMap<N, int[]>();

		ArrayList<N> nodeSet = graph.getNodeMap();
		for (int i = 0; i < nodeSet.size(); i++) {
			map.put(nodeSet.get(i), new int[] { Hpos, sizeY / 2 });
			Hpos += Hspacing;
			
		}

		return map;
	}

}
