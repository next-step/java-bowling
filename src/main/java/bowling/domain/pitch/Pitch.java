package bowling.domain.pitch;

import bowling.domain.common.FalledPins;

public interface Pitch {

	Pitch play(final int falledPinsCount);

	Pitch play(final FalledPins falledPins);

	FalledPins getFalledPins();
}
