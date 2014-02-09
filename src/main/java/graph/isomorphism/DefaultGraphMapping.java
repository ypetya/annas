/**
 * 
 */
package annas.graph.isomorphism;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * @author Sam Wilson
 * @version 1.1
 * 
 */
public class DefaultGraphMapping<N1, A1 extends ArcInterface<N1>, N2, A2 extends ArcInterface<N2>>
		implements
 GraphMapping<N1, A1, N2, A2> {

	private Map<N1, N2> g1Tog2;

	private Map<N2, N1> g2Tog1;

	private GraphInterface<N1, A1> g1;

	private GraphInterface<N2, A2> g2;

	private static final String err_msg = "This Graph mapping is not the mapping for one/both of the graph provided";

	/**
	 * 
	 */
	public DefaultGraphMapping(GraphInterface<N1, A1> g1,
			GraphInterface<N2, A2> g2) {
		super();
		this.g1 = g1;
		this.g2 = g2;

		int maxNodeCount = (g1.getNuNodes() > g2.getNuNodes()) ? g1
				.getNuNodes() : g2.getNuNodes();

		this.g1Tog2 = new Hashtable<N1, N2>(maxNodeCount);
		this.g2Tog1 = new Hashtable<N2, N1>(maxNodeCount);
	}

	@Override
	public void addMapping(N1 n1, N2 n2) {
		this.g1Tog2.put(n1, n2);
		this.g2Tog1.put(n2, n1);
	}

	@Override
	public int size() {
		return this.g1Tog2.size();
	}

	@Override
	public Object getMappedNode(GraphInterface from, GraphInterface to,
			Object node) {

		if (from == this.g1 && to == this.g2) {
			return this.fromg1Tog2((N1) node);
		} else if (from == this.g2 && to == this.g1) {
			return this.fromg2Tog1((N2) node);
		} else {
			throw new RuntimeException(err_msg);
		}

	}

	@Override
	public ArrayList<ArcInterface> getMappedArc(GraphInterface from,
			GraphInterface to, ArcInterface arc) {
		Object newhead = this.getMappedNode(from, to, arc.getHead());
		Object newtail = this.getMappedNode(from, to, arc.getTail());

		if (this.correctGraphsforMapping(from, to)) {
			return to.getArc(newtail, newhead);
		} else {
			throw new RuntimeException(err_msg);
		}

	}

	private boolean correctGraphsforMapping(GraphInterface from,
			GraphInterface to) {

		return ((this.g1 == from || this.g1 == to) && (this.g2 == from || this.g2 == to));
	}

	private N2 fromg1Tog2(N1 node) {
		return this.g1Tog2.get(node);
	}

	private N1 fromg2Tog1(N2 node) {
		return this.g2Tog1.get(node);
	}


	@Override
	public String toString() {
		return "DefaultGraphMapping [g1Tog2=" + g1Tog2 + ", g2Tog1=" + g2Tog1
				+ "]";
	}

	@Override
	public boolean conatains(GraphInterface g, Object obj) {
		if (g == this.g1) {
			return this.g1Tog2.get(obj) != null;
		} else if (g == this.g2) {
			return this.g2Tog1.get(obj) != null;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((g1Tog2 == null) ? 0 : g1Tog2.hashCode());
		result = prime * result + ((g2Tog1 == null) ? 0 : g2Tog1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DefaultGraphMapping)) {

			if (obj instanceof Hashtable) {
				return this.g1Tog2.equals(obj);
			}
			return false;
		}

		DefaultGraphMapping other = (DefaultGraphMapping) obj;
		if (g1Tog2 == null) {
			if (other.g1Tog2 != null)
				return false;
		} else if (!g1Tog2.equals(other.g1Tog2))
			return false;
		if (g2Tog1 == null) {
			if (other.g2Tog1 != null)
				return false;
		} else if (!g2Tog1.equals(other.g2Tog1))
			return false;
		return true;
	}

}
