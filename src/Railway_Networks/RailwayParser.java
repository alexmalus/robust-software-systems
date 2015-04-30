package Railway_Networks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RailwayParser {
	/*
	 * TODO validate input using regex a segment cannot be connected to itself
	 * e.g. CONN h h make comment about invalid lines, sukkah! Such as lines
	 * that are not comments. PT they are being ignored. check for duplicate
	 * stations and end points. Do we allow two isolated systems not connected
	 * to eachother? Shouldn't all stations connect to every other?
	 */

	private int lineNumber = 1;
	HashMap<String, Segment> segments = new HashMap<String, Segment>();
	ArrayList<String> errorList = new ArrayList<String>();

	// STATION CHECKLIST!
	// - Can either have two connections or one ending and one connection
	// - Needs an end
	// - Only one end per
	// CONNECTION CHECKLIST
	// - If no station has been defined with an ID

	private int errorCounter = 0;

	public HashMap<String, Segment> Run(String filepath) {
		try {
			File file = new File(filepath);

			if (!file.exists()) {
				System.err.println("ERROR: '" + filepath + "' doesn't exist!");
				return null;
			}

			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {

				String line = scan.nextLine();

				// Removes unnecessary white spaces and leading and trailing
				// spaces.
				line = line.replaceAll("\\s+", " ").trim();

				// Allow comments in text file
				if ("#".equals(line.substring(0, 1))) {
					// line is a comment.
					++lineNumber;
					continue; // skip to next iteration
				}

				String[] words = line.split(" ");

				if (line.startsWith("STAT")) {
					if (words.length == 3) {
						CheckStation(words);
					} else if (words.length > 3) {
						printErrorMessage("Line has too many tokens. Stations requires three tokens (STAT, station_name, ID)");
					} else if (words.length < 3) {
						printErrorMessage("Line has too few tokens. Stations requires three tokens (STAT, station_name, ID)");
					}
				} else if (line.startsWith("CONN")) {
					if (words.length == 3) {
						CheckConnection(words);
					} else if (words.length > 3) {
						printErrorMessage("Line has too many tokens. Connections requires three tokens (CONN, station_name, ID)");
					} else if (words.length < 3) {
						printErrorMessage("Line has too few tokens. Connections requires three tokens (CONN, station_name, ID)");
					}

				} else if (line.startsWith("END")) {
					if (words.length == 2) {
						CheckEnd(words);
					} else if (words.length > 2) {
						printErrorMessage("Line has too many tokens. Endings requires two tokens (CONN, ID)");
					} else if (words.length < 2) {
						printErrorMessage("Line has too few tokens. Endings requires two tokens (END, ID)");
					}
				} else {
					printErrorMessage("Invalid line!");
				}

				++lineNumber;
				System.out.println();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (String key : segments.keySet()) {
			System.out.println(key + " " + segments.get(key).getConnections());
		}

		Inspector();

		for (String key : segments.keySet()) {
			if (segments.get(key).getComments().size() > 0)
				System.out.println(segments.get(key).getComments());
		}
		if (segments.size() == 0) {
			errorCounter++;
			System.out.println("File is empty or contains no valid lines");
		}

		printErrorList();

		System.out.println(errorCounter != 0 ? "No errors found"
				: errorCounter == 1 ? errorCounter + " error found"
						: errorCounter + " errors found.");
		return segments;
	}

	public void CheckStation(String[] word) {
		System.out.print("Found STATION  (" + word[1] + ") = " + word[2]);

		if (!word[1].matches("^[a-zA-Z0-9ÆæØøÅå]+$")) {
			printErrorMessage("Station name includes invalid characters");
		}

		// Verify both length (length == 1?) and that it is a valid character
		if (word[2].length() > 1) {
			printErrorMessage("Station ID should only use one character");
		}
		segments.put(word[2], new Segment(word[0]));
	}

	// helper functions
	public void CheckConnection(String[] word) {

		// Create list of connections. Cross reference with stations
		// to see which are stations and which are simple connections (two)
		// and which are three way connections
		System.out.print("Found CONNECTION");

		// Again, remember to verify the input - both characters and length
		System.out.print(" (" + word[1] + ")");
		System.out.print(" (" + word[2] + ")");

		if (segments.containsKey(word[1])) {
			if (!"STAT".equals(segments.get(word[1]).getType())
					&& segments.get(word[1]).getSize() > 2) {
				printErrorMessage("ERROR: too many connections for this station!");
			} else {
				segments.get(word[1]).addConnection(word[2]);
			}
		} else {
			segments.put(word[1], new Segment(word[0]));
			segments.get(word[1]).addConnection(word[2]);
		}

		if (segments.containsKey(word[2])) {
			segments.get(word[2]).addConnection(word[1]);
		} else {
			segments.put(word[2], new Segment(word[0]));
			segments.get(word[2]).addConnection(word[1]);
		}
	}

	// Just pass word[1] instead of entire array.
	public void CheckEnd(String[] word) {
		// Check that all connections with only one connections
		// have an end-point - otherwise, throw a hissy fit! ... or an exception
		// as they are called.
		if (segments.containsKey(word[1])) {
			if (segments.get(word[1]).getSize() > 1) {
				// throw new RailwayException("","","","");
				printErrorMessage("ERROR: A segment can only contain one end point ");
				return;
			} else
				segments.get(word[1]).addConnection("END");
			System.out.print("Found ENDING (added: " + word[1] + ")");
		} else {
			System.out.println("Segment " + word[1] + " does not exist.");
			errorCounter++;
		}
	}

	public void printErrorMessage(String msg) {
		errorList.add(msg + " (at line: " + lineNumber + ")");
		// System.out.print(msg + " (at line: " +lineNumber + ")");
		errorCounter++;
	}

	public void printErrorList() {
		System.out.println("Syntax errors: ");
		for (String s : errorList) {
			System.out.println(s);
		}
	}

//	public boolean checkIfConnectionExists() {
//		return true;
//	}

	public void Inspector() {
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
			errorCounter++;
		}

		if (segments.get(key).getConnectionLength() > 2) {
			errorCounter++;
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
			errorCounter++;
		}
		if (segments.get(key).getConnectionLength() > 3) {
			segments.get(key).addComment("ERROR: " + key + " has too many conncetions");
			errorCounter++;
		}
		// Threeway split containing an end point
		if (segments.get(key).getConnectionLength() == 3 && segments.get(key).getConnections().toLowerCase().contains("end")) {
			segments.get(key).addComment("ERROR: " + key + " is a threeway connection and cannot contain an end point");
			errorCounter++;
		}
		return true;
	}
}
