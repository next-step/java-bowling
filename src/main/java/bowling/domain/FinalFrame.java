package bowling.domain;

public class FinalFrame extends Frame {

    private static final int FIRST_PITCH_INDEX = 0;
    private static final int SECOND_PITCH_INDEX = 1;
    private static final int PITCHES_LIMIT = 2;
    private static final int STRIKE_PITCHES_LIMIT = 3;
    private static final int NONE_STRIKE_MAX = 10;
    private static final int SINGLE_STRIKE_MAX = 20;
    private static final String NONE_STRIKE_INVALID_PITCHES_SUM = String.format("1번째 투구가 스트라이크가 아닌 경우 모든 투구의 합은 %d 이하여야 합니다", NONE_STRIKE_MAX);
    private static final String SINGLE_STRIKE_INVALID_PITCHES_SUM = String.format("1번째 투구만 스트라이크인 경우 모든 투구의 합은 %d ~ %d 의 값이어야 합니다", NONE_STRIKE_MAX, SINGLE_STRIKE_MAX);

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

    private void requireValidSum(final Pitch pitch) {
        if (isStrike(FIRST_PITCH_INDEX) || isSpare()) {
            requireValidSumWhenSingleStrike(pitch);
            return;
        }
        if (pitchesSum(pitch) > NONE_STRIKE_MAX) {
            throw new IllegalArgumentException(NONE_STRIKE_INVALID_PITCHES_SUM);
        }
    }

    private void requireValidSumWhenSingleStrike(final Pitch pitch) {
        if (isStrike(SECOND_PITCH_INDEX)) {
            return;
        }
        if (pitchesSum(pitch) > SINGLE_STRIKE_MAX) {
            throw new IllegalArgumentException(SINGLE_STRIKE_INVALID_PITCHES_SUM);
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
