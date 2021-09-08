package bowling.ui;

import bowling.domain.frame.rolling.NormalRollings;
import bowling.domain.frame.rolling.Rolling;

import java.util.Arrays;
import java.util.Optional;

public enum NormalFrameDisplay {

    STRIKE("  X   |", (first, second) -> first.isStrike()),
    SPARE("  %d|/ |", (first, second) -> second != null && second.isSpare(first)),
    MISS("  %d|%d |", (first, second) -> second != null && !second.isGutter()),
    GUTTER("  %d|- |", (first, second) -> second != null && second.isGutter()),
    FIRST("  %d   |", (first, second) -> !first.isStrike()),
    NONE("      |", (first, second) -> first == null);

    private final String display;
    private final NormalFrameOperator operator;

    NormalFrameDisplay(String display, NormalFrameOperator operator) {
        this.display = display;
        this.operator = operator;
    }

    public static String display(NormalRollings normalRollings) {
        Rolling first = normalRollings.first();
        Rolling second = normalRollings.second();
        return Arrays.stream(NormalFrameDisplay.values())
                .filter(normalFrameDisplay -> normalFrameDisplay.operator.display(first, second))
                .findFirst()
                .map(normalFrameDisplay -> display(normalFrameDisplay.display, normalRollings))
                .orElse(NONE.display);

    }

    private static String display(String normalFrameDisplay, NormalRollings normalRollings) {
        Rolling first = normalRollings.first();
        Optional<Rolling> second = Optional.ofNullable(normalRollings.second());
        return String.format(normalFrameDisplay, first.fallenPin(), second.map(Rolling::fallenPin).orElse(0));
    }

}
