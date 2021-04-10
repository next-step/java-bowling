package bowling.domain;

public enum BowlingRole {
    STRIKE, SPARE, MISS;

    private static final int POINT_MAX_BOUND = 10;

    public static BowlingRole valueOf(int first, int second) {
        if (first == POINT_MAX_BOUND) {
            return STRIKE;
        }
        if (first + second == POINT_MAX_BOUND) {
            return SPARE;
        }
        return MISS;
    }
}
