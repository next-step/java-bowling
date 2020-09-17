package bowling.domain.state;

import bowling.domain.DownedPinCount;

import java.util.Objects;

public class Spare implements State {

	private static final String SPARE = "|/";

	private final DownedPinCount first;
	private final DownedPinCount second;

	Spare(DownedPinCount first, DownedPinCount second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public State roll(DownedPinCount downedPinCount) {
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
