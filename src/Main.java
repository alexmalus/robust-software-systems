import java.util.Scanner;

import Railway_Networks.RailwayParser;



public class Main {

	public static void main(String[] args) {

		new RailwayParser().Run("train.txt");
		
//		Scanner s = new Scanner(System.in);
//		for (int i = 0; i <= 10; i++) {
//			System.out.println("########################################################################################\n");
//			System.out.println("PROCESSING railway_"+i+".txt");
//			new RailwayParser().Run("railway_"+i+".txt");
//			s.nextLine();
//		}
//		s.close();
		
		//rp.Run("test_cases\\railway\\railway_1.txt");
	}

}
