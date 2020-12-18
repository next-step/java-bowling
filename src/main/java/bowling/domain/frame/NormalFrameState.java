package bowling.domain.frame;

import bowling.domain.Pins;

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
        if (firstPitching.isStrike()) {
            return STRIKE;
        }

        if (secondPitching == null) {
            return SECOND_PITCHING;
        }

        if (firstPitching.isSpare(secondPitching)) {
            return SPARE;
        }
        return MISS;
    }
}
