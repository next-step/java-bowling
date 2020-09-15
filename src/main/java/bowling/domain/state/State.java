package bowling.domain.state;

import bowling.domain.DownedPinCount;

import static bowling.domain.DownedPinCount.ZERO;

public interface State {

	String GUTTER = "-";

	default String convertReportPattern(DownedPinCount downedPinCount) {
		if(downedPinCount == ZERO) {
			return GUTTER;
		}
		return downedPinCount.toString();
	}

	State roll(DownedPinCount downedPinCount);
	boolean isDone();
	String reportState();
}
