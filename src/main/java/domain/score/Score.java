package domain.score;

import domain.state.State;
import domain.BowlingPins;

public class Score {

	private static final int INITIAL_SCORE = 0;

	private final int score;
	private final ScoreState scoreState;

	private Score(int score, ScoreState scoreState) {
		this.score = score;
		this.scoreState = scoreState;
	}

	public static Score of(int score, int left, boolean reflectedPrevious) {
		return new Score(score, ScoreState.of(left, reflectedPrevious));
	}

	public static Score of(boolean reflectedPrevious) {
		return new Score(INITIAL_SCORE, ScoreState.of(reflectedPrevious));
	}

	public Score reflect(State state) {
		ScoreState newScoreState = scoreState;
		if (state.isLastStateToDecideScoreLeft()) {
			newScoreState = scoreState.initializeLeft(state.getLeftStatesToCalculateScore());
		}
		return new Score(score + state.getFallenBowlingPins(), newScoreState);
	}

	public Score reflect(BowlingPins pins) {
		if (scoreState.canReflect()) {
			return new Score(score + pins.getPins(), scoreState.minusOneLeft());
		}
		return this;
	}

	public Score reflectPrevScore(int prevScore) {
		return new Score(score + prevScore, scoreState.reflected());
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
