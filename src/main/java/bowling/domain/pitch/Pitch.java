package bowling.domain.pitch;

import bowling.domain.common.KnockedPins;

public interface Pitch {

	Pitch play(final int knockedPinsCount);

	Pitch play(final KnockedPins knockedPins);

	KnockedPins getKnockedPins();
}
