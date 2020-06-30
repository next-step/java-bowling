package bowling.domain.frame;

import bowling.util.FrameSize;

public class NormalFrame implements Frame {

	private final int index;

	private NormalFrame(int index) {
		this.index = index;
	}

	public static NormalFrame of(int index) {
		return new NormalFrame(index);
	}

	public static NormalFrame createFirstFrame() {
		final int FIRST_INDEX = 0;
		return new NormalFrame(FIRST_INDEX);
	}

	@Override
	public Frame addNextFrame() {
		final int nextIndex = index + 1;
		if (nextIndex == FrameSize.NORMAL_FRAME_SIZE) {
			return FinalFrame.of();
		}
		return NormalFrame.of(nextIndex);
	}
}
