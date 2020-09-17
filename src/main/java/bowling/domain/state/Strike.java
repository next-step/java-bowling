package bowling.domain.state;

import bowling.domain.DownedPinCount;

import java.util.Objects;

public class Strike implements State {

	private static final String STRIKE_MESSAGE = "X  ";

	private final DownedPinCount first;

	Strike(DownedPinCount downedPinCount) {
		this.first = downedPinCount;
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
		return STRIKE_MESSAGE;
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
