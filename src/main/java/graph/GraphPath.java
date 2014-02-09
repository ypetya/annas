package annas.graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Models a Path in a graph
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class GraphPath<N, A extends ArcInterface<N>> {

	/**
	 * Graph containing the path only
	 */
	private GraphInterface<N, A> graph;

	/**
	 * Start node
	 */
	private N start;

	/**
	 * End node
	 */
	private N end;

	/**
	 * Last node added
	 */
	private N lastadded;

	/**
	 * If the path has reached its end node
	 */
	private boolean finished;

	/**
	 * Constructor
	 * 
	 * @param start
	 *            start node of the path
	 * @param end
	 *            end node of the path
	 */
	public GraphPath(N start, N end) {
		super();
		this.start = start;
		this.end = end;
		this.lastadded = null;
		this.finished = false;
		this.graph = new DirectedGraph<N, A>();
	}

	/**
	 * Adds the next node into the path
	 * 
	 * @param node
	 *            Tail
	 * @param arc
	 */
	public void add(N node, A arc) {
		if ((this.graph.getNuNodes() == 0 && node == start)
				|| lastadded != null & !this.finished) {
			if(this.lastadded == null) {
				this.lastadded = node;
				this.graph.addNode(node);
			}
			if (!this.graph.addNode(arc.getHead())) {
				throw new IllegalArgumentException(
						"Cylces/Branches  are not permitted in a Graph path");
			}
			this.graph.addArc(lastadded, arc.getHead(), arc
					.getWeightedInterface());

			this.finished = arc.getHead() == this.end;
			this.lastadded = arc.getHead();
		} else {
			if(!this.finished) {
			throw new IllegalArgumentException(
					"The first node in the path must be " + start
							+ ", as specified in the constructor");
			} else if (this.finished) {
				throw new IllegalArgumentException(
						"The last node in the path must be " + end
								+ ", as specified in the constructor");
			}
		}
	}

	/**
	 * Number of nodes in the path
	 * 
	 * @return
	 */
	public int size() {
		return this.graph.getNuNodes();
	}

	/**
	 * Gets an iterator over the path
	 * 
	 * @return
	 */
	public Iterator<N> getIterator() {
		ArrayList<N> list = new ArrayList<N>();
		N tmp = this.start;

		while (tmp != this.end) {
			list.add(tmp);
			tmp = this.graph.getArc(tmp).get(0).getHead();
		}
		list.add(this.end);

		return list.iterator();

	}
	
	/**
	 * Gets the distance between the start node and end node
	 * 
	 * @return
	 */
	public double getDistance(){
		Iterator<N> i = this.getIterator();
		double dist = 0;
		while(i.hasNext()){
			N n = i.next();
			if(n != this.end)
			dist += this.graph.getArc(n).get(0).getWeight();
		}
		return dist;
	}

}
