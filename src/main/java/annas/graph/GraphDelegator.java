package annas.graph;

import java.util.ArrayList;

/**
 * Delegates all call to the graph provided at object creation.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc Type
 */
public class GraphDelegator<N, A extends ArcInterface<N>> implements
		GraphInterface<N, A> {

	protected GraphInterface<N, A> delegate;

	public GraphDelegator(GraphInterface<N, A> delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public boolean addArc(N tail, N head, WeightedInterface wi) {
		return this.delegate.addArc(tail, head, wi);
	}

	@Override
	public boolean addNode(N node) {
		return this.delegate.addNode(node);
	}

	@Override
	public boolean contains(N node) {
		return this.delegate.contains(node);
	}

	@Override
	public ArrayList<A> getArc(N tail) {
		return this.delegate.getArc(tail);
	}

	@Override
	public ArrayList<A> getArc(N tail, N head) {
		return this.delegate.getArc(tail, head);
	}

	@Override
	public ArcFactory<N, A> getArcFactory() {
		return this.delegate.getArcFactory();
	}

	@Override
	public ArrayList<N> getNodeMap() {
		return this.delegate.getNodeMap();
	}

	@Override
	public int getNuArcs() {
		return this.delegate.getNuArcs();
	}

	@Override
	public int getNuNodes() {
		return this.delegate.getNuNodes();
	}

	@Override
	public GraphObserver getObserver() {
		return this.delegate.getObserver();
	}

	@Override
	public int getVersion() {
		return this.delegate.getVersion();
	}

	@Override
	public boolean removeArc(N tail, A arc) {
		return this.delegate.removeArc(tail, arc);
	}

	@Override
	public boolean removeArc(N tail, N head) {
		return this.delegate.removeArc(tail, head);
	}

	@Override
	public boolean removeArc(N tail) {
		return this.delegate.removeArc(tail);
	}

	@Override
	public boolean removeNode(N node) {
		return this.delegate.removeNode(node);
	}

	@Override
	public void resetArcs() {
		this.delegate.resetArcs();

	}

}
