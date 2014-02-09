package annas.graph;

import java.io.Serializable;

/**
 * Any class wishing to observer a class must implement this interface
 * 
 * @author Sam Wilson
 */
public interface GraphObserver extends Serializable {

	/**
	 * Graph event representing the event that has occurred
	 * 
	 * @param e
	 */
	public void update(GraphEvent event);

}
