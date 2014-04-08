package annas.graph.migrate.dot.parser.terminals;

import annas.graph.migrate.dot.parser.DotToken;
import annas.graph.migrate.dot.parser.TokenReader;
import annas.graph.migrate.dot.parser.exception.ParserException;

/**
 * Models the attribute in the dot format.
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public class Attribute extends DotToken {
	
	/**
	 * Attributed name
	 */
	private Name name;
	
	/**
	 * Value
	 */
	private Value value;

	public Attribute(TokenReader tr) {
		super(tr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DotToken parse() throws ParserException {
		this.name = (Name) new Name(this.treader).parse();

		if (this.treader.nextToken().equals("=")) {
			this.treader.consumeToken();

			this.value = (Value) new Value(this.treader).parse();

		} else {
			throw new ParserException(this.treader,
					"Attribute has not been assigned a value", this.treader
							.getCharNo());
		}
		return this;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", value=" + value + "]";
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
	 * @return the value
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Value value) {
		this.value = value;
	}

}
