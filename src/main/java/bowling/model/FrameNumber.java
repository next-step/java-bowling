package bowling.model;

public class FrameNumber {
    private static final int MIN = 1;
    private static final int MAX = 10;

    private final int number;

    public FrameNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException(String.format("프레임 번호는 %d이상 %d 이하이어야 합니다.", MIN, MAX));
        }
    }
}
