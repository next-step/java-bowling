package bowling.domain.type;

import java.util.Arrays;

public enum BowlingRule {
    STRIKE(10),
    SPARE(10),
    MISS(1_9),
    GUTTER(0);

    private final int pinCount;

    BowlingRule(int pinCount) {
        this.pinCount = pinCount;
    }

    public static BowlingRule convertRuleByPinCount(int pinCount, boolean totalPitch) {
        if (isSpare(pinCount, totalPitch)) {
            return SPARE;
        }

        return Arrays.stream(BowlingRule.values())
                .filter(rule -> BowlingRule.SPARE != rule)
                .filter(rule -> rule.pinCount == pinCount)
                .findAny()
                .orElse(MISS);
    }

    private static boolean isSpare(int pinCount, boolean totalPitch) {
        return BowlingRule.SPARE.pinCount == pinCount && totalPitch;
    }
}
