package bowling.domain.pitch;

import bowling.domain.exception.OutOfRangeArgumentException;

public class Pin {
    private static final String OUT_OF_RANGE_ERROR_MESSAGE =
            "핀 갯수는 %d에서 %d 사이여야 합니다.";
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Pin[] cache = new Pin[11];

    static {
        for (int i = MIN; i <= MAX; i++) {
            cache[i] = new Pin(i);
        }
    }

    private int count;

    private Pin(int count) {
        this.count = count;
    }

    public boolean isMin() {
        return count == MIN;
    }

    public boolean isMax() {
        return count == MAX;
    }

    public int getCount() {
        return count;
    }

    public boolean isInRangeAfterAdd(Pin pin) {
        return isInRange(this.count + pin.count);
    }

    public Pin add(Pin pin) {
        return valueOf(count + pin.count);
    }

    public static Pin valueOf(int count) {
        if (!isInRange(count)) {
            throw new OutOfRangeArgumentException(
                    String.format(OUT_OF_RANGE_ERROR_MESSAGE, MIN, MAX));
        }

        return cache[count];
    }

    private static boolean isInRange(int count) {
        return count >= MIN && count <= MAX;
    }
}
