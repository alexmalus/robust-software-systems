package Train_Routes;

/**
 * 
 * @author Esbern Andersen-Hoppe s093484
 *
 */
public class trTrackTimingModel {

	private Character trackId;
	private int arrivalTime;
	private int depatureTime;
	private String routeName;
	
	public trTrackTimingModel(Character trackId, int arrivalTime, int departureTime, String routeName){
		this.trackId = trackId;
		this.arrivalTime = arrivalTime;
		this.depatureTime = departureTime;
		this.routeName = routeName;
	}
	
	public Character getTrackId(){
		return this.trackId;
	}
	
	public int getArrivalTime(){
		return this.arrivalTime;
	}
	
	public int getDepartureTime(){
		return this.depatureTime;
	}
	
	public String getRouteName(){
		return this.routeName;
	}
}
