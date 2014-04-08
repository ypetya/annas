package annas.graph.migrate.dot.parser.exception;

public class BuilderException extends Exception {

	private String msg;

	public BuilderException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "BuilderException " + msg + "\n";
	}
}
