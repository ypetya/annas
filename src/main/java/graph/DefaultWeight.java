package annas.graph;

/**
 * Default implementation of WeightedInterface
 * 
 * @author Sam Wilson
 * 
 */
public class DefaultWeight implements WeightedInterface {

	private Double weight;

	public DefaultWeight(Double weight) {
		super();
		this.weight = weight;
	}

	/**
	 * {@inheritDoc}
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double evaluate() {
		return this.weight;
	}

}
