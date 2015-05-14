package Tests.Train_Routes;
import static org.junit.Assert.*;
import org.hamcrest.*;
import org.junit.Test;
import java.io.*;
import java.util.*;

public class TrainRouteRunnerTest {
	/*
	This is a test for TrainRouteRunner.java
	We verify that the files exist
	*/
	@Test
	public void testTrainRouteRunner() {
		for (int i = 1; i <= 3; i++) {
			File file = new File("testRoute"+i+".txt");
			assertTrue(file.exists());
		}
	}
}

