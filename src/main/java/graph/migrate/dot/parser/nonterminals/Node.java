package annas.graph.migrate.dot.parser.nonterminals;

import java.util.Hashtable;

import annas.graph.migrate.dot.parser.DotToken;
import annas.graph.migrate.dot.parser.TokenReader;
import annas.graph.migrate.dot.parser.exception.ParserException;
import annas.graph.migrate.dot.parser.terminals.Attribute;
import annas.graph.migrate.dot.parser.terminals.Name;

/**
 * Models a node in Dot
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public class Node extends DotToken {

	/**
	 * Name
	 */
	private Name label;

	/**
	 * Attributes
	 */
	private Hashtable<String, Object> attr;

	public Node(TokenReader tr) {
		super(tr);
		this.attr = new Hashtable<String, Object>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DotToken parse() throws ParserException {

		if (this.treader.nextToken().equals("[")) {
			this.treader.consumeToken();
			while (this.treader.hasMoreTokens() && !this.treader.hasEndStmt()
					&& !this.treader.nextToken().equals("]")) {
				Attribute a = (Attribute) new Attribute(this.treader).parse();
				this.attr.put(a.getName().getName(), a.getValue().getValue());
			}

			if (this.treader.nextToken().equals("]")) {
				this.treader.consumeToken();
			} else {
				throw new ParserException(this.treader,
						"No closing attribute missing \"]\" at "
								+ this.treader.getCharNo(), this.treader
								.getCharNo());
			}
		}
		if (this.treader.nextTokenIsSemiColon()) {
			this.treader.consumeSemiColon();
		} else {
			throw new ParserException(this.treader, "Missing terminator",
					this.treader.getCharNo());
		}

		return this;
	}

	/**
	 * @return the label
	 */
	public Name getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(Name label) {
		this.label = label;
	}

	/**
	 * @return the attr
	 */
	public Hashtable<String, Object> getAttr() {
		return attr;
	}

	/**
	 * @param attr
	 *            the attr to set
	 */
	public void setAttr(Hashtable<String, Object> attr) {
		this.attr = attr;
	}

	@Override
	public String toString() {
		return "Node [attr=" + attr + ", label=" + label + "]";
	}

}
