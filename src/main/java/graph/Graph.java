package annas.graph;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * Base graph class
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public abstract class Graph<N, A extends ArcInterface<N>> implements
		GraphInterface<N, A> {

	/**
	 * Holds a list of all nodes in this graph
	 */
	protected Map<N, MultiHashMap<N, A>> nodeMap;

	/**
	 * Factory for creating arcs
	 */
	protected ArcFactory<N, A> factory;

	/**
	 * GraphObserver @see GraphObserser
	 */
	protected GraphObserver observer = null;

	/**
	 * Keeps the version number of the graph. The version of the graph is
	 * initially 0, each call which modifies the graph increments the value by
	 * 1.
	 */
	protected int version;

	/**
	 * Default Constructor
	 */
	public Graph() {
		super();
		this.nodeMap = new Hashtable<N, MultiHashMap<N, A>>();
		this.factory = (ArcFactory<N, A>) new DefaultArcFactory<N>();
		this.version = 0;
	}

	public Graph(ArcFactory<N, A> arcfactory) {
		super();
		this.nodeMap = new Hashtable<N, MultiHashMap<N, A>>();
		this.factory = arcfactory;
		this.version = 0;
	}

	/**
	 * Constructor used to set the observer from creation
	 * 
	 * @param observer
	 */
	public Graph(GraphObserver observer) {
		super();
		this.nodeMap = new Hashtable<N, MultiHashMap<N, A>>();
		this.factory = (ArcFactory<N, A>) new DefaultArcFactory<N>();
		this.observer = observer;
		this.version = 0;
	}

	public GraphObserver getObserver() {
		return observer;
	}

	public void setObserver(GraphObserver observer) {
		this.observer = observer;
	}

	@Override
	public boolean addArc(N tail, N head, WeightedInterface wi) {
		if (this.nodeMap.containsKey(tail) && this.nodeMap.containsKey(head)) {
			this.nodeMap.get(tail).put(head,
					this.factory.create(tail, head, wi));
			Object[] h = { tail, head, wi };
			this.raiseEvent(new GraphEvent(Event.Arc_Added, h));
			return true;
		}
		return false;
	}

	@Override
	public boolean addNode(N node) {
		if (!this.nodeMap.containsKey(node)) {
			this.nodeMap.put(node, new MultiHashMap<N, A>());
			Object[] h = { node };
			this.raiseEvent(new GraphEvent(Event.Node_Added, h));
			return true;
		}
		return false;

	}

	@Override
	public boolean contains(N node) {
		return this.nodeMap.containsKey(node);
	}

	@Override
	public ArrayList<A> getArc(N tail) {
		MultiHashMap<N, A> h = this.nodeMap.get(tail);
		ArrayList<A> arcs = new ArrayList<A>();
		Set<N> s = (Set<N>) h.keySet();
		for (N n : (N[]) s.toArray()) {
			arcs.addAll(h.getCollection(n));
		}

		return arcs;
	}

	@Override
	public ArrayList<A> getArc(N tail, N head) {
		ArrayList<A> h = (ArrayList<A>) this.nodeMap.get(tail).getCollection(
				head);
		return (h != null) ? h : new ArrayList<A>();
	}

	@Override
	public ArcFactory<N, A> getArcFactory() {
		return this.factory;
	}

	@Override
	public ArrayList<N> getNodeMap() {
		return (ArrayList<N>) new ArrayList(this.nodeMap.keySet());
	}

	@Override
	public boolean removeArc(N tail, A arc) {
		if (this.nodeMap.containsKey(tail)) {
			boolean h = ((ArrayList<A>) this.nodeMap.get(tail).get(
					arc.getHead())).remove(arc);
			Object[] j = { tail, arc };
			this.raiseEvent(new GraphEvent(Event.Arc_Removed, j));
			return h;
		}
		return false;
	}

	@Override
	public boolean removeArc(N tail, N head) {
		boolean retval = true;
		if (this.contains(tail) && this.contains(head)) {
			ArrayList<A> arcs = new ArrayList<A>(this.nodeMap.get(tail)
					.getCollection(head));
			for (A f : arcs)
				retval = retval && this.removeArc(tail, f);
		}

		return retval;
	}

	@Override
	public boolean removeArc(N tail) {
		boolean retval = true;
		if (this.contains(tail)) {
			this.nodeMap.get(tail).clear();
			retval = this.nodeMap.get(tail).isEmpty();
			Object[] h = { tail };
			this.raiseEvent(new GraphEvent(Event.Arc_Reset, h));
		}

		return retval;
	}

	@Override
	public void resetArcs() {
		ArrayList<N> nodes = this.getNodeMap();
		for (N j : nodes) {
			this.nodeMap.get(j).clear();
			Object[] h = {};
			this.raiseEvent(new GraphEvent(Event.Arc_Reset, h));
		}

	}

	@Override
	public boolean removeNode(N node) {
		ArrayList<N> m = new ArrayList<N>(this.nodeMap.keySet());
		boolean retval = true;
		for (N mhm : m) {
			this.nodeMap.get(mhm).remove(node);
			retval = retval & !this.nodeMap.get(mhm).containsKey(node);
		}
		this.nodeMap.remove(node);
		Object[] j = { node };
		this.raiseEvent(new GraphEvent(Event.Node_Removed, j));
		return retval;

	}

	public int getVersion() {
		return this.version;
	}

	public int getNuArcs() {
		ArrayList<MultiHashMap<N, A>> adj = new ArrayList<MultiHashMap<N, A>>(
				this.nodeMap.values());
		int count = 0;
		for (MultiHashMap<N, A> h : adj) {
			count += h.totalSize();
		}
		return count;
	}

	public int getNuNodes() {
		return this.nodeMap.keySet().size();
	}

	protected void raiseEvent(GraphEvent e) {
		this.version++;
		if (this.observer != null) {
			this.observer.update(e);
		}
	}

}
