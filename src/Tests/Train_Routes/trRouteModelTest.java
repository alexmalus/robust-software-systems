package Tests.Train_Routes;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.Test;

import Train_Routes.trRouteModel;

import java.io.*;
import java.util.*;

public class trRouteModelTest {
	/*
	This is a test for trRouteModel.java
	*/
	@Test
	public void testtrRouteModel() {
		trRouteModel tr_routemodel = new trRouteModel("name", "route", "error", "comment");
		assertEquals("failure - strings are not equal", tr_routemodel.getName(), "name");
		assertEquals("failure - strings are not equal", tr_routemodel.getRoute(), "route");
		assertEquals("failure - strings are not equal", tr_routemodel.getErrors(), "error");
		assertEquals("failure - strings are not equal", tr_routemodel.getComments(), "comment");
	}
}