package bowling.domain;

import static bowling.domain.Scoring.*;

class FrameStatus {
    private final int firstFallingPins;
    private final int secondFallingPins;
    private final Scoring scoring;

    public FrameStatus(int fallingPins, Scoring scoring) {
        this(fallingPins, 0, scoring);
    }

    public FrameStatus(int firstFallingPins, int secondFallingPins) {
        this(firstFallingPins, secondFallingPins, MISS);
    }

    public FrameStatus(int firstFallingPins, int secondFallingPins, Scoring scoring) {
        this.firstFallingPins = firstFallingPins;
        this.secondFallingPins = secondFallingPins;
        this.scoring = scoring;
    }

    public static FrameStatus strike() {
        return new FrameStatus(10, STRIKE);
    }

    public static FrameStatus spare(int firstFallingPins) {
        return new FrameStatus(firstFallingPins, SPARE);
    }

    public static FrameStatus miss(int first, int second) {
        return new FrameStatus(first, second);
    }

    public static FrameStatus playing(int first) {
        return new FrameStatus(first, NONE);
    }

    @Override
    public String toString() {
        if (scoring == Scoring.STRIKE) {
            return "X";
        }
        if (scoring == Scoring.NONE) {
            return String.valueOf(firstFallingPins);
        }
        if (scoring == Scoring.SPARE) {
            return String.format("%d|/", firstFallingPins);
        }

        return String.format("%s|%s", toNumberOrGutter(firstFallingPins), toNumberOrGutter(secondFallingPins));
    }

    private String toNumberOrGutter(int fallingPins) {
        if (fallingPins == 0)
            return "-";
        return String.valueOf(fallingPins);
    }
}
