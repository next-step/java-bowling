package bowling.domain.score;

import java.util.function.Function;

public enum ScoreType {
    STRIKE(point -> "X"),
    SPARE(point -> "/"),
    GUTTER(point -> "-"),
    MISS(point -> point.toString());

    private Function<Integer, String> expression;

    ScoreType(Function<Integer, String> expression) {
        this.expression = expression;
    }

    public String pointToScore(int point) {
        return expression.apply(point);
    }
}
