package bowling.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum FrameStatus {
    STRIKE(1, (sum) -> sum == Constants.MAX_PIN_COUNT, 2),
    SPARE(2, (sum) -> sum == Constants.MAX_PIN_COUNT, 1),
    MISS(2, (sum) -> sum < Constants.MAX_PIN_COUNT, 0),
    PROGRESS(1, (sum) -> sum < Constants.MAX_PIN_COUNT, 0);

    private final int rollCount;
    private final Function<Integer, Boolean> sum;
    private final int bonusCount;

    FrameStatus(int rollCount, Function<Integer, Boolean> sum, int bonusCount) {
        this.rollCount = rollCount;
        this.sum = sum;
        this.bonusCount = bonusCount;
    }

    public static FrameStatus match(Rolls rolls) {
        return Arrays.stream(FrameStatus.values())
                .filter(result -> isMatch(rolls, result))
                .findFirst()
                .orElseGet(() -> PROGRESS);
    }

    private static boolean isMatch(Rolls rolls, FrameStatus result) {
        return result.rollCount == rolls.size() && result.sum.apply(rolls.sum());
    }

    public Score calculateScore(Rolls rolls) {
        return new Score(rolls.sum(), bonusCount);
    }

    private static class Constants {
        public static final int MAX_PIN_COUNT = 10;
    }
}
