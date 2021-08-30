package bowling.domain.frame;

import java.util.Collections;
import java.util.List;

import bowling.domain.KnockedPins;
import bowling.domain.frame.exception.InvalidProgressFrameException;
import bowling.domain.pitch.Pitch;

public final class NormalFrame extends BaseFrame {

	private static final int MAX_PITCHES_COUNT = 2;

	private NormalFrame(final List<Pitch> pitches) {
		super(pitches);
	}

	public static Frame of() {
		return new NormalFrame(Collections.emptyList());
	}

	@Override
	public boolean possiblePitch() {
		return !(pitches.size() == MAX_PITCHES_COUNT || isFirstStrike());
	}

	@Override
	public Frame pitch(final KnockedPins knockedPins) {
		if (!possiblePitch()) {
			throw new InvalidProgressFrameException();
		}

		return new NormalFrame(playedPitches(knockedPins));
	}

	@Override
	public Frame next() {
		return NormalFrame.of();
	}

	@Override
	public Frame last() {
		return FinalFrame.of();
	}
}
