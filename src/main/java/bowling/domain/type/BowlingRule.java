package bowling.domain.type;

import java.util.Arrays;

public enum BowlingRule {
    STRIKE(10, false),
    SPARE(10, true),
    MISS(1_9, true),
    GUTTER(0, false),
    PINS_LEFT(-1, false);

    private final int pins;
    private final boolean totalPitch;

    BowlingRule(int pins, boolean totalPitch) {
        this.pins = pins;
        this.totalPitch = totalPitch;
    }

    public static BowlingRule convertForNormalFrame(int pins, boolean firstPitch) {
        if (isStrike(pins, firstPitch)) {
            return STRIKE;
        }

        return Arrays.stream(BowlingRule.values())
                .filter(rule -> BowlingRule.STRIKE != rule)
                .filter(rule -> rule.pins == pins)
                .findAny()
                .orElse(MISS);
    }

    private static boolean isStrike(int pins, boolean firstPitch) {
        return BowlingRule.STRIKE.pins == pins && firstPitch;
    }

    public static BowlingRule convertForFinalFrame(int pins, boolean secondPitch, boolean totalPitch) {
        if (isMiss(pins, secondPitch, totalPitch)) {
            return MISS;
        }

        return Arrays.stream(BowlingRule.values())
                .filter(rule -> BowlingRule.MISS != rule)
                .filter(rule -> rule.pins == pins && rule.totalPitch == totalPitch)
                .findAny()
                .orElse(PINS_LEFT);
    }

    private static boolean isMiss(int pins, boolean secondPitch, boolean totalPitch) {
        if (!secondPitch) {
            return false;
        }

        return BowlingRule.SPARE.pins > pins
                && BowlingRule.GUTTER.pins < pins
                && totalPitch;
    }
}
