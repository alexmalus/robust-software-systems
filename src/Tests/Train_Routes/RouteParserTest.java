package Tests.Train_Routes;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.Test;

import Train_Routes.RouteParser;

import java.io.*;
import java.util.*;

public class RouteParserTest {
	/*
	This is a test for RouteParser.java
	*/
	@Test
	public void testRouteParser() {
		RouteParser route_parser = new RouteParser("file_name", "line_text", 3, "error");
		assertEquals("failure - strings are not equal", trainroute_exception.errorText, "error");
		assertEquals("failure - strings are not equal", trainroute_exception.filePath, "file_name");
		assertEquals("failure - strings are not equal", trainroute_exception.lineText, "line_text");
		assertEquals("failure - numbers are not equal", trainroute_exception.lineNumber, 3);
	}
}