package Railway_Networks;

import static org.junit.Assert.*;

import org.junit.Test;

public class StationTest {

	@Test
	public void testStations() {
		Station station = new Station("test_name", "ID");
		assertEquals("failure - strings are not equal", station.get_name(), "test_name");
		assertEquals("failure - strings are not equal", station.get_ID(), "ID");
	}

	@Test
	public void testAddEnd() {
		Station station = new Station("test_name", "ID");
		assertTrue("failure - should be true", station.addEnd("this_is_the_end"));
		assertEquals("failure - strings are not equal", station.get_end(), "this_is_the_end");
	}

}
