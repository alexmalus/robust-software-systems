package Train_Routes;

public class trCommentModel {
	
	private String filePath = "";
	private int lineNumber = 0;
	private String comment = "";
	
	public trCommentModel(String filePath, int lineNumber, String comment){
		this.filePath = filePath;
		this.lineNumber = lineNumber;
		this.comment = comment;
	}
	
	public String getFilePath(){
		return this.filePath;
	}
	
	public int getLineNumber(){
		return this.lineNumber;
	}
	
	public String getComment(){
		return this.comment;
	}
}
