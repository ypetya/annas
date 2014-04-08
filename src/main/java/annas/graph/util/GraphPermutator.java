package annas.graph.util;
import java.util.ArrayList;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.isomorphism.DefaultGraphMapping;
import annas.graph.isomorphism.GraphMapping;

public class GraphPermutator<N, A extends ArcInterface<N>, A1 extends ArcInterface<N>>
		implements Permutator<N, A, N, A1> {

	private GraphInterface<N, A> src;

	public GraphPermutator(GraphInterface<N, A> src) {
		super();
		this.src = src;
	}

	@Override
	public GraphMapping<N, A, N, A1> getPermutation(GraphInterface<N, A1> target) {
		if (target.getClass() != this.src.getClass())
			return null;

		DefaultGraphMapping<N, A, N, A1> mapping = new DefaultGraphMapping<N, A, N, A1>(
				this.src, target);
		ArrayList<N> srcNodes = this.src.getNodeMap();
		ArrayList<N> targetNodes = this.src.getNodeMap();

		for (int i = 0; i < srcNodes.size(); i++) {
			N srcrep = srcNodes.get(i);
			target.addNode(srcrep);
			int repindex = (int) (Math.random() * targetNodes.size());
			N targetrep = targetNodes.get(repindex);
			mapping.addMapping(srcrep, targetrep);
			targetNodes.remove(repindex);
		}

		ArrayList<A> tmp = null;
		N tmpnode = null;
		for (int i = 0; i < src.getNuNodes(); i++) {
			tmpnode = src.getNodeMap().get(i);
			tmp = src.getArc(tmpnode);
			for (int j = 0; j < tmp.size(); j++) {
				target.addArc((N) mapping.getMappedNode(src, target, tmpnode),
						(N) mapping.getMappedNode(src, target, tmp.get(j)
								.getHead()),
						new DefaultWeight(1d));
			}
		}
		return mapping;
	}
}
