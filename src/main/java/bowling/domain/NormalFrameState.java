package bowling.domain;

public enum NormalFrameState {
    FIRST_PITCHING,
    SECOND_PITCHING,
    STRIKE,
    SPARE,
    MISS,
    ;

    public boolean isPitchable() {
        return this == FIRST_PITCHING || this == SECOND_PITCHING;
    }

    public static NormalFrameState nextState(final Pins firstPitching, final Pins secondPitching) {
        if (isStrike(firstPitching)) {
            return STRIKE;
        }

        if (secondPitching == null) {
            return SECOND_PITCHING;
        }

        if (isSpare(firstPitching, secondPitching)) {
            return SPARE;
        }
        return MISS;
    }

    private static boolean isStrike(final Pins pitching) {
        return Pins.MAX.equals(pitching);
    }

    private static boolean isSpare(final Pins firstPitching, final Pins secondPitching) {
        return Pins.MAX.equals(firstPitching.sum(secondPitching));
    }
}
