package bowling.state.finish;

public class LastFrameCount {
    private static final int MIN = 0;
    private static final int MAX = 3;

    private int count;

    private LastFrameCount(int count) {
        this.count = count;
    }

    public static LastFrameCount of() {
        return new LastFrameCount(MIN);
    }

    private static LastFrameCount of(int count) {
        return new LastFrameCount(count);
    }

    public void increase() {
        count++;
        if (count > MAX) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isMax() {
        return count == MAX;
    }

    public int getCount() {
        return count;
    }
}
