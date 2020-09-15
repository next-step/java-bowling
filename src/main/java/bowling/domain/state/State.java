package bowling.domain.state;

import bowling.domain.DownedPinCount;

import static bowling.domain.DownedPinCount.TEN;
import static bowling.domain.DownedPinCount.ZERO;

public interface State {

	String STRIKE = "X";
	String GUTTER = "-";

	default String convertReportPattern(DownedPinCount downedPinCount) {
		if(downedPinCount == TEN) {
			return STRIKE;
		}
		if(downedPinCount == ZERO) {
			return GUTTER;
		}
		return downedPinCount.toString();
	}

	State roll(DownedPinCount downedPinCount);
	boolean isDone();
	String reportState();
}
