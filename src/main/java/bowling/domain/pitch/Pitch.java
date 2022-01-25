package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public interface Pitch {
    boolean isSpare(KnockedPins knockedPins);
}
