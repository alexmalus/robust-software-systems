package Train_Routes;

/**
 * 
 * @author Esbern Andersen-Hoppe s093484
 *
 */
public class trTrackModel {
	private char id;
	private boolean isStop;
	
	public trTrackModel(char id, boolean isStop){
		this.id = id;
		this.isStop = isStop;
	}
	
	public char getId(){
		return this.id;
	}
	
	public boolean getIsStop(){
		return this.isStop;
	}
}
