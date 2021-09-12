package bowling.domain;

import bowling.exception.NormalFrameInvalidPitchesSumException;

public class NormalFrame extends Frame {

    private static final int FIRST_INDEX = 0;
    private static final int PITCHES_LIMIT = 2;
    private static final int TOTAL_MAX = 10;

    @Override
    public boolean addPitchIfPossible(final Pitch pitch) {
        if (isFull()) {
            return false;
        }
        requireValidSum(pitch);
        return pitches.add(pitch);
    }

    @Override
    public boolean isFull() {
        return isFirstStrike()
                || pitches.size() == PITCHES_LIMIT;
    }

    private void requireValidSum(final Pitch pitch) {
        if (pitchesSum(pitch) > TOTAL_MAX) {
            throw new NormalFrameInvalidPitchesSumException(TOTAL_MAX);
        }
    }

    private boolean isFirstStrike() {
        return !pitches.isEmpty()
                && firstPitch().isStrike();
    }

    private Pitch firstPitch() {
        return pitches.get(FIRST_INDEX);
    }

}
