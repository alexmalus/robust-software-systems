package Train_Routes;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainRouteRunner {
	
	public static void main(String args[]){
		System.out.println("START");
		RouteParser rp = new RouteParser();
		
		String[] paths = {"testRoute1.txt", "testRoute2.txt", "testRoute3.txt"};
		HashMap<String, trRouteModel> routeList = rp.readRouteSpecifications(paths);
		
		String test = "hej";
		TrainRouteValidation tmpTests = new TrainRouteValidation();
		
		for(String routeName : routeList.keySet()){
			//tmpTests.isRouteValid(routeList.get(routeName));
		}
	}
}
