package bowling.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Rule {
    STRIKE((a, b) -> a == 10, true),
    SPARE((a, b) -> (a+b) == 10, false),
    FIRST_GUTTER((a, b) -> a == 0, true),
    SECOND_GUTTER((a, b) -> b == 0, false),
    MISS((a, b) -> true, false);

    private BiPredicate<Integer, Integer> knockOverCount;
    private boolean isFirstBall;

    Rule(BiPredicate<Integer, Integer> knockOverCount, boolean isFirstBall) {
        this.knockOverCount = knockOverCount;
        this.isFirstBall = isFirstBall;
    }

    public static Rule of(Pin pin, boolean isFirstBall) {
        return Arrays.stream(values())
                .filter(score -> pin.checkOfDownPin(score.knockOverCount) && score.isFirstBall == isFirstBall)
                .findFirst()
                .orElse(MISS);
    }
}
