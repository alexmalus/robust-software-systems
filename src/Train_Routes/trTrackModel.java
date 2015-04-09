package Train_Routes;

public class trTrackModel {
	private char id;
	private boolean isStop;
//	private trTrackModel leftConnection;
//	private trTrackModel rightConnection;
	
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
	
//	public void setIsStop(boolean isStop){
//		this.isStop = isStop;
//	}
//	
//	public trTrackModel getLeftConnection(){
//		return this.leftConnection;
//	}
//	
//	public void setLeftConnection(trTrackModel leftConenction){
//		this.leftConnection = leftConenction;
//	}
//	
//	public trTrackModel getRightConnection(){
//		return this.rightConnection;
//	}
//	
//	public void setRightConnection(trTrackModel rightConnection){
//		this.rightConnection = rightConnection;
//	}
}
