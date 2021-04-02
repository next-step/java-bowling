package bowling.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum ScoreEnum {
    STRIKE((a, b) -> a == 10, true),
    GUTTER((a, b) -> a == 0, false),
    MISS((a, b) -> (a+b) < 10, false),
    SPARE((a, b) -> (a+b) == 10, false);

    private BiPredicate<Integer, Integer> knockOverCount;
    private boolean isFirst;

    ScoreEnum(BiPredicate<Integer, Integer> knockOverCount, boolean isFirst) {
        this.knockOverCount = knockOverCount;
        this.isFirst = isFirst;
    }

    public static ScoreEnum of(int firstBall, int secondBall) {
        return Arrays.stream(values())
                .filter(score -> score.knockOverCount(firstBall, secondBall))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private boolean knockOverCount(int firstBall, int secondBall) {
        return knockOverCount.test(firstBall, secondBall);
    }


}
