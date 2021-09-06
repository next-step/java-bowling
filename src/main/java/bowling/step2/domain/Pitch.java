package bowling.step2.domain;

public class Pitch {
    private final int count;

    private static final int MIN_PITCH_COUNT = 0;

    private static final int MAX_PITCH_COUNT = 10;

    private Pitch(int count) {
        validateCount(count);
        this.count = count;
    }

    public static Pitch of(int count) {
        return new Pitch(count);
    }

    private void validateCount(int count) {
        if (countNotInRange(count)) {
            throw new RuntimeException("쓰러뜨릴수 있는 Pin의 갯수는 " + MIN_PITCH_COUNT + "이상 " + MAX_PITCH_COUNT + "이하여야 합니다.");
        }
    }

    private boolean countNotInRange(int count) {
        return count < MIN_PITCH_COUNT || count > MAX_PITCH_COUNT;
    }

    public int count() {
        return this.count;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
