package Train_Routes;

import java.util.ArrayList;

/**
 * 
 * @author Esbern Andersen-Hoppe s093484
 *
 */
public class trRouteModel {

	private String name;
	private ArrayList<trTrackModel> route;
	private ArrayList<Exception> errors;
	private ArrayList<trCommentModel> comments;
	
	public trRouteModel(String name, ArrayList<trTrackModel> route, ArrayList<Exception> errors, ArrayList<trCommentModel> comments){
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
	
	public ArrayList<trCommentModel> getComments(){
		return this.comments;
	}
}
