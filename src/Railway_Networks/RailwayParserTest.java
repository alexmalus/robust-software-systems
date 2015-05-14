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
		for (int i = 0; i <= 9; i++) {
			HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
//			HashMap<String, Segment> segments = railway_parser.Run("railway_1.txt");
			for (String key : segments.keySet()) {
				assertThat(segments.get(key).getConnectionsArray().get(0), 
					not(equalTo(segments.get(key).getConnectionsArray().get(1))));
			}
		}
    }
    
	@Test(expected= IndexOutOfBoundsException.class) 
	//this test tries to verify whether a station is connected to too many other stations
    public void empty3() {
		RailwayParser railway_parser = new RailwayParser();
		for (int i = 0; i <= 9; i++) {
			HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
//			HashMap<String, Segment> segments = railway_parser.Run("railway_3.txt");
			for (String key : segments.keySet()) {
				if (segments.get(key).getType().equals("STAT") && (segments.get(key).getConnectionsArray().get(2) != null))
				{
					fail("Not supposed to have this many connections");
				}
				else if(segments.get(key).getType().equals("CONN") && (segments.get(key).getConnectionsArray().get(3) != null))
				{
					fail("Not supposed to have this many connections");
				}
			}
		}
    }
    
	@Test
	//this test tries to verify the Segment type is not of STAT or CONN
    public void empty4() {
		RailwayParser railway_parser = new RailwayParser();
		for (int i = 0; i <= 9; i++) {
			HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
//			HashMap<String, Segment> segments = railway_parser.Run("railway_3.txt");
			for (String key : segments.keySet()) {
				if (!(segments.get(key).getType().equals("STAT") ||
					segments.get(key).getType().equals("CONN")))
				{
					fail("Not supposed to have this type");
				}
			}
		}
    }
		
	//a station cannot be defined twice; same thing for an end station
	@Test
	public void empty5() {
		int station_counter = 0;
		RailwayParser railway_parser = new RailwayParser();
		for (int i = 0; i <= 9; i++) {
			station_counter = 0;
			HashMap<String, Segment> segments = railway_parser.Run("railway_"+i+".txt");
	//		HashMap<String, Segment> segments = railway_parser.Run("railway_1.txt");
			String[] test_word = {"STAT", "Hellerup", "h"};
			
			for (String key : segments.keySet()) {
				System.out.println(key);
				if (key.equals(test_word[2]))
				{
					station_counter ++;
				}
			}
			assertEquals("counter should be 1", station_counter, 1);
		}
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
