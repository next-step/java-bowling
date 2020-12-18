package bowling.domain.frame;

import bowling.domain.Pins;

public enum FinalFrameState {
    FIRST_PITCHING,
    SECOND_PITCHING,
    THIRD_PITCHING,
    END,
    ;

    public boolean isPitchable() {
        return this != END;
    }

    public static FinalFrameState nextState(final Pins firstPitching, final Pins secondPitching, final FinalFrameState beforeState) {
        if (beforeState == FIRST_PITCHING) {
            return SECOND_PITCHING;
        }

        if (beforeState == SECOND_PITCHING) {
            return calStateAfterSecondPitching(firstPitching, secondPitching);
        }

        if (beforeState == THIRD_PITCHING) {
            return END;
        }

        throw new IllegalStateException();
    }
    
    private static FinalFrameState calStateAfterSecondPitching(final Pins firstPitching, final Pins secondPitching) {
        if (firstPitching.isStrike()) {
            return THIRD_PITCHING;
        }

        if (firstPitching.isSpare(secondPitching)) {
            return THIRD_PITCHING;
        }

        return END;
    }
}
