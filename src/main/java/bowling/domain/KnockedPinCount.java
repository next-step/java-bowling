package bowling.domain;

public class KnockedPinCount {
    public static final KnockedPinCount TEN_COUNT = new KnockedPinCount(10);
    public static final String INVALID_KNOCK_OUT_COUNT_MESSAGE = "쓰러뜨린 핀의 개수는 0~10개입니다.";

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 10;

    private final int value;

    public KnockedPinCount(int count) {
        if (count < MIN_VALUE || count > MAX_VALUE) {
            throw new IllegalArgumentException(INVALID_KNOCK_OUT_COUNT_MESSAGE);
        }
        value = count;
    }

    public KnockedPinCount sum(KnockedPinCount knockOutCount) {
        return new KnockedPinCount(value + knockOutCount.value);
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KnockedPinCount that = (KnockedPinCount) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
