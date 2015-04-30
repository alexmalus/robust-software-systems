package Train_Routes;

/**
 * 
 * @author Esbern Andersen-Hoppe s093484
 *
 */
public class TrainRouteValidationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1985762824165698985L;
	private String errorText = "";
	
	public TrainRouteValidationException(String error){
		this.errorText = error;
	}
	
	public String getErrorText(){
		return this.errorText;
	}
}
