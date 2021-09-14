package bowling.domain;

import bowling.exception.NoStrikeFinalFrameInvalidPitchesSumException;
import bowling.exception.StrikeFinalFrameInvalidPitchesSumException;

public class FinalFramePitches extends Pitches {

    private static final int FIRST_PITCH_INDEX = 0;
    private static final int SECOND_PITCH_INDEX = 1;
    private static final int PITCHES_LIMIT = 2;
    private static final int STRIKE_PITCHES_LIMIT = 3;
    private static final int NONE_STRIKE_MAX = 10;
    private static final int SINGLE_STRIKE_MAX = 20;
    private static final int NO_SCORE = -1;

    @Override
    public boolean add(final Pitch pitch) {
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
    public Score score(final Frame nextFrame) {
        if (!isFull()) {
            return Score.noScore();
        }
        return Score.from(sum());
    }

    private void requireValidSum(final Pitch pitch) {
        if (isStrike(FIRST_PITCH_INDEX) || isSpare()) {
            requireValidSumWhenSingleStrike(pitch);
            return;
        }
        if (sum(pitch) > NONE_STRIKE_MAX) {
            throw new NoStrikeFinalFrameInvalidPitchesSumException(NONE_STRIKE_MAX);
        }
    }

    private void requireValidSumWhenSingleStrike(final Pitch pitch) {
        if (isStrike(SECOND_PITCH_INDEX)) {
            return;
        }
        if (sum(pitch) > SINGLE_STRIKE_MAX) {
            throw new StrikeFinalFrameInvalidPitchesSumException(NONE_STRIKE_MAX, SINGLE_STRIKE_MAX);
        }
    }


}
