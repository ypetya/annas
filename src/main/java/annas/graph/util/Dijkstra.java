package annas.graph.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

import annas.graph.ArcInterface;
import annas.graph.DefaultArc;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.GraphPath;

/**
 * Determines a single source-destination shortest path. Implements the
 * algorithm described <a
 * href="http://mathworld.wolfram.com/DijkstrasAlgorithm.html"> here </a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class Dijkstra<N, A extends ArcInterface<N>> {

	/**
	 * Graph to implement the algorithm on.
	 */
	protected GraphInterface<N, A> graph;

	private Hashtable<N, Marker<N>> htable;

	public Dijkstra(GraphInterface<N, A> graph) {
		super();
		this.graph = graph;
		this.htable = new Hashtable<N, Marker<N>>();
		ArrayList<N> nodes = this.graph.getNodeMap();
		for (int i = 0; i < nodes.size(); i++) {
			this.htable.put(nodes.get(i), new Marker<N>(nodes.get(i)));
		}
	}

	/**
	 * Executes a single source-destination shortest path search.
	 * 
	 * @param source
	 *            Source node
	 * @param destination
	 *            Destination node
	 * @return GraphPath of the shortest path
	 */
	public GraphPath<N, DefaultArc<N>> execute(N source, N destination) {
		Marker<N> Current = this.htable.get(source);
		Current.setOrder(0);
		Current.setDist(0);
		Current.setPermanent(true);

		ArrayList<N> nodes = this.graph.getNodeMap();
		ArrayList<Marker<N>> q = new ArrayList<Marker<N>>(this.htable.values());


		for (int i = 0; i < nodes.size(); i++) {
			this.tempMarker(Current, Current.dist);
			Current = this.extractMin(q);
			Current.setOrder(i);
			Current.setPermanent(true);
			q.remove(Current);
		}

		GraphPath<N, DefaultArc<N>> gp = new GraphPath<N, DefaultArc<N>>(
				source, destination);
		
		N tmp = destination;
		N tmpp = null;
		ArrayList<N> path = new ArrayList<N>();
		path.add(tmp);
		while(tmp != source) {
			tmpp = this.htable.get(tmp).getPrevious();
			path.add(tmpp);
			tmp = tmpp;
		}
		
		Marker<N> d,s = null;
		for(int i = path.size()-1; i!=0; i--){
			d = this.htable.get(path.get(i-1));
			s = this.htable.get(path.get(i));
			gp.add(s.representing, new DefaultArc(s.representing,d.representing,new DefaultWeight(d.dist-s.dist)));
		}

		return gp;
	}

	private void tempMarker(Marker<N> n, double d) {
		ArrayList<A> c = this.graph.getArc((N) n.representing);
		A CurrentArc;
		Marker<N> CurrentMarker;
		for (int i = 0; i < c.size(); i++) {
			CurrentArc = c.get(i);
			CurrentMarker = this.htable.get(CurrentArc.getHead());

			if (CurrentMarker.getDist() > d + CurrentArc.getWeight()) {
				CurrentMarker.setPrevious((N) n.representing);
				CurrentMarker.setDist(d + CurrentArc.getWeight());
			}
		}
	}

	private Marker<N> extractMin(ArrayList<Marker<N>> q ){
		ArrayList<Marker<N>> l = q;
		Collections.sort(l, new DistComparator());
		return (Marker<N>) l.get(0);
	}

	class DistComparator implements Comparator<Marker<N>> {

		@Override
		public int compare(Marker<N> o1, Marker<N> o2) {
			if (o1.dist < o2.dist)
				return -1;
			else if (o1.dist > o2.dist)
				return 1;
			else
				return 0;

		}

	}

	class Marker<N> {

		private N representing;

		private double dist = Double.POSITIVE_INFINITY;

		private N previous;

		private int order;

		private boolean permanent;

		public Marker(N rep) {
			super();
			this.representing = rep;
			this.order = Integer.MAX_VALUE;
			this.previous = null;
			this.permanent = false;

		}


		public double getDist() {
			return dist;
		}

		public void setDist(double dist) {
			this.dist = dist;
		}

		public N getPrevious() {
			return previous;
		}

		public void setPrevious(N previous) {
			this.previous = previous;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public boolean isPermanent() {
			return permanent;
		}

		public void setPermanent(boolean permanent) {
			this.permanent = permanent;
		}

		@Override
		public String toString() {
			return "Marker [dist=" + dist + ", order=" + order + ", permanent="
					+ permanent + ", previous=" + previous
					+ "]";
		}

	}
}
