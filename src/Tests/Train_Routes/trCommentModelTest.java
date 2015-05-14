package Tests.Train_Routes;
import static org.junit.Assert.*;

import org.hamcrest.*;
import org.junit.Test;

import Train_Routes.trCommentModel;

import java.io.*;
import java.util.*;

public class trCommentModelTest {
	/*
	This is a test for trCommentModel.java
	*/
	@Test
	public void testtrCommentModel() {
		trCommentModel tr_commentmodel = new trCommentModel("file_name", 3, "comment");
		assertEquals("failure - strings are not equal", tr_commentmodel.getFilePath(), "file_name");
		assertEquals("failure - numbers are not equal", tr_commentmodel.getLineNumber(), 3);
		assertEquals("failure - strings are not equal", tr_commentmodel.getComment(), "comment");
	}
}