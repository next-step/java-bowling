package bowling.view;

import bowling.model.frame.FrameFallenPin;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameFallenPinStatus {
    STRIKE("  %s   |", FrameFallenPin::isStrike),
    SPARE("  %s|/ |", FrameFallenPin::isSpare),
    MISS("  %s|%s |", FrameFallenPin::isMiss),
    SINGLE_GUTTER("  %s   |", FrameFallenPin::isFirstAndGutter),
    DOUBLE_GUTTER("  %s|%s |", FrameFallenPin::isDoubleGutter),
    NONE("  %s   |", (fallenPin) -> fallenPin.isEmpty() || fallenPin.isFirst());

    private final String displayFormat;
    private final Function<FrameFallenPin, Boolean> operator;

    FrameFallenPinStatus(String displayFormat, Function<FrameFallenPin, Boolean> operator) {
        this.displayFormat = displayFormat;
        this.operator = operator;
    }

    public static String findDisplayFormat(FrameFallenPin fallenPin) {
        return Arrays.stream(FrameFallenPinStatus.values())
                .filter(frameFallenPinStatus -> frameFallenPinStatus.operator.apply(fallenPin))
                .findFirst()
                .orElse(NONE)
                .getDisplayFormat();
    }

    private String getDisplayFormat() {
        return displayFormat;
    }
}
