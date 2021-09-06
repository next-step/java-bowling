package bowling.domain.pitch;

import bowling.domain.common.Pins;

public interface Pitch {

	Pitch play(final int pinsCount);

	Pitch play(final Pins pins);

	Pins getPins();
}
