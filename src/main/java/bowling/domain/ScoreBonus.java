package bowling.domain;

public class ScoreBonus {
    private static final int BONUS_INIT = -1;
    private static final int MAX = 2;
    private static final int MIN = 0;
    private static final int FIRST_OR_SPARE = 1;
    private static final int DECREASE = -1;

    private final int count;

    private ScoreBonus(int count) {
        this.count = count;
    }

    public static ScoreBonus of(int count) {
        return new ScoreBonus(count);
    }

    public static ScoreBonus init() {
        return new ScoreBonus(BONUS_INIT);
    }

    public static ScoreBonus display() {
        return new ScoreBonus(MIN);
    }

    public static ScoreBonus oneMore() {
        return new ScoreBonus(FIRST_OR_SPARE);
    }

    public static ScoreBonus max() {
        return new ScoreBonus(MAX);
    }

    public boolean noLeft() {
        return count == MIN;
    }

    public ScoreBonus decrease() {
        return ScoreBonus.of(count + DECREASE);
    }
}
