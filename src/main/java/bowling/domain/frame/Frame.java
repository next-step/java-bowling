package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import bowling.domain.DownedPinCounts;
import bowling.exception.BowlingException;

import java.util.Objects;

public class Frame {

	static final String OVER_DEFAULT_MAX_PITCH_COUNT = "이번 프레임에 %d회를 초과해서 투구할 수 없습니다.";

	static final int DEFAULT_MAX_PITCH_COUNT = 2;

	private static final int SEQUENCE_ADDER = 1;

	private final int frameSequence;
	private final DownedPinCounts downedPinCounts;

	public Frame(int index) {
		this.frameSequence = index + SEQUENCE_ADDER;
		this.downedPinCounts = new DownedPinCounts();
	}

	//forTest
	Frame(int index, DownedPinCount downedPinCount) {
		this.frameSequence = index + SEQUENCE_ADDER;
		this.downedPinCounts = new DownedPinCounts(downedPinCount);
	}

	public boolean record(DownedPinCount downedPinCount) {
		validatePinScoresCount();
		return downedPinCounts.add(downedPinCount);
	}

	public int getFrameSequence() {
		return frameSequence;
	}

	public boolean isFrameFinished() {
		return downedPinCounts.isAllPinDowned() || isMaxCountPitched();
	}

	private boolean isMaxCountPitched() {
		return downedPinCounts.size() >= DEFAULT_MAX_PITCH_COUNT;
	}

	private void validatePinScoresCount() {
		if(isMaxCountPitched()) {
			throw new BowlingException(String.format(OVER_DEFAULT_MAX_PITCH_COUNT, DEFAULT_MAX_PITCH_COUNT));
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Frame frame = (Frame) o;
		return downedPinCounts.equals(frame.downedPinCounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(downedPinCounts);
	}
}
