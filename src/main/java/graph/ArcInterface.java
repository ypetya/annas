package annas.graph;

import java.io.Serializable;

/**
 * Interface of all arcs used in a Graph
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 */
public interface ArcInterface<N> extends Serializable {

	/**
	 * Gets the head of the arc
	 * 
	 * @return Node at the head of the arc
	 */
	public N getHead();

	/**
	 * Gets the tail of the arc
	 * 
	 * @return Node at the tail of the arc
	 */
	public N getTail();

	/**
	 * Get the Weight of the arc by evaluating @see WeightedInterface.evaluate()
	 * 
	 * @return weight of the arc
	 */
	public Double getWeight();

	/**
	 * Sets the Weight function
	 * 
	 * @param wi
	 *            new weight function
	 */
	public void setWeight(WeightedInterface wi);

	/**
	 * Gets the Weight function
	 * 
	 * @return WeighteInterface used by the arc
	 */
	public WeightedInterface getWeightedInterface();

}
