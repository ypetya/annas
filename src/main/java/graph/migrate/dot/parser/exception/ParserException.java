package annas.graph.migrate.dot.parser.exception;

import annas.graph.migrate.dot.parser.TokenReader;

/**
 * Models exceptions caused by the parsing of an input.
 * 
 * @author Sam Wilson
 * 
 */
public class ParserException extends Exception {

	/**
	 * Description of the cause
	 */
	private String message;

	/**
	 * Location in the input
	 */
	private int location;

	private TokenReader treader;

	public ParserException(TokenReader tr, String message, int errorloc) {
		super();
		this.message = message;
		this.location = errorloc;
		this.treader = tr;
	}

	private ParserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	private ParserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	private ParserException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	private String errorMsg() {
		String txt = this.treader.toString().replaceAll("\t", " ");
		txt = txt.replaceAll("\n", " ");
		String str = txt.substring(0, this.location) + "\n";
		for (int i = 0; i < this.location - 1; i++) {
			str = str + " ";
		}
		str = str + "^";
		return str;
	}

	@Override
	public String toString() {
		return "ParserException: " + message + " \n"
 + this.errorMsg();
	}

}
