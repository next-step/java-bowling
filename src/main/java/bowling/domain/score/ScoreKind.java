package bowling.domain.score;

import java.util.Arrays;
import java.util.function.Function;

public enum ScoreKind {
    STRIKE(pin -> "X"),
    SPARE(pin -> "/"),
    GUTTER(pin -> "-"),
    MISS(Object::toString);

    private final Function<Integer, String> expression;

    ScoreKind(Function<Integer, String> expression) {
        this.expression = expression;
    }

    public String pinToScore(int pin) {
        return expression.apply(pin);
    }

    public boolean isClear(ScoreKind scoreKind) {
        return Arrays.asList(STRIKE, SPARE).contains(scoreKind);
    }
}
