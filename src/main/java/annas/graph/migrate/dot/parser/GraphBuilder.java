package annas.graph.migrate.dot.parser;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;
import annas.graph.migrate.dot.parser.nonterminals.GraphStructure;

public abstract class GraphBuilder<N, A extends ArcInterface<N>> {

	public abstract GraphInterface<N, A> build(GraphStructure gs);
}
