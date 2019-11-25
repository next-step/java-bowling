package domain.score;

import domain.state.State;
import domain.BowlingPins;

public class Score {

	private int score;
	private final ScoreState scoreState;

	private Score(int score, ScoreState scoreState) {
		this.score = score;
		this.scoreState = scoreState;
	}

	public static Score of(int score, int left, boolean reflectedPrevious) {
		return new Score(score, ScoreState.of(left, reflectedPrevious));
	}

	public void reflect(State state) {
		score += state.getFallenBowlingPins();
		if (state.isLastStateToDecideScoreLeft()) {
			scoreState.initializeLeft(state.getLeftStatesToCalculateScore());
		}
	}

	public void reflect(BowlingPins pins) {
		if (scoreState.canReflect()) {
			score += pins.getPins();
			scoreState.minusOneLeft();
		}
	}

	public void reflectPrevScore(int prevScore) {
		score += prevScore;
		scoreState.reflected();
	}

	public boolean isEndCalculation() {
		return !scoreState.canReflect();
	}

	public boolean canShowScore() {
		return scoreState.canShowScore();
	}

	public int getScore() {
		return score;
	}

}
