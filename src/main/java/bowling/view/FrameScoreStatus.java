package bowling.view;

import bowling.model.frame.FrameScore;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameScoreStatus {
    STRIKE("  %s   |", FrameScore::isStrike),
    SPARE("  %s|/ |", FrameScore::isSpare),
    MISS("  %s|%s |", FrameScore::isMiss),
    SINGLE_GUTTER("  %s   |", FrameScore::isFirstAndGutter),
    DOUBLE_GUTTER("  %s|%s |", FrameScore::isDoubleGutter),
    NONE("  %s   |", (score) -> score.isEmpty() || score.isFirst());

    private final String displayFormat;
    private final Function<FrameScore, Boolean> operator;

    FrameScoreStatus(String displayFormat, Function<FrameScore, Boolean> operator) {
        this.displayFormat = displayFormat;
        this.operator = operator;
    }

    public static String findDisplayFormat(FrameScore score) {
        return Arrays.stream(FrameScoreStatus.values())
                .filter(frameScoreStatus -> frameScoreStatus.operator.apply(score))
                .findFirst()
                .orElse(NONE)
                .getDisplayFormat();
    }

    private String getDisplayFormat() {
        return displayFormat;
    }
}
