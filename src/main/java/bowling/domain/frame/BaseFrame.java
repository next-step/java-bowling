package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bowling.domain.common.Pins;
import bowling.domain.exception.InvalidMethodCallException;
import bowling.domain.pitch.NormalPitch;
import bowling.domain.pitch.Pitch;
import bowling.domain.pitch.SparePitch;
import bowling.domain.pitch.StrikePitch;

public abstract class BaseFrame implements Frame {

	private static final Pins STRIKE_PINS = Pins.of(10);
	private static final int FIRST_INDEX = 0;
	private static final int SECOND_INDEX = 1;

	protected final List<Pitch> pitches;

	protected BaseFrame(final List<Pitch> pitches) {
		this.pitches = pitches;
	}

	@Override
	public Frame pitch(final int pinsCount) {
		return pitch(Pins.of(pinsCount));
	}

	protected List<Pitch> playedPitches(final Pins pins) {
		final Pitch playedPitch = pitches.isEmpty()
			? firstPitch(pins)
			: otherPitch(pins);

		return Stream.concat(pitches.stream(), Stream.of(playedPitch))
			.collect(Collectors.toList());
	}

	private Pitch firstPitch(final Pins pins) {
		return (pins.isEqualsCount(STRIKE_PINS))
			? new StrikePitch()
			: new NormalPitch(pins);
	}

	private Pitch otherPitch(final Pins pins) {
		return pitches.get(pitches.size() - 1).play(pins);
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
