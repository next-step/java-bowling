package domain.score;

import domain.state.State;
import domain.states.BowlingPins;

public class Score {

	private int score;
	private int left;

	private Score(int score, int left) {
		this.score = score;
		this.left = left;
	}

	public static Score of(int score, int left) {
		return new Score(score, left);
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
			left--;
		}
	}

	public void reflect(int prevScore) {
		score += prevScore;
	}

	public boolean isEndCalculation() {
		return left == 0;
	}

	public int getScore() {
		return score;
	}

}
