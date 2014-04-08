package annas.graph.migrate.dot.parser.nonterminals;

import java.util.ArrayList;

import annas.graph.migrate.dot.parser.terminals.Name;

/**
 * Models a very abstract graph. This interface is used by the graph builder and
 * provides all the methods required to construct a graph (annas.graph.graph).
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public interface GraphStructure {

	public Name getName();

	public void setName(Name name);

	public ArrayList<Node> getNodes();

	public void setNodes(ArrayList<Node> nodes);

	public ArrayList<Arc> getArcs();

	public void setArcs(ArrayList<Arc> arcs);

}
