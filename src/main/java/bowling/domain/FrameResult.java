package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameResult {
    STRIKE(1, (score) -> score.equals(Constants.TEN)),
    SPARE(2, (score) -> score.equals(Constants.TEN)),
    MISS(2, (score) -> !score.equals(Constants.ZERO)),
    GUTTER(2, (score) -> score.equals(Constants.ZERO));

    private final int count;
    private final Function<Score, Boolean> isInRange;

    FrameResult(int count, Function<Score, Boolean> rangeIn) {
        this.count = count;
        this.isInRange = rangeIn;
    }

    public static FrameResult match(Scores scores) {
        return Arrays.stream(FrameResult.values())
                .filter(result -> isMatch(scores, result))
                .findFirst()
                .orElseThrow(() -> new BowlingGameException(ErrorMessage.FRAME_RESULT_NOT_EXIST));
    }

    private static boolean isMatch(Scores scores, FrameResult result) {
        return result.count == scores.size() && result.isInRange.apply(scores.sum());
    }

    private static class Constants {
        public static final Score TEN = new Score(10);
        public static final Score ZERO = new Score(0);
    }
}
