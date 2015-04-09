package Train_Routes;

import java.util.ArrayList;

public class tmpTrainRouteTests {

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
		/**
		 * Here it needs to be verified that there are no duplicate tracks.
		 */
		
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
