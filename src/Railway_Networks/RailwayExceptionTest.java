package Railway_Networks;

import static org.junit.Assert.*;

import org.junit.Test;

import Railway_Networks.*;

public class RailwayExceptionTest {

	@Test
	public void testRailwayException() {
		RailwayException railway_exception = new RailwayException("file_name", "line_text", 3, "error");
		assertEquals("failure - strings are not equal", railway_exception.errorMessage, "error");
		assertEquals("failure - strings are not equal", railway_exception.fileName, "file_name");
		assertEquals("failure - strings are not equal", railway_exception.lineText, "line_text");
		assertEquals("failure - numbers are not equal", railway_exception.lineNumber, 3);
	}

}
