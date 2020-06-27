package bowling.domain.score;

import java.util.function.Function;

public enum  ScoreType {
    STRIKE(point -> "X"),
    SPARE(point -> "/"),
    GUTTER(point -> "-"),
    MISS(point -> point.toString()),
    NORMAL(point -> point.toString());

    private Function<Integer, String> scoreType;

    ScoreType(Function<Integer, String> scoreType) {
        this.scoreType = scoreType;
    }

    public String pointToScore(int point) {
        return scoreType.apply(point);
    }
}
