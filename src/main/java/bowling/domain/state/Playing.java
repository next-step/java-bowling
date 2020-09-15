package bowling.domain.state;

import bowling.domain.DownedPinCount;

import java.util.Objects;

import static bowling.domain.DownedPinCount.TEN;

public class Playing implements State {

	private static final String PLAYING_MESSAGE = "%s  ";

	protected final DownedPinCount first;

	protected Playing(DownedPinCount downedPinCount) {
		this.first = downedPinCount;
	}

	@Override
	public State roll(DownedPinCount downedPinCount) {
		if(DownedPinCount.sum(first, downedPinCount) == TEN) {
			return new Spare(first, downedPinCount);
		}
		return new Open(first, downedPinCount);
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Playing playing = (Playing) o;
		return first == playing.first;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first);
	}
}
