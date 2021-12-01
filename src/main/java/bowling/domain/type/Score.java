package bowling.domain.type;

import bowling.annotations.GetterForUI;

import java.util.Arrays;

public enum Score {
    STRIKE(10, "X"),
    SPARE(10, "/"),
    MISS(-1, ""),
    GUTTER(0, "-");

    private final int pins;
    private final String mark;

    Score(int pins, String mark) {
        this.pins = pins;
        this.mark = mark;
    }

    public static Score convert(int pins, boolean firstPitch) {
        if (isStrike(pins, firstPitch)) {
            return STRIKE;
        }

        return Arrays.stream(Score.values())
                .filter(rule -> Score.STRIKE != rule)
                .filter(rule -> rule.pins == pins)
                .findAny()
                .orElse(MISS);
    }

    private static boolean isStrike(int pins, boolean firstPitch) {
        return Score.STRIKE.pins == pins && firstPitch;
    }

    @GetterForUI
    public String getMark() {
        return mark;
    }
}
