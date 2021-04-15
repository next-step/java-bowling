package bowling.domain;

import java.util.Arrays;

public enum BowlingRule {
    STRIKE("strike", "X"),
    SPARE("spare", "9|/"),
    MISS("miss", "8|1"),
    GUTTER("gutter", "-");

    private final String type;
    private final String score;

    BowlingRule(String type, String score) {
        this.type = type;
        this.score = score;
    }

    public static BowlingRule match(String type) {
        return Arrays.stream(BowlingRule.values())
                .filter(bowlingRule -> bowlingRule.type.equals(type))
                .findFirst()
                .orElse(GUTTER);
    }

}
