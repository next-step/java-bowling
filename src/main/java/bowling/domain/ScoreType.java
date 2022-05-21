package bowling.domain;

public enum ScoreType {
    STRIKE, SPARE, MISS, GUTTER, SECOND, INIT;

    public static ScoreType of(int hit) {
        if (hit == 10) {
            return STRIKE;
        }
        return SECOND;
    }
}
