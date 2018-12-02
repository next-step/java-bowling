package domain;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 02/12/2018.
 */
public class FrameNumber {
	public static final int MAX_FRAME_NUMBER = 10;
	private static final int FIRST_NORMAL_FRAME_NUMBER = 1;
	private int frameNumber;

	public FrameNumber(int frameNumber) {
		checkArgument(frameNumber <= MAX_FRAME_NUMBER);
		checkArgument(frameNumber >= FIRST_NORMAL_FRAME_NUMBER);
		this.frameNumber = frameNumber;
	}

	public int toInteger() {
		return frameNumber;
	}
}
