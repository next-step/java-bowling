package bowling.domain;

import static bowling.domain.PitchResult.*;

import bowling.domain.exception.InvalidPinCountException;

public class NormalFrame extends Frame {

	private static final int MAX = 10;

	private NormalFrame() {

	}

	private NormalFrame(final Pitch first) {
		super(first);
	}

	private NormalFrame(final Pitch first, final Pitch second) {
		super(first, second);
	}

	public static NormalFrame of() {
		return new NormalFrame();
	}

	public static NormalFrame of(final Pitch first) {
		return new NormalFrame(first);
	}

	public static NormalFrame of(final Pitch first, final Pitch second) {
		return new NormalFrame(first, second);
	}

	@Override
	public boolean isEnd() {
		if (first == null) {
			return false;
		}

		if (first.getPitchResult().equals(STRIKE)) {
			return true;
		}

		return second != null;
	}

	@Override
	public void pitch(final int pinCount) {
		if (first == null) {
			first = new Pitch(pinCount);
			return;
		}

		if (first.getPinCount() + pinCount > MAX) {
			throw new InvalidPinCountException();
		}

		second = first.next(pinCount);
	}

	@Override
	public String getResult() {
		if (first == null) {
			return "";
		}

		if (first.getPitchResult().equals(STRIKE)) {
			return STRIKE.getFlag(first.getPinCount());
		}

		if (isEnd()) {
			return first.getPinCount() + DELIMITER + second.getPitchResult().getFlag(second.getPinCount());
		}

		return String.valueOf(first.getPinCount());
	}
}
