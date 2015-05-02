package Railway_Networks;
import java.util.Scanner;



public class RailwayRunner {

	public static void main(String[] args) {

//		new RailwayParser().Run("train.txt");
		
		Scanner s = new Scanner(System.in);
		for (int i = 0; i <= 10; i++) {
			System.out.println("########################################################################################\n");
			System.out.println("PROCESSING railway_"+i+".txt");
			new RailwayValidation(new RailwayParser().Run("railway_"+i+".txt"));
			System.out.println("Press ENTER");
			s.nextLine();
		}
		System.out.println("done.");
		s.close();
		
		
		
	}

}
