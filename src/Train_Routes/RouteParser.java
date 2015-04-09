package Train_Routes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouteParser {

	//private HashMap<Character, trTrackModel> route;
	private HashMap<String, ArrayList<trTrackModel>> routeList;
	public static void main(String args[]){
		RouteParser rp = new RouteParser();
		
		rp.run();
	}
	
	public void run(){
		System.out.println("START");
		//route = new HashMap<Character, trTrackModel>();
		routeList = new HashMap<String, ArrayList<trTrackModel>>();
		
		String[] paths = {"testRoute1.txt"};
		readRouteSpecifications(paths);
		
		for(String routeName : this.routeList.keySet()){
			isRouteValid(routeList.get(routeName));
		}
	}
	
	public void readRouteSpecifications(String[] filePaths){
		int counter = 1;
		
		for(String fPath : filePaths){
			String routeName = "NoName_" + counter;
			
			ArrayList<trTrackModel> route = new ArrayList<trTrackModel>();
			
			try{
				File f = new File(fPath);
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				 
				String line = null;
				while ((line = br.readLine()) != null){
					if (line.startsWith("NAME")){
						String[] nameParts = line.split(" -- ");
						
						if (nameParts.length == 2){
							if (nameParts[1].length() > 0){
								routeName = nameParts[1];
							}
							else{
								System.out.println("ERROR: A line that starts with 'NAME' does not contain a name.");
								System.out.println("\t" + line);
							}
						}
						else{
							System.out.println("ERROR: A line that starts with 'NAME' does not follow the specification rules.");
							System.out.println("\t" + line);
						}
					}
					else if (line.startsWith("TRACK")){
						String[] trackParts = line.split(" ");
						
						if (trackParts.length == 3){							
							if (trackParts[1].length() == 1 && (trackParts[2].equals("TRUE") || trackParts[2].equals("FALSE"))){
								char id = trackParts[1].charAt(0);
								
								if (Character.isLetterOrDigit(id)){
									if (trackParts[2].equals("TRUE")){
										route.add(new trTrackModel(id, true));
									}
									else{
										route.add(new trTrackModel(id, false));
									}
								}
								else{
									System.out.println("ERROR: A line that starts with 'TRACK' does not have a single character id which is a letter or digit.");
									System.out.println("\t" + line);
								}
								
							}
							else{
								System.out.println("ERROR: A line that starts with 'TRACK' does not have a single character id or a correct boolean value (TRUE/FALSE).");
								System.out.println("\t" + line);
							}
						}
						else{
							System.out.println("ERROR: A line that starts with 'TRACK' does not follow the specification rules.");
							System.out.println("\t" + line);
						}
					}
					else if (line.startsWith("#")){
						System.out.println("COMMENT: " + line);
					}
					else{
						System.out.println("ERROR: A in the file does not follow the specification rules.");
						System.out.println("\t" + line);
					}
				}
			 
				br.close();
				fr.close();
			}
			catch(Exception e){
				e.printStackTrace();;
			}	
			
			this.routeList.put(routeName, route);
			counter++;
		}

	}
	
	public boolean isRouteValid(ArrayList<trTrackModel> route){
		for (trTrackModel track : route){
			System.out.println("Id: " + track.getId() + " - Stop: " + track.getIsStop());
		}
		boolean trueTracks = tracksExistInNetwork(route);
		boolean isLinked = isRouteLinked(route);
		boolean has2EndTracks = hasTwoEnds(route);
		boolean noDuplicates = noDuplicateTracks(route);
		
		return trueTracks & isLinked & has2EndTracks & noDuplicates;
	}
	
	public boolean tracksExistInNetwork(ArrayList<trTrackModel> route){
		/**
		 * here it needs to be verified that all the tracks in the route exist in the  network.
		 */
		return true;
	}

	public boolean isRouteLinked(ArrayList<trTrackModel> route){
		/**
		 * here it needs to be verified that the connection in the route exist in the network
		 * and that no jumps are performed.
		 */
		return true;
	}
	
	public boolean hasTwoEnds(ArrayList<trTrackModel> route){
		/**
		 * here it needs to be verified that the first and last track in the route is also
		 * connected to another track or an end track. If it leads to another track it does
		 * not need to be part of the route.
		 */
		return true;
	}
	
	public boolean noDuplicateTracks(ArrayList<trTrackModel> route){
		
		ArrayList<Character> ids = new ArrayList<Character>();
		
		for (trTrackModel track : route){
			if (ids.contains(track.getId())){
				return false;
			}
			else{
				ids.add(track.getId());
			}
		}
		return true;
	}
}
