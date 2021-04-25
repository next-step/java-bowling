package bowling.domain;

public class Pitch {

    private static final int MIN_DOWN_COUNT = 0;
    private static final int MAX_DOWN_COUNT = 10;

    private final int value;

    public Pitch(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MIN_DOWN_COUNT || value > MAX_DOWN_COUNT) {
            throw new IllegalArgumentException("핀 처리 갯수는 " + MIN_DOWN_COUNT + " 이상 " + MAX_DOWN_COUNT + " 이하의 수 여야 합니다.");
        }
    }

    public int value() {
        return this.value;
    }
}
