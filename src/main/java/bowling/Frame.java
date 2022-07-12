package bowling;

public class Frame {
    private int pinCount = 10;
    private int attemptCount = 1;

    public Frame(int count) {
        this.pinCount -= count;

        if (this.pinCount < 0) {
            throw new IllegalArgumentException("핀은 10개 이상 넘어갈 수 없습니다");
        }
        attemptCount++;
    }

    public boolean isStrike() {
        return this.pinCount == 0;
    }
}
