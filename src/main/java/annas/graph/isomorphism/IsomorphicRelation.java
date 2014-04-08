package annas.graph.isomorphism;

import annas.graph.ArcInterface;

/**
 * 
 * @author Sam Wilson
 * @version 1.1
 */
public interface IsomorphicRelation<N, A extends ArcInterface<N>, N1, A1 extends ArcInterface<N1>>
		extends GraphIsomorphismInspector {

	public GraphMapping<N, A, N1, A1> getMapping();

}
