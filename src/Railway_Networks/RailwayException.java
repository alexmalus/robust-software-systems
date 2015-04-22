package Railway_Networks;

public class RailwayException extends Exception {

	private static final long serialVersionUID = 1L;
	String fileName;
	String lineText;
	String lineNumber;
	String errorMessage;
	
	public RailwayException(String fileName, String lineText, String lineNumber, String errorMessage) {
		this.fileName = fileName;
		this.lineText = lineText;
		this.lineNumber = lineNumber;
		this.errorMessage = errorMessage;
	}
}
