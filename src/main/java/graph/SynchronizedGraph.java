package annas.graph;

public class SynchronizedGraph<N, A extends ArcInterface<N>> extends
		GraphDelegator<N, A> {

	public SynchronizedGraph(GraphInterface<N, A> delegate) {
		super(delegate);
	}

}
