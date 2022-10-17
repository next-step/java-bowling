package bowling.domain;

public class Pin {
    private final int value;

    public Pin(int value) {
        if (value > 10 || value < 0) {
            throw new IllegalArgumentException("점수는 0부터 10까지만 입력할 수 있습니다.");
        }
        this.value = value;
    }

    public boolean isStrike() {
        return this.value == 10;
    }

    public int getValue() {
        return value;
    }

}
