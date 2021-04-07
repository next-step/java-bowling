package bowling.domain;

import java.util.Arrays;

public enum ScoreRule {
    STRIKE(10, "x", 2),
    SPARE(10, "/", 1),
    GUTTER(0, "-", 0),
    MISS(-1, "", 0);

    private int count;
    public String symbol;
    public int bonusChance;

    ScoreRule(int count, String symbol,int bonusChance) {
        this.count = count;
        this.symbol = symbol;
        this.bonusChance = bonusChance;
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
