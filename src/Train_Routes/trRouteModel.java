package Train_Routes;

import java.util.ArrayList;

public class trRouteModel {

	private String name;
	private ArrayList<trTrackModel> route;
	private ArrayList<Exception> errors;
	private ArrayList<TrainRouteCommentObj> comments;
	
	public trRouteModel(String name, ArrayList<trTrackModel> route, ArrayList<Exception> errors, ArrayList<TrainRouteCommentObj> comments){
		this.name = name;
		this.route = route;
		this.errors = errors;
		this.comments = comments;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<trTrackModel> getRoute(){
		return this.route;
	}
	
	public ArrayList<Exception> getErrors(){
		return this.errors;
	}
	
	public ArrayList<TrainRouteCommentObj> getComments(){
		return this.comments;
	}
}
