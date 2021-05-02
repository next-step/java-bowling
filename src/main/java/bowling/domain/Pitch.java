package bowling.domain;

public class Pitch {

    public static final int STRIKE_COUNT = 10;
    private static final int GUTTER_COUNT = 0;

    private final int value;

    public Pitch(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < GUTTER_COUNT || value > STRIKE_COUNT) {
            throw new IllegalArgumentException("핀 처리 갯수는 " + GUTTER_COUNT + " 이상 " + STRIKE_COUNT + " 이하의 수 여야 합니다.");
        }
    }

    public int value() {
        return this.value;
    }

    public boolean isStrike() {
        return this.value == STRIKE_COUNT;
    }

}
