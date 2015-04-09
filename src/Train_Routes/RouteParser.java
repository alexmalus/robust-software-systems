package Train_Routes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class RouteParser {
	
	public HashMap<String, ArrayList<trTrackModel>> readRouteSpecifications(String[] filePaths){
		int counter = 1;
		
		HashMap<String, ArrayList<trTrackModel>> routeList = new HashMap<String, ArrayList<trTrackModel>>();
		
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
			
			routeList.put(routeName, route);
			counter++;
		}
		
		return routeList;
	}
}
