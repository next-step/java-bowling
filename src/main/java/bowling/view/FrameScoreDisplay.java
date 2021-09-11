package bowling.view;

import bowling.model.frame.Score;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameScoreDisplay {
    ONE_DIGIT("  %d   |", Score::isOneDigit),
    TWO_DIGIT("  %d  |", Score::isTwoDigit),
    THREE_DIGIT("  %d |", Score::isThreeDigit);

    private final String displayFormat;
    private final Function<Score, Boolean> operator;

    FrameScoreDisplay(String displayFormat, Function<Score, Boolean> operator) {
        this.displayFormat = displayFormat;
        this.operator = operator;
    }

    public static String findDisplayFormat(Score score) {
        return Arrays.stream(FrameScoreDisplay.values())
                .filter(frameScoreDisplay -> frameScoreDisplay.operator.apply(score))
                .findFirst()
                .orElse(THREE_DIGIT)
                .displayFormat;
    }
}
