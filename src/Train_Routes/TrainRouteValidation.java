package Train_Routes;

import java.util.ArrayList;
import java.util.HashMap;

import Railway_Networks.Segment;

/**
 * 
 * @author Esbern Andersen-Hoppe s093484
 *
 */
public class TrainRouteValidation {

//	public boolean isRouteValid(trRouteModel route, HashMap<String, Segment> network){
//		for (trTrackModel track : route.getRoute()){
//			System.out.println("Id: " + track.getId() + " - Stop: " + track.getIsStop());
//		}
//		boolean trueTracks = tracksExistInNetwork(route, network);
//		boolean isLinked = isRouteLinked(route, network);
//		//boolean has2EndTracks = hasTwoEnds(route, network);
//		boolean noDuplicates = noDuplicateTracks(route);
//		
//		return trueTracks & isLinked & noDuplicates; //& has2EndTracks
//	}
	
	public boolean tracksExistInNetwork(trRouteModel route, HashMap<String, Segment> network) throws TrainRouteValidationException{
		/**
		 * here it needs to be verified that all the tracks in the route exist in the  network.
		 */
		boolean error = false;
		String trackIdListStr = "";
		boolean first = true;
		
		for (trTrackModel trtm : route.getRoute()){
			if (!network.containsKey(trtm.getId())){
				if (first == true){
					trackIdListStr += trtm.getId();
					first = false;
				}
				else{
					trackIdListStr += ", " + trtm.getId();
				}
				
				error = true;
			}
		}
		
		if (error == true){
			throw new TrainRouteValidationException("The following track id's does not exist in the network: " + trackIdListStr);
		}
		
		return true;
	}

	public boolean isRouteLinked(trRouteModel route, HashMap<String, Segment> network) throws TrainRouteValidationException{
		/**
		 * here it needs to be verified that the connections in the route exist in the network
		 * and that no jumps are performed.
		 * TODO: check that connections are correct (Especially with switch tracks).
		 */
		boolean error = false;
		boolean first = true;
		trTrackModel previousTRTM = null;
		String trackConnListStr = "";
		boolean firstError = true;
		
		for (trTrackModel currentTRTM : route.getRoute()){
			if (first == true){
				previousTRTM = currentTRTM;
				first = false;
			}
			else{
				Segment tmpSegment = network.get(previousTRTM.getId());
				
				if (tmpSegment.getConnections().indexOf(currentTRTM.getId()) < 0){
					if (firstError == true){
						trackConnListStr += previousTRTM.getId() + "-" + currentTRTM.getId();
					}
					else{
						trackConnListStr += ", " + previousTRTM.getId() + "-" + currentTRTM.getId();
					}
					
					error = true;
				}
				
				previousTRTM = currentTRTM;
			}
		}
		
		if (error == true){
			throw new TrainRouteValidationException("The following connections does not exist in the network: " + trackConnListStr);
		}
		
		return true;
	}
	
	public boolean hasTwoEndStops(trRouteModel route) throws TrainRouteValidationException{
		/**
		 * here it needs to be verified that the first and last track in the route are stops.
		 */		 
		 trTrackModel first = route.getRoute().get(0);
		 trTrackModel last = route.getRoute().get(route.getRoute().size() - 1);
		 
		 if (first.getIsStop() == false && last.getIsStop() == true){
			 throw new TrainRouteValidationException("The first track is not a stop which it should be.");
		 }
		 else if (first.getIsStop() == true && last.getIsStop() == false){
			 throw new TrainRouteValidationException("The last track is not a stop which it should be.");
		 }
		 else if (first.getIsStop() == false && last.getIsStop() == false){
			 throw new TrainRouteValidationException("The first and last tracks are not stops which they should be.");
		 }
		 else{
			 return true; 
		 }		
	}
	
	public boolean stopsAtStations(trRouteModel route, HashMap<String, Segment> network) throws TrainRouteValidationException{
		ArrayList<Character> stationIds = new ArrayList<Character>();
		
		for (String str : network.keySet()){
			if (network.get(str).getType().equals("STAT")){
				stationIds.add(str.charAt(0));
			}
		}
		
		boolean check = false;
		
		for (trTrackModel track : route.getRoute()){
			if (track.getIsStop() == true && stationIds.contains(track.getId())){
				check = true;
			}
		}
		
		if (check == false){
			throw new TrainRouteValidationException("The train route '" + route.getName() + "' does not stop at any stations.");
		}
		
		return true;
	}
	
