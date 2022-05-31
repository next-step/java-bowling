package bowling.domain.game;

public class Turn {
    private static final int MIN = 0;

    private final int index;
    private final int max;

    public Turn(int index, int max) {
        this.index = index;
        this.max = max;
    }

    public Turn next() {
        if (index == max) {
            return new Turn(MIN, max);
        }

        return new Turn(index + 1, max);
    }

    public int index() {
        return index;
    }

    public boolean isLast() {
        return index == max;
    }

    public static Turn initialize(int gameCount) {
        return new Turn(0, gameCount - 1);
    }
}
