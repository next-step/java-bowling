package bowling.domain;

import static bowling.domain.Pin.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bowling.exception.GameOverException;

public class FinalFrame implements Frame {
	private static final int MIN_PITCH_COUNT = 2;
	private static final int MAX_PITCH_COUNT = 3;
	private final Pins pins;

	public FinalFrame() {
		this.pins = new Pins();
	}

	public void pitch(int count) {
		if (this.isEnd()) {
			throw new GameOverException();
		}
		pins.pitch(count);
	}

	public boolean isEnd() {
		if (pins.isEmpty()) {
			return false;
		}
		if (pins.size() == MIN_PITCH_COUNT && pins.sumAll() < MAX_PIN_COUNT) {
			return true;
		}
		return pins.overPitching(MAX_PITCH_COUNT);
	}

	@Override
	public Frame next() {
		throw new GameOverException();
	}

	public List<Pin> getPins() {
		return Collections.unmodifiableList(pins.getPins());
	}

	@Override
	public List<String> getScore() {
		return pins.getScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FinalFrame that = (FinalFrame)o;
		return Objects.equals(pins, that.pins);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pins);
	}
}
