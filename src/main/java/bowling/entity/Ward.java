package bowling.entity;

import static bowling.exception.UserExceptionMesssage.*;

import bowling.exception.UserException;

public abstract class Ward {
	private final int score;

	public Ward(int score) {
		validate(score);
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	private void validate(int score) {
		if (score > 10) {
			throw new UserException(BOWLING_BOUND);
		}
	}

	abstract boolean hasNext();
}
