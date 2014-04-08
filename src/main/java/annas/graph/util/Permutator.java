package annas.graph.util;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;
import annas.graph.isomorphism.GraphMapping;


public interface Permutator<N, A extends ArcInterface<N>, N1, A1 extends ArcInterface<N1>> {

	
	public GraphMapping<N, A, N1, A1> getPermutation(
			GraphInterface<N1, A1> target);

}
