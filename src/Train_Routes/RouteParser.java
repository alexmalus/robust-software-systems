package Train_Routes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Esbern Andersen-Hoppe s093484
 *
 */
public class RouteParser {
	
	public HashMap<String, trRouteModel> readRouteSpecifications(String[] filePaths){
		int counter = 1;
		
		HashMap<String, trRouteModel> routeList = new HashMap<String, trRouteModel>();
		
		for(String fPath : filePaths){
			String routeName = "NoName_" + counter;
			
			ArrayList<trTrackModel> route = new ArrayList<trTrackModel>();
			ArrayList<Exception> errors = new ArrayList<Exception>();
			ArrayList<trCommentModel> comments = new ArrayList<trCommentModel>();
			
			try{
				File f = new File(fPath);
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				 
				int lineNr = 1;
				String line = null;
				while ((line = br.readLine()) != null){
					try{
						if (line.startsWith("NAME")){
							String[] nameParts = line.split(" -- ");
							
							if (nameParts.length == 2){
								if (nameParts[1].length() > 0){
									routeName = nameParts[1];
								}
								else{
									throw new TrainRouteException(fPath, line, lineNr, "The line starts with 'NAME' so a name for the route is expected in the route but no name was found.");
//									System.out.println("ERROR: A line that starts with 'NAME' does not contain a name.");
//									System.out.println("\t" + line);
								}
							}
							else{
								throw new TrainRouteException(fPath, line, lineNr, "The line starts with 'NAME' but it does not follow the specification rules for route name.");
//								System.out.println("ERROR: A line that starts with 'NAME' does not follow the specification rules.");
//								System.out.println("\t" + line);
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
										throw new TrainRouteException(fPath, line, lineNr, "The line starts with 'TRACK' and a single character id (Letter or Digit) is expected but not found.");
//										System.out.println("ERROR: A line that starts with 'TRACK' does not have a single character id which is a letter or digit.");
//										System.out.println("\t" + line);
									}
									
								}
								else{
									throw new TrainRouteException(fPath, line, lineNr, "The line starts with 'TRACK' but it does not follow the specification rules for a route track.");
//									System.out.println("ERROR: A line that starts with 'TRACK' does not have a single character id or a correct boolean value (TRUE/FALSE).");
//									System.out.println("\t" + line);
								}
							}
							else{
								throw new TrainRouteException(fPath, line, lineNr, "The line starts with 'TRACK' but it does not follow the specification rules for a route track.");
//								System.out.println("ERROR: A line that starts with 'TRACK' does not follow the specification rules.");
//								System.out.println("\t" + line);
							}
						}
						else if (line.startsWith("#")){
							comments.add(new trCommentModel(fPath, lineNr, line));
//							System.out.println("COMMENT: " + line);
						}
						else{
							throw new TrainRouteException(fPath, line, lineNr, "The line does not follow any of the specification rules defined for a train route file.");
//							System.out.println("ERROR: A in the file does not follow the specification rules.");
//							System.out.println("\t" + line);
						}
					}
					catch(TrainRouteException e){
						errors.add(e);
					}
					catch(Exception e){
						errors.add(e);
					}
					
					lineNr++;
				}
			 
				br.close();
				fr.close();
			}
			catch(Exception e){
				errors.add(e);
			}	
			
			routeList.put(routeName, new trRouteModel(routeName, route, errors, comments));
			counter++;
		}
		
		return routeList;
	}
}
