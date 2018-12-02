package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hspark on 02/12/2018.
 */
public class FrameNumberTest {
	@Test(expected = IllegalArgumentException.class)
	public void test_음수생성() {
		FrameNumber frameNumber = new FrameNumber(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_10이상생성() {
		FrameNumber frameNumber = new FrameNumber(11);
	}
}