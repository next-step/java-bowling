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
    private final Function<Pin, Boolean> isInRange;

    FrameResult(int count, Function<Pin, Boolean> rangeIn) {
        this.count = count;
        this.isInRange = rangeIn;
    }

    public static FrameResult match(Rolls rolls) {
        return Arrays.stream(FrameResult.values())
                .filter(result -> isMatch(rolls, result))
                .findFirst()
                .orElseThrow(() -> new BowlingGameException(ErrorMessage.FRAME_RESULT_NOT_EXIST));
    }

    private static boolean isMatch(Rolls rolls, FrameResult result) {
        return result.count == rolls.size() && result.isInRange.apply(rolls.sum());
    }

    private static class Constants {
        public static final Pin TEN = new Pin(10);
        public static final Pin ZERO = new Pin(0);
    }
}
