package bowling.domain.state;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;
import bowling.domain.score.normalframe.StrikeScore;

import java.util.Objects;

public class Strike implements State {

	private static final String STRIKE_MESSAGE = "X  ";

	private final DownedPinCount first;

	private Score score;

	Strike(DownedPinCount downedPinCount, Score accumulated) {
		this.first = downedPinCount;
		this.score = new StrikeScore(accumulated);
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
		return STRIKE_MESSAGE;
	}

	@Override
	public Score getScore() {
		return score;
	}

	@Override
	public void addPreviousCount(DownedPinCount pinCount) {
		score.addExtraCount(pinCount);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Strike strike = (Strike) o;
		return first.equals(strike.first);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first);
	}
}
