package annas.graph.migrate.dot.parser.nonterminals;

import java.util.Hashtable;

import annas.graph.migrate.dot.parser.DotToken;
import annas.graph.migrate.dot.parser.TokenReader;
import annas.graph.migrate.dot.parser.exception.ParserException;
import annas.graph.migrate.dot.parser.terminals.Attribute;
import annas.graph.migrate.dot.parser.terminals.Name;

/**
 * Models an Arc in dot
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public class Arc extends DotToken {

	/**
	 * Tail
	 */
	private Name tail;

	/**
	 * Head
	 */
	private Name head;

	/**
	 * Attributes
	 */
	private Hashtable<String, Object> attr;

	private boolean directed;

	public Arc(TokenReader tr) {
		super(tr);
		this.attr = new Hashtable<String, Object>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DotToken parse() throws ParserException {

		if (this.treader.nextToken().equals("--")
				|| this.treader.nextToken().equals("->")) {
			this.directed = this.treader.consumeToken().equals("->");
			this.head = (Name) new Name(this.treader).parse();

			if (this.treader.nextToken().equals("[")) {

				while (this.treader.hasMoreTokens()
						&& !this.treader.hasEndStmt()
						&& !this.treader.nextToken().equals("]")
						&& this.treader.nextToken().equals("[")) {
					this.treader.consumeToken();
					Attribute a = (Attribute) new Attribute(this.treader)
							.parse();
					this.attr.put(a.getName().getName(), a.getValue()
							.getValue());
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
		} else {
			throw new ParserException(this.treader,
					"If you ever get this message your input is dynamic",
					this.treader.getCharNo());
		}
		if (this.treader.nextTokenIsSemiColon()) {
			this.treader.consumeSemiColon();
		} else {
			throw new ParserException(this.treader, "Missing terminator",
					this.treader.getCharNo());
		}
		;
		return this;
	}

	/**
	 * @return the tail
	 */
	public Name getTail() {
		return tail;
	}

	/**
	 * @param tail
	 *            the tail to set
	 */
	public void setTail(Name tail) {
		this.tail = tail;
	}

	/**
	 * @return the head
	 */
	public Name getHead() {
		return head;
	}

	/**
	 * @param head
	 *            the head to set
	 */
	public void setHead(Name head) {
		this.head = head;
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
		return "Arc [attr=" + attr + ", directed=" + directed + ", head="
				+ head + ", tail=" + tail + "]";
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

}
