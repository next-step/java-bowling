package bowling.domain.frame;

import bowling.domain.KnockedPins;

public interface Frame {
    default boolean isEmpty(KnockedPins firstKnockedPins) {
        return firstKnockedPins == null;
    }

    default boolean isStrike(KnockedPins firstKnockedPins) {
        return firstKnockedPins.equals(new KnockedPins(10));
    }

    String convert();

    void makeScore(KnockedPins firstKnockedPins, int index);

    boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB);

    boolean isLastFrame();

    boolean hasDoneFirstPitch();

    boolean hasDoneSecondPitch();
}
