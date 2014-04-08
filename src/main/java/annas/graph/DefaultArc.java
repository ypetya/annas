package annas.graph;

/**
 * Default arc with basic implementation of all the methods. This class is ideal
 * for extending.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 */
public class DefaultArc<N> implements ArcInterface<N> {

	/**
	 * Head of the arc
	 */
	private N head;

	/**
	 * Tail of the arc
	 */
	private N tail;

	/**
	 * Weight function of the arc
	 */
	private WeightedInterface wi;

	/**
	 * Constructor
	 * 
	 * @param tail
	 *            Tail of the arc
	 * @param head
	 *            Head of the arc
	 * @param wi
	 *            Weight function of the arc
	 */
	public DefaultArc(N tail, N head, WeightedInterface wi) {
		super();
		this.tail = tail;
		this.head = head;
		this.wi = wi;
	}

	/**
	 * {@inheritDoc}
	 */
	public WeightedInterface getWeightedInterface() {
		return wi;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public N getHead() {
		return this.head;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public N getTail() {
		return this.tail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getWeight() {
		return wi.evaluate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setWeight(WeightedInterface wi) {
		this.wi = wi;
	}

}
