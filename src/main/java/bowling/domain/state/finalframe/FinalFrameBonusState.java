package bowling.domain.state.finalframe;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;
import bowling.domain.score.finalframe.FinalFrameBonusScore;
import bowling.domain.score.finalframe.FinalFrameSecondScore;
import bowling.domain.state.Open;
import bowling.domain.state.State;

public class FinalFrameBonusState extends FinalFrameSecondState {

	private final DownedPinCount bonus;

	public FinalFrameBonusState(DownedPinCount first, DownedPinCount second, DownedPinCount bonus, Score accumulated) {
		super(first, second, accumulated);
		this.bonus = bonus;
		score = new FinalFrameBonusScore(first, second, bonus, accumulated);

	}

	@Override
	public State roll(DownedPinCount downedPinCount, Score accumulated) {
		return this;
	}

	@Override
	public boolean isDone() {
		return true;
	}

	@Override
	public String reportState() {
		return super.reportState() + Open.SPLITTER + convertReportPattern(bonus);
	}

//	@Override
//	public Score getScore() {
//		return score;
//	}
}
