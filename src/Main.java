import java.util.Scanner;

import Railway_Networks.RailwayParser;



public class Main {

	public static void main(String[] args) {

		new RailwayParser().Run("train.txt");
		
<<<<<<< HEAD
		Scanner s = new Scanner(System.in);
		for (int i = 0; i <= 10; i++) {
			System.out.println("########################################################################################\n");
			System.out.println("PROCESSING railway_"+i+".txt");
			new RailwayParser().Run("railway_"+i+".txt");
			System.out.println("Press ENTER");
			s.nextLine();
		}
		System.out.println("done.");
		s.close();
=======
//		Scanner s = new Scanner(System.in);
//		for (int i = 0; i <= 10; i++) {
//			System.out.println("########################################################################################\n");
//			System.out.println("PROCESSING railway_"+i+".txt");
//			new RailwayParser().Run("railway_"+i+".txt");
//			s.nextLine();
//		}
//		s.close();
>>>>>>> 6055e06b4e8731f27538bbeffaf99b07cde4c7f4
		
		//rp.Run("test_cases\\railway\\railway_1.txt");
	}

}
