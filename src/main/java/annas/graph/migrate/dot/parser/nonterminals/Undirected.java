package annas.graph.migrate.dot.parser.nonterminals;

import java.util.ArrayList;

import annas.graph.migrate.dot.parser.DotToken;
import annas.graph.migrate.dot.parser.TokenReader;
import annas.graph.migrate.dot.parser.exception.ParserException;
import annas.graph.migrate.dot.parser.terminals.Name;

/**
 * Models an undirected graph in DOT
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public class Undirected extends DotToken implements GraphStructure {

	/**
	 * Name of the graph
	 */
	private Name name;

	/**
	 * Nodes parsed
	 */
	private ArrayList<Node> nodes;

	/**
	 * Arcs parsed
	 */
	private ArrayList<Arc> arcs;

	public Undirected(TokenReader tr) {
		super(tr);
		this.nodes = new ArrayList<Node>();
		this.arcs = new ArrayList<Arc>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DotToken parse() throws ParserException {

		this.name = (Name) new Name(this.treader).parse();
		String bracket = this.treader.consumeToken();
		while (this.treader.hasMoreTokens()
				&& !this.treader.nextToken().equals("}")) {

			Name item = (Name) new Name(this.treader).parse();
			if (this.treader.nextToken().equals("--")
					|| this.treader.nextToken().equals("->")) {
				if (this.treader.nextToken().equals("->")) {
					throw new ParserException(
							this.treader,
							"Undirected graphs are not permitted to use directed arcs",
							this.treader.getCharNo());
				}

				Arc a = (Arc) new Arc(this.treader).parse();
				a.setTail(item);
				this.arcs.add(a);

			} else {
				Node n = (Node) new Node(this.treader).parse();
				n.setLabel(item);
				this.nodes.add(n);
			}
		}
		if (this.treader.nextToken().equals("}")) {
			this.treader.consumeToken();
		}
		return this;
	}

	@Override
	public String toString() {
		return "Undirected [arcs=" + arcs + ", name=" + name + ", nodes="
				+ nodes + "]";
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * @return the nodes
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	/**
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the arcs
	 */
	public ArrayList<Arc> getArcs() {
		return arcs;
	}

	/**
	 * @param arcs
	 *            the arcs to set
	 */
	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}


}
