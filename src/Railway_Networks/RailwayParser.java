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

	HashMap<String, Segment> segments = new HashMap<String, Segment>();
//	HashMap<String, Segment> segments = new HashMap<String, Segment>();
//	HashMap<String, Segment> segments = new HashMap<String, Segment>();

//	STATION CHECKLIST!
//	- Can either have two connections or one ending and one connection
//	- Needs an end 
//	
//	CONNECTION CHECKLIST
//	- If no station has defined with ID
//	
//	
//	
	
	public void Run(String filepath) {
		{

			try {
				File file = new File(filepath);

				if(!file.exists()){
					System.out.println("ERROR: '" + filepath + "' doesn't exist!");
					return;
				}
				
				Scanner scan = new Scanner(file);
				
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					
					// Removes unnecessary white spaces
					line = line.replaceAll("\\s+", " ");
//					String[] word = line.trim().split(" ");
					
					// Allow comments in text file
					if ("#".equals(line.substring(0,1))){					
						continue;
					}

					String[] lineSplit = line.split(" ");
					if (lineSplit.length == 3) {
						if (lineSplit[0].equals("STAT")) {
							CheckStation(lineSplit);
						} else if (lineSplit[0].equals("CONN")) {
							CheckConnection(lineSplit);
						}
					}

					else if (lineSplit.length == 2) {
						if (lineSplit[0].equals("END")) {
							CheckEnd(lineSplit);
						}
					} else
						System.out.print("Invalid line!");

					System.out.println();

				}
				scan.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			// Hold da kæft, så meget arbejde bare for at søge gennem et dictionary!?
			Iterator<?> it = segments.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				System.out.println(pair.getKey() + " " + segments.get(pair.getKey()).getConnections());
				it.remove();
			}
		}
		// System.out.println(connections);

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

	public void CheckConnection(String[] word) {

		// Create list of connections. Cross reference with stations
		// to see which are stations and which are simple connections (two)
		// and which are three way connections
		System.out.print("Found CONNECTION");

		// Again, remember to verify the input - both characters and length
		System.out.print(" (" + word[1] + ")");
		System.out.print(" (" + word[2] + ")");

		if(segments.containsKey(word[1])){
			segments.get(word[1]).addConnection(word[2]);
		} else {
			segments.put(word[1], new Segment(word[0]));
			segments.get(word[1]).addConnection(word[2]);
		}
		
		if(segments.containsKey(word[2])){
			segments.get(word[2]).addConnection(word[1]);
		} else {
			segments.put(word[2], new Segment(word[0]));
			segments.get(word[2]).addConnection(word[1]);
		}
				
//		if (segments.containsKey(lineSplit[1])) {
//			segments.get(lineSplit[1]).addConnection(lineSplit[2]);
//			System.out.print(" (added: " + lineSplit[2] + ")");
//		} else {
//			segments.put(lineSplit[1], new Segment(lineSplit[0]));
//			segments.get(lineSplit[1]).addConnection(lineSplit[2]);
//		}
	}

	public void CheckEnd(String[] lineSplit) {
		System.out.print("Found ENDING");
		// Check that all connections with only one connections
		// have an end-point - otherwise, throw a hissy fit!
		if (segments.containsKey(lineSplit[1])) {
			segments.get(lineSplit[1]).addConnection("END");
			System.out.print(" (added: " + lineSplit[1] + ")");
		}
	}
	
	public boolean checkIfConnectionExists() {
		
		return true;
	}

}
