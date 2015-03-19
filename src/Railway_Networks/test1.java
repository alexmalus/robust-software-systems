package Railway_Networks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class test1 {

	public void test() {

		HashMap stations = new HashMap<String, String[]>();
		HashMap connections = new HashMap<String, String>();
		HashMap endings = new HashMap<String, String>();

		try {
			File file = new File("train.txt");

			Scanner scan = new Scanner(file);

			while (scan.hasNextLine()) {
				String line = scan.nextLine();

				// Allows java-esque comments
				if (line.charAt(0) == '/' && line.charAt(1) == '/')
					continue;

				String[] lineSplit = line.split(" ");
				if (lineSplit.length == 3) {
					if (lineSplit[0].equals("STAT")) {

						// keep track of found stations so you're able to check
						// whether they have
						// a both a connection and an end station
						System.out.print("Found STATION");

						// Verify string for valid characters (regex that
						// sucker!)
						// Remember to include Danish characters
						System.out.print(" (" + lineSplit[1] + ")");

						// Verify both length (length = 1) and that it is a
						// valid character
						System.out.print(" = " + lineSplit[2]);
					} else if (lineSplit[0].equals("CONN")) {

						// Create list of connections. Cross reference with
						// stations
						// to see which are stations and which are simple
						// connections (two)
						// and which are three way connections
						System.out.print("Found CONNECTION");

						// Again, remember to verify the input - both characters
						// and length
						System.out.print(" (" + lineSplit[1] + ")");

						System.out.print(" (" + lineSplit[2] + ")");

					}
				}

				if (lineSplit.length == 2)
					if (lineSplit[0].equals("END")) {
						System.out.print("Found ENDING");
						// Check that all connections with only one connections
						// have an end-point - otherwise, throw a hissy fit!
					}

				System.out.println();

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}