package bowling.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameStatus {
    STRIKE(1, (score) -> score.equals(Constants.TEN)),
    SPARE(2, (score) -> score.equals(Constants.TEN)),
    MISS(2, (score) -> !score.equals(Constants.TEN)),
    PROGRESS(1, (score) -> !score.equals(Constants.TEN));

    private final int count;
    private final Function<Score, Boolean> sum;

    FrameStatus(int count, Function<Score, Boolean> sum) {
        this.count = count;
        this.sum = sum;
    }

    public static FrameStatus match(Rolls rolls) {
        return Arrays.stream(FrameStatus.values())
                .filter(result -> isMatch(rolls, result))
                .findFirst()
                .orElseGet(() -> PROGRESS);
    }

    private static boolean isMatch(Rolls rolls, FrameStatus result) {
        return result.count == rolls.size() && result.sum.apply(rolls.sum());
    }

    private static class Constants {
        public static final Score TEN = new Score(10);
    }
}
