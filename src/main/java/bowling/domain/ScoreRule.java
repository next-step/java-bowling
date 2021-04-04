package bowling.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ScoreRule {
    STRIKE(a -> a == 10, "x"),
    SPARE(a -> a == 10, "/"),
    GUTTER(a -> a == 0, "-"),
    MISS(a -> true, "");

    private Predicate<Integer> knockOverCount;
    public String symbol;

    ScoreRule(Predicate<Integer> knockOverCount, String symbol) {
        this.knockOverCount = knockOverCount;
        this.symbol = symbol;
    }

    public static ScoreRule of(int pin, boolean isFirst) {
        ScoreRule result = Arrays.stream(values())
                .filter(rule -> rule.knockOverCount.test(pin))
                .findFirst()
                .orElse(MISS);

        if (result == ScoreRule.STRIKE && !isFirst) {
            return ScoreRule.SPARE;
        }
        return result;
    }
}
