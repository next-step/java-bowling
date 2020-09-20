package bowling.domain.state;

import bowling.domain.DownedPinCount;
import bowling.domain.score.PlayingScore;
import bowling.domain.score.Score;

import java.util.Objects;

import static bowling.domain.DownedPinCount.ALL_PIN_DOWN;

public class Playing implements State {

	private static final String PLAYING_MESSAGE = "%s  ";

	protected final DownedPinCount first;
	protected Score score;

	protected Playing(DownedPinCount downedPinCount, Score accumulated) {
		this.first = downedPinCount;
		this.score = new PlayingScore(first, accumulated);
	}

	@Override
	public State roll(DownedPinCount downedPinCount, Score accumulated) {
		if(ALL_PIN_DOWN.equals(DownedPinCount.sum(first, downedPinCount))) {
			return new Spare(first, downedPinCount, accumulated);
		}
		return new Open(first, downedPinCount, accumulated);
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public String reportState() {
		return String.format(PLAYING_MESSAGE, convertReportPattern(first));
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
		Playing playing = (Playing) o;
		return first.equals(playing.first);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first);
	}
}
