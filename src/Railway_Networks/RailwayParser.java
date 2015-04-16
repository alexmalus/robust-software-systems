package Railway_Networks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;

public class RailwayParser {

	// HashMap stations = new HashMap<String, Segment>();
	// HashMap connections = new HashMap<String, String>();
	// HashMap endings = new HashMap<String, String>();

	private int lineNumber = 1;
	HashMap<String, Segment> segments = new HashMap<String, Segment>();

	// HashMap<String, Segment> segments = new HashMap<String, Segment>();
	// HashMap<String, Segment> segments = new HashMap<String, Segment>();

	// STATION CHECKLIST!
	// - Can either have two connections or one ending and one connection
	// - Needs an end
	//
	// CONNECTION CHECKLIST
	// - If no station has defined with ID
	//
	//
	//
	private int errorCounter = 0;
	public void Run(String filepath) {
		{
			try {
				File file = new File(filepath);

				if (!file.exists()) {
					System.err.println("ERROR: '" + filepath
							+ "' doesn't exist!");
					return;
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
					if (words.length == 3) {
						if ("STAT".equals(words[0])) {
							CheckStation(words);
						} else if ("CONN".equals(words[0])) {
							CheckConnection(words);
						}
					}

					else if (words.length == 2) {
						if ("END".equals(words[0])) {
							CheckEnd(words);
						}
					} else
						printErrorMessage("Invalid line!");

					++lineNumber;
					System.out.println();
				}
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			for (String key : segments.keySet()) {
				System.out.println(key + " "
						+ segments.get(key).getConnections());
			}
		}
		System.out.println(errorCounter == 0 ? "No errors found" : errorCounter == 1 ? errorCounter + " error found" : errorCounter + " errors found.");
	}

	public void CheckStation(String[] lineSplit) {
		// keep track of found stations so you're able to check
		// whether they have a both a connection and an end station
		System.out.print("Found STATION");

		// Verify string for valid characters (regex that
		// sucker!). Remember to include Danish characters
		System.out.print(" (" + lineSplit[1] + ")");

		// Verify both length (length == 1?) and that it is a valid character
		System.out.print(" = " + lineSplit[2]);
		segments.put(lineSplit[2], new Segment(lineSplit[0]));
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
			} else
				segments.get(word[1]).addConnection(word[2]);
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

		// if (segments.containsKey(lineSplit[1])) {
		// segments.get(lineSplit[1]).addConnection(lineSplit[2]);
		// System.out.print(" (added: " + lineSplit[2] + ")");
		// } else {
		// segments.put(lineSplit[1], new Segment(lineSplit[0]));
		// segments.get(lineSplit[1]).addConnection(lineSplit[2]);
		// }
	}

	public void CheckEnd(String[] word) {
		// Check that all connections with only one connections
		// have an end-point - otherwise, throw a hissy fit! ... or an exception
		// as they are called.
		if (segments.containsKey(word[1])) {
			if (segments.get(word[1]).getSize() > 1) {
				printErrorMessage("ERROR (" + lineNumber + ")");
				return;
			} else
				segments.get(word[1]).addConnection("END");
			System.out.print("Found ENDING (added: " + word[1] + ")");
		}
	}

	public void printErrorMessage(String msg){
		System.out.print(msg);
		errorCounter++;
	}
	
	public boolean checkIfConnectionExists() {

		return true;
	}

}
