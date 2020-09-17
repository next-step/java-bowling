package bowling.domain.state;

import bowling.domain.DownedPinCount;

import static bowling.domain.DownedPinCount.*;

public interface State {

	String STRIKE = "X";
	String GUTTER = "-";

	default String convertReportPattern(DownedPinCount downedPinCount) {
		if(ALL_PIN_DOWN.equals(downedPinCount)) {
			return STRIKE;
		}
		if(NO_PIN_DOWN.equals(downedPinCount)) {
			return GUTTER;
		}
		return downedPinCount.toString();
	}

	State roll(DownedPinCount downedPinCount);
	boolean isDone();
	String reportState();
}
