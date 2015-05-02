package Railway_Networks;

public class Station {

	private boolean hasEnding;
	private String name;
	private String ID;
	private String end;
	
	public Station(String name, String ID){
		this.name = name;
		this.ID = ID;
	}
	
	public boolean addEnd(String end){
		if(!hasEnding) {
			this.end = end;
			return true;
		}
		return false;
	}
}
