package bowling.domain;

import bowling.exception.InputException;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ScoreType {
    STRIKE("X", (order, score) -> order == 1 && score == 10),
    SPARE("9 | /", (order, score) -> order == 2 && score == 10),
    MISS("8 | 1", (order, score) -> order == 2 && score < 10 && score > 0),
    GUTTER("-", (order, score) -> order == 2 && score == 0);

    private String symbol;
    private BiPredicate<Integer, Integer> predicate;

    ScoreType(String symbol, BiPredicate<Integer, Integer> predicate) {
        this.symbol = symbol;
        this.predicate = predicate;
    }

    static ScoreType of(int order, int score) {
        return Arrays.stream(ScoreType.values())
                .filter(type -> type.predicate.test(order, score))
                .findFirst()
                .orElseThrow(() -> new InputException("올바르지 않는 입력값입니다."));
    }

    public String getSymbol() {
        return symbol;
    }

}
