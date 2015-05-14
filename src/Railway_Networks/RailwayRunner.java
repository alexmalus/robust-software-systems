package Railway_Networks;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @authors Martin Petersson s103619, Casper Nielsen s093866
 *
 */

public class RailwayRunner {

	public static void main(String[] args) {

//		new RailwayParser().Run("train.txt");
		
		Scanner s = new Scanner(System.in);
		for (int i = 0; i <= 10; i++) {
			System.out.println("########################################################################################\n");
			System.out.println("PROCESSING railway_"+i+".txt");
			HashMap<String, Segment> segments = new RailwayParser().Run("railway_"+i+".txt");
			segments = new RailwayValidation().RunInspection(segments);
			
			for (String key : segments.keySet()) {
				if (segments.get(key).getComments().size() > 0)
					System.out.println(segments.get(key).getComments());
			}
			System.out.println("Press ENTER");
			s.nextLine();
		}
		System.out.println("done.");
		s.close();
		
		
		
	}

}
