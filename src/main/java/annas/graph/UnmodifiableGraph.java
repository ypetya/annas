package annas.graph;


public class UnmodifiableGraph<N, A extends ArcInterface<N>> extends
		GraphDelegator<N, A> {

	private final String errorMsg = "This graph does not permit modifications";

	public UnmodifiableGraph(GraphInterface<N, A> delegate) {
		super(delegate);
	}

	@Override
	public boolean addArc(N tail, N head, WeightedInterface wi) {
		throw new UnsupportedOperationException(this.errorMsg);
	}

	@Override
	public boolean addNode(N node) {
		throw new UnsupportedOperationException(this.errorMsg);
	}

	@Override
	public boolean removeArc(N tail, A arc) {
		throw new UnsupportedOperationException(this.errorMsg);
	}

	@Override
	public boolean removeArc(N tail, N head) {
		throw new UnsupportedOperationException(this.errorMsg);
	}

	@Override
	public boolean removeArc(N tail) {
		throw new UnsupportedOperationException(this.errorMsg);
	}

	@Override
	public boolean removeNode(N node) {
		throw new UnsupportedOperationException(this.errorMsg);
	}

	@Override
	public void resetArcs() {
		throw new UnsupportedOperationException(this.errorMsg);
	}

}
