package bowling.domain.frame;

import bowling.domain.Constants;

public class FinalFrame extends Frame {

	public FinalFrame save(int score) {
		this.add(score);

		if (isNext() || isMaxSize()) {
			return new FinalFrame();
		}
		return this;
	}

	public boolean isNext() {
		return !isStrike(this.getFirstIndex()) && isMiss(this.getLastIndex()) && this.getScores().size() > 1;
	}

	public boolean isMaxSize() {
		return this.getScores().size() == Constants.FINAL_FRAME_SCORE_SIZE;
	}
}
