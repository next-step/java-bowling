package bowling;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameResult {
    STRIKE(1, (score) -> score.equals(new Score(10))),
    SPARE(2, (score) -> score.equals(new Score(10))),
    MISS(2, (score) -> !score.equals(new Score(0))),
    GUTTER(2, (score) -> score.equals(new Score(0)));

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
}
