package annas.graph;

import java.util.ArrayList;
import java.util.Collections;

public class GraphUnion<N, A extends ArcInterface<N>> implements
		GraphInterface<N, ArcInterface<N>> {

	private GraphInterface<N, A> g1;

	private GraphInterface<N, A> g2;

	private final String msg = "This method is not supported by "
			+ this.getClass().getName();

	public GraphUnion(GraphInterface<N, A> g1, GraphInterface<N, A> g2) {
		super();
		if (g1 == null || g2 == null) {
			throw new RuntimeException("The graph's suppied must not be null");
		}
		if (g1 == g2) {
			throw new RuntimeException("The graph's must not bethe same object");
		}
		this.g1 = g1;
		this.g2 = g2;
	}

	@Override
	public boolean addArc(N tail, N head, WeightedInterface wi) {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public boolean addNode(N node) {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public boolean contains(N node) {
		return this.g1.contains(node) || this.g2.contains(node);
	}

	@Override
	public ArrayList<ArcInterface<N>> getArc(N tail) {
		ArrayList<ArcInterface<N>> n = new ArrayList<ArcInterface<N>>();
		n.addAll(this.g1.getArc(tail));
		n.addAll(this.g2.getArc(tail));
		return new ArrayList<ArcInterface<N>>(Collections
				.unmodifiableCollection(n));
	}

	@Override
	public ArrayList<ArcInterface<N>> getArc(N tail, N head) {
		ArrayList<ArcInterface<N>> n = new ArrayList<ArcInterface<N>>();
		n.addAll(this.g1.getArc(tail, head));
		n.addAll(this.g2.getArc(tail, head));
		return new ArrayList<ArcInterface<N>>(Collections
				.unmodifiableCollection(n));
	}

	@Override
	public ArcFactory<N, ArcInterface<N>> getArcFactory() {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public ArrayList<N> getNodeMap() {
		ArrayList<N> n = new ArrayList<N>();
		n.addAll(this.g1.getNodeMap());
		n.addAll(this.g2.getNodeMap());
		return new ArrayList<N>(Collections.unmodifiableCollection(n));
	}

	@Override
	public int getNuArcs() {
		return this.g1.getNuArcs() + this.g2.getNuArcs();
	}

	@Override
	public int getNuNodes() {
		return this.g1.getNuNodes() + this.g2.getNuNodes();
	}

	@Override
	public GraphObserver getObserver() {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public int getVersion() {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public boolean removeArc(N tail, ArcInterface<N> arc) {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public boolean removeArc(N tail, N head) {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public boolean removeArc(N tail) {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public boolean removeNode(N node) {
		throw new UnsupportedOperationException(msg);
	}

	@Override
	public void resetArcs() {
		throw new UnsupportedOperationException(msg);
	}

}
