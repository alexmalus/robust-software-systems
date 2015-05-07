package Railway_Networks;

import static org.junit.Assert.*;
import java.io.File;

import org.junit.Test;

public class RailwayRunnerTest {

	@Test
	public void testMain() {
		for (int i = 0; i <= 10; i++) {
			File file = new File("railway_"+i+".txt");
			assertTrue(file.exists());
		}
	}

}
