package Railway_Networks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RailwayParser {

	public void validate(String filePath) {

		HashMap stations = new HashMap<String, String[]>();
		HashMap<String, ArrayList<String>> connections = new HashMap<String, ArrayList<String>>();
		HashMap endings = new HashMap<String, String>();

		//endings.put("Hellerup", "h");
		for(String s : connections.get("moe"))
			System.out.println(s);
		try {
			File file = new File(filePath);

			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				String line = scan.nextLine();

				// Lines beginning with # are comments
				if ("#".equals(line.substring(0,1))){					
					continue;
				}

				//maybe that is dangerous because it could read two unintentional whitespaces...
				//trim alone is not enough use regex
				String[] word = line.trim().split(" ");
				if (word.length == 3) {
					if (word[0].equals("STAT")) {

						// keep track of found stations so you're able to check
						// whether they have
						// both a connection and an end station
						System.out.print("Found STATION");

						// Verify string for valid characters (regex that
						// sucker!)
						// Remember to include Danish characters
						System.out.print(" (" + word[1] + ")");

						// Verify both length (length = 1) and that it is a
						// valid character
						System.out.print(" = " + word[2]);
					} else if (word[0].equals("CONN")) {

						// Create list of connections. Cross reference with
						// stations
						// to see which are stations and which are simple
						// connections (two)
						// and which are three way connections
						System.out.print("Found CONNECTION");

						// Again, remember to verify the input - both characters
						// and length
						System.out.print(" (" + word[1] + ")");

						System.out.print(" (" + word[2] + ")");

					}
				}

				if (word.length == 2){
					if (word[0].equals("END")) {
						System.out.print("Found ENDING");
						// Check that all connections with only one connections
						// have an end-point - otherwise, throw a hissy fit!
					}
				}
				System.out.println();
				
			}
			//robustness!
			scan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
		
	}

}