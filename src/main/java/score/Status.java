package score;

import java.util.Arrays;

public enum Status {
    STRIKE(Status::isStrike),
    SPARE(Status::isSpare),
    MISS(Status::isMiss),
    GUTTER(Status::isGutter);

    private static final int STRIKE_SCORE = 10;
    private static final int GUTTER_SCORE = 0;

    private StatusMatcher matcher;

    Status(StatusMatcher matcher) {
        this.matcher = matcher;
    }

    public static Status findStatus(int score, int sum) {
        return Arrays.stream(values())
                .filter(status -> status.match(score, sum))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태값 입니다."));
    }

    private boolean match(int score, int sum) {
        return matcher.match(score, sum);
    }

    private static boolean isStrike(int score, int sum) {
        return score == STRIKE_SCORE;
    }

    private static boolean isSpare(int score, int sum) {
        return (score != STRIKE_SCORE) && (sum == STRIKE_SCORE);
    }

    private static boolean isMiss(int score, int sum) {
        return (score != GUTTER_SCORE) && (GUTTER_SCORE < sum) && (sum < STRIKE_SCORE);
    }

    private static boolean isGutter(int score, int sum) {
        return score == GUTTER_SCORE;
    }
}
