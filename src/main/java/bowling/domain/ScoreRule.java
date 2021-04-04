package bowling.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum ScoreRule {
    STRIKE(a -> a == 10),
    SPARE(a -> a == 10),
    GUTTER(a -> a == 0),
    MISS(a -> true);

    private Predicate<Integer> knockOverCount;

    ScoreRule(Predicate<Integer> knockOverCount) {
        this.knockOverCount = knockOverCount;
    }

    public static ScoreRule of(int pin, boolean isFirst) {
        ScoreRule result = Arrays.stream(values())
                .filter(rule -> rule.knockOverCount.test(pin))
                .findFirst()
                .orElse(MISS);

        if (result.equals(ScoreRule.STRIKE) && !isFirst) {
            return ScoreRule.SPARE;
        }
        return result;
    }
}
