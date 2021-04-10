package bowling.domain;

public enum BowlingRole {
    STRIKE, SPARE, MISS;

    public static BowlingRole valueOf(int first, int second) {
        if (first == 10) {
            return STRIKE;
        }
        if (first + second == 10) {
            return SPARE;
        }
        return MISS;
    }
}
