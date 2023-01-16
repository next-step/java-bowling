package bowling.refactoring.domain;

public class Pins {
    static final String SIZE_EXCEPTION = "볼링핀의 갯수는 0-10사이의 숫자가 입력되어야 합니다.";
    private static final int STRIKE = 10;

    private final int pins;

    public Pins(int count) {
        validate(count);
        this.pins = count;
    }

    private void validate(int count) {
        if (count < 0 || count > 10) {
            throw new IllegalArgumentException(SIZE_EXCEPTION);
        }
    }

    public boolean isStrike() {
        return this.pins == STRIKE;
    }

    public int count() {
        return pins;
    }
}
