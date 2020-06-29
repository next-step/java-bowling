package bowling.model;

public class Pins {

    public static final int MAX_PIN_COUNT = 10;
    public static final int MIN_PIN_COUNT = 0;

    private int pinCount;

    public Pins() {
        this.pinCount = MAX_PIN_COUNT;
    }

    public boolean isRemain(final int count) {
        return pinCount == count;
    }

    public int drop(final int dropPinCount) {
        deductPins(dropPinCount);
        return dropPinCount;
    }

    private void deductPins(final int dropPinCount) {
        if (pinCount < dropPinCount) {
            throw new IllegalStateException("쓰러진 핀의 갯수가 더 많을 수 없습니다.");
        }

        pinCount -= dropPinCount;
    }

    public void reset() {
        this.pinCount = MAX_PIN_COUNT;
    }
}
