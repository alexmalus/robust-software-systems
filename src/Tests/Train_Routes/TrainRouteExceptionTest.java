package Tests.Train_Routes;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.Test;

import Train_Routes.TrainRouteException;

import java.io.*;
import java.util.*;

public class TrainRouteExceptionTest {
	/*
	This is a test for TrainRouteException.java
	*/
	@Test
	public void testTrainRouteException() {
		TrainRouteException trainroute_exception = new TrainRouteException("file_name", "line_text", 3, "error");
		assertEquals("failure - strings are not equal", trainroute_exception.getErrorText(), "error");
		assertEquals("failure - strings are not equal", trainroute_exception.getFilePath(), "file_name");
		assertEquals("failure - strings are not equal", trainroute_exception.getLineText(), "line_text");
		assertEquals("failure - numbers are not equal", trainroute_exception.getLineNumber(), 3);
	}
}