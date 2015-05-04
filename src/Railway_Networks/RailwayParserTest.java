package Railway_Networks;

import static org.junit.Assert.*;

import org.junit.Test;

public class RailwayParserTest {

	@Test(expected= IndexOutOfBoundsException.class)
	public void testRun() {
		RailwayParser railway_parser = new RailwayParser();
		railway_parser.errorList.get(0);
		railway_parser.segments.get(1);
		
		//maybe check that once you define a station, one of the connections and end need to contain the starting letter
		//of the station, lowercased
		//does not seems feasible to look into
		//assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }), everyItem(containsString("n")))
		
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
