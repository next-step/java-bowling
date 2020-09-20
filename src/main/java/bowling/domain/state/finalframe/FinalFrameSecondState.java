package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;
import bowling.domain.score.finalframe.FinalFrameSecondScore;
import bowling.domain.state.Open;
import bowling.domain.state.State;

import static bowling.domain.DownedPinCount.ALL_PIN_DOWN;

public class FinalFrameSecondState extends Open {

	private static final String SPARE_REPORT_PATTERN = "/";

	public FinalFrameSecondState(DownedPinCount first, DownedPinCount second, Score accumulated) {
		super(first, second, accumulated);
		this.score = new FinalFrameSecondScore(first, second, accumulated);
	}

	protected String getSecondReportPattern(DownedPinCount second) {

		return DownedPinCount.isSpare(first, second) ? SPARE_REPORT_PATTERN : convertReportPattern(second);
	}

	@Override
	public State roll(DownedPinCount downedPinCount, Score accumulated) {
		if(!isDone()) {
			return new FinalFrameBonusState(first, second, downedPinCount, accumulated);
		}
		return this;
	}

	@Override
	public boolean isDone() {
		return !DownedPinCount.containsStrike(first, second) && !DownedPinCount.isSpare(first, second);
	}

	@Override
	public String reportState() {
		return convertReportPattern(first) + SPLITTER + getSecondReportPattern(second);
	}

	@Override
	public Score getScore() {
		return score;
	}
}
