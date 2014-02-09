package annas.graph;

/**
 * Graph Event models All possible events that can occur on a Graph
 * 
 * @author Sam Wilson
 */
public class GraphEvent {

	/**
	 * Code of the Event the objects is modelling
	 */
	private Event event;
	
	/**
	 * List of Objects
	 */
	private Object[] params;

	
	
	/**
	 * Default Constructor
	 */
	public GraphEvent() {
		super();
	}

	/**
	 * Constructor
	 * @param event Event code
	 * @param params List of objects from the method which raised the event
	 * in the same order as the parameters in that method.
	 */
	public GraphEvent(Event event, Object[] params) {
		super();
		this.event = event;
		this.params = params;
	}

	/**
	 * Gets the event
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 * A list of objects in the same or as the parameters of the method that raised the event
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * Sets the list of objects. The objects should be in the same order as the 
	 * parameters of the method that raised the event
	 * @param params the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}
	
	
	
	
}
