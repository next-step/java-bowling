package bowling.domain.state;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;
import bowling.domain.score.normalframe.SpareScore;

import java.util.Objects;

public class Spare implements State {

	private static final String SPARE = "|/";

	private final DownedPinCount first;
	private final DownedPinCount second;
	private Score score;

	Spare(DownedPinCount first, DownedPinCount second, Score accumulated) {
		this.first = first;
		this.second = second;
		this.score = new SpareScore(first, second, accumulated);
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
		return convertReportPattern(first) + SPARE;
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
		Spare spare = (Spare) o;
		return first.equals(spare.first) &&
				second.equals(spare.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}
