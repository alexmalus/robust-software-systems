package Railway_Networks;

public class RailwayException extends Exception {

	private static final long serialVersionUID = 1L;
	String fileName;
	String lineText;
	int lineNumber;
	String errorMessage;
	
	public RailwayException(String fileName, String lineText, int lineNumber2, String errorMessage) {
		this.fileName = fileName;
		this.lineText = lineText;
		this.lineNumber = lineNumber2;
		this.errorMessage = errorMessage;
	}
}
