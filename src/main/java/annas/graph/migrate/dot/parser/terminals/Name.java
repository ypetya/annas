package annas.graph.migrate.dot.parser.terminals;

import annas.graph.migrate.dot.parser.DotToken;
import annas.graph.migrate.dot.parser.TokenReader;

/**
 * Models the Name of an attribute
 * 
 * @see <a href="http://en.wikipedia.org/wiki/DOT_language"> Dot language</a>
 * @author Sam Wilson
 * 
 */
public class Name extends DotToken {

	/**
	 * Name
	 */
	private String name;

	public Name(TokenReader tr) {
		super(tr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DotToken parse() {
		this.name = this.treader.consumeToken();
		return this;
	}

	@Override
	public String toString() {
		return "Name [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
