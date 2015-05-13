package Railway_Networks;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.Test;

public class SegmentTest {

	@Test
	public void testSegment() {
		Segment segment = new Segment("segment_test");
		segment.addConnection("a_connection");
		assertEquals("should be 1", segment.getConnectionLength(), 1);
		segment.addConnection("a_connection");
		segment.addConnection("a_connection");
		segment.addConnection("a_connection");
		assertEquals("should still be 3 even though we tried to add 4", segment.getConnectionLength(), 3);
	}

	@Test
	public void testGetConnections() {
		Segment segment = new Segment("segment_test");
		assertEquals("should be zero", segment.getConnectionLength() , 0);
		assertThat(segment.getConnectionLength(), instanceOf(Integer.class));
	}

	@Test
	public void testGetSize() {
		Segment segment = new Segment("segment_test");
		assertEquals("should be zero", segment.getConnectionLength() , 0);
		assertThat(segment.getConnectionLength(), instanceOf(Integer.class));
	}

	@Test
	public void testGetType() {
		Segment segment = new Segment("segment_test");
		assertNotNull("should not be null", segment.getType());
	}

	@Test
	public void testGetComments() {
		Segment segment = new Segment("segment_test");
		assertEquals("should be zero", segment.getComments().size(), 0);
	}

}
