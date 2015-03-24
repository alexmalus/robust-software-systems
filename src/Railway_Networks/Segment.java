package Railway_Networks;

import java.util.ArrayList;

public class Segment {

	private String type;
	private ArrayList<String> connections;
	
	public Segment(String type){
		this.type = type;
		connections = new ArrayList<String>();
	}

	public void addConnection(String connection)	{
		if(connections.size() < 3){
			connections.add(connection);
		} 
	}
	
	public String getConnections(){
		return connections.toString();
	}
	
	public int getSize(){
		return connections.size();
	}
	
	public String getType(){
		return type;
	}
	
}
