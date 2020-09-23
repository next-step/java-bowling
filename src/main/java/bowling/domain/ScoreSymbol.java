package bowling.domain;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiPredicate;

public enum ScoreSymbol {
    STRIKE("X", (score, isFirstTurn) -> score == 10 && isFirstTurn),
    SPARE("/", (score, isFirstTurn) -> score == 10 && !isFirstTurn),
    GUTTER("-", (score, isFirstTurn) -> score == 0 && !isFirstTurn),
    MISS("", (score, isFirstTurn) -> true);

    private final String value;
    private final BiPredicate<Integer, Boolean> expression;

    public static final Set<ScoreSymbol> tenScore = EnumSet.of(STRIKE, SPARE);

    ScoreSymbol(String value, BiPredicate<Integer, Boolean> expression) {
        this.value = value;
        this.expression = expression;
    }

    public static ScoreSymbol valueOf(int score, boolean isFirstTurn) {
        return Arrays.stream(values())
                .filter(it -> it.expression.test(score, isFirstTurn))
                .findFirst()
                .orElse(MISS);
    }

    public String getValue() {
        return value;
    }
}
