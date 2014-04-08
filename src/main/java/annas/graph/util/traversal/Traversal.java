package annas.graph.util.traversal;

import java.util.Iterator;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

public interface Traversal<N, A extends ArcInterface<N>> {

	public abstract Iterator<N> run(N s);

	public abstract Iterator<N> run(N s, N tar);

	public abstract GraphInterface<N, A> getGraph();

	public abstract void setGraph(GraphInterface<N, A> g);

}