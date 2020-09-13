package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import bowling.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frame {

	static final String OVER_DEFAULT_MAX_PITCH_COUNT = "이번 프레임에 %d회를 초과해서 투구할 수 없습니다.";
	static final String OVER_MAX_PIN_COUNT_PER_FRAME = "한 프레임 당 쓰러뜨린 핀의 갯수는 %d개를 초과할 수 없습니다.";
	static final int DEFAULT_MAX_PITCH_COUNT = 2;
	static final int MAX_PIN_COUNT_PER_FRAME = 10;
	private static final int SEQUENCE_ADDER = 1;

	private final int frameSequence;
	private final List<DownedPinCount> downedPinCounts;

	public Frame(int index) {
		this.frameSequence = index + SEQUENCE_ADDER;
		this.downedPinCounts = new ArrayList<>();
	}

	//forTest
	Frame(int index, DownedPinCount downedPinCount) {
		this.frameSequence = index + SEQUENCE_ADDER;
		List<DownedPinCount> downedPinCounts = new ArrayList<>();
		downedPinCounts.add(downedPinCount);
		this.downedPinCounts = downedPinCounts;
	}

	public boolean record(DownedPinCount downedPinCount) {
		validateBeforeAdd(downedPinCount);
		return downedPinCounts.add(downedPinCount);
	}

	public int getFrameSequence() {
		return frameSequence;
	}

	public boolean isFrameFinished() {
		return isAllPinDowned(getDownedPinCountSum()) || isMaxCountPitched();
	}

	private void validateBeforeAdd(DownedPinCount downedPinCount) {
		validatePinScoreSum(downedPinCount);
		validatePinScoresCount();

	}

	private void validatePinScoreSum(DownedPinCount downedPinCount) {
		if(isValidPinCountSumPerFrame(downedPinCount)) {
			throw new BowlingException(String.format(OVER_MAX_PIN_COUNT_PER_FRAME, MAX_PIN_COUNT_PER_FRAME));
		}
	}

	private boolean isValidPinCountSumPerFrame(DownedPinCount downedPinCount) {
		Integer pinScoreSum = getDownedPinCountSum();
		return isAllPinDowned(pinScoreSum) || (MAX_PIN_COUNT_PER_FRAME - pinScoreSum < downedPinCount.intValue());
	}

	private Integer getDownedPinCountSum() {
		return downedPinCounts.stream()
				.map(DownedPinCount::intValue)
				.reduce(0, Integer::sum);
	}

	private boolean isAllPinDowned(int pinScoreSum) {
		return (pinScoreSum == MAX_PIN_COUNT_PER_FRAME);
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
