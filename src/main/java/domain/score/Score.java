package domain.score;

import domain.state.State;
import domain.states.BowlingPins;

public class Score {

	private int score;
	private int left;

	private Score(int score) {
		this.score = score;
		this.left = -1;
	}

	public static Score of(int score) {
		return new Score(score);
	}

	public void initialize(int score) {
		this.score = score;
	}

	public void reflect(State state) {
		score += state.getFallenBowlingPins();
		if (state.isLastStateToDecideScoreLeft()) {
			left = state.getLeftStatesToCalculateScore();
		}
	}

	public void reflect(BowlingPins pins) {
		if (left != 0) {
			score += pins.getPins();
			left --;
		}
	}

	public boolean canCalculateScore() {
		return left == 0;
	}

	public int getScore() {
		return score;
	}

}
