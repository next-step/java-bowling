package bowling.domain;

public enum ScoreType {
    STRIKE, SPARE, MISS, GUTTER, SECOND, NULL;

    public static ScoreType of(int first) {
        if (first == 10) {
            return STRIKE;
        }
        return SECOND;
    }

    public static ScoreType of(int first, int second) {
        int total = first + second;
        if (total == 10) {
            return SPARE;
        }
        if (total == 0) {
            return GUTTER;
        }
        if (total < 10) {
            return MISS;
        }
        throw new RuntimeException("unreachable score: " + total);
    }
}
