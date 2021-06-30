package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public interface Pitch {
    KnockedPins knockedPins();

    Pitch play(int knockedPinsCount);

    Pitch play(KnockedPins knockedPins);
}
