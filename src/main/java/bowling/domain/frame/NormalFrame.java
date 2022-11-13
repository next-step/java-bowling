package bowling.domain.frame;

import bowling.domain.Constants;

public class NormalFrame extends Frame {

	public NormalFrame save(int score) {
		this.add(score);

		if (isStrike(this.getLastIndex()) || isMaxSize()) {
			return new NormalFrame();
		}
		return this;
	}

	public boolean isMaxSize() {
		return this.getScores().size() == Constants.NORMAL_FRAME_SCORE_SIZE;
	}
}
