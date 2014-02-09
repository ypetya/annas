package annas.graph.migrate.dot.parser;

import java.util.ArrayList;

import annas.graph.GraphInterface;
import annas.graph.migrate.dot.parser.exception.BuilderException;
import annas.graph.migrate.dot.parser.exception.ParserException;
import annas.graph.migrate.dot.parser.exception.SanityException;
import annas.graph.migrate.dot.parser.nonterminals.Arc;
import annas.graph.migrate.dot.parser.nonterminals.Directed;
import annas.graph.migrate.dot.parser.nonterminals.GraphStructure;
import annas.graph.migrate.dot.parser.nonterminals.Node;
import annas.graph.migrate.dot.parser.nonterminals.Undirected;
import annas.graph.migrate.dot.parser.terminals.Name;

/**
 * DOT language parser
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public class Parser {

	/**
	 * Enable debug mode
	 */
	private final boolean DEBUG = true;

	/**
	 * Builds a graph
	 */
	private GraphBuilder gb;

	/**
	 * Token reader
	 */
	private TokenReader treader;

	/**
	 * Constructor
	 * 
	 * @param g
	 *            Builder
	 * @param raw
	 *            input string
	 */
	public Parser(GraphBuilder g, String raw) {
		super();
		this.gb = g;
		this.treader = new TokenReader(raw);
	}

	/**
	 * Parse the input
	 * 
	 * @return
	 * @throws ParserException
	 * @throws SanityException
	 * @throws BuilderException
	 */
	public GraphInterface parse() throws ParserException, SanityException,
			BuilderException {

		DotToken structs = null;
		while (this.treader.hasMoreTokens()) {
			String lookahead = this.treader.consumeToken();

			if (lookahead.equals("graph")) {
				structs = new Undirected(this.treader).parse();

			} else if (lookahead.equals("digraph")) {

				structs = new Directed(this.treader).parse();
			} else {
				throw new ParserException(this.treader,
						"Unknown type at char: " + this.treader.getCharNo(),
						this.treader.getCharNo());
			}

		}
		this.SanityCheck(structs);

		return this.assemble(structs);

	}

	/**
	 * Checks for simple errors which should prevent a graph from being
	 * constructed
	 * 
	 * @param dt
	 * @throws SanityException
	 */
	private void SanityCheck(DotToken dt) throws SanityException {
		if (dt instanceof GraphStructure) {
			GraphStructure gs = (GraphStructure) dt;
			ArrayList<Name> names = this.getNameList(gs.getNodes());
			for (Arc a : gs.getArcs()) {

				if (!names.contains(a.getHead())) {
					throw new SanityException("The node "
							+ a.getHead().getName() + " is undefined");
				}
				if (!names.contains(a.getTail())) {
					throw new SanityException("The node "
							+ a.getTail().getName() + " is undefined");
				}

			}

		} else {
			throw new SanityException(
					"Item parsed was not a graph, how very strange, someone may have been messing");
		}

	}

	/**
	 * Assembles the graph using the graph builder from the graphstrcuture.
	 * 
	 * @param dt
	 * @return
	 * @throws BuilderException
	 */
	private GraphInterface assemble(DotToken dt) throws BuilderException {
		if (dt instanceof GraphStructure) {
			GraphStructure gs = (GraphStructure) dt;

			if (this.gb != null) {
				return this.gb.build(gs);
			} else {
				throw new BuilderException("builder null pointer");
			}

		} else {
			throw new BuilderException(
					"Item parsed was not a graph, how very strange, someone may have been messing");
		}
	}

	private ArrayList<Name> getNameList(ArrayList<Node> ns) {
		ArrayList<Name> retval = new ArrayList<Name>();

		for (Node n : ns)
			retval.add(n.getLabel());

		return retval;

	}

	private void debug(String msg) {
		if (DEBUG) {
			System.out.println(msg);
		}
	}
}
