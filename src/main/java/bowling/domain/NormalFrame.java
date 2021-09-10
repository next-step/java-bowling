package bowling.domain;

public class NormalFrame extends Frame {

    private static final int FIRST_INDEX = 0;
    private static final int PITCHES_LIMIT = 2;
    private static final int TOTAL_MAX = 10;
    private static final String INVALID_PITCHES_SUM = String.format("모든 투구의 합은 %d 이하여야 합니다", TOTAL_MAX);

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
        return isFirstStrike()
                || pitches.size() == PITCHES_LIMIT;
    }

    private void requireValidSum(final Pitch pitch) {
        if (pitchesSum(pitch) > TOTAL_MAX) {
            throw new IllegalArgumentException(INVALID_PITCHES_SUM);
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
