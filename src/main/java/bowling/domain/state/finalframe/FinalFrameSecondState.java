package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.state.Open;
import bowling.domain.state.State;

import static bowling.domain.DownedPinCount.ALL_PIN_DOWN;

public class FinalFrameSecondState extends Open {

	private static final String SPARE_REPORT_PATTERN = "/";

	public FinalFrameSecondState(DownedPinCount first, DownedPinCount second) {
		super(first, second);
	}

	protected boolean isSpare() {
		return ALL_PIN_DOWN.equals(DownedPinCount.sum(first, second));
	}

	protected boolean containsStrike() {
		return ALL_PIN_DOWN.equals(first) || ALL_PIN_DOWN.equals(second);
	}

	protected String getSecondReportPattern(DownedPinCount second) {

		return isSpare() ? SPARE_REPORT_PATTERN : convertReportPattern(second);
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
