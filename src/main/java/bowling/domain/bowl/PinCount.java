package bowling.domain.bowl;

public class PinCount {
    public static final int MIN = 0;
    public static final int MAX = 10;

    private final int pinCount;

    public PinCount(int pinCount) {
        validate(pinCount);
        this.pinCount = pinCount;
    }

    private void validate(int pinCount) {
        if (pinCount < MIN) {
            throw new IllegalArgumentException(String.format("pinCount(%s)는 최솟값(%s) 미만 일 수 없습니다.", pinCount, MIN));
        }
        if (pinCount > MAX) {
            throw new IllegalArgumentException(String.format("pinCount(%s)는 최댓값(%s) 초과 일 수 없습니다.", pinCount, MAX));
        }
    }

}
