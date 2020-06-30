package bowling.domain;

public class Pin {
    public static final int MAX_COUNT = 10;
    private final int count;

    public Pin(int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(int count) {
        if (count > MAX_COUNT) {
            throw new IllegalArgumentException("pin count can not more than " + MAX_COUNT);
        }
    }

    public int getCount() {
        return count;
    }
}