	public boolean noDuplicateTracks(trRouteModel route) throws TrainRouteValidationException{
		/**
		 * Here it needs to be verified that there are no duplicate tracks.
		 */		
		ArrayList<Character> ids = new ArrayList<Character>();
		boolean error = false;
		String duplicateIdList = "";
		boolean first = true;
		
		for (trTrackModel track : route.getRoute()){
			if (!ids.contains(track.getId())){
				ids.add(track.getId());
			}
			else{
				if (first == true){
					duplicateIdList += track.getId();
				}
				else{
					duplicateIdList += ", " + track.getId();
				}
				
				error = true;
			}
		}
		
		if (error == true){
			throw new TrainRouteValidationException("The following tracks are used more than once in the route specification: " + duplicateIdList);
		}
		
		return true;
	}
	
	public boolean checkForCollisions(HashMap<String, trRouteModel> routeList, int avgTrackTravelTime, int avgStopTime) throws TrainRouteValidationException{
		HashMap<String, HashMap<Character, trTrackTimingModel>> routeListTimings = new HashMap<String, HashMap<Character, trTrackTimingModel>>();
		ArrayList<Character> trackIdList = new ArrayList<Character>();
		ArrayList<String> errorTexts = new ArrayList<String>();
		
		/**
		 * Calculate arrival and departure time for all tracks in the routes where all routes start at time 0.
		 */
		for (String routeName : routeList.keySet()){
			HashMap<Character, trTrackTimingModel> routeTimings = new HashMap<Character, trTrackTimingModel>();
			int time = 0;
			
			for (trTrackModel track : routeList.get(routeName).getRoute()){
				if (!trackIdList.contains(track.getId())){
					trackIdList.add(track.getId());
				}
				
				if (track.getIsStop() == true){
					routeTimings.put(track.getId(), new trTrackTimingModel(track.getId(), time, time + avgTrackTravelTime + avgStopTime, routeName));
					time = time + avgTrackTravelTime + avgStopTime;
				}
				else{
					routeTimings.put(track.getId(), new trTrackTimingModel(track.getId(), time, time + avgTrackTravelTime, routeName));
					time = time + avgTrackTravelTime;
				}
			}
			
			routeListTimings.put(routeName, routeTimings);
		}
		
		/**
		 * For each track, check if at any time there will be more than one train.
		 */
		for (Character trackId : trackIdList){
			ArrayList<trTrackTimingModel> trackTimings = new ArrayList<trTrackTimingModel>();
			
			for (String routeName : routeListTimings.keySet()){
				trackTimings.add(routeListTimings.get(routeName).get(trackId));
			}
			
			if (trackTimings.size() > 1){
				for (trTrackTimingModel trTTM : trackTimings){
					for (trTrackTimingModel trTTMtmp : trackTimings){
						if (!trTTM.getRouteName().equals(trTTMtmp.getRouteName())){
							if (trTTM.getArrivalTime() <= trTTMtmp.getArrivalTime() && trTTM.getDepartureTime() >= trTTMtmp.getArrivalTime()){
								errorTexts.add("The routes '" + trTTM.getRouteName() + "' and '" + trTTMtmp.getRouteName() + "' will collide at track '" + trTTM.getTrackId() + "' at some point between start time +" + trTTM.getArrivalTime() + " seconds and start time + " + trTTMtmp.getArrivalTime() + " seconds \n");
							}
							else if (trTTM.getArrivalTime() <= trTTMtmp.getDepartureTime() && trTTM.getDepartureTime() >= trTTMtmp.getDepartureTime()){
								errorTexts.add("The routes '" + trTTM.getRouteName() + "' and '" + trTTMtmp.getRouteName() + "' will collide at track '" + trTTM.getTrackId() + "' at some point between start time +" + trTTM.getArrivalTime() + " seconds and start time + " + trTTMtmp.getDepartureTime() + " seconds \n");
							}
							else if (trTTMtmp.getArrivalTime() <= trTTM.getArrivalTime() && trTTMtmp.getDepartureTime() >= trTTM.getArrivalTime()){
								errorTexts.add("The routes '" + trTTM.getRouteName() + "' and '" + trTTMtmp.getRouteName() + "' will collide at track '" + trTTM.getTrackId() + "' at some point between start time +" + trTTMtmp.getArrivalTime() + " seconds and start time + " + trTTM.getArrivalTime() + " seconds \n");
							}
							else if (trTTMtmp.getArrivalTime() <= trTTM.getDepartureTime() && trTTMtmp.getDepartureTime() >= trTTM.getDepartureTime()){
								errorTexts.add("The routes '" + trTTM.getRouteName() + "' and '" + trTTMtmp.getRouteName() + "' will collide at track '" + trTTM.getTrackId() + "' at some point between start time +" + trTTMtmp.getArrivalTime() + " seconds and start time + " + trTTM.getDepartureTime() + " seconds \n");
							}	
						}
					}
				}
			}
		}
		
		if (errorTexts.size() > 0){
			String totalErrorTxt = "";
			
			for (String errorTxt : errorTexts){
				totalErrorTxt += errorTxt;
			}
			
			throw new TrainRouteValidationException(totalErrorTxt);
		}
		
		return true;
	}
}
