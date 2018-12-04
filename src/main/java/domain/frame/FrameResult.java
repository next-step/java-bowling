package domain.frame;

import domain.frame.state.State;

/**
 * Created by hspark on 04/12/2018.
 */
public class FrameResult {
	public static final int NON_CALCULATE_SCORE = -1;

	private State state;
	private int score;

	public FrameResult(State state, int score) {
		this.state = state;
		this.score = score;
	}

	public String getResult() {
		return state.toString();
	}

	public int getScore() {
		return score;
	}

	public boolean isCalculatedScore() {
		return this.score != NON_CALCULATE_SCORE;
	}

	public State getState() {
		return state;
	}
}
