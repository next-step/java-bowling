package bowling.domain;

import java.util.Objects;

public class FinalFrame extends Frame {
	private static final int FINAL_FRAME_NUMBER = 10;
	private static final String FRAME_ERROR_MESSAGE = "Final Frame은 10프레임만 가능합니다.";

	public FinalFrame(int frameNumber) {
		super(frameNumber);
	}

	protected void validateFrameNumber(int frameNumber) {
		if (FINAL_FRAME_NUMBER != frameNumber) {
			throw new IllegalArgumentException(FRAME_ERROR_MESSAGE);
		}
	}

	protected void validatePlayCount() {
		if (playCount > 2) {
			throw new RuntimeException("최대 3회까지만 투구 가능합니다.");
		}
	}

	public boolean isEndFrame() {
		if (playCount < 2) {
			return false;
		}
		if ((pinStatus().firstPin() + pinStatus().secondPin() < 10) || playCount == 3) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FinalFrame that = (FinalFrame)o;
		return frameNumber == that.frameNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameNumber);
	}
}
