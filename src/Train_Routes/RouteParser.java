package Train_Routes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class RouteParser {

	private HashMap<Character, trTrackModel> route;
	
	public static void main(String args[]){
		RouteParser rp = new RouteParser();
		
		rp.run();
	}
	
	public void run(){
		System.out.println("START");
		route = new HashMap<Character, trTrackModel>();
		
		readRouteSpecifications();
	}
	
	public void readRouteSpecifications(){
		
		try{
			File f = new File("testRoute1.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			 
			String line = null;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				if (line.startsWith("ROUTE")){
					String[] parts = line.split(" ");//can this result in a null value in the array in any possible way? - Casper
					
					if (parts.length == 3){
						if (parts[1].length() == 1 && parts[2].length() == 1){
							char id1 = parts[1].toCharArray()[0];
							char id2 = parts[2].toCharArray()[0];
							
							if (Character.isLetterOrDigit(id1) && Character.isLetterOrDigit(id2)){
								trackSpecification(id1, id2);
							}
							else{
								System.out.println("ERROR: id1 and/or id2 is not a char - " + line);
							}
						}
						else{
							System.out.println("ERROR: id1 and/or id2 is not a char - " + line);
						}
					}
					else{
						System.out.println("ERROR: parts length track - " + line);
					}
				}
				else if (line.startsWith("STOP")){
					String[] parts = line.split(" ");
					
					if (parts.length == 2){
						if (parts[1].length() == 1){
							char id = parts[1].toCharArray()[0];
							
							if (Character.isLetterOrDigit(id)){
								stopSpecification(id);
							}
							else{
								System.out.println("ERROR: id is not a char - " + line);
							}
						}
						else{
							System.out.println("ERROR: id is not a char - " + line);
						}
					}
					else{
						System.out.println("ERROR: parts length stop - " + line);
					}
				}
				else if (line.startsWith("#")){
					System.out.println("COMMENT: " + line);
				}
				else{
					System.out.println("ERROR: incorrect line - " + line);
				}
			}
		 
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();;
		}
	}
	
	public void trackSpecification(char id1, char id2){
		if (!this.route.containsKey(id1)){
			this.route.put(id1, new trTrackModel(id1));
		}
		
		if (!this.route.containsKey(id2)){
			this.route.put(id2, new trTrackModel(id2));
		}
		
		this.route.get(id1).setRightConnection(this.route.get(id2));
		this.route.get(id2).setLeftConnection(this.route.get(id1));
	}
	
	public void stopSpecification(char id){
		if (this.route.containsKey(id)){
			this.route.get(id).setIsStop(true);
		}
		else{
			trTrackModel t = new trTrackModel(id);
			t.setIsStop(true);
			
			this.route.put(id, t);
		}
	}
	
	public void updateRoute(){
		
	}
}
