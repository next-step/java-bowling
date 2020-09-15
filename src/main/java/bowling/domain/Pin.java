package bowling.domain;

public class Pin {
    public static final int FULL_COUNT = 10;
    public static final int NO_COUNT = 0;

    private int count;

    private Pin(int count) {
        this.count = count;
    }

    public static Pin from() {
        return new Pin(FULL_COUNT);
    }

    public static Pin of(int count) {
        return new Pin(count);
    }

    public Pin roll(int count) {
        if (this.count + count > FULL_COUNT || count > this.count) {
            throw new IllegalArgumentException("더이상 쓰러질 핀이 없습니다.");
        }

        return new Pin(count);
    }

    public boolean isStrike() {
        return count == FULL_COUNT;
    }

    public boolean isSpare(int count) {
        return this.count + count == FULL_COUNT;
    }

    public boolean isGutter() {
        return count == NO_COUNT;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
