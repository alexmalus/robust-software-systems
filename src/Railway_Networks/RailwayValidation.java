package Railway_Networks;

import java.util.HashMap;

public class RailwayValidation {

	HashMap<String, Segment> segments;

	public RailwayValidation() {
	}

	public HashMap<String, Segment> RunInspection(HashMap<String, Segment> segments) {
		this.segments = segments;
		System.out.println("Semantic errors: ");
		for (String key : segments.keySet()) {
			switch (segments.get(key).getType()) {
			case "STAT":
				InspectStation(key);
				break;
			case "CONN":
				InspectConnection(key);
				break;
			default:
				segments.get(key).addComment("ERROR: " + key + " has invalid type");
				break;
			}
		}
		return segments;
	}

	public boolean InspectStation(String key) {
		// A segment cannot be connected to itself 
		if (segments.get(key).getConnections().contains(key)) {
			segments.get(key).addComment(
					"ERROR: " + key + " contains ID as connection");
			RailwayParser.errorCounter++;
		}
		if (segments.get(key).getConnectionLength() < 2) {
			segments.get(key).addComment(
					"ERROR: " + key + " has too few conncetions");
			RailwayParser.errorCounter++;
		}

		if (segments.get(key).getConnectionLength() > 2) {
			RailwayParser.errorCounter++;
			segments.get(key).addComment(
					"ERROR: " + key + " has too many conncetions");
		}
		return true;
	}

	public boolean InspectConnection(String key) {
		if (segments.get(key).getConnectionLength() < 2) {
			segments.get(key).addComment(
					"ERROR: " + key + " has too few conncetions");
			RailwayParser.errorCounter++;
		}
		if (segments.get(key).getConnectionLength() > 3) {
			segments.get(key).addComment(
					"ERROR: " + key + " has too many conncetions");
			RailwayParser.errorCounter++;
		}
		// Threeway split containing an end point
		if (segments.get(key).getConnectionLength() == 3
				&& segments.get(key).getConnections().toLowerCase()
						.contains("end")) {
			segments.get(key)
					.addComment(
							"ERROR: "
									+ key
									+ " is a threeway connection and cannot contain an end point");
			RailwayParser.errorCounter++;
		}
		return true;
	}

}
