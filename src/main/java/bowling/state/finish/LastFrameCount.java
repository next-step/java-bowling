package bowling.state.last;

public class LastFrameCount {
    private static final int MIN = 0;
    private static final int MAX = 3;
    private static final int INCREMENT_VALUE = 1;

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

    public LastFrameCount increase() {
        return of(count + INCREMENT_VALUE);
    }

    public boolean isMax() {
        return count == MAX;
    }

}
