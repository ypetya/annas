package annas.graph.migrate.dot.parser.terminals;

import annas.graph.migrate.dot.parser.DotToken;
import annas.graph.migrate.dot.parser.TokenReader;
import annas.graph.migrate.dot.parser.exception.ParserException;

/**
 * Models the value of an attribute
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public class Value extends DotToken {

	/**
	 * Type of the value
	 */
	private Class type;

	/**
	 * Value
	 */
	private Object value;

	public Value(TokenReader tr) {
		super(tr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DotToken parse() throws ParserException {
		if (this.treader.hasMoreTokens()) {
			String str = this.treader.consumeToken();

			try {
				Integer i = Integer.parseInt(str);
				this.value = i;
				this.type = Integer.class;
			} catch (NumberFormatException e) {
				this.value = str;
				this.type = String.class;
			}

		} else {
			throw new ParserException(this.treader,
					"Unexpected end of statement",
					this.treader.getCharNo());
		}

		return this;
	}

	/**
	 * @return the type
	 */
	public Class getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Class type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Value [type=" + type + ", value=" + value + "]";
	}

}
