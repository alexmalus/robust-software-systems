package Train_Routes;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainRouteRunner {
	
	public static void main(String args[]){
		System.out.println("START");
		RouteParser rp = new RouteParser();
		
		String[] paths = {"testRoute1.txt"};
		HashMap<String, ArrayList<trTrackModel>> routeList = rp.readRouteSpecifications(paths);
		
		tmpTrainRouteTests tmpTests = new tmpTrainRouteTests();
		
		for(String routeName : routeList.keySet()){
			tmpTests.isRouteValid(routeList.get(routeName));
		}
	}
}
