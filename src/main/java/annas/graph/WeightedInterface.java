package annas.graph;

import java.io.Serializable;

/**
 * Represents a weight function
 * 
 * @author Sam Wilson
 * 
 */
public interface WeightedInterface extends Serializable {

	/**
	 * Default weight of an arc
	 */
	public static final Double DEFAULT_ARC_WEIGHT = 1.0;

	/**
	 * Used to evaluate the weight function
	 * 
	 * @return the result from evaluating the weight function
	 */
	public Double evaluate();

}
