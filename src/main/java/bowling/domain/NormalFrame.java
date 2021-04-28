package bowling.domain;

import java.util.Objects;

import bowling.exception.InvalidFrameNumberException;
import bowling.exception.InvalidPlayCountException;

public class NormalFrame extends Frame {
	private static final int MIN_FRAME_NUMBER = 1;
	private static final int MAX_FRAME_NUMBER = 9;
	private static final String FRAME_ERROR_MESSAGE = "Normal Frame은 1~9 프레임까지만 가능합니다.";
	private static final String INVALID_PLAY_ERROR_MESSAGE = "최대 2회까지만 투구 가능합니다.";

	public NormalFrame(int frameNumber) {
		super(frameNumber);
	}

	protected void validateFrameNumber(int frameNumber) {
		if (MIN_FRAME_NUMBER > frameNumber || MAX_FRAME_NUMBER < frameNumber) {
			throw new InvalidFrameNumberException(FRAME_ERROR_MESSAGE);
		}
	}

	protected void validatePlayCount() {
		if (playCount > 1) {
			throw new InvalidPlayCountException(INVALID_PLAY_ERROR_MESSAGE);
		}
	}

	public boolean isEndFrame() {
		return (playCount > 0 && pinStatus().firstPin() == 10) || playCount == 2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NormalFrame that = (NormalFrame)o;
		return frameNumber == that.frameNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameNumber);
	}

}
