package bowling.domain.status;

import bowling.domain.Pitches;

public interface Status {
    boolean condition(Pitches pitches);

    boolean conditionOf(int fallenPins, int accumulatedPins, int pitchIndex);

    String display(Pitches pitches);

    String display(int fallenPins);

    boolean isStrike();

    boolean isSpare();

    boolean isOpen();
}
