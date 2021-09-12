package bowling.domain;

import bowling.exception.NoStrikeFinalFrameInvalidPitchesSumException;
import bowling.exception.StrikeFinalFrameInvalidPitchesSumException;

public class FinalFrame extends Frame {

    private static final int FIRST_PITCH_INDEX = 0;
    private static final int SECOND_PITCH_INDEX = 1;
    private static final int PITCHES_LIMIT = 2;
    private static final int STRIKE_PITCHES_LIMIT = 3;
    private static final int NONE_STRIKE_MAX = 10;
    private static final int SINGLE_STRIKE_MAX = 20;
    private static final int NO_SCORE = 0;

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
        return isStrike(FIRST_PITCH_INDEX) || isSpare()
                ? pitches.size() == STRIKE_PITCHES_LIMIT
                : pitches.size() == PITCHES_LIMIT;
    }

    @Override
    public int score() {
        if (!isFull()) {
            return NO_SCORE;
        }
        return pitchesSum();
    }

    private void requireValidSum(final Pitch pitch) {
        if (isStrike(FIRST_PITCH_INDEX) || isSpare()) {
            requireValidSumWhenSingleStrike(pitch);
            return;
        }
        if (pitchesSum(pitch) > NONE_STRIKE_MAX) {
            throw new NoStrikeFinalFrameInvalidPitchesSumException(NONE_STRIKE_MAX);
        }
    }

    private void requireValidSumWhenSingleStrike(final Pitch pitch) {
        if (isStrike(SECOND_PITCH_INDEX)) {
            return;
        }
        if (pitchesSum(pitch) > SINGLE_STRIKE_MAX) {
            throw new StrikeFinalFrameInvalidPitchesSumException(NONE_STRIKE_MAX, SINGLE_STRIKE_MAX);
        }
    }

    private boolean isSpare() {
        return pitches.size() >= PITCHES_LIMIT
                && pitchesSum() == NONE_STRIKE_MAX;
    }

    private boolean isStrike(final int pitchIndex) {
        return pitches.size() > pitchIndex
                && getPitch(pitchIndex).isStrike();
    }

    private Pitch getPitch(final int pitchIndex) {
        return pitches.get(pitchIndex);
    }

}
