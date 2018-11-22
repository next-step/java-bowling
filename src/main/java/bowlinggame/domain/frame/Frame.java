package bowlinggame.domain.frame;

import bowlinggame.domain.result.Result;
import bowlinggame.dto.FrameResultDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Frame {

	public static final int FIRST_FRAME = 1;
	public static final int LAST_FRAME = 10;

	private int number;
	private FrameResult frameResult;

	protected Frame(int number) {
		this.number = number;
		this.frameResult = new FrameResult();
	}

	public static Frame of(int number) {
		if (number == LAST_FRAME) {
			return new FinalFrame(number);
		}
		return new NormalFrame(number);
	}

	public Frame next() {
		return of(number + 1);
	}

	protected Result record(Pin pin) {
		return frameResult.record(pin);
	}

	protected boolean isSameRollOpportunity(int rollOpportunity) {
		return frameResult.isSameRollOpportunity(rollOpportunity);
	}

	protected boolean isSameLastResult(Result result) {
		return frameResult.isSameLastResult(result);
	}

	protected Pin getRemainingPin() {
		return frameResult.getRemainingPin();
	}

	public Frame roll(int pinCount) {
		return roll(Pin.from(pinCount));
	}

	public FrameResultDto getFrameResult() {
		List<String> totalResult = frameResult.getTotalResult()
				.stream()
				.map(Result::getResult)
				.collect(Collectors.toList());
		return new FrameResultDto(totalResult);
	}

	public abstract Frame roll(Pin pin);
	public abstract boolean isCompleted();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Frame)) {
			return false;
		}
		Frame frame = (Frame) o;
		return number == frame.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
