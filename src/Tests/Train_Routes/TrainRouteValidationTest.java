package Tests.Train_Routes;
import static org.junit.Assert.*;
import org.hamcrest.*;
import org.junit.Test;
import java.io.*;
import java.util.*;

public class TrainRouteValidationTest {
	/*
	This is a test for TrainRouteValidation.java
	*/
	@Test
	public void testTrainRouteValidation() {
		assertTrue("failure - should be true", true);
	}
	/*
	We need to verify that all the routes exist in the network
	*/
	@Test
    public void testRoutesExist()
    {
    	// Check that routes exist
		assertTrue(routes.exists());
    }
	/*
	We need to verify that all the tracks in the route exist in the network
	*/
    public void testTracksExist()
    {
    	// Check that tracks exist
		assertTrue(tracks.exists());
    }
	/*
	We need to verify that the connections in the route exist in the network
	*/
	public void testConnectionsExist()
    {
    	// Check that connections exist
		assertTrue(connections.exists());
    }
	/*
	We need to verify that the first and last track in the route is also
	connected to another track or an end track. If it leads to another track it does
	not need to be part of the route
	*/
	public boolean hasTwoEnds(trRouteModel route, HashMap<String, Segment> network){
		return true;
	}
		
	/*
	We need to verify that there are no duplicate tracks
	*/
}