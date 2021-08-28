package bowling.domain;

import static bowling.domain.PitchResult.*;

import java.util.Optional;

public abstract class Frame {

	protected static final String DELIMITER = "|";

	protected Pitch first;
	protected Pitch second;

	protected Frame() {

	}

	protected Frame(final Pitch first) {
		this.first = first;
	}

	protected Frame(final Pitch first, final Pitch second) {
		this.first = first;
		this.second = second;
	}

	public abstract boolean isEnd();

	public abstract void pitch(final int pinNumber);

	public boolean isStrike() {
		return first.getPitchResult().equals(STRIKE);
	}

	public boolean isSpare() {
		return second.getPitchResult().equals(SPARE);
	}

	public boolean isMiss() {
		return Optional.ofNullable(second)
			.map(e -> second.getPitchResult().equals(MISS))
			.orElseGet(() -> first.getPitchResult().equals(MISS));
	}

	public boolean isGutter() {
		return Optional.ofNullable(second)
			.map(e -> second.getPitchResult().equals(GUTTER))
			.orElseGet(() -> first.getPitchResult().equals(GUTTER));
	}

	public abstract String getResult();

	public Pitch getFirst() {
		return first;
	}

	public Pitch getSecond() {
		return second;
	}
}
