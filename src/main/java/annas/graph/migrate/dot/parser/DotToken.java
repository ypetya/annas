package annas.graph.migrate.dot.parser;

import annas.graph.migrate.dot.parser.exception.ParserException;

public abstract class DotToken {

	protected TokenReader treader;

	public DotToken(TokenReader tr) {
		super();
		this.treader = tr;
	}
	
	private DotToken() {
		super();
	}

	public abstract DotToken parse() throws ParserException;

}
