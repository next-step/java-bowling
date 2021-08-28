package bowling.domain;

import java.util.Objects;

public class Score {

	private final int value;

	public Score(final int value) {
		this.value = value;
	}

	public Score(final Frame frame) {
		this.value = frame.getFirst().getPinCount() + frame.getSecond().getPinCount();
	}

	public static Score zero() {
		return new Score(0);
	}

	public int getValue() {
		return value;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Score))
			return false;
		final Score score = (Score)o;
		return getValue() == score.getValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}
}
