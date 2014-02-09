package annas.graph;

/**
 * Default implementation of ArcFactor
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 */
public class DefaultArcFactory<N> implements ArcFactory<N, DefaultArc<N>> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DefaultArc<N> create(N tail, N head, WeightedInterface wi) {
		return new DefaultArc<N>(tail,head, wi);
	}

}
