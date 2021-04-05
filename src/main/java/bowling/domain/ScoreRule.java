package bowling.domain;

import java.util.Arrays;

public enum ScoreRule {
    STRIKE(10, "x"),
    SPARE(10, "/"),
    GUTTER(0, "-"),
    MISS(-1, "");

    private int count;
    public String symbol;

    ScoreRule(int count, String symbol) {
        this.count = count;
        this.symbol = symbol;
    }

    public static ScoreRule of(int pin, boolean isFirst) {
        ScoreRule result = Arrays.stream(values())
                .filter(rule -> rule.count == pin)
                .findFirst()
                .orElse(MISS);

        if (result == ScoreRule.STRIKE && !isFirst) {
            return ScoreRule.SPARE;
        }
        return result;
    }
}
