package bowling.domain.frame;

import java.util.Collections;
import java.util.List;

import bowling.domain.KnockedPins;
import bowling.domain.exception.InvalidMethodCallException;
import bowling.domain.frame.exception.InvalidProgressFrameException;
import bowling.domain.pitch.Pitch;

public final class FinalFrame extends BaseFrame {

	private static final int MAX_PITCHES_COUNT = 3;

	private FinalFrame(final List<Pitch> pitches) {
		super(pitches);
	}

	public static Frame of() {
		return new FinalFrame(Collections.emptyList());
	}

	@Override
	public boolean possiblePitch() {
		if (pitches.size() == MAX_PITCHES_COUNT) {
			return false;
		}

		return pitches().size() < MAX_PITCHES_COUNT - 1
			|| isFirstStrike() || isSecondSpare();
	}

	@Override
	public Frame pitch(final KnockedPins knockedPins) {
		if (!possiblePitch()) {
			throw new InvalidProgressFrameException();
		}

		return new FinalFrame(playedPitches(knockedPins));
	}
}
