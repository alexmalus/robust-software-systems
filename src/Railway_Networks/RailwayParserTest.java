package Railway_Networks;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.util.HashMap;

import org.junit.Test;

public class RailwayParserTest {
	
	@Test
	//this test tries to verify if you have made use of a station correctly
	//for example if you're defining it and not using it
    public void empty() {
		RailwayParser railway_parser = new RailwayParser();
		for (int i = 0; i <= 9; i++) {
			HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
			//HashMap<String, Segment> segments = railway_parser.Run("railway_1.txt");
			for (String key : segments.keySet()) {
				segments.get(key).getConnectionsArray().get(1);
			}
		}
    }
	
	@Test
	//this test tries to verify whether a connection is linked to itself in a CONN
	//or if there are duplicates like CONN h b, CONN h b
    public void empty2() {
		RailwayParser railway_parser = new RailwayParser();
		//for (int i = 0; i <= 9; i++) {
			//HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
			HashMap<String, Segment> segments = railway_parser.Run("railway_1.txt");
			for (String key : segments.keySet()) {
				assertThat(segments.get(key).getConnectionsArray().get(0), 
					not(equalTo(segments.get(key).getConnectionsArray().get(1))));
			}
		//}
    }
    
	@Test(expected= IndexOutOfBoundsException.class) 
	//this test tries to verify whether a station is connected to too many other stations
    public void empty3() {
		RailwayParser railway_parser = new RailwayParser();
		for (int i = 0; i <= 9; i++) {
			//HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
			HashMap<String, Segment> segments = railway_parser.Run("railway_3.txt");
			for (String key : segments.keySet()) {
				if (segments.get(key).getType().equals("STAT") && (segments.get(key).getConnectionsArray().get(2) != null))
				{
					fail("Not supposed to have this many connections");
				}
			}
		}
    }
    
	//a station cannot be defined twice
	//same thing for an end station
	//we need to save the symbol of the station when we CheckStation
	//so I can check it here if they exist with different station names
	//currently if we do STAT Hellerup h, STAT Lyngby h, we will have one station.
	@Test
	public void empty4() {
		RailwayParser railway_parser = new RailwayParser();
    }
	
	@Test
	//this test tries to verify the Segment type is not of STAT or CONN
    public void empty5() {
		RailwayParser railway_parser = new RailwayParser();
		for (int i = 0; i <= 9; i++) {
			//HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
			HashMap<String, Segment> segments = railway_parser.Run("railway_3.txt");
			for (String key : segments.keySet()) {
				if (!(segments.get(key).getType().equals("STAT") ||
					segments.get(key).getType().equals("CONN")))
				{
					fail("Not supposed to have this type");
				}
			}
		}
    }
	
	@Test
	//this test tries to verify whether a connection is linked to itself in a CONN
    public void empty6() {
		RailwayParser railway_parser = new RailwayParser();
		//for (int i = 0; i <= 9; i++) {
			//HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
			HashMap<String, Segment> segments = railway_parser.Run("railway_6.txt");
			for (String key : segments.keySet()) {
				assertThat(segments.get(key).getConnectionsArray().get(0), 
					not(equalTo(segments.get(key).getConnectionsArray().get(1))));
			}
		//}
    }

	@Test
	public void testPrintErrorList() {
		RailwayParser railway_parser = new RailwayParser();
		assertEquals("should be zero", railway_parser.errorList.size(), 0);
		assertEquals("should be zero", railway_parser.get_error_counter(), 0);
		assertEquals("should be one", railway_parser.get_line_number(), 1);
		railway_parser.printErrorMessage("error message test");
		assertEquals("should be one", railway_parser.errorList.size(), 1);
		assertEquals("should be one", railway_parser.get_error_counter(), 1);
	}

}
