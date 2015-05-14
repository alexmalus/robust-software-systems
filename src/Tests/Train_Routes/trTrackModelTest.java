package Tests.Train_Routes;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.Test;

import Train_Routes.trTrackModel;

import java.io.*;
import java.util.*;

public class trTrackModelTest {
	/*
	This is a test for trTrackModel.java
	*/
	@Test
	public void testtrTrackModel() {
		trTrackModel tr_trackmodel = new trTrackModel("id", "is_stop");
		assertEquals("failure - strings are not equal", tr_trackmodel.getId(), "id");
		assertEquals("failure - strings are not equal", tr_trackmodel.getIsStop(), "is_stop");
	}
}