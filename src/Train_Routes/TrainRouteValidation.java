package Train_Routes;

import java.util.ArrayList;
import java.util.HashMap;

import Railway_Networks.Segment;

public class TrainRouteValidation {

	public boolean isRouteValid(trRouteModel route, HashMap<String, Segment> network){
		for (trTrackModel track : route.getRoute()){
			System.out.println("Id: " + track.getId() + " - Stop: " + track.getIsStop());
		}
		boolean trueTracks = tracksExistInNetwork(route, network);
		boolean isLinked = isRouteLinked(route, network);
		//boolean has2EndTracks = hasTwoEnds(route, network);
		boolean noDuplicates = noDuplicateTracks(route);
		
		return trueTracks & isLinked & noDuplicates; //& has2EndTracks
	}
	
	public boolean tracksExistInNetwork(trRouteModel route, HashMap<String, Segment> network){
		/**
		 * here it needs to be verified that all the tracks in the route exist in the  network.
		 */
		boolean check = true;
		
		for (trTrackModel trtm : route.getRoute()){
			if (!network.containsKey(trtm.getId())){
				check = false;
			}
		}
		
		return check;
	}

	public boolean isRouteLinked(trRouteModel route, HashMap<String, Segment> network){
		/**
		 * here it needs to be verified that the connections in the route exist in the network
		 * and that no jumps are performed.
		 */
		boolean check = true;
		boolean first = true;
		trTrackModel previousTRTM = null;
		
		for (trTrackModel currentTRTM : route.getRoute()){
			if (first == true){
				previousTRTM = currentTRTM;
				first = false;
			}
			else{
				Segment tmpSegment = network.get(previousTRTM.getId());
				
				if (tmpSegment.getConnections().indexOf(currentTRTM.getId()) < 0){
					check = false;
				}
				
				previousTRTM = currentTRTM;
			}
		}
		
		return check;
	}
	
	//public boolean hasTwoEnds(trRouteModel route, HashMap<String, Segment> network){
		/**
		 * here it needs to be verified that the first and last track in the route is also
		 * connected to another track or an end track. If it leads to another track it does
		 * not need to be part of the route.
		 */
	//	return true;
	//}
	
	public boolean noDuplicateTracks(trRouteModel route){
		/**
		 * Here it needs to be verified that there are no duplicate tracks.
		 */
		
		ArrayList<Character> ids = new ArrayList<Character>();
		
		for (trTrackModel track : route.getRoute()){
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
