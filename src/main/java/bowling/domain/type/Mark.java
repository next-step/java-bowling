package bowling.domain.type;

import bowling.annotations.GetterForUI;

import java.util.Arrays;

public enum Mark {
    STRIKE(10, "X"),
    SPARE(10, "/"),
    MISS(-1, ""),
    GUTTER(0, "-");

    private final int pins;
    private final String mark;

    Mark(int pins, String mark) {
        this.pins = pins;
        this.mark = mark;
    }

    public static Mark convert(int pins, boolean firstPitch) {
        if (isStrike(pins, firstPitch)) {
            return STRIKE;
        }

        return Arrays.stream(Mark.values())
                .filter(rule -> Mark.STRIKE != rule)
                .filter(rule -> rule.pins == pins)
                .findAny()
                .orElse(MISS);
    }

    private static boolean isStrike(int pins, boolean firstPitch) {
        return Mark.STRIKE.pins == pins && firstPitch;
    }

    @GetterForUI
    public String getMark() {
        return mark;
    }
}
