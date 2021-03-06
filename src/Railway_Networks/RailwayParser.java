package Railway_Networks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @authors Martin Petersson s103619, Casper Nielsen s093866
 *
 */

public class RailwayParser {
	/*
	 * TODO validate input using regex a segment cannot be connected to itself
	 * e.g. CONN h h make comment about invalid lines, sukkah! Such as lines
	 * that are not comments. PT they are being ignored. check for duplicate
	 * stations and end points. Do we allow two isolated systems not connected
	 * to eachother? Shouldn't all stations connect to every other?
	 */

	public static int errorCounter = 0;

	private int lineNumber = 1;
    HashMap<String, Segment> segments = new HashMap<String, Segment>();
	ArrayList<String> errorList = new ArrayList<String>();

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
					continue; // skip to next iteration of while loop
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
						CheckEnd(words[1]);
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

		// Check semantic errors
//		for (String key : segments.keySet()) {
//			if (segments.get(key).getComments().size() > 0)
//				System.out.println(segments.get(key).getComments());
//		}
		if (segments.size() == 0) {
			printErrorMessage("File is empty or contains no valid lines");
		}

		printErrorList();

		System.out.println(errorCounter == 0 ? "No errors found"
				: errorCounter == 1 ? errorCounter + " error found"
						: errorCounter + " errors found.");
		return segments;
	}

	public void CheckStation(String[] word) {
		System.out.print("Found STATION  (" + word[1] + ") = " + word[2]);

		if (!word[1].matches("^[a-zA-Z0-9ÆæØøÅå]+$")) {
			printErrorMessage("Station name includes invalid characters");
			return;
		}

		if (word[2].length() > 1) {
			printErrorMessage("Station ID should only use one character");
			return;
		}
		if (!word[2].matches("^[a-zA-Z0-9]+$")) {
			printErrorMessage("Station ID uses an invalid character (letters or alphabetic characters)");
			return;
		}
		if(!segments.containsKey(word[2])){
			segments.put(word[2], new Segment(word[0]));
		}
		else
		{
			printErrorMessage("Station ID (" + word[2] + ") already exists");
			return;
		}
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

		if (!word[1].matches("^[a-zA-Z0-9]+$")) {
			printErrorMessage("First connection ID uses an invalid character (letters or alphabetic characters)");
			return;
		} else if (!word[2].matches("^[a-zA-Z0-9]+$")) {
			printErrorMessage("Second connection ID uses an invalid character (letters or alphabetic characters)");
			return;
		}

		if (segments.containsKey(word[1])) {
			
			//if not a station??
			if (!"STAT".equals(segments.get(word[1]).getType())
					&& segments.get(word[1]).getConnectionLength() > 2) {
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

	public void CheckEnd(String word) {
		// Check that all connections with only one connections
		// have an end-point - otherwise, throw a hissy fit! ... or an exception
		// as they are called.
		if (segments.containsKey(word)) {
			if (segments.get(word).getConnectionLength() > 1) {
				printErrorMessage("ERROR: contains too many connections already");
				return;
			} else if (segments.get(word).getConnections().contains("END")){
				printErrorMessage("ERROR: already contains an ending");
				return;				
			} else
				segments.get(word).addConnection("END");
			System.out.print("Found ENDING (added: " + word + ")");
		} else {
			printErrorMessage("Segment " + word + " does not exist.");
		}
	}

	public void printErrorMessage(String msg) {
		errorList.add(msg + " (at line: " + lineNumber + ")");
		errorCounter++;
	}

	public void printErrorList() {
		System.out.println("Syntax errors: ");
		for (String s : errorList) {
			System.out.println(s);
		}
	}
	
	int get_error_counter()
	{
		return errorCounter;
	}
	
	int get_line_number()
	{
		return lineNumber;
	}

//	public boolean checkIfConnectionExists() {
//		return true;
//	}

}
