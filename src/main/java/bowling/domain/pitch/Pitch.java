package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public interface Pitch {
    Pitch play(KnockedPins knockedPins);

    KnockedPins getKnockedPins();
}
