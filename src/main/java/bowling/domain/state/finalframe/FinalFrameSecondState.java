package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.state.Open;
import bowling.domain.state.State;

import static bowling.domain.DownedPinCount.TEN;

public class FinalFrameSecondState extends Open {

	private static final int ALL_PIN_DOWN = 10;

	public FinalFrameSecondState(DownedPinCount first, DownedPinCount second) {
		super(first, second);
	}

	protected boolean isSpare() {
		return DownedPinCount.sumIntValue(first, second) == ALL_PIN_DOWN;
	}

	protected boolean containsStrike() {
		return first == TEN || second == TEN;
	}

	protected String getSecondReportPattern(DownedPinCount second) {
		if(isSpare()) {
			return "/";
		}
		return convertReportPattern(second);
	}

	@Override
	public State roll(DownedPinCount downedPinCount) {
		if(!isDone()) {
			return new FinalFrameBonusState(first, second, downedPinCount);
		}
		return this;
	}

	@Override
	public boolean isDone() {
		return !containsStrike() && !isSpare();
	}

	@Override
	public String reportState() {
		return convertReportPattern(first) + SPLITTER + getSecondReportPattern(second);
	}
}
