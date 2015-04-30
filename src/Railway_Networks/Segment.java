package Railway_Networks;

import java.util.ArrayList;
import java.util.List;

public class Segment {

	private String type;
	private List<String> comments;
	private ArrayList<String> connections;
	
	public Segment(String type){
		this.type = type.toUpperCase();
		connections = new ArrayList<String>();
		comments = new ArrayList<String>();
	}

	public void addConnection(String connection)	{
		if(connections.size() < 3){
			connections.add(connection);
		} 
	}
	
	public String getConnections(){
		return connections.toString();
	}
	
	public int getConnectionLength(){
		return connections.size();
	}
	
	public int getSize(){
		return connections.size();
	}
	
	public String getType(){
		return type;
	}
	
	public void addComment(String comment){
		comments.add(comment);
	}
	
	public List<String> getComments(){
		return comments;
	}
}
