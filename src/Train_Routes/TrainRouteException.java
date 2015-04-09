package Train_Routes;

public class TrainRouteException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -665023366943828833L;
	private String filePath = "";
	private String lineText = "";
	private int lineNumber = 0;
	private String errorText = "";
	
	public TrainRouteException(String filePath, String lineText, int lineNumber, String errorText){
		this.filePath = filePath;
		this.lineText = lineText;
		this.lineNumber = lineNumber;
		this.errorText = errorText;
	}
	
	public String getFilePath(){
		return this.filePath;
	}
	
	public String getLineText(){
		return this.lineText;
	}
	
	public int getLineNumber(){
		return this.lineNumber;
	}
	
	public String getErrorText(){
		return this.errorText;
	}
}
