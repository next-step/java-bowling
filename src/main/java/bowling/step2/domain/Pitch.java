package bowling.step2.domain;

public class Pitch {
    private final int count;

    private final int MIN = 0;

    private final int MAX = 10;

    private Pitch(int count) {
        validateCount(count);
        this.count = count;
    }

    public static Pitch of(int count) {
        return new Pitch(count);
    }

    private void validateCount(int count) {
        if (countNotInRange(count)) {
            throw new RuntimeException("쓰러뜨릴수 있는 Pin의 갯수는 " + MIN + "이상 " + MAX + "이하여야 합니다.");
        }
    }

    private boolean countNotInRange(int count) {
        return count < MIN || count > MAX;
    }

    public int count() {
        return this.count;
    }
}
