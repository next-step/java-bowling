package bowling.domain.frame;

import bowling.domain.KnockedPins;

public interface Frame {

    String convert();

    void bowl(KnockedPins knockedPins, int index);

    boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB);

    boolean isLastFrame();

    boolean hasDoneFirstPitch();

    boolean hasDoneSecondPitch();
}
