package bowling.domain.state;

import bowling.domain.DownedPinCount;

import java.util.Objects;

public class Open implements State {
	protected static final String SPLITTER = "|";
	protected final DownedPinCount first;
	protected final DownedPinCount second;

	protected Open(DownedPinCount first, DownedPinCount second) {
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
		return convertReportPattern(first) + SPLITTER + convertReportPattern(second);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Open open = (Open) o;
		return first.equals(open.first) &&
				second.equals(open.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
}
