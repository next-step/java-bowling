package bowling.entity;

import static bowling.exception.UserExceptionMesssage.*;

import bowling.exception.UserException;
import bowling.views.BowlingScoreString;

public class NormalFrameScore {
	private final FirstWard firstWard;
	private final SecondWard secondWard;

	public NormalFrameScore(FirstWard firstWard) {
		this(firstWard, null);
	}

	public NormalFrameScore(FirstWard firstWard, SecondWard secondWard) {
		this.firstWard = firstWard;
		this.secondWard = secondWard;
	}

	public boolean isKeepGoing() {
		return firstWard.hasNext();
	}

	public static NormalFrameScore ofFirst(int score) {
		return new NormalFrameScore(new FirstWard(score));
	}

	public static NormalFrameScore ofSecond(NormalFrameScore normalFrameScore, int score) {
		validate(score + normalFrameScore.firstWard.getScore());
		return new NormalFrameScore(normalFrameScore.firstWard, new SecondWard(score));
	}

	private static void validate(int score) {
		if (score < 0 || score > 10) {
			throw new UserException(BOWLING_BOUND);
		}
	}

	public String getFrameScoreToString() {
		BowlingScoreType bowlingScoreType = BowlingScoreType.getInstance(firstWard, secondWard);
		return BowlingScoreString.getScoreString(bowlingScoreType, firstWard, secondWard);
	}

}
