package annas.graph.migrate.dot.parser.exception;

public class SanityException extends Exception {

	private String msg;

	public SanityException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "SanityCheck " + msg + "\n";
	}

}
