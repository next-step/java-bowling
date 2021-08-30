package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bowling.domain.common.KnockedPins;
import bowling.domain.exception.InvalidMethodCallException;
import bowling.domain.pitch.NormalPitch;
import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.SparePitch;
import bowling.domain.pitch.StrikePitch;

public abstract class BaseFrame implements Frame {

	private static final int STRIKE_PINS_COUNT = 10;
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;

	protected final List<Pitch> pitches;

	protected BaseFrame(final List<Pitch> pitches) {
		this.pitches = pitches;
	}

	@Override
	public Frame pitch(final int knockedPinsCount) {
		return pitch(KnockedPins.of(knockedPinsCount));
	}

	protected List<Pitch> playedPitches(final KnockedPins knockedPins) {
		final Pitch playedPitch = pitches.isEmpty()
			? firstPitch(knockedPins)
			: otherPitch(knockedPins);

		return Stream.concat(pitches.stream(), Stream.of(playedPitch))
			.collect(Collectors.toList());
	}

	protected Pitch firstPitch(final KnockedPins knockedPins) {
		return (knockedPins.count() == STRIKE_PINS_COUNT)
			? new StrikePitch()
			: new NormalPitch(knockedPins);
	}

	protected Pitch otherPitch(final KnockedPins knockedPins) {
		return pitches.get(pitches.size() - 1).play(knockedPins);
	}

	protected boolean isFirstStrike() {
		return pitches.size() > FIRST_INDEX
			&& pitches.get(FIRST_INDEX) instanceof StrikePitch;
	}

	protected boolean isSecondSpare() {
		return pitches.size() > SECOND_INDEX
			&& pitches.get(SECOND_INDEX) instanceof SparePitch;
	}

	@Override
	public List<Pitch> pitches() {
		return Collections.unmodifiableList(pitches);
	}

	@Override
	public Frame next() {
		throw new InvalidMethodCallException();
	}

	@Override
	public Frame last() {
		throw new InvalidMethodCallException();
	}
}
