package Railway_Networks;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;

import org.junit.Test;

public class RailwayRunnerTest {

	@Test
	public void testMain() {
		for (int i = 0; i <= 10; i++) {
			File file = new File("railway_"+i+".txt");
			assertTrue(file.exists());
			
			HashMap<String, Segment> segments = new RailwayParser().Run("railway_"+i+".txt");
			segments = new RailwayValidation().RunInspection(segments);
			for (String key : segments.keySet()) {
				assertEquals("zero is ideal = no errors", segments.get(key).getComments().size(), 0);
			}
		}
	}

}
