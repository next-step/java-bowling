package bowling.game.domain;

public class FrameNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 10;

    private final int number;

    private FrameNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("유효하지 않은 번호입니다.");
        }
    }

    public static FrameNumber of(int number) {
        return new FrameNumber(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%02d", number);
    }
}
