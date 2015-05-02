package Railway_Networks;

import java.util.HashMap;

public class RailwayValidation {

	HashMap<String, Segment> segments;
	
	public RailwayValidation(HashMap<String, Segment> segments){
		this.segments = segments;
	}

	public void RunInspection() {
		System.out.println("Semantic errors: ");
		for (String key : segments.keySet()) {
			switch (segments.get(key).getType()) {
			case "STAT":
				InspectStation(key);
				break;
			case "CONN":
				InspectConnection(key);
				break;
			}
		}
	}

	public boolean InspectStation(String key) {
		if (segments.get(key).getConnectionLength() < 2) {
			segments.get(key).addComment("ERROR: " + key + " has too few conncetions");
//			errorCounter++;
		}

		if (segments.get(key).getConnectionLength() > 2) {
//			errorCounter++;
			segments.get(key).addComment("ERROR: " + key + " has too many conncetions");
		}
		// contains more than one end point
//		if(!segments.get(key).getConnections().toLowerCase().contains("end")){
//			
//		}
		return true;
	}

	public boolean InspectConnection(String key) {
		if (segments.get(key).getConnectionLength() < 2) {
			segments.get(key).addComment("ERROR: " + key + " has too few conncetions");
//			errorCounter++;
		}
		if (segments.get(key).getConnectionLength() > 3) {
			segments.get(key).addComment("ERROR: " + key + " has too many conncetions");
//			errorCounter++;
		}
		// Threeway split containing an end point
		if (segments.get(key).getConnectionLength() == 3 && segments.get(key).getConnections().toLowerCase().contains("end")) {
			segments.get(key).addComment("ERROR: " + key + " is a threeway connection and cannot contain an end point");
//			errorCounter++;
		}
		return true;
	}
	
}
