package bowling.ui;

import bowling.domain.frame.rolling.FinalRollings;
import bowling.domain.frame.rolling.Rolling;

import java.util.Arrays;
import java.util.Optional;

public enum FinalFrameDisplay {

    STRIKE("  X   |", (first, second, third) -> first.isStrike() && second == null && third == null),
    DOUBLE("  XX  |", (first, second, third) -> first.isStrike() && second.isStrike() && third == null),
    TURKEY("  XXX |", (first, second, third) -> first.isStrike() && second.isStrike() && third.isStrike()),

    SPARE_SECOND("  %d/  |", (first, second, third) -> second != null && second.isSpare(first) && third == null),
    MISS_SECOND("  %d%d |", (first, second, third) -> second != null && !second.isGutter() && third == null),
    GUTTER_SECOND("  %d-  |", (first, second, third) -> second != null && second.isGutter() && third == null),

    STRIKE_THIRD_BEFORE_SPARE("  %d/X |", (first, second, third) -> third != null && second.isSpare(first) && third.isStrike()),
    STRIKE_THIRD_BEFORE_GUTTER("  %d-X |", (first, second, third) -> third != null && second.isSpare(first) && third.isStrike()),
    SPARE_THIRD("  %d%d/ |", (first, second, third) -> third != null && third.isSpare(second)),
    MISS_THIRD("  %d%d%d |", (first, second, third) -> third != null && !third.isGutter()),
    GUTTER_THIRD_BEFORE_SPARE("  %d/- |", (first, second, third) -> third != null && second.isSpare(first) && third.isGutter()),
    GUTTER_THIRD("  %d-- |", (first, second, third) -> third != null && third.isGutter()),

    FIRST("  %d   |", (first, second, third) -> !first.isStrike()),
    NONE("      |", (first, second, third) -> first == null);

    private final String display;
    private final FinalFrameOperator operator;

    FinalFrameDisplay(String display, FinalFrameOperator operator) {
        this.display = display;
        this.operator = operator;
    }

    public static String display(FinalRollings finalRollings) {
        Rolling first = finalRollings.first();
        Rolling second = finalRollings.second();
        Rolling third = finalRollings.third();
        return Arrays.stream(FinalFrameDisplay.values())
                .filter(normalFrameDisplay -> normalFrameDisplay.operator.display(first, second, third))
                .findFirst()
                .map(normalFrameDisplay -> display(normalFrameDisplay.display, finalRollings))
                .orElse(NONE.display);

    }

    private static String display(String finalFrameDisplay, FinalRollings finalRollings) {
        Rolling first = finalRollings.first();
        Optional<Rolling> second = Optional.ofNullable(finalRollings.second());
        Optional<Rolling> third = Optional.ofNullable(finalRollings.third());
        return String.format(finalFrameDisplay, first.fallenPin(), second.map(Rolling::fallenPin).orElse(0), third.map(Rolling::fallenPin).orElse(0));
    }

}
